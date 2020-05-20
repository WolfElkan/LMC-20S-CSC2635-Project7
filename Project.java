import java.util.Date;

class Project {
	public static void main(String[] args) {
		onOpen();

		Email e = new Email(
			"WolfElkan@landmark.edu",
			"KarinaAssiter@landmark.edu",
			"Final Project",
			"Hello Professor, This is my email."
		);
		Mailbox m = new Mailbox();
		e.write();

		onClose();
	}
	public static void onOpen() {

	}
	public static void onClose() {

	}
}