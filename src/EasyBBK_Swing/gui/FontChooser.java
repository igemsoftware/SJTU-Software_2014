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
import javax.swing.JOptionPane;  
import javax.swing.JPanel;  
import javax.swing.JScrollPane;  
import javax.swing.JTextField;  
import javax.swing.event.ListSelectionEvent;  
import javax.swing.event.ListSelectionListener;
 
@SuppressWarnings("serial")
public class FontChooser extends JPanel 
{  
  
    // 设置界面风格  
    {  
        try {  
            javax.swing.UIManager.setLookAndFeel(javax.swing.UIManager.getSystemLookAndFeelClassName());  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
    }  
      
    //[start] 定义变量  
    private String current_fontName = "Arial";//当前的字体名称,默认Time New Roman.  
    private String showStr = "I'm handsome";//展示的文字  
    private int current_fontStyle = Font.PLAIN;//当前的字样,默认常规.  
    private int current_fontSize = 9;//当前字体大小,默认9号.  
    private Color current_color = Color.BLACK;//当前字色,默认黑色.  
    private JDialog dialog; //用于显示模态的窗体  
    private JLabel lblFont; //选择字体的LBL  
    private JLabel lblStyle; //选择字型的LBL  
    private JLabel lblSize; //选择字大小的LBL  
    private JLabel lblColor; //选择Color的label  
    private JLabel otherColor; //其它颜色  
    private JTextField txtFont; //显示选择字体的TEXT  
    private JTextField txtStyle; //显示选择字型的TEXT  
    private JTextField txtSize; //显示选择字大小的TEXT  
    private JTextField showTF; //展示框（输入框）  
    @SuppressWarnings("rawtypes")
	private JList lstFont; //选择字体的列表.  
    @SuppressWarnings("rawtypes")
	private JList lstStyle; //选择字型的列表.  
    @SuppressWarnings("rawtypes")
	private JList lstSize; //选择字体大小的列表.  
    @SuppressWarnings("rawtypes")
	private JComboBox cbColor; //选择Color的下拉框.  
    private JButton ok, cancel; //"确定","取消"按钮.  
    private JScrollPane spFont;  
    private JScrollPane spSize;
    private JPanel showPan; //显示框.  
    @SuppressWarnings("rawtypes")
	private Map sizeMap; //字号映射表.  
    @SuppressWarnings("rawtypes")
	private Map colorMap; //字着色映射表.  
    private Font selectedfont; //用户选择的字体  
    private Color selectedcolor; //用户选择的颜色  
    public String styleString="Normal";
    //[end]  
    
    //无参初始化  
    public FontChooser()
    {  
    	this.selectedfont = null;  
    	this.selectedcolor = null;  
    	/* 初始化界面 */  
    	init(null,null);  
    }  
      
    //重载构造，有参的初始化 用于初始化字体界面  
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
            /* 初始化界面 */  
            init(font,color);  
        }
        else
        {  
            JOptionPane.showMessageDialog(this, "没有被选择的控件", "错误", JOptionPane.ERROR_MESSAGE);  
        }  
    }  
  
    //可供外部调用的方法  
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
      
    /*初始化界面*/   
    @SuppressWarnings({ "unchecked", "rawtypes" })
	private void init(Font font,Color color) 
    {  
        //实例化变量  
        lblFont = new JLabel("Font:");  
        lblStyle = new JLabel("Style:");  
        lblSize = new JLabel("Size:");  
        lblColor = new JLabel("Color:");  
        otherColor = new JLabel("<html><U>Other Colors</U></html>");  
        txtFont = new JTextField("Arial");  
        txtStyle = new JTextField("Normal");  
        txtSize = new JTextField("9");
                 
        //取得当前环境可用字体.  
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();  
        String[] fontNames = ge.getAvailableFontFamilyNames();  
  
        lstFont = new JList(fontNames);
          
        //字形.  
        lstStyle = new JList(new String[]{"Normal", "Bold" ,"Italic", "BoldItalic"});  
          
        //字号.  
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
  
        //颜色  
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
          
          
        //布局控件  
        //字体框  
        this.setLayout(null);   //不用布局管理器  
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
  
        //样式  
        add(lblStyle);  
        lblStyle.setBounds(175, 10, 50, 20);  
        txtStyle.setEditable(false);  
        add(txtStyle);  
        txtStyle.setBounds(175, 30, 130, 20);  
        lstStyle.setBorder(javax.swing.BorderFactory.createLineBorder(Color.gray));  
        add(lstStyle);  
        lstStyle.setBounds(175, 50, 130, 100);  
        txtStyle.setText("Normal"); //初始化为默认的样式  
        lstStyle.setSelectedValue("Normal",true);   //初始化为默认的样式  
        styleString = "Normal";
        if(font != null)
        {  
            lstStyle.setSelectedIndex(font.getStyle()); //初始化样式list  
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
  
  
        //大小  
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
  
        //颜色  
        add(lblColor);  
        lblColor.setBounds(18, 225, 50, 20);  
        cbColor.setBounds(18, 245, 100, 22);  
        cbColor.setMaximumRowCount(5);  
        add(cbColor);  
        otherColor.setForeground(Color.black);  
        otherColor.setBounds(130, 245, 120, 22);  
        otherColor.setCursor(new Cursor(Cursor.HAND_CURSOR));  
        add(otherColor);  

        //展示框  
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
            showTF.setFont(font); // 设置示例中的文字格式  
        }  
        if (font != null) 
        {  
            showTF.setForeground(color);  
        }  
  
        //确定和取消按钮  
        add(ok);  
        ok.setBounds(230, 245, 60, 20);  
        add(cancel);  
        cancel.setBounds(300, 245, 60, 20);  
        //布局控件_结束  
  
        //listener.....  
        /*用户选择字体*/  
        lstFont.addListSelectionListener(new ListSelectionListener() {  
            public void valueChanged(ListSelectionEvent e) 
            {  
                current_fontName = (String) lstFont.getSelectedValue();  
                txtFont.setText(current_fontName);  
                showTF.setFont(new Font(current_fontName, current_fontStyle, current_fontSize));  
            }  
        });  
  
        /*用户选择字型*/  
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
  
        /*用户选择字体大小*/  
        lstSize.addListSelectionListener(new ListSelectionListener() {  
            public void valueChanged(ListSelectionEvent e) {  
                current_fontSize = (Integer) sizeMap.get(lstSize.getSelectedValue());  
                txtSize.setText(String.valueOf(current_fontSize));  
                showTF.setFont(new Font(current_fontName, current_fontStyle, current_fontSize));  
            }  
        });  
  
        /*用户选择字体颜色*/  
        cbColor.addActionListener(new ActionListener() {  
            public void actionPerformed(ActionEvent e) {  
                current_color = (Color) colorMap.get(cbColor.getSelectedItem());  
                showTF.setForeground(current_color);  
            }  
        });  
        /*其它颜色*/  
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
        /*用户确定*/  
        ok.addActionListener(new ActionListener() {  
            public void actionPerformed(ActionEvent e) {  
                setSelectedfont(new Font(current_fontName, current_fontStyle, current_fontSize));    
                setSelectedcolor(current_color);
                setSelectedstyle(styleString);
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
        String  title = "Font";  
        dialog = new JDialog(parent, title,true);  
        dialog.add(this);  
        dialog.setResizable(false);  
        dialog.setSize(400, 310);  
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

