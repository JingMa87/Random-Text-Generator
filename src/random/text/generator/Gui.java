package random.text.generator;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class Gui {

    // Make variable instance.
    JPanel panel;
    JLabel pathlbl, label;
    JButton openFile;
    JFrame frame;
    JTextArea textField;
    JButton generateTextButton;
    File file;

    public Gui() {
        init();
    }

    public void init() {

        frame = new JFrame("Random Text Generator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setPreferredSize(new Dimension(600, 500));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        frame.add(panel);

        // Button for open JFileChooser. 
        openFile = new JButton("Open File");
        final JFileChooser fileDialog = new JFileChooser();
        openFile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int returnVal = fileDialog.showOpenDialog(frame);
                if (returnVal == JFileChooser.APPROVE_OPTION) {
                    file = fileDialog.getSelectedFile();
                    pathlbl.setText("File Selected :" + file.getName());
                } else {
                    pathlbl.setText("Open command cancelled by user.");
                }
            }
        });
        openFile.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(openFile);

        // Lable for show path of the file
        pathlbl = new JLabel("");
        pathlbl.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(pathlbl);

        label = new JLabel("Click the button on the bottom!");
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(label);

        textField = new JTextArea(5, 5);
        textField.setText("Not randomly generated text.");
        textField.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(textField);

        generateTextButton = new JButton("Generate text");
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
