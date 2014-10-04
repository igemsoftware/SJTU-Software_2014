package EasyBBK_Swing.gui;

import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

import data_center.*;

public class InitializeDetailPage extends Thread{
	public String keyword;
	public DataCenter datacenter;
	public JPanel container;
	public JScrollPane scrollpanel;
	public DetailsofResults_Compare detailsofresults;
	public int num;
	
	public InitializeDetailPage(String keyword1, DataCenter datacenter1, JPanel container1, JScrollPane scrollpanel1, DetailsofResults_Compare detailsofresults1, int num1){
		keyword = keyword1;
		datacenter = datacenter1;
		container = container1;
		scrollpanel = scrollpanel1;
		detailsofresults = detailsofresults1;
		num = num1;
	}
	
	public void showresult(JPanel container, JScrollPane scrollpanel, DetailsofResults_Compare detailsofresults, BbkDetail bbkdetail){
		detailsofresults = new DetailsofResults_Compare();
		detailsofresults.ID_Content.setText(bbkdetail.name);
		detailsofresults.Type_Content.setText(bbkdetail.type);
		detailsofresults.Author_Content.setText(bbkdetail.author);
		detailsofresults.EnteredDate_Content.setText(bbkdetail.enterDate);
		detailsofresults.URL_Content.setText(bbkdetail.url);
		detailsofresults.ReleasedStatus_Content.setText(bbkdetail.releaseStatus);
		if(bbkdetail.rating.average_stars.equals("No Stars")){
			detailsofresults.AverageStar_Content.setText(bbkdetail.rating.average_stars);
		}
		else if(bbkdetail.rating.average_stars.length() == 1){
			detailsofresults.AverageStar_Content.setText(bbkdetail.rating.average_stars);
		}
		else if(bbkdetail.rating.average_stars.length() >= 3){
			detailsofresults.AverageStar_Content.setText(bbkdetail.rating.average_stars.substring(0,3));
		}
		detailsofresults.ResultsInGoogle_Content.setText(bbkdetail.rating.google_items);
		
		String shortdescription = bbkdetail.shortDesc;
		if(shortdescription.length()<=40){
			detailsofresults.Description1.setText(shortdescription);
			detailsofresults.Description2.setText(null);
		}
		else{
			detailsofresults.Description1.setText(shortdescription.substring(0, 40));
			detailsofresults.Description2.setText(shortdescription.substring(40));
		}
		
		detailsofresults.ResultsInGoogle_Content.setText(bbkdetail.rating.google_items);
		
		String score = "" + bbkdetail.getScore();
		detailsofresults.Score.setText(score);
		
		scrollpanel = new JScrollPane(detailsofresults);
		JScrollBar scrollbar = new JScrollBar();
		scrollbar.setUnitIncrement(100);
		scrollpanel.setVerticalScrollBar(scrollbar);
		scrollpanel.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollpanel.setBounds(0, 0, 455, 591);
		scrollpanel.validate();
		
		container.removeAll();;
		container.add(scrollpanel);
		container.updateUI();
	}
	
	public void run(){
		JLabel searching = new JLabel("Searching...");
		searching.setFont(new Font("Times New Roman", Font.BOLD, 30));
		searching.setBounds(20, 0, 300, 50);
		container.removeAll();
		container.add(searching);
		container.updateUI();
		
		BbkDetail bbkdetail = datacenter.compareCenter.assignDetail(keyword, num);
		
		if(bbkdetail == null){
			JLabel noresults = new JLabel("Sorry, no results found.");
			noresults.setFont(new Font("Times New Roman", Font.BOLD, 30));
			noresults.setBounds(20, 0, 300, 50);
			container.removeAll();
			container.add(noresults);
		}
		else{
			showresult(container, scrollpanel, detailsofresults, bbkdetail);
		}
	}
}
