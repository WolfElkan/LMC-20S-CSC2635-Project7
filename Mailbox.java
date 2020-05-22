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
	public void add(Email email) {
		content[lContent++] = email;
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
	public static void main(String[] args) {
		Mailbox m = new Mailbox();
		Email e = new Email(
			"WolfElkan@landmark.edu",
			"KarinaAssiter@landmark.edu",
			"Final Project",
			"Hello Professor, This is my email."
		);
		m.add(e);
		// e = new Email(
		// 	"WolfElkan@landmark.edu",
		// 	"contact@weirdal.com",
		// 	"Stinky Cheese",
		// 	"Hey everyone, listen up, your attention if you please."
		// );
		// m.add(e);
		MailboxWindow window = new MailboxWindow(m);
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
	public MailboxWindow(Mailbox mailbox) {

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

		JPanel messagesPanel = new JPanel();
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

		for (int e=0; e<mailbox.lContent; e++) {
			messagesPanel.add(mailbox.content[e].panel(columns));
		}

		this.setSize(FRAME_WIDTH, FRAME_HEIGHT);
		this.setTitle("Mailbox");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		panel.add(headerPanel);
		panel.add(messagesPanel);
		
		this.add(panel);

		this.setVisible(true);
	}
	public void display() {
		this.setVisible(true);
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