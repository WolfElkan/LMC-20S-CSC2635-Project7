import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.util.Date;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.lang.reflect.Field;

abstract class Column {
	public String title;
	public String header() {
		return title;
	}
	public Column(String title) {
		this.title = title;
	}
	public Column() {
		this.title = "unnamed";
	}
	public abstract Object read(String data);
	public abstract String write(Object entity);
	public boolean test(String data) {
		Object entity = read(data);
		return data.equals(write(entity));
	}
	public boolean test(Object entity) {
		String data = write(entity);
		return entity.equals(read(data));
	}
	public static Column infer(String header) {
		// System.out.print(StringColumn.regex);
		// System.out.print(' ');
		// System.out.println(header);
		// System.out.println();
		Column column = new StringColumn("");

		Pattern stringPattern = Pattern.compile(StringColumn.regex);
		Matcher stringMatcher = stringPattern.matcher(header);
		if (stringMatcher.find()) {
			column = new StringColumn(stringMatcher.group("title"));
		}
		Pattern datePattern = Pattern.compile(DateColumn.regex);
		Matcher dateMatcher = datePattern.matcher(header);
		if (dateMatcher.find()) {
			column = new DateColumn(
				dateMatcher.group("title"),
				Epoch.valueOf(dateMatcher.group("sys"))
			);
		}
		return column;
	}
}

class DateColumn extends Column {
	public static String regex = "<DateColumn:(?<sys>[A-Z]+)>(?<title>[a-zA-Z ]+)";
	public Epoch sys;
	public DateColumn(String title, Epoch sys) {
		super(title);
		this.sys = sys;
	}
	public DateColumn(String title) {
		super(title);
		this.sys = Epoch.UNIX;
	}
	public String header() {
		return "<DateColumn:"+sys+">" + title;
	}
	public Object read(String data) {
		long millisecond = Long.parseLong(data);
		Date date = new Date(millisecond);
		return date;
	}
	public String write(Object entity) {
		ExcelDate date = (ExcelDate) entity;
		return write(date);
	}
	public String write(Date entity) {
		ExcelDate date = (ExcelDate) entity;
		return write(date);
	}
	public String write(ExcelDate entity) {
		long unix = entity.getTime();
		if (sys == Epoch.UNIX) {
			return Long.toString(unix);
		} else {
			ExcelDate excel = new ExcelDate(unix);
			double serial = 0;
			if (sys == Epoch.MCM) {
				serial = excel.Excel1900();
			} else if (sys == Epoch.MCMIV) {
				serial = excel.Excel1904();
			}
			return Double.toString(serial);
		}
	}
}

class StringColumn extends Column {
	public static String regex = "<StringColumn>(?<title>[a-zA-Z ]+)";
	public StringColumn(String title) {super(title);}
	public String header() {
		return "<StringColumn>" + title;
	}
	public Object read(String data) {
		return data;
	}
	public String write(Object entity) {
		String data = entity.toString();
		data = sanitize(data);
		return data;
	}
	public static String sanitize(String text) {
		if (text.contains(",")) {
			return "\""+text+"\"";
		} else {
			return text;
		}
	}
	public static String desanitize(String text) {
		int len = text.length();
		String first = text.substring(0,1);
		String last  = text.substring(len-1,len);
		if (first.equals("\"") && last.equals("\"")){
			return text.substring(1,len-1);
		} else {
			return text;
		}
	}
}

class CSV {
	public Column[] columns;
	public CSV(Column[] columns) {
		this.columns = columns;
	}
	public CSV(String head) {
		Column[] columns = new Column[26];
		int c = 0;
		while (!head.isEmpty() && c < 5) {
			// System.out.print("----");
			// System.out.println(head);
			int comma = nextValidComma(head);
			String header;
			if (comma >= 0) {
				header = head.substring(0,comma);
			} else {
				header = head;
			}
			columns[c++] = Column.infer(header);
			head = head.substring(comma+1);
		}
		this.columns = columns;
	}
	public void write(String filename, Mailbox mail) throws IOException {
		if (filename.contains(".")) {
			int dot = filename.lastIndexOf(".");
			String ext = filename.substring(dot+1);
			filename = filename.substring(0,dot);
			write(filename,ext,mail);
		} else {
			write(filename,"csv",mail);
		}
	}
	public void write(String filename, String ext, Mailbox mail) throws IOException {
		System.out.print("File Written: ");
		System.out.println(filename+"."+ext);

		FileWriter file = new FileWriter(filename+"."+ext);
		for (int c=0; c<5; c++) {
			file.write(columns[c].header());
			if (c < 4) {
				file.write(',');
			} else {
				file.write('\n');
			}
		}
		for (int i=0; i<mail.lContent; i++) {
			mail.content[i].write(file, columns);
		}
		file.close();
	}
	public static int nextValidComma(String row, int from) {
		int comma = row.indexOf(",", from);
		int quote = row.indexOf("\"", from);
		if (comma == -1) {
			return -1;
			// return row.length();
		} else if (comma < quote || quote == -1) {
			return comma;
		} else {
			int endquote = row.indexOf("\"", quote+1);
			return nextValidComma(row, endquote);
		}
	}
	public static int nextValidComma(String row) {
		return nextValidComma(row, 0);
	}
	public static String getNext(String[] rowref) {
		String row = rowref[0];
		if (!row.contains(",")) {
			return StringColumn.desanitize(row);
		}
		int comma = nextValidComma(row);
		if (comma == -1) {
			return StringColumn.desanitize(row);
		}
		String data = row.substring(0,comma);
		data = StringColumn.desanitize(data);
		row = row.substring(comma+1);
		rowref[0] = row;
		return data;
	}
	public Object read(String filename_ext) {return null;}
	public static void main(String[] args) {
		DateColumn con = new DateColumn("content", Epoch.MCM);
		con.sys = Epoch.MCM;
		Field[] fields = Serializable.class.getFields();
		System.out.println(fields.length);
	}
}


