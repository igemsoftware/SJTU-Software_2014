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
public class Child_Design extends JPanel {
	public MainPage mainpage;
	private JLayeredPane panel = new JLayeredPane();
	private TPanel Tpanel = new TPanel();
	private Boolean choose = new Boolean(false);
	private Object source = new Object();
	private Pen pen = null;
	private LinePanel linePanel = new LinePanel(pen);
	private TextLabel textLabel = null;
	
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
		JButton textButton = new JButton("");
		JLabelWithID promoter = new JLabelWithID("");
		JLabelWithID rbs = new JLabelWithID("");
		JLabelWithID coding = new JLabelWithID("");
		JLabelWithID terminator = new JLabelWithID("");
		JLabelWithID primer = new JLabelWithID("");
		JLabelWithID reporter = new JLabelWithID("");
		JButton backout = new JButton();
		Pen text = new Pen();
		Pen eraser = new Pen();
		Pen line_1 = new Pen();
		
		sketchCenter.newProject();
		
		this.setBounds(0, 0, 1366, 670);
		this.setLayout(null);
		
		backout.setBounds(0,0,100,50);
		backout.setText("Backout");
        this.add(backout);
		
		textButton.setBounds(0,50,100,50);
		textButton.setText("Font");
        this.add(textButton);
		
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
		
		ImageIcon image_line_1 = new ImageIcon(Child_Design.class.getResource("/EasyBBK_Swing/image/dc_iso_codingsequence_Move.png"));
		image_line_1.setImage(image_line_1.getImage().getScaledInstance(100,50,Image.SCALE_DEFAULT));
		line_1.setIcon(image_line_1);
		line_1.setBounds(8, 498, 100, 50);
		line_1.setName("line");
		line_1.setType(0);
		this.add(line_1);
		
		ImageIcon image_text = new ImageIcon(Child_Design.class.getResource("/EasyBBK_Swing/image/dtext_Hover.png"));
		image_text.setImage(image_text.getImage().getScaledInstance(100,50,Image.SCALE_DEFAULT));
		text.setIcon(image_text);
		text.setBounds(119, 125, 100, 50);
		text.setName("text");
		text.setType(5);
		this.add(text);	
		
		ImageIcon image_eraser = new ImageIcon(Child_Design.class.getResource("/EasyBBK_Swing/image/dtext_Hover.png"));
		image_eraser.setImage(image_text.getImage().getScaledInstance(100,50,Image.SCALE_DEFAULT));
		eraser.setIcon(image_eraser);
		eraser.setBounds(226, 125, 100, 50);
		eraser.setName("eraser");
		eraser.setType(2);
		this.add(eraser);
		
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
		
		GetCompListener listener_backbone = new GetCompListener();
		backbone.addMouseListener(listener_backbone);
		backbone.addMouseMotionListener(listener_backbone);
		
		IfDrawLineListener line_1Listener = new IfDrawLineListener();
		line_1.addMouseListener(line_1Listener);
		line_1.addMouseMotionListener(line_1Listener);
		
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
		textButton.addMouseListener(showFont);
		textButton.addMouseMotionListener(showFont);
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
				pen.inUse();
			}						
			else if (((Pen)e.getSource()) != pen & pen==null)
			{
				pen = (Pen)e.getSource();
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
	    			SketchProject project = sketchCenter.currentProject;
	    			
	    			point =  e.getPoint();
		    		BackBone newBackBone = new BackBone(panel,Tpanel);
		    		newBackBone.ID=compCount++;		    			    		
		    		newBackBone.setIcon(((BackBone)source).getIcon());
		    		point.x = point.x - (((BackBone)source).getWidth())/2;
		    		point.y = point.y - (((BackBone)source).getHeight())/2;
		    		
		    		//Location
		    		newBackBone.setBounds(point.x, point.y, ((BackBone)source).getWidth(), 
		    				((BackBone)source).getHeight());
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
		    		newLabel.setIcon(((JLabelWithID)source).getIcon());
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
	 * Delete components
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
