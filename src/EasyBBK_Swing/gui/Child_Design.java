package EasyBBK_Swing.gui;

import EasyBBK_Swing.gui.BackBone;
import EasyBBK_Swing.gui.Child_Design;
import EasyBBK_Swing.gui.DeleteListener;
import EasyBBK_Swing.gui.DragCompListener;
import EasyBBK_Swing.gui.JLabelWithID;
import EasyBBK_Swing.gui.LinePanel;
import EasyBBK_Swing.gui.Pen;
import EasyBBK_Swing.gui.TPanel;
import EasyBBK_Swing.gui.Child_Design.DrawCompListener;
import EasyBBK_Swing.gui.Child_Design.IfDrawLineListener;
import EasyBBK_Swing.gui.Child_Design.GetCompListener;

import java.awt.Color;
import java.awt.Component;
import java.awt.Image;
import java.awt.Point;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.event.MouseInputListener;

import java.awt.Font;
import java.awt.event.MouseEvent;

public class Child_Design extends JPanel {
	public MainPage mainpage;
	private JLayeredPane panel = new JLayeredPane();
	private TPanel Tpanel = new TPanel();
	private Boolean choose = new Boolean(false);
	private Object source = new Object();
	private JTextField txtW;
	private Pen pen = new Pen();
	private LinePanel linePanel = new LinePanel(pen);
	
