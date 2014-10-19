package EasyBBK_Swing.gui;

import java.awt.Cursor;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Child_Main extends JPanel {
	private static final long serialVersionUID = 1L;
	
	public JLabel Logo;
	public JLabel SearchDrawingBox;
	public JLabel DesignDrawingBox;
	public JLabel UploadDrawingBox;
	public JLabel CompareDrawingBox;
	public MainPage mainpage;
	public JLabel BackGround;
	/**
	 * Create the Child_Main panel.
	 */
	public Child_Main(MainPage mainpage1) {
		mainpage = mainpage1;
		setLayout(null);
		if(mainpage.small == false){
			setBounds(0, 0, 1366, 670);
		}
		else if(mainpage.small == true){
			setBounds(0, 0, 1280, 670);
		}
		setVisible(true);
		setBackground(new Color(255, 255, 255));
		
		BackGround = new JLabel("");
		
		Logo = new JLabel("");
		if(mainpage.small == false){
			Logo.setBounds(486, 35, 410, 187);
		}
		else if(mainpage.small == true){
			Logo.setBounds(455, 35, 410, 187);
		}
		Logo.setIcon(new ImageIcon(MainPage.class.getResource("/EasyBBK_Swing/image/Logo.png")));
		Logo.setVisible(true);
		add(Logo);
		
		SearchDrawingBox = new JLabel("");
		if(mainpage.small == false){
			SearchDrawingBox.setBounds(123, 327, 254, 293);
		}
		else if(mainpage.small == true){
			SearchDrawingBox.setBounds(115, 327, 254, 293);
		}
		SearchDrawingBox.setIcon(new ImageIcon(MainPage.class.getResource("/EasyBBK_Swing/image/StartPage_searching.png")));
		SearchDrawingBox.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				SearchDrawingBox.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
				SearchDrawingBox.setIcon(new ImageIcon(MainPage.class.getResource("/EasyBBK_Swing/image/StartPage_searching_back.png")));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				SearchDrawingBox.setIcon(new ImageIcon(MainPage.class.getResource("/EasyBBK_Swing/image/StartPage_searching.png")));
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getButton() == MouseEvent.BUTTON1){
					SearchDrawingBox.setIcon(new ImageIcon(MainPage.class.getResource("/EasyBBK_Swing/image/StartPage_searching.png")));
					mainpage.Search.setIcon(new ImageIcon(MainPage.class.getResource("/EasyBBK_Swing/image/Search_click1.png")));
					mainpage.Design.setIcon(new ImageIcon(MainPage.class.getResource("/EasyBBK_Swing/image/Design1.png")));
					mainpage.Upload.setIcon(new ImageIcon(MainPage.class.getResource("/EasyBBK_Swing/image/Upload1.png")));
					mainpage.Compare.setIcon(new ImageIcon(MainPage.class.getResource("/EasyBBK_Swing/image/Compare.png")));
					
					if(mainpage.child_search_main_current == null){
						Child_Search_Main child_search_main = new Child_Search_Main(mainpage);
						mainpage.Mainpanel.removeAll();
						mainpage.Mainpanel.add(child_search_main);
						child_search_main.SearchText.requestFocus();
						mainpage.Mainpanel.updateUI();
						mainpage.CurrentPage = 1;
					}
					else if(mainpage.child_search_main_current != null && mainpage.child_search_current == null){
						mainpage.Mainpanel.removeAll();
						mainpage.Mainpanel.add(mainpage.child_search_main_current);
						mainpage.Mainpanel.updateUI();
						mainpage.CurrentPage = 1;
					}
					else if(mainpage.child_search_current != null){
						mainpage.Mainpanel.removeAll();
						mainpage.Mainpanel.add(mainpage.child_search_current);
						mainpage.Mainpanel.updateUI();
						mainpage.CurrentPage = 11;
					}
				}
			}
		});
		SearchDrawingBox.setIcon(new ImageIcon(MainPage.class.getResource("/EasyBBK_Swing/image/StartPage_searching.png")));
		SearchDrawingBox.setVisible(true);
		BackGround.add(SearchDrawingBox);
		
		DesignDrawingBox = new JLabel("");
		if(mainpage.small == false){
			DesignDrawingBox.setBounds(705, 327, 254, 293);
		}
		else if(mainpage.small == true){
			DesignDrawingBox.setBounds(660, 327, 254, 293);
		}
		DesignDrawingBox.setIcon(new ImageIcon(MainPage.class.getResource("/EasyBBK_Swing/image/StartPage_painting.png")));
		DesignDrawingBox.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				DesignDrawingBox.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
				DesignDrawingBox.setIcon(new ImageIcon(MainPage.class.getResource("/EasyBBK_Swing/image/StartPage_painting_back1.png")));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				DesignDrawingBox.setIcon(new ImageIcon(MainPage.class.getResource("/EasyBBK_Swing/image/StartPage_painting.png")));
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getButton() == MouseEvent.BUTTON1){
					DesignDrawingBox.setIcon(new ImageIcon(MainPage.class.getResource("/EasyBBK_Swing/image/StartPage_painting.png")));
					mainpage.Search.setIcon(new ImageIcon(MainPage.class.getResource("/EasyBBK_Swing/image/Search1.png")));
					mainpage.Design.setIcon(new ImageIcon(MainPage.class.getResource("/EasyBBK_Swing/image/Design_click1.png")));
					mainpage.Upload.setIcon(new ImageIcon(MainPage.class.getResource("/EasyBBK_Swing/image/Upload1.png")));
					mainpage.Compare.setIcon(new ImageIcon(MainPage.class.getResource("/EasyBBK_Swing/image/Compare.png")));
					
					if(mainpage.child_design_current == null){
						Child_Design child_design = new Child_Design(mainpage);
						mainpage.Mainpanel.removeAll();
						mainpage.Mainpanel.add(child_design);
						mainpage.Mainpanel.updateUI();
						mainpage.CurrentPage = 2;
					}
					else if(mainpage.child_design_current != null){
						mainpage.Mainpanel.removeAll();
						mainpage.Mainpanel.add(mainpage.child_design_current);
						mainpage.Mainpanel.updateUI();
						mainpage.CurrentPage = 2;
					}
				}
			}
		});
		DesignDrawingBox.setIcon(new ImageIcon(MainPage.class.getResource("/EasyBBK_Swing/image/StartPage_painting.png")));
		DesignDrawingBox.setVisible(true);
		BackGround.add(DesignDrawingBox);
		
		UploadDrawingBox = new JLabel("");
		if(mainpage.small == false){
			UploadDrawingBox.setBounds(990, 327, 254, 293);
		}
		else if(mainpage.small == true){
			UploadDrawingBox.setBounds(930, 327, 254, 293);
		}
		UploadDrawingBox.setIcon(new ImageIcon(MainPage.class.getResource("/EasyBBK_Swing/image/StartPage_upload.png")));
		UploadDrawingBox.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				UploadDrawingBox.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
				UploadDrawingBox.setIcon(new ImageIcon(MainPage.class.getResource("/EasyBBK_Swing/image/StartPage_upload_back.png")));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				UploadDrawingBox.setIcon(new ImageIcon(MainPage.class.getResource("/EasyBBK_Swing/image/StartPage_upload.png")));
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getButton() == MouseEvent.BUTTON1){
					UploadDrawingBox.setIcon(new ImageIcon(MainPage.class.getResource("/EasyBBK_Swing/image/StartPage_upload.png")));
					mainpage.Search.setIcon(new ImageIcon(MainPage.class.getResource("/EasyBBK_Swing/image/Search1.png")));
					mainpage.Design.setIcon(new ImageIcon(MainPage.class.getResource("/EasyBBK_Swing/image/Design1.png")));
					mainpage.Upload.setIcon(new ImageIcon(MainPage.class.getResource("/EasyBBK_Swing/image/Upload_click1.png")));
					mainpage.Compare.setIcon(new ImageIcon(MainPage.class.getResource("/EasyBBK_Swing/image/Compare.png")));
					
					if(mainpage.child_upload_current == null){
						Child_Upload child_upload = new Child_Upload(mainpage);
						mainpage.Mainpanel.removeAll();
						mainpage.Mainpanel.add(child_upload);
						mainpage.Mainpanel.updateUI();
						mainpage.CurrentPage = 3;
					}
					else if(mainpage.child_upload_current != null){
						mainpage.Mainpanel.removeAll();
						mainpage.Mainpanel.add(mainpage.child_upload_current);
						mainpage.Mainpanel.updateUI();
						mainpage.CurrentPage = 3;
					}
				}
			}
		});
		UploadDrawingBox.setIcon(new ImageIcon(MainPage.class.getResource("/EasyBBK_Swing/image/StartPage_upload.png")));
		UploadDrawingBox.setVisible(true);
		BackGround.add(UploadDrawingBox);
		
		CompareDrawingBox = new JLabel("");
		if(mainpage.small == false){
			CompareDrawingBox.setBounds(412, 327, 254, 293);
		}
		else if(mainpage.small == true){
			CompareDrawingBox.setBounds(386, 327, 254, 293);
		}
		CompareDrawingBox.setIcon(new ImageIcon(MainPage.class.getResource("/EasyBBK_Swing/image/StartPage_compare.png")));
		CompareDrawingBox.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getButton() == MouseEvent.BUTTON1){
					CompareDrawingBox.setIcon(new ImageIcon(MainPage.class.getResource("/EasyBBK_Swing/image/StartPage_compare.png")));
					mainpage.Search.setIcon(new ImageIcon(MainPage.class.getResource("/EasyBBK_Swing/image/Search1.png")));
					mainpage.Design.setIcon(new ImageIcon(MainPage.class.getResource("/EasyBBK_Swing/image/Design1.png")));
					mainpage.Upload.setIcon(new ImageIcon(MainPage.class.getResource("/EasyBBK_Swing/image/Upload1.png")));
					mainpage.Compare.setIcon(new ImageIcon(MainPage.class.getResource("/EasyBBK_Swing/image/Compare_click.png")));

					if(mainpage.child_search_current == null){
						Child_Compare child_compare = new Child_Compare(mainpage, null);
						mainpage.Mainpanel.removeAll();
						mainpage.Mainpanel.add(child_compare);
						mainpage.Mainpanel.updateUI();
						mainpage.CurrentPage = 4;
					}
					else if(mainpage.child_search_current != null){
						Child_Compare child_compare = new Child_Compare(mainpage);
						mainpage.Mainpanel.removeAll();
						mainpage.Mainpanel.add(child_compare);
						mainpage.Mainpanel.updateUI();
						mainpage.CurrentPage = 4;
					}
				}
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				CompareDrawingBox.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
				CompareDrawingBox.setIcon(new ImageIcon(MainPage.class.getResource("/EasyBBK_Swing/image/StartPage_compare_back.png")));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				CompareDrawingBox.setIcon(new ImageIcon(MainPage.class.getResource("/EasyBBK_Swing/image/StartPage_compare.png")));
			}
		});
		CompareDrawingBox.setBackground(new Color(0,0,0,0));
		CompareDrawingBox.setVisible(true);
		BackGround.add(CompareDrawingBox);
		
		BackGround.setVisible(true);
		if(mainpage.small == false){
			BackGround.setBounds(0, 0, 1366, 670);
		}
		else if(mainpage.small == true){
			BackGround.setBounds(0, 0, 1280, 670);
		}
		BackGround.setIcon(new ImageIcon(MainPage.class.getResource("/EasyBBK_Swing/image/BackGround.png")));
		add(BackGround);
	}
}
