package EasyBBK_Swing.gui;

import EasyBBK_Swing.gui.BackBone;
import EasyBBK_Swing.gui.Child_Design;
import EasyBBK_Swing.gui.DragCompListener;
import EasyBBK_Swing.gui.JLabelWithID;
import EasyBBK_Swing.gui.LinePanel;
import EasyBBK_Swing.gui.Pen;
import EasyBBK_Swing.gui.TPanel;
import EasyBBK_Swing.gui.FontChooser;





import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.border.TitledBorder;
import javax.swing.event.MouseInputListener;
import javax.swing.text.AttributeSet;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

import data_center.BbkType;
import data_center.SketchCenter;
import data_center.SketchComponent;
import data_center.SketchProject;
import data_center.SketchComponent.BioBrick;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

@SuppressWarnings("serial")
public class Child_Design extends JLayeredPane {
	public MainPage mainpage;
	private JLayeredPane panel = new JLayeredPane();
	private TPanel Tpanel = new TPanel();
	private Boolean choose = new Boolean(false);
	private Object source = new Object();
	private Pen pen = null;
	private LinePanel linePanel = new LinePanel(pen);
	private TextLabel textLabel = null;
	
	private Color lineColor = Color.black;
	private float lineStroke = 3.0f;
	
	private int compCount = 0;
	
	SketchCenter sketchCenter = new SketchCenter();
	
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
		//initialize tool labels
		JLabelWithID promoter = new JLabelWithID("");
		JLabelWithID rbs = new JLabelWithID("");
		JLabelWithID coding = new JLabelWithID("");
		JLabelWithID terminator = new JLabelWithID("");
		JLabelWithID primer = new JLabelWithID("");
		JLabelWithID reporter = new JLabelWithID("");
		JLabelWithID factor = new JLabelWithID("");
		JLabelWithID recepter = new JLabelWithID("");
		JLabelWithID protein = new JLabelWithID("");
		JLabelWithID plasmid = new JLabelWithID("");
		JButton backout = new JButton();
		JButton fontButton = new JButton();
		JButton lineButton = new JButton();
		Pen text = new Pen();
		Pen eraser = new Pen();
		Pen line_inhabit = new Pen();
		Pen line_enhance = new Pen();
		Pen line_other = new Pen();
		JLabel background = new JLabel();
		
		sketchCenter.newProject();
		
		this.setBounds(0, 0, 1366, 670);
		this.setLayout(null);
		
		backout.setBounds(214,113,45,48);
		ImageIcon image_backout = new ImageIcon(Child_Design.class.getResource("/EasyBBK_Swing/image/Backout.png"));
		backout.setIcon(image_backout);
        this.add(backout);
		
        fontButton.setBounds(77,113,45,48);
		ImageIcon image_font = new ImageIcon(Child_Design.class.getResource("/EasyBBK_Swing/image/Font.png"));
		fontButton.setIcon(image_font);
        this.add(fontButton);
        
        ImageIcon image_text = new ImageIcon(Child_Design.class.getResource("/EasyBBK_Swing/image/Text_design.png"));
		text.setIcon(image_text);
		text.setBounds(12, 113, 45, 48);
		text.setName("text");
		text.setType(5);
		this.add(text);	
		
		ImageIcon image_eraser = new ImageIcon(Child_Design.class.getResource("/EasyBBK_Swing/image/Eraser.png"));
		eraser.setIcon(image_eraser);
		eraser.setBounds(148, 113, 45, 48);
		eraser.setName("eraser");
		eraser.setType(2);
		this.add(eraser);

		ImageIcon image_promoter = new ImageIcon(Child_Design.class.getResource("/EasyBBK_Swing/image/Promoter.png"));
		promoter.setIcon(image_promoter);
		promoter.setBounds(6, 233, 84, 60);
		promoter.setName("promoter");
		this.add(promoter);		
		
		ImageIcon image_rbs = new ImageIcon(Child_Design.class.getResource("/EasyBBK_Swing/image/RBS.png"));
		rbs.setIcon(image_rbs);
		rbs.setBounds(100, 233, 84, 60);
		rbs.setName("rbs");
		this.add(rbs);		
		
		ImageIcon image_coding = new ImageIcon(Child_Design.class.getResource("/EasyBBK_Swing/image/Coding.png"));
		coding.setIcon(image_coding);
		coding.setBounds(193, 233, 84, 60);
		coding.setName("coding");
		this.add(coding);		
		
