package EasyBBK_Swing.gui;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;

import data_center.*;

public class InitializeResultPage extends Thread{
	public SearchResultList filteredlist;
	public JPanel resultpanel;
	public int blast;
	public boolean confirmed_clicked;
	public Information information;
	public SearchingResultPage searchingresultpage;
	
	public InitializeResultPage(SearchResultList searchresultlist, JPanel resultpanel1, int blast1, SearchingResultPage searchingresultpage1){
		filteredlist = searchresultlist;
		resultpanel = resultpanel1;
		blast = blast1;
		searchingresultpage = searchingresultpage1;
	}
	
	public void run(){
		
	}
}
