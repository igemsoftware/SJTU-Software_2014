package EasyBBK_Swing.gui;

import java.awt.Dimension;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

import data_center.*;

public class InitializeDetail extends Thread{
	public Child_Search child_search;
	public BbkOutline bbkoutline;
	
	public InitializeDetail(Child_Search child_search1, BbkOutline bbkoutline1){
		child_search = child_search1;
		bbkoutline = bbkoutline1;
	}
	
	public void run(){
		final BbkDetail bbkdetail = child_search.searchcenter.getDetail(bbkoutline.name);
		DetailsofResults detailsofresults = new DetailsofResults();
		
		boolean addflag = false;
		for(int i = 0; i < child_search.comparisonlist.size(); i++){
			if(child_search.comparisonlist.get(i).name.equals(bbkdetail.name))
				addflag = true;
		}
		
		if(addflag == true)
			detailsofresults.Addforcomparison.setSelected(true);
		
		ItemListener itemListener_addforcomparison = new ItemListener() {
            JCheckBox jCheckBox;
 
            public void itemStateChanged(ItemEvent e) {
                jCheckBox = (JCheckBox) e.getSource();
 
                if(jCheckBox.isSelected()) {
                	if(child_search.comparisonlist.size() >= 3){
                		//jCheckBox.setSelected(false);
                		JOptionPane.showMessageDialog(null, "The comparison list is full!", "Attention",JOptionPane.PLAIN_MESSAGE);
                		return;
                	}
                	child_search.comparisonlist.add(bbkdetail);
                	//JOptionPane.showMessageDialog(null, "Successfully add for comparison", null, JOptionPane.PLAIN_MESSAGE);
                }else {
                	int j = 10;
                	for(int i = 0; i < child_search.comparisonlist.size(); i++){
            			if(child_search.comparisonlist.get(i).name.equals(bbkdetail.name))
            				j = i;
            		}
                	if(j != 10)
                		child_search.comparisonlist.remove(j);
                	//JOptionPane.showMessageDialog(null, "Successfully remove from comparison", null, JOptionPane.PLAIN_MESSAGE);
                }
            }
        };
		detailsofresults.Addforcomparison.addItemListener(itemListener_addforcomparison);
		
		detailsofresults.ID_Content.setText(bbkdetail.name);
		detailsofresults.Type_Content.setText(bbkdetail.type);
		detailsofresults.Author_Content.setText(bbkdetail.author);
		detailsofresults.EnteredDate_Content.setText(bbkdetail.enterDate);
		detailsofresults.URL_Content.setText("<html><u>" + bbkdetail.url + "</u></html>");
		detailsofresults.URL_Content.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getButton() == MouseEvent.BUTTON1){
					try{
						Runtime.getRuntime().exec("explorer " + bbkdetail.url);
					}
					catch(Exception ex){
						ex.printStackTrace();
					}
				}
			}
		});
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
		
		String score = "" + bbkdetail.getScore();
		char c = '.';
		if(score.charAt(1) == c){
			score = score.substring(0, 4);
		}
		else if(score.charAt(2) == c){
			score = score.substring(0, 5);
		}
		detailsofresults.Score.setText(score);
		
		detailsofresults.setPreferredSize(new Dimension(665, 1500));
		child_search.scrollPane1 = new JScrollPane(detailsofresults);
		child_search.scrollbar1 = new JScrollBar();
		child_search.scrollbar1.setUnitIncrement(100);
		child_search.scrollPane1.setVerticalScrollBar(child_search.scrollbar1);
		child_search.scrollPane1.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		child_search.scrollPane1.setBounds(0, 0, 683, 670);
		child_search.scrollPane1.validate();
		
		child_search.Details.removeAll();;
		child_search.Details.add(child_search.scrollPane1);
		child_search.Details.updateUI();
	}
}
