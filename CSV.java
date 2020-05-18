import java.io.FileReader;
import java.io.FileWriter;
import java.util.Date;

abstract class Column {
	public String title;
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
}

enum Epoch {
	MCM,   // Microsoft Excel date system
	MCMIV, // Microsoft Excel 1904-based date system
	UNIX,  // Standard Unix Time
}

class DateColumn extends Column {
	public Epoch sys = Epoch.UNIX;
	public DateColumn(String title) {super(title);}

	public Object read(String data) {
		long millisecond = Long.parseLong(data);
		Date date = new Date(millisecond);
		return date;
	}
	public String write(Object entity) {
		return "hi";
	}
	public String write(Date entity) {
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
	public StringColumn(String title) {super(title);}
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
		// System.out.print(first);
		// System.out.print(last);
		// System.out.println();
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
	public void write(String filename, Object[] data) {
		if (filename.contains(".")) {
			int dot = filename.lastIndexOf(".");
			String ext = filename.substring(dot+1);
			filename = filename.substring(0,dot);
			write(filename,ext,data);
		} else {
			write(filename,"csv",data);
		}
	}
	public void write(String filename, String ext, Object[] data) {
		System.out.println(filename+"."+ext);
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
		// System.out.println(row);
		// System.out.println();
		if (!row.contains(",")) {
			return StringColumn.desanitize(row);
		}
		int comma = nextValidComma(row);
		if (comma == -1) {
			return StringColumn.desanitize(row);
		}
		String data = row.substring(0,comma);
		data = StringColumn.desanitize(data);
		// System.out.println(comma);
		row = row.substring(comma+1);
		rowref[0] = row;
		return data;
		// System.out.println(data);
	}
	public Object read(String filename_ext) {return null;}
	public static void main(String[] args) {
		DateColumn con = new DateColumn("content");
		con.sys = Epoch.MCM;
		System.out.println(con.sys);
	}
}


