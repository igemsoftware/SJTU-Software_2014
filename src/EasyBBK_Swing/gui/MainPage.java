package EasyBBK_Swing.gui;

import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GraphicsEnvironment;
import java.awt.Insets;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

import data_center.*;
import data_center.BbkUpload.SpecifiedSubscar;

public class MainPage extends JFrame{
	public static JFrame frame;
	public JPanel Mainpanel;
	public JLabel Search;
	public JLabel Design;
	public JLabel Upload;
	public JLabel Compare;
	public JLabel GreenBar;
	public boolean Search_flag;
	public boolean Design_flag;
	public boolean Upload_flag;
	public boolean Compare_flag;
	public MainPage mainpage;
	public JLabel Home;
	public Child_Main child_main = null;
	public Child_Search_Main child_search_main_current = null;
	public Child_Search child_search_current = null;
	public Child_Design child_design_current = null;
	public Child_Upload child_upload_current = null;
	//public Child_Compare child_compare_current = null;
	public int CurrentPage = 0;
	public String sequencestring = "";
	public String subpartstring = "";
	public String subscarstring = "";
	public BbkDetail subpart_bbkdetail = null;
	public SpecifiedSubscar subscar = null;
	static Point origin = new Point();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainPage window = new MainPage();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainPage() {
		mainpage = this;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		frame = new JFrame();
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		//Dimension scrSize = Toolkit.getDefaultToolkit().getScreenSize();    
		//Insets scrInsets=Toolkit.getDefaultToolkit().getScreenInsets(GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDefaultConfiguration());
		//frame.setBounds(scrInsets.left,scrInsets.top,scrSize.width-scrInsets.left-scrInsets.right,scrSize.height-scrInsets.top-scrInsets.bottom);
		frame.setSize(new Dimension(1366, 728));
		frame.setLocationRelativeTo(null);
		frame.setUndecorated(true);
		
		Mainpanel = new JPanel();
		//Mainpanel.setOpaque(true);
		Mainpanel.setBounds(0, 58, 1366, 670);
		Mainpanel.setVisible(true);
		Mainpanel.setLayout(null);
		frame.getContentPane().add(Mainpanel);
		
		GreenBar = new JLabel("");
		GreenBar.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {  //按下（mousePressed 不是点击，而是鼠标被按下没有抬起）
                origin.x = e.getX();  //当鼠标按下的时候获得窗口当前的位置
                origin.y = e.getY();
            }
		});
		
		GreenBar.addMouseMotionListener(new MouseMotionAdapter() {
            public void mouseDragged(MouseEvent e) {  //拖动（mouseDragged 指的不是鼠标在窗口中移动，而是用鼠标拖动）
                
                Point p = frame.getLocation();  //当鼠标拖动时获取窗口当前位置
                //设置窗口的位置
                //窗口当前的位置 + 鼠标当前在窗口的位置 - 鼠标按下的时候在窗口的位置
                frame.setLocation(p.x + e.getX() - origin.x, p.y + e.getY() - origin.y);
            }
		});
		GreenBar.setOpaque(true);
		GreenBar.setBounds(0, 0, 1366, 58);
		GreenBar.setIcon(new ImageIcon(MainPage.class.getResource("/EasyBBK_Swing/image/greenbar.png")));
		frame.getContentPane().add(GreenBar);
		
		Home = new JLabel();
		Home.setIcon(new ImageIcon(MainPage.class.getResource("/EasyBBK_Swing/image/home.png")));
		Home.setBounds(0, 0, 227, 58);
		Home.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getButton() == e.BUTTON1){
					if(CurrentPage == 0) return;
					
					Search.setIcon(new ImageIcon(MainPage.class.getResource("/EasyBBK_Swing/image/Search1.png")));
					Design.setIcon(new ImageIcon(MainPage.class.getResource("/EasyBBK_Swing/image/Design1.png")));
					Upload.setIcon(new ImageIcon(MainPage.class.getResource("/EasyBBK_Swing/image/Upload1.png")));

					if(CurrentPage == 1){
						Component component = Mainpanel.getComponent(0);
						if(component instanceof Child_Search_Main){
							child_search_main_current = (Child_Search_Main) component;
						}
					}
					if(CurrentPage == 11){
						Component component = Mainpanel.getComponent(0);
						if(component instanceof Child_Search){
							child_search_current = (Child_Search) component;
						}
					}
					if(CurrentPage == 2){
						Component component = Mainpanel.getComponent(0);
						if(component instanceof Child_Design){
							child_design_current = (Child_Design) component;
						}
					}
					if(CurrentPage == 3){
						Component component = Mainpanel.getComponent(0);
						if(component instanceof Child_Upload){
							child_upload_current = (Child_Upload) component;
						}
					}
					
					Mainpanel.removeAll();
					Mainpanel.add(child_main);
					Mainpanel.updateUI();
					CurrentPage = 0;
				}
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				Home.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
				Home.setIcon(new ImageIcon(MainPage.class.getResource("/EasyBBK_Swing/image/home_Hover.png")));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				Home.setIcon(new ImageIcon(MainPage.class.getResource("/EasyBBK_Swing/image/home.png")));
			}
		});
		GreenBar.add(Home);
		
		final JLabel Close = new JLabel();
		Close.setIcon(new ImageIcon(MainPage.class.getResource("/EasyBBK_Swing/image/Close1.png")));
		Close.setBounds(1323, 3, 40, 40);
		Close.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getButton() == e.BUTTON1)
					System.exit(0);
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				Close.setIcon(new ImageIcon(MainPage.class.getResource("/EasyBBK_Swing/image/Close_Hover1.png")));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				Close.setIcon(new ImageIcon(MainPage.class.getResource("/EasyBBK_Swing/image/Close1.png")));
			}
		});
		GreenBar.add(Close);
		
		final JLabel Min = new JLabel();
		Min.setIcon(new ImageIcon(MainPage.class.getResource("/EasyBBK_Swing/image/Min1.png")));
		Min.setBounds(1277, 3, 40, 40);
		Min.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getButton() == e.BUTTON1)
					frame.setExtendedState(JFrame.ICONIFIED);
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				Min.setIcon(new ImageIcon(MainPage.class.getResource("/EasyBBK_Swing/image/Min_Hover1.png")));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				Min.setIcon(new ImageIcon(MainPage.class.getResource("/EasyBBK_Swing/image/Min1.png")));
			}
		});
		GreenBar.add(Min);
		
		Search = new JLabel();
		Search_flag = false;
		Search.setIcon(new ImageIcon(MainPage.class.getResource("/EasyBBK_Swing/image/Search1.png")));
		Search.setBounds(399, 0, 150, 58);
		Search.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getButton() == e.BUTTON1){
					if(CurrentPage == 1 || CurrentPage == 11) return;
					
					Search_flag = true;
					Design_flag = false;
					Upload_flag = false;
					Compare_flag = false;
					Search.setIcon(new ImageIcon(MainPage.class.getResource("/EasyBBK_Swing/image/Search_click1.png")));
					Design.setIcon(new ImageIcon(MainPage.class.getResource("/EasyBBK_Swing/image/Design1.png")));
					Upload.setIcon(new ImageIcon(MainPage.class.getResource("/EasyBBK_Swing/image/Upload1.png")));
					Compare.setIcon(new ImageIcon(MainPage.class.getResource("/EasyBBK_Swing/image/Compare.png")));
					
					if(CurrentPage == 2){
						Component component = Mainpanel.getComponent(0);
						if(component instanceof Child_Design){
							child_design_current = (Child_Design) component;
						}
					}
					
					if(CurrentPage == 3){
						Component component = Mainpanel.getComponent(0);
						if(component instanceof Child_Upload){
							child_upload_current = (Child_Upload) component;
						}
					}
					
					/*if(CurrentPage == 4){
						Component component = Mainpanel.getComponent(0);
						if(component instanceof Child_Compare){
							child_compare_current = (Child_Compare) component;
						}
					}*/
					
					if(child_search_main_current == null){
						Child_Search_Main child_search_main = new Child_Search_Main(mainpage);
						Mainpanel.removeAll();
						Mainpanel.add(child_search_main);
						child_search_main.SearchText.requestFocus();
						Mainpanel.updateUI();
						CurrentPage = 1;
					}
					else if(child_search_main_current != null && child_search_current == null){
						Mainpanel.removeAll();
						Mainpanel.add(child_search_main_current);
						Mainpanel.updateUI();
						CurrentPage = 1;
					}
					else if(child_search_current.textField.getText()== null || child_search_current.textField.getText().trim().equals("")){
						Child_Search_Main child_search_main = new Child_Search_Main(mainpage);
						Mainpanel.removeAll();
						Mainpanel.add(child_search_main);
						child_search_main.SearchText.requestFocus();
						Mainpanel.updateUI();
						CurrentPage = 1;
					}
					else if(child_search_current != null){
						Mainpanel.removeAll();
						Mainpanel.add(child_search_current);
						Mainpanel.updateUI();
						CurrentPage = 11;
					}
				}
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				Search.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
				Search.setIcon(new ImageIcon(MainPage.class.getResource("/EasyBBK_Swing/image/Search_click1.png")));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				if(Search_flag == false)
				Search.setIcon(new ImageIcon(MainPage.class.getResource("/EasyBBK_Swing/image/Search1.png")));
			}
		});
		GreenBar.add(Search);
		
		Design = new JLabel();
		Design.setIcon(new ImageIcon(MainPage.class.getResource("/EasyBBK_Swing/image/Design1.png")));
		Design.setBounds(755, 0, 150, 58);
		Design.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getButton() == e.BUTTON1){
					if(CurrentPage == 2) return;
					
					Design_flag = true;
					Search_flag = false;
					Upload_flag = false;
					Compare_flag = false;
					Design.setIcon(new ImageIcon(MainPage.class.getResource("/EasyBBK_Swing/image/Design_click1.png")));
					Search.setIcon(new ImageIcon(MainPage.class.getResource("/EasyBBK_Swing/image/Search1.png")));
					Upload.setIcon(new ImageIcon(MainPage.class.getResource("/EasyBBK_Swing/image/Upload1.png")));
					Compare.setIcon(new ImageIcon(MainPage.class.getResource("/EasyBBK_Swing/image/Compare.png")));
					
					if(CurrentPage == 1){
						Component component = Mainpanel.getComponent(0);
						if(component instanceof Child_Search_Main){
							child_search_main_current = (Child_Search_Main) component;
						}
					}
					
					if(CurrentPage == 11){
						Component component = Mainpanel.getComponent(0);
						if(component instanceof Child_Search){
							child_search_current = (Child_Search) component;
						}
					}
					
					if(CurrentPage == 3){
						Component component = Mainpanel.getComponent(0);
						if(component instanceof Child_Upload){
							child_upload_current = (Child_Upload) component;
						}
					}
					
					/*if(CurrentPage == 4){
						Component component = Mainpanel.getComponent(0);
						if(component instanceof Child_Compare){
							child_compare_current = (Child_Compare) component;
						}
					}*/
					
					if(child_design_current == null){
						Child_Design child_design = new Child_Design(mainpage);
						Mainpanel.removeAll();
						Mainpanel.add(child_design);
						Mainpanel.updateUI();
						CurrentPage = 2;
					}
					else if(child_design_current != null){
						Mainpanel.removeAll();
						Mainpanel.add(child_design_current);
						Mainpanel.updateUI();
						CurrentPage = 2;
					}
				}
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				Design.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
				Design.setIcon(new ImageIcon(MainPage.class.getResource("/EasyBBK_Swing/image/Design_click1.png")));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				if(Design_flag == false)
				Design.setIcon(new ImageIcon(MainPage.class.getResource("/EasyBBK_Swing/image/Design1.png")));
			}
		});
		GreenBar.add(Design);
		
		Upload = new JLabel();
		Upload.setIcon(new ImageIcon(MainPage.class.getResource("/EasyBBK_Swing/image/Upload1.png")));
		Upload.setBounds(937, 0, 150, 58);
		Upload.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getButton() == e.BUTTON1){
					if(CurrentPage == 3) return;
					
					Upload_flag = true;
					Search_flag = false;
					Design_flag = false;
					Compare_flag = false;
					Upload.setIcon(new ImageIcon(MainPage.class.getResource("/EasyBBK_Swing/image/Upload_click1.png")));
					Search.setIcon(new ImageIcon(MainPage.class.getResource("/EasyBBK_Swing/image/Search1.png")));
					Design.setIcon(new ImageIcon(MainPage.class.getResource("/EasyBBK_Swing/image/Design1.png")));
					Compare.setIcon(new ImageIcon(MainPage.class.getResource("/EasyBBK_Swing/image/Compare.png")));
					
					if(CurrentPage == 1){
						Component component = Mainpanel.getComponent(0);
						if(component instanceof Child_Search_Main){
							child_search_main_current = (Child_Search_Main) component;
						}
					}
					
					if(CurrentPage == 11){
						Component component = Mainpanel.getComponent(0);
						if(component instanceof Child_Search){
							child_search_current = (Child_Search) component;
						}
					}
					
					if(CurrentPage == 2){
						Component component = Mainpanel.getComponent(0);
						if(component instanceof Child_Design){
							child_design_current = (Child_Design) component;
						}
					}
					
					/*if(CurrentPage == 4){
						Component component = Mainpanel.getComponent(0);
						if(component instanceof Child_Compare){
							child_compare_current = (Child_Compare) component;
						}
					}*/
					
					if(child_upload_current == null){
						Child_Upload child_upload = new Child_Upload(mainpage);
						Mainpanel.removeAll();
						Mainpanel.add(child_upload);
						Mainpanel.updateUI();
						CurrentPage = 3;
					}
					else if(child_upload_current != null){
						Mainpanel.removeAll();
						Mainpanel.add(child_upload_current);
						Mainpanel.updateUI();
						CurrentPage = 3;
					}
				}
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				Upload.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
				Upload.setIcon(new ImageIcon(MainPage.class.getResource("/EasyBBK_Swing/image/Upload_click1.png")));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				if(Upload_flag == false)
				Upload.setIcon(new ImageIcon(MainPage.class.getResource("/EasyBBK_Swing/image/Upload1.png")));
			}
		});
		GreenBar.add(Upload);
		
		child_main = new Child_Main(this);
		Mainpanel.add(child_main);
		
		Compare = new JLabel();
		Compare.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getButton() == e.BUTTON1){
					if(CurrentPage == 4) return;
					
					Compare_flag = true;
					Upload_flag = false;
					Search_flag = false;
					Design_flag = false;
					Compare.setIcon(new ImageIcon(MainPage.class.getResource("/EasyBBK_Swing/image/Compare_click.png")));
					Upload.setIcon(new ImageIcon(MainPage.class.getResource("/EasyBBK_Swing/image/Upload1.png")));
					Search.setIcon(new ImageIcon(MainPage.class.getResource("/EasyBBK_Swing/image/Search1.png")));
					Design.setIcon(new ImageIcon(MainPage.class.getResource("/EasyBBK_Swing/image/Design1.png")));
					
					if(CurrentPage == 1){
						Component component = Mainpanel.getComponent(0);
						if(component instanceof Child_Search_Main){
							child_search_main_current = (Child_Search_Main) component;
						}
					}
					
					if(CurrentPage == 11){
						Component component = Mainpanel.getComponent(0);
						if(component instanceof Child_Search){
							child_search_current = (Child_Search) component;
						}
					}
					
					if(CurrentPage == 2){
						Component component = Mainpanel.getComponent(0);
						if(component instanceof Child_Design){
							child_design_current = (Child_Design) component;
						}
					}
					
					if(CurrentPage == 3){
						Component component = Mainpanel.getComponent(0);
						if(component instanceof Child_Upload){
							child_upload_current = (Child_Upload) component;
						}
					}
					
					
					if(child_search_current == null){
						Child_Compare child_compare = new Child_Compare(mainpage, null);
						Mainpanel.removeAll();
						Mainpanel.add(child_compare);
						Mainpanel.updateUI();
						CurrentPage = 4;
					}else if(child_search_current != null){
						Child_Compare child_compare = new Child_Compare(mainpage);
						Mainpanel.removeAll();
						Mainpanel.add(child_compare);
						Mainpanel.updateUI();
						CurrentPage = 4;
					}
				}
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				Compare.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
				Compare.setIcon(new ImageIcon(MainPage.class.getResource("/EasyBBK_Swing/image/Compare_click.png")));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				if(Compare_flag == false)
					Compare.setIcon(new ImageIcon(MainPage.class.getResource("/EasyBBK_Swing/image/Compare.png")));
			}
		});
		Compare.setBounds(580, 0, 150, 58);
		Compare.setIcon(new ImageIcon(MainPage.class.getResource("/EasyBBK_Swing/image/Compare.png")));
		GreenBar.add(Compare);
		
		Mainpanel.updateUI();
	}
}