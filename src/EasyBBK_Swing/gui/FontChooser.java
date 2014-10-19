package EasyBBK_Swing.gui;

import java.awt.BorderLayout;  
import java.awt.Color;  
import java.awt.Cursor;  
import java.awt.Font;  
import java.awt.Frame;  
import java.awt.GraphicsEnvironment;  
import java.awt.Insets;
import java.awt.event.ActionEvent;  
import java.awt.event.ActionListener;  
import java.awt.event.MouseAdapter;  
import java.awt.event.MouseEvent;  
import java.awt.event.WindowAdapter;  
import java.awt.event.WindowEvent;  
import java.util.HashMap;  
import java.util.Map;  

import javax.swing.JButton;  
import javax.swing.JColorChooser;  
import javax.swing.JComboBox;  
import javax.swing.JDialog;  
import javax.swing.JLabel;  
import javax.swing.JList;  
import javax.swing.JPanel;  
import javax.swing.JScrollPane;  
import javax.swing.JTextField;  
import javax.swing.event.ListSelectionEvent;  
import javax.swing.event.ListSelectionListener;
/**
 * Show a dialog in which you can choose font and color of the word show on the text label.
 * @author LC
 *
 */
@SuppressWarnings("serial")
public class FontChooser extends JPanel 
{  
  
