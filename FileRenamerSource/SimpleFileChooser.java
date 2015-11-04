import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;

public class SimpleFileChooser extends JFrame {
	private static final long serialVersionUID = 1L;

public SimpleFileChooser() {
    super("File Chooser Test Frame");
    setSize(100, 100);
    setDefaultCloseOperation(EXIT_ON_CLOSE);

    Container c = getContentPane();
    c.setLayout(new FlowLayout());
    JButton dirButton = new JButton("Pick Dir");
    final JLabel statusbar = 
                 new JLabel("");

  
    // Create a file chooser that allows you to pick a directory
    // rather than a file
    dirButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent ae) {
        JFileChooser chooser = new JFileChooser();
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int option = chooser.showOpenDialog(SimpleFileChooser.this);
        if (option == JFileChooser.APPROVE_OPTION) {
        	returnerMethod(chooser.getSelectedFile().getAbsolutePath());
        }
      }

	private void returnerMethod(String absolutePath) {
		String x = absolutePath;
		x = x.replace("\\", "\\\\");		
		File file = new File(x);
		FileRenamer.recurseRename(file);
					}
    });
    c.add(dirButton);
    c.add(statusbar);
  }

}
