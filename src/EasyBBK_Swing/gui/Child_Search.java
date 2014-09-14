package EasyBBK_Swing.gui;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;

import java.awt.Font;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JSeparator;
import javax.swing.JCheckBox;
import javax.swing.JSpinner;

public class Child_Search {

	private JFrame frame;
	private JCheckBox chckbxReleased;
	private JCheckBox chckbxNewCheckBox;
	private JCheckBox chckbxNotReleased;
	private JCheckBox chckbxAvailable;
	private JCheckBox chckbxPlanning;
	private JCheckBox chckbxInformational;
	private JCheckBox chckbxNotDeleted;
	private JCheckBox chckbxDeleted;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Child_Search window = new Child_Search();
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
	public Child_Search() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.setBounds(0, 0, 1366, 670);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setUndecorated(true);
		frame.getContentPane().setLayout(null);
		
		JPanel Result = new JPanel();
		Result.setBounds(0, 0, 683, 670);
		Result.setBorder(BorderFactory.createLineBorder(Color.black));
		frame.getContentPane().add(Result);
		Result.setLayout(null);
		
		JLabel Back = new JLabel();
		Back.setBounds(51, 42, 50, 50);
		Back.setIcon(new ImageIcon(MainPage.class.getResource("/EasyBBK_Swing/image/SearchBox_backward.png")));
		Result.add(Back);
		
		JLabel Forward = new JLabel();
		Forward.setBounds(107, 42, 70, 50);
		Forward.setIcon(new ImageIcon(MainPage.class.getResource("/EasyBBK_Swing/image/SearchBox_forward.png")));
		Result.add(Forward);
		
		JLabel Text_BackGround = new JLabel();
		Text_BackGround.setBounds(207, 42, 300, 50);
		Text_BackGround.setIcon(new ImageIcon(MainPage.class.getResource("/EasyBBK_Swing/image/Text.png")));
		Result.add(Text_BackGround);
		
		JLabel Search = new JLabel();
		Search.setBounds(530, 42, 100, 50);
		Search.setIcon(new ImageIcon(MainPage.class.getResource("/EasyBBK_Swing/image/SearchBox_searchButton.png")));
		Result.add(Search);
		
		JLabel Text_BackGround1 = new JLabel();
		Text_BackGround1.setBounds(207, 112, 300, 50);
		Text_BackGround1.setIcon(new ImageIcon(MainPage.class.getResource("/EasyBBK_Swing/image/Text.png")));
		Result.add(Text_BackGround1);
		
		JTextField textField = new JTextField("Input Here!");
		textField.setFont(new Font("Times New Roman", Font.PLAIN, 24));
		textField.setVisible(true);
		textField.setBounds(10, 5, 283, 32);
		Text_BackGround.add(textField);
		textField.setColumns(20);
		
		JTextField textField1 = new JTextField("Input Here!");
		textField1.setFont(new Font("Times New Roman", Font.PLAIN, 24));
		textField1.setVisible(true);
		textField1.setBounds(10, 5, 283, 32);
		Text_BackGround1.add(textField1);
		textField1.setColumns(20);
		
		JPanel panel = new JPanel();
		panel.setBounds(62, 186, 558, 211);
		Result.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Filters:");
		lblNewLabel.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblNewLabel.setBounds(10, 10, 68, 28);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Release status:");
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblNewLabel_1.setBounds(39, 48, 90, 17);
		panel.add(lblNewLabel_1);
        
		ItemListener itemListener1 = new ItemListener() {
            JCheckBox jCheckBox;
 
            public void itemStateChanged(ItemEvent e) {
                jCheckBox = (JCheckBox) e.getSource();
 
                if (jCheckBox.isSelected()) {
                    if(jCheckBox == chckbxReleased){
                    	chckbxNewCheckBox.setSelected(false);
                    	chckbxNotReleased.setSelected(false);
                    }
                    else if(jCheckBox == chckbxNewCheckBox){
                    	chckbxReleased.setSelected(false);
                    	chckbxNotReleased.setSelected(false);
                    }
                    else if(jCheckBox == chckbxNotReleased){
                    	chckbxReleased.setSelected(false);
                    	chckbxNewCheckBox.setSelected(false);
                    }
                } else {
                    
                }
 
            }
        };
		
        chckbxReleased = new JCheckBox("Released");
        chckbxReleased.addItemListener(itemListener1);
		chckbxReleased.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		chckbxReleased.setBounds(131, 46, 103, 23);
		panel.add(chckbxReleased);
		
