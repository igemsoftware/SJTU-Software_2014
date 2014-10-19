package EasyBBK_Swing.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.BorderFactory;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.JComboBox;
import javax.swing.JButton;

import data_center.SearchResultList;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Choicepanel extends JPanel {
	private static final long serialVersionUID = 1L;
	
	public JRadioButton Released;
	public JRadioButton Deleted;
	public JRadioButton NotReleased;
	public JRadioButton Available;
	public JRadioButton Planning;
	public JRadioButton Informational;
	public JRadioButton NotDeleted;
	public JRadioButton Deleted1;
	public JCheckBox EnteredDate;
	public JCheckBox GoogleQouteNumber;
	public JCheckBox AverageStars;
	public JCheckBox ConfirmedTimes;
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
	public JComboBox<?> Year1;
	public JComboBox<?> Year2;
	public JSpinner PercentofStatus;
	public JSpinner PercentofQuality;
	public JSpinner PercentofFeedbacks;
	public JSpinner PercentofPublication;
	public JCheckBox high;
	public JCheckBox middle;
	public JCheckBox low;
	public JButton Confirm;
	public Information information;
	public boolean confirmed_clicked = false;
	/**
	 * Create the Choicepanel.
	 */
	public Choicepanel() {
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getButton() == MouseEvent.BUTTON1)
					requestFocus();
			}
		});
		setBackground(new Color(255, 255, 255));
		setBounds(0, 0, 623, 489);
		setBorder(BorderFactory.createLineBorder(Color.black));
		setLayout(null);
		
		information = new Information();
		
		JLabel Filters = new JLabel("Filters:");
		Filters.setFont(new Font("Arial", Font.PLAIN, 20));
		Filters.setBounds(10, 81, 76, 28);
		add(Filters);
		
		JLabel ReleaseStatus = new JLabel("Part Status:");
		ReleaseStatus.setFont(new Font("Arial", Font.PLAIN, 16));
		ReleaseStatus.setBounds(39, 265, 103, 17);
		add(ReleaseStatus);
        
		ItemListener itemListener_releasestatus = new ItemListener() {
			JRadioButton jRadioButton;
 
            public void itemStateChanged(ItemEvent e) {
            	jRadioButton = (JRadioButton) e.getSource();
 
                if (jRadioButton.isSelected()) {
                    if(jRadioButton == Released){
                    	Deleted.setSelected(false);
                    	NotReleased.setSelected(false);
                    }
                    else if(jRadioButton == Deleted){
                    	Released.setSelected(false);
                    	NotReleased.setSelected(false);
                    }
                    else if(jRadioButton == NotReleased){
                    	Released.setSelected(false);
                    	Deleted.setSelected(false);
                    }
                }else{
                    
                }
            }
        };
		
        Released = new JRadioButton("Released");
        Released.addItemListener(itemListener_releasestatus);
		Released.setFont(new Font("Arial", Font.PLAIN, 16));
		Released.setBounds(156, 262, 103, 23);
		Released.setBackground(new Color(255, 255, 255));
		add(Released);
		
		Deleted = new JRadioButton("Deleted");
		Deleted.addItemListener(itemListener_releasestatus);
		Deleted.setFont(new Font("Arial", Font.PLAIN, 16));
		Deleted.setBounds(261, 262, 103, 23);
		Deleted.setBackground(new Color(255, 255, 255));
		add(Deleted);
		
		NotReleased = new JRadioButton("Not Released");
		NotReleased.addItemListener(itemListener_releasestatus);
		NotReleased.setFont(new Font("Arial", Font.PLAIN, 16));
		NotReleased.setBounds(366, 262, 121, 23);
		NotReleased.setBackground(new Color(255, 255, 255));
		add(NotReleased);
		
		JLabel DNAStatus = new JLabel("DNA Status:");
		DNAStatus.setFont(new Font("Arial", Font.PLAIN, 16));
		DNAStatus.setBounds(39, 291, 99, 15);
		add(DNAStatus);
		
		ItemListener itemListener_DNAstatus = new ItemListener() {
			JRadioButton jRadioButton;
 
            public void itemStateChanged(ItemEvent e) {
            	jRadioButton = (JRadioButton) e.getSource();
 
                if (jRadioButton.isSelected()) {
                    if(jRadioButton == Available){
                    	Planning.setSelected(false);
                    	Informational.setSelected(false);
                    }
                    else if(jRadioButton == Planning){
                    	Available.setSelected(false);
                    	Informational.setSelected(false);
                    }
                    else if(jRadioButton == Informational){
                    	Available.setSelected(false);
                    	Planning.setSelected(false);
                    }
                }else{
                    
                }
            }
        };
		
		Available = new JRadioButton("Available");
		Available.addItemListener(itemListener_DNAstatus);
		Available.setFont(new Font("Arial", Font.PLAIN, 16));
		Available.setBounds(157, 289, 103, 23);
		Available.setBackground(new Color(255, 255, 255));
		add(Available);
		
		Planning = new JRadioButton("Planning");
		Planning.addItemListener(itemListener_DNAstatus);
		Planning.setFont(new Font("Arial", Font.PLAIN, 16));
		Planning.setBounds(261, 288, 103, 23);
		Planning.setBackground(new Color(255, 255, 255));
		add(Planning);
		
		Informational = new JRadioButton("Informational");
		Informational.addItemListener(itemListener_DNAstatus);
		Informational.setFont(new Font("Arial", Font.PLAIN, 16));
		Informational.setBounds(366, 289, 121, 23);
		Informational.setBackground(new Color(255, 255, 255));
		add(Informational);
		
		JLabel Whetherornot = new JLabel("Whether or not Deleted:");
		Whetherornot.setFont(new Font("Arial", Font.PLAIN, 16));
		Whetherornot.setBounds(39, 321, 173, 15);
		add(Whetherornot);
		
		ItemListener itemListener_deleted = new ItemListener() {
			JRadioButton jRadioButton;
 
            public void itemStateChanged(ItemEvent e) {
            	jRadioButton = (JRadioButton) e.getSource();
 
                if (jRadioButton.isSelected()) {
                    if(jRadioButton == NotDeleted){
                    	Deleted1.setSelected(false);
                    }
                    else if(jRadioButton == Deleted1){
                    	NotDeleted.setSelected(false);
                    }
                } else {
                    
                }
 
            }
        };
		
		NotDeleted = new JRadioButton("Not Deleted");
		NotDeleted.addItemListener(itemListener_deleted);
		NotDeleted.setFont(new Font("Arial", Font.PLAIN, 16));
		NotDeleted.setBounds(235, 317, 121, 23);
		NotDeleted.setBackground(new Color(255, 255, 255));
		add(NotDeleted);
		
		Deleted1 = new JRadioButton("Deleted");
		Deleted1.addItemListener(itemListener_deleted);
		Deleted1.setFont(new Font("Arial", Font.PLAIN, 16));
		Deleted1.setBounds(366, 317, 103, 23);
		Deleted1.setBackground(new Color(255, 255, 255));
		add(Deleted1);
		
		JLabel AverageStar = new JLabel("Average Rating Given by Previous Teams:");
		AverageStar.setFont(new Font("Arial", Font.PLAIN, 16));
		AverageStar.setBounds(39, 347, 299, 15);
		add(AverageStar);
		
		high = new JCheckBox(">=4");
		high.setFont(new Font("Arial", Font.PLAIN, 16));
		high.setBounds(341, 342, 68, 23);
		high.setBackground(new Color(255, 255, 255));
		add(high);
		
		middle = new JCheckBox("2-4");
		middle.setFont(new Font("Arial", Font.PLAIN, 16));
		middle.setBounds(411, 342, 65, 23);
		middle.setBackground(new Color(255, 255, 255));
		add(middle);
		
		low = new JCheckBox("<2");
		low.setFont(new Font("Arial", Font.PLAIN, 16));
		low.setBounds(493, 342, 65, 23);
		low.setBackground(new Color(255, 255, 255));
		add(low);
		
		JLabel Preference = new JLabel("Preferences:");
		Preference.setFont(new Font("Arial", Font.PLAIN, 20));
		Preference.setBounds(10, 370, 121, 29);
		add(Preference);
		
		JLabel Status = new JLabel("Status(%):");
		Status.setFont(new Font("Arial", Font.PLAIN, 16));
		Status.setBounds(39, 411, 76, 15);
		add(Status);
		
		PercentofStatus = new JSpinner();
		PercentofStatus.setModel(new SpinnerNumberModel(0, 0, 100, 1));
		PercentofStatus.setBounds(113, 406, 41, 27);
		add(PercentofStatus);
		
		JLabel Quality = new JLabel("Reliability(%):");
		Quality.setFont(new Font("Arial", Font.PLAIN, 16));
		Quality.setBounds(156, 411, 103, 15);
		add(Quality);
		
		PercentofQuality = new JSpinner();
		PercentofQuality.setModel(new SpinnerNumberModel(0, 0, 100, 1));
		PercentofQuality.setBounds(253, 406, 41, 27);
		add(PercentofQuality);
		
		JLabel Feedbacks = new JLabel("Feedback(%):");
		Feedbacks.setFont(new Font("Arial", Font.PLAIN, 16));
		Feedbacks.setBounds(296, 411, 108, 15);
		add(Feedbacks);
		
		PercentofFeedbacks = new JSpinner();
		PercentofFeedbacks.setModel(new SpinnerNumberModel(0, 0, 100, 1));
		PercentofFeedbacks.setBounds(398, 406, 41, 27);
		add(PercentofFeedbacks);
		
		JLabel Publication = new JLabel("Publication(%):");
		Publication.setFont(new Font("Arial", Font.PLAIN, 16));
		Publication.setBounds(446, 411, 112, 15);
		add(Publication);
		
		PercentofPublication = new JSpinner();
		PercentofPublication.setModel(new SpinnerNumberModel(0, 0, 100, 1));
		PercentofPublication.setBounds(559, 406, 41, 27);
		add(PercentofPublication);
		
		JLabel EnteredYear = new JLabel("Entered Year:");
		EnteredYear.setFont(new Font("Arial", Font.PLAIN, 16));
		EnteredYear.setBounds(39, 229, 99, 17);
		add(EnteredYear);
		
		JLabel From = new JLabel("from");
		From.setFont(new Font("Arial", Font.PLAIN, 16));
		From.setBounds(147, 229, 43, 17);
		add(From);
		
		String year[] = {"2003", "2004", "2005", "2006", "2007", "2008", "2009", "2010", "2011", "2012", "2013", "2014"};
		Year1 = new JComboBox<Object>(year);
		Year1.setSelectedIndex(0);
		Year1.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange() == ItemEvent.SELECTED){
					String s = (String)Year1.getSelectedItem();
					information.enteredyear[0] = Integer.parseInt(s);
				}
			}
		});
		Year1.setBounds(200, 228, 58, 21);
		add(Year1);
		
		JLabel To = new JLabel("to");
		To.setFont(new Font("Arial", Font.PLAIN, 16));
		To.setBounds(277, 229, 28, 20);
		add(To);
		
		Year2 = new JComboBox<Object>(year);
		Year2.setSelectedIndex(11);
		Year2.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange() == ItemEvent.SELECTED){
					String s = (String)Year2.getSelectedItem();
					information.enteredyear[1] = Integer.parseInt(s);
				}
			}
		});
		Year2.setBounds(315, 228, 58, 21);
		add(Year2);
		
		JLabel Type = new JLabel("Type:");
		Type.setFont(new Font("Arial", Font.PLAIN, 16));
		Type.setBounds(39, 118, 45, 17);
		add(Type);
		
		Promoter = new JCheckBox("Promoter");
		Promoter.setFont(new Font("Arial", Font.PLAIN, 16));
		Promoter.setBackground(Color.WHITE);
		Promoter.setBounds(84, 115, 99, 23);
		add(Promoter);
		
		RBS = new JCheckBox("RBS");
		RBS.setFont(new Font("Arial", Font.PLAIN, 16));
		RBS.setBackground(Color.WHITE);
		RBS.setBounds(179, 115, 65, 23);
		add(RBS);
		
		Protein_Domain = new JCheckBox("Protein_Domain");
		Protein_Domain.setFont(new Font("Arial", Font.PLAIN, 16));
		Protein_Domain.setBackground(Color.WHITE);
		Protein_Domain.setBounds(246, 115, 148, 23);
		add(Protein_Domain);
		
		Protein_Coding_Sequence = new JCheckBox("Protein_Coding_Sequence");
		Protein_Coding_Sequence.setFont(new Font("Arial", Font.PLAIN, 16));
		Protein_Coding_Sequence.setBackground(Color.WHITE);
		Protein_Coding_Sequence.setBounds(395, 115, 222, 23);
		add(Protein_Coding_Sequence);
		
		Translation_Unit = new JCheckBox("Translation_Unit");
		Translation_Unit.setFont(new Font("Arial", Font.PLAIN, 16));
		Translation_Unit.setBackground(Color.WHITE);
		Translation_Unit.setBounds(84, 141, 150, 23);
		add(Translation_Unit);
		
		Terminator = new JCheckBox("Terminator");
		Terminator.setFont(new Font("Arial", Font.PLAIN, 16));
		Terminator.setBackground(Color.WHITE);
		Terminator.setBounds(235, 140, 103, 23);
		add(Terminator);
		
		DNA = new JCheckBox("DNA");
		DNA.setFont(new Font("Arial", Font.PLAIN, 16));
		DNA.setBackground(Color.WHITE);
		DNA.setBounds(346, 140, 68, 23);
		add(DNA);
		
		Plasmid_Backbone = new JCheckBox("Plasmid_Backbone");
		Plasmid_Backbone.setFont(new Font("Arial", Font.PLAIN, 16));
		Plasmid_Backbone.setBackground(Color.WHITE);
		Plasmid_Backbone.setBounds(435, 141, 165, 23);
		add(Plasmid_Backbone);
		
		Plasmid = new JCheckBox("Plasmid");
		Plasmid.setFont(new Font("Arial", Font.PLAIN, 16));
		Plasmid.setBackground(Color.WHITE);
		Plasmid.setBounds(84, 170, 86, 23);
		add(Plasmid);
		
		Primer = new JCheckBox("Primer");
		Primer.setFont(new Font("Arial", Font.PLAIN, 16));
		Primer.setBackground(Color.WHITE);
		Primer.setBounds(213, 170, 86, 23);
		add(Primer);
		
		Composite = new JCheckBox("Composite");
		Composite.setFont(new Font("Arial", Font.PLAIN, 16));
		Composite.setBackground(Color.WHITE);
		Composite.setBounds(324, 170, 109, 23);
		add(Composite);
		
		Protein_Generator = new JCheckBox("Protein_Generator");
		Protein_Generator.setFont(new Font("Arial", Font.PLAIN, 16));
		Protein_Generator.setBackground(Color.WHITE);
		Protein_Generator.setBounds(443, 170, 157, 23);
		add(Protein_Generator);
		
		Reporter = new JCheckBox("Reporter");
		Reporter.setFont(new Font("Arial", Font.PLAIN, 16));
		Reporter.setBackground(Color.WHITE);
		Reporter.setBounds(84, 199, 90, 23);
		add(Reporter);
		
		Inventer = new JCheckBox("Inventer");
		Inventer.setFont(new Font("Arial", Font.PLAIN, 16));
		Inventer.setBackground(Color.WHITE);
		Inventer.setBounds(176, 199, 82, 23);
		add(Inventer);
		
		Signalling = new JCheckBox("Signalling");
		Signalling.setFont(new Font("Arial", Font.PLAIN, 16));
		Signalling.setBackground(Color.WHITE);
		Signalling.setBounds(264, 199, 99, 23);
		add(Signalling);
		
		Measurement = new JCheckBox("Measurement");
		Measurement.setFont(new Font("Arial", Font.PLAIN, 16));
		Measurement.setBackground(Color.WHITE);
		Measurement.setBounds(365, 199, 131, 23);
		add(Measurement);
		
		Other = new JCheckBox("Other");
		Other.setFont(new Font("Arial", Font.PLAIN, 16));
		Other.setBackground(Color.WHITE);
		Other.setBounds(500, 199, 68, 23);
		add(Other);
		
		JLabel Sort = new JLabel("Sort by:");
		Sort.setFont(new Font("Arial", Font.PLAIN, 20));
		Sort.setBounds(10, 10, 76, 28);
		add(Sort);
		
		ItemListener itemListener_sort = new ItemListener() {
            JCheckBox jCheckBox;
 
            public void itemStateChanged(ItemEvent e) {
                jCheckBox = (JCheckBox) e.getSource();
 
                if (jCheckBox.isSelected()) {
                    if(jCheckBox == EnteredDate){
                    	ConfirmedTimes.setSelected(false);
                    	GoogleQouteNumber.setSelected(false);
                    	AverageStars.setSelected(false);
                    	TotalScore.setSelected(false);
                    }
                    else if(jCheckBox == ConfirmedTimes){
                    	EnteredDate.setSelected(false);
                    	GoogleQouteNumber.setSelected(false);
                    	AverageStars.setSelected(false);
                    	TotalScore.setSelected(false);
                    }
                    else if(jCheckBox == GoogleQouteNumber){
                    	EnteredDate.setSelected(false);
                    	ConfirmedTimes.setSelected(false);
                    	AverageStars.setSelected(false);
                    	TotalScore.setSelected(false);
                    }
                    else if(jCheckBox == AverageStars){
                    	EnteredDate.setSelected(false);
                    	ConfirmedTimes.setSelected(false);
                    	GoogleQouteNumber.setSelected(false);
                    	TotalScore.setSelected(false);
                    }
                    else if(jCheckBox == TotalScore){
                    	EnteredDate.setSelected(false);
                    	ConfirmedTimes.setSelected(false);
                    	GoogleQouteNumber.setSelected(false);
                    	AverageStars.setSelected(false);
                    }
                }else{
                    
                }
            }
        };
		
		EnteredDate = new JCheckBox("Entered Date");
		EnteredDate.addItemListener(itemListener_sort);
		EnteredDate.setFont(new Font("Arial", Font.PLAIN, 16));
		EnteredDate.setBackground(Color.WHITE);
		EnteredDate.setBounds(88, 35, 131, 23);
		add(EnteredDate);
		
		ConfirmedTimes = new JCheckBox("Confirmed Times");
		ConfirmedTimes.addItemListener(itemListener_sort);
		ConfirmedTimes.setFont(new Font("Arial", Font.PLAIN, 16));
		ConfirmedTimes.setBackground(Color.WHITE);
		ConfirmedTimes.setBounds(88, 58, 146, 23);
		add(ConfirmedTimes);
		
		GoogleQouteNumber = new JCheckBox("Number of Results Related on Google Scholar");
		GoogleQouteNumber.addItemListener(itemListener_sort);
		GoogleQouteNumber.setFont(new Font("Arial", Font.PLAIN, 16));
		GoogleQouteNumber.setBackground(Color.WHITE);
		GoogleQouteNumber.setBounds(243, 35, 351, 23);
		add(GoogleQouteNumber);
		
		AverageStars = new JCheckBox("Average Rating");
		AverageStars.addItemListener(itemListener_sort);
		AverageStars.setFont(new Font("Arial", Font.PLAIN, 16));
		AverageStars.setBackground(Color.WHITE);
		AverageStars.setBounds(469, 58, 148, 23);
		add(AverageStars);
		
		TotalScore = new JCheckBox("Default Score Given to Part");
		TotalScore.addItemListener(itemListener_sort);
		TotalScore.setFont(new Font("Arial", Font.PLAIN, 16));
		TotalScore.setBackground(Color.WHITE);
		TotalScore.setBounds(243, 58, 215, 23);
		add(TotalScore);
		
		information = new Information();
		
		Confirm = new JButton("Confirm");
		Confirm.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getButton() == MouseEvent.BUTTON1){
					
					if(EnteredDate.isSelected()){
						information.sortby = "Entered Date";
					}
					if(GoogleQouteNumber.isSelected()){
						information.sortby = "Google Qoute Number";
					}
					if(AverageStars.isSelected()){
						information.sortby = "Average Stars";
					}
					if(ConfirmedTimes.isSelected()){
						information.sortby = "Confirmed Times";
					}
					if(TotalScore.isSelected()){
						information.sortby = "Total Score";
					}
					
					if(Promoter.isSelected()){
						information.type.add(SearchResultList.Filter.Type.PRIMER);
					}
					if(RBS.isSelected()){
						information.type.add(SearchResultList.Filter.Type.RBS);
					}
					if(Protein_Domain.isSelected()){
						information.type.add(SearchResultList.Filter.Type.PROTEIN_DOMAIN);
					}
					if(Protein_Coding_Sequence.isSelected()){
						information.type.add(SearchResultList.Filter.Type.PROTEIN_CODING_SEQUENCE);
					}
					if(Translation_Unit.isSelected()){
						information.type.add(SearchResultList.Filter.Type.TRANSLATIONAL_UNIT);
					}
					if(Terminator.isSelected()){
						information.type.add(SearchResultList.Filter.Type.TERMINATOR);
					}
					if(DNA.isSelected()){
						information.type.add(SearchResultList.Filter.Type.DNA);
					}
					if(Plasmid_Backbone.isSelected()){
						information.type.add(SearchResultList.Filter.Type.PLASMID_BACKBONE);
					}
					if(Plasmid.isSelected()){
						information.type.add(SearchResultList.Filter.Type.PLASMID);
					}
					if(Primer.isSelected()){
						information.type.add(SearchResultList.Filter.Type.PRIMER);
					}
					if(Composite.isSelected()){
						information.type.add(SearchResultList.Filter.Type.COMPOSITE);
					}
					if(Protein_Generator.isSelected()){
						information.type.add(SearchResultList.Filter.Type.PROTEIN_GENERATOR);
					}
					if(Reporter.isSelected()){
						information.type.add(SearchResultList.Filter.Type.REPORTER);
					}
					if(Inventer.isSelected()){
						information.type.add(SearchResultList.Filter.Type.INVERTER);
					}
					if(Signalling.isSelected()){
						information.type.add(SearchResultList.Filter.Type.SIGNALLING);
					}
					if(Other.isSelected()){
						information.type.add(SearchResultList.Filter.Type.OTHER);
					}
					
					if(Released.isSelected()){
						information.releasestatus.released = true;
					}
					if(Deleted.isSelected()){
						information.releasestatus.deleted = true;
					}
					if(NotReleased.isSelected()){
						information.releasestatus.notreleased = true;
					}
					
					if(Available.isSelected()){
						information.dnastatus.available = true;
					}
					if(Planning.isSelected()){
						information.dnastatus.planning = true;
					}
					if(Informational.isSelected()){
						information.dnastatus.informational = true;
					}
					
					if(NotDeleted.isSelected()){
						information.whetherornot = false;
					}
					if(Deleted1.isSelected()){
						information.whetherornot = true;
					}
					
					if(high.isSelected()){
						information.averagestars.high = true;
					}
					if(middle.isSelected()){
						information.averagestars.middle = true;
					}
					if(low.isSelected()){
						information.averagestars.low = true;
					}
					
					information.preferences.status = (Integer)PercentofStatus.getValue();
					information.preferences.quality = (Integer)PercentofQuality.getValue();
					information.preferences.publication = (Integer)PercentofPublication.getValue();
					information.preferences.feedbacks = (Integer)PercentofFeedbacks.getValue();
					
					confirmed_clicked = true;
				}
			}
		});
		Confirm.setFont(new Font("Arial", Font.BOLD, 16));
		Confirm.setBounds(243, 451, 121, 28);
		add(Confirm);
	}
}
