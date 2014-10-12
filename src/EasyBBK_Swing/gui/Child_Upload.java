package EasyBBK_Swing.gui;

import java.awt.Color;

import sun.misc.*;

import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JToolTip;
import javax.swing.ScrollPaneConstants;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.JCheckBox;

import data_center.*;
import data_center.BbkUpload.*;

import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Child_Upload extends JPanel {
	public MainPage mainpage;
	public JScrollPane scrollpanel;
	public JTextField ShortDescription;
	public JComboBox Type;
	public JTextField Nickname;
	public JTextField Designers;
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
	public JPanel featurepanel;
	public JButton AddFeature;
	public JButton RemoveFeature;
	public JTextArea ContactInfo;
	public JScrollPane contactscrollpanel;
	public JButton SubmitToDatabase;
	public JButton SubmitToWebsite;
	public String typestring;
	public String information_shown = "";
	public BbkUpload bbkupload;
	public JPanel parameterpanel;
	public JButton AddParameter;
	public JButton RemoveParameter;
	public JTextArea LongDescription;
	public JScrollPane LDscrollpanel;
	public JTextArea Source;
	public JScrollPane Sourcescrollpanel;
	public JTextArea DesignConsideration;
	public JScrollPane DCscrollpanel;
	public int parameternumber = 0;
	public Parameter_item[] parameter_item = new Parameter_item[4];
	public int categorynumber = 0;
	public Category_item[] category_item = new Category_item[4];
	public int featurenumber = 0;
	public Feature_item[] feature_item = new Feature_item[4];
	public JTextField UserName;
	public JPasswordField Password;
	public SubpartDialog subpartdialog;
	public JPanel showinfopanel;
	public JLabel showinfo;
	public JLabel BackGround;
	/**
	 * Create the panel.
	 */
	public Child_Upload(MainPage mainpage1) {
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getButton() == e.BUTTON1)
					requestFocus();
			}
		});
		bbkupload = new BbkUpload();
		mainpage = mainpage1;
		setBounds(0, 0, 1366, 670);
		setBackground(new Color(255, 255, 255));
		setLayout(null);
		setVisible(true);
		
		UploadContainer = new JPanel();
		UploadContainer.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getButton() == e.BUTTON1)
					UploadContainer.requestFocus();
			}
		});
		UploadContainer.setBounds(0, 0, 1348, 1800);
		UploadContainer.setPreferredSize(new Dimension(1348, 1800));
		UploadContainer.setBackground(new Color(255, 255, 255));
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
		typestring = "Promoter";
		Type.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		Type.setSelectedIndex(0);
		Type.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange() == ItemEvent.SELECTED){
					typestring = (String)Type.getSelectedItem();
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
		
		JLabel Sequence = new JLabel("Sequence Information:");
		Sequence.setFont(new Font("Times New Roman", Font.PLAIN, 24));
		Sequence.setBounds(373, 285, 231, 30);
		UploadContainer.add(Sequence);
		
		SequenceInformation = new JTextArea(4, 15);
		SequenceInformation.setEditable(false);
		SequenceInformation.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		SequenceInformation.setLineWrap(true);
		SequenceInformation.setWrapStyleWord(true);
		SequenceInformation.setBackground(new Color(255, 255, 255));
		
		textscrollpanel = new JScrollPane(SequenceInformation);
		textscrollpanel.setBounds(373, 325, 486, 117);
		JScrollBar scrollbar1 = new JScrollBar();
		scrollbar1.setUnitIncrement(20);
		textscrollpanel.setVerticalScrollBar(scrollbar1);
		textscrollpanel.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		textscrollpanel.validate();
		UploadContainer.add(textscrollpanel);
		
		AddSequence = new JButton("Add sequence");
		AddSequence.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getButton() == e.BUTTON1){
					new SequenceDialog(mainpage, "Please input a sequence", true);
					if(mainpage.sequencestring.equals("") == false){
						information_shown += mainpage.sequencestring;
						information_shown += "  ";
						SequenceInformation.setText(information_shown);
						bbkupload.sequenceTokens.add(mainpage.sequencestring);
						mainpage.sequencestring = "";
					}	
				}		
			}
		});
		AddSequence.setToolTipText("Click me to add a sequence.");
		AddSequence.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		AddSequence.setBounds(373, 465, 156, 30);
		UploadContainer.add(AddSequence);
		
		AddSubpart = new JButton("Add subpart");
		AddSubpart.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getButton() == e.BUTTON1){
					subpartdialog = new SubpartDialog(mainpage, "Please input a subpart name", true);
					if(mainpage.subpartstring.equals("") == false){
						information_shown += mainpage.subpartstring;
						information_shown += "  ";
						SequenceInformation.setText(information_shown);
						bbkupload.sequenceTokens.add(mainpage.subpart_bbkdetail);
						mainpage.subpartstring = "";
					}
				}
			}
		});
		AddSubpart.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		AddSubpart.setBounds(539, 465, 156, 30);
		UploadContainer.add(AddSubpart);
		
		AddSubscar = new JButton("Add subscar");
		AddSubscar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getButton() == e.BUTTON1){
					new SubscarDialog(mainpage, "Please input a subscar name", true);
					if(mainpage.subscarstring.equals("") == false){
						information_shown += mainpage.subscarstring;
						information_shown += "  ";
						SequenceInformation.setText(information_shown);
						bbkupload.sequenceTokens.add(mainpage.subscar);
						mainpage.subscarstring = "";
					}
				}
			}
		});
		AddSubscar.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		AddSubscar.setBounds(705, 465, 156, 30);
		UploadContainer.add(AddSubscar);
		
		UseDefaultScar = new JCheckBox("Use default scar");
		UseDefaultScar.setBackground(new Color(0, 255, 255));
		UseDefaultScar.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		UseDefaultScar.setBounds(373, 513, 169, 30);
		UploadContainer.add(UseDefaultScar);
		
		JLabel Parameter = new JLabel("Parameter:");
		Parameter.setFont(new Font("Times New Roman", Font.PLAIN, 24));
		Parameter.setBounds(373, 553, 113, 30);
		UploadContainer.add(Parameter);
		
		parameterpanel = new JPanel();
		parameterpanel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getButton() == e.BUTTON1)
					parameterpanel.requestFocus();
			}
		});
		parameterpanel.setBounds(373, 593, 486, 124);
		parameterpanel.setPreferredSize(new Dimension(468, 500));
		parameterpanel.setBackground(new Color(155, 255, 255));
		parameterpanel.setLayout(null);
		UploadContainer.add(parameterpanel);
		
		AddParameter = new JButton("Add");
		AddParameter.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getButton() == e.BUTTON1){
					if(parameternumber < 4){
						parameter_item[parameternumber] = new Parameter_item();
						parameter_item[parameternumber].setLocation(0, parameternumber*31);
						parameterpanel.add(parameter_item[parameternumber]);
						parameterpanel.updateUI();
						parameternumber++;
					}
				}
			}
		});
		AddParameter.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		AddParameter.setBounds(618, 553, 75, 30);
		UploadContainer.add(AddParameter);
		
		RemoveParameter = new JButton("Remove");
		RemoveParameter.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getButton() == e.BUTTON1){
					if(parameternumber >= 1){
						parameterpanel.remove(parameter_item[parameternumber-1]);
						parameterpanel.updateUI();
						parameternumber--;
					}
				}
			}
		});
		RemoveParameter.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		RemoveParameter.setBounds(713, 553, 100, 30);
		UploadContainer.add(RemoveParameter);
		
		JLabel Category = new JLabel("Category:");
		Category.setFont(new Font("Times New Roman", Font.PLAIN, 24));
		Category.setBounds(373, 733, 113, 30);
		UploadContainer.add(Category);
		
		categorypanel = new JPanel();
		categorypanel.setBounds(373, 773, 486, 124);
		categorypanel.setBackground(new Color(155, 255, 255));
		categorypanel.setLayout(null);
		UploadContainer.add(categorypanel);
		
		AddCategory = new JButton("Add");
		AddCategory.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getButton() == e.BUTTON1){
					if(categorynumber < 4){
						category_item[categorynumber] = new Category_item();
						category_item[categorynumber].setLocation(0, categorynumber*31);
						categorypanel.add(category_item[categorynumber]);
						categorypanel.updateUI();
						categorynumber++;
					}
				}
			}
		});
		AddCategory.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		AddCategory.setBounds(618, 733, 75, 30);
		UploadContainer.add(AddCategory);
		
		RemoveCategory = new JButton("Remove");
		RemoveCategory.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getButton() == e.BUTTON1){
					if(categorynumber >= 1){
						categorypanel.remove(category_item[categorynumber-1]);
						categorypanel.updateUI();
						categorynumber--;
					}
				}
			}
		});
		RemoveCategory.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		RemoveCategory.setBounds(713, 733, 100, 30);
		UploadContainer.add(RemoveCategory);
		
		JLabel Features = new JLabel("Features:");
		Features.setFont(new Font("Times New Roman", Font.PLAIN, 24));
		Features.setBounds(373, 905, 113, 30);
		UploadContainer.add(Features);
		
		featurepanel = new JPanel();
		featurepanel.setBounds(373, 945, 486, 124);
		featurepanel.setBackground(new Color(155, 255, 255));
		featurepanel.setLayout(null);
		UploadContainer.add(featurepanel);
		
		AddFeature = new JButton("Add");
		AddFeature.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getButton() == e.BUTTON1){
					if(featurenumber < 4){
						feature_item[featurenumber] = new Feature_item();
						feature_item[featurenumber].setLocation(0, featurenumber*31);
						featurepanel.add(feature_item[featurenumber]);
						featurepanel.updateUI();
						featurenumber++;
					}
				}
			}
		});
		AddFeature.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		AddFeature.setBounds(618, 905, 75, 30);
		UploadContainer.add(AddFeature);
		
		RemoveFeature = new JButton("Remove");
		RemoveFeature.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getButton() == e.BUTTON1){
					if(featurenumber >= 1){
						featurepanel.remove(feature_item[featurenumber-1]);
						featurepanel.updateUI();
						featurenumber--;
					}
				}
			}
		});
		RemoveFeature.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		RemoveFeature.setBounds(713, 905, 100, 30);
		UploadContainer.add(RemoveFeature);
		
		JLabel LongDescription_text = new JLabel("Long Description:");
		LongDescription_text.setFont(new Font("Times New Roman", Font.PLAIN, 24));
		LongDescription_text.setBounds(373, 1077, 200, 30);
		UploadContainer.add(LongDescription_text);
		
		LongDescription = new JTextArea(4, 15);
		LongDescription.setEditable(true);
		LongDescription.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		LongDescription.setLineWrap(true);
		LongDescription.setWrapStyleWord(true);
		LongDescription.setBackground(new Color(255, 255, 255));
		
		LDscrollpanel = new JScrollPane(LongDescription);
		LDscrollpanel.setBounds(373, 1117, 486, 117);
		JScrollBar scrollbar4 = new JScrollBar();
		scrollbar4.setUnitIncrement(20);
		LDscrollpanel.setVerticalScrollBar(scrollbar4);
		LDscrollpanel.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		LDscrollpanel.validate();
		UploadContainer.add(LDscrollpanel);
		
		JLabel Claim = new JLabel("Contents below only can be uploaded to offical website!");
		Claim.setForeground(Color.red);
		Claim.setFont(new Font("Times New Roman", Font.PLAIN, 24));
		Claim.setBounds(373, 1249, 600, 30);
		UploadContainer.add(Claim);
		
		JLabel Sourcetext = new JLabel("The source of this part:");
		Sourcetext.setFont(new Font("Times New Roman", Font.PLAIN, 24));
		Sourcetext.setBounds(373, 1289, 240, 30);
		UploadContainer.add(Sourcetext);
		
		Source = new JTextArea(4, 15);
		Source.setEditable(true);
		Source.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		Source.setLineWrap(true);
		Source.setWrapStyleWord(true);
		Source.setBackground(new Color(255, 255, 255));
		
		Sourcescrollpanel = new JScrollPane(Source);
		Sourcescrollpanel.setBounds(373, 1329, 486, 117);
		JScrollBar scrollbar5 = new JScrollBar();
		scrollbar5.setUnitIncrement(20);
		Sourcescrollpanel.setVerticalScrollBar(scrollbar5);
		Sourcescrollpanel.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		Sourcescrollpanel.validate();
		UploadContainer.add(Sourcescrollpanel);
		
		JLabel DesignConsiderationtext = new JLabel("Design Consideration:");
		DesignConsiderationtext.setFont(new Font("Times New Roman", Font.PLAIN, 24));
		DesignConsiderationtext.setBounds(373, 1461, 240, 30);
		UploadContainer.add(DesignConsiderationtext);
		
		DesignConsideration = new JTextArea(4, 15);
		DesignConsideration.setEditable(true);
		DesignConsideration.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		DesignConsideration.setLineWrap(true);
		DesignConsideration.setWrapStyleWord(true);
		DesignConsideration.setBackground(new Color(255, 255, 255));
		
		DCscrollpanel = new JScrollPane(DesignConsideration);
		DCscrollpanel.setBounds(373, 1501, 486, 117);
		JScrollBar scrollbar6 = new JScrollBar();
		scrollbar6.setUnitIncrement(20);
		DCscrollpanel.setVerticalScrollBar(scrollbar6);
		DCscrollpanel.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		DCscrollpanel.validate();
		UploadContainer.add(DCscrollpanel);
		
		JLabel username = new JLabel("User Name:");
		username.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		username.setBounds(373, 1633, 103, 24);
		UploadContainer.add(username);
		
		JLabel password = new JLabel("Password:");
		password.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		password.setBounds(373, 1667, 103, 24);
		UploadContainer.add(password);
		
		UserName = new JTextField();
		UserName.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		UserName.setBounds(486, 1633, 146, 24);
		UploadContainer.add(UserName);
		UserName.setColumns(10);
		
		Password = new JPasswordField();
		Password.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		Password.setColumns(10);
		Password.setBounds(486, 1667, 146, 24);
		UploadContainer.add(Password);
		
		SubmitToDatabase = new JButton("<html>" + "Submit to" + "<br>" + " our database" + "</html>");
		SubmitToDatabase.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getButton() == e.BUTTON1){
					showinfo.setText("Uploading your biobrick...");;
					showinfopanel.updateUI();
					
					bbkupload.shortDesc = ShortDescription.getText();
					bbkupload.type = typestring;
					bbkupload.nickname = Nickname.getText();
					bbkupload.author = Designers.getText();
					if(UseDefaultScar.isSelected()){
						bbkupload.setSequence(true, false);
					}
					else if(!UseDefaultScar.isSelected()){
						bbkupload.setSequence(false, false);
					}
					
					for(int i = 0; i < parameternumber; i++){
						Parameter parameter = new Parameter(parameter_item[i].content, parameter_item[i].textField.getText(), null, null, null, null);
						bbkupload.parameters.add(parameter);
					}
					
					for(int i = 0; i < categorynumber; i++){
						Category category = new Category(category_item[i].content);
						bbkupload.categories.add(category);
					}
					
					for(int i = 0; i < featurenumber; i++){
						Feature feature = new Feature(null, feature_item[i].Label.getText(), feature_item[i].content1, feature_item[i].content2, feature_item[i].Start.getText(), feature_item[i].End.getText());
						bbkupload.features.add(feature);
					}
					bbkupload.longDesc = LongDescription.getText();
					UploadCenter uploadcenter = new UploadCenter();
					//uploadcenter.getBbkUploadByNameAndOddNum(name, oddNum);
				}
			}
		});
		SubmitToDatabase.setFont(new Font("Times New Roman", Font.PLAIN, 24));
		SubmitToDatabase.setBounds(403, 1720, 180, 55);
		UploadContainer.add(SubmitToDatabase);
		
		SubmitToWebsite = new JButton("<html>" + "Submit to" + "<br>" + " offical website" + "</html>");
		SubmitToWebsite.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getButton() == e.BUTTON1){
					bbkupload.shortDesc = ShortDescription.getText();
					bbkupload.type = typestring;
					bbkupload.nickname = Nickname.getText();
					bbkupload.author = Designers.getText();
					if(UseDefaultScar.isSelected()){
						bbkupload.setSequence(true, false);
					}
					else if(!UseDefaultScar.isSelected()){
						bbkupload.setSequence(false, false);
					}
					
					for(int i = 0; i < parameternumber; i++){
						Parameter parameter = new Parameter(parameter_item[i].content, parameter_item[i].textField.getText(), null, null, null, null);
						bbkupload.parameters.add(parameter);
					}
					
					for(int i = 0; i < categorynumber; i++){
						Category category = new Category(category_item[i].content);
						bbkupload.categories.add(category);
					}
					
					for(int i = 0; i < featurenumber; i++){
						Feature feature = new Feature(null, feature_item[i].Label.getText(), feature_item[i].content1, feature_item[i].content2, feature_item[i].Start.getText(), feature_item[i].End.getText());
						bbkupload.features.add(feature);
					}
					bbkupload.source = Source.getText();
					bbkupload.notes = DesignConsideration.getText();
					
					WriteTxt rt = new WriteTxt();
					Thread demo1 = new Thread(rt);
					demo1.start();
				}
			}
		});
		SubmitToWebsite.setFont(new Font("Times New Roman", Font.PLAIN, 24));
		SubmitToWebsite.setBounds(649, 1720, 180, 55);
		UploadContainer.add(SubmitToWebsite);
		
		JLabel information = new JLabel("Information", SwingConstants.CENTER);
		information.setBounds(1040, 1450, 120, 30);
		information.setFont(new Font("Times New Roman", Font.PLAIN, 24));
		UploadContainer.add(information);
		
		showinfopanel = new JPanel();
		showinfopanel.setBounds(920, 1500, 360, 240);
		showinfopanel.setBackground(new Color(255, 255, 255));
		UploadContainer.add(showinfopanel);
		
		showinfo = new JLabel("", SwingConstants.CENTER);
		showinfo.setVisible(true);
		showinfo.setBounds(0, 0, 360, 240);
		showinfo.setFont(new Font("Times New Roman", Font.PLAIN, 24));
		showinfopanel.add(showinfo);
		
		BackGround = new JLabel("");
		BackGround.setVisible(true);
		BackGround.setBounds(0, 0, 1366, 1800);
		BackGround.setIcon(new ImageIcon(MainPage.class.getResource("/EasyBBK_Swing/image/BackGround2.png")));
		UploadContainer.add(BackGround);
		
		JScrollBar scrollbar = new JScrollBar();
		scrollbar.setUnitIncrement(100);
		scrollpanel.setVerticalScrollBar(scrollbar);
		scrollpanel.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollpanel.setBounds(0, 0, 1366, 670);
		scrollpanel.validate();
		add(scrollpanel);
	}
	
	class WriteTxt implements Runnable {
		public void run() {
			try {
				showinfo.setText("Uploading your biobrick...");;
				showinfopanel.updateUI();				
				boolean login = OfficialUploadPoster.login(UserName.getText(), String.valueOf(Password.getPassword()));
				if (!login)
				{
					showinfo.setText("Username or password is wrong.");
				}
				showinfo.setText("<html>Uploading your biobrick...<br>10% Complete</html>");
				String newBbk = OfficialUploadPoster.getNextAvailablePartName();
				newBbk = "BBa_K1479011";
				showinfo.setText("<html>Uploading your biobrick...<br>21% Complete<br>New BioBrick is "+newBbk+"</html>");
				String newId = OfficialUploadPoster.createNewPart(newBbk, bbkupload);
				bbkupload.setName(newBbk);
				bbkupload.setID(newId);
				showinfo.setText("<html>Uploading your biobrick...<br>33% Complete<br>New BioBrick is "+newBbk+"</html>");
				OfficialUploadPoster.modifyPrimaryInfo(bbkupload);
				showinfo.setText("<html>Uploading your biobrick...<br>45% Complete<br>New BioBrick is "+newBbk+"</html>");
				OfficialUploadPoster.modifyParameters(bbkupload);
				showinfo.setText("<html>Uploading your biobrick...<br>58% Complete<br>New BioBrick is "+newBbk+"</html>");
				OfficialUploadPoster.modifyCategories(bbkupload);
				showinfo.setText("<html>Uploading your biobrick...<br>72% Complete<br>New BioBrick is "+newBbk+"</html>");
				OfficialUploadPoster.modifySequence(bbkupload);
				showinfo.setText("<html>Uploading your biobrick...<br>87% Complete<br>New BioBrick is "+newBbk+"</html>");
				OfficialUploadPoster.modifyFeatures(bbkupload);
				showinfo.setText("<html>Uploading your biobrick...<br>100% Complete<br>New BioBrick is "+newBbk+"</html>");
				
				
				

//				System.out.print(1);
//				showinfo.setText("Uploading your biobrick...");;
//				showinfopanel.updateUI();
//				FileWriter output = new FileWriter("upload");
//				BufferedWriter bf = new BufferedWriter(output);
//				/*for (String l : list) {
//					bf.write(l + "\r\n");
//				}*/
//				//System.out.print(1);
//				String pwd_output = (new BASE64Encoder()).encode(String.valueOf(Password.getPassword()).getBytes());
//				bf.write(UserName.getText() + "\r\n");
//				bf.write(pwd_output + "\r\n");
//				bf.write(typestring + "\r\n");
//				bf.write(ShortDescription.getText() + "\r\n");
//				bf.write(LongDescription.getText() + "\r\n");
//				bf.write(Source.getText() + "\r\n");
//				bf.write(DesignConsideration.getText() + "\r\n");
//				bf.write(Nickname.getText() + "\r\n");
//				bf.write(Designers.getText() + "\r\n");
//				//System.out.print(2);
//				bf.write("{");
//				for(int i = 0; i < parameternumber; i++){
//					if(i == (parameternumber - 1)) 
//						bf.write("'" + parameter_item[i].content + "':'" + parameter_item[i].textField.getText() + "'");
//					else 
//						bf.write("'" + parameter_item[i].content + "':'" + parameter_item[i].textField.getText() + "',");
//				}
//				bf.write("}" + "\r\n");
//				
//				bf.write("{");
//				for(int i = 0; i < categorynumber; i++){
//					if(i == (categorynumber - 1)) bf.write("'" + category_item[i].content + "'");
//					else bf.write("'" + category_item[i].content + "',");
//				}
//				bf.write("}" + "\r\n");
//				//System.out.print(3);
//				if(UseDefaultScar.isSelected()){
//					bbkupload.setSequence(true, false);
//				}
//				else if(!UseDefaultScar.isSelected()){
//					bbkupload.setSequence(false, false);
//				}
//				//System.out.print(3.5);
//				//if(subpartdialog.confirmedflag){
//				//waitingdialog.inputtext.setText("Getting sequence");
//				bf.write(bbkupload.getSequence() + "\r\n");
//				//}
//				//child_upload.waitingdialog.inputtext.setText("Getting sequence done");
//				
//				//System.out.print(4);
//				bf.write("[");
//				for(int i = 0; i < featurenumber; i++){
//					if(i == (featurenumber - 1)) bf.write("['" + feature_item[i].content1 + "','"+ feature_item[i].Label.getText() + "','" + feature_item[i].Start.getText() + "','" + feature_item[i].End.getText() + "','" + feature_item[i].content2 + "']");
//					else bf.write("['" + feature_item[i].content1 + "','"+ feature_item[i].Label.getText() + "','" + feature_item[i].Start.getText() + "','" + feature_item[i].End.getText() + "','" + feature_item[i].content2 + "'],");
//				}
//				bf.write("]");
//				//System.out.print(5);
//				
//				bf.flush();
//				bf.close();
//				//System.out.println(System.getProperty("user.dir"));
//				
//				Process proc = Runtime.getRuntime().exec("./post_py/LoginToUploadBiobrick.exe");
//				BufferedReader br = new BufferedReader(new InputStreamReader(proc.getInputStream()));
//				try {
//					proc.waitFor();
//				} catch (InterruptedException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//				String str = br.readLine();		        
//		        if (str.equals("-1")){
//		        	//System.out.println("Username or password is wrong");
//		        	showinfo.setText("Username or password is wrong");
//		        	showinfopanel.updateUI();
//		        	//waitingdialog.inputtext.setText("Username or password is wrong");
//		        	//waitingdialog.Confirmed.setVisible(true);
//		        }
//		        else{
//		        	//System.out.println("New BioBrick is :"+str);
//		        	showinfo.setText("<html>" + "Successful uploading!" + "<br>" +  "New BioBrick is :" + str + "<html>");
//		        	showinfopanel.updateUI();
//		        	//waitingdialog.inputtext.setText("New BioBrick is :" + str);
//		        	//waitingdialog.Confirmed.setVisible(true);
//		        }

			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}


		/*public static void main(String[] args) {
				WriteTxt rt = new WriteTxt();
				Thread demo1 = new Thread(rt);
				demo1.start();*/


		// 读txt文件里的内容
		/*try {
		FileReader input = new FileReader("E://igem//Code//potential-octo-wookie//test.txt");
		BufferedReader br = new BufferedReader(input);
		String text = null;
		while ((text = br.readLine()) != null) {
		System.out.println(text);
		}
		br.close();
		input.close();
		} catch (IOException e) {
		e.printStackTrace();
		}*/
	}
}
	
