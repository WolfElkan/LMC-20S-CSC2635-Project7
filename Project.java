import java.util.Date;
import java.io.IOException;
import java.io.File;
import java.util.Scanner;
// import java.io.FileReader;

class Project {
	public static void main(String[] args) throws IOException {
		// onOpen();

		String filename = "emails.csv";
		File file = new File(filename);
		Mailbox mail;
		if (file.exists()) {
			Scanner reader = new Scanner(file);
			mail = new Mailbox(reader);
			reader.close();
		} else {
			mail = new Mailbox();
		}
		System.out.println(mail);
		mail.content[0].print();





		// onClose();
	}
	public static void onOpen() throws IOException {

	}
	public static void onClose() throws IOException {
		Mailbox m = new Mailbox();
		Email e = new Email(
			"WolfElkan@landmark.edu",
			"KarinaAssiter@landmark.edu",
			"Final Project",
			"Hello Professor, This is my email."
		);
		m.add(e);
		m.writeCSV("emails");
	}
}