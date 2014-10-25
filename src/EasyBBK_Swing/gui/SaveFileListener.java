package EasyBBK_Swing.gui;

import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.*;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.event.MouseInputListener;
import javax.swing.filechooser.FileFilter;

/**
 * Show a dialog in which you can save your job as a picture file.
 * @author LC
 *
 */
public class SaveFileListener implements MouseInputListener
{
	private JLayeredPane panel = new JLayeredPane();

	
	SaveFileListener(JLayeredPane panel)
	{
		this.panel = panel;
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
	
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		BufferedImage savedImage = new BufferedImage(panel.getWidth(),panel.getHeight(), BufferedImage.TYPE_INT_RGB);
		Graphics2D g = (Graphics2D)savedImage.getGraphics();
		panel.paint(g);
		
		JFileChooser chooser=new JFileChooser();
		chooser.setLocale(Locale.ENGLISH);
		
		chooser.setAcceptAllFileFilterUsed(true);
		try {
			javax.swing.UIManager.setLookAndFeel(javax.swing.UIManager.getSystemLookAndFeelClassName()); 
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
		chooser.addChoosableFileFilter(new MyFileFilter("jpg","JPEG"));
		chooser.addChoosableFileFilter(new MyFileFilter("bmp","BMP"));
		chooser.addChoosableFileFilter(new MyFileFilter("png","PNG"));
		chooser.setCurrentDirectory(new File("."));
		if(chooser.showSaveDialog(null)==JFileChooser.APPROVE_OPTION)
		{
			File oFile=chooser.getSelectedFile();
			FileFilter filter = chooser.getFileFilter();
			if (!validateFileName(oFile.getName()))
			{
				JOptionPane.showMessageDialog(chooser, "Invalid FIle Name");
				return;
			}
			String desc = filter.getDescription();
			String extension = "";
			if (desc.equals("JPEG(.jpg)"))
				extension = ".jpg";
			else if (desc.equals("PNG(.png)"))
				extension = ".png";
			else if (desc.equals("BMP(.bmp)"))
				extension = ".bmp";
			else 
				extension = ".jpg";
			if (!oFile.getAbsolutePath().toUpperCase().endsWith(extension.toUpperCase()))
			{
				oFile = new File(oFile.getAbsolutePath() + extension);
			}
			try
			{
				if (extension.equals(".jpg"))
					ImageIO.write(savedImage, "jpg", oFile);
				else if (extension.equals(".png"))
					ImageIO.write(savedImage, "png", oFile);
				else if (extension.equals(".bmp"))
					ImageIO.write(savedImage, "bmp", oFile);
			}
			catch(IOException ex)
			{
				ex.printStackTrace();
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
	            if (extension != null && filters.get(getExtension(f)) != null) {  
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