		ImageIcon image_terminator = new ImageIcon(Child_Design.class.getResource("/EasyBBK_Swing/image/Terminator.png"));
		terminator.setIcon(image_terminator);
		terminator.setBounds(6, 299, 84, 60);
		terminator.setName("terminator");
		this.add(terminator);				
		
		ImageIcon image_primer = new ImageIcon(Child_Design.class.getResource("/EasyBBK_Swing/image/Primer.png"));
		primer.setIcon(image_primer);
		primer.setBounds(100, 299, 84, 60);
		primer.setName("primer");
		this.add(primer);		
		
		ImageIcon image_reporter = new ImageIcon(Child_Design.class.getResource("/EasyBBK_Swing/image/Reporter.png"));
		reporter.setIcon(image_reporter);
		reporter.setBounds(193, 299, 84, 60);
		reporter.setName("reporter");
		this.add(reporter);
		
		ImageIcon image_recepter = new ImageIcon(Child_Design.class.getResource("/EasyBBK_Swing/image/Recepter.png"));
		recepter.setIcon(image_recepter);
		recepter.setBounds(6, 365, 84, 60);
		recepter.setName("recepter");
		this.add(recepter);
		
		ImageIcon image_factor = new ImageIcon(Child_Design.class.getResource("/EasyBBK_Swing/image/Factor.png"));
		factor.setIcon(image_factor);
		factor.setBounds(100, 365, 84, 60);
		factor.setName("factor");
		this.add(factor);
		
		ImageIcon image_protein = new ImageIcon(Child_Design.class.getResource("/EasyBBK_Swing/image/Protein.png"));
		protein.setIcon(image_protein);
		protein.setBounds(193, 365, 84, 60);
		protein.setName("protein");
		this.add(protein);
		
		ImageIcon image_plasmid = new ImageIcon(Child_Design.class.getResource("/EasyBBK_Swing/image/Plasmid.png"));
		plasmid.setIcon(image_plasmid);
		plasmid.setBounds(6, 433, 84, 60);
		plasmid.setName("plasmid");
		this.add(plasmid);
		
		BackBone backbone = new BackBone("");
		ImageIcon image_backbone = new ImageIcon(Child_Design.class.getResource("/EasyBBK_Swing/image/Backbone.png"));
		backbone.setIcon(image_backbone);
		backbone.setBounds(6, 596, 224, 40);
		backbone.setName("backbone");
		this.add(backbone);
		
		lineButton.setBounds(234,596,43,40);
		ImageIcon image_line = new ImageIcon(Child_Design.class.getResource("/EasyBBK_Swing/image/LineStyleChooser.png"));
		lineButton.setIcon(image_line);
        this.add(lineButton);
		
		ImageIcon image_line_inhabit = new ImageIcon(Child_Design.class.getResource("/EasyBBK_Swing/image/InhabitLine.png"));
		line_inhabit.setIcon(image_line_inhabit);
		line_inhabit.setBounds(6, 517, 84, 59);
		line_inhabit.setName("line");
		line_inhabit.setType(-1);
		this.add(line_inhabit);
		
		ImageIcon image_line_enhance = new ImageIcon(Child_Design.class.getResource("/EasyBBK_Swing/image/EnhanceLine.png"));
		line_enhance.setIcon(image_line_enhance);
		line_enhance.setBounds(100, 517, 84, 59);
		line_enhance.setName("line");
		line_enhance.setType(0);
		this.add(line_enhance);
		
		ImageIcon image_line_other = new ImageIcon(Child_Design.class.getResource("/EasyBBK_Swing/image/OtherLine.png"));
		line_other.setIcon(image_line_other);
		line_other.setBounds(193, 517, 84, 59);
		line_other.setName("line");
		line_other.setType(1);
		this.add(line_other);
		
		panel.setLayout(null);
		panel.setOpaque(true);
		panel.setBorder(BorderFactory.createEtchedBorder());
		panel.setBackground(Color.WHITE);		
		panel.setBounds(283, 0, 1083, 665);
		this.add(panel);
		
		Tpanel.setLayout(null);
		Tpanel.setBounds(0, 0, 1083, 665);
		panel.add(Tpanel,0);
		
		linePanel.setLayout(null);
		linePanel.setBounds(0, 0, 1083, 665);
		panel.add(linePanel);
		
		//add action to labels
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
		
