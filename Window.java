import javax.swing.*;
import java.awt.*;

public class Window {
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		
		Dimension labelSize = new Dimension(0,70);

		JLabel senLabel = new JLabel("From");
		JTextField senField = new JTextField("",20);
		senLabel.setMinimumSize(labelSize);
		JPanel senPanel = new JPanel();
		senPanel.add(senLabel);
		senPanel.add(senField);

		JLabel recLabel = new JLabel("To");
		JTextField recField = new JTextField("",20);
		recLabel.setMinimumSize(labelSize);
		JPanel recPanel = new JPanel();
		recPanel.add(recLabel);
		recPanel.add(recField);

		JLabel subLabel = new JLabel("Subject");
		JTextField subField = new JTextField("",20);
		subLabel.setMinimumSize(labelSize);
		JPanel subPanel = new JPanel();
		subPanel.add(subLabel);
		subPanel.add(subField);

		JTextPane contentBox = new JTextPane();


		JButton send = new JButton("Send Email");


		JPanel fields = new JPanel();
		fields.add(senPanel);
		fields.add(recPanel);
		fields.add(subPanel);
		fields.add(contentBox);
		fields.add(send);
		frame.add(fields);

		final int FRAME_WIDTH = 400;
		final int FRAME_HEIGHT = 200;
		frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
		frame.setTitle("Email Interface");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		frame.setVisible(true);
	}
}