		chckbxNewCheckBox = new JCheckBox("Deleted");
		chckbxNewCheckBox.addItemListener(itemListener1);
		chckbxNewCheckBox.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		chckbxNewCheckBox.setBounds(235, 46, 103, 23);
		panel.add(chckbxNewCheckBox);
		
		chckbxNotReleased = new JCheckBox("Not Released");
		chckbxNotReleased.addItemListener(itemListener1);
		chckbxNotReleased.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		chckbxNotReleased.setBounds(340, 46, 121, 23);
		panel.add(chckbxNotReleased);
		
		JLabel lblDnaStatus = new JLabel("DNA status:");
		lblDnaStatus.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblDnaStatus.setBounds(39, 75, 90, 15);
		panel.add(lblDnaStatus);
		
		chckbxAvailable = new JCheckBox("Available");
		chckbxAvailable.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		chckbxAvailable.setBounds(131, 72, 103, 23);
		panel.add(chckbxAvailable);
		
		chckbxPlanning = new JCheckBox("Planning");
		chckbxPlanning.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		chckbxPlanning.setBounds(235, 71, 103, 23);
		panel.add(chckbxPlanning);
		
		chckbxInformational = new JCheckBox("Informational");
		chckbxInformational.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		chckbxInformational.setBounds(340, 72, 121, 23);
		panel.add(chckbxInformational);
		
		JLabel lblNewLabel_2 = new JLabel("Whether or not deleted:");
		lblNewLabel_2.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblNewLabel_2.setBounds(39, 101, 151, 15);
		panel.add(lblNewLabel_2);
		
		chckbxNotDeleted = new JCheckBox("Not Deleted");
		chckbxNotDeleted.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		chckbxNotDeleted.setBounds(196, 98, 103, 23);
		panel.add(chckbxNotDeleted);
		
		chckbxDeleted = new JCheckBox("Deleted");
		chckbxDeleted.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		chckbxDeleted.setBounds(311, 98, 103, 23);
		panel.add(chckbxDeleted);
		
		JLabel lblAverageStarsGiven = new JLabel("Average stars Given by previous teams:");
		lblAverageStarsGiven.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblAverageStarsGiven.setBounds(39, 126, 238, 15);
		panel.add(lblAverageStarsGiven);
		
		JCheckBox checkBox = new JCheckBox(">=4");
		checkBox.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		checkBox.setBounds(283, 123, 55, 23);
		panel.add(checkBox);
		
		JCheckBox chckbxNewCheckBox_1 = new JCheckBox("2-4");
		chckbxNewCheckBox_1.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		chckbxNewCheckBox_1.setBounds(359, 122, 55, 23);
		panel.add(chckbxNewCheckBox_1);
		
		JCheckBox checkBox_1 = new JCheckBox("<=2");
		checkBox_1.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		checkBox_1.setBounds(432, 122, 55, 23);
		panel.add(checkBox_1);
		
		JLabel lblNewLabel_3 = new JLabel("Preferences:");
		lblNewLabel_3.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblNewLabel_3.setBounds(10, 151, 99, 15);
		panel.add(lblNewLabel_3);
		
		JLabel lblStatus = new JLabel("Status:");
		lblStatus.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblStatus.setBounds(39, 176, 47, 15);
		panel.add(lblStatus);
		
		JSpinner spinner = new JSpinner();
		spinner.setBounds(96, 174, 29, 22);
		panel.add(spinner);
		
		JLabel lblQuality = new JLabel("Quality:");
		lblQuality.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblQuality.setBounds(147, 177, 54, 15);
		panel.add(lblQuality);
		
		JSpinner spinner_1 = new JSpinner();
		spinner_1.setBounds(211, 174, 29, 22);
		panel.add(spinner_1);
		
		JLabel lblFeedbacks = new JLabel("Feedbacks:");
		lblFeedbacks.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblFeedbacks.setBounds(264, 177, 74, 15);
		panel.add(lblFeedbacks);
		
		JSpinner spinner_2 = new JSpinner();
		spinner_2.setBounds(351, 174, 29, 22);
		panel.add(spinner_2);
		
		JLabel lblPublication = new JLabel("Publication:");
		lblPublication.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblPublication.setBounds(404, 177, 74, 15);
		panel.add(lblPublication);
		
		JSpinner spinner_3 = new JSpinner();
		spinner_3.setBounds(488, 174, 29, 22);
		panel.add(spinner_3);
		
		JPanel Details = new JPanel();
		Details.setBounds(684, 0, 683, 670);
		Details.setBorder(BorderFactory.createLineBorder(Color.black));
		frame.getContentPane().add(Details);
		Details.setLayout(null);
		
		
	}
}
