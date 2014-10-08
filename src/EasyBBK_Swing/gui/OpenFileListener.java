package EasyBBK_Swing.gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Locale;

import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLayeredPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.event.MouseInputListener;
import javax.swing.filechooser.FileFilter;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;

import EasyBBK_Swing.gui.SaveListener.MyFileFilter;
import data_center.BbkType;
import data_center.SketchCenter;
import data_center.SketchComponent;
import data_center.SketchProject;

public class OpenFileListener implements MouseInputListener
{
	public File path;
	SketchCenter sketchCenter;
	
	JLayeredPane panel=null;
	TPanel Tpanel=null;
	LinePanel linePanel=null;
	
	OpenFileListener(SketchCenter sketchCenter,JLayeredPane panel,TPanel Tpanel,LinePanel linePanel)
	{	
		super();
		this.sketchCenter = sketchCenter;
		this.panel=panel;
		this.Tpanel=Tpanel;
		this.linePanel=linePanel;
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
		// TODO Auto-generated method stub
		
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
		
		//Begin draw sketch map
		sketchCenter.loadProject(path.toString());
		for (SketchComponent.Component component : sketchCenter.currentProject.componentList)
		{	
			addComponent(component);
		}
		System.out.print(path);	
		
		
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
			panel.add(newText);
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
    		ImageIcon image_newLabel = new ImageIcon(Child_Design.class.getResource("/EasyBBK_Swing/image/"+GUITypeName+"_move.png"));
    		newLabel.setIcon(image_newLabel);
    		newLabel.setBounds(biobrick.bounds);
    		newLabel.setName(GUITypeName);
			panel.add(newLabel);
    		
		}
		else if (primaryType.equals(SketchComponent.Protein.class.getSimpleName()))
		{
			SketchComponent.Protein protein = component.toProtein();
			
			JLabelWithID newLabel = new JLabelWithID();
			newLabel.ID=protein.ID; 
			String GUITypeName=null;
			
			switch (protein.secondaryType)
			{
				case BbkType.Sketch.Protein.FACTOR:
					GUITypeName = "factor";	break;
				case BbkType.Sketch.Protein.RECEPTER:
					GUITypeName = "receptor";	break;
				case BbkType.Sketch.Protein.COMBINED:
					GUITypeName = "protein";	break;
			}
			ImageIcon image_newLabel = new ImageIcon(Child_Design.class.getResource("/EasyBBK_Swing/image/"+GUITypeName+"_move.png"));
    		newLabel.setIcon(image_newLabel);    		
    		newLabel.setName(GUITypeName);
			newLabel.setBounds(protein.bounds);
			panel.add(newLabel);
		}
		else if (primaryType.equals(SketchComponent.BackBone.class.getSimpleName()))
		{
			SketchComponent.BackBone backbone = component.toBackBone();

    		BackBone newBackBone = new BackBone(panel,Tpanel, sketchCenter);
    		newBackBone.ID = newBackBone.ID;		
    		
    		ImageIcon image_newBackBone = new ImageIcon(Child_Design.class.getResource("/EasyBBK_Swing/image/backbone_move.png"));
    		image_newBackBone.setImage(image_newBackBone.getImage().getScaledInstance(((int)(backbone.bounds).getWidth()),50,Image.SCALE_DEFAULT));
    		newBackBone.setIcon(image_newBackBone);
    		newBackBone.setBounds(backbone.bounds);
    		newBackBone.setName("backbone");
    		newBackBone.activate();
			panel.add(newBackBone,-1);
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
			
			newLine.ID=relation.ID;
			ImageIcon image = new ImageIcon(newimg);
			newLine.setIcon(image);
			newLine.setBounds(linePanel.getLineBorder()[0]-10, linePanel.getLineBorder()[2]-10,
					linePanel.getLineBorder()[1]-linePanel.getLineBorder()[0]+20,
					linePanel.getLineBorder()[3]-linePanel.getLineBorder()[2]+20);
			panel.add(newLine);
			
			linePanel.color = new Color(previousColor.getRGB());
			linePanel.stroke = previousStroke;
			
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
    		newLabel.setBounds(bioVector.bounds);	    		
			ImageIcon image_newLabel = new ImageIcon(Child_Design.class.getResource("/EasyBBK_Swing/image/"+GUIBioVectorType+"_move.png"));
    		newLabel.setIcon(image_newLabel);
    		newLabel.setName(GUIBioVectorType);
			panel.add(newLabel);
		}
		else
			return;
		
	}
	
	class MyFileFilter extends FileFilter {  
		  
	    private String TYPE_UNKNOWN = "Type   Unknown ";  
	    private String HIDDEN_FILE = "Hidden   File ";  
	    private Hashtable filters = null;  
	    private String description = null;  
	    private String fullDescription = null;  
	    private boolean useExtensionsInDescription = true;  
	  
	    public MyFileFilter() {  
	        this.filters = new Hashtable();  
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
	            	//System.out.println(extension);
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
	            filters = new Hashtable(5);  
	        }  
	        filters.put(extension.toLowerCase(), this);  
	        fullDescription = null;  
	    }  
	  
	    public String getDescription() {  
	        if (fullDescription == null) {  
	            if (description == null || isExtensionListInDescription()) {  
	                fullDescription = description == null ? "(" : description + "(";  
	//   build   the   description   from   the   extension   list   
	                Enumeration extensions = filters.keys();  
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
