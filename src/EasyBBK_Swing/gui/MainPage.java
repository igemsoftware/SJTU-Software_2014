package EasyBBK_Swing.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics2D;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.Insets;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.Canvas;
import java.awt.image.BufferedImage;
import java.awt.Dialog.ModalExclusionType;

import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.LayoutStyle;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

public class MainPage{

	private JFrame frame;
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
		Dimension scrSize = Toolkit.getDefaultToolkit().getScreenSize();    
		Insets scrInsets=Toolkit.getDefaultToolkit().getScreenInsets(GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDefaultConfiguration());
		frame.setBounds(scrInsets.left,scrInsets.top,scrSize.width-scrInsets.left-scrInsets.right,scrSize.height-scrInsets.top-scrInsets.bottom);
		frame.setUndecorated(true);
		
		JDesktopPane desk = new JDesktopPane();
		Container MainPanel = frame.getContentPane();
		desk.setBounds(0, 58, 1366, 670);
		desk.setLayout(new BorderLayout());
		//desk.setBackground(Color.black);
		MainPanel.add(desk);
		Child_Main child_main = new Child_Main();
		desk.add(child_main.frame, "Center");;
		
		JLabel GreenBar = new JLabel(" ");
		GreenBar.setOpaque(true);
		GreenBar.setBounds(0, 0, 1366, 58);
		Color green = new Color(40, 159, 57);
		GreenBar.setBackground(green);
		frame.getContentPane().add(GreenBar);
		
		JLabel Home = new JLabel();
		Home.setIcon(new ImageIcon(MainPage.class.getResource("/EasyBBK_Swing/image/home.png")));
		Home.setBounds(174, 13, 44, 42);
		Home.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				Home.setIcon(new ImageIcon(MainPage.class.getResource("/EasyBBK_Swing/image/home_Hover.png")));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				Home.setIcon(new ImageIcon(MainPage.class.getResource("/EasyBBK_Swing/image/home.png")));
			}
		});
		GreenBar.add(Home);
		
		JLabel Close = new JLabel();
		Close.setIcon(new ImageIcon(MainPage.class.getResource("/EasyBBK_Swing/image/Close1.png")));
		Close.setBounds(1324, 13, 30, 30);
		Close.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
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
		
		JLabel Min = new JLabel();
		Min.setIcon(new ImageIcon(MainPage.class.getResource("/EasyBBK_Swing/image/Min1.png")));
		Min.setBounds(1288, 13, 30, 30);
		Min.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
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
		
		JLabel Search = new JLabel();
		Search.setIcon(new ImageIcon(MainPage.class.getResource("/EasyBBK_Swing/image/Search1.png")));
		Search.setBounds(522, 17, 100, 40);
		Search.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				Search.setIcon(new ImageIcon(MainPage.class.getResource("/EasyBBK_Swing/image/Search_click1.png")));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				Search.setIcon(new ImageIcon(MainPage.class.getResource("/EasyBBK_Swing/image/Search1.png")));
			}
		});
		GreenBar.add(Search);
		
		JLabel Design = new JLabel();
		Design.setIcon(new ImageIcon(MainPage.class.getResource("/EasyBBK_Swing/image/Design1.png")));
		Design.setBounds(638, 17, 100, 40);
		Design.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				Design.setIcon(new ImageIcon(MainPage.class.getResource("/EasyBBK_Swing/image/Design_click1.png")));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				Design.setIcon(new ImageIcon(MainPage.class.getResource("/EasyBBK_Swing/image/Design1.png")));
			}
		});
		GreenBar.add(Design);
		
		JLabel Upload = new JLabel();
		Upload.setIcon(new ImageIcon(MainPage.class.getResource("/EasyBBK_Swing/image/Upload1.png")));
		Upload.setBounds(754, 17, 100, 40);
		Upload.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				Upload.setIcon(new ImageIcon(MainPage.class.getResource("/EasyBBK_Swing/image/Upload_click1.png")));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				Upload.setIcon(new ImageIcon(MainPage.class.getResource("/EasyBBK_Swing/image/Upload1.png")));
			}
		});
		GreenBar.add(Upload);
	}
}
