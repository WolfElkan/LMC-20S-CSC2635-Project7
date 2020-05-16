import java.io.FileReader;
import java.io.FileWriter;

abstract class Column {
	public String title;
	public abstract Object read(String data);
	public abstract String write(Object entity);
	public boolean test(String data) {
		Object entity = read(data);
		return data == write(entity);
	}
	public boolean test(Object entity) {
		String data = write(entity);
		return entity.equals(read(data));
	}
}

class DateColumn extends Column {
	public String title = "date";
	public Object read(String data) {
		return "Hello";
	}
	public String write(Object entity) {
		// return Integer.toString(entity.date.getTime());
		return "hi";
	}
}

class CSV {
	public Column[] columns;
	public void write(String filename) {
		if (filename.contains(".")) {
			System.out.println("yes");

		} else {
			write(filename,"csv");
		}
	}
	public void write(String filename, String ext) {
		System.out.println(filename+"."+ext);
	}
}