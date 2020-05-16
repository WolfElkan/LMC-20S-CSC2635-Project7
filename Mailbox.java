import java.io.File;

class Mailbox {
	public Email[] content = new Email[256];
	public int lContent = 0; // length of content
	public int sortstate = 0; // how is Mailbox currently sorted
	public void add(Email email) {
		content[lContent++] = email;
	}
	public void writeCSV(String filename) {
		if (filename.contains(".")) {
			int dot = filename.lastIndexOf(".");
			String ext = filename.substring(dot+1);
			filename = filename.substring(0,dot);
			writeCSV(filename,ext);
		} else {
			writeCSV(filename,"csv");
		}
	}
	public void writeCSV(String filename, String ext) {
		System.out.println(filename+"."+ext);
		for (int i=0; i<lContent; i++) {
			content[i].writeCSV();
		}
	}
}