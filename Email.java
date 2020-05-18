import java.util.Date;
import java.io.File;

class Email {
	public String sender;
	public String recip;
	public ExcelDate date;
	public String subject;
	public String content;

	public Email(String sen, String rec, String sub, String con) {
		sender  = sen;
		recip   = rec;
		date    = new ExcelDate(); // Automatically the current date
		subject = sub;
		content = con;
	}
	public Email(String csvrow) {
		int comma = csvrow.indexOf(",");
		String sender  = csvrow.substring(0,comma);
		// System.out.println(sender );
		sender = StringColumn.desanitize(sender);
		this.sender = sender;
		csvrow = csvrow.substring(comma+1);

		comma = csvrow.indexOf(",");
		String recip   = csvrow.substring(0,comma);
		// System.out.println(recip  );
		recip = StringColumn.desanitize(recip);
		this.recip = recip;
		csvrow = csvrow.substring(comma+1);

		comma = csvrow.indexOf(",");
		String date    = csvrow.substring(0,comma);
		// System.out.println(date   );
		long ms = Long.parseLong(date);
		this.date = new ExcelDate(ms);
		csvrow = csvrow.substring(comma+1);

		comma = csvrow.indexOf(",");
		String subject = csvrow.substring(0,comma);
		// System.out.println(subject);
		subject = StringColumn.desanitize(subject);
		this.subject = subject;
		csvrow = csvrow.substring(comma+1);

		comma = csvrow.indexOf(",");
		String content = csvrow.substring(0,comma);
		// System.out.println(content);
		content = StringColumn.desanitize(content);
		this.content = content;
		csvrow = csvrow.substring(comma+1);

		// System.out.println(comma);
	}
	public void write() {
		System.out.print(StringColumn.sanitize(sender ));
		System.out.print(',');
		System.out.print(StringColumn.sanitize(recip  ));
		System.out.print(',');
		System.out.print(date.write(Epoch.UNIX));
		System.out.print(',');
		System.out.print(StringColumn.sanitize(subject));
		System.out.print(',');
		System.out.print(StringColumn.sanitize(content));
		System.out.println();
	}
	public static void main(String[] args) {
		String csvrow = "WolfElkan@landmark.edu,KarinaAssiter@landmark.edu,1589837225266,Final Project,\"Hello Professor, This is my email.\"";
		Email e = new Email(csvrow);
		System.out.println(e.content);
	}
}