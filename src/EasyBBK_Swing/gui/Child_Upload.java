package EasyBBK_Swing.gui;

import java.awt.Color;

import sun.misc.*;

import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.DefaultCellEditor;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JToolTip;
import javax.swing.ScrollPaneConstants;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

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
	public JButton Clearall;
	
	public JTable paramTable = null;
	public String [] paramCols = {"Parameter Name","Value"};
	public String [][] paramRows = null;
	public JScrollPane paramJsp;
	
	public JTable categoryTable = null;
	public String [] categoryCols = {"Category"};
	public String [][] categoryRows = null;
	
	public JTable featureTable = null;
	public String [] featureCols = {"Type","label","Start","End","Dir"};
	public String [][] featureRows = null;
	
	/**
	 * Create the panel.
	 */
	public Child_Upload(MainPage mainpage1) {
		mainpage = mainpage1;
		bbkupload = new BbkUpload();
		
		initialize();
	}
	
	public Child_Upload(MainPage mainpage1, BbkUpload bbkupload1){
		mainpage = mainpage1;
		bbkupload = bbkupload1;
		
		initialize();
		
		information_shown = bbkupload.getSequence();
		featurenumber = bbkupload.features.size();
		
		
		for  (int i = 0; i < bbkupload.features.size(); i++)
		{
			String []addnew = {bbkupload.features.get(i).type,bbkupload.features.get(i).title,bbkupload.features.get(i).startPos,
					bbkupload.features.get(i).endPos,bbkupload.features.get(i).direction};
			((DefaultTableModel) featureTable.getModel()).addRow(addnew);
		}
//Feature fearture = new Feature(ID, title, type, direction, startPos, endPos)				
//		for(int i = 0; i < featurenumber; i++){
//			feature_item[i] = new Feature_item();
//			int j;
//			for(j = 0; j < feature_item[i].feature.length; j++){
//				if(bbkupload.features.get(i).type.equals(feature_item[i].feature[j]))
//					break;
//			}
//			feature_item[i].Choice.setSelectedIndex(j);
//			
//			for(j = 0; j < feature_item[i].direction.length; j++){
//				if(bbkupload.features.get(i).direction.equals(feature_item[i].direction[j]))
//					break;
//			}
//			feature_item[i].Direction.setSelectedIndex(j);
//			
//			feature_item[i].Start.setText(bbkupload.features.get(i).startPos);
//			feature_item[i].End.setText(bbkupload.features.get(i).endPos);
//			feature_item[i].Label.setText(bbkupload.features.get(i).title);
//			
//			feature_item[i].setLocation(0, featurenumber*31);
//			featurepanel.add(feature_item[i]);
//		}
		
		
	}
	
	public void initialize(){
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getButton() == e.BUTTON1)
					requestFocus();
			}
		});
		
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
		UploadContainer.setBounds(0, 0, 1348, 2000);
		UploadContainer.setPreferredSize(new Dimension(1348, 2000));
		UploadContainer.setBackground(new Color(255, 255, 255));
		UploadContainer.setLayout(null);
		
		scrollpanel = new JScrollPane(UploadContainer);
		
		JLabel Title = new JLabel("Welcome to the Upload Page!", JLabel.CENTER);
		Title.setFont(new Font("Arial", Font.BOLD, 40));
		Title.setBounds(383, 10, 600, 50);
		UploadContainer.add(Title);
		
		JLabel Description = new JLabel("A short description of this biobrick:");
		Description.setFont(new Font("Arial", Font.PLAIN, 24));
		Description.setBounds(373, 85, 394, 30);
		UploadContainer.add(Description);
		
		ShortDescription = new JTextField();
		ShortDescription.setFont(new Font("Arial", Font.PLAIN, 24));
		ShortDescription.setColumns(10);
		ShortDescription.setBounds(373, 125, 536, 30);
		UploadContainer.add(ShortDescription);
		
		JLabel ChooseType = new JLabel("Type:");
		ChooseType.setFont(new Font("Arial", Font.PLAIN, 24));
		ChooseType.setBounds(373, 165, 67, 30);
		UploadContainer.add(ChooseType);
		
		String type[] = {"Promoter", "RBS", "Protein_Domain", "Protein_Coding_Sequence", "Translation_Unit", "Terminator", "DNA", "Plasmid_Backbone", "Plasmid", "Primer", "Composite", "Protein_Generator", "Reporter", "Inventer", "Signalling", "Measurement", "Other"};
		Type = new JComboBox(type);
		typestring = "Promoter";
		Type.setFont(new Font("Arial", Font.PLAIN, 20));
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
		ChooseNickname.setFont(new Font("Arial", Font.PLAIN, 24));
		ChooseNickname.setBounds(373, 205, 113, 30);
		UploadContainer.add(ChooseNickname);
		
		Nickname = new JTextField();
		Nickname.setFont(new Font("Arial", Font.PLAIN, 24));
		Nickname.setColumns(10);
		Nickname.setBounds(496, 205, 205, 30);
		UploadContainer.add(Nickname);
		
		JLabel ChooseDesigners = new JLabel("Designer(s):");
		ChooseDesigners.setFont(new Font("Arial", Font.PLAIN, 24));
		ChooseDesigners.setBounds(373, 245, 139, 30);
		UploadContainer.add(ChooseDesigners);
		
		Designers = new JTextField();
		Designers.setFont(new Font("Arial", Font.PLAIN, 24));
		Designers.setColumns(10);
		Designers.setBounds(522, 245, 205, 30);
		UploadContainer.add(Designers);
		
		JLabel Sequence = new JLabel("Sequence Information:");
		Sequence.setFont(new Font("Arial", Font.PLAIN, 24));
		Sequence.setBounds(373, 285, 259, 30);
		UploadContainer.add(Sequence);
		
		SequenceInformation = new JTextArea(4, 15);
		SequenceInformation.setEditable(false);
		SequenceInformation.setFont(new Font("Arial", Font.PLAIN, 20));
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
		AddSequence.setFont(new Font("Arial", Font.PLAIN, 20));
		AddSequence.setBounds(373, 465, 166, 30);
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
		AddSubpart.setFont(new Font("Arial", Font.PLAIN, 20));
		AddSubpart.setBounds(549, 465, 146, 30);
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
		AddSubscar.setFont(new Font("Arial", Font.PLAIN, 20));
		AddSubscar.setBounds(705, 465, 156, 30);
		UploadContainer.add(AddSubscar);
		
		UseDefaultScar = new JCheckBox("Use default scar");
		UseDefaultScar.setBackground(new Color(0, 255, 255));
		UseDefaultScar.setFont(new Font("Arial", Font.PLAIN, 20));
		UseDefaultScar.setBounds(373, 513, 169, 30);
		UploadContainer.add(UseDefaultScar);
			
		
		
		
		
		//Parameters
		JLabel Parameter = new JLabel("Parameters:");
		Parameter.setFont(new Font("Arial", Font.PLAIN, 24));
		Parameter.setBounds(373, 553, 200, 30);		
		UploadContainer.add(Parameter);
		
		AddParameter = new JButton("Add");
		AddParameter.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				updateTable();
			}
		});
		AddParameter.setFont(new Font("Arial", Font.PLAIN, 20));
		AddParameter.setBounds(618, 553, 75, 30);
		UploadContainer.add(AddParameter);
		
		RemoveParameter = new JButton("Remove");
		RemoveParameter.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				deleteBtn_Clicked();
			}
		});
		RemoveParameter.setFont(new Font("Arial", Font.PLAIN, 20));
		RemoveParameter.setBounds(713, 553, 120, 30);
		UploadContainer.add(RemoveParameter);
		
		String parameter[] = {"--Add Another Parameter--","abs", "activation_coeficient", "biology", 
				"chassis", "color", "control", "copies", "device_type", 
				"direction", "dissociation_constant", "ec_num", "efficiency", 
				"emission", "emit", "excitation", "excite", "family", 
				"forward_efficiency", "function", "genotype", "hill_coeficient", 
				"input_s", "insert", "i_h", "i_l", "kegg", "latency", "len_end", 
				"len_fr", "len_start", "ligands", "lum", "mcs", "n/a", 
				"negative_regulators", "origin", "output", "output_s", "o_h", 
				"o_l", "pct_gc", "positive_regulators", "protein", "proteins", 
				"protein_in", "protein_out", "rbs", "receiver", "resistance", 
				"reversed_version", "reverse_efficiency", "sender", 
				"signalling_molecule", "strain", "swisspro", "switch_point", 
				"t-oh", "t-ol", "tag", "target", "type", "t_hl", "t_lh", "t_m", "uniprot"};
		JComboBox paramComboBox = new JComboBox(parameter);
		paramComboBox.setFont(new Font("Arial", Font.PLAIN, 20));
		paramComboBox.setSelectedIndex(0);
		paramTable = new JTable(paramRows,paramCols);
		paramTable.setFont(new Font("Arial", Font.PLAIN, 20));
		paramTable.setModel(new DefaultTableModel(paramRows,paramCols));
		JTableHeader header=paramTable.getTableHeader();
		header.setPreferredSize(new Dimension(header.getWidth(),0));
		//header.setFont(new Font("Arial", Font.PLAIN, 20));
		header.setVisible(false);
		//table.setPreferredSize(new Dimension(header.getWidth(),30));
		paramTable.setRowHeight(30);
		paramTable.setAutoCreateRowSorter(true);
		TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(paramTable.getModel());  
		paramTable.setRowSorter(sorter);  
		paramTable.getColumnModel().getColumn(0).setPreferredWidth(200);
		paramTable.getColumnModel().getColumn(0).setCellEditor(new DefaultCellEditor(paramComboBox));
		//paramTable.setBackground(new Color(155, 255, 255));
		JScrollPane paramJsp=new JScrollPane(paramTable);
		paramJsp.setBounds(373, 593, 486, 182);
		paramJsp.setPreferredSize(new Dimension(468, 500));
		paramJsp.setBackground(new Color(155, 255, 255));
		UploadContainer.add(paramJsp);
				
		
		
		
		//Category
		JLabel Category = new JLabel("Categories:");
		Category.setFont(new Font("Arial", Font.PLAIN, 24));
		Category.setBounds(373, 780, 150, 30);
		UploadContainer.add(Category);
		
