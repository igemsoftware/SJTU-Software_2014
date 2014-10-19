package EasyBBK_Swing.gui;

import java.awt.BasicStroke;
import java.awt.BorderLayout;  
import java.awt.Color;  
import java.awt.Cursor;
import java.awt.Frame;  
import java.awt.Graphics;
import java.awt.Graphics2D;
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
import javax.swing.JDialog;  
import javax.swing.JLabel;  
import javax.swing.JList;  
import javax.swing.JPanel;  
import javax.swing.JScrollPane;  
import javax.swing.JTextField;  
import javax.swing.event.ListSelectionEvent;  
import javax.swing.event.ListSelectionListener;
 
/**
 * Show a dialog in which you can choose thickness and color of the line.
 * @author LC
 *
 */
public class LineStyleChooser extends JPanel 
{  
	private static final long serialVersionUID = 1L;

	private class ShowLine extends JPanel
	{
		private static final long serialVersionUID = 1L;
		
		int size=3;
		Color color=Color.BLACK;
		
		ShowLine()
		{
			super();
			setOpaque(false);
			this.repaint();
		}
		
		public void paintComponent(Graphics g)
		{
			super.repaint();
			Graphics2D gra = (Graphics2D)g;
			gra.setStroke(new BasicStroke(size));
			gra.setPaint(color);
			gra.drawLine(10, 25, 180, 25);
		}
		
		public void setSize(int size)
		{
			this.size=size;
		}
		
		public void setColor(Color color)
		{
			this.color=color;
		}
	}
	
