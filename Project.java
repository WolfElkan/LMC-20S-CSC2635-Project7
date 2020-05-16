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
		m.add(e);
		m.writeCSV("emails");
		// System.out.println(e.date.getTime());
		Date k = new Date();
		CSV emails = new CSV();
		// emails.write("ema.ils");
		System.out.println("foo.bar".split("."));

		onClose();
	}
	public static void onOpen() {

	}
	public static void onClose() {

	}
}