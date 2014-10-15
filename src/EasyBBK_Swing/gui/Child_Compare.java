package EasyBBK_Swing.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;

import data_center.*;

public class Child_Compare extends JPanel {
	private static final long serialVersionUID = 1L;
	
	public MainPage mainpage;
	public ArrayList<BbkDetail> comparisonlist = new ArrayList<BbkDetail>();
	public JPanel Containerpanel;
	public JLabel BackGround;
	public Child_Compare child_compare;
	/**
	 * Create the panel.
	 * @wbp.parser.constructor
	 */
	public Child_Compare(MainPage mainpage1) {
		mainpage = mainpage1;
		child_compare = this;
		comparisonlist = mainpage.child_search_current.comparisonlist;
		initialize();
	}
	
	public Child_Compare(MainPage mainpage1, ArrayList<BbkDetail> comparisonlist1) {
		mainpage = mainpage1;
		child_compare = this;
		comparisonlist = comparisonlist1;
		initialize();
	}
	
	public void initialize(){
		setBounds(0, 0, 1366, 670);
		setBackground(new Color(255, 255, 255));
		setLayout(null);
		
		Containerpanel = new JPanel();
		Containerpanel.setBounds(0, 1000, 1348, 1000);
		Containerpanel.setPreferredSize(new Dimension(1348, 1000));
		Containerpanel.setBackground(new Color(255, 255, 255));
		Containerpanel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane(Containerpanel);
		JScrollBar scrollbar = new JScrollBar();
		scrollbar.setUnitIncrement(50);
		scrollPane.setVerticalScrollBar(scrollbar);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(0, 0, 1366, 670);
		scrollPane.validate();
		add(scrollPane);
		
		JTextField comparison = new JTextField("The Comparison for Biobricks");
		comparison.setOpaque(false);
		comparison.setFont(new Font("Arial", Font.BOLD, 40));
		comparison.setBackground(new Color(255, 255, 255));
		comparison.setBounds(383, 50, 600, 50);
		comparison.setEditable(false);
		comparison.setBorder(new EmptyBorder(0,0,0,0));
		Containerpanel.add(comparison);
		comparison.setColumns(10);
		
		initialpage();
		
		BackGround = new JLabel("");
		BackGround.setVisible(true);
		BackGround.setBounds(0, 0, 1366, 1000);
		BackGround.setIcon(new ImageIcon(MainPage.class.getResource("/EasyBBK_Swing/image/BackGround1.png")));
		Containerpanel.add(BackGround);
		
		setVisible(true);
	}
	
	public void initialpage(){
		if(comparisonlist == null || comparisonlist.size() == 0){
			JTextField attention = new JTextField("Parts being compared should be added from Search");
			attention.setOpaque(false);
			attention.setForeground(Color.red);
			attention.setFont(new Font("Arial", Font.BOLD, 30));
			attention.setBackground(new Color(255, 255, 255));
			attention.setBounds(283, 150, 800, 50);
			attention.setEditable(false);
			attention.setBorder(new EmptyBorder(0,0,0,0));
			Containerpanel.add(attention);
			attention.setColumns(10);
		}
		
		else if(comparisonlist.size() != 0){
			Comparison_info comparison_info = new Comparison_info();
			comparison_info.setBounds(123, 210, 220, 690);
			Containerpanel.add(comparison_info);
			
			int num = comparisonlist.size();
			
			for(int i = 0; i < num; i++){
				Comparison_item comparison_item = new Comparison_item();
				comparison_item.setBounds(343 + i*310, 150, 300, 750);
				final BbkDetail bbkdetail = comparisonlist.get(i);
				comparison_item.PartName.setText(bbkdetail.name);
				comparison_item.Type.setText(bbkdetail.type);
				if(bbkdetail.shortDesc.length() <= 30){
					comparison_item.ShortDescription.setText(bbkdetail.shortDesc);
				}
				else if(bbkdetail.shortDesc.length() > 30){
					comparison_item.ShortDescription.setText("<html>" + bbkdetail.shortDesc.substring(0, 30) + "<br>" + bbkdetail.shortDesc.substring(30) + "</html>");
				}
				comparison_item.PartStatus.setText(bbkdetail.releaseStatus);
				comparison_item.SampleStatus.setText(bbkdetail.sampleStatus);
				comparison_item.Url.setText(bbkdetail.url);
				comparison_item.DNAStatus.setText(bbkdetail.DNA_status);
				comparison_item.DeleteThisPart.setText(bbkdetail.rating.delete_this_part);
				comparison_item.ConfirmedTimes.setText(bbkdetail.rating.tot_confirmed);
				comparison_item.LengthofDocumentation.setText(bbkdetail.rating.documentation);
				comparison_item.PartResults.setText(bbkdetail.results);
				comparison_item.GroupFavorite.setText(bbkdetail.groupFavorite);
				comparison_item.UsedTimes.setText(bbkdetail.rating.used_times);
				if(bbkdetail.rating.average_stars.equals("No Stars")){
					comparison_item.AverageRating.setText(bbkdetail.rating.average_stars);
				}
				else if(bbkdetail.rating.average_stars.length() == 1){
					comparison_item.AverageRating.setText(bbkdetail.rating.average_stars);
				}
				else if(bbkdetail.rating.average_stars.length() >= 3){
					comparison_item.AverageRating.setText(bbkdetail.rating.average_stars.substring(0,3));
				}
				comparison_item.NumberofComments.setText(bbkdetail.rating.tot_commets);
				comparison_item.GoogleItems.setText(bbkdetail.rating.google_items);
				comparison_item.Remove.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						if(e.getButton() == MouseEvent.BUTTON1){
							for(int i = 0; i < comparisonlist.size(); i++){
								if(comparisonlist.get(i).name.equals(bbkdetail.name)){
									comparisonlist.remove(i);
									break;
								}
									
							}
							Containerpanel.removeAll();
							
							JTextField comparison = new JTextField("The Comparison for Biobricks");
							comparison.setOpaque(false);
							comparison.setFont(new Font("Arial", Font.BOLD, 40));
							comparison.setBackground(new Color(255, 255, 255));
							comparison.setBounds(383, 50, 600, 50);
							comparison.setEditable(false);
							comparison.setBorder(new EmptyBorder(0,0,0,0));
							Containerpanel.add(comparison);
							comparison.setColumns(10);
							
							initialpage();
							
							BackGround = new JLabel("");
							BackGround.setVisible(true);
							BackGround.setBounds(0, 0, 1366, 1000);
							BackGround.setIcon(new ImageIcon(MainPage.class.getResource("/EasyBBK_Swing/image/BackGround1.png")));
							Containerpanel.add(BackGround);
							
							Containerpanel.updateUI();
						}
					}
				});
				
				Containerpanel.add(comparison_item);
			}
		}
	}
}
