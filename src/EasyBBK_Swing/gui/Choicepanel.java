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
import javax.swing.JComboBox;

public class Choicepanel extends JPanel {
	public JCheckBox Released;
	public JCheckBox Deleted;
	public JCheckBox NotReleased;
	public JCheckBox Available;
	public JCheckBox Planning;
	public JCheckBox Informational;
	public JCheckBox NotDeleted;
	public JCheckBox Deleted1;
	public JCheckBox EnteredDate;
	public JCheckBox GoogleQouteNumber;
	public JCheckBox AverageStars;
	public JCheckBox ConfirmedTimes;
	public JCheckBox BlastResult;
	public JCheckBox TotalScore;
	public JCheckBox Promoter;
	public JCheckBox RBS;
	public JCheckBox Protein_Domain;
	public JCheckBox Protein_Coding_Sequence;
	public JCheckBox Translation_Unit;
	public JCheckBox Terminator;
	public JCheckBox DNA;
	public JCheckBox Plasmid_Backbone;
	public JCheckBox Plasmid;
	public JCheckBox Primer;
	public JCheckBox Composite;
	public JCheckBox Protein_Generator;
	public JCheckBox Reporter;
	public JCheckBox Inventer;
	public JCheckBox Signalling;
	public JCheckBox Measurement;
	public JCheckBox Other;
	public JComboBox Year1;
	public JComboBox Year2;
	public JSpinner PercentofStatus;
	public JSpinner PercentofQuality;
	public JSpinner PercentofFeedbacks;
	public JSpinner spinner_3;
	public JCheckBox high;
	public JCheckBox middle;
	public JCheckBox low;
	/**
	 * Create the panel.
	 */
	public Choicepanel() {
		setBackground(new Color(255, 255, 255));
		setBounds(0, 0, 563, 430);
		setBorder(BorderFactory.createLineBorder(Color.black));
		setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Filters:");
		lblNewLabel.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblNewLabel.setBounds(10, 81, 68, 28);
		add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Release Status:");
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblNewLabel_1.setBounds(39, 264, 99, 17);
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
        
        Released = new JCheckBox("Released");
        //chckbxReleased.addItemListener(itemListener1);
		Released.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		Released.setBounds(131, 263, 103, 23);
		Released.setBackground(new Color(255, 255, 255));
		add(Released);
		
		Deleted = new JCheckBox("Deleted");
		//chckbxNewCheckBox.addItemListener(itemListener1);
		Deleted.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		Deleted.setBounds(235, 263, 103, 23);
		Deleted.setBackground(new Color(255, 255, 255));
		add(Deleted);
		
		NotReleased = new JCheckBox("Not Released");
		//chckbxNotReleased.addItemListener(itemListener1);
		NotReleased.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		NotReleased.setBounds(340, 263, 121, 23);
		NotReleased.setBackground(new Color(255, 255, 255));
		add(NotReleased);
		
		JLabel lblDnaStatus = new JLabel("DNA Status:");
		lblDnaStatus.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblDnaStatus.setBounds(39, 291, 90, 15);
		add(lblDnaStatus);
		
		Available = new JCheckBox("Available");
		Available.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		Available.setBounds(131, 288, 103, 23);
		Available.setBackground(new Color(255, 255, 255));
		add(Available);
		
		Planning = new JCheckBox("Planning");
		Planning.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		Planning.setBounds(235, 287, 103, 23);
		Planning.setBackground(new Color(255, 255, 255));
		add(Planning);
		
		Informational = new JCheckBox("Informational");
		Informational.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		Informational.setBounds(340, 288, 121, 23);
		Informational.setBackground(new Color(255, 255, 255));
		add(Informational);
		
		JLabel lblNewLabel_2 = new JLabel("Whether or not Deleted:");
		lblNewLabel_2.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblNewLabel_2.setBounds(39, 317, 151, 15);
		add(lblNewLabel_2);
		
		NotDeleted = new JCheckBox("Not Deleted");
		NotDeleted.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		NotDeleted.setBounds(196, 314, 103, 23);
		NotDeleted.setBackground(new Color(255, 255, 255));
		add(NotDeleted);
		
		Deleted1 = new JCheckBox("Deleted");
		Deleted1.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		Deleted1.setBounds(311, 314, 103, 23);
		Deleted1.setBackground(new Color(255, 255, 255));
		add(Deleted1);
		
		JLabel lblAverageStarsGiven = new JLabel("Average Stars Given by Previous Teams:");
		lblAverageStarsGiven.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblAverageStarsGiven.setBounds(39, 342, 247, 15);
		add(lblAverageStarsGiven);
		
		high = new JCheckBox(">=4");
		high.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		high.setBounds(292, 340, 55, 23);
		high.setBackground(new Color(255, 255, 255));
		add(high);
		
		middle = new JCheckBox("2-4");
		middle.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		middle.setBounds(368, 339, 55, 23);
		middle.setBackground(new Color(255, 255, 255));
		add(middle);
		
		low = new JCheckBox("<=2");
		low.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		low.setBounds(441, 339, 55, 23);
		low.setBackground(new Color(255, 255, 255));
		add(low);
		
		JLabel lblNewLabel_3 = new JLabel("Preferences:");
		lblNewLabel_3.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblNewLabel_3.setBounds(10, 367, 99, 15);
		add(lblNewLabel_3);
		
		JLabel lblStatus = new JLabel("Status:");
		lblStatus.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblStatus.setBounds(39, 398, 47, 15);
		add(lblStatus);
		
		PercentofStatus = new JSpinner();
		PercentofStatus.setModel(new SpinnerNumberModel(0, 0, 100, 1));
		PercentofStatus.setBounds(88, 393, 41, 27);
		add(PercentofStatus);
		
		JLabel lblQuality = new JLabel("Quality:");
		lblQuality.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblQuality.setBounds(147, 399, 54, 15);
		add(lblQuality);
		
		PercentofQuality = new JSpinner();
		PercentofQuality.setModel(new SpinnerNumberModel(0, 0, 100, 1));
		PercentofQuality.setBounds(213, 393, 41, 27);
		add(PercentofQuality);
		
		JLabel lblFeedbacks = new JLabel("Feedbacks:");
		lblFeedbacks.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblFeedbacks.setBounds(264, 399, 74, 15);
		add(lblFeedbacks);
		
		PercentofFeedbacks = new JSpinner();
		PercentofFeedbacks.setModel(new SpinnerNumberModel(0, 0, 100, 1));
		PercentofFeedbacks.setBounds(353, 393, 41, 27);
		add(PercentofFeedbacks);
		
		JLabel lblPublication = new JLabel("Publication:");
		lblPublication.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblPublication.setBounds(404, 399, 74, 15);
		add(lblPublication);
		
		JSpinner PercentofPublication = new JSpinner();
		PercentofPublication.setModel(new SpinnerNumberModel(0, 0, 100, 1));
		PercentofPublication.setBounds(488, 393, 41, 27);
		add(PercentofPublication);
		
		JLabel EnteredYear = new JLabel("Entered Year:");
		EnteredYear.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		EnteredYear.setBounds(39, 237, 90, 17);
		add(EnteredYear);
		
		JLabel From = new JLabel("from");
		From.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		From.setBounds(131, 237, 35, 17);
		add(From);
		
		Year1 = new JComboBox();
		Year1.setBounds(176, 236, 58, 21);
		add(Year1);
		
		JLabel To = new JLabel("to");
		To.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		To.setBounds(251, 237, 18, 17);
		add(To);
		
		Year2 = new JComboBox();
		Year2.setBounds(280, 236, 58, 21);
		add(Year2);
		
		JLabel Type = new JLabel("Type:");
		Type.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		Type.setBounds(39, 118, 41, 17);
		add(Type);
		
		Promoter = new JCheckBox("Promoter");
		Promoter.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		Promoter.setBackground(Color.WHITE);
		Promoter.setBounds(84, 115, 82, 23);
		add(Promoter);
		
		RBS = new JCheckBox("RBS");
		RBS.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		RBS.setBackground(Color.WHITE);
		RBS.setBounds(172, 115, 55, 23);
		add(RBS);
		
		Protein_Domain = new JCheckBox("Protein_Domain");
		Protein_Domain.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		Protein_Domain.setBackground(Color.WHITE);
		Protein_Domain.setBounds(231, 115, 123, 23);
		add(Protein_Domain);
		
		Protein_Coding_Sequence = new JCheckBox("Protein_Coding_Sequence");
		Protein_Coding_Sequence.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		Protein_Coding_Sequence.setBackground(Color.WHITE);
		Protein_Coding_Sequence.setBounds(368, 115, 189, 23);
		add(Protein_Coding_Sequence);
		
		Translation_Unit = new JCheckBox("Translation_Unit");
		Translation_Unit.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		Translation_Unit.setBackground(Color.WHITE);
		Translation_Unit.setBounds(84, 141, 125, 23);
		add(Translation_Unit);
		
		Terminator = new JCheckBox("Terminator");
		Terminator.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		Terminator.setBackground(Color.WHITE);
		Terminator.setBounds(213, 141, 99, 23);
		add(Terminator);
		
		DNA = new JCheckBox("DNA");
		DNA.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		DNA.setBackground(Color.WHITE);
		DNA.setBounds(311, 141, 68, 23);
		add(DNA);
		
		Plasmid_Backbone = new JCheckBox("Plasmid_Backbone");
		Plasmid_Backbone.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		Plasmid_Backbone.setBackground(Color.WHITE);
		Plasmid_Backbone.setBounds(386, 140, 143, 23);
		add(Plasmid_Backbone);
		
		Plasmid = new JCheckBox("Plasmid");
		Plasmid.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		Plasmid.setBackground(Color.WHITE);
		Plasmid.setBounds(84, 170, 74, 23);
		add(Plasmid);
		
		Primer = new JCheckBox("Primer");
		Primer.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		Primer.setBackground(Color.WHITE);
		Primer.setBounds(160, 170, 68, 23);
		add(Primer);
		
		Composite = new JCheckBox("Composite");
		Composite.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		Composite.setBackground(Color.WHITE);
		Composite.setBounds(238, 170, 100, 23);
		add(Composite);
		
		Protein_Generator = new JCheckBox("Protein_Generator");
		Protein_Generator.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		Protein_Generator.setBackground(Color.WHITE);
		Protein_Generator.setBounds(338, 170, 140, 23);
		add(Protein_Generator);
		
		Reporter = new JCheckBox("Reporter");
		Reporter.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		Reporter.setBackground(Color.WHITE);
		Reporter.setBounds(84, 199, 82, 23);
		add(Reporter);
		
		Inventer = new JCheckBox("Inventer");
		Inventer.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		Inventer.setBackground(Color.WHITE);
		Inventer.setBounds(172, 199, 74, 23);
		add(Inventer);
		
		Signalling = new JCheckBox("Signalling");
		Signalling.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		Signalling.setBackground(Color.WHITE);
		Signalling.setBounds(256, 199, 82, 23);
		add(Signalling);
		
		Measurement = new JCheckBox("Measurement");
		Measurement.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		Measurement.setBackground(Color.WHITE);
		Measurement.setBounds(348, 199, 113, 23);
		add(Measurement);
		
		Other = new JCheckBox("Other");
		Other.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		Other.setBackground(Color.WHITE);
		Other.setBounds(467, 199, 68, 23);
		add(Other);
		
		JLabel Sort = new JLabel("Sort by:");
		Sort.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		Sort.setBounds(10, 10, 68, 28);
		add(Sort);
		
		EnteredDate = new JCheckBox("Entered Date");
		EnteredDate.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		EnteredDate.setBackground(Color.WHITE);
		EnteredDate.setBounds(88, 35, 105, 23);
		add(EnteredDate);
		
		ConfirmedTimes = new JCheckBox("Confirmed Times");
		ConfirmedTimes.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		ConfirmedTimes.setBackground(Color.WHITE);
		ConfirmedTimes.setBounds(88, 58, 130, 23);
		add(ConfirmedTimes);
		
		GoogleQouteNumber = new JCheckBox("Google Qoute Number");
		GoogleQouteNumber.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		GoogleQouteNumber.setBackground(Color.WHITE);
		GoogleQouteNumber.setBounds(223, 35, 171, 23);
		add(GoogleQouteNumber);
		
		AverageStars = new JCheckBox("Average Stars");
		AverageStars.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		AverageStars.setBackground(Color.WHITE);
		AverageStars.setBounds(404, 35, 121, 23);
		add(AverageStars);
		
		BlastResult = new JCheckBox("BlastResult");
		BlastResult.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		BlastResult.setBackground(Color.WHITE);
		BlastResult.setBounds(223, 58, 105, 23);
		add(BlastResult);
		
		TotalScore = new JCheckBox("Total Score");
		TotalScore.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		TotalScore.setBackground(Color.WHITE);
		TotalScore.setBounds(340, 58, 113, 23);
		add(TotalScore);
	}
}