//		categorypanel = new JPanel();
//		categorypanel.setBounds(373, 780, 486, 124);
//		categorypanel.setBackground(new Color(155, 255, 255));
//		categorypanel.setLayout(null);
//		UploadContainer.add(categorypanel);
		
		AddCategory = new JButton("Add");
		AddCategory.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getButton() == e.BUTTON1){
					categoryAddClicked();
				}
			}
		});
		AddCategory.setFont(new Font("Arial", Font.PLAIN, 20));
		AddCategory.setBounds(618, 780, 75, 30);
		UploadContainer.add(AddCategory);
		
		RemoveCategory = new JButton("Remove");
		RemoveCategory.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getButton() == e.BUTTON1){
					categoryDeleteClicked();
				}
			}
		});
		RemoveCategory.setFont(new Font("Arial", Font.PLAIN, 20));
		RemoveCategory.setBounds(713, 780, 120, 30);
		UploadContainer.add(RemoveCategory);
		
		String category[] = {"--Add Another Category--","//biosafety", "//biosafety/kill_switch", "//biosafety/semantic_containment",
				"//biosafety/xnase", "//cds", "//cds/biosynthesis", "//cds/chromatinremodeling", 
				"//cds/enzyme", "//cds/enzyme/chromatinremodeling", "//cds/enzyme/dnapolymerase", 
				"//cds/enzyme/dnase", "//cds/enzyme/endonuclease", "//cds/enzyme/endonuclease/restriction", 
				"//cds/enzyme/exonuclease", "//cds/enzyme/ligase", "//cds/enzyme/lysis", 
				"//cds/enzyme/methylation", "//cds/enzyme/phosphorylation", "//cds/enzyme/protease", 
				"//cds/enzyme/rnap", "//cds/enzyme/rnapolymerase", "//cds/ligand", "//cds/lysis", 
				"//cds/membrane", "//cds/membrane/channel", "//cds/membrane/extracellular", 
				"//cds/membrane/lysis", "//cds/membrane/pump", "//cds/membrane/receptor", 
				"//cds/membrane/transporter", "//cds/receptor", "//cds/receptor/antibody", 
				"//cds/reporter", "//cds/reporter/cfp", "//cds/reporter/chromoprotein", 
				"//cds/reporter/gfp", "//cds/reporter/rfp", "//cds/reporter/yfp", 
				"//cds/selectionmarker", "//cds/selectionmarker/antibioticresistance", 
				"//cds/transcriptionalregulator", "//cds/transcriptionalregulator/activator", 
				"//cds/transcriptionalregulator/repressor", "//chassis", "//chassis/aferrooxidans", 
				"//chassis/bacteriophage", "//chassis/bacteriophage/t3", "//chassis/bacteriophage/t4", 
				"//chassis/bacteriophage/t7", "//chassis/eukaryote", "//chassis/eukaryote/human", 
				"//chassis/eukaryote/pichia", "//chassis/eukaryote/yeast", "//chassis/miscellaneous", 
				"//chassis/multihost", "//chassis/organelle", "//chassis/organelle/chloroplast", 
				"//chassis/organelle/mitochondrion", "//chassis/prokaryote", 
				"//chassis/prokaryote/bcepacia", "//chassis/prokaryote/bsubtilis", 
				"//chassis/prokaryote/cyanobacterium", "//chassis/prokaryote/ecoli", 
				"//chassis/prokaryote/lactobacillus", "//chassis/prokaryote/mflorum", 
				"//chassis/prokaryote/pananatis", "//chassis/prokaryote/pmirabilis", 
				"//chassis/prokaryote/pputida", "//chassis/prokaryote/reuphora", 
				"//chassis/prokaryote/salmonella", "//chassis/prokaryote/sespanaensis", 
				"//chassis/prokaryote/subtilis", "//chassis/prokaryote/synechocystis", 
				"//chassis/prokaryote/vharveyi", "//classic/composite/uncategorized", 
				"//classic/device/uncategorized", "//classic/generator/plasmids", 
				"//classic/generator/prc", "//classic/generator/prct", "//classic/generator/rc", 
				"//classic/generator/rct", "//classic/generator/uncategorized", 
				"//classic/intermediate/uncategorized", "//classic/inverter/uncategorized",
				"//classic/measurement/o_h", "//classic/measurement/uncategorized", 
				"//classic/other/uncategorized", "//classic/plasmid/measurement", 
				"//classic/plasmid/uncategorized", "//classic/project/uncategorized", 
				"//classic/rbs/uncategorized", "//classic/regulatory/other", 
				"//classic/regulatory/uncategorized", "//classic/reporter", 
				"//classic/reporter/constitutive", "//classic/reporter/multiple", 
				"//classic/reporter/pret", "//classic/reporter/ret", "//classic/rna/uncategorized", 
				"//classic/signalling/receiver", "//classic/signalling/sender", 
				"//classic/temporary/uncategorized", "//collections", "//collections/biofab", 
				"//direction", "//direction/bidirectional", "//direction/forward", "//direction/reverse", 
				"//dna", "//dna/aptamer", "//dna/bioscaffold", "//dna/chromosomalintegration", 
				"//dna/cloningsite", "//dna/conjugation", "//dna/nucleotide", "//dna/origami", 
				"//dna/origin_of_replication", "//dna/primerbindingsite", "//dna/restrictionsite", 
				"//dna/scar", "//dna/spacer", "//dna/transposome/tn5", "//dna/transposon", 
				"//function/biofuels", "//function/biosynthesis", "//function/biosynthesis/ahl", 
				"//function/biosynthesis/butanol", "//function/biosynthesis/heme", 
				"//function/biosynthesis/isoprenoid", "//function/biosynthesis/odorant", 
				"//function/biosynthesis/phycocyanobilin", "//function/biosynthesis/plastic", 
				"//function/biosynthesis/pyocyanin", "//function/celldeath", "//function/cellsignalling", 
				"//function/coliroid", "//function/conjugation", "//function/crispr", 
				"//function/crispr/cas9", "//function/degradation", "//function/degradation/ahl", 
				"//function/degradation/bisphenol", "//function/degradation/cellulose", "//function/dna", 
				"//function/fret", "//function/immunology", "//function/mismatchrepair",
				"//function/motility", "//function/odor", "//function/recombination", 
				"//function/recombination/cre", "//function/recombination/fim", 
				"//function/recombination/flp", "//function/recombination/hin", 
				"//function/recombination/lambda", "//function/recombination/p22", 
				"//function/recombination/xer", "//function/regulation/transcriptional", 
				"//function/reporter", "//function/reporter/color", "//function/reporter/fluorescence", 
				"//function/reporter/light", "//function/reporter/pigment", "//function/sensor", 
				"//function/sensor/lead", "//function/sensor/light", "//function/structures", 
				"//function/tumorkillingbacteria", "//legal/ip-free", "//plasmid", 
				"//plasmid/chromosomalintegration", "//plasmid/component/cloningsite", 
				"//plasmid/component/insulation", "//plasmid/component/origin", "//plasmid/component/other", 
				"//plasmid/component/primerbindingsite", "//plasmid/construction", "//plasmid/expression", 
				"//plasmid/expression/t7", "//plasmid/measurement", "//plasmid/sp6", "//plasmidbackbone", 
				"//plasmidbackbone/archive", "//plasmidbackbone/assembly", 
				"//plasmidbackbone/component/defaultinsert", 
				"//plasmidbackbone/component/selectionmarker/antibioticresistance", 
				"//plasmidbackbone/copynumber", "//plasmidbackbone/copynumber/high", 
				"//plasmidbackbone/copynumber/inducible", "//plasmidbackbone/copynumber/low", 
				"//plasmidbackbone/copynumber/medium", "//plasmidbackbone/expression", 
				"//plasmidbackbone/expression/constitutive", "//plasmidbackbone/expression/inducible", 
				"//plasmidbackbone/libraryscreening", "//plasmidbackbone/libraryscreening/codingsequence", 
				"//plasmidbackbone/libraryscreening/promoter", "//plasmidbackbone/libraryscreening/rbscodingsequence", 
				"//plasmidbackbone/operation", "//plasmidbackbone/proteinfusion", "//plasmidbackbone/synthesis", "//plasmidbackbone/version/10", "//plasmidbackbone/version/3", "//plasmidbackbone/version/4", "//plasmidbackbone/version/5", "//primer/m13", "//primer/part", "//primer/part/amplification", "//primer/part/sequencing", "//primer/plasmid/amplification", "//primer/reporter/cfp", "//primer/reporter/gfp", "//primer/reporter/yfp", "//primer/sp6", "//primer/t3", "//primer/t7", "//promoter", "//promoter/anderson", "//promoter/iron", "//promoter/logic/ustc", "//promoter/stresskit", "//proteindomain", "//proteindomain/activation", "//proteindomain/affinity", "//proteindomain/binding", "//proteindomain/cleavage", "//proteindomain/degradation", "//proteindomain/dnabinding", "//proteindomain/head", "//proteindomain/internal", "//proteindomain/internal/special", "//proteindomain/linker", "//proteindomain/localization", "//proteindomain/repression", "//proteindomain/tail", "//proteindomain/transmembrane", "//rbs/prokaryote", "//rbs/prokaryote/constitutive", "//rbs/prokaryote/constitutive/anderson", "//rbs/prokaryote/constitutive/community", "//rbs/prokaryote/constitutive/constitutive", "//rbs/prokaryote/constitutive/miscellaneous", "//rbs/prokaryote/constitutive/rackham", "//rbs/prokaryote/regulated/issacs", "//rbs/prokaryotic/constitutive/miscellaneous", "//regulation", "//regulation/constitutive", "//regulation/multiple", "//regulation/negative", "//regulation/positive", "//regulation/unknown", "//ribosome", "//ribosome/eukaryote", "//ribosome/eukaryote/yeast", "//ribosome/prokaryote", "//ribosome/prokaryote/bcepacia", "//ribosome/prokaryote/bsubtilis", "//ribosome/prokaryote/custom", "//ribosome/prokaryote/ecoli", "//ribosome/prokaryote/pananatis", "//ribosome/prokaryote/pputida", "//ribosome/prokaryote/salmonella", "//ribosome/prokaryote/sespanaensis", "//rnap", "//rnap/bacteriophage/sp6", "//rnap/bacteriophage/t3", "//rnap/bacteriophage/t7", "//rnap/eukaryote", "//rnap/eukaryote/pichia", "//rnap/eukaryote/yeast", "//rnap/miscellaneous", "//rnap/prokaryote", "//rnap/prokaryote/aferrooxidans", "//rnap/prokaryote/ecoli", "//rnap/prokaryote/ecoli/sigma24", "//rnap/prokaryote/ecoli/sigma25", "//rnap/prokaryote/ecoli/sigma32", "//rnap/prokaryote/ecoli/sigma54", "//rnap/prokaryote/ecoli/sigma70", "//rnap/prokaryote/ecoli/sigmas", "//rnap/prokaryote/pmirabilis", "//rnap/prokaryote/pputida", "//rnap/prokaryote/reuphora", "//rnap/prokaryote/salmonella", "//rnap/prokaryote/subtilis/sigmaa", "//rnap/prokaryote/subtilis/sigmab", "//rnap/prokaryote/synechocystis", "//rnap/prokaryote/vharveyi/sigma54", "//t3", "//t3/t2", "//t3/t4", "//terminator", "//terminator/double", "//terminator/single", "//test", "//test/test2", "//test1", "//viral_vectors", "//viral_vectors/aav", "//viral_vectors/aav/capsid_coding", "//viral_vectors/aav/miscellaneous", "//viral_vectors/aav/vector_plasmid","extremophiles","receiver","regulator","transcriptional","unnamed_4455zz"};
		JComboBox categoryComboBox = new JComboBox(category);
		categoryComboBox.setFont(new Font("Arial", Font.PLAIN, 20));
		categoryComboBox.setSelectedIndex(0);
		categoryTable = new JTable(categoryRows,categoryCols);
		categoryTable.setFont(new Font("Arial", Font.PLAIN, 20));
		categoryTable.setModel(new DefaultTableModel(categoryRows,categoryCols));
		JTableHeader categoryHeader=categoryTable.getTableHeader();
		categoryHeader.setPreferredSize(new Dimension(categoryHeader.getWidth(),0));
		//header.setFont(new Font("Arial", Font.PLAIN, 20));
		categoryHeader.setVisible(false);
		//table.setPreferredSize(new Dimension(header.getWidth(),30));
		categoryTable.setRowHeight(30); 
		//categoryTable.getColumnModel().getColumn(0).setPreferredWidth(200);
		categoryTable.getColumnModel().getColumn(0).setCellEditor(new DefaultCellEditor(categoryComboBox));
		//paramTable.setBackground(new Color(155, 255, 255));
		JScrollPane categoryJsp=new JScrollPane(categoryTable);
		categoryJsp.setBounds(373, 815, 486, 182);
		categoryJsp.setPreferredSize(new Dimension(468, 500));
		categoryJsp.setBackground(new Color(155, 255, 255));
		UploadContainer.add(categoryJsp);
		
		
		
		
		//Features
		JLabel Features = new JLabel("Features:");
		Features.setFont(new Font("Arial", Font.PLAIN, 24));
		Features.setBounds(373, 1005, 113, 30);
		UploadContainer.add(Features);
		
		AddFeature = new JButton("Add");
		AddFeature.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getButton() == e.BUTTON1){
					featureAddClicked();
				}
			}
		});
		AddFeature.setFont(new Font("Arial", Font.PLAIN, 20));
		AddFeature.setBounds(618, 1005, 75, 30);
		UploadContainer.add(AddFeature);
		
		RemoveFeature = new JButton("Remove");
		RemoveFeature.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getButton() == e.BUTTON1){
					featureDeleteClicked();
				}
			}
		});
		RemoveFeature.setFont(new Font("Arial", Font.PLAIN, 20));
		RemoveFeature.setBounds(713, 1005, 120, 30);
		UploadContainer.add(RemoveFeature);
		
		String feature[] = {"--New--","misc", "promoter", "rbs", "cds", "start", "stop", "scar", "dna", "tag", 
				"stem_loop", "mutation", "s_mutation", "primer_binding", "operator", "binding", "protein", 
				"conserved", "polya", "barcode", "BioBrick"};
		String direction[] = {"Fwd", "Rev"};
		JComboBox featureComboBox = new JComboBox(feature);
		featureComboBox.setFont(new Font("Arial", Font.PLAIN, 20));
		featureComboBox.setSelectedIndex(0);
		JComboBox directionComboBox = new JComboBox(direction);
		directionComboBox.setFont(new Font("Arial", Font.PLAIN, 20));
		directionComboBox.setSelectedIndex(0);
		featureTable = new JTable(featureRows,featureCols);
		featureTable.setFont(new Font("Arial", Font.PLAIN, 20));
		featureTable.setModel(new DefaultTableModel(featureRows,featureCols));
		JTableHeader featureHeader=featureTable.getTableHeader();
		featureHeader.setPreferredSize(new Dimension(featureHeader.getWidth(),30));
		featureHeader.setFont(new Font("Arial", Font.PLAIN, 20));
		featureHeader.setVisible(true);
		featureTable.setRowHeight(30); 
		featureTable.getColumnModel().getColumn(0).setPreferredWidth(118);
		featureTable.getColumnModel().getColumn(1).setPreferredWidth(200);
		featureTable.getColumnModel().getColumn(2).setPreferredWidth(50);
		featureTable.getColumnModel().getColumn(3).setPreferredWidth(50);
		featureTable.getColumnModel().getColumn(4).setPreferredWidth(50);		
		featureTable.getColumnModel().getColumn(0).setCellEditor(new DefaultCellEditor(featureComboBox));
		featureTable.getColumnModel().getColumn(4).setCellEditor(new DefaultCellEditor(directionComboBox));
		//paramTable.setBackground(new Color(155, 255, 255));
		JScrollPane featureJsp=new JScrollPane(featureTable);
		featureJsp.setBounds(373, 1040, 486, 213);
		featureJsp.setPreferredSize(new Dimension(468, 500));
		featureJsp.setBackground(new Color(155, 255, 255));
		UploadContainer.add(featureJsp);
		
		
		
		JLabel LongDescription_text = new JLabel("Long Description:");
		LongDescription_text.setFont(new Font("Arial", Font.PLAIN, 24));
		LongDescription_text.setBounds(373, 1260, 200, 30);
		UploadContainer.add(LongDescription_text);
		
		LongDescription = new JTextArea(4, 15);
		LongDescription.setEditable(true);
		LongDescription.setFont(new Font("Arial", Font.PLAIN, 20));
		LongDescription.setLineWrap(true);
		LongDescription.setWrapStyleWord(true);
		LongDescription.setBackground(new Color(255, 255, 255));
		
		LDscrollpanel = new JScrollPane(LongDescription);
		LDscrollpanel.setBounds(373, 1300, 486, 117);
		JScrollBar scrollbar4 = new JScrollBar();
		scrollbar4.setUnitIncrement(20);
		LDscrollpanel.setVerticalScrollBar(scrollbar4);
		LDscrollpanel.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		LDscrollpanel.validate();
		UploadContainer.add(LDscrollpanel);
		
		JLabel Claim = new JLabel("Contents below only can be uploaded to offical website!");
		Claim.setForeground(Color.red);
		Claim.setFont(new Font("Arial", Font.PLAIN, 24));
		Claim.setBounds(373, 1425, 600, 30);
		UploadContainer.add(Claim);
		
		JLabel Sourcetext = new JLabel("The source of this part:");
		Sourcetext.setFont(new Font("Arial", Font.PLAIN, 24));
		Sourcetext.setBounds(373, 1460, 300, 30);
		UploadContainer.add(Sourcetext);
		
		Source = new JTextArea(4, 15);
		Source.setEditable(true);
		Source.setFont(new Font("Arial", Font.PLAIN, 20));
		Source.setLineWrap(true);
		Source.setWrapStyleWord(true);
		Source.setBackground(new Color(255, 255, 255));
		
		Sourcescrollpanel = new JScrollPane(Source);
		Sourcescrollpanel.setBounds(373, 1500, 486, 117);
		JScrollBar scrollbar5 = new JScrollBar();
		scrollbar5.setUnitIncrement(20);
		Sourcescrollpanel.setVerticalScrollBar(scrollbar5);
		Sourcescrollpanel.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		Sourcescrollpanel.validate();
		UploadContainer.add(Sourcescrollpanel);
		
		JLabel DesignConsiderationtext = new JLabel("Design Consideration:");
		DesignConsiderationtext.setFont(new Font("Arial", Font.PLAIN, 24));
		DesignConsiderationtext.setBounds(373, 1625, 240, 30);
		UploadContainer.add(DesignConsiderationtext);
		
		DesignConsideration = new JTextArea(4, 15);
		DesignConsideration.setEditable(true);
		DesignConsideration.setFont(new Font("Arial", Font.PLAIN, 20));
		DesignConsideration.setLineWrap(true);
		DesignConsideration.setWrapStyleWord(true);
		DesignConsideration.setBackground(new Color(255, 255, 255));
		
		DCscrollpanel = new JScrollPane(DesignConsideration);
		DCscrollpanel.setBounds(373, 1665, 486, 117);
		JScrollBar scrollbar6 = new JScrollBar();
		scrollbar6.setUnitIncrement(20);
		DCscrollpanel.setVerticalScrollBar(scrollbar6);
		DCscrollpanel.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		DCscrollpanel.validate();
		UploadContainer.add(DCscrollpanel);
		
		JLabel username = new JLabel("User Name:");
		username.setFont(new Font("Arial", Font.PLAIN, 20));
		username.setBounds(373, 1790, 120, 24);
		UploadContainer.add(username);
		
		JLabel password = new JLabel("Password:");
		password.setFont(new Font("Arial", Font.PLAIN, 20));
		password.setBounds(373, 1830, 103, 24);
		UploadContainer.add(password);
		
		UserName = new JTextField();
		UserName.setFont(new Font("Arial", Font.PLAIN, 20));
		UserName.setBounds(500, 1790, 146, 24);
		UploadContainer.add(UserName);
		UserName.setColumns(10);
		
		Password = new JPasswordField();
		Password.setFont(new Font("Arial", Font.PLAIN, 20));
		Password.setColumns(10);
		Password.setBounds(500, 1830, 146, 24);
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
					
					for (int i = 0; i < paramTable.getRowCount();i++)
					{
						Parameter parameter = new Parameter(((DefaultTableModel) paramTable.getModel()).getValueAt(i, 0).toString(),
								((DefaultTableModel) paramTable.getModel()).getValueAt(i, 1).toString(), null, null, null, null);
						bbkupload.parameters.add(parameter);
					}
					
					for (int i = 0; i < categoryTable.getRowCount();i++)
					{
						Category category = new Category(((DefaultTableModel) categoryTable.getModel()).getValueAt(i, 0).toString());
						bbkupload.categories.add(category);
					}
					
					for (int i = 0; i < featureTable.getRowCount();i++)
					{
						Feature feature = new Feature("",((DefaultTableModel) featureTable.getModel()).getValueAt(i, 1).toString(),
								((DefaultTableModel) featureTable.getModel()).getValueAt(i, 0).toString(),
								((DefaultTableModel) featureTable.getModel()).getValueAt(i, 4).toString(),
								((DefaultTableModel) featureTable.getModel()).getValueAt(i, 2).toString(),
								((DefaultTableModel) featureTable.getModel()).getValueAt(i, 3).toString());
						bbkupload.features.add(feature);
					}
