import java.io.File;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;
import java.io.Serializable;
import java.util.Scanner;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class Mailbox implements Serializable {
	public Email[] content = new Email[256];
	public int lContent = 0; // length of content
	public int sortstate = 0; // how is Mailbox currently sorted
	public MailboxWindow window;
	public void add(Email email) {
		content[lContent++] = email;
		if (window != null) {
			window.update(email);
		}
	}
	public Mailbox() {}
	public Mailbox(Scanner file) throws IOException {
		System.out.println("Mailbox.java:17");
		CSV csv = new CSV(file.nextLine());
		while (file.hasNextLine()) {
			add(new Email(file.nextLine(),csv));
		}
	}
	public void writeCSV(String filename) throws IOException {
		System.out.println("Mailbox.java:24");
		if (filename.contains(".")) {
			int dot = filename.lastIndexOf(".");
			String ext = filename.substring(dot+1);
			filename = filename.substring(0,dot);
			writeCSV(filename,ext);
		} else {
			writeCSV(filename,"csv");
		}
	}
	public void writeCSV(String filename, String ext) throws IOException {
		System.out.println("Mailbox.java:35");
		Column[] columns = {
			new StringColumn("Sender"),
			new StringColumn("Recipient"),
			new DateColumn("Date",Epoch.MCM),
			new StringColumn("Subject"),
			new StringColumn("Content"),
		};
		CSV csv = new CSV(columns);
		csv.write(filename, ext, this);
	}
	public static void main(String[] args) throws IOException {

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
		MailboxWindow window = new MailboxWindow(mail);

	}
}

class ColumnPanel {
	public String title;
	public int width;
	public ColumnPanel(String title, int width) {
		this.title = title;
		this.width = width;
	}
	public JPanel header() {
		JLabel label = new JLabel(title);
		JPanel padding = new JPanel();
		padding.add(label);
		padding.setPreferredSize(new Dimension(width,40));
		return padding;
	}
}

class MailboxWindow extends JFrame {
	public Mailbox mailbox;
	public JPanel messagesPanel = new JPanel();
	ColumnPanel[] columns;
	public MailboxWindow(Mailbox mailbox) {

		mailbox.window = this;
		this.mailbox = mailbox;

		final int senWid = 200;
		final int recWid = 200;
		final int datWid = 220;
		final int subWid = 400;

		final int headHeight =  40;
		final int listHeight = 260;

		final int FRAME_WIDTH = senWid + datWid + recWid + subWid + 20;
		final int FRAME_HEIGHT = headHeight + listHeight;

		// JPanel buttonPanel = new JPanel();
		JButton button = new JButton("New Message");
		// buttonPanel.add(button);

		JPanel panel = new JPanel();

		JPanel headerPanel = new JPanel();

		ColumnPanel senPanel = new ColumnPanel("Sender",senWid);
		ColumnPanel recPanel = new ColumnPanel("Recipient",recWid);
		ColumnPanel datPanel = new ColumnPanel("Date",datWid);
		ColumnPanel subPanel = new ColumnPanel("Subject",subWid);
		
		JPanel subHeader = subPanel.header();
		subHeader.setPreferredSize(new Dimension(subWid-200,headHeight));

		button.addActionListener(new NewMessage(mailbox));

		headerPanel.add(senPanel.header());
		headerPanel.add(recPanel.header());
		headerPanel.add(datPanel.header());
		headerPanel.add(subHeader);
		headerPanel.add(button);

		ColumnPanel[] columns = {senPanel,recPanel,datPanel,subPanel};
		this.columns = columns;

		for (int e=0; e<mailbox.lContent; e++) {
			// messagesPanel.add(mailbox.content[e].panel(columns));
			addMessage(mailbox.content[e]);
		}

		this.setSize(FRAME_WIDTH, FRAME_HEIGHT);
		this.setTitle("Mailbox");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		panel.add(headerPanel);
		panel.add(messagesPanel);
		messagesPanel.setPreferredSize(new Dimension(FRAME_WIDTH,listHeight));
		
		this.add(panel);

		this.setVisible(true);
	}
	public void display() {
		this.setVisible(true);
	}
	public void addMessage(Email email) {
		// System.out.println(messagesPanel);
		messagesPanel.add(email.panel(columns));
	}
	public void update(Email email) {
		System.out.println(mailbox.lContent);
		this.setVisible(false);
		addMessage(email);
		this.setVisible(true);
		// messagesPanel.add(email.panel(columns));
	}
}

class NewMessage implements ActionListener {
	public Mailbox mailbox;
	public NewMessage(Mailbox mailbox) {
		this.mailbox = mailbox;
	}
	public void actionPerformed(ActionEvent event) {
		Window newMessage = new Window(mailbox);
		newMessage.display();
	}
}