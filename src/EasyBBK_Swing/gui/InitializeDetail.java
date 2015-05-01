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
	public boolean small;
	
	public InitializeDetail(Child_Search child_search1, BbkOutline bbkoutline1, boolean small1){
		child_search = child_search1;
		bbkoutline = bbkoutline1;
		small = small1;
	}
	
	public void run(){
		final BbkDetail bbkdetail = child_search.searchcenter.getDetail(bbkoutline.name);
		DetailsofResults detailsofresults = new DetailsofResults(small);
		
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
                		JOptionPane.showMessageDialog(null, "The comparison list is full!", "Attention",JOptionPane.PLAIN_MESSAGE);
                		return;
                	}
                	child_search.comparisonlist.add(bbkdetail);
                }else {
                	int j = 10;
                	for(int i = 0; i < child_search.comparisonlist.size(); i++){
            			if(child_search.comparisonlist.get(i).name.equals(bbkdetail.name))
            				j = i;
            		}
                	if(j != 10)
                		child_search.comparisonlist.remove(j);
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
		
		detailsofresults.Sequences_Content.setText(bbkdetail.sequence);
		
		String categories = "";
		for(int i = 0; i < bbkdetail.categories.size(); i++){
			if(i != bbkdetail.categories.size()-1)
				categories = categories + bbkdetail.categories.get(i).category + "\n";
			else if(i == bbkdetail.categories.size()-1)
				categories = categories + bbkdetail.categories.get(i).category;
		}
		detailsofresults.Categories_Content.setText(categories);
		
		String twins = "";
		for(int i = 0; i < bbkdetail.twins.size(); i++){
			if(i != bbkdetail.twins.size()-1)
				twins = twins + bbkdetail.twins.get(i).twin + "\n";
			else if (i == bbkdetail.twins.size()-1)
				twins = twins + bbkdetail.twins.get(i).twin;
		}
		detailsofresults.Twins_Content.setText(twins);
		
		detailsofresults.related_URL.setText("<html><u>" + bbkdetail.rating.google_query_link + "</u></html>");
		if(!detailsofresults.related_URL.getText().equals("")){
			detailsofresults.related_URL.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					if(e.getButton() == MouseEvent.BUTTON1){
						try{
							Runtime.getRuntime().exec("explorer " + bbkdetail.rating.google_query_link);
						}
						catch(Exception ex){
							ex.printStackTrace();
						}
					}
				}
			});
		}
		detailsofresults.ReleasedStatus_Content.setText(bbkdetail.releaseStatus);
		detailsofresults.SampleStatus_Content.setText(bbkdetail.sampleStatus);
		detailsofresults.DNAStatus_Content.setText(bbkdetail.DNA_status);
		detailsofresults.Deletethispart_Content.setText(bbkdetail.rating.delete_this_part);
		detailsofresults.ConfirmedTimes_Content.setText(bbkdetail.rating.tot_confirmed);
		//detailsofresults.LengthofDocumentation_Content.setText(bbkdetail.rating.documentation);
		detailsofresults.PartResults_Content.setText(bbkdetail.results);
		detailsofresults.GroupFavorite_Content.setText(bbkdetail.groupFavorite);
		detailsofresults.UsedTimes_Content.setText(bbkdetail.rating.used_times);
		
		if(bbkdetail.rating.average_stars.equals("No Stars")){
			detailsofresults.AverageStar_Content.setText(bbkdetail.rating.average_stars);
		}
		else if(bbkdetail.rating.average_stars.length() == 1){
			detailsofresults.AverageStar_Content.setText(bbkdetail.rating.average_stars);
		}
		else if(bbkdetail.rating.average_stars.length() >= 3){
			detailsofresults.AverageStar_Content.setText(bbkdetail.rating.average_stars.substring(0,3));
		}
		detailsofresults.NumberofComments_Content.setText(bbkdetail.rating.tot_commets);
		
		detailsofresults.ResultsInGoogle_Content.setText(bbkdetail.rating.google_items);
		String shortdescription = bbkdetail.shortDesc;
		detailsofresults.Description.setText(shortdescription);
		
		String score = "" + bbkdetail.getScore();
		char c = '.';
		if(score.charAt(1) == c){
			score = score.substring(0, 4);
		}
		else if(score.charAt(2) == c){
			score = score.substring(0, 5);
		}
		detailsofresults.Score.setText(score);
		
		if(small == false){
			detailsofresults.setPreferredSize(new Dimension(665, 1200));
		}
		else if(small == true){
			detailsofresults.setPreferredSize(new Dimension(622, 1200));
		}
		
		child_search.scrollPane1 = new JScrollPane(detailsofresults);
		if(small == false){
			child_search.scrollPane1.setBounds(0, 0, 683, 670);
		}
		else if(small == true){
			child_search.scrollPane1.setBounds(0, 0, 640, 670);
		}
		child_search.scrollbar1 = new JScrollBar();
		child_search.scrollbar1.setUnitIncrement(100);
		child_search.scrollPane1.setVerticalScrollBar(child_search.scrollbar1);
		child_search.scrollPane1.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		child_search.scrollPane1.validate();
		
		child_search.Details.removeAll();;
		child_search.Details.add(child_search.scrollPane1);
		child_search.Details.updateUI();
	}
}
