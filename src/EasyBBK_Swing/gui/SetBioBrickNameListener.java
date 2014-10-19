package EasyBBK_Swing.gui;

import java.awt.Frame;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.MouseInputListener;

import data_center.BbkOutline;
import data_center.SketchCenter;
import data_center.SketchComponent;

/**
 * Show a dialog in which you can assign the chosen component as a specific biobrick which has 
 * been included in database.
 * @author LC
 *
 */
public class SetBioBrickNameListener implements MouseInputListener
{
	SketchCenter sketchCenter;
	int showID;
	
	SetBioBrickNameListener(SketchCenter sketchCenter)
	{
		super();
		this.sketchCenter = sketchCenter;
	}
	
	public void mouseClicked(MouseEvent e) 
	{
		if (e.getClickCount()==2 & e.getButton()==MouseEvent.BUTTON1)
		{
			if (((JLabelWithID)e.getComponent()).getName()=="promoter"||
					((JLabelWithID)e.getComponent()).getName()=="rbs"||
					((JLabelWithID)e.getComponent()).getName()=="coding"||
					((JLabelWithID)e.getComponent()).getName()=="primer"||
					((JLabelWithID)e.getComponent()).getName()=="terminator"||
					((JLabelWithID)e.getComponent()).getName()=="reporter")
			{
				SetBioBrickNameDialog one = new SetBioBrickNameDialog(((JLabelWithID)e.getComponent()).ID);
				showID=((JLabelWithID)e.getComponent()).ID;
				one.currentName=((JLabelWithID)(e.getComponent())).bioName;
				one.selectedName=((JLabelWithID)(e.getComponent())).bioName;
				one.showDialog((JFrame)((JLabelWithID)(e.getSource())).getRootPane().getParent(),500,200);
				String name = one.getSelectedName();
				((JLabelWithID)(e.getComponent())).bioName = name;
			}
		}
	}
	
	public void mousePressed(MouseEvent e) {}

	public void mouseDragged(MouseEvent e) {}
	
	public void mouseMoved(MouseEvent e) {}
		
	public void mouseReleased(MouseEvent e){}
		
	public void mouseEntered(MouseEvent e) {}

	public void mouseExited(MouseEvent e) {}
	
	public class SetBioBrickNameDialog extends JPanel 
	{  
	    private static final long serialVersionUID = 1L;

		{  
	        try {  
	            javax.swing.UIManager.setLookAndFeel(javax.swing.UIManager.getSystemLookAndFeelClassName());  
	        } catch (Exception e) {  
	            e.printStackTrace();  
	        }  
	    }  
    
	    public String selectedName=null;
	    public String currentName=null;
	    public boolean setDone=false;
	      
	    public SetBioBrickNameDialog(int ID)
	    {     
	        init(ID);    
	    }  
	  
	    //可供外部调用的方法  
	    public String getSelectedName() 
	    {  
	        return selectedName;  
	    }  
	  
	    private void setSelectedName(String selectedName) 
	    {  
	        this.selectedName = selectedName;  
	    }  
	    
	    private JDialog dialog; 
	    
