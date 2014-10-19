package EasyBBK_Swing.gui;

import java.awt.event.MouseEvent;
import java.io.File;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Locale;

import javax.swing.JFileChooser;
import javax.swing.JLayeredPane;
import javax.swing.SwingUtilities;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.event.MouseInputListener;
import javax.swing.filechooser.FileFilter;

import EasyBBK_Swing.gui.SaveListener.MyFileFilter;
import data_center.SketchCenter;

@SuppressWarnings("unused")
/**
 * Show a dialog in which you can save your job in detail as a XML file.
 * @author LC
 *
 */
public class SaveListener implements MouseInputListener
{
	SketchCenter sketchCenter;
	
	public SaveListener(SketchCenter sketchCenter,JLayeredPane panel,TPanel Tpanel)
	{	
		this.sketchCenter = sketchCenter;
	}
	
	public void mouseClicked(MouseEvent e) 
	{
		if (sketchCenter.currentProject.filePath==null)
		{
			JFileChooser chooser=new JFileChooser();
			chooser.setLocale(Locale.ENGLISH);
			chooser.setAcceptAllFileFilterUsed(true);
			chooser.addChoosableFileFilter(new MyFileFilter("xml","XML"));
			
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
			if(chooser.showSaveDialog(null)==JFileChooser.APPROVE_OPTION)
			{
				File oFile=chooser.getSelectedFile();
				FileFilter filter = chooser.getFileFilter();
				String desc = filter.getDescription();
				String extension = ".xml";
				if (!oFile.getAbsolutePath().toUpperCase().endsWith(extension.toUpperCase()))
				{
					oFile = new File(oFile.getAbsolutePath() + extension);
				}
				
				sketchCenter.saveProject(oFile.toString());
			}
		}
		else
		{
			sketchCenter.currentProject.saveIntoFile();
		}
				
	}
	
	public void mousePressed(MouseEvent e) {}

	public void mouseDragged(MouseEvent e) {}
	
	public void mouseMoved(MouseEvent e) {}
		
	public void mouseReleased(MouseEvent e){}
		
	public void mouseEntered(MouseEvent e) {}

	public void mouseExited(MouseEvent e) {}
	
	
	
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
