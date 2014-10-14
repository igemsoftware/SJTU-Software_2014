package EasyBBK_Swing.gui;

import java.awt.Component;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.MouseInputListener;

import data_center.BbkUpload;
import data_center.SketchCenter;
import data_center.SketchComponent;

public class IfUploadListener implements MouseInputListener
{
	SketchCenter sketchCenter;
	public MainPage mainpage;
	
	IfUploadListener(SketchCenter sketchCenter,MainPage mainpage)
	{
		super();
		this.sketchCenter = sketchCenter;
		this.mainpage = mainpage;
	}
	
	public void mouseClicked(MouseEvent e) 
	{
		if (e.getClickCount()==2 & e.getButton()==MouseEvent.BUTTON1)
		{
			if (((JLabelWithID)e.getComponent()).getName()=="backbone")
			{
				UploadDialog one = new UploadDialog(((BackBone)e.getComponent()).ID);
				one.showDialog((JFrame)((BackBone)(e.getSource())).getRootPane().getParent(),500,200);

			}
		}
	}
	
	public void mousePressed(MouseEvent e) {}

	public void mouseDragged(MouseEvent e) {}
	
	public void mouseMoved(MouseEvent e) {}
		
	public void mouseReleased(MouseEvent e){}
		
	public void mouseEntered(MouseEvent e) {}

	public void mouseExited(MouseEvent e) {}
	
	public class UploadDialog extends JPanel 
	{  
	    private static final long serialVersionUID = 1L;

		{  
	        try {  
	            javax.swing.UIManager.setLookAndFeel(javax.swing.UIManager.getSystemLookAndFeelClassName());  
	        } catch (Exception e) {  
	            e.printStackTrace();  
	        }  
	    }  

	    public UploadDialog(int ID)
	    {     
	        init(ID);    
	    }  
	  
	    //可供外部调用的方法  
	    private JDialog dialog; 
	    
	    JButton ok = new JButton();
	    JButton cancel = new JButton();
	    JLabel showField1 = new JLabel();
	    JLabel showField2 = new JLabel();
	    //	    
	    
	    
	    //
		private void init(final int ID) 
	    {
	    	//layout
	    	this.setLayout(null);
	    	
	    	showField1.setBounds(0,10,350,35);
	    	showField1.setBorder(null);
	    	showField1.setText("Upload this backbone as a biobrick and");
	    	showField1.setOpaque(false);
	    	showField1.setHorizontalAlignment(JTextField.CENTER);
	    	showField1.setFont(new Font("Arial",Font.PLAIN,18));
	    	this.add(showField1);
	    	
	    	showField2.setBounds(0,45,350,35);
	    	showField2.setBorder(null);
	    	showField2.setText("switch to upload interface?");
	    	showField2.setOpaque(false);
	    	showField2.setHorizontalAlignment(JTextField.CENTER);
	    	showField2.setFont(new Font("Arial",Font.PLAIN,18));
	    	this.add(showField2);
	    	
	    	ok.setBounds(200,80,60,30);
	    	ok.setText("OK");
	    	cancel.setFont(new Font("Arial",Font.PLAIN,15));
	    	this.add(ok);
	    	
	    	cancel.setBounds(270,80,60,30);
	    	cancel.setText("Cancel");
	    	cancel.setFont(new Font("Arial",Font.PLAIN,15));
	    	this.add(cancel);
	    	cancel.setMargin(new Insets(0,0,0,0));

	    	/*用户确定*/  
	        ok.addActionListener(new ActionListener() {  
	            public void actionPerformed(ActionEvent e) 
	            {  
	            	SketchComponent.BackBone backbone = 
	            			sketchCenter.currentProject.findBackBoneInBackBoneListByID(ID);
	            	if (backbone == null)
	            	{	System.out.println("The backbone can be found no where in backstage, please check~ @.@");
	            		return;	
	            	}
	            	
	            	@SuppressWarnings("unused")
					BbkUpload bbkUpload = sketchCenter.generateBbkUploadFromBackBone(backbone, false);
	            	
	            	//to upload interface
	            	mainpage.Upload_flag = true;
	            	mainpage.Search_flag = false;
	            	mainpage.Design_flag = false;
	            	mainpage.Compare_flag = false;
	            	mainpage.Upload.setIcon(new ImageIcon(MainPage.class.getResource("/EasyBBK_Swing/image/Upload_click1.png")));
	            	mainpage.Search.setIcon(new ImageIcon(MainPage.class.getResource("/EasyBBK_Swing/image/Search1.png")));
	            	mainpage.Design.setIcon(new ImageIcon(MainPage.class.getResource("/EasyBBK_Swing/image/Design1.png")));
	            	mainpage.Compare.setIcon(new ImageIcon(MainPage.class.getResource("/EasyBBK_Swing/image/Compare.png")));
					
	            	Component component = mainpage.Mainpanel.getComponent(0);
					if(component instanceof Child_Design){
						mainpage.child_design_current = (Child_Design) component;
					}
					
					
					Child_Upload child_upload = new Child_Upload(mainpage);
					mainpage.Mainpanel.removeAll();
					mainpage.Mainpanel.add(child_upload);
					mainpage.Mainpanel.updateUI();
					mainpage.CurrentPage = 3;
					
					
	            	
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
	        String  title = "Upload";  
	        dialog = new JDialog(parent, title,true);  
	        dialog.add(this);  
	        dialog.setResizable(false);  
	        dialog.setSize(350, 150);  
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

