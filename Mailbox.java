class Mailbox {
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
	}
}