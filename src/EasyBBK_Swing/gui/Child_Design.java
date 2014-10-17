package EasyBBK_Swing.gui;

import EasyBBK_Swing.gui.BackBone;
import EasyBBK_Swing.gui.Child_Design;
import EasyBBK_Swing.gui.DragCompListener;
import EasyBBK_Swing.gui.JLabelWithID;
import EasyBBK_Swing.gui.LinePanel;
import EasyBBK_Swing.gui.Pen;
import EasyBBK_Swing.gui.TPanel;
import EasyBBK_Swing.gui.FontChooser;

import java.awt.AWTEvent;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Toolkit;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.MouseInputListener;
import javax.swing.filechooser.FileFilter;
import javax.swing.text.AttributeSet;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

import data_center.BbkType;
import data_center.SketchCenter;
import data_center.SketchComponent;
import data_center.SketchOperation;
import data_center.SketchProject;

import java.awt.event.AWTEventListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Locale;

@SuppressWarnings("serial")
public class Child_Design extends JLayeredPane {
	public MainPage mainpage;
	JLabel background = new JLabel();
	private JLayeredPane panel = new JLayeredPane();
	private TPanel Tpanel = new TPanel();
	private JTextField statusBar = new JTextField();
	private Boolean choose = new Boolean(false);
	private Object source = null;
	private Pen pen = null;
	private LinePanel linePanel = new LinePanel(pen);
	private TextLabel textLabel = null;
	boolean addText=false;
	private Rectangle outer_rect = new Rectangle();
	
	private ArrayList<Component> totalCompList=new ArrayList<Component>();
	
	private Color lineColor = Color.black;
	private float lineStroke = 3.0f;
	
	private int compCount = 0;
	
	SketchCenter sketchCenter = new SketchCenter();
	
