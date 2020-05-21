import java.util.Date;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;

class Email implements Serializable {
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
	public Email(String csvrow, CSV csv) {
		String[] rowref = {csvrow};
		for (int c=0; c<csv.columns.length; c++) {
			Column column = csv.columns[c];
			// System.out.println(column.title);
			set_attr(column,csv.getNext(rowref));
		}
	}
	public Email(String csvrow) {
		String[] rowref = {csvrow};
		sender  = CSV.getNext(rowref);
		recip   = CSV.getNext(rowref);
		String date = CSV.getNext(rowref);
		long ms = Long.parseLong(date);
		this.date = new ExcelDate(ms);
		subject = CSV.getNext(rowref);
		content = CSV.getNext(rowref);
	}
	// public void write(FileWriter file) throws IOException {
	// 	file.write(StringColumn.sanitize(sender ));
	// 	file.write(',');
	// 	file.write(StringColumn.sanitize(recip  ));
	// 	file.write(',');
	// 	file.write(date.write(Epoch.UNIX));
	// 	file.write(',');
	// 	file.write(StringColumn.sanitize(subject));
	// 	file.write(',');
	// 	file.write(StringColumn.sanitize(content));
	// 	file.write('\n');
	// }
	public void write(FileWriter file, Column[] columns) throws IOException {
		for (int c=0; c<5; c++) {
			Column column = columns[c];
			file.write(column.write(get_attr(column)));
			if (c < 4) {
				file.write(',');
			} else {
				file.write('\n');
			}
		}
	}
	public Object get_attr(Column column) {
		switch (column.title) {
			case "Sender":
				return sender;
			case "Recipient":
				return recip;
			case "Subject":
				return subject;
			case "Content":
				return content;
			case "Date":
				return date;
		}
		return "Email.java:73";
	}
	public void set_attr(Column column, String data) {
		System.out.println(data);
		switch (column.title) {
			case "Sender":
				this.sender = data;
				break;
			case "Recipient":
				this.recip = data;
				break;
			case "Subject":
				this.subject = data;
				break;
			case "Content":
				this.content = data;
				break;
			case "Date":
				String parsed = column.read(data);
				long parsedlong = Long.parseLong(parsed);
				this.date = new ExcelDate(new Date(parsedlong));
				break;
		}
	}
	public void print() {
		System.out.print("From:     ");
		System.out.println(sender    );
		System.out.print("To:       ");
		System.out.println(recip     );
		System.out.print("Date:     ");
		System.out.println(date      );
		System.out.print("Subject:  ");
		System.out.println(subject   );
		System.out.print("Content:  ");
		System.out.println(content   );
	}
	public static void main(String[] args) {
		String csvrow = "WolfElkan@landmark.edu,KarinaAssiter@landmark.edu,1589837225266,Final Project,\"Hello Professor, This is my email.\"";
		Email e = new Email(csvrow);
		e.print();
	}
}