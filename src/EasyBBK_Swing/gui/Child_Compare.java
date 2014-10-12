package EasyBBK_Swing.gui;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
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
	public MainPage mainpage;
	public ArrayList<BbkDetail> comparisonlist = new ArrayList<BbkDetail>();
	public JPanel Containerpanel;
	public JLabel BackGround;
	/**
	 * Create the panel.
	 */
	public Child_Compare(MainPage mainpage1) {
		mainpage = mainpage1;
		comparisonlist = mainpage.child_search_current.comparisonlist;
		initialize();
	}
	
	public Child_Compare(MainPage mainpage1, ArrayList<BbkDetail> comparisonlist1) {
		mainpage = mainpage1;
		comparisonlist = comparisonlist1;
		initialize();
	}
	
	public void initialize(){
		setBounds(0, 0, 1366, 670);
		setBackground(new Color(255, 255, 255));
		setLayout(null);
		
		Containerpanel = new JPanel();
		Containerpanel.setBounds(0, 0, 1348, 1000);
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
		Comparison_info comparison_info = new Comparison_info();
		comparison_info.setBounds(123, 150, 220, 690);
		Containerpanel.add(comparison_info);
		
		if(comparisonlist == null) return;
		int num = comparisonlist.size();
		
		for(int i = 0; i < num; i++){
			Comparison_item comparison_item = new Comparison_item();
			comparison_item.setBounds(343 + i*300, 150, 300, 690);
			BbkDetail bbkdetail = comparisonlist.get(i);
			comparison_item.PartName.setText(bbkdetail.name);
			comparison_item.Type.setText(bbkdetail.type);
			comparison_item.ShortDescription.setText(bbkdetail.shortDesc);
			comparison_item.PartStatus.setText(bbkdetail.releaseStatus);
			comparison_item.SampleStatus.setText(bbkdetail.sampleStatus);
			comparison_item.Url.setText("<html><u>" + bbkdetail.url + "</u></html>");
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
			
			Containerpanel.add(comparison_item);
		}
	}
}
