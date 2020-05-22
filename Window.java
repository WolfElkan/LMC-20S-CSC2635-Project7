import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

class Field extends JPanel {

	public Dimension size = new Dimension(0,70);
	public JTextField field;

	public Field (String title, CreateMessage listener) {
		JLabel label = new JLabel(title);
		label.setMinimumSize(size);
		this.add(label);

		field = new JTextField("",20);
		this.add(field);

		listener.setField(title,this);
	}

	public String getText() {
		return field.getText();
	}
}

public class Window extends JFrame {
	public Window(Mailbox mailbox) {

		final int FRAME_WIDTH  = 400;
		final int FRAME_HEIGHT = 300;
		
		Dimension contentBoxSize = new Dimension(FRAME_WIDTH,100);

		JEditorPane contentBox = new JEditorPane();
		contentBox.setContentType("text/plain");
		contentBox.setEditable(true);
		contentBox.setPreferredSize(contentBoxSize);

		JButton sendButton = new JButton("Send Email");
		CreateMessage sendAction = new CreateMessage();
		sendButton.addActionListener(sendAction);

		JPanel compose = new JPanel();

		Field senField = new Field("From",sendAction);
		Field recField = new Field("To",sendAction);
		Field subField = new Field("Subject",sendAction);

		sendAction.con = contentBox;
		sendAction.mailbox = mailbox;
		sendAction.window = this;

		compose.add(senField);
		compose.add(recField);
		compose.add(subField);

		compose.add(contentBox);
		compose.add(sendButton);
		this.add(compose);

		this.setSize(FRAME_WIDTH, FRAME_HEIGHT);
		this.setTitle("Compose New Message");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}
	public void display() {
		this.setVisible(true);
	}
	public static void main(String[] args) {
		Mailbox mailbox = new Mailbox();
		Window window = new Window(mailbox);
		window.display();
	}
}

class CreateMessage implements ActionListener {
	Field sen;
	Field rec;
	Field sub;
	JEditorPane con;
	Mailbox mailbox;
	Window window;
	public void setField(String title, Field field) {
		switch (title) {
			case "From":
				sen = field;
				break;
			case "To":
				rec = field;
				break;
			case "Subject":
				sub = field;
				break;
		}
	}
	public void actionPerformed(ActionEvent event) {
		Email email = new Email(
			sen.getText(),
			rec.getText(),
			sub.getText(),
			con.getText()
		);
		mailbox.add(email);
		// window.setVisible(false);
		window.dispose();
		// mailbox.writeCSV("emails.csv");
	}
}
