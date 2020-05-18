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
}