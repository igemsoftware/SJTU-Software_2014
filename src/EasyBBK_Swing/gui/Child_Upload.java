package EasyBBK_Swing.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JToolTip;
import javax.swing.ScrollPaneConstants;
import javax.swing.JTextField;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.JCheckBox;

public class Child_Upload extends JPanel {
	public MainPage mainpage;
	public JScrollPane scrollpanel;
	public JTextField ShortDescription;
	public JComboBox Type;
	public JTextField Nickname;
	public JTextField Designers;
	public JComboBox DNAStatus;
	public JTextArea SequenceInformation;
	public JPanel UploadContainer;
	public JScrollPane textscrollpanel;
	public JButton AddSequence;
	public JButton AddSubpart;
	public JButton AddSubscar;
	public JCheckBox UseDefaultScar;
	public JPanel categorypanel;
	public JButton AddCategory;
	public JButton RemoveCategory;
	public JPanel featurespanel;
	public JButton AddFeature;
	public JButton RemoveFeature;
	public JTextArea ContactInfo;
	public JScrollPane contactscrollpanel;
	public JButton Submit;
	/**
	 * Create the panel.
	 */
	public Child_Upload(MainPage mainpage1) {
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				requestFocus();
			}
		});
		mainpage = mainpage1;
		setBounds(0, 0, 1366, 670);
		setBackground(new Color(255, 255, 255));
		setLayout(null);
		setVisible(true);
		
		UploadContainer = new JPanel();
		UploadContainer.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				UploadContainer.requestFocus();
			}
		});
		UploadContainer.setBounds(0, 0, 1348, 1250);
		UploadContainer.setPreferredSize(new Dimension(1348, 1250));
		UploadContainer.setBackground(new Color(0, 255, 255));
		UploadContainer.setLayout(null);
		
		scrollpanel = new JScrollPane(UploadContainer);
		
		JLabel Title = new JLabel("Welcome to the Upload Page!", JLabel.CENTER);
		Title.setFont(new Font("Times New Roman", Font.BOLD, 40));
		Title.setBounds(415, 10, 536, 50);
		UploadContainer.add(Title);
		
		JLabel Description = new JLabel("A short description of this biobrick:");
		Description.setFont(new Font("Times New Roman", Font.PLAIN, 24));
		Description.setBounds(373, 85, 354, 30);
		UploadContainer.add(Description);
		
		ShortDescription = new JTextField();
		ShortDescription.setFont(new Font("Times New Roman", Font.PLAIN, 24));
		ShortDescription.setColumns(10);
		ShortDescription.setBounds(373, 125, 536, 30);
		UploadContainer.add(ShortDescription);
		
		JLabel ChooseType = new JLabel("Type:");
		ChooseType.setFont(new Font("Times New Roman", Font.PLAIN, 24));
		ChooseType.setBounds(373, 165, 67, 30);
		UploadContainer.add(ChooseType);
		
		String type[] = {"Promoter", "RBS", "Protein_Domain", "Protein_Coding_Sequence", "Translation_Unit", "Terminator", "DNA", "Plasmid_Backbone", "Plasmid", "Primer", "Composite", "Protein_Generator", "Reporter", "Inventer", "Signalling", "Measurement", "Other"};
		Type = new JComboBox(type);
		Type.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		Type.setSelectedIndex(0);
		Type.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange() == ItemEvent.SELECTED){
					String s = (String)Type.getSelectedItem();
				}
			}
		});
		Type.setBounds(450, 165, 244, 30);
		UploadContainer.add(Type);
		
		JLabel ChooseNickname = new JLabel("Nickname:");
		ChooseNickname.setFont(new Font("Times New Roman", Font.PLAIN, 24));
		ChooseNickname.setBounds(373, 205, 113, 30);
		UploadContainer.add(ChooseNickname);
		
		Nickname = new JTextField();
		Nickname.setFont(new Font("Times New Roman", Font.PLAIN, 24));
		Nickname.setColumns(10);
		Nickname.setBounds(496, 205, 205, 30);
		UploadContainer.add(Nickname);
		
		JLabel ChooseDesigners = new JLabel("Designer(s):");
		ChooseDesigners.setFont(new Font("Times New Roman", Font.PLAIN, 24));
		ChooseDesigners.setBounds(373, 245, 125, 30);
		UploadContainer.add(ChooseDesigners);
		
		Designers = new JTextField();
		Designers.setFont(new Font("Times New Roman", Font.PLAIN, 24));
		Designers.setColumns(10);
		Designers.setBounds(506, 245, 205, 30);
		UploadContainer.add(Designers);
		
		JLabel Status = new JLabel("DNA Status:");
		Status.setFont(new Font("Times New Roman", Font.PLAIN, 24));
		Status.setBounds(373, 285, 135, 30);
		UploadContainer.add(Status);
		
		String dnastatus[] = {"Available", "Planning", "Informational", "Unavailable", "Deleted"};
		DNAStatus = new JComboBox(dnastatus);
		DNAStatus.setFont(new Font("Times New Roman", Font.PLAIN, 20));
	    DNAStatus.setSelectedIndex(0);
		DNAStatus.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange() == ItemEvent.SELECTED){
					String s = (String)Type.getSelectedItem();
				}
			}
		});
		DNAStatus.setBounds(516, 285, 135, 30);
		UploadContainer.add(DNAStatus);
		
		JLabel Sequence = new JLabel("Sequence Information:");
		Sequence.setFont(new Font("Times New Roman", Font.PLAIN, 24));
		Sequence.setBounds(373, 325, 231, 30);
		UploadContainer.add(Sequence);
		
		SequenceInformation = new JTextArea(4, 15);
		SequenceInformation.setEditable(false);
		SequenceInformation.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		SequenceInformation.setLineWrap(true);
		SequenceInformation.setWrapStyleWord(true);
		SequenceInformation.setBackground(new Color(255, 255, 255));
		
		textscrollpanel = new JScrollPane(SequenceInformation);
		textscrollpanel.setBounds(373, 365, 486, 117);
		JScrollBar scrollbar1 = new JScrollBar();
		scrollbar1.setUnitIncrement(20);
		textscrollpanel.setVerticalScrollBar(scrollbar1);
		textscrollpanel.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		textscrollpanel.validate();
		UploadContainer.add(textscrollpanel);
		
		AddSequence = new JButton("Add sequence");
		AddSequence.setToolTipText("Click me to add a sequence.");
		AddSequence.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		AddSequence.setBounds(373, 505, 156, 30);
		UploadContainer.add(AddSequence);
		
		AddSubpart = new JButton("Add subpart");
		AddSubpart.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		AddSubpart.setBounds(539, 505, 156, 30);
		UploadContainer.add(AddSubpart);
		
		AddSubscar = new JButton("Add subscar");
		AddSubscar.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		AddSubscar.setBounds(705, 505, 156, 30);
		UploadContainer.add(AddSubscar);
		
		UseDefaultScar = new JCheckBox("Use default scar");
		UseDefaultScar.setBackground(new Color(0, 255, 255));
		UseDefaultScar.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		UseDefaultScar.setBounds(373, 553, 169, 30);
		UploadContainer.add(UseDefaultScar);
		
		JLabel Category = new JLabel("Category:");
		Category.setFont(new Font("Times New Roman", Font.PLAIN, 24));
		Category.setBounds(373, 593, 113, 30);
		UploadContainer.add(Category);
		
		categorypanel = new JPanel();
		categorypanel.setBounds(373, 633, 486, 117);
		categorypanel.setBackground(new Color(255, 255, 255));
		categorypanel.setLayout(null);
		UploadContainer.add(categorypanel);
		
		AddCategory = new JButton("Add");
		AddCategory.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		AddCategory.setBounds(618, 593, 75, 30);
		UploadContainer.add(AddCategory);
		
		RemoveCategory = new JButton("Remove");
		RemoveCategory.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		RemoveCategory.setBounds(713, 593, 100, 30);
		UploadContainer.add(RemoveCategory);
		
		JLabel Features = new JLabel("Features:");
		Features.setFont(new Font("Times New Roman", Font.PLAIN, 24));
		Features.setBounds(373, 765, 113, 30);
		UploadContainer.add(Features);
		
		featurespanel = new JPanel();
		featurespanel.setBounds(373, 805, 486, 117);
		featurespanel.setBackground(new Color(255, 255, 255));
		featurespanel.setLayout(null);
		UploadContainer.add(featurespanel);
		
		AddFeature = new JButton("Add");
		AddFeature.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		AddFeature.setBounds(618, 765, 75, 30);
		UploadContainer.add(AddFeature);
		
		RemoveFeature = new JButton("Remove");
		RemoveFeature.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		RemoveFeature.setBounds(713, 765, 100, 30);
		UploadContainer.add(RemoveFeature);
		
		JLabel Contact = new JLabel("Contact Information:");
		Contact.setFont(new Font("Times New Roman", Font.PLAIN, 24));
		Contact.setBounds(373, 937, 240, 30);
		UploadContainer.add(Contact);
		
		ContactInfo = new JTextArea(4, 15);
		ContactInfo.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		ContactInfo.setLineWrap(true);
		ContactInfo.setWrapStyleWord(true);
		ContactInfo.setBackground(new Color(255, 255, 255));
		
		contactscrollpanel = new JScrollPane(ContactInfo);
		contactscrollpanel.setBounds(373, 982, 486, 117);
		JScrollBar scrollbar3 = new JScrollBar();
		scrollbar3.setUnitIncrement(20);
		contactscrollpanel.setVerticalScrollBar(scrollbar3);
		contactscrollpanel.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		contactscrollpanel.validate();
		UploadContainer.add(contactscrollpanel);
		
		AddFeature = new JButton("Submit");
		AddFeature.setFont(new Font("Times New Roman", Font.PLAIN, 24));
		AddFeature.setBounds(558, 1120, 120, 30);
		UploadContainer.add(AddFeature);
		
		JScrollBar scrollbar = new JScrollBar();
		scrollbar.setUnitIncrement(100);
		scrollpanel.setVerticalScrollBar(scrollbar);
		scrollpanel.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollpanel.setBounds(0, 0, 1366, 670);
		scrollpanel.validate();
		add(scrollpanel);
	}
}
