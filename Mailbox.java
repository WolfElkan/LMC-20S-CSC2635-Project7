import java.io.File;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;

class Mailbox {
	public Email[] content = new Email[256];
	public int lContent = 0; // length of content
	public int sortstate = 0; // how is Mailbox currently sorted
	public void add(Email email) {
		content[lContent++] = email;
	}
	public Mailbox() {}
	public Mailbox(FileReader file) throws IOException {
		char[] cbuf = {};
		file.read(cbuf);
		System.out.println(cbuf);

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
		FileWriter file = new FileWriter(filename+"."+ext);
		for (int c=0; c<5; c++) {
			file.write(columns[c].header());
			if (c < 4) {
				file.write(',');
			} else {
				file.write('\n');
			}
		}
		for (int i=0; i<lContent; i++) {
			content[i].write(file, columns);
		}
		file.close();
	}
}