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
		date    = new Date(); // Automatically the current date
		subject = sub;
		content = con;
	}
}