	    JTextField nameInput =new JTextField();
	    JButton set = new JButton();
	    JButton ok = new JButton();
	    JButton cancel = new JButton();
	    SearchingResult searchingresult = new SearchingResult();
	    //
	    
	    
	    //
		private void init(final int ID) 
	    {
	    	String biobrickName;
	    	SketchComponent.Component component = sketchCenter.currentProject.findComponentByID(ID);
	    	if (component == null || !component.getClass().equals(SketchComponent.BioBrick.class))
	    		biobrickName = "";
	    	else if (component.toBioBrick().bbkOutline == null)
	    		biobrickName = "";
	    	else
	    		biobrickName = component.toBioBrick().bbkOutline.name;
	    	
	    	this.setLayout(null);
	    	
	    	nameInput.setBounds(50,10,400,30);
	    	nameInput.setText(biobrickName);
	    	nameInput.setEditable(true);
	    	this.add(nameInput);
	    	
	    	set.setBounds(490,10,60,30);
	    	set.setText("Set");
	    	this.add(set);
	    	
	    	ok.setBounds(430,252,60,30);
	    	ok.setText("OK");
	    	this.add(ok);
	    	
	    	cancel.setBounds(500,252,60,30);
	    	cancel.setText("Cancel");
	    	this.add(cancel);
	    	cancel.setMargin(new Insets(0,0,0,0));
	    	
	    	this.add(searchingresult);
	    	searchingresult.setBounds(15, 50, 569, 192);
	    	
	        /*用户确定*/  
	    	set.addActionListener(new ActionListener() {  
	            public void actionPerformed(ActionEvent e)
	            {  
	            	SketchComponent.Component component = sketchCenter.currentProject.findComponentByID(ID);
	            	if (component == null || !component.getClass().equals(SketchComponent.BioBrick.class))
	            		return;
	            	BbkOutline bbkoutline = 
	            			sketchCenter.assignBbkOutlineToBioBrick
	            			(nameInput.getText(), component.toBioBrick());
	            	
	            	if(bbkoutline != null)
	            	{
	            		currentName=nameInput.getText(); 
	            		setDone=true;
	            	}
	            	else
	            	{
	            		currentName=null;
	            		setDone=false;
	            	}
	            	
	            	
	            	if(bbkoutline != null){
		            	searchingresult.ID_Content.setText(bbkoutline.name);
		            	searchingresult.Type_Content.setText(bbkoutline.type);
		            	searchingresult.Author_Content.setText(bbkoutline.author);
		            	searchingresult.EnteredDate_Content.setText(bbkoutline.enterDate);
		            	searchingresult.URL_Content.setText(bbkoutline.url);
		            	searchingresult.ReleasedStatus_Content.setText(bbkoutline.releaseStatus);
		        		if(bbkoutline.rating.average_stars.equals("No Stars")){
		        			searchingresult.AverageStar_Content.setText(bbkoutline.rating.average_stars);
		        		}
		        		else if(bbkoutline.rating.average_stars.length() == 1){
		        			searchingresult.AverageStar_Content.setText(bbkoutline.rating.average_stars);
		        		}
		        		else if(bbkoutline.rating.average_stars.length() >= 3){
		        			searchingresult.AverageStar_Content.setText(bbkoutline.rating.average_stars.substring(0,3));
		        		}
		        		searchingresult.ResultsInGoogle_Content.setText(bbkoutline.rating.google_items);
		        		
		        		String shortdescription = bbkoutline.shortDesc;
		        		searchingresult.Description.setText(shortdescription);
		        		
		        		String score = "" + bbkoutline.getScore();
		        		char c = '.';
		        		if(score.charAt(1) == c){
		        			score = score.substring(0, 4);
		        		}
		        		else if(score.charAt(2) == c){
		        			score = score.substring(0, 5);
		        		}
		        		searchingresult.Score.setText(score);
		        		
		        		searchingresult.Evalue.setVisible(false);
                
		        		searchingresult.updateUI();
	            	}
	            	
	            }  
	        });  
	    	
	    	/*用户确定*/  
	        ok.addActionListener(new ActionListener() {  
	            public void actionPerformed(ActionEvent e) {  
	            	if (setDone)
	            	{
	            		setSelectedName(currentName); 
	            	}  
	            	else
	            	{
	            		setSelectedName(null);
	            	}
	                dialog.dispose();  
	                dialog = null;  
	            }  
	        });  
	  
	        /*用户取消*/  
	        cancel.addActionListener(new ActionListener() {  
	            public void actionPerformed(ActionEvent e) {  
	                dialog.dispose();  
	                dialog = null;  
	            }  
	        }); 
	    }  
	      
	    /*显示字体选择器对话框(x,y表示窗体的启动位置)*/  
	    public void showDialog(Frame parent,int x,int y) 
	    {  
	        String  title = "Set_Biobrick_Name";  
	        dialog = new JDialog(parent, title,true);  
	        dialog.add(this);  
	        dialog.setResizable(false);  
	        dialog.setSize(600, 327);  
	        setDone=false;
	        
	        if (selectedName !=null)
	        {
	        	nameInput.setText(selectedName);
	        	
	        	SketchComponent.Component component = sketchCenter.currentProject.findComponentByID(showID);
            	BbkOutline bbkoutline = 
            			sketchCenter.assignBbkOutlineToBioBrick
            			(selectedName, component.toBioBrick());
	        	
	        	searchingresult.ID_Content.setText(bbkoutline.name);
            	searchingresult.Type_Content.setText(bbkoutline.type);
            	searchingresult.Author_Content.setText(bbkoutline.author);
            	searchingresult.EnteredDate_Content.setText(bbkoutline.enterDate);
            	searchingresult.URL_Content.setText(bbkoutline.url);
            	searchingresult.ReleasedStatus_Content.setText(bbkoutline.releaseStatus);
        		if(bbkoutline.rating.average_stars.equals("No Stars")){
        			searchingresult.AverageStar_Content.setText(bbkoutline.rating.average_stars);
        		}
        		else if(bbkoutline.rating.average_stars.length() == 1){
        			searchingresult.AverageStar_Content.setText(bbkoutline.rating.average_stars);
        		}
        		else if(bbkoutline.rating.average_stars.length() >= 3){
        			searchingresult.AverageStar_Content.setText(bbkoutline.rating.average_stars.substring(0,3));
        		}
        		searchingresult.ResultsInGoogle_Content.setText(bbkoutline.rating.google_items);
        		
        		String shortdescription = bbkoutline.shortDesc;
        		searchingresult.Description.setText(shortdescription);
        		
        		String score = "" + bbkoutline.getScore();
        		char c = '.';
        		if(score.charAt(1) == c){
        			score = score.substring(0, 4);
        		}
        		else if(score.charAt(2) == c){
        			score = score.substring(0, 5);
        		}
        		searchingresult.Score.setText(score);
        		
        		searchingresult.Evalue.setVisible(false);
        
        		searchingresult.updateUI();
	        }
	        
	      //设置接界面的启动位置  
	        dialog.setLocation(x,y);  
	        dialog.addWindowListener(new WindowAdapter() {  
	  
	            /*窗体关闭时调用*/  
	            public void windowClosing(WindowEvent e) 
	            {  
	                dialog.removeAll();
	                dialog.dispose();  
	                dialog = null;  
	            }  
	        });  
	        dialog.setVisible(true);  
	    }
	}
	
	
}