	/**
	 * Create the panel.
	 */
	public Child_Design(MainPage mainpage1) {
		mainpage = mainpage1;
		setBounds(0, 0, 1366, 670);
		setBackground(new Color(255, 255, 255));
		setLayout(null);
		initialize();
		
		setVisible(true);
	}
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() 
	{	
		JLabelWithID promoter = new JLabelWithID("");
		JLabelWithID rbs = new JLabelWithID("");
		JLabelWithID coding = new JLabelWithID("");
		JLabelWithID terminator = new JLabelWithID("");
		JLabelWithID primer = new JLabelWithID("");
		JLabelWithID reporter = new JLabelWithID("");
		
		this.setBounds(0, 0, 1366, 670);
		this.setLayout(null);		
		
		ImageIcon image_promoter = new ImageIcon(Child_Design.class.getResource("/EasyBBK_Swing/image/dc_iso_promoter_Hover.png"));
		image_promoter.setImage(image_promoter.getImage().getScaledInstance(100,50,Image.SCALE_DEFAULT));
		promoter.setIcon(image_promoter);
		promoter.setBounds(8, 241, 100, 50);
		promoter.setName("promoter");
		this.add(promoter);		
		
		ImageIcon image_rbs = new ImageIcon(Child_Design.class.getResource("/EasyBBK_Swing/image/dc_iso_rbs_Hover.png"));
		image_rbs.setImage(image_rbs.getImage().getScaledInstance(100,50,Image.SCALE_DEFAULT));
		rbs.setIcon(image_rbs);
		rbs.setBounds(116, 241, 100, 50);
		rbs.setName("rbs");
		this.add(rbs);		
		
		ImageIcon image_coding = new ImageIcon(Child_Design.class.getResource("/EasyBBK_Swing/image/dc_iso_codingsequence_Hover.png"));
		image_coding.setImage(image_coding.getImage().getScaledInstance(100,50,Image.SCALE_DEFAULT));
		coding.setIcon(image_coding);
		coding.setBounds(224, 241, 100, 50);
		coding.setName("coding");
		this.add(coding);		
		
		ImageIcon image_terminator = new ImageIcon(Child_Design.class.getResource("/EasyBBK_Swing/image/dc_iso_terminator_Hover.png"));
		image_terminator.setImage(image_terminator.getImage().getScaledInstance(100,50,Image.SCALE_DEFAULT));
		terminator.setIcon(image_terminator);
		terminator.setBounds(8, 297, 100, 50);
		terminator.setName("terminator");
		this.add(terminator);				
		
		ImageIcon image_primer = new ImageIcon(Child_Design.class.getResource("/EasyBBK_Swing/image/dc_iso_primer_Hover.png"));
		image_primer.setImage(image_primer.getImage().getScaledInstance(100,50,Image.SCALE_DEFAULT));
		primer.setIcon(image_primer);
		primer.setBounds(116, 297, 100, 50);
		primer.setName("primer");
		this.add(primer);		
		
		ImageIcon image_reporter = new ImageIcon(Child_Design.class.getResource("/EasyBBK_Swing/image/dc_iso_reporter_Hover.png"));
		image_reporter.setImage(image_reporter.getImage().getScaledInstance(100,50,Image.SCALE_DEFAULT));
		reporter.setIcon(image_reporter);
		reporter.setBounds(224, 297, 100, 50);
		reporter.setName("reporter");
		this.add(reporter);
		
		BackBone backbone = new BackBone("");
		ImageIcon image_backbone = new ImageIcon(Child_Design.class.getResource("/EasyBBK_Swing/image/dc_backbone_Hover.png"));
		image_backbone.setImage(image_backbone.getImage().getScaledInstance(210,50,Image.SCALE_DEFAULT));
		backbone.setIcon(image_backbone);
		backbone.setBounds(7, 431, 210, 50);
		backbone.setName("backbone");
		this.add(backbone);
		
		ImageIcon image_pen = new ImageIcon(Child_Design.class.getResource("/EasyBBK_Swing/image/dc_iso_codingsequence_Move.png"));
		image_pen.setImage(image_pen.getImage().getScaledInstance(100,50,Image.SCALE_DEFAULT));
		pen.setIcon(image_pen);
		pen.setBounds(8, 498, 100, 50);
		pen.setName("pen");
		this.add(pen);		
		
		panel.setLayout(null);
		panel.setOpaque(true);
		panel.setBorder(BorderFactory.createEtchedBorder());
		panel.setBackground(Color.WHITE);		
		panel.setBounds(346, 0, 1017, 665);
		this.add(panel);
		
		Tpanel.setLayout(null);
		Tpanel.setBounds(0, 0, 1017, 665);
		panel.add(Tpanel,0);
		
		linePanel.setLayout(null);
		linePanel.setBounds(0, 0, 1017, 665);
		panel.add(linePanel);
		
		GetCompListener listener_promoter = new GetCompListener();
		promoter.addMouseListener(listener_promoter);
		promoter.addMouseMotionListener(listener_promoter);
		
		GetCompListener listener_rbs = new GetCompListener();
		rbs.addMouseListener(listener_rbs);
		rbs.addMouseMotionListener(listener_rbs);
		
		GetCompListener listener_coding = new GetCompListener();
		coding.addMouseListener(listener_coding);
		coding.addMouseMotionListener(listener_coding);
		
		GetCompListener listener_terminator = new GetCompListener();
		terminator.addMouseListener(listener_terminator);
		terminator.addMouseMotionListener(listener_terminator);
		
		GetCompListener listener_primer = new GetCompListener();
		primer.addMouseListener(listener_primer);
		primer.addMouseMotionListener(listener_primer);
		
		GetCompListener listener_reporter = new GetCompListener();
		reporter.addMouseListener(listener_reporter);
		reporter.addMouseMotionListener(listener_reporter);
		
		GetCompListener listener_backbone = new GetCompListener();
		backbone.addMouseListener(listener_backbone);
		backbone.addMouseMotionListener(listener_backbone);
		
		IfDrawLineListener penListener = new IfDrawLineListener();
		pen.addMouseListener(penListener);
		pen.addMouseMotionListener(penListener);
		
		DrawCompListener Plistener = new DrawCompListener();
		panel.addMouseListener(Plistener);
		panel.addMouseMotionListener(Plistener);
		
		//test
		txtW = new JTextField();
		txtW.setText("");
		txtW.setBounds(48, 10, 246, 174);
		this.add(txtW);
		txtW.setColumns(10);
		
	}
	
	/**
	 * Imply drag
	 */
	class GetCompListener implements MouseInputListener
	{
		Point point = new Point(0,0);
		
		public void mousePressed(MouseEvent e){}
		
		public void mouseDragged(MouseEvent e){}
		
		public void mouseReleased(MouseEvent e){}
	      
	    public void mouseEntered(MouseEvent e){}
	      
	    public void mouseExited(MouseEvent e){}
	      
