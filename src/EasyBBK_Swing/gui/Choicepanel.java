package EasyBBK_Swing.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.BorderFactory;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

public class Choicepanel extends JPanel {
	public JCheckBox chckbxReleased;
	public JCheckBox chckbxNewCheckBox;
	public JCheckBox chckbxNotReleased;
	public JCheckBox chckbxAvailable;
	public JCheckBox chckbxPlanning;
	public JCheckBox chckbxInformational;
	public JCheckBox chckbxNotDeleted;
	public JCheckBox chckbxDeleted;
	/**
	 * Create the panel.
	 */
	public Choicepanel() {
		setBackground(new Color(255, 255, 255));
		setBounds(0, 0, 558, 211);
		setBorder(BorderFactory.createLineBorder(Color.black));
		setVisible(false);
		setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Filters:");
		lblNewLabel.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblNewLabel.setBounds(10, 10, 68, 28);
		add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Release status:");
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblNewLabel_1.setBounds(39, 48, 90, 17);
		add(lblNewLabel_1);
		
		/*ItemListener itemListener1 = new ItemListener() {
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
        };*/
        
        chckbxReleased = new JCheckBox("Released");
        //chckbxReleased.addItemListener(itemListener1);
		chckbxReleased.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		chckbxReleased.setBounds(131, 46, 103, 23);
		chckbxReleased.setBackground(new Color(255, 255, 255));
		add(chckbxReleased);
		
		chckbxNewCheckBox = new JCheckBox("Deleted");
		//chckbxNewCheckBox.addItemListener(itemListener1);
		chckbxNewCheckBox.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		chckbxNewCheckBox.setBounds(235, 46, 103, 23);
		chckbxNewCheckBox.setBackground(new Color(255, 255, 255));
		add(chckbxNewCheckBox);
		
		chckbxNotReleased = new JCheckBox("Not Released");
		//chckbxNotReleased.addItemListener(itemListener1);
		chckbxNotReleased.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		chckbxNotReleased.setBounds(340, 46, 121, 23);
		chckbxNotReleased.setBackground(new Color(255, 255, 255));
		add(chckbxNotReleased);
		
		JLabel lblDnaStatus = new JLabel("DNA status:");
		lblDnaStatus.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblDnaStatus.setBounds(39, 75, 90, 15);
		add(lblDnaStatus);
		
		chckbxAvailable = new JCheckBox("Available");
		chckbxAvailable.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		chckbxAvailable.setBounds(131, 72, 103, 23);
		chckbxAvailable.setBackground(new Color(255, 255, 255));
		add(chckbxAvailable);
		
		chckbxPlanning = new JCheckBox("Planning");
		chckbxPlanning.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		chckbxPlanning.setBounds(235, 71, 103, 23);
		chckbxPlanning.setBackground(new Color(255, 255, 255));
		add(chckbxPlanning);
		
		chckbxInformational = new JCheckBox("Informational");
		chckbxInformational.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		chckbxInformational.setBounds(340, 72, 121, 23);
		chckbxInformational.setBackground(new Color(255, 255, 255));
		add(chckbxInformational);
		
		JLabel lblNewLabel_2 = new JLabel("Whether or not deleted:");
		lblNewLabel_2.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblNewLabel_2.setBounds(39, 101, 151, 15);
		add(lblNewLabel_2);
		
		chckbxNotDeleted = new JCheckBox("Not Deleted");
		chckbxNotDeleted.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		chckbxNotDeleted.setBounds(196, 98, 103, 23);
		chckbxNotDeleted.setBackground(new Color(255, 255, 255));
		add(chckbxNotDeleted);
		
		chckbxDeleted = new JCheckBox("Deleted");
		chckbxDeleted.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		chckbxDeleted.setBounds(311, 98, 103, 23);
		chckbxDeleted.setBackground(new Color(255, 255, 255));
		add(chckbxDeleted);
		
		JLabel lblAverageStarsGiven = new JLabel("Average stars Given by previous teams:");
		lblAverageStarsGiven.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblAverageStarsGiven.setBounds(39, 126, 238, 15);
		add(lblAverageStarsGiven);
		
		JCheckBox checkBox = new JCheckBox(">=4");
		checkBox.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		checkBox.setBounds(283, 123, 55, 23);
		checkBox.setBackground(new Color(255, 255, 255));
		add(checkBox);
		
		JCheckBox chckbxNewCheckBox_1 = new JCheckBox("2-4");
		chckbxNewCheckBox_1.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		chckbxNewCheckBox_1.setBounds(359, 122, 55, 23);
		chckbxNewCheckBox_1.setBackground(new Color(255, 255, 255));
		add(chckbxNewCheckBox_1);
		
		JCheckBox checkBox_1 = new JCheckBox("<=2");
		checkBox_1.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		checkBox_1.setBounds(432, 122, 55, 23);
		checkBox_1.setBackground(new Color(255, 255, 255));
		add(checkBox_1);
		
		JLabel lblNewLabel_3 = new JLabel("Preferences:");
		lblNewLabel_3.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblNewLabel_3.setBounds(10, 151, 99, 15);
		add(lblNewLabel_3);
		
		JLabel lblStatus = new JLabel("Status:");
		lblStatus.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblStatus.setBounds(39, 176, 47, 15);
		add(lblStatus);
		
		JSpinner spinner = new JSpinner();
		spinner.setModel(new SpinnerNumberModel(0, 0, 100, 1));
		spinner.setBounds(88, 171, 41, 27);
		add(spinner);
		
		JLabel lblQuality = new JLabel("Quality:");
		lblQuality.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblQuality.setBounds(147, 177, 54, 15);
		add(lblQuality);
		
		JSpinner spinner_1 = new JSpinner();
		spinner_1.setModel(new SpinnerNumberModel(0, 0, 100, 1));
		spinner_1.setBounds(213, 171, 41, 27);
		add(spinner_1);
		
		JLabel lblFeedbacks = new JLabel("Feedbacks:");
		lblFeedbacks.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblFeedbacks.setBounds(264, 177, 74, 15);
		add(lblFeedbacks);
		
		JSpinner spinner_2 = new JSpinner();
		spinner_2.setModel(new SpinnerNumberModel(0, 0, 100, 1));
		spinner_2.setBounds(353, 171, 41, 27);
		add(spinner_2);
		
		JLabel lblPublication = new JLabel("Publication:");
		lblPublication.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblPublication.setBounds(404, 177, 74, 15);
		add(lblPublication);
		
		JSpinner spinner_3 = new JSpinner();
		spinner_3.setModel(new SpinnerNumberModel(0, 0, 100, 1));
		spinner_3.setBounds(488, 171, 41, 27);
		add(spinner_3);
	}

}
