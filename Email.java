import java.util.Date;
import java.io.File;

class Email {
	public String sender;
	public String recip;
	public Date date;
	public String subject;
	public String content;

	public Email(String sen, String rec, String sub, String con) {
		sender  = sen;
		recip   = rec;
		subject = sub;
		content = con;
		date    = new Date(); // Automatically the current date
	}
	public Email(String csvrow) {
		
	}
	public String sanitize(String text) {
		return text;
	}
	public void writeCSV() {
		System.out.print(sender+",");
		System.out.print(recip+",");
		System.out.print(subject+",");
		System.out.print(content+",");
		System.out.print(date.getTime()+",");

		System.out.println();
	}
}