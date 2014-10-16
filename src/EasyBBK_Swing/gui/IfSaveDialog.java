package EasyBBK_Swing.gui;

import java.awt.Font;
import java.awt.Frame;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Locale;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.filechooser.FileFilter;

import data_center.SketchCenter;

public class IfSaveDialog extends JPanel
{
	private static final long serialVersionUID = 1L;
	
	SketchCenter sketchCenter;
	boolean ifOperate = false;
	
	IfSaveDialog(SketchCenter sketchCenter)
	{
		this.sketchCenter = sketchCenter;
	}
	
	{  
        try {  
            javax.swing.UIManager.setLookAndFeel(javax.swing.UIManager.getSystemLookAndFeelClassName());  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
    }  

    public IfSaveDialog()
    {     
        init();    
    }  
  
    //可供外部调用的方法  
    private JDialog dialog; 
    
    JButton ok = new JButton();
    JButton cancel = new JButton();
    JLabel showField1 = new JLabel();
    JLabel showField2 = new JLabel();
    //	    
    
    
    //
	private void init() 
    {
    	//layout
    	this.setLayout(null);
    	
    	showField1.setBounds(0,10,350,35);
    	showField1.setBorder(null);
    	showField1.setText("Do you want to save modifies");
    	showField1.setOpaque(false);
    	showField1.setHorizontalAlignment(JTextField.CENTER);
    	showField1.setFont(new Font("Arial",Font.PLAIN,18));
    	this.add(showField1);
    	
    	showField2.setBounds(0,45,350,35);
    	showField2.setBorder(null);
    	showField2.setText("that you did before?");
    	showField2.setOpaque(false);
    	showField2.setHorizontalAlignment(JTextField.CENTER);
    	showField2.setFont(new Font("Arial",Font.PLAIN,18));
    	this.add(showField2);
    	
    	ok.setBounds(200,80,60,30);
    	ok.setText("Yes");
    	cancel.setFont(new Font("Arial",Font.PLAIN,15));
    	this.add(ok);
    	
    	cancel.setBounds(270,80,60,30);
    	cancel.setText("No");
    	cancel.setFont(new Font("Arial",Font.PLAIN,15));
    	this.add(cancel);
    	cancel.setMargin(new Insets(0,0,0,0));

    	/*用户确定*/  
        ok.addActionListener(new ActionListener() {  
            @SuppressWarnings("unused")
			public void actionPerformed(ActionEvent e) 
            {
            	ifOperate=true;
            	//Open save dialog
            	JFileChooser chooser=new JFileChooser();//文件保存对话框
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
            	
                dialog.dispose();  
                dialog = null;  
            }  
        });  
  
        /*用户取消*/  
        cancel.addActionListener(new ActionListener() {  
            public void actionPerformed(ActionEvent e) { 
            	ifOperate=true;
                dialog.dispose();  
                dialog = null;  
            }  
        }); 
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
	
      
    /*显示字体选择器对话框(x,y表示窗体的启动位置)*/  
    public void showDialog(Frame parent,int x,int y) 
    {  
    	ifOperate=false;
        String  title = "Warnning";  
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
