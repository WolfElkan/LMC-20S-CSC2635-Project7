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
		System.out.println("Mailbox.java:17");
		CSV csv = new CSV(file.nextLine());
		while (file.hasNextLine()) {
			add(new Email(file.nextLine(),csv));
		}
	}
	public void writeCSV(String filename) throws IOException {
		System.out.println("Mailbox.java:24");
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
		System.out.println("Mailbox.java:35");
		Column[] columns = {
			new StringColumn("Sender"),
			new StringColumn("Recipient"),
			new DateColumn("Date",Epoch.MCM),
			new StringColumn("Subject"),
			new StringColumn("Content"),
		};
		CSV csv = new CSV(columns);
		csv.write(filename, ext, this);
	}
}