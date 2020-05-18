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
	public Object read(String filename_ext) {return null;}
	public static void main(String[] args) {
		DateColumn con = new DateColumn("content");
		con.sys = Epoch.MCM;
		System.out.println(con.sys);
	}
}