	    public void mouseClicked(MouseEvent e)
	    {
	    	if (source != e.getSource())
	    	{
	    		if (choose)
	    		{
	    			if (((Component)source).getName() == "backbone")
	    			{
	    				((BackBone)source).setEnabled(true);
	    			}
	    			else
	    			{
	    				((JLabelWithID)source).setEnabled(true);
	    			}
	    		}
	    		choose = true;
	    		source = e.getSource();
	    		if (((Component)source).getName() != "backbone")
    			{
    				((JLabelWithID)source).setEnabled(false);
    			}
    			else
    			{
    				((BackBone)source).setEnabled(false);
    			}
	    		pen.noUse();
	    	}
	    	else
	    	{
	    		choose = false;
	    		if (((Component)source).getName() != "backbone")
    			{
    				((JLabelWithID)source).setEnabled(true);
    			}
    			else
    			{
    				((BackBone)source).setEnabled(true);
    			}
	    		source=null;
	    	}
	    }
	      
	    public void mouseMoved(MouseEvent e){}
	}
	
	/**
	 * Provide container to sketch map
	 */
	class DrawCompListener implements MouseInputListener
	{
		Point point = new Point(0,0);
		
		public void mousePressed(MouseEvent e){}
		
		public void mouseDragged(MouseEvent e){}
		
		public void mouseReleased(MouseEvent e){}
	      
	    public void mouseEntered(MouseEvent e){}
	      
	    public void mouseExited(MouseEvent e){}
	      
	    public void mouseClicked(MouseEvent e)
	    {
	    	//Paint component
	    	if (choose)
	    	{	
	    		if (((Component)source).getName() == "backbone")
	    		{
	    			point =  e.getPoint();
		    		BackBone newBackBone = new BackBone(panel,Tpanel);
		    		newBackBone.setIcon(((BackBone)source).getIcon());
		    		point.x = point.x - (((BackBone)source).getWidth())/2;
		    		point.y = point.y - (((BackBone)source).getHeight())/2;
		    		newBackBone.setBounds(point.x, point.y, ((BackBone)source).getWidth(), 
		    				((BackBone)source).getHeight());
		    		newBackBone.setName(((BackBone)source).getName());
		    		newBackBone.activate();
					panel.add(newBackBone,-1);
					
					DragCompListener listener = new DragCompListener(panel,txtW,Tpanel);
					newBackBone.addMouseListener(listener);
					newBackBone.addMouseMotionListener(listener);
					
					DeleteListener delete = new DeleteListener();
					newBackBone.addKeyListener(delete);

	    		}
	    		else
	    		{
					point =  e.getPoint();
		    		JLabelWithID newLabel = new JLabelWithID();
		    		newLabel.setIcon(((JLabelWithID)source).getIcon());
		    		point.x = point.x - (((JLabelWithID)source).getWidth())/2;
		    		point.y = point.y - (((JLabelWithID)source).getHeight())/2;
		    		newLabel.setBounds(point.x, point.y, ((JLabelWithID)source).getWidth(), 
		    				((JLabelWithID)source).getHeight());
		    		newLabel.setName(((JLabelWithID)source).getName());
					panel.add(newLabel);
					
					DragCompListener listener = new DragCompListener(panel,txtW,Tpanel);
					newLabel.addMouseListener(listener);
					newLabel.addMouseMotionListener(listener);
					
					DeleteListener delete = new DeleteListener();
					newLabel.addKeyListener(delete);

	    		}
	    	}
	    	
	    	//Draw lines
	    	linePanel.repaint();
	    }
	      
	    public void mouseMoved(MouseEvent e){}
	}
	
	/**
	 * Provide container to sketch map
	 */
	class IfDrawLineListener implements MouseInputListener
	{
		public void mouseClicked(MouseEvent e) 
		{
			if (((Pen)e.getSource()).ifUse())
			{
				((Pen)e.getSource()).noUse();
			}
			else
			{
				if (choose)
				{
					if (((Component)source).getName() == "backbone")
	    			{
	    				((BackBone)source).setEnabled(true);
	    			}
	    			else
	    			{
	    				((JLabelWithID)source).setEnabled(true);
	    			}
					choose=false;
					source=null;
				}
				((Pen)e.getSource()).inUse();
			}
		}
		
		public void mousePressed(MouseEvent e) {}

		public void mouseMoved(MouseEvent e) {}

		public void mouseDragged(MouseEvent e) {}
			
		public void mouseReleased(MouseEvent e){}
			
		public void mouseEntered(MouseEvent e) {}

		public void mouseExited(MouseEvent e) {}
	}

}
