import java.util.Date;
import java.io.IOException;

class Project {
	public static void main(String[] args) throws IOException {
		onOpen();

		Email e = new Email(
			"WolfElkan@landmark.edu",
			"KarinaAssiter@landmark.edu",
			"Final Project",
			"Hello Professor, This is my email."
		);
		Mailbox m = new Mailbox();
		m.add(e);
		m.writeCSV("emails");

		onClose();
	}
	public static void onOpen() {

	}
	public static void onClose() {

	}
}