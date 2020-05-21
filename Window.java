import javax.swing.*;
import java.awt.*;

class Field extends JPanel {
	public Dimension size = new Dimension(0,70);
	public Field (String title) {
		JLabel label = new JLabel(title);
		JTextField field = new JTextField("",20);
		label.setMinimumSize(size);
		this.add(label);
		this.add(field);
	}
}

public class Window {
	public static void main(String[] args) {
		JFrame frame = new JFrame();

		final int FRAME_WIDTH  = 400;
		final int FRAME_HEIGHT = 300;
		
		Dimension labelSize = new Dimension(0,70);
		Dimension contentBoxSize = new Dimension(FRAME_WIDTH,100);

		// JLabel senLabel = new JLabel("From");
		// JTextField senField = new JTextField("",20);
		// senLabel.setMinimumSize(labelSize);
		// JPanel senPanel = new JPanel();
		// senPanel.add(senLabel);
		// senPanel.add(senField);

		// JLabel recLabel = new JLabel("To");
		// JTextField recField = new JTextField("",20);
		// recLabel.setMinimumSize(labelSize);
		// JPanel recPanel = new JPanel();
		// recPanel.add(recLabel);
		// recPanel.add(recField);

		// JLabel subLabel = new JLabel("Subject");
		// JTextField subField = new JTextField("",20);
		// subLabel.setMinimumSize(labelSize);
		// JPanel subPanel = new JPanel();
		// subPanel.add(subLabel);
		// subPanel.add(subField);

		JEditorPane contentBox = new JEditorPane();
		contentBox.setContentType("text/plain");
		contentBox.setEditable(true);
		contentBox.setPreferredSize(contentBoxSize);

		JButton send = new JButton("Send Email");

		JPanel compose = new JPanel();
		compose.add(new Field("From"));
		compose.add(new Field("To"));
		compose.add(new Field("Subject"));
		compose.add(contentBox);
		compose.add(send);
		frame.add(compose);

		frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
		frame.setTitle("Email Interface");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		frame.setVisible(true);
	}
}
