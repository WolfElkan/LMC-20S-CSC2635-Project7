import java.util.Date;
import java.io.IOException;
import java.io.File;
import java.io.FileReader;

class Project {
	public static void main(String[] args) throws IOException {
		onOpen();
		String filename = "emails.csv";
		File file = new File(filename);
		Mailbox mail;
		if (file.exists()) {
			FileReader reader = new FileReader(filename);
			mail = new Mailbox(reader);
		} else {
			mail = new Mailbox();
		}
		// System.out.println(mail);





		// onClose();
	}
	public static void onOpen() throws IOException {

	}
	// public static void onClose() throws IOException {
	// 	Email e = new Email(
	// 		"WolfElkan@landmark.edu",
	// 		"KarinaAssiter@landmark.edu",
	// 		"Final Project",
	// 		"Hello Professor, This is my email."
	// 	);
	// 	m.add(e);
	// 	m.writeCSV("emails");
	// }
}