		GetCompListener listener_recepter = new GetCompListener();
		recepter.addMouseListener(listener_recepter);
		recepter.addMouseMotionListener(listener_recepter);
		
		GetCompListener listener_factor = new GetCompListener();
		factor.addMouseListener(listener_factor);
		factor.addMouseMotionListener(listener_factor);
		
		GetCompListener listener_protein = new GetCompListener();
		protein.addMouseListener(listener_protein);
		protein.addMouseMotionListener(listener_protein);
		
		GetCompListener listener_plasmid = new GetCompListener();
		plasmid.addMouseListener(listener_plasmid);
		plasmid.addMouseMotionListener(listener_plasmid);
		
		GetCompListener listener_backbone = new GetCompListener();
		backbone.addMouseListener(listener_backbone);
		backbone.addMouseMotionListener(listener_backbone);
		
		IfDrawLineListener line_inhabit_Listener = new IfDrawLineListener();
		line_inhabit.addMouseListener(line_inhabit_Listener);
		line_inhabit.addMouseMotionListener(line_inhabit_Listener);
		
		IfDrawLineListener line_enhance_Listener = new IfDrawLineListener();
		line_enhance.addMouseListener(line_enhance_Listener);
		line_enhance.addMouseMotionListener(line_enhance_Listener);
		
		IfDrawLineListener line_other_Listener = new IfDrawLineListener();
		line_other.addMouseListener(line_other_Listener);
		line_other.addMouseMotionListener(line_other_Listener);
		
		IfDrawLineListener eraserListener = new IfDrawLineListener();
		eraser.addMouseListener(eraserListener);
		eraser.addMouseMotionListener(eraserListener);
		
		IfDrawLineListener textListener = new IfDrawLineListener();
		text.addMouseListener(textListener);
		text.addMouseMotionListener(textListener);
		
		DrawCompListener Plistener = new DrawCompListener();
		panel.addMouseListener(Plistener);
		panel.addMouseMotionListener(Plistener);
		
		DrawLineListener drawLineListener = new DrawLineListener();
		panel.addMouseListener(drawLineListener);
		panel.addMouseMotionListener(drawLineListener);	
		
		ShowFontChooser showFont = new ShowFontChooser();
		fontButton.addMouseListener(showFont);
		fontButton.addMouseMotionListener(showFont);

		ShowLineStyleChooser showLineStyle = new ShowLineStyleChooser();
		lineButton.addMouseListener(showLineStyle);
		lineButton.addMouseMotionListener(showLineStyle);		
		
		//background
		this.add(background);
		this.setPosition(background, -1);
		background.setBounds(0, 0, 1366, 670);
		ImageIcon image_background = new ImageIcon(Child_Design.class.getResource("/EasyBBK_Swing/image/design_background.png"));
		background.setIcon(image_background);
	}
	
	/**
	 * Decide to use which component
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
	    		
	    		if (pen != null)
	    		{
	    			pen.noUse();
	    			if (pen.getType()==-1 | pen.getType()==0 | pen.getType()==1)
	    			{
	    				if (linePanel.lineList.size()!=0)
	    				{
	    					BufferedImage img = new BufferedImage(panel.getWidth(),panel.getHeight(),BufferedImage.TRANSLUCENT);
							Graphics2D g = (Graphics2D)img.getGraphics();
							linePanel.paint(g);
							
							BufferedImage newimg = img.getSubimage(linePanel.getLineBorder()[0]-10, linePanel.getLineBorder()[2]-10,
									linePanel.getLineBorder()[1]-linePanel.getLineBorder()[0]+20,
									linePanel.getLineBorder()[3]-linePanel.getLineBorder()[2]+20);
							JLabelWithID newLine = new JLabelWithID();
							newLine.ID=compCount++;
							ImageIcon image = new ImageIcon(newimg);
							newLine.setIcon(image);
							newLine.setBounds(linePanel.getLineBorder()[0]-10, linePanel.getLineBorder()[2]-10,
									linePanel.getLineBorder()[1]-linePanel.getLineBorder()[0]+20,
									linePanel.getLineBorder()[3]-linePanel.getLineBorder()[2]+20);
							System.out.println("ok");
							panel.add(newLine);
							
							//ÒÆ¶¯
							DragLineListener dragListener = new DragLineListener();
							newLine.addMouseListener(dragListener);
							newLine.addMouseMotionListener(dragListener);
							linePanel.endLine();
							
							//É¾³ý
							DeleteListener deleteLine = new DeleteListener();
							newLine.addMouseListener(deleteLine);
							newLine.addMouseMotionListener(deleteLine);
	    				}
	    			}
	    		}	
	    		pen = null;
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
	    		source = null;
	    		pen = null;
	    	}
	    }
	      
	    public void mouseMoved(MouseEvent e){}
	}
	
	/**
	 * Decide to use which component
	 */
	class IfDrawLineListener implements MouseInputListener
	{
		public void mouseClicked(MouseEvent e) 
		{			
			
			if (((Pen)e.getSource()) != pen & pen != null)
			{
				pen.noUse();
				if (pen.getType()==-1 | pen.getType()==0 | pen.getType()==1)
    			{
    				if (linePanel.lineList.size()!=0)
    				{
    					BufferedImage img = new BufferedImage(panel.getWidth(),panel.getHeight(),BufferedImage.TRANSLUCENT);
						Graphics2D g = (Graphics2D)img.getGraphics();
						linePanel.paint(g);
						
						BufferedImage newimg = img.getSubimage(linePanel.getLineBorder()[0]-10, linePanel.getLineBorder()[2]-10,
								linePanel.getLineBorder()[1]-linePanel.getLineBorder()[0]+20,
								linePanel.getLineBorder()[3]-linePanel.getLineBorder()[2]+20);
						JLabelWithID newLine = new JLabelWithID();
						newLine.ID=compCount++;
						ImageIcon image = new ImageIcon(newimg);
						newLine.setIcon(image);
						newLine.setBounds(linePanel.getLineBorder()[0]-10, linePanel.getLineBorder()[2]-10,
								linePanel.getLineBorder()[1]-linePanel.getLineBorder()[0]+20,
								linePanel.getLineBorder()[3]-linePanel.getLineBorder()[2]+20);
						System.out.println("ok");
						panel.add(newLine);
						
						//ÒÆ¶¯
						DragLineListener dragListener = new DragLineListener();
						newLine.addMouseListener(dragListener);
						newLine.addMouseMotionListener(dragListener);
						linePanel.endLine();
						
						//É¾³ý
						DeleteListener deleteLine = new DeleteListener();
						newLine.addMouseListener(deleteLine);
						newLine.addMouseMotionListener(deleteLine);
    				}
    			}
				pen = (Pen)e.getSource();
				linePanel.setPen(pen);
				pen.inUse();
			}						
			else if (((Pen)e.getSource()) != pen & pen==null)
			{
				pen = (Pen)e.getSource();
				linePanel.setPen(pen);
				pen.inUse();
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
			}
			else if (pen.ifUse())
			{
				pen.noUse();
				if (pen.getType()==-1 | pen.getType()==0 | pen.getType()==1)
    			{
    				if (linePanel.lineList.size()!=0)
    				{
    					BufferedImage img = new BufferedImage(panel.getWidth(),panel.getHeight(),BufferedImage.TRANSLUCENT);
						Graphics2D g = (Graphics2D)img.getGraphics();
						linePanel.paint(g);
						
						BufferedImage newimg = img.getSubimage(linePanel.getLineBorder()[0]-10, linePanel.getLineBorder()[2]-10,
								linePanel.getLineBorder()[1]-linePanel.getLineBorder()[0]+20,
								linePanel.getLineBorder()[3]-linePanel.getLineBorder()[2]+20);
						JLabelWithID newLine = new JLabelWithID();
						newLine.ID=compCount++;
						ImageIcon image = new ImageIcon(newimg);
						newLine.setIcon(image);
						newLine.setBounds(linePanel.getLineBorder()[0]-10, linePanel.getLineBorder()[2]-10,
								linePanel.getLineBorder()[1]-linePanel.getLineBorder()[0]+20,
								linePanel.getLineBorder()[3]-linePanel.getLineBorder()[2]+20);
						System.out.println("ok");
						panel.add(newLine);
						
						//ÒÆ¶¯
						DragLineListener dragListener = new DragLineListener();
						newLine.addMouseListener(dragListener);
						newLine.addMouseMotionListener(dragListener);
						linePanel.endLine();
						
						//É¾³ý
						DeleteListener deleteLine = new DeleteListener();
						newLine.addMouseListener(deleteLine);
						newLine.addMouseMotionListener(deleteLine);
    				}
    			}
				pen = null;
				linePanel.setPen(pen);
				
			}
		}
		
		public void mousePressed(MouseEvent e) {}

		public void mouseMoved(MouseEvent e) {}

		public void mouseDragged(MouseEvent e) {}
			
		public void mouseReleased(MouseEvent e){}
			
		public void mouseEntered(MouseEvent e) {}

		public void mouseExited(MouseEvent e) {}
	}
	
	/**
	 * Draw components and text
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
		    		newBackBone.ID=compCount++;		
		    		
		    		ImageIcon image_newBackBone = new ImageIcon(Child_Design.class.getResource("/EasyBBK_Swing/image/backbone_move.png"));
		    		newBackBone.setIcon(image_newBackBone);
		    		
		    		point.x = point.x - 41;
		    		point.y = point.y - 25;
		    		
		    		//Location
		    		newBackBone.setBounds(point.x, point.y, 83, 
		    				50);
		    		newBackBone.setName(((BackBone)source).getName());
		    		newBackBone.activate();
					panel.add(newBackBone,-1);
					
					Point center = new Point((int)(point.x+((BackBone)source).getWidth()/2),
							(int)(point.y+((BackBone)source).getHeight()/2));
					sketchCenter.currentProject.addComponent
						(new SketchComponent.BackBone(newBackBone.ID, center, ((BackBone)source).getWidth()));
					
					DragCompListener listener = new DragCompListener(panel,Tpanel,sketchCenter);
					newBackBone.addMouseListener(listener);
					newBackBone.addMouseMotionListener(listener);
					
					//É¾³ý
					DeleteListener deleteBackbone = new DeleteListener();
					newBackBone.addMouseListener(deleteBackbone);
					newBackBone.addMouseMotionListener(deleteBackbone);
					
	    		}
	    		else
	    		{
					point =  e.getPoint();
		    		JLabelWithID newLabel = new JLabelWithID();
		    		newLabel.ID=compCount++;
		    		
		    		ImageIcon image_newLabel = new ImageIcon(Child_Design.class.getResource("/EasyBBK_Swing/image/"+(((JLabelWithID)source)).getName()+"_move.png"));
		    		newLabel.setIcon(image_newLabel);		    		
		    		
		    		point.x = point.x - (((JLabelWithID)source).getWidth())/2;
		    		point.y = point.y - (((JLabelWithID)source).getHeight())/2;
		    		
		    		//Location
		    		newLabel.setBounds(point.x, point.y, ((JLabelWithID)source).getWidth(), 
		    				((JLabelWithID)source).getHeight());
		    		newLabel.setName(((JLabelWithID)source).getName());
					panel.add(newLabel);
					
					Point center = new Point((int)(point.x+((JLabelWithID)source).getWidth()/2),
							(int)(point.y+((JLabelWithID)source).getHeight()/2));

					// test
					
					SketchComponent.Component component = null;
					switch (newLabel.getName())
					{	
						case "promoter":
							component = new SketchComponent.BioBrick(newLabel.ID, BbkType.Sketch.BioBrick.PROMOTER, center, null);
							break;
						case "rbs":
							component = new SketchComponent.BioBrick(newLabel.ID, BbkType.Sketch.BioBrick.RBS, center, null);
							break;
						case "coding":
							component = new SketchComponent.BioBrick(newLabel.ID, BbkType.Sketch.BioBrick.PROTEIN_CODING_SEQUENCE, center, null);
							break;
						case "terminator":
							component = new SketchComponent.BioBrick(newLabel.ID, BbkType.Sketch.BioBrick.TERMINATOR, center, null);
							break;	
						case "primer":
							component = new SketchComponent.BioBrick(newLabel.ID, BbkType.Sketch.BioBrick.PRIMER, center, null);
							break;
						case "reporter":
							component = new SketchComponent.BioBrick(newLabel.ID, BbkType.Sketch.BioBrick.REPORTER, center, null);
							break;
						case "factor":
							component = new SketchComponent.Protein(newLabel.ID, BbkType.Sketch.Protein.FACTOR, center, null);
							break;
						case "recepter":
							component = new SketchComponent.Protein(newLabel.ID, BbkType.Sketch.Protein.RECEPTER, center, null);
							break;
						case "plasmid":
							component = new SketchComponent.BioVector(newLabel.ID, BbkType.Sketch.BioVector.PLASMID, center, 1);
							break;
						case "virus":
							component = new SketchComponent.BioVector(newLabel.ID, BbkType.Sketch.BioVector.VIRUS, center, 1);
							break;
						case "bacteria":
							component = new SketchComponent.BioVector(newLabel.ID, BbkType.Sketch.BioVector.BACTERIA, center, 1);
							break;
						case "plant_cell":
							component = new SketchComponent.BioVector(newLabel.ID, BbkType.Sketch.BioVector.PLANT_CELL, center, 1);
							break;
						case "animal_cell":
							component = new SketchComponent.BioVector(newLabel.ID, BbkType.Sketch.BioVector.ANIMAL_CELL, center, 1);
							break;
					}
					
					sketchCenter.currentProject.addComponent(component);
					
					DragCompListener listener = new DragCompListener(panel,Tpanel,sketchCenter);
					newLabel.addMouseListener(listener);
					newLabel.addMouseMotionListener(listener);
					
					//É¾³ý
					DeleteListener deleteLabel = new DeleteListener();
					newLabel.addMouseListener(deleteLabel);
					newLabel.addMouseMotionListener(deleteLabel);
	    		}
	    	}
	    	else if ((pen!=null) & (pen.getType()==5))
	    	{
	    		point =  e.getPoint();
	    		TextLabel newText = new TextLabel(panel,Tpanel);
	    		point.x = point.x - 25;
	    		point.y = point.y - 15;
	    		newText.setBounds(point.x, point.y, 50,30);
	    		newText.ID=compCount++;
	    		panel.add(newText);
				
	    		ChooseCurrentText chooseTextListener = new ChooseCurrentText();
	    		newText.addFocusListener(chooseTextListener);
	    		
	    		Point center = new Point(point.x + 25, point.y + 15);
	    		
	    		sketchCenter.currentProject.addComponent
	    			(new SketchComponent.Label(newText.ID, null, center, new Font("Time New Roman", Font.PLAIN, 16), Color.BLACK));
	    		
	    		DragTextListener dragListener = new DragTextListener();
	    		newText.addMouseListener(dragListener);
	    		newText.addMouseMotionListener(dragListener);
	    		
	    		//É¾³ý
				DeleteListener deleteText = new DeleteListener();
				newText.addMouseListener(deleteText);
				newText.addMouseMotionListener(deleteText);
	    	}
	    	
	    	//Draw lines
	    	
	    	panel.setPosition(linePanel, 0);
	    	linePanel.repaint();
	    }
	      
	    public void mouseMoved(MouseEvent e){}
	}
	
	/**
	 * Draw line
	 */
	public class DrawLineListener implements MouseInputListener
	{
		private ArrayList<Point> lineList;
		
		public void mouseClicked(MouseEvent e) 
		{
			if (pen!=null)
			{
				if (pen.ifUse() & (pen.getType()==-1 | pen.getType()==0 | pen.getType()==1))
				{
					if (e.getButton()==MouseEvent.BUTTON3)
					{
						linePanel.addPoint(e.getPoint());
						linePanel.repaint();			

						BufferedImage img = new BufferedImage(panel.getWidth(),panel.getHeight(),BufferedImage.TRANSLUCENT);
						Graphics2D g = (Graphics2D)img.getGraphics();
						linePanel.paint(g);
						
						BufferedImage newimg = img.getSubimage(linePanel.getLineBorder()[0]-10, linePanel.getLineBorder()[2]-10,
								linePanel.getLineBorder()[1]-linePanel.getLineBorder()[0]+20,
								linePanel.getLineBorder()[3]-linePanel.getLineBorder()[2]+20);
						JLabelWithID newLine = new JLabelWithID();
						newLine.ID=compCount++;
						ImageIcon image = new ImageIcon(newimg);
						newLine.setIcon(image);
						newLine.setBounds(linePanel.getLineBorder()[0]-10, linePanel.getLineBorder()[2]-10,
								linePanel.getLineBorder()[1]-linePanel.getLineBorder()[0]+20,
								linePanel.getLineBorder()[3]-linePanel.getLineBorder()[2]+20);
						System.out.println("ok");
						panel.add(newLine);
						
						int lineType = -1;
						switch (linePanel.lineType)
						{	
							case LinePanel.LINE_WITH_EMPTY_ARROW:
								lineType = BbkType.Sketch.Relation.PROMOTE;	break;
							case LinePanel.LINE_WIHT_STOP_END:
								lineType = BbkType.Sketch.Relation.SUPPRESS;	break;
							case LinePanel.LINE_WITH_FULL_ARROW:
								lineType = BbkType.Sketch.Relation.OTHER;	break;
						}						
						
						sketchCenter.currentProject.addComponent(new SketchComponent.Relation(newLine.ID, lineType, lineList, linePanel.color, (int) linePanel.stroke));
						
						//ÒÆ¶¯
						DragLineListener dragListener = new DragLineListener();
						newLine.addMouseListener(dragListener);
						newLine.addMouseMotionListener(dragListener);
						linePanel.endLine();
						
						//É¾³ý
						DeleteListener deleteLine = new DeleteListener();
						newLine.addMouseListener(deleteLine);
						newLine.addMouseMotionListener(deleteLine);
					}
					else if (e.getClickCount()==1 & e.getButton()==MouseEvent.BUTTON1)
					{
						linePanel.addPoint(e.getPoint());
						linePanel.repaint();
					}
				}
			}
		}
		
		public void mousePressed(MouseEvent e) {}

		public void mouseMoved(MouseEvent e) {}

		public void mouseDragged(MouseEvent e) {}
			
		public void mouseReleased(MouseEvent e){}
			
		public void mouseEntered(MouseEvent e) {}

		public void mouseExited(MouseEvent e) {}
		
		
	}
	
	/**
	 * Show FontChooser
	 */
	class ShowFontChooser implements MouseInputListener
	{
		public void mouseClicked(MouseEvent e) 
		{	
			Color col = null;
			Font pFont = null;
			if (textLabel == null)
			{
				col = Color.black;
		        pFont = new Font("Time New Roman",Font.PLAIN,16);
			}
			else
			{
				StyledDocument totalStyle = (StyledDocument)(((TextLabel)textLabel).getDocument());
				AttributeSet attr = ((TextLabel)textLabel).getCharacterAttributes();
		        col = totalStyle.getForeground(attr);
		        pFont = totalStyle.getFont(attr);	
			}
			
			FontChooser one = new FontChooser(pFont, col);    
	        one.showDialog((JFrame)((JButton)(e.getSource())).getRootPane().getParent(),500,200);
	        
	        Font font=one.getSelectedfont();  
	        Color color=one.getSelectedcolor(); 	        
	        SimpleAttributeSet attrNew = new SimpleAttributeSet();
	        StyleConstants.setAlignment(attrNew, StyleConstants.ALIGN_CENTER);
	        StyleConstants.setForeground(attrNew, color);
	        if ((one.getSelectedstyle()).equals("Normal")) 
            {  
	        	StyleConstants.setItalic(attrNew, false); 
	        	StyleConstants.setBold(attrNew, false);
            }  
            if ((one.getSelectedstyle()).equals("Italic")) 
            {  
            	StyleConstants.setItalic(attrNew, true); 
            	StyleConstants.setBold(attrNew, false);       	
            }  
            if ((one.getSelectedstyle()).equals("Bold")) 
            {  
            	StyleConstants.setBold(attrNew, true);  
            	StyleConstants.setItalic(attrNew, false);
            }  
            if ((one.getSelectedstyle()).equals("BoldItalic")) 
            {  
            	StyleConstants.setBold(attrNew, true); 
            	StyleConstants.setItalic(attrNew, true);  
            }
	        StyleConstants.setFontSize(attrNew, font.getSize());
	        StyleConstants.setFontFamily(attrNew, font.getFamily());
	        if (textLabel!=null)
	        {
	        	textLabel.setCharacterAttributes(attrNew, true);
		        textLabel.setParagraphAttributes(attrNew, true);
	        }        
		}
		
		public void mousePressed(MouseEvent e) {}

		public void mouseMoved(MouseEvent e) {}

		public void mouseDragged(MouseEvent e) {}
			
		public void mouseReleased(MouseEvent e){}
			
		public void mouseEntered(MouseEvent e) {}

		public void mouseExited(MouseEvent e) {}
	}
	
	/**
	 * Show LineStyleChooserChooser
	 */
	class ShowLineStyleChooser implements MouseInputListener
	{
		public void mouseClicked(MouseEvent e) 
		{	
			LineStyleChooser one = new LineStyleChooser((int)lineStroke, lineColor);    
	        one.showDialog((JFrame)((JButton)(e.getSource())).getRootPane().getParent(),500,200);
	        
	        lineStroke = (float)one.getSelectedsize();  
	        lineColor=one.getSelectedcolor(); 
	        
	        linePanel.stroke = lineStroke;
	        linePanel.color = lineColor;
		}
		
		public void mousePressed(MouseEvent e) {}

		public void mouseMoved(MouseEvent e) {}

		public void mouseDragged(MouseEvent e) {}
			
		public void mouseReleased(MouseEvent e){}
			
		public void mouseEntered(MouseEvent e) {}

		public void mouseExited(MouseEvent e) {}
	}
	
	class DragLineListener implements MouseInputListener
	{
		Point point = new Point(0,0);
		
		public void mouseClicked(MouseEvent e) {}
		
		public void mousePressed(MouseEvent e) 
		{
			point = SwingUtilities.convertPoint(e.getComponent() , e.getPoint(), 
					(e.getComponent()).getParent());
		}

		public void mouseDragged(MouseEvent e) 
		{
			Point newPoint = SwingUtilities.convertPoint(e.getComponent() , 
					e.getPoint(), (e.getComponent()).getParent());
			(e.getComponent()).setLocation(
					(e.getComponent()).getX()+(newPoint.x-point.x),
					(e.getComponent()).getY()+(newPoint.y-point.y));
			point = newPoint;		
		}
		
		public void mouseMoved(MouseEvent e) 
		{
			TitledBorder border = new TitledBorder("");
			((JLabelWithID)(e.getComponent())).setBorder(border);
		}
			
		public void mouseReleased(MouseEvent e){}
			
		public void mouseEntered(MouseEvent e) 
		{
			TitledBorder border = new TitledBorder("");
			((JLabelWithID)(e.getComponent())).setBorder(border);
		}

		public void mouseExited(MouseEvent e) 
		{
			((JLabelWithID)(e.getComponent())).setBorder(null);
		}
	}
	
	/**
	 * Drag component
	 */
	class DragTextListener implements MouseInputListener
	{
		Point point = new Point(0,0);
		
		public void mouseClicked(MouseEvent e) {}
		
		public void mousePressed(MouseEvent e) 
		{
			point = SwingUtilities.convertPoint(e.getComponent() , e.getPoint(), 
					(e.getComponent()).getParent());
		}

		public void mouseDragged(MouseEvent e) 
		{
			if (!((TextLabel)e.getComponent()).getResize() & ((TextLabel)e.getComponent()).movable )
			{
				Point newPoint = SwingUtilities.convertPoint(e.getComponent() , 
						e.getPoint(), (e.getComponent()).getParent());
				(e.getComponent()).setLocation(
						(e.getComponent()).getX()+(newPoint.x-point.x),
						(e.getComponent()).getY()+(newPoint.y-point.y));
				point = newPoint;
			}		
		}
		
		public void mouseMoved(MouseEvent e) {}
			
		public void mouseReleased(MouseEvent e){}
			
		public void mouseEntered(MouseEvent e) {}

		public void mouseExited(MouseEvent e) {}
	}
	
	/**
	 * Choose current listener
	 */
	class ChooseCurrentText implements FocusListener
	{		
		public void focusGained(FocusEvent e) 
		{
			textLabel = (TextLabel)(e.getSource());
			TitledBorder border = new TitledBorder("");
			textLabel.setBorder(border);
			textLabel.focus=true;
		}

		public void focusLost(FocusEvent e) 
		{
			textLabel.focus=false;
			textLabel.setBorder(null);			
		}
	}
	
	/**
	 * Delete components
	 */
	class DeleteListener implements MouseInputListener
	{		
		public void mouseClicked(MouseEvent e) 
		{
			if (pen!=null)
			{
				if (pen.getType()==2)
				{
					((e.getComponent()).getParent()).remove(e.getComponent());
				}
			}
		}
		
		public void mousePressed(MouseEvent e) {}

		public void mouseDragged(MouseEvent e) {}
		
		public void mouseMoved(MouseEvent e) {}
			
		public void mouseReleased(MouseEvent e){}
			
		public void mouseEntered(MouseEvent e) {}

		public void mouseExited(MouseEvent e) {}
	}
	
	/**
	 * Backout
	 */
	class BackoutListener implements MouseInputListener
	{		
		public void mouseClicked(MouseEvent e) 
		{
			
		}
		
		public void mousePressed(MouseEvent e) {}

		public void mouseDragged(MouseEvent e) {}
		
		public void mouseMoved(MouseEvent e) {}
			
		public void mouseReleased(MouseEvent e){}
			
		public void mouseEntered(MouseEvent e) {}

		public void mouseExited(MouseEvent e) {}
	}
}