    // interface style
    {  
        try {  
            javax.swing.UIManager.setLookAndFeel(javax.swing.UIManager.getSystemLookAndFeelClassName());  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
    }  
      
    private String current_fontName = "Arial";//default font: Arial 
    private String showStr = "I Love iGEM";//example  
    private int current_fontStyle = Font.PLAIN;//default style: plain
    private int current_fontSize = 9;//default size: 9  
    private Color current_color = Color.BLACK;//default color: black  
    private JDialog dialog;  
    private JLabel lblFont;  
    private JLabel lblStyle; 
    private JLabel lblSize;  
    private JLabel lblColor; 
    private JLabel otherColor; //select other color 
    private JTextField txtFont; 
    private JTextField txtStyle;  
    private JTextField txtSize;  
    private JTextField showTF; //Show example
    @SuppressWarnings("rawtypes")
	private JList lstFont;  //choose font
    @SuppressWarnings("rawtypes")
	private JList lstStyle; //choose font style  
    @SuppressWarnings("rawtypes")
	private JList lstSize; //choose size 
    @SuppressWarnings("rawtypes")
	private JComboBox cbColor; //choose color
    private JButton ok, cancel;
    private JScrollPane spFont;  
    private JScrollPane spSize;
    private JPanel showPan; //Show example; 
    @SuppressWarnings("rawtypes")
	private Map sizeMap; 
    @SuppressWarnings("rawtypes")
	private Map colorMap; 
    private Font selectedfont;
    private Color selectedcolor;
    public String styleString="Normal";  
  
    public FontChooser(Font font, Color color)
    {  
        if (font != null) 
        {  
        	this.selectedfont = font;  
            this.selectedcolor = color;  
            this.current_fontName = font.getName();  
            this.current_fontSize = font.getSize();  
            this.current_fontStyle = font.getStyle();  
            this.current_color = color;  

            init(font,color);  
        }  
    }  
  
    public Font getSelectedfont() 
    {  
        return selectedfont;  
    }  
  
    public void setSelectedfont(Font selectedfont) 
    {  
        this.selectedfont = selectedfont;  
    }  
  
    public Color getSelectedcolor() 
    {  
        return selectedcolor;  
    }  
  
    public void setSelectedcolor(Color selectedcolor) 
    {  
        this.selectedcolor = selectedcolor;  
    }  
    
    public void setSelectedstyle(String styleString) 
    {  
        this.styleString = styleString;  
    }
    
    public String getSelectedstyle()
    {
    	return styleString;
    }
 
    @SuppressWarnings({ "unchecked", "rawtypes" })
	private void init(Font font,Color color) 
    {  
        lblFont = new JLabel("Font:");  
        lblStyle = new JLabel("Style:");  
        lblSize = new JLabel("Size:");  
        lblColor = new JLabel("Color:");  
        otherColor = new JLabel("<html><U>Other Colors</U></html>");  
        txtFont = new JTextField("Arial");  
        txtStyle = new JTextField("Normal");  
        txtSize = new JTextField("9");
                 
        //get all fonts which are available  
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();  
        String[] fontNames = ge.getAvailableFontFamilyNames();  
  
        lstFont = new JList(fontNames);
          
        //Style 
        lstStyle = new JList(new String[]{"Normal", "Bold" ,"Italic", "BoldItalic"});  
          
        //Size  
        String[] sizeStr = new String[]{  
            "8", "9", "10", "11", "12", "14", "16", "18", "20", "22", "24", "26", "28", "36", "48", "72"};  
        int sizeVal[] = {8, 9, 10, 11, 12, 14, 16, 18, 20, 22, 24, 26, 28, 36, 48, 72};  
        sizeMap = new HashMap();  
        for (int i = 0; i < sizeStr.length; ++i) 
        {  
            sizeMap.put(sizeStr[i], sizeVal[i]);  
        }  
        lstSize = new JList(sizeStr);  
        spFont = new JScrollPane(lstFont);  
        spSize = new JScrollPane(lstSize);  
  
        //Color
        String[] colorStr = new String[]{  
            "Black", "Blue", "Cyan", "DarkGray", "Gray", "Green", "LightGray", "Magenta", "Orange", "Pink", "Red", "White", "Yellow"  };  
        Color[] colorVal = new Color[]{  
            Color.BLACK, Color.BLUE, Color.CYAN, Color.DARK_GRAY, Color.GRAY, Color.GREEN, Color.LIGHT_GRAY, Color.MAGENTA, Color.ORANGE, Color.PINK, Color.RED, Color.WHITE, Color.YELLOW  
        };  
        colorMap = new HashMap();  
        for (int i = 0; i < colorStr.length; i++) 
        {  
            colorMap.put(colorStr[i], colorVal[i]);  
        }  
        cbColor = new JComboBox(colorStr);  
        showPan = new JPanel();  
        ok = new JButton("OK");  
        cancel = new JButton("Cancel"); 
        cancel.setMargin(new Insets(0,0,0,0));
            
        //Font
        this.setLayout(null);
        add(lblFont);  
        lblFont.setBounds(12, 10, 30, 20);  
        txtFont.setEditable(false);  
        add(txtFont);  
        txtFont.setBounds(10, 30, 155, 20);  
        txtFont.setText("Arial");  
        lstFont.setSelectedValue("Arial", true);  
        if (font != null) 
        {  
            txtFont.setText(font.getName());  
            lstFont.setSelectedValue(font.getName(), true);  
        }  
          
        add(spFont);  
        spFont.setBounds(10, 50, 155, 100);  
  
        //Style
        add(lblStyle);  
        lblStyle.setBounds(175, 10, 50, 20);  
        txtStyle.setEditable(false);  
        add(txtStyle);  
        txtStyle.setBounds(175, 30, 130, 20);  
        lstStyle.setBorder(javax.swing.BorderFactory.createLineBorder(Color.gray));  
        add(lstStyle);  
        lstStyle.setBounds(175, 50, 130, 100);  
        txtStyle.setText("Normal"); //default style
        lstStyle.setSelectedValue("Normal",true);   //
        styleString = "Normal";
        if(font != null)
        {  
            lstStyle.setSelectedIndex(font.getStyle());  
            if (font.getStyle() == 0) 
            {  
                txtStyle.setText("Normal");  
            } 
            else if (font.getStyle() == 1) 
            {  
                txtStyle.setText("Bold");  
            } 
            else if (font.getStyle() == 2) 
            {  
                txtStyle.setText("Italic");  
            } 
            else if (font.getStyle() == 3) 
            {  
                txtStyle.setText("BoldItalic");  
            }  
        }  
  
  
        //button size
        add(lblSize);  
        lblSize.setBounds(320, 10, 30, 20);  
        txtSize.setEditable(false);  
        add(txtSize);  
        txtSize.setBounds(320, 30, 60, 20);  
        add(spSize);  
        spSize.setBounds(320, 50, 60, 100);  
        lstSize.setSelectedValue("9", false);  
        txtSize.setText("9");  
        if (font != null) {  
            lstSize.setSelectedValue(Integer.toString(font.getSize()), false);  
            txtSize.setText(Integer.toString(font.getSize()));  
        }  
  
        //color
        add(lblColor);  
        lblColor.setBounds(18, 225, 50, 20);  
        cbColor.setBounds(18, 245, 100, 22);  
        cbColor.setMaximumRowCount(5);  
        add(cbColor);  
        otherColor.setForeground(Color.blue);  
        otherColor.setBounds(130, 245, 120, 22);  
        otherColor.setCursor(new Cursor(Cursor.HAND_CURSOR));  
        add(otherColor);  

        //show panel 
        showTF = new JTextField();  
        showTF.setFont(new Font(current_fontName, current_fontStyle, current_fontSize));  
        showTF.setBounds(10, 10, 300, 50);  
        showTF.setHorizontalAlignment(JTextField.CENTER);  
        showTF.setText(showStr);  
        showTF.setBackground(Color.white);  
        showTF.setEditable(false);  
        showPan.setBorder(javax.swing.BorderFactory.createTitledBorder("Example"));  
        add(showPan);  
        showPan.setBounds(13, 150,370, 80);  
        showPan.setLayout(new BorderLayout());  
        showPan.add(showTF);  
        if (font != null) {  
            showTF.setFont(font); //set font of example
        }  
        if (font != null) 
        {  
            showTF.setForeground(color);  
        }  
  
        add(ok);  
        ok.setBounds(230, 245, 60, 20);  
        add(cancel);  
        cancel.setBounds(300, 245, 60, 20);   
  
        //choose font
        lstFont.addListSelectionListener(new ListSelectionListener() {  
            public void valueChanged(ListSelectionEvent e) 
            {  
                current_fontName = (String) lstFont.getSelectedValue();  
                txtFont.setText(current_fontName);  
                showTF.setFont(new Font(current_fontName, current_fontStyle, current_fontSize));  
            }  
        });  
  
        //choose style  
        lstStyle.addListSelectionListener(new ListSelectionListener() {  
            public void valueChanged(ListSelectionEvent e) {  
                String value = (String) ((JList) e.getSource()).getSelectedValue();  
                if (value.equals("Normal")) 
                {  
                    current_fontStyle = Font.PLAIN;
                    styleString = "Normal";
                }  
                if (value.equals("Italic")) 
                {  
                    current_fontStyle = Font.ITALIC;  
                    styleString = "Italic";
                }  
                if (value.equals("Bold")) 
                {  
                    current_fontStyle = Font.BOLD;  
                    styleString = "Bold";
                }  
                if (value.equals("BoldItalic")) 
                {  
                    current_fontStyle = Font.BOLD | Font.ITALIC;  
                    styleString = "BoldItalic";
                }  
                txtStyle.setText(value);  
                showTF.setFont(new Font(current_fontName, current_fontStyle, current_fontSize));  
            }  
        });  
  
        //choose size
        lstSize.addListSelectionListener(new ListSelectionListener() {  
            public void valueChanged(ListSelectionEvent e) {  
                current_fontSize = (Integer) sizeMap.get(lstSize.getSelectedValue());  
                txtSize.setText(String.valueOf(current_fontSize));  
                showTF.setFont(new Font(current_fontName, current_fontStyle, current_fontSize));  
            }  
        });  
  
        //choose color 
        cbColor.addActionListener(new ActionListener() {  
            public void actionPerformed(ActionEvent e) {  
                current_color = (Color) colorMap.get(cbColor.getSelectedItem());  
                showTF.setForeground(current_color);  
            }  
        });
        
        //other colors
        otherColor.addMouseListener(new MouseAdapter() {  
            @Override  
            public void mouseClicked(MouseEvent e) {  
                @SuppressWarnings("static-access")
				Color col_temp = new JColorChooser().showDialog(null, null, Color.pink);  
                if (col_temp != null) {  
                    current_color = col_temp;  
                    showTF.setForeground(current_color);  
                    super.mouseClicked(e);  
                }  
            }  
        });  
          
        ok.addActionListener(new ActionListener() {  
            public void actionPerformed(ActionEvent e) {  
                setSelectedfont(new Font(current_fontName, current_fontStyle, current_fontSize));    
                setSelectedcolor(current_color);
                setSelectedstyle(styleString);
                dialog.dispose();  
                dialog = null;  
            }  
        });  
    
        cancel.addActionListener(new ActionListener() {  
            public void actionPerformed(ActionEvent e) {  
                dialog.dispose();  
                dialog = null;  
            }  
        });  
    }  
        
    public void showDialog(Frame parent,int x,int y) 
    {  
        String  title = "Font";  
        dialog = new JDialog(parent, title,true);  
        dialog.add(this);  
        dialog.setResizable(false);  
        dialog.setSize(400, 310);  
        //location of dialog in screen
        dialog.setLocation(x,y);  
        dialog.addWindowListener(new WindowAdapter() {  
  
            //windows close event 
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