    {  
        try {  
            javax.swing.UIManager.setLookAndFeel(javax.swing.UIManager.getSystemLookAndFeelClassName());  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
    }  
      
    //[start] 定义变量    
    private int current_Size = 3; 
    private Color current_color = Color.BLACK;
    
    private JDialog dialog; 
 
    private JLabel lblColor; 
    private JLabel lblSize; 
    private JLabel otherColor;
    
    private JTextField txtColor;   
    private JTextField txtSize;
    
    private ShowLine showPanel; //展示框（输入框）  

	private JList<?> lstColor; //选择字型的列表.  
	private JList<?> lstSize; //选择字体大小的列表.  
	
 
    private JButton ok, cancel; //"确定","取消"按钮.
    
    private JScrollPane spColor;  
    private JScrollPane spSize;
    
    private JPanel showPan; //显示框. 

	private Map<String, Integer> sizeMap; //字号映射表.  
	private Map<String, Color> colorMap; //字着色映射表.  
	private Map<Color, String> colortitleMap;
    private Integer selectedsize; //用户选择的字体  
    private Color selectedcolor; //用户选择的颜色  
    //[end]  

    public LineStyleChooser()
    {  
    	this.selectedsize = null;  
    	this.selectedcolor = null;   
    	init(null,null);  
    }  
      
    public LineStyleChooser(int size, Color color)
    {  
    	this.selectedsize = size;  
        this.selectedcolor = color;    
        this.current_Size = size;   
        this.current_color = color;    
        init(size,color);    
    }  
  
    //可供外部调用的方法  
    public int getSelectedsize() 
    {  
        return selectedsize;  
    }  
  
    public void setSelectedsize(int selectedsize) 
    {  
        this.selectedsize = selectedsize;  
    }  
  
    public Color getSelectedcolor() 
    {  
        return selectedcolor;  
    }  
  
    public void setSelectedcolor(Color selectedcolor) 
    {  
        this.selectedcolor = selectedcolor;  
    }  
  
    private void init(Integer size,Color color) 
    {  
        lblColor = new JLabel("Color:");  
        lblSize = new JLabel("Size:");   

        txtColor = new JTextField("Normal");  
        txtSize = new JTextField("3");
          
        //Color Chooser  
        String[] colorStr = new String[]{  
                "Black", "Blue", "Cyan", "DarkGray", "Gray", "Green", "LightGray", "Magenta", "Orange", "Pink", "Red", "White", "Yellow"  };  
        Color[] colorVal = new Color[]{  
                Color.BLACK, Color.BLUE, Color.CYAN, Color.DARK_GRAY, Color.GRAY, Color.GREEN, Color.LIGHT_GRAY, Color.MAGENTA, Color.ORANGE, Color.PINK, Color.RED, Color.WHITE, Color.YELLOW  
            };  
        colorMap = new HashMap<String, Color>(); 
        colortitleMap = new HashMap<Color, String>(); 
        for (int i = 0; i < colorStr.length; i++) 
        {  
        	colorMap.put(colorStr[i], colorVal[i]);  
        	colortitleMap.put(colorVal[i], colorStr[i]);
        }  
        lstColor = new JList<Object>(colorStr);       
        
        //Size chooser
        String[] sizeStr = new String[]{  
            "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15"};  
        int sizeVal[] = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15};  
        sizeMap = new HashMap<String, Integer>();  
        for (int i = 0; i < sizeStr.length; ++i) 
        {  
            sizeMap.put(sizeStr[i], sizeVal[i]);  
        }
        
        
        lstSize = new JList<Object>(sizeStr); 
        
        //Scroll
        spColor = new JScrollPane(lstColor);  
        spSize = new JScrollPane(lstSize);  
           
        showPan = new JPanel();
        //button
        ok = new JButton("OK");  
        cancel = new JButton("Cancel"); 
        cancel.setMargin(new Insets(0,0,0,0));

        this.setLayout(null);
  
        //Color 
        add(lblColor);  
        lblColor.setBounds(10, 10, 50, 20); 
        
        txtColor.setEditable(false);        
        add(txtColor);  
        txtColor.setBounds(10, 30, 130, 20); 
        txtColor.setText("Black"); //初始化为默认的样式  
        
        otherColor = new JLabel("<html><U>Other Colors</U></html>");
        otherColor.setForeground(Color.blue);  
        otherColor.setBounds(60, 8, 120, 20);  
        otherColor.setCursor(new Cursor(Cursor.HAND_CURSOR));  
        add(otherColor); 
        
        add(spColor);  
        spColor.setBounds(10, 50, 130, 100);  
        
        lstColor.setSelectedValue("Black",false);   //初始化为默认的样式  
        txtColor.setText("Black");
        
        if(color != null)
        {  
            lstColor.setSelectedValue(color.toString(),false); //初始化样式list
            String colorTitle =(String) colortitleMap.get(color);
            txtColor.setText(colorTitle); 
        }  

  
        //Size 
        add(lblSize);  
        lblSize.setBounds(155, 10, 30, 20);  
        
        txtSize.setEditable(false);  
        add(txtSize);  
        txtSize.setBounds(155, 30, 60, 20);  
        
        add(spSize);  
        spSize.setBounds(155, 50, 60, 100); 
        
        lstSize.setSelectedValue("3", false);  
        txtSize.setText("3");  
        
        if (size != null) {  
            lstSize.setSelectedValue(Integer.toString(size), false);  
            txtSize.setText(Integer.toString(size));  
        }  
  
        //展示框          
        showPan.setBorder(javax.swing.BorderFactory.createTitledBorder("Example"));  
        add(showPan);         
        showPan.setBounds(11, 150,205, 80);  
        showPan.setLayout(new BorderLayout()); 
        showPanel = new ShowLine(); 
        showPan.add(showPanel);
        showPanel.setBounds(10, 10, 135, 50);
        showPanel.setBackground(Color.white);
        
        if (size != null) {  
        	showPanel.setSize(size); 
        }  
        if (color != null) 
        {  
            showPanel.setColor(color);  
        }  
  
        //确定和取消按钮  
        add(ok);  
        ok.setBounds(90, 245, 60, 20);  
        add(cancel);  
        cancel.setBounds(160, 245, 60, 20);  
        //布局控件_结束   

  
        //set color 
        lstColor.addListSelectionListener(new ListSelectionListener() {  
            public void valueChanged(ListSelectionEvent e) 
            {  
                String value = (String) ((JList<?>) e.getSource()).getSelectedValue();  
                current_color = (Color) colorMap.get(value);
                txtColor.setText(value);  
                showPanel.setColor(current_color);
            }  
        });  
  
        //set size  
        lstSize.addListSelectionListener(new ListSelectionListener() {  
            public void valueChanged(ListSelectionEvent e) {  
                current_Size = (Integer) sizeMap.get(lstSize.getSelectedValue());  
                txtSize.setText(String.valueOf(current_Size));  
                showPanel.setSize(current_Size);  
            }  
        });  

        /*用户确定*/  
        ok.addActionListener(new ActionListener() {  
            public void actionPerformed(ActionEvent e) {  
                setSelectedsize(current_Size);    
                setSelectedcolor(current_color);
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
        
        otherColor.addMouseListener(new MouseAdapter() {  
            @Override  
            public void mouseClicked(MouseEvent e) {  
                @SuppressWarnings("static-access")
				Color col_temp = new JColorChooser().showDialog(null, null, Color.pink);  
                if (col_temp != null) {  
                    current_color = col_temp;  
                    showPanel.setColor(current_color);  
                    super.mouseClicked(e);  
                }  
            }  
        });
    }  
      
    /*显示字体选择器对话框(x,y表示窗体的启动位置)*/  
    public void showDialog(Frame parent,int x,int y) 
    {  
        String  title = "LineStyle";  
        dialog = new JDialog(parent, title,true);  
        dialog.add(this);  
        dialog.setResizable(false);  
        dialog.setSize(235, 310);  
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
