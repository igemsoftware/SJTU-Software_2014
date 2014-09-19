package EasyBBK_Swing.gui;

import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;

public class Child_Design extends JPanel {
	public MainPage mainpage;
	/**
	 * Create the panel.
	 */
	public Child_Design(MainPage mainpage1) {
		mainpage = mainpage1;
		setBounds(0, 0, 1366, 670);
		setBackground(new Color(255, 255, 255));
		setLayout(null);
		
		JLabel lblDesign = new JLabel("Design");
		lblDesign.setFont(new Font("Times New Roman", Font.PLAIN, 36));
		lblDesign.setBounds(226, 117, 149, 109);
		add(lblDesign);
		
		setVisible(true);
	}
}
