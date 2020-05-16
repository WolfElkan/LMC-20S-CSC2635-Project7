import java.util.Date;

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
}