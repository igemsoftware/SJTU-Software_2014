package EasyBBK_Swing.gui;

import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;

import java.awt.Color;

public class Child_Main{

	public JInternalFrame frame;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Child_Main window = new Child_Main();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Child_Main() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		frame = new JInternalFrame();
		frame.setResizable(false);
		frame.setBounds(0, 0, 1366, 670);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//frame.setUndecorated(true);
		frame.getContentPane().setLayout(null);
		frame.setVisible(true);
		
		JLabel label = new JLabel("New label");
		label.setBackground(new Color(240, 240, 240));
		label.setIcon(new ImageIcon(MainPage.class.getResource("/EasyBBK_Swing/image/home.png")));
		label.setBounds(302, 115, 258, 148);
		frame.getContentPane().add(label);
	}
}
