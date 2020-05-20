import java.io.File;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;
import java.io.Serializable;
import java.util.Scanner;

class Mailbox implements Serializable {
	public Email[] content = new Email[256];
	public int lContent = 0; // length of content
	public int sortstate = 0; // how is Mailbox currently sorted
	public void add(Email email) {
		content[lContent++] = email;
	}
	public Mailbox() {}
	public Mailbox(Scanner file) throws IOException {
		// char[] cbuf = {};
		// file.read(cbuf);
		// System.out.println(file.nextLine());
		CSV csv = new CSV(file.nextLine());

	}
	public void writeCSV(String filename) throws IOException {
		if (filename.contains(".")) {
			int dot = filename.lastIndexOf(".");
			String ext = filename.substring(dot+1);
			filename = filename.substring(0,dot);
			writeCSV(filename,ext);
		} else {
			writeCSV(filename,"csv");
		}
	}
	public void writeCSV(String filename, String ext) throws IOException {
			StringColumn senColumn = new StringColumn("Sender");
			StringColumn recColumn = new StringColumn("Recipient");
			DateColumn  dateColumn = new DateColumn("Date");
			StringColumn subColumn = new StringColumn("Subject");
			StringColumn conColumn = new StringColumn("Content");
		dateColumn.sys = Epoch.MCM;
		Column[] columns = {
			senColumn,
			recColumn,
			dateColumn,
			subColumn,
			conColumn,
		};
		CSV csv = new CSV(columns);
		csv.write(filename, ext, this);
	}
}