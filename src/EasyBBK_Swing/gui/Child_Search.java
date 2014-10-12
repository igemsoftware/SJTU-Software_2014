package EasyBBK_Swing.gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JScrollBar;
import javax.swing.JTextField;

import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.JScrollPane;

import data_center.*;

public class Child_Search extends JPanel {
	public JTextField textField;
	public JPanel Result;
	public JLabel Search;
	public MainPage mainpage;
	public JScrollPane scrollPane;
	public JScrollPane scrollPane1;
	private JLabel Blast;
	public JPanel Details;
	public JLabel page6;
	public JLabel page7;
	public JLabel page8;
	public JLabel page9;
	public JLabel page10;
	public int currentpage = 1;
	public SearchingResultPage searchingresultpage;
	public JScrollBar scrollbar;
	public JScrollBar scrollbar1;
	public SearchCenter searchcenter;
	public Information information;
	public int blast;
	public JPanel resultpanel;
	public JLabel previouspage;
	public JLabel nextpage;
	public JLabel totalpagenum;
	public JLabel showpagenum;
	public boolean confirmed_clicked;
	public Child_Search child_search;
	public String keyword;
	public ArrayList<BbkDetail> comparisonlist = new ArrayList<BbkDetail>();
	/**
	 * Create the panel.
	 */
	public Child_Search(MainPage mainpage1, String searchcontent, Information information1, int blast1, boolean confirmed_clicked1) {
		child_search = this;
		confirmed_clicked = confirmed_clicked1;
		searchcenter = new SearchCenter();
		keyword = searchcontent;
		blast = blast1;
		information = information1;
		mainpage = mainpage1;
		setBounds(0, 0, 1366, 670);
		setBackground(new Color(255, 255, 255));
		setLayout(null);
		setVisible(true);
		
		Result = new JPanel();
		Result.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getButton() == e.BUTTON1)
					Result.requestFocus();
			}
		});
		Result.setBounds(0, 0, 683, 670);
		Result.setBackground(new Color(255, 255, 255));
		Result.setBorder(BorderFactory.createLineBorder(Color.black));
		add(Result);
		Result.setLayout(null);
		
		final JLabel Back = new JLabel();
		Back.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getButton() == e.BUTTON1){
					SearchResultList previousList = searchcenter.rollBack();
					if(previousList == null) return;
					else{
						requestFocus();
						textField.setText(previousList.keyword);
						resultpanel.removeAll();
						InitializeResultPage initializeresultpage = new InitializeResultPage(child_search, previousList.keyword, true, previousList);
						initializeresultpage.start();
						resultpanel.updateUI();
					}
				}
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				Back.setIcon(new ImageIcon(MainPage.class.getResource("/EasyBBK_Swing/image/SearchBox_backward_Hock.png")));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				Back.setIcon(new ImageIcon(MainPage.class.getResource("/EasyBBK_Swing/image/SearchBox_backward.png")));
			}
		});
		Back.setBounds(0, 0, 58, 55);
		Back.setIcon(new ImageIcon(MainPage.class.getResource("/EasyBBK_Swing/image/SearchBox_backward.png")));
		Result.add(Back);
		
		final JLabel Forward = new JLabel();
		Forward.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getButton() == e.BUTTON1){
					SearchResultList forwardList = searchcenter.goForward();
					if(forwardList == null) return;
					else{
						requestFocus();
						textField.setText(forwardList.keyword);
						resultpanel.removeAll();
						InitializeResultPage initializeresultpage = new InitializeResultPage(child_search, forwardList.keyword, true, forwardList);
						initializeresultpage.start();
						resultpanel.updateUI();
					}
				}
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				Forward.setIcon(new ImageIcon(MainPage.class.getResource("/EasyBBK_Swing/image/SearchBox_forward_Hock.png")));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				Forward.setIcon(new ImageIcon(MainPage.class.getResource("/EasyBBK_Swing/image/SearchBox_forward.png")));
			}
		});
		Forward.setBounds(58, 0, 54, 55);
		Forward.setIcon(new ImageIcon(MainPage.class.getResource("/EasyBBK_Swing/image/SearchBox_forward.png")));
		Result.add(Forward);

		Search = new JLabel();
		Search.setBounds(490, 0, 104, 53);
		Search.setIcon(new ImageIcon(MainPage.class.getResource("/EasyBBK_Swing/image/SearchBox_searchButton.png")));
		Search.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getButton() == e.BUTTON1){
					if(textField.getText().equals("")){
						Component component = mainpage.Mainpanel.getComponent(0);
						if(component instanceof Child_Search){
							mainpage.child_search_current = (Child_Search) component;
							Child_Search_Main child_search_main = new Child_Search_Main(mainpage);
							mainpage.Mainpanel.removeAll();
							mainpage.Mainpanel.add(child_search_main);
							mainpage.Mainpanel.updateUI();
							mainpage.CurrentPage = 1;
						}
						return;
					}
					else{
						requestFocus();
						currentpage = 1;
						blast = 1;
						InitializeResultPage initializeresultpage = new InitializeResultPage(child_search, textField.getText(), false);
						initializeresultpage.start();
					}
				}
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				Search.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
				Search.setIcon(new ImageIcon(MainPage.class.getResource("/EasyBBK_Swing/image/SearchBox_searchButton_Hock.png")));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				Search.setIcon(new ImageIcon(MainPage.class.getResource("/EasyBBK_Swing/image/SearchBox_searchButton.png")));
			}
		});
		Result.add(Search);
		
		textField = new JTextField();
		textField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyChar() == e.VK_ENTER){
					if(textField.getText().equals("")){
						Component component = mainpage.Mainpanel.getComponent(0);
						if(component instanceof Child_Search){
							mainpage.child_search_current = (Child_Search) component;
							Child_Search_Main child_search_main = new Child_Search_Main(mainpage);
							mainpage.Mainpanel.removeAll();
							mainpage.Mainpanel.add(child_search_main);
							child_search_main.SearchText.requestFocus();
							mainpage.Mainpanel.updateUI();
							mainpage.CurrentPage = 1;
						}
						return;
					}
					else{
						Search.requestFocus();
						currentpage = 1;
						blast = 1;
						InitializeResultPage initializeresultpage = new InitializeResultPage(child_search, textField.getText(), false);
						initializeresultpage.start();
					}
				}
			}
		});
		textField.setFont(new Font("Arial", Font.PLAIN, 40));
		textField.setVisible(true);
		textField.setBounds(112, 0, 378, 54);
		Result.add(textField);
		textField.setColumns(20);
		
		resultpanel = new JPanel();
		resultpanel.setBackground(new Color(255, 255, 255));
		resultpanel.setBounds(51, 105, 576, 565);
		Result.add(resultpanel);
		resultpanel.setLayout(null);
		
		
		Blast = new JLabel("");
		Blast.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getButton() == e.BUTTON1){
					if(textField.getText().equals("")){
						Component component = mainpage.Mainpanel.getComponent(0);
						if(component instanceof Child_Search){
							mainpage.child_search_current = (Child_Search) component;
							Child_Search_Main child_search_main = new Child_Search_Main(mainpage);
							mainpage.Mainpanel.removeAll();
							mainpage.Mainpanel.add(child_search_main);
							mainpage.Mainpanel.updateUI();
							mainpage.CurrentPage = 1;
						}
						return;
					}
					else{
						requestFocus();
						currentpage = 1;
						blast = 2;
						InitializeResultPage initializeresultpage = new InitializeResultPage(child_search, textField.getText(), false);
						initializeresultpage.start();
					}
				}
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				Blast.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
				Blast.setIcon(new ImageIcon(MainPage.class.getResource("/EasyBBK_Swing/image/blast_enter.png")));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				Blast.setIcon(new ImageIcon(MainPage.class.getResource("/EasyBBK_Swing/image/blast.png")));
			}
		});
		Blast.setBounds(596, 0, 84, 53);
		Blast.setIcon(new ImageIcon(MainPage.class.getResource("/EasyBBK_Swing/image/blast.png")));
		Result.add(Blast);
		
		Details = new JPanel();
		Details.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Details.requestFocus();
			}
		});
		Details.setBounds(684, 0, 683, 670);
		Details.setBackground(new Color(255, 255, 255));
		Details.setBorder(BorderFactory.createLineBorder(Color.black));
		add(Details);
		Details.setLayout(null);
		
		updateUI();
		
		InitializeResultPage initializeresultpage = new InitializeResultPage(child_search, keyword, false);
		initializeresultpage.start();
	}
}