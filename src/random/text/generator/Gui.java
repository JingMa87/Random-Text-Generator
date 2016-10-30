package random.text.generator;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;


public class Gui {
        private RandomTextGenerator generator;
        //private static final int DEFAULT_WORD_AMOUNT;
        
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

		JLabel headerLabel = new JLabel("Click the button on the bottom!");
		headerLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		panel.add(headerLabel);
                
                JComboBox<String> generatorSelectBox = new JComboBox<>();
                generatorSelectBox.addItem("SpaceEfficientGenerator");
                generatorSelectBox.addItem("SpeedEfficientGenerator");
                generatorSelectBox.setAlignmentX(Component.RIGHT_ALIGNMENT);
                panel.add(generatorSelectBox);
                
		JTextArea inputTextArea = new JTextArea(5, 5);
		inputTextArea.setText(RandomTextGenerator.getDefaultText());
		inputTextArea.setAlignmentX(Component.CENTER_ALIGNMENT);
                inputTextArea.setLineWrap(true);
                inputTextArea.setWrapStyleWord(true);
		panel.add(inputTextArea);
                
                JTextArea outputTextArea = new JTextArea(5, 5);
		outputTextArea.setText("Result");
                outputTextArea.setEditable(false);
		outputTextArea.setAlignmentX(Component.CENTER_ALIGNMENT);
                outputTextArea.setLineWrap(true);
                outputTextArea.setWrapStyleWord(true);
		panel.add(outputTextArea);
		
		JButton generateTextButton = new JButton("Generate text");
		generateTextButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		panel.add(generateTextButton);
                
                JLabel executionTimeLabel = new JLabel();
		panel.add(executionTimeLabel);
                
                generateTextButton.addActionListener(new ActionListener(){
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String generatorType = (String)generatorSelectBox.getSelectedItem();
                        String inputText = inputTextArea.getText();
                        switch(generatorType){
                            case "SpeedEfficientGenerator":
                                generator = new SpeedEfficientGenerator(inputText);
                                break;
                            default:
                                generator = new SpaceEfficientGenerator(inputText);
                        }
                        long startTime = System.currentTimeMillis();
                        outputTextArea.setText(generator.generateText(200));
                        long stopTime = System.currentTimeMillis();
                        long elapsedTime = stopTime - startTime;
                        executionTimeLabel.setText("Time taken: " + elapsedTime + "ms");
                    }
                });
		
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