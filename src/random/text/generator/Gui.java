package random.text.generator;

import java.awt.Component;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;


public class Gui {
	public Gui() {
		init();
	}

	public void init() {

		JFrame frame = new JFrame("Random Text Generator");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.setPreferredSize(new Dimension(600, 500));
		panel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
		frame.add(panel);

		JLabel label = new JLabel("Click the button on the bottom!");
		label.setAlignmentX(Component.CENTER_ALIGNMENT);
		panel.add(label);
		
		JTextArea inputTextArea = new JTextArea(5, 5);
		inputTextArea.setText(RandomTextGenerator.getDefaultText());
		inputTextArea.setAlignmentX(Component.CENTER_ALIGNMENT);
		panel.add(inputTextArea);
                
                JTextArea outputTextArea = new JTextArea(5, 5);
		outputTextArea.setText("Not randomly generated text.");
		outputTextArea.setAlignmentX(Component.CENTER_ALIGNMENT);
		panel.add(outputTextArea);
		
		JButton generateTextButton = new JButton("Generate text");
		generateTextButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		panel.add(generateTextButton);
		
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

	public static void main(String[] args) {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new Gui();
			}
		});
	}
}