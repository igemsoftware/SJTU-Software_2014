package EasyBBK_Swing.gui;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class Child_Upload extends JPanel {
	public MainPage mainpage;
	/**
	 * Create the panel.
	 */
	public Child_Upload(MainPage mainpage1) {
		mainpage = mainpage1;
		setBounds(0, 0, 1366, 670);
		setBackground(new Color(255, 255, 255));
		setLayout(null);
		
		JLabel lblDesign = new JLabel("Upload");
		lblDesign.setFont(new Font("Times New Roman", Font.PLAIN, 36));
		lblDesign.setBounds(517, 126, 149, 109);
		add(lblDesign);
		
		setVisible(true);
	}

}