//					for(int i = 0; i < parameternumber; i++){
//						Parameter parameter = new Parameter(parameter_item[i].content, parameter_item[i].textField.getText(), null, null, null, null);
//						bbkupload.parameters.add(parameter);
//					}
//					
//					for(int i = 0; i < categorynumber; i++){
//						Category category = new Category(category_item[i].content);
//						bbkupload.categories.add(category);
//					}
//					
//					for(int i = 0; i < featurenumber; i++){
//						Feature feature = new Feature(null, feature_item[i].Label.getText(), feature_item[i].content1, feature_item[i].content2, feature_item[i].Start.getText(), feature_item[i].End.getText());
//						bbkupload.features.add(feature);
//					}
					bbkupload.longDesc = LongDescription.getText();
					//UploadCenter uploadcenter = new UploadCenter();
					//uploadcenter.getBbkUploadByNameAndOddNum(name, oddNum);
				}
			}
		});
		SubmitToDatabase.setFont(new Font("Arial", Font.PLAIN, 24));
		SubmitToDatabase.setBounds(403, 1870, 180, 55);
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
					
					for (int i = 0; i < paramTable.getRowCount();i++)
					{
						Parameter parameter = new Parameter(((DefaultTableModel) paramTable.getModel()).getValueAt(i, 0).toString(),
								((DefaultTableModel) paramTable.getModel()).getValueAt(i, 1).toString(), null, null, null, null);
						bbkupload.parameters.add(parameter);
					}
					
					for (int i = 0; i < categoryTable.getRowCount();i++)
					{
						Category category = new Category(((DefaultTableModel) categoryTable.getModel()).getValueAt(i, 0).toString());
						bbkupload.categories.add(category);
					}
					System.out.println(featureTable.getRowCount());
					for (int i = 0; i < featureTable.getRowCount();i++)
					{
						Feature feature = new Feature("",((DefaultTableModel) featureTable.getModel()).getValueAt(i, 1).toString(),
								((DefaultTableModel) featureTable.getModel()).getValueAt(i, 0).toString(),
								((DefaultTableModel) featureTable.getModel()).getValueAt(i, 4).toString(),
								((DefaultTableModel) featureTable.getModel()).getValueAt(i, 2).toString(),
								((DefaultTableModel) featureTable.getModel()).getValueAt(i, 3).toString());
						bbkupload.features.add(feature);
					}
					