	/**
	 * Create the panel.
	 */
	public Child_Design(MainPage mainpage1) 
	{		
		sketchCenter.newProject();
		
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
		JButton newButton = new JButton();
		JButton openButton = new JButton();
		JButton saveButton = new JButton();
		JButton exportButton = new JButton();
		JButton backout = new JButton();
		JButton forward = new JButton();
		JButton fontButton = new JButton();
		JButton lineButton = new JButton();
		JLabelWithID virus = new JLabelWithID("");
		JLabelWithID ecoil = new JLabelWithID("");
		JButton text = new JButton();
		Pen eraser = new Pen();
		Pen line_inhibit = new Pen();
		Pen line_enhance = new Pen();
		Pen line_other = new Pen();
		
		if (mainpage.small)
		{
			this.setBounds(0, 0, 1277, 670);
		}
		else
		{
			this.setBounds(0, 0, 1366, 670);
		}	
		this.setLayout(null);
        
		newButton.setBounds(9,9,35,35);
		ImageIcon image_newButton = new ImageIcon(Child_Design.class.getResource("/EasyBBK_Swing/image/New_design.png"));
		newButton.setIcon(image_newButton);
		newButton.setToolTipText("Create a new sketch map.");
        background.add(newButton);
        
        openButton.setBounds(56,9,35,35);
		ImageIcon image_openButton = new ImageIcon(Child_Design.class.getResource("/EasyBBK_Swing/image/Open_design.png"));
		openButton.setIcon(image_openButton);
		openButton.setToolTipText("Open a XML file.");
        background.add(openButton);
        
        saveButton.setBounds(103,9,35,35);
		ImageIcon image_saveButton = new ImageIcon(Child_Design.class.getResource("/EasyBBK_Swing/image/Save_design.png"));
		saveButton.setIcon(image_saveButton);
		saveButton.setToolTipText("Save your work as a XML file.");
        background.add(saveButton);
		
        exportButton.setBounds(151,9,35,35);
		ImageIcon image_exportButton = new ImageIcon(Child_Design.class.getResource("/EasyBBK_Swing/image/Export_design.png"));
		exportButton.setIcon(image_exportButton);
		exportButton.setToolTipText("Export your work as a picture.");
        background.add(exportButton);        
		
		backout.setBounds(151,49,35,35);
		ImageIcon image_backout = new ImageIcon(Child_Design.class.getResource("/EasyBBK_Swing/image/Backout.png"));
		backout.setIcon(image_backout);
		backout.setToolTipText("Undo");
        background.add(backout);
		
        forward.setBounds(195,49,35,35);
		ImageIcon image_forward = new ImageIcon(Child_Design.class.getResource("/EasyBBK_Swing/image/Forward.png"));
		forward.setIcon(image_forward);
		forward.setToolTipText("Redo");
        background.add(forward);
        
        fontButton.setBounds(56,49,35,35);
		ImageIcon image_font = new ImageIcon(Child_Design.class.getResource("/EasyBBK_Swing/image/Font.png"));
		fontButton.setIcon(image_font);
		fontButton.setToolTipText("Choose the font of the text label.");
        background.add(fontButton);
        
        ImageIcon image_text = new ImageIcon(Child_Design.class.getResource("/EasyBBK_Swing/image/Text_design.png"));
		text.setIcon(image_text);
		text.setBounds(9, 49, 35, 35);
		text.setName("text");
		text.setToolTipText("text");
		background.add(text);	
		
		ImageIcon image_eraser = new ImageIcon(Child_Design.class.getResource("/EasyBBK_Swing/image/Eraser.png"));
		eraser.setIcon(image_eraser);
		eraser.setBounds(103, 49, 35, 35);
		eraser.setBorder(new LineBorder(Color.GRAY));
		eraser.setName("eraser");
		eraser.setToolTipText("Remove components.");
		eraser.setType(2);
		background.add(eraser);

		ImageIcon image_promoter = new ImageIcon(Child_Design.class.getResource("/EasyBBK_Swing/image/Promoter.png"));
		promoter.setIcon(image_promoter);
		promoter.setBounds(6, 217, 84, 60);
		promoter.setName("promoter");
		promoter.setToolTipText("promoter");
		background.add(promoter);		
		
		ImageIcon image_rbs = new ImageIcon(Child_Design.class.getResource("/EasyBBK_Swing/image/RBS.png"));
		rbs.setIcon(image_rbs);
		rbs.setBounds(100, 217, 84, 60);
		rbs.setName("rbs");
		rbs.setToolTipText("rbs");
		background.add(rbs);		
		
		ImageIcon image_coding = new ImageIcon(Child_Design.class.getResource("/EasyBBK_Swing/image/Coding.png"));
		coding.setIcon(image_coding);
		coding.setBounds(193, 217, 84, 60);
		coding.setName("coding");
		coding.setToolTipText("coding sequence");
		background.add(coding);		
		
		ImageIcon image_terminator = new ImageIcon(Child_Design.class.getResource("/EasyBBK_Swing/image/Terminator.png"));
		terminator.setIcon(image_terminator);
		terminator.setBounds(6, 283, 84, 60);
		terminator.setName("terminator");
		terminator.setToolTipText("terminator");
		background.add(terminator);				
		
		ImageIcon image_primer = new ImageIcon(Child_Design.class.getResource("/EasyBBK_Swing/image/Primer.png"));
		primer.setIcon(image_primer);
		primer.setBounds(100, 283, 84, 60);
		primer.setName("primer");
		primer.setToolTipText("primer");
		background.add(primer);		
		
		ImageIcon image_reporter = new ImageIcon(Child_Design.class.getResource("/EasyBBK_Swing/image/Reporter.png"));
		reporter.setIcon(image_reporter);
		reporter.setBounds(193, 283, 84, 60);
		reporter.setName("reporter");
		reporter.setToolTipText("reporter");
		background.add(reporter);
		
		ImageIcon image_recepter = new ImageIcon(Child_Design.class.getResource("/EasyBBK_Swing/image/Recepter.png"));
		recepter.setIcon(image_recepter);
		recepter.setBounds(6, 349, 84, 60);
		recepter.setName("recepter");
		recepter.setToolTipText("receptor");
		background.add(recepter);
		
		ImageIcon image_factor = new ImageIcon(Child_Design.class.getResource("/EasyBBK_Swing/image/Factor.png"));
		factor.setIcon(image_factor);
		factor.setBounds(100, 349, 84, 60);
		factor.setName("factor");
		factor.setToolTipText("factor");
		background.add(factor);
		
		ImageIcon image_protein = new ImageIcon(Child_Design.class.getResource("/EasyBBK_Swing/image/Protein.png"));
		protein.setIcon(image_protein);
		protein.setBounds(193, 349, 84, 60);
		protein.setName("protein");
		protein.setToolTipText("protein");
		background.add(protein);
		
		ImageIcon image_plasmid = new ImageIcon(Child_Design.class.getResource("/EasyBBK_Swing/image/Plasmid.png"));
		plasmid.setIcon(image_plasmid);
		plasmid.setBounds(6, 415, 84, 60);
		plasmid.setName("plasmid");
		plasmid.setToolTipText("plasmid");
		background.add(plasmid);
		
		ImageIcon image_virus = new ImageIcon(Child_Design.class.getResource("/EasyBBK_Swing/image/Virus.png"));
		virus.setIcon(image_virus);
		virus.setBounds(100, 416, 84, 59);
		virus.setName("virus");
		virus.setToolTipText("virus");
		background.add(virus);
		
		ImageIcon image_ecoil = new ImageIcon(Child_Design.class.getResource("/EasyBBK_Swing/image/Ecoil.png"));
		ecoil.setIcon(image_ecoil);
		ecoil.setBounds(193, 416, 84, 59);
		ecoil.setName("ecoil");
		ecoil.setToolTipText("bacteria");
		background.add(ecoil);
		
		BackBone backbone = new BackBone("", sketchCenter);
		ImageIcon image_backbone = new ImageIcon(Child_Design.class.getResource("/EasyBBK_Swing/image/Backbone.png"));
		backbone.setIcon(image_backbone);
		backbone.setBounds(6, 133, 271, 28);
		backbone.setName("backbone");
		backbone.setToolTipText("backbone");
		background.add(backbone);
		
		lineButton.setBounds(6,596,31,29);
		ImageIcon image_line = new ImageIcon(Child_Design.class.getResource("/EasyBBK_Swing/image/LineStyleChooser.png"));
		lineButton.setIcon(image_line);
		lineButton.setToolTipText("Set the style of the line.");
        background.add(lineButton);
		
		ImageIcon image_line_inhibit = new ImageIcon(Child_Design.class.getResource("/EasyBBK_Swing/image/InhibitLine.png"));
		line_inhibit.setIcon(image_line_inhibit);
		line_inhibit.setBounds(6, 531, 84, 59);
		line_inhibit.setName("line");
		line_inhibit.setType(-1);
		line_inhibit.setToolTipText("line_inhibit");
		background.add(line_inhibit);
		
		ImageIcon image_line_enhance = new ImageIcon(Child_Design.class.getResource("/EasyBBK_Swing/image/EnhanceLine.png"));
		line_enhance.setIcon(image_line_enhance);
		line_enhance.setBounds(100, 531, 84, 59);
		line_enhance.setName("line");
		line_enhance.setType(0);
		line_enhance.setToolTipText("line_enhance");
		background.add(line_enhance);
		
		ImageIcon image_line_other = new ImageIcon(Child_Design.class.getResource("/EasyBBK_Swing/image/OtherLine.png"));
		line_other.setIcon(image_line_other);
		line_other.setBounds(193, 531, 84, 59);
		line_other.setName("line");
		line_other.setToolTipText("line_other");
		line_other.setType(1);
		background.add(line_other);
		
		panel.setLayout(null);
		panel.setOpaque(true);
		panel.setBorder(null);
		panel.setBackground(Color.white);
		
		if (mainpage.small)
		{
			panel.setBounds(283, 0, 997, 625);
		}
		else
		{
			panel.setBounds(283, 0, 1083, 625);
		}			
		background.add(panel);
		
		Tpanel.setLayout(null);
		if (mainpage.small)
		{
			Tpanel.setBounds(0, 0, 997, 625);
		}
		else
		{
			Tpanel.setBounds(0, 0, 1083, 625);
		}
		panel.add(Tpanel,0);
		
		linePanel.setLayout(null);
		if (mainpage.small)
		{
			linePanel.setBounds(0, 0, 997, 625);
		}
		else
		{
			linePanel.setBounds(0, 0, 1083, 625);
		}
		panel.add(linePanel);
		
		if (mainpage.small)
		{
			statusBar.setBounds(283,625,997,45);
		}
		else
		{
			statusBar.setBounds(283,625,1083,45);
		}	
		statusBar.setBorder(BorderFactory.createEtchedBorder());
		statusBar.setBackground(new Color(225,225,225));
		background.add(statusBar);
		
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
		
		GetCompListener listener_virus = new GetCompListener();
		virus.addMouseListener(listener_virus);
		virus.addMouseMotionListener(listener_virus);
		
		GetCompListener listener_ecoil = new GetCompListener();
		ecoil.addMouseListener(listener_ecoil);
		ecoil.addMouseMotionListener(listener_ecoil);
		
		GetCompListener listener_backbone = new GetCompListener();
		backbone.addMouseListener(listener_backbone);
		backbone.addMouseMotionListener(listener_backbone);
		
		IfDrawLineListener line_inhibit_Listener = new IfDrawLineListener();
		line_inhibit.addMouseListener(line_inhibit_Listener);
		line_inhibit.addMouseMotionListener(line_inhibit_Listener);
		
		IfDrawLineListener line_enhance_Listener = new IfDrawLineListener();
		line_enhance.addMouseListener(line_enhance_Listener);
		line_enhance.addMouseMotionListener(line_enhance_Listener);
		
		IfDrawLineListener line_other_Listener = new IfDrawLineListener();
		line_other.addMouseListener(line_other_Listener);
		line_other.addMouseMotionListener(line_other_Listener);
		
		IfDrawLineListener eraserListener = new IfDrawLineListener();
		eraser.addMouseListener(eraserListener);
		eraser.addMouseMotionListener(eraserListener);
		
		addTextListener paddTextlistener = new addTextListener();
		panel.addMouseListener(paddTextlistener);
		panel.addMouseMotionListener(paddTextlistener);
		
		IfaddTextListener ifaddTextListener = new IfaddTextListener();
		text.addMouseListener(ifaddTextListener);
		text.addMouseMotionListener(ifaddTextListener);
		
		DrawCompListener Plistener = new DrawCompListener();
		panel.addMouseListener(Plistener);
		panel.addMouseMotionListener(Plistener);
		
		DrawLineListener drawLineListener = new DrawLineListener();
		panel.addMouseListener(drawLineListener);
		panel.addMouseMotionListener(drawLineListener);	
		
		PanelFocusListener panelFocusListener = new PanelFocusListener();
		panel.addMouseListener(panelFocusListener);
		panel.addMouseMotionListener(panelFocusListener);	
		
		ShowFontChooser showFont = new ShowFontChooser();
		fontButton.addMouseListener(showFont);
		fontButton.addMouseMotionListener(showFont);

		ShowLineStyleChooser showLineStyle = new ShowLineStyleChooser();
		lineButton.addMouseListener(showLineStyle);
		lineButton.addMouseMotionListener(showLineStyle);		
		
		SaveFileListener export = new SaveFileListener(panel);
		exportButton.addMouseListener(export);
		exportButton.addMouseMotionListener(export);
		
		StopDrawListener  exportStop = new StopDrawListener();
		exportButton.addMouseListener(exportStop);
		exportButton.addMouseMotionListener(exportStop);
		
		SaveListener save = new SaveListener(sketchCenter,panel,Tpanel);
		saveButton.addMouseListener(save);
		saveButton.addMouseMotionListener(save);
		
		StopDrawListener  saveStop = new StopDrawListener();
		saveButton.addMouseListener(saveStop);
		saveButton.addMouseMotionListener(saveStop);
		
		NewListener newListener= new NewListener();
		newButton.addMouseListener(newListener);
		newButton.addMouseMotionListener(newListener);
		
		OpenFileListener open = new OpenFileListener();
		openButton.addMouseListener(open);
		openButton.addMouseMotionListener(open);
		
		BackoutListener backoutListerer = new BackoutListener();
		backout.addMouseListener(backoutListerer);
		backout.addMouseMotionListener(backoutListerer);
		
		ForwardListener forwardListener = new ForwardListener();
		forward.addMouseListener(forwardListener);
		forward.addMouseMotionListener(forwardListener);
		
		//background
		this.add(background);
		this.setPosition(background, -1);
		if (mainpage.small)
		{
			background.setBounds(0, 0, 1280, 670);
		}
		else
		{
			background.setBounds(0, 0, 1366, 670);
		}		
		ImageIcon image_background = new ImageIcon(Child_Design.class.getResource("/EasyBBK_Swing/image/design_background.png"));
		background.setIcon(image_background);
		
		//Undo by keyboard
		Toolkit.getDefaultToolkit().addAWTEventListener( 
				new AWTEventListener(){
					 public void eventDispatched(AWTEvent e) {
	                        if(e.getClass() == KeyEvent.class){
	                        	KeyEvent ek = (KeyEvent) e;
	                        	if (ek.getKeyCode()==KeyEvent.VK_Z && ek.isControlDown() 
	                        			&& ek.getID() == KeyEvent.KEY_RELEASED)
	                        	{
	                        		SketchOperation operation = sketchCenter.currentProject.ctrlZ();

	                    			if (operation == null)
	                    			{
	                    				return;
	                    			}

	                    			SketchComponent.Component component;
	                    			if (operation.operationType == SketchOperation.ADD)
	                    			{
	                    				component = (SketchComponent.Component) operation.following;
	                    				addComponent(component);
	                    			}
	                    			else if (operation.operationType == SketchOperation.REMOVE)
	                    			{	
	                    				component = (SketchComponent.Component) operation.previous;
	                    				removeComponent(component);
	                    			}
	                    			else
	                    			{	
	                    				component = sketchCenter.currentProject.findComponentByID(operation.ID);
	                    				if (component == null)
	                    					return;
	                    				else
	                    					modifyComponent(component, operation.attributeType, operation.previous, operation.following);
	                    			}
	                    			
	                    			panel.repaint();
	                        	}
	                       }
					 }
				},  AWTEvent.KEY_EVENT_MASK);		
				
		//Redo by keyboard
		Toolkit.getDefaultToolkit().addAWTEventListener( 
				new AWTEventListener(){
					 public void eventDispatched(AWTEvent e) {
	                        if(e.getClass() == KeyEvent.class){
	                        	KeyEvent ek = (KeyEvent) e;
	                        	if (ek.getKeyCode()==KeyEvent.VK_Y && ek.isControlDown()
	                        			&& ek.getID() == KeyEvent.KEY_RELEASED)
	                        	{
	                        		SketchOperation operation = sketchCenter.currentProject.ctrlY();

	                    			if (operation == null)
	                    				return;
 
	                    			SketchComponent.Component component;
	                    			if (operation.operationType == SketchOperation.ADD)
	                    			{
	                    				component = (SketchComponent.Component) operation.following;
	                    				addComponent(component);
	                    			}
	                    			else if (operation.operationType == SketchOperation.REMOVE)
	                    			{	
	                    				component = (SketchComponent.Component) operation.previous;
	                    				removeComponent(component);
	                    			}
	                    			else
	                    			{	
	                    				component = sketchCenter.currentProject.findComponentByID(operation.ID);
	                    				if (component == null)
	                    					return;
	                    				else
	                    					modifyComponent(component, operation.attributeType, operation.previous, operation.following);
	                    			}
	                    			
	                    			panel.repaint();
	                        	}
	                       }
					 }
				},  AWTEvent.KEY_EVENT_MASK);		
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
							newLine.setName("line");
							totalCompList.add(newLine);
							newLine.ID=compCount++;
							ImageIcon image = new ImageIcon(newimg);
							newLine.setIcon(image);
							newLine.setBounds(linePanel.getLineBorder()[0]-10, linePanel.getLineBorder()[2]-10,
									linePanel.getLineBorder()[1]-linePanel.getLineBorder()[0]+20,
									linePanel.getLineBorder()[3]-linePanel.getLineBorder()[2]+20);
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
							
							Color lineColor = new Color(linePanel.color.getRGB());
							float lineStroke = linePanel.stroke;
							ArrayList<Point> points = new ArrayList<Point>();
							for (Point point : linePanel.lineList)
								points.add(new Point(point));
							
							Rectangle bounds = new Rectangle(newLine.getBounds());
							sketchCenter.currentProject.addComponent(new SketchComponent.Relation
									(newLine.ID, lineType, bounds, points, lineColor, lineStroke));
							
							//imply move
							DragLineListener dragListener = new DragLineListener();
							newLine.addMouseListener(dragListener);
							newLine.addMouseMotionListener(dragListener);
							linePanel.endLine();
							
							//Delete
							DeleteListener deleteLine = new DeleteListener();
							newLine.addMouseListener(deleteLine);
							newLine.addMouseMotionListener(deleteLine);
							
							ShowFocusListener showListener = new ShowFocusListener();
							newLine.addMouseListener(showListener);
							newLine.addMouseMotionListener(showListener);
							
							KeyDeleteListener keyDeleteListener = new KeyDeleteListener();
							newLine.addKeyListener(keyDeleteListener);
	    				}
	    			}
	    			pen = null;
	    		}	
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
	    	addText=false;
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
						newLine.setName("line");
						totalCompList.add(newLine);
						newLine.ID=compCount++;
						ImageIcon image = new ImageIcon(newimg);
						newLine.setIcon(image);
						newLine.setBounds(linePanel.getLineBorder()[0]-10, linePanel.getLineBorder()[2]-10,
								linePanel.getLineBorder()[1]-linePanel.getLineBorder()[0]+20,
								linePanel.getLineBorder()[3]-linePanel.getLineBorder()[2]+20);
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
						
						Color lineColor = new Color(linePanel.color.getRGB());
						float lineStroke = linePanel.stroke;
						ArrayList<Point> points = new ArrayList<Point>();
						for (Point point : linePanel.lineList)
							points.add(new Point(point));
						
						Rectangle bounds = new Rectangle(newLine.getBounds());
						sketchCenter.currentProject.addComponent(new SketchComponent.Relation
								(newLine.ID, lineType, bounds, points, lineColor, lineStroke));
						
						//imply move
						DragLineListener dragListener = new DragLineListener();
						newLine.addMouseListener(dragListener);
						newLine.addMouseMotionListener(dragListener);
						linePanel.endLine();
						
						//Delete
						DeleteListener deleteLine = new DeleteListener();
						newLine.addMouseListener(deleteLine);
						newLine.addMouseMotionListener(deleteLine);
						
						ShowFocusListener showListener = new ShowFocusListener();
						newLine.addMouseListener(showListener);
						newLine.addMouseMotionListener(showListener);
						
						KeyDeleteListener keyDeleteListener = new KeyDeleteListener();
						newLine.addKeyListener(keyDeleteListener);
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
						newLine.setName("line");
						totalCompList.add(newLine);
						newLine.ID=compCount++;
						ImageIcon image = new ImageIcon(newimg);
						newLine.setIcon(image);
						newLine.setBounds(linePanel.getLineBorder()[0]-10, linePanel.getLineBorder()[2]-10,
								linePanel.getLineBorder()[1]-linePanel.getLineBorder()[0]+20,
								linePanel.getLineBorder()[3]-linePanel.getLineBorder()[2]+20);
						
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
						
						Color lineColor = new Color(linePanel.color.getRGB());
						float lineStroke = linePanel.stroke;
						ArrayList<Point> points = new ArrayList<Point>();
						for (Point point : linePanel.lineList)
							points.add(new Point(point));
						
						Rectangle bounds = new Rectangle(newLine.getBounds());
						sketchCenter.currentProject.addComponent(new SketchComponent.Relation
								(newLine.ID, lineType, bounds, points, lineColor, lineStroke));
						
						//imply move
						DragLineListener dragListener = new DragLineListener();
						newLine.addMouseListener(dragListener);
						newLine.addMouseMotionListener(dragListener);
						linePanel.endLine();
						
						//Delete
						DeleteListener deleteLine = new DeleteListener();
						newLine.addMouseListener(deleteLine);
						newLine.addMouseMotionListener(deleteLine);
						
						ShowFocusListener showListener = new ShowFocusListener();
						newLine.addMouseListener(showListener);
						newLine.addMouseMotionListener(showListener);
						
						KeyDeleteListener keyDeleteListener = new KeyDeleteListener();
						newLine.addKeyListener(keyDeleteListener);
    				}
    			}
				pen = null;
				linePanel.setPen(pen);
			}
			addText=false;
		}
		
		public void mousePressed(MouseEvent e) {}

		public void mouseMoved(MouseEvent e) {}

		public void mouseDragged(MouseEvent e) {}
			
		public void mouseReleased(MouseEvent e){}
			
		public void mouseEntered(MouseEvent e) {}

		public void mouseExited(MouseEvent e) {}
	}
	
	/**
	 * If add text
	 */
	public class IfaddTextListener implements MouseInputListener
	{	
		public void mouseClicked(MouseEvent e) 
		{
			if (pen!=null)
			{
				pen.setEnabled(true);
			}
			pen=null;
			if (source!=null)
			{
				((JLabelWithID)source).setEnabled(true);
			}
			source = null;
			choose = false;
			
			addText=true;
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
						newLine.setName("line");
						totalCompList.add(newLine);
						newLine.ID=compCount++;
						ImageIcon image = new ImageIcon(newimg);
						newLine.setIcon(image);
						newLine.setBounds(linePanel.getLineBorder()[0]-10, linePanel.getLineBorder()[2]-10,
								linePanel.getLineBorder()[1]-linePanel.getLineBorder()[0]+20,
								linePanel.getLineBorder()[3]-linePanel.getLineBorder()[2]+20);
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
						
						Color lineColor = new Color(linePanel.color.getRGB());
						float lineStroke = linePanel.stroke;
						ArrayList<Point> points = new ArrayList<Point>();
						for (Point point : linePanel.lineList)
							points.add(new Point(point));
						
						Rectangle bounds = new Rectangle(newLine.getBounds());
						sketchCenter.currentProject.addComponent(new SketchComponent.Relation
								(newLine.ID, lineType, bounds, points, lineColor, lineStroke));
						
						//imply move
						DragLineListener dragListener = new DragLineListener();
						newLine.addMouseListener(dragListener);
						newLine.addMouseMotionListener(dragListener);
						linePanel.endLine();
						
						//Delete
						DeleteListener deleteLine = new DeleteListener();
						newLine.addMouseListener(deleteLine);
						newLine.addMouseMotionListener(deleteLine);
						
						ShowFocusListener showListener = new ShowFocusListener();
						newLine.addMouseListener(showListener);
						newLine.addMouseMotionListener(showListener);
						
						KeyDeleteListener keyDeleteListener = new KeyDeleteListener();
						newLine.addKeyListener(keyDeleteListener);
    				}
    			}
    			pen = null;
    			linePanel.setPen(pen);
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
	 * Draw components
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
		    		BackBone newBackBone = new BackBone(panel,Tpanel, sketchCenter);
		    		totalCompList.add(newBackBone);
		    		newBackBone.ID=compCount++;		
		    		
		    		ImageIcon image_newBackBone = new ImageIcon(Child_Design.class.getResource("/EasyBBK_Swing/image/backbone_move.png"));
		    		newBackBone.setIcon(image_newBackBone);
		    		
		    		point.x = point.x - 41;
		    		point.y = point.y - 30;
		    		
		    		//Location
		    		newBackBone.setBounds(point.x, point.y, 200, 60);
		    		newBackBone.setName("backbone");
		    		newBackBone.activate();
					panel.add(newBackBone,-1);
					
					sketchCenter.currentProject.addComponent
						(new SketchComponent.BackBone(newBackBone.ID, new Rectangle(newBackBone.getBounds())));
					
					DragCompListener listener = new DragCompListener(panel,Tpanel,statusBar,sketchCenter);
					newBackBone.addMouseListener(listener);
					newBackBone.addMouseMotionListener(listener);
					
					//Delete
					DeleteListener deleteBackbone = new DeleteListener();
					newBackBone.addMouseListener(deleteBackbone);
					newBackBone.addMouseMotionListener(deleteBackbone);
					
					IfUploadListener ifUploadListener = new IfUploadListener(sketchCenter,mainpage);
					newBackBone.addMouseListener(ifUploadListener);
					newBackBone.addMouseMotionListener(ifUploadListener);
					
					ShowFocusListener showListener = new ShowFocusListener();
					newBackBone.addMouseListener(showListener);
					newBackBone.addMouseMotionListener(showListener);
					
					KeyDeleteListener keyDeleteListener = new KeyDeleteListener();
					newBackBone.addKeyListener(keyDeleteListener);
					
					ShowBorderListener showBorderListener = new ShowBorderListener();
					newBackBone.addMouseListener(showBorderListener);
					newBackBone.addMouseMotionListener(showBorderListener);
	    		}
	    		else
	    		{
					point =  e.getPoint();
		    		JLabelWithID newLabel = new JLabelWithID();
		    		totalCompList.add(newLabel);
		    		newLabel.ID=compCount++;
		    		
		    		ImageIcon image_newLabel = new ImageIcon(Child_Design.class.getResource("/EasyBBK_Swing/image/"+(((JLabelWithID)source)).getName()+"_move.png"));
		    		newLabel.setIcon(image_newLabel);		    		
		    		
		    		point.x = point.x - (newLabel.getWidth())/2;
		    		point.y = point.y - (newLabel.getHeight())/2;
		    		
		    		//Location
		    		if (((JLabelWithID)source).getName()=="ecoil")
		    		{
		    			newLabel.setBounds(point.x-62, point.y-30, 125,60);
		    			newLabel.setName(((JLabelWithID)source).getName());
		    		}
		    		else if(((JLabelWithID)source).getName()=="virus")
		    		{
		    			newLabel.setBounds(point.x-25, point.y-30, 50,60);
		    			newLabel.setName(((JLabelWithID)source).getName());
		    		}
		    		else
		    		{
		    			newLabel.setBounds(point.x-41, point.y-30, 83,60);
			    		newLabel.setName(((JLabelWithID)source).getName());
		    		}		    		

					panel.add(newLabel);
					
					SketchComponent.Component component = null;
					Rectangle bounds = new Rectangle(newLabel.getBounds());
					String labelName = newLabel.getName();
					if (labelName.equals("promoter"))
						component = new SketchComponent.BioBrick(newLabel.ID, BbkType.Sketch.BioBrick.PROMOTER, bounds, null);
					else if (labelName.equals("rbs"))
						component = new SketchComponent.BioBrick(newLabel.ID, BbkType.Sketch.BioBrick.RBS, bounds, null);
					else if (labelName.equals("coding"))
						component = new SketchComponent.BioBrick(newLabel.ID, BbkType.Sketch.BioBrick.PROTEIN_CODING_SEQUENCE, bounds, null);
					else if (labelName.equals("terminator"))
						component = new SketchComponent.BioBrick(newLabel.ID, BbkType.Sketch.BioBrick.TERMINATOR, bounds, null);
					else if (labelName.equals("primer"))
						component = new SketchComponent.BioBrick(newLabel.ID, BbkType.Sketch.BioBrick.PRIMER, bounds, null);
					else if (labelName.equals("reporter"))
						component = new SketchComponent.BioBrick(newLabel.ID, BbkType.Sketch.BioBrick.REPORTER, bounds, null);
					else if (labelName.equals("factor"))
						component = new SketchComponent.Protein(newLabel.ID, BbkType.Sketch.Protein.FACTOR, bounds, null);
					else if (labelName.equals("recepter"))
						component = new SketchComponent.Protein(newLabel.ID, BbkType.Sketch.Protein.RECEPTER, bounds, null);
					else if (labelName.equals("protein"))
						component = new SketchComponent.Protein(newLabel.ID, BbkType.Sketch.Protein.COMBINED, bounds, null);
					else if (labelName.equals("plasmid"))
						component = new SketchComponent.BioVector(newLabel.ID, BbkType.Sketch.BioVector.PLASMID, bounds);
					else if (labelName.equals("virus"))
						component = new SketchComponent.BioVector(newLabel.ID, BbkType.Sketch.BioVector.VIRUS, bounds);
					else if (labelName.equals("ecoil"))
						component = new SketchComponent.BioVector(newLabel.ID, BbkType.Sketch.BioVector.BACTERIA, bounds);
					else
						component = null;
					
					if (component != null)
						sketchCenter.currentProject.addComponent(component);
					
					DragCompListener listener = new DragCompListener(panel,Tpanel,statusBar,sketchCenter);
					newLabel.addMouseListener(listener);
					newLabel.addMouseMotionListener(listener);
					
					SetBioBrickNameListener setNameListener = new SetBioBrickNameListener(sketchCenter);
					newLabel.addMouseListener(setNameListener);
					newLabel.addMouseMotionListener(setNameListener);
					
					//Delete
					DeleteListener deleteLabel = new DeleteListener();
					newLabel.addMouseListener(deleteLabel);
					newLabel.addMouseMotionListener(deleteLabel);
					
					ShowFocusListener showListener = new ShowFocusListener();
					newLabel.addMouseListener(showListener);
					newLabel.addMouseMotionListener(showListener);
					
					KeyDeleteListener keyDeleteListener = new KeyDeleteListener();
					newLabel.addKeyListener(keyDeleteListener);
					
					ShowBorderListener showBorderListener = new ShowBorderListener();
					newLabel.addMouseListener(showBorderListener);
					newLabel.addMouseMotionListener(showBorderListener);
	    		}
	    	}
	    }
	      
	    public void mouseMoved(MouseEvent e){}
	}
	
	public class addTextListener implements MouseInputListener
	{	
		Point point = new Point(0,0);
		public void mouseClicked(MouseEvent e) 
		{
			if (addText)
	    	{
	    		point =  e.getPoint();
	    		TextLabel newText = new TextLabel(panel,Tpanel, sketchCenter);
	    		newText.setName("text");
	    		point.x = point.x - 30;
	    		point.y = point.y - 15;
	    		newText.setBounds(point.x, point.y, 60,30);
	    		totalCompList.add(newText);
	    		newText.ID=compCount++;	    		
	    		panel.add(newText);
	    		panel.setPosition(newText, 0);
				
	    		ChooseCurrentText chooseTextListener = new ChooseCurrentText();
	    		newText.addFocusListener(chooseTextListener);
	    		
	    		Rectangle bounds = new Rectangle(newText.getBounds());
	    		sketchCenter.currentProject.addComponent
	    			(new SketchComponent.Label(newText.ID, newText.getText(),
	    					bounds, new Font("Arial", Font.PLAIN, 16), Color.BLACK));
	    		
	    		DragTextListener dragListener = new DragTextListener();
	    		newText.addMouseListener(dragListener);
	    		newText.addMouseMotionListener(dragListener);
	    		
	    		//Delete
				DeleteListener deleteText = new DeleteListener();
				newText.addMouseListener(deleteText);
				newText.addMouseMotionListener(deleteText);
				
				ShowFocusListener showListener = new ShowFocusListener();
				newText.addMouseListener(showListener);
				newText.addMouseMotionListener(showListener);
				
				KeyDeleteListener keyDeleteListener = new KeyDeleteListener();
				newText.addKeyListener(keyDeleteListener);
				
				addText=false;
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
	 * Draw line
	 */
	public class DrawLineListener implements MouseInputListener
	{		
		public void mouseClicked(MouseEvent e) 
		{
			if (pen!=null)
			{
				if (pen.ifUse() & (pen.getType()==-1 | pen.getType()==0 | pen.getType()==1))
				{
					linePanel.setPen(pen);
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
						newLine.setName("line");
						totalCompList.add(newLine);
						newLine.ID=compCount++;
						ImageIcon image = new ImageIcon(newimg);
						newLine.setIcon(image);
						newLine.setBounds(linePanel.getLineBorder()[0]-10, linePanel.getLineBorder()[2]-10,
								linePanel.getLineBorder()[1]-linePanel.getLineBorder()[0]+20,
								linePanel.getLineBorder()[3]-linePanel.getLineBorder()[2]+20);
						
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
						Color lineColor = new Color(linePanel.color.getRGB());
						float lineStroke = linePanel.stroke;
						ArrayList<Point> points = new ArrayList<Point>();
						for (Point point : linePanel.lineList)
							points.add(new Point(point));
						
						Rectangle bounds = new Rectangle(newLine.getBounds());
						sketchCenter.currentProject.addComponent(new SketchComponent.Relation
								(newLine.ID, lineType, bounds, points, lineColor, lineStroke));
						
						//imply move
						DragLineListener dragListener = new DragLineListener();
						newLine.addMouseListener(dragListener);
						newLine.addMouseMotionListener(dragListener);
						linePanel.endLine();
						
						//Delete
						DeleteListener deleteLine = new DeleteListener();
						newLine.addMouseListener(deleteLine);
						newLine.addMouseMotionListener(deleteLine);
						
						ShowFocusListener showListener = new ShowFocusListener();
						newLine.addMouseListener(showListener);
						newLine.addMouseMotionListener(showListener);
						
						KeyDeleteListener keyDeleteListener = new KeyDeleteListener();
						newLine.addKeyListener(keyDeleteListener);
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
			stopDrawLine();
			Color col = null;
			Font pFont = null;
			if (textLabel == null)
			{
				col = Color.black;
		        pFont = new Font("Arial",Font.PLAIN,16);
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
	        
	        Font folFont = new Font(font.getFamily(), font.getStyle(), font.getSize());
	        Color folColor = new Color(color.getRGB());
	        if (textLabel != null)
	        {	sketchCenter.currentProject.modifyComponent(textLabel.ID, SketchOperation.TYPE_FONT, folFont);
	        	sketchCenter.currentProject.modifyComponent(textLabel.ID, SketchOperation.TYPE_COLOR, folColor);
	        }
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
	        
	        if (pen != null)
    		{
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
						newLine.setName("line");
						totalCompList.add(newLine);
						newLine.ID=compCount++;
						ImageIcon image = new ImageIcon(newimg);
						newLine.setIcon(image);
						newLine.setBounds(linePanel.getLineBorder()[0]-10, linePanel.getLineBorder()[2]-10,
								linePanel.getLineBorder()[1]-linePanel.getLineBorder()[0]+20,
								linePanel.getLineBorder()[3]-linePanel.getLineBorder()[2]+20);
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
						
						Color lineColor = new Color(linePanel.color.getRGB());
						float lineStroke = linePanel.stroke;
						ArrayList<Point> points = new ArrayList<Point>();
						for (Point point : linePanel.lineList)
							points.add(new Point(point));
						
						Rectangle bounds = new Rectangle(newLine.getBounds());
						sketchCenter.currentProject.addComponent(new SketchComponent.Relation
								(newLine.ID, lineType, bounds, points, lineColor, lineStroke));
						
						//imply move
						DragLineListener dragListener = new DragLineListener();
						newLine.addMouseListener(dragListener);
						newLine.addMouseMotionListener(dragListener);
						linePanel.endLine();
						
						//Delete
						DeleteListener deleteLine = new DeleteListener();
						newLine.addMouseListener(deleteLine);
						newLine.addMouseMotionListener(deleteLine);
						
						ShowFocusListener showListener = new ShowFocusListener();
						newLine.addMouseListener(showListener);
						newLine.addMouseMotionListener(showListener);
						
						KeyDeleteListener keyDeleteListener = new KeyDeleteListener();
						newLine.addKeyListener(keyDeleteListener);
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
			
		public void mouseReleased(MouseEvent e)
		{
			outer_rect=panel.getVisibleRect();
			//delete
			if(!outer_rect.contains((e.getComponent()).getLocation()))
			{
				Component comp = e.getComponent();
				(comp.getParent()).remove(e.getComponent());
				
				SketchProject project = sketchCenter.currentProject;
				int ID = -1;
				
				Component component = e.getComponent();
				
				if ((component.getName()).equals("text"))
					ID = ((TextLabel) component).ID;
				else
					ID = ((JLabelWithID) component).ID;
				project.delComponent(project.findComponentByID(ID));
				
				statusBar.setText(null);
			}
			else
			{
				JLabelWithID lineMoved = (JLabelWithID)e.getComponent();
				Rectangle folBounds = new Rectangle(lineMoved.getBounds());
				sketchCenter.currentProject.modifyComponent
					(lineMoved.ID, SketchOperation.TYPE_BOUNDS, folBounds);
			}
		}
			
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
	 * Drag text
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
			
		public void mouseReleased(MouseEvent e)
		{
			outer_rect=panel.getVisibleRect();
			//delete
			if(!outer_rect.contains((e.getComponent()).getLocation()))
			{
				Component comp = e.getComponent();
				(comp.getParent()).remove(e.getComponent());
				
				SketchProject project = sketchCenter.currentProject;
				int ID = -1;
				
				Component component = e.getComponent();
				
				if ((component.getName()).equals("text"))
					ID = ((TextLabel) component).ID;
				else
					ID = ((JLabelWithID) component).ID;
				project.delComponent(project.findComponentByID(ID));
				
				statusBar.setText(null);
			}
			else
			{
				TextLabel textMoved = (TextLabel)e.getComponent();
				if (textMoved.movable)
				{
					Rectangle folBounds = new Rectangle(textMoved.getBounds());
					sketchCenter.currentProject.modifyComponent
						(textMoved.ID, SketchOperation.TYPE_BOUNDS, folBounds);
				}
			}
		}
			
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
			String content=textLabel.getText();
			sketchCenter.currentProject.modifyComponent(textLabel.ID, 
					SketchOperation.TYPE_STRING, new String(content));
			
			textLabel.focus=false;
			textLabel.setBorder(null);			
		}
	}
	
	class PanelFocusListener implements MouseInputListener
	{		
		public void mouseClicked(MouseEvent e) 
		{
			panel.requestFocus();
		}
		
		public void mousePressed(MouseEvent e) {}

		public void mouseDragged(MouseEvent e) {}
		
		public void mouseMoved(MouseEvent e) 
		{
			panel.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
	    	Tpanel.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
		}
			
		public void mouseReleased(MouseEvent e){}
			
		public void mouseEntered(MouseEvent e) {}

		public void mouseExited(MouseEvent e) {}
	}
	
	/**
	 * Show information in status bar
	 */
	class ShowFocusListener implements MouseInputListener
	{		
		public void mouseClicked(MouseEvent e) 
		{
			(e.getComponent()).requestFocus();
			
			if ((e.getComponent()).getName()!="line")
			{
				if ((e.getComponent()).getName()=="backbone")
				{
					statusBar.setText("     "+(e.getComponent()).getName()+"  "+"Position ("
							+ (e.getComponent()).getX() + " , " + (e.getComponent()).getY() + ")" +
							"  "+"Length: " + (e.getComponent()).getWidth() );
				}
				else if ((e.getComponent()).getName()=="text")
				{
					statusBar.setText("     "+(e.getComponent()).getName()+"  "+"Position ("
							+ (e.getComponent()).getX() + " , " + (e.getComponent()).getY() + ")" +
							"  "+"Length: " + (e.getComponent()).getWidth() +
							"  "+"Height: " + (e.getComponent()).getHeight());
				}
				else
				{
					statusBar.setText("     "+(e.getComponent()).getName()+"  "+"Position ("
							+ (e.getComponent()).getX() + " , " + (e.getComponent()).getY() + ")" );
				}
			}
			else
			{
				statusBar.setText(null);
			}
		}
		
		public void mousePressed(MouseEvent e) {}

		public void mouseDragged(MouseEvent e) 
		{
			if ((e.getComponent()).getName()!="line")
			{
				if ((e.getComponent()).getName()=="backbone")
				{
					statusBar.setText("     "+(e.getComponent()).getName()+"  "+"Position ("
							+ (e.getComponent()).getX() + " , " + (e.getComponent()).getY() + ")" +
							"  "+"Length: " + (e.getComponent()).getWidth() );
				}
				else if ((e.getComponent()).getName()=="text")
				{
					statusBar.setText("     "+(e.getComponent()).getName()+"  "+"Position ("
							+ (e.getComponent()).getX() + " , " + (e.getComponent()).getY() + ")" +
							"  "+"Length: " + (e.getComponent()).getWidth() +
							"  "+"Height: " + (e.getComponent()).getHeight());
				}
				else
				{
					statusBar.setText("     "+(e.getComponent()).getName()+"  "+"Position ("
							+ (e.getComponent()).getX() + " , " + (e.getComponent()).getY() + ")" );
				}
			}
			else
			{
				statusBar.setText(null);
			}
		}
		
		public void mouseMoved(MouseEvent e) 
		{
			if ((e.getComponent()).getName()!="line")
			{
				if ((e.getComponent()).getName()=="backbone")
				{
					statusBar.setText("     "+(e.getComponent()).getName()+"  "+"Position ("
							+ (e.getComponent()).getX() + " , " + (e.getComponent()).getY() + ")" +
							"  "+"Length: " + (e.getComponent()).getWidth() );
				}
				else if ((e.getComponent()).getName()=="text")
				{
					statusBar.setText("     "+(e.getComponent()).getName()+"  "+"Position ("
							+ (e.getComponent()).getX() + " , " + (e.getComponent()).getY() + ")" +
							"  "+"Length: " + (e.getComponent()).getWidth() +
							"  "+"Height: " + (e.getComponent()).getHeight());
				}
				else
				{
					statusBar.setText("     "+(e.getComponent()).getName()+"  "+"Position ("
							+ (e.getComponent()).getX() + " , " + (e.getComponent()).getY() + ")" );
				}
			}
			else
			{
				statusBar.setText(null);
			}
		}
			
		public void mouseReleased(MouseEvent e){}
			
		public void mouseEntered(MouseEvent e) 
		{
			if ((e.getComponent()).getName()!="line")
			{
				if ((e.getComponent()).getName()=="backbone")
				{
					statusBar.setText("     "+(e.getComponent()).getName()+"  "+"Position ("
							+ (e.getComponent()).getX() + " , " + (e.getComponent()).getY() + ")" +
							"  "+"Length: " + (e.getComponent()).getWidth() );
				}
				else if ((e.getComponent()).getName()=="text")
				{
					statusBar.setText("     "+(e.getComponent()).getName()+"  "+"Position ("
							+ (e.getComponent()).getX() + " , " + (e.getComponent()).getY() + ")" +
							"  "+"Length: " + (e.getComponent()).getWidth() +
							"  "+"Height: " + (e.getComponent()).getHeight());
				}
				else
				{
					statusBar.setText("     "+(e.getComponent()).getName()+"  "+"Position ("
							+ (e.getComponent()).getX() + " , " + (e.getComponent()).getY() + ")" );
				}
			}
			else
			{
				statusBar.setText(null);
			}
		}

		public void mouseExited(MouseEvent e) 
		{
			statusBar.setText(null);
		}
	}
	
	/**
	 * Show border
	 */
	class ShowBorderListener implements MouseInputListener
	{		
		public void mouseClicked(MouseEvent e) {}
		
		public void mousePressed(MouseEvent e) {}

		public void mouseDragged(MouseEvent e) {}
		
		public void mouseMoved(MouseEvent e) {}
			
		public void mouseReleased(MouseEvent e){}
			
		public void mouseEntered(MouseEvent e) 
		{
			((JLabelWithID)e.getComponent()).setBorder(new TitledBorder(""));
		}

		public void mouseExited(MouseEvent e) 
		{
			((JLabelWithID)e.getComponent()).setBorder(null);
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
					Component comp = e.getComponent();
					(comp.getParent()).remove(e.getComponent());
					
					SketchProject project = sketchCenter.currentProject;
					int ID = -1;
					
					Component component = e.getComponent();
					
					if ((component.getName()).equals("text"))
						ID = ((TextLabel) component).ID;
					else
						ID = ((JLabelWithID) component).ID;
					project.delComponent(project.findComponentByID(ID));
					
					statusBar.setText(null);
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
	 * Delete component by keyboard
	 */
	class KeyDeleteListener implements KeyListener
	{
		public void keyPressed(KeyEvent e){}
		
		public void keyTyped(KeyEvent e){}
		
		public void keyReleased(KeyEvent e)
		{
			if (e.getKeyCode()==KeyEvent.VK_DELETE)
			{
				Component comp = e.getComponent();
				(comp.getParent()).remove(e.getComponent());
				
				SketchProject project = sketchCenter.currentProject;
				int ID = -1;
				
				Component component = e.getComponent();
				
				if ((component.getName()).equals("text"))
					ID = ((TextLabel) component).ID;
				else
					ID = ((JLabelWithID) component).ID;
				project.delComponent(project.findComponentByID(ID));
				
				statusBar.setText(null);
			}
		}
		
	}
	
	/**
	 * Undo
	 */
	class BackoutListener implements MouseInputListener
	{		
		public void mouseClicked(MouseEvent e) 
		{
			stopDrawLine();
			SketchOperation operation = sketchCenter.currentProject.ctrlZ();

			if (operation == null)
			{
				return;
			}

			SketchComponent.Component component;
			if (operation.operationType == SketchOperation.ADD)
			{
				component = (SketchComponent.Component) operation.following;
				addComponent(component);
			}
			else if (operation.operationType == SketchOperation.REMOVE)
			{	
				component = (SketchComponent.Component) operation.previous;
				removeComponent(component);
			}
			else
			{	
				component = sketchCenter.currentProject.findComponentByID(operation.ID);
				if (component == null)
					return;
				else
					modifyComponent(component, operation.attributeType, operation.previous, operation.following);
			}
			
			panel.repaint();
		}

		public void mousePressed(MouseEvent e) {}

		public void mouseDragged(MouseEvent e) {}
		
		public void mouseMoved(MouseEvent e) {}
			
		public void mouseReleased(MouseEvent e){}
			
		public void mouseEntered(MouseEvent e) {}

		public void mouseExited(MouseEvent e) {}
	}
	
	/**
	 * Redo
	 */
	class ForwardListener implements MouseInputListener
	{		
		public void mouseClicked(MouseEvent e) 
		{
			stopDrawLine();
			SketchOperation operation = sketchCenter.currentProject.ctrlY();

			if (operation == null)
				return;

			SketchComponent.Component component;
			if (operation.operationType == SketchOperation.ADD)
			{
				component = (SketchComponent.Component) operation.following;
				addComponent(component);
			}
			else if (operation.operationType == SketchOperation.REMOVE)
			{	
				component = (SketchComponent.Component) operation.previous;
				removeComponent(component);
			}
			else
			{	
				component = sketchCenter.currentProject.findComponentByID(operation.ID);
				if (component == null)
					return;
				else
					modifyComponent(component, operation.attributeType, operation.previous, operation.following);
			}
			
			panel.repaint();
		}

		public void mousePressed(MouseEvent e) {}

		public void mouseDragged(MouseEvent e) {}
		
		public void mouseMoved(MouseEvent e) {}
			
		public void mouseReleased(MouseEvent e){}
			
		public void mouseEntered(MouseEvent e) {}

		public void mouseExited(MouseEvent e) {}
	}
	
	/**
	 * New (or clear)
	 */
	class NewListener implements MouseInputListener
	{		
		public void mouseClicked(MouseEvent e) 
		{
			stopDrawLine();
			IfSaveDialog one = new IfSaveDialog(sketchCenter);
			if (sketchCenter.currentProject.modified)
			{
				//Open Dialog
				one.showDialog((JFrame)panel.getRootPane().getParent(),500,200);
			}
			
			if (one.ifOperate)
			{
				panel.removeAll();
				panel.repaint();
				panel.add(Tpanel);
				panel.add(linePanel);
				if (pen!=null)
				{
					pen.setEnabled(true);
				}
				pen=null;
				if (source!=null)
				{
					((JLabelWithID)source).setEnabled(true);
				}
				source = null;
				choose = false;
				textLabel=null;
				totalCompList=new ArrayList<Component>();
				lineColor = Color.black;
				lineStroke = 3.0f;
				compCount = 0;	
			}
			
		}

		public void mousePressed(MouseEvent e) {}

		public void mouseDragged(MouseEvent e) {}
		
		public void mouseMoved(MouseEvent e) {}
			
		public void mouseReleased(MouseEvent e){}
			
		public void mouseEntered(MouseEvent e) {}

		public void mouseExited(MouseEvent e) {}
	}
	
	//Open file listener
	public class OpenFileListener implements MouseInputListener
	{
		public File path;
		
		OpenFileListener()
		{	
			super();
			path = new File("");
		}
		
		public boolean validateFileName(String name) {
	        if (name.indexOf('\\') != -1 || name.indexOf('/') != -1 ||
	            name.indexOf(':') != -1 || name.indexOf('*') != -1 ||
	            name.indexOf('?') != -1 || name.indexOf('"') != -1 ||
	            name.indexOf('<') != -1 || name.indexOf('>') != -1 ||
	            name.indexOf('|') != -1) { 

	            return false;
	        } else {
	            return true;
	        }
	    }
		
		public void mouseClicked(MouseEvent e) 
		{
			stopDrawLine();
			// TODO Auto-generated method stub
			if (sketchCenter.currentProject.modified)
			{
				//Open Dialog
				IfSaveDialog one = new IfSaveDialog(sketchCenter);
				one.showDialog((JFrame)panel.getRootPane().getParent(),500,200);
			}
			//Show open dialog
			
				JFileChooser chooser=new JFileChooser();//文件打开对话框
				chooser.setLocale(Locale.ENGLISH);
				chooser.setAcceptAllFileFilterUsed(true);
				chooser.setMultiSelectionEnabled(false);
				try {
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (InstantiationException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IllegalAccessException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (UnsupportedLookAndFeelException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			    SwingUtilities.updateComponentTreeUI(chooser);
			    
				chooser.addChoosableFileFilter(new MyFileFilter("xml","XML"));

				chooser.setCurrentDirectory(new File("."));

				chooser.showOpenDialog(null);

				path = chooser.getSelectedFile();
				
				if (path!=null)
				{
					//clear all
					panel.removeAll();
					panel.repaint();
					panel.add(Tpanel);
					panel.add(linePanel);
					if (pen!=null)
					{
						pen.setEnabled(true);
					}
					pen=null;
					if (source!=null)
					{
						((JLabelWithID)source).setEnabled(true);
					}
					source = null;
					choose = false;
					textLabel=null;
					totalCompList=new ArrayList<Component>();
					lineColor = Color.black;
					lineStroke = 3.0f;
					compCount = 0;
						
					//Begin draw sketch map
					sketchCenter.loadProject(path.toString());
					for (SketchComponent.Component component : sketchCenter.currentProject.componentList)
					{
						compCount=sketchCenter.currentProject.getMaxID()+1;
						addComponent(component);
					}						
				}			
		}

		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		public void mouseDragged(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		public void mouseMoved(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
		
		@SuppressWarnings("unused")
		class MyFileFilter extends FileFilter {  
			  
		    private String TYPE_UNKNOWN = "Type   Unknown ";  
		    private String HIDDEN_FILE = "Hidden   File ";  
		    private Hashtable<String, MyFileFilter> filters = null;  
		    private String description = null;  
		    private String fullDescription = null;  
		    private boolean useExtensionsInDescription = true;  
		  
		    public MyFileFilter() {  
		        this.filters = new Hashtable<String, MyFileFilter>();  
		    }  
		  
		    public MyFileFilter(String extension) {  
		        this(extension, null);  
		    }  
		  
		    public MyFileFilter(String extension, String description) {  
		        this();  
		        if (extension != null) {  
		            addExtension(extension);  
		        }  
		        if (description != null) {  
		            setDescription(description);  
		        }  
		    }  
		  
		    public MyFileFilter(String[] filters) {  
		        this(filters, null);  
		    }  
		  
		    public MyFileFilter(String[] filters, String description) {  
		        this();  
		        for (int i = 0; i < filters.length; i++) {  
		            //   add   filters   one   by   one   
		            addExtension(filters[i]);  
		        }  
		        if (description != null) {  
		            setDescription(description);  
		        }  
		    }  
		  
		    public boolean accept(File f) {  
		        if (f != null) {  
		            String extension = getExtension(f);  
		            if (extension != null && f.getName().toUpperCase().endsWith(".XML")) {  
		                return true;  
		            };  
		        }  
		        return false;  
		    }  
		  
		    public String getExtension(File f) {  
		        if (f != null) {  
		            String filename = f.getName();  
		            int i = filename.lastIndexOf('.');  
		            if (i > 0 && i < filename.length() - 1) {  
		                return filename.substring(i + 1).toLowerCase();  
		            };  
		        }  
		        return null;  
		    }  
		  
		    public void addExtension(String extension) {  
		        if (filters == null) {  
		            filters = new Hashtable<String, MyFileFilter>(5);  
		        }  
		        filters.put(extension.toLowerCase(), this);  
		        fullDescription = null;  
		    }  
		  
		    public String getDescription() {  
		        if (fullDescription == null) {  
		            if (description == null || isExtensionListInDescription()) {  
		                fullDescription = description == null ? "(" : description + "(";  
		//   build   the   description   from   the   extension   list   
		                Enumeration<String> extensions = filters.keys();  
		                if (extensions != null) {  
		                    fullDescription += "." + (String) extensions.nextElement();  
		                    while (extensions.hasMoreElements()) {  
		                        fullDescription += "," + (String) extensions.nextElement();  
		                    }  
		                }  
		                fullDescription += ")";  
		            } else {  
		                fullDescription = description;  
		            }  
		        }  
		        return fullDescription;  
		    }  
		  
		    public void setDescription(String description) {  
		        this.description = description;  
		        fullDescription = null;  
		    }  
		  
		    public void setExtensionListInDescription(boolean b) {  
		        useExtensionsInDescription = b;  
		        fullDescription = null;  
		    }  
		  
		    public boolean isExtensionListInDescription() {  
		        return useExtensionsInDescription;  
		    }    
		}  
	}
	
	class StopDrawListener implements MouseInputListener
	{		
		public void mouseClicked(MouseEvent e) 
		{
			stopDrawLine();
		}
		
		public void mousePressed(MouseEvent e) {}

		public void mouseDragged(MouseEvent e) {}
		
		public void mouseMoved(MouseEvent e) {}
			
		public void mouseReleased(MouseEvent e){}
			
		public void mouseEntered(MouseEvent e) {}

		public void mouseExited(MouseEvent e) {}
	}
	
	@SuppressWarnings("unchecked")
	public void addComponent(SketchComponent.Component component)
	{
		String primaryType = component.primaryType;
		if (primaryType.equals(SketchComponent.Label.class.getSimpleName()))
		{	
			SketchComponent.Label label = component.toLabel();
			
			Color color=new Color(label.color.getRGB());
			
			TextLabel newText = new TextLabel(panel,Tpanel, sketchCenter);
			
	        SimpleAttributeSet attrNew = new SimpleAttributeSet();
	        StyleConstants.setAlignment(attrNew, StyleConstants.ALIGN_CENTER);
	        StyleConstants.setForeground(attrNew, color);
	        StyleConstants.setFontSize(attrNew, (label.font).getSize());
	        StyleConstants.setFontFamily(attrNew, (label.font).getFamily());
	        if ((label.font).getStyle()==Font.PLAIN) 
            {  
	        	StyleConstants.setItalic(attrNew, false); 
	        	StyleConstants.setBold(attrNew, false);
            }  
	        else if ((label.font).getStyle()==Font.ITALIC) 
            {  
            	StyleConstants.setItalic(attrNew, true); 
            	StyleConstants.setBold(attrNew, false);       	
            }  
	        else if ((label.font).getStyle()==Font.BOLD) 
            {  
            	StyleConstants.setBold(attrNew, true);  
            	StyleConstants.setItalic(attrNew, false);
            }  
	        else 
            {   //No ItalicBold in font
            	StyleConstants.setBold(attrNew, true); 
            	StyleConstants.setItalic(attrNew, true);  
            }
	        
	        newText.setCharacterAttributes(attrNew, true);
	        newText.setParagraphAttributes(attrNew, true);
			newText.setBounds(label.bounds);
			newText.setText(label.text);
			newText.ID=label.ID;
			
			if (totalCompList.size()<(newText.ID+1))
			{
				
				ArrayList<Component> temptotalCompList = new ArrayList<Component>();
				temptotalCompList = (ArrayList<Component>) totalCompList.clone();
				for (int i=0;i<temptotalCompList.size();i++)
				{					
					totalCompList.set(i, temptotalCompList.get(i));
				}
				
				for (int i2=0;i2<newText.ID-temptotalCompList.size();i2++)
				{					
					totalCompList.add(null);
				}
				totalCompList.add(newText);
			}
			else
			{
				totalCompList.set(newText.ID, newText);
			}

			panel.add(newText);
			
			DragTextListener dragListener = new DragTextListener();
    		newText.addMouseListener(dragListener);
    		newText.addMouseMotionListener(dragListener);
    		
    		//Delete
			DeleteListener deleteText = new DeleteListener();
			newText.addMouseListener(deleteText);
			newText.addMouseMotionListener(deleteText);
			
			ShowFocusListener showListener = new ShowFocusListener();
			newText.addMouseListener(showListener);
			newText.addMouseMotionListener(showListener);
			
			KeyDeleteListener keyDeleteListener = new KeyDeleteListener();
			newText.addKeyListener(keyDeleteListener);
		}
		else if (primaryType.equals(SketchComponent.BioBrick.class.getSimpleName()))
		{
			SketchComponent.BioBrick biobrick = component.toBioBrick();
			String GUITypeName = null;
			switch (biobrick.secondaryType)
			{
				case BbkType.Sketch.BioBrick.PRIMER:
					GUITypeName = "primer";	break;
				case BbkType.Sketch.BioBrick.PROMOTER:
					GUITypeName = "promoter";	break;
				case BbkType.Sketch.BioBrick.PROTEIN_CODING_SEQUENCE:
					GUITypeName = "coding";	break;
				case BbkType.Sketch.BioBrick.RBS:
					GUITypeName = "rbs";	break;
				case BbkType.Sketch.BioBrick.REPORTER: 
					GUITypeName = "reporter";	break;
				case BbkType.Sketch.BioBrick.TERMINATOR:
					GUITypeName = "terminator";	break;
			}
			JLabelWithID newLabel = new JLabelWithID();
			
			newLabel.ID=biobrick.ID;
			
			if (totalCompList.size()<(newLabel.ID+1))
			{
				
				ArrayList<Component> temptotalCompList = new ArrayList<Component>();
				temptotalCompList = (ArrayList<Component>) totalCompList.clone();
				for (int i=0;i<temptotalCompList.size();i++)
				{					
					totalCompList.set(i, temptotalCompList.get(i));
				}
				
				for (int i2=0;i2<newLabel.ID-temptotalCompList.size();i2++)
				{					
					totalCompList.add(null);
				}
				totalCompList.add(newLabel);
			}
			else
			{
				totalCompList.set(newLabel.ID, newLabel);
			}	
			
    		ImageIcon image_newLabel = new ImageIcon(Child_Design.class.getResource("/EasyBBK_Swing/image/"+GUITypeName+"_move.png"));
    		newLabel.setIcon(image_newLabel);
    		newLabel.setBounds(biobrick.bounds);
    		newLabel.setName(GUITypeName);
			panel.add(newLabel);
    		
			DragCompListener listener = new DragCompListener(panel,Tpanel,statusBar,sketchCenter);
			newLabel.addMouseListener(listener);
			newLabel.addMouseMotionListener(listener);
			
			SetBioBrickNameListener setNameListener = new SetBioBrickNameListener(sketchCenter);
			newLabel.addMouseListener(setNameListener);
			newLabel.addMouseMotionListener(setNameListener);
			
			//Delete
			DeleteListener deleteLabel = new DeleteListener();
			newLabel.addMouseListener(deleteLabel);
			newLabel.addMouseMotionListener(deleteLabel);
			
			ShowFocusListener showListener = new ShowFocusListener();
			newLabel.addMouseListener(showListener);
			newLabel.addMouseMotionListener(showListener);
			
			KeyDeleteListener keyDeleteListener = new KeyDeleteListener();
			newLabel.addKeyListener(keyDeleteListener);
			
			ShowBorderListener showBorderListener = new ShowBorderListener();
			newLabel.addMouseListener(showBorderListener);
			newLabel.addMouseMotionListener(showBorderListener);
			
			panel.setPosition(newLabel, 0);
			panel.repaint();
		}
		else if (primaryType.equals(SketchComponent.Protein.class.getSimpleName()))
		{
			SketchComponent.Protein protein = component.toProtein();
			
			JLabelWithID newLabel = new JLabelWithID();
			newLabel.ID=protein.ID; 
			if (totalCompList.size()<(newLabel.ID+1))
			{
				
				ArrayList<Component> temptotalCompList = new ArrayList<Component>();
				temptotalCompList = (ArrayList<Component>) totalCompList.clone();
				for (int i=0;i<temptotalCompList.size();i++)
				{					
					totalCompList.set(i, temptotalCompList.get(i));
				}
				
				for (int i2=0;i2<newLabel.ID-temptotalCompList.size();i2++)
				{					
					totalCompList.add(null);
				}
				totalCompList.add(newLabel);
			}
			else
			{
				totalCompList.set(newLabel.ID, newLabel);
			}
			String GUITypeName=null;
			
			switch (protein.secondaryType)
			{
				case BbkType.Sketch.Protein.FACTOR:
					GUITypeName = "factor";	break;
				case BbkType.Sketch.Protein.RECEPTER:
					GUITypeName = "recepter";	break;
				case BbkType.Sketch.Protein.COMBINED:
					GUITypeName = "protein";	break;
			}
			ImageIcon image_newLabel = new ImageIcon(Child_Design.class.getResource("/EasyBBK_Swing/image/"+GUITypeName+"_move.png"));
    		newLabel.setIcon(image_newLabel);    		
    		newLabel.setName(GUITypeName);
			newLabel.setBounds(protein.bounds);
			panel.add(newLabel);
			
			DragCompListener listener = new DragCompListener(panel,Tpanel,statusBar,sketchCenter);
			newLabel.addMouseListener(listener);
			newLabel.addMouseMotionListener(listener);
			
			SetBioBrickNameListener setNameListener = new SetBioBrickNameListener(sketchCenter);
			newLabel.addMouseListener(setNameListener);
			newLabel.addMouseMotionListener(setNameListener);
			
			//Delete
			DeleteListener deleteLabel = new DeleteListener();
			newLabel.addMouseListener(deleteLabel);
			newLabel.addMouseMotionListener(deleteLabel);
			
			ShowFocusListener showListener = new ShowFocusListener();
			newLabel.addMouseListener(showListener);
			newLabel.addMouseMotionListener(showListener);
			
			KeyDeleteListener keyDeleteListener = new KeyDeleteListener();
			newLabel.addKeyListener(keyDeleteListener);
			
			ShowBorderListener showBorderListener = new ShowBorderListener();
			newLabel.addMouseListener(showBorderListener);
			newLabel.addMouseMotionListener(showBorderListener);
		}
		else if (primaryType.equals(SketchComponent.BackBone.class.getSimpleName()))
		{
			SketchComponent.BackBone backbone = component.toBackBone();

    		BackBone newBackBone = new BackBone(panel,Tpanel, sketchCenter);
    		
    		newBackBone.ID = backbone.ID;	
    		
    		if (totalCompList.size()<(newBackBone.ID+1))
			{
				
				ArrayList<Component> temptotalCompList = new ArrayList<Component>();
				temptotalCompList = (ArrayList<Component>) totalCompList.clone();
				for (int i=0;i<temptotalCompList.size();i++)
				{					
					totalCompList.set(i, temptotalCompList.get(i));
				}
				
				for (int i2=0;i2<newBackBone.ID-temptotalCompList.size();i2++)
				{					
					totalCompList.add(null);
				}
				totalCompList.add(newBackBone);
			}
			else
			{
				totalCompList.set(newBackBone.ID, newBackBone);
			}
    		
    		ImageIcon image_newBackBone = new ImageIcon(Child_Design.class.getResource("/EasyBBK_Swing/image/backbone_move.png"));
    		image_newBackBone.setImage(image_newBackBone.getImage().getScaledInstance(((int)(backbone.bounds).getWidth()),60,Image.SCALE_DEFAULT));
    		newBackBone.setIcon(image_newBackBone);
    		newBackBone.setBounds(backbone.bounds);
    		newBackBone.setName("backbone");
    		newBackBone.activate();
    		panel.add(newBackBone);
			
			DragCompListener listener = new DragCompListener(panel,Tpanel,statusBar,sketchCenter);
			newBackBone.addMouseListener(listener);
			newBackBone.addMouseMotionListener(listener);
			
			//Delete
			DeleteListener deleteBackbone = new DeleteListener();
			newBackBone.addMouseListener(deleteBackbone);
			newBackBone.addMouseMotionListener(deleteBackbone);
			
			IfUploadListener ifUploadListener = new IfUploadListener(sketchCenter,mainpage);
			newBackBone.addMouseListener(ifUploadListener);
			newBackBone.addMouseMotionListener(ifUploadListener);
			
			ShowFocusListener showListener = new ShowFocusListener();
			newBackBone.addMouseListener(showListener);
			newBackBone.addMouseMotionListener(showListener);
			
			KeyDeleteListener keyDeleteListener = new KeyDeleteListener();
			newBackBone.addKeyListener(keyDeleteListener);
			
			ShowBorderListener showBorderListener = new ShowBorderListener();
			newBackBone.addMouseListener(showBorderListener);
			newBackBone.addMouseMotionListener(showBorderListener);
			
			panel.setPosition(newBackBone, -1);
			panel.repaint();
		}
		else if (primaryType.equals(SketchComponent.Relation.class.getSimpleName()))
		{
			SketchComponent.Relation relation = component.toRelation();
			int GUIRelationType=-2;
			switch (relation.secondaryType)
			{	
				case BbkType.Sketch.Relation.PROMOTE:
					GUIRelationType = LinePanel.LINE_WITH_EMPTY_ARROW;	break;
				case BbkType.Sketch.Relation.SUPPRESS:
					GUIRelationType = LinePanel.LINE_WIHT_STOP_END;	break;
				case BbkType.Sketch.Relation.OTHER:
					GUIRelationType = LinePanel.LINE_WITH_FULL_ARROW;	break;
			}
			
			Color previousColor = new Color(linePanel.color.getRGB());
			float previousStroke = linePanel.stroke;
			
			linePanel.lineList = (ArrayList<Point>) relation.posList.clone();
			linePanel.setPenValue(GUIRelationType);
			linePanel.color = new Color(relation.color.getRGB());
			linePanel.stroke = (float)relation.thickness;
			linePanel.repaint();
			
			BufferedImage img = new BufferedImage(panel.getWidth(),panel.getHeight(),BufferedImage.TRANSLUCENT);
			Graphics2D g = (Graphics2D)img.getGraphics();
			linePanel.paint(g);
			
			BufferedImage newimg = img.getSubimage(linePanel.getLineBorder()[0]-10, linePanel.getLineBorder()[2]-10,
					linePanel.getLineBorder()[1]-linePanel.getLineBorder()[0]+20,
					linePanel.getLineBorder()[3]-linePanel.getLineBorder()[2]+20);
			
			JLabelWithID newLine = new JLabelWithID();
			newLine.setName("line");
			linePanel.setPen(null);
			newLine.ID=relation.ID;
			
			if (totalCompList.size()<(newLine.ID+1))
			{
				
				ArrayList<Component> temptotalCompList = new ArrayList<Component>();
				temptotalCompList = (ArrayList<Component>) totalCompList.clone();
				for (int i=0;i<temptotalCompList.size();i++)
				{					
					totalCompList.set(i, temptotalCompList.get(i));
				}
				
				for (int i2=0;i2<newLine.ID-temptotalCompList.size();i2++)
				{					
					totalCompList.add(null);
				}
				totalCompList.add(newLine);
			}
			else
			{
				totalCompList.set(newLine.ID, newLine);
			}
			
			ImageIcon image = new ImageIcon(newimg);
			newLine.setIcon(image);
			newLine.setBounds(linePanel.getLineBorder()[0]-10, linePanel.getLineBorder()[2]-10,
					linePanel.getLineBorder()[1]-linePanel.getLineBorder()[0]+20,
					linePanel.getLineBorder()[3]-linePanel.getLineBorder()[2]+20);
			panel.add(newLine);
			
			linePanel.color = new Color(previousColor.getRGB());
			linePanel.stroke = previousStroke;
			
			//imply move
			DragLineListener dragListener = new DragLineListener();
			newLine.addMouseListener(dragListener);
			newLine.addMouseMotionListener(dragListener);
			linePanel.endLine();
			
			//Delete
			DeleteListener deleteLine = new DeleteListener();
			newLine.addMouseListener(deleteLine);
			newLine.addMouseMotionListener(deleteLine);
			
			ShowFocusListener showListener = new ShowFocusListener();
			newLine.addMouseListener(showListener);
			newLine.addMouseMotionListener(showListener);
			
			KeyDeleteListener keyDeleteListener = new KeyDeleteListener();
			newLine.addKeyListener(keyDeleteListener);
		}
		else if (primaryType.equals(SketchComponent.BioVector.class.getSimpleName()))
		{
			SketchComponent.BioVector bioVector = component.toBioVictor();
			String GUIBioVectorType = null;
			switch (bioVector.secondaryType)
			{	
				case BbkType.Sketch.BioVector.PLASMID:
					GUIBioVectorType = "plasmid";	break;
				case BbkType.Sketch.BioVector.VIRUS:
					GUIBioVectorType = "virus";	break;
				case BbkType.Sketch.BioVector.BACTERIA:
					GUIBioVectorType = "ecoil";	break;
			}
			
			JLabelWithID newLabel = new JLabelWithID();
    		newLabel.ID=bioVector.ID;
    		
    		if (totalCompList.size()<(newLabel.ID+1))
			{
				ArrayList<Component> temptotalCompList = new ArrayList<Component>();
				temptotalCompList = (ArrayList<Component>) totalCompList.clone();
				for (int i=0;i<temptotalCompList.size();i++)
				{					
					totalCompList.set(i, temptotalCompList.get(i));
				}
				
				for (int i2=0;i2<newLabel.ID-temptotalCompList.size();i2++)
				{					
					totalCompList.add(null);
				}
				totalCompList.add(newLabel);
			}
			else
			{
				totalCompList.set(newLabel.ID, newLabel);
			}
    		
    		totalCompList.set(newLabel.ID, newLabel);
    		newLabel.setBounds(bioVector.bounds);	    		
			ImageIcon image_newLabel = new ImageIcon(Child_Design.class.getResource("/EasyBBK_Swing/image/"+GUIBioVectorType+"_move.png"));
    		newLabel.setIcon(image_newLabel);
    		newLabel.setName(GUIBioVectorType);
			panel.add(newLabel);
			
			DragCompListener listener = new DragCompListener(panel,Tpanel,statusBar,sketchCenter);
			newLabel.addMouseListener(listener);
			newLabel.addMouseMotionListener(listener);
			
			SetBioBrickNameListener setNameListener = new SetBioBrickNameListener(sketchCenter);
			newLabel.addMouseListener(setNameListener);
			newLabel.addMouseMotionListener(setNameListener);
			
			//Delete
			DeleteListener deleteLabel = new DeleteListener();
			newLabel.addMouseListener(deleteLabel);
			newLabel.addMouseMotionListener(deleteLabel);
			
			ShowFocusListener showListener = new ShowFocusListener();
			newLabel.addMouseListener(showListener);
			newLabel.addMouseMotionListener(showListener);
			
			KeyDeleteListener keyDeleteListener = new KeyDeleteListener();
			newLabel.addKeyListener(keyDeleteListener);
			
			ShowBorderListener showBorderListener = new ShowBorderListener();
			newLabel.addMouseListener(showBorderListener);
			newLabel.addMouseMotionListener(showBorderListener);
		}
		else
			return;		
	}
	
	public void removeComponent(SketchComponent.Component component)
	{	
		int ID = component.ID;
		Component comp = totalCompList.get(ID);
		(comp.getParent()).remove(comp);
	}
	
	public void modifyComponent(SketchComponent.Component component, int attributeType, Object previous, Object following)
	{
		String primaryType = component.primaryType;
		
		if (primaryType.equals(SketchComponent.BioBrick.class.getSimpleName()) || 
			primaryType.equals(SketchComponent.Protein.class.getSimpleName()) ||
			primaryType.equals(SketchComponent.Relation.class.getSimpleName()) ||
			primaryType.equals(SketchComponent.BioVector.class.getSimpleName()))
		{	if (attributeType == SketchOperation.TYPE_BOUNDS)
			{	
				Rectangle bounds = (Rectangle) following;
				int ID = component.ID;
				Component comp = totalCompList.get(ID);
				comp.setBounds(bounds);
			}
			else
				return;
		}
		else if (primaryType.equals(SketchComponent.BackBone.class.getSimpleName()))
		{	if (attributeType == SketchOperation.TYPE_BOUNDS)
			{	
				Rectangle bounds = (Rectangle) following;
				int ID = component.ID;
				
				if (isMoveEvent(previous, following))	// linkage
				{	
					Component comp = totalCompList.get(ID);
					Rectangle previousBounds = comp.getBounds();
					comp.setBounds(bounds);
					
					SketchComponent.BackBone backbone = component.toBackBone();
					ArrayList<Integer> compIndexList = backbone.bbkChildren;
					if (compIndexList.size()>0)
					{
						for (int i=0;i<compIndexList.size();i++)
						{
							int compID = compIndexList.get(i);
							Component sequence = totalCompList.get(compID);
							Rectangle compBounds = new Rectangle(sequence.getX()+(comp.getBounds().x-previousBounds.x),
									sequence.getY()+(comp.getBounds().y-previousBounds.y),
									sequence.getWidth(),sequence.getHeight());
							sequence.setBounds(compBounds);
						}
					}
				}
				else	// no linkage
				{	
					Component comp = totalCompList.get(ID);
					comp.setBounds(bounds);
				}				
			}
			else
				return;
		}
		else if (primaryType.equals(SketchComponent.Label.class.getSimpleName()))
		{	
			if (attributeType == SketchOperation.TYPE_BOUNDS)
			{	
				Rectangle bounds = (Rectangle) following;
				int ID = component.ID;
				Component comp = totalCompList.get(ID);
				comp.setBounds(bounds);
			}
			else if (attributeType == SketchOperation.TYPE_FONT)
			{	
				Font font = (Font) following;				
				Color color = component.toLabel().color;
				int ID = component.ID;
				Component comp = totalCompList.get(ID);
				
		        SimpleAttributeSet attrNew = new SimpleAttributeSet();
		        StyleConstants.setAlignment(attrNew, StyleConstants.ALIGN_CENTER);
		        StyleConstants.setForeground(attrNew, color);
		        StyleConstants.setFontSize(attrNew, font.getSize());
		        StyleConstants.setFontFamily(attrNew, font.getFamily());
		        if (font.getStyle()==Font.PLAIN) 
	            {  
		        	StyleConstants.setItalic(attrNew, false); 
		        	StyleConstants.setBold(attrNew, false);
	            }  
		        else if (font.getStyle()==Font.ITALIC) 
	            {  
	            	StyleConstants.setItalic(attrNew, true); 
	            	StyleConstants.setBold(attrNew, false);       	
	            }  
		        else if (font.getStyle()==Font.BOLD) 
	            {  
	            	StyleConstants.setBold(attrNew, true);  
	            	StyleConstants.setItalic(attrNew, false);
	            }  
		        else 
	            {   //No ItalicBold in font
	            	StyleConstants.setBold(attrNew, true); 
	            	StyleConstants.setItalic(attrNew, true);  
	            }
		        
		        ((TextLabel)comp).setCharacterAttributes(attrNew, true);
		        ((TextLabel)comp).setParagraphAttributes(attrNew, true);				
			}
			else if (attributeType == SketchOperation.TYPE_COLOR)
			{	
				Font font = component.toLabel().font;				
				Color color = (Color) following;
				int ID = component.ID;
				Component comp = totalCompList.get(ID);
				
		        SimpleAttributeSet attrNew = new SimpleAttributeSet();
		        StyleConstants.setAlignment(attrNew, StyleConstants.ALIGN_CENTER);
		        StyleConstants.setForeground(attrNew, color);
		        StyleConstants.setFontSize(attrNew, font.getSize());
		        StyleConstants.setFontFamily(attrNew, font.getFamily());
		        if (font.getStyle()==Font.PLAIN) 
	            {  
		        	StyleConstants.setItalic(attrNew, false); 
		        	StyleConstants.setBold(attrNew, false);
	            }  
		        else if (font.getStyle()==Font.ITALIC) 
	            {  
	            	StyleConstants.setItalic(attrNew, true); 
	            	StyleConstants.setBold(attrNew, false);       	
	            }  
		        else if (font.getStyle()==Font.BOLD) 
	            {  
	            	StyleConstants.setBold(attrNew, true);  
	            	StyleConstants.setItalic(attrNew, false);
	            }  
		        else 
	            {   //No ItalicBold in font
	            	StyleConstants.setBold(attrNew, true); 
	            	StyleConstants.setItalic(attrNew, true);  
	            }
		        
		        ((TextLabel)comp).setCharacterAttributes(attrNew, true);
		        ((TextLabel)comp).setParagraphAttributes(attrNew, true);

			}
			else if (attributeType == SketchOperation.TYPE_STRING)
			{	int ID = component.ID;
				String str = (String) following;
				
				Component comp = totalCompList.get(ID);
				((TextLabel)comp).setText(str);
			}
			else
				return;
		}
		else
			return;
	}
	
	public void stopDrawLine()
	{
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
						newLine.setName("line");
						totalCompList.add(newLine);
						newLine.ID=compCount++;
						ImageIcon image = new ImageIcon(newimg);
						newLine.setIcon(image);
						newLine.setBounds(linePanel.getLineBorder()[0]-10, linePanel.getLineBorder()[2]-10,
								linePanel.getLineBorder()[1]-linePanel.getLineBorder()[0]+20,
								linePanel.getLineBorder()[3]-linePanel.getLineBorder()[2]+20);
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
						
						Color lineColor = new Color(linePanel.color.getRGB());
						float lineStroke = linePanel.stroke;
						ArrayList<Point> points = new ArrayList<Point>();
						for (Point point : linePanel.lineList)
							points.add(new Point(point));
						
						Rectangle bounds = new Rectangle(newLine.getBounds());
						sketchCenter.currentProject.addComponent(new SketchComponent.Relation
								(newLine.ID, lineType, bounds, points, lineColor, lineStroke));
						
						//imply move
						DragLineListener dragListener = new DragLineListener();
						newLine.addMouseListener(dragListener);
						newLine.addMouseMotionListener(dragListener);
						linePanel.endLine();
						
						//Delete
						DeleteListener deleteLine = new DeleteListener();
						newLine.addMouseListener(deleteLine);
						newLine.addMouseMotionListener(deleteLine);
						
						ShowFocusListener showListener = new ShowFocusListener();
						newLine.addMouseListener(showListener);
						newLine.addMouseMotionListener(showListener);
						
						KeyDeleteListener keyDeleteListener = new KeyDeleteListener();
						newLine.addKeyListener(keyDeleteListener);
 				}
 			}
 			pen=null;
 			linePanel.setPen(pen);
 		}
	}

	/** Judge if the modify contains a move event by the size of the component. 
	 * If the size has not changed(and the position has changed, namely), 
	 * it should be a move event. Otherwise it is the user dragging the edge 
	 * of the backbone to extend or shrink it, not contains a linkage event.  */
	private boolean isMoveEvent(Object previous, Object following)
	{	
		Rectangle preBounds = (Rectangle) previous;
		Rectangle folBounds = (Rectangle) following;
		return !(preBounds.width - folBounds.width == 0 && 
				 preBounds.height - folBounds.height == 0);
	}
}