//					for(int i = 0; i < parameternumber; i++){
//						Parameter parameter = new Parameter(parameter_item[i].content, parameter_item[i].textField.getText(), null, null, null, null);
//						bbkupload.parameters.add(parameter);
//					}
//					
//					for(int i = 0; i < categorynumber; i++){
//						Category category = new Category(category_item[i].content);
//						bbkupload.categories.add(category);
//					}
//					
//					for(int i = 0; i < featurenumber; i++){
//						Feature feature = new Feature(null, feature_item[i].Label.getText(), feature_item[i].content1, feature_item[i].content2, feature_item[i].Start.getText(), feature_item[i].End.getText());
//						bbkupload.features.add(feature);
//					}
					bbkupload.source = Source.getText();
					bbkupload.notes = DesignConsideration.getText();
					
					WriteTxt rt = new WriteTxt();
					Thread demo1 = new Thread(rt);
					demo1.start();
				}
			}
		});
		SubmitToWebsite.setFont(new Font("Arial", Font.PLAIN, 24));
		SubmitToWebsite.setBounds(649, 1870, 200, 55);
		UploadContainer.add(SubmitToWebsite);
		
		JLabel information = new JLabel("Information", SwingConstants.CENTER);
		information.setBounds(1040, 1450, 120, 30);
		information.setFont(new Font("Arial", Font.PLAIN, 24));
		UploadContainer.add(information);
		
		showinfopanel = new JPanel();
		showinfopanel.setBounds(920, 1500, 360, 240);
		showinfopanel.setOpaque(false);
		//showinfopanel.setBackground(new Color(255, 255, 255));
		UploadContainer.add(showinfopanel);
		
		showinfo = new JLabel("", SwingConstants.LEFT);
		showinfo.setOpaque(false);
		showinfo.setVisible(true);
		showinfo.setBounds(0, 0, 360, 240);
		showinfo.setFont(new Font("Arial", Font.PLAIN, 24));
		showinfopanel.add(showinfo);
		
		Clearall = new JButton("Clear All");
		Clearall.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				Clearall.requestFocus();
				ShortDescription.setText(null);
				Type.setSelectedIndex(0);
				Nickname.setText(null);
				Designers.setText(null);
				SequenceInformation.setText(null);
				UseDefaultScar.setSelected(false);
				for (int i = paramTable.getRowCount()-1;i>=0;i--)
		    		 ((DefaultTableModel) paramTable.getModel()).removeRow(i);
				for (int i = categoryTable.getRowCount()-1;i>=0;i--)
		    		 ((DefaultTableModel) categoryTable.getModel()).removeRow(i);
				for (int i = featureTable.getRowCount()-1;i>=0;i--)
		    		 ((DefaultTableModel) featureTable.getModel()).removeRow(i);
				//paramTable = null;
				//categoryTable = null;
				//featureTable = null;
				//parameterpanel.removeAll();
				//categorypanel.removeAll();
				//featurepanel.removeAll();
				LongDescription.setText(null);
				Source.setText(null);
				DesignConsideration.setText(null);
				showinfopanel.removeAll();
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				Clearall.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
		});
		Clearall.setFont(new Font("Arial", Font.PLAIN, 24));
		Clearall.setBounds(1100, 30, 125, 35);
		UploadContainer.add(Clearall);
		
		BackGround = new JLabel("");
		BackGround.setVisible(true);
		BackGround.setBounds(0, 0, 1366, 2000);
		BackGround.setIcon(new ImageIcon(MainPage.class.getResource("/EasyBBK_Swing/image/BackGround3.png")));
		UploadContainer.add(BackGround);
		
		JScrollBar scrollbar = new JScrollBar();
		scrollbar.setUnitIncrement(100);
		scrollpanel.setVerticalScrollBar(scrollbar);
		scrollpanel.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollpanel.setBounds(0, 0, 1366, 670);
		scrollpanel.validate();
		add(scrollpanel);
	}
	
	private void updateTable(){
		String[] addnew = {"--Add Another Parameter--",""};
		if (paramTable.getRowCount()>0)
			if (((DefaultTableModel) paramTable.getModel()).getValueAt(paramTable.getRowCount()-1, 0).equals(addnew[0]))
				return;		
		((DefaultTableModel) paramTable.getModel()).addRow(addnew);
    }
	
	private void deleteBtn_Clicked(){
    	int row=paramTable.getSelectedRow();
    	System.out.println(row);
    	if(row>-1){
    		 ((DefaultTableModel) paramTable.getModel()).removeRow(row);
    	}
    }
	
	private void categoryAddClicked(){
		String []addnew = {"--Add Another Category--"};
		if (categoryTable.getRowCount()>0)
			if (((DefaultTableModel) categoryTable.getModel()).getValueAt(categoryTable.getRowCount()-1, 0).equals(addnew[0]))
				return;		
		((DefaultTableModel) categoryTable.getModel()).addRow(addnew);
    }
	
	private void categoryDeleteClicked(){
    	int row=categoryTable.getSelectedRow();
    	if(row>-1){
    		 ((DefaultTableModel) categoryTable.getModel()).removeRow(row);
    	}
    }
	
	private void featureAddClicked(){
		String []addnew = {"--New--","","","","Fwd"};
		if (featureTable.getRowCount()>0)
			if (((DefaultTableModel) featureTable.getModel()).getValueAt(featureTable.getRowCount()-1, 0).equals(addnew[0]))
				return;
		((DefaultTableModel) featureTable.getModel()).addRow(addnew);
		//System.out.println(featureTable.getRowCount());
    }
	
	private void featureDeleteClicked(){
    	int row = featureTable.getSelectedRow();
    	if(row>-1){
    		 ((DefaultTableModel) featureTable.getModel()).removeRow(row);
    	}
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
					return;
				}
				showinfo.setText("<html>Uploading your biobrick...<br>10% Complete</html>");
				String newBbk = OfficialUploadPoster.getNextAvailablePartName();
				//newBbk = "BBa_K1479005";
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
	}
}				
				

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