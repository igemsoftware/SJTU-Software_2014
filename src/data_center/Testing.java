package data_center;

import java.awt.Color;
import java.awt.Font;
import java.awt.Point;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;

import javax.swing.*;

import data_center.SketchComponent.*;

public class Testing  extends JFrame
{	
	/*
	adadadada
	JTable tableList =null;
	String[] cols={"name","type","author","enterDate","shortDesc","url"};
    String[][] rows=null;
    public Testing()
    {
    	BbkDatabaseConnector conncetor = new BbkDatabaseConnector();
    	//SearchResultList resultList = conncetor.search("B0034");
    	SearchResultList resultList = conncetor.search("jcbraff","part_author");
    	rows = resultList.showSearchResult();
    	JPanel jp=(JPanel)this.getContentPane();
    	tableList = new JTable(rows,cols);
		JScrollPane jsp_table=new JScrollPane(tableList);
		jp.add(jsp_table);
		this.setTitle("Biobrick Search");
		this.setSize(600,400);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    */
	public static void main(String[] args)
	{	
		//Test for Search
		//new Testing();
		
		//SearchResultList list
			//= BbkBlaster.blast_local(BbkBlaster.DEFAULT_INFILE, BbkBlaster.MODE_INPUT_FILE_PATH);
		//for (int i = 0; i < 100; ++i)
			//list.get(i).display();
		
		
		BbkDatabaseConnector connector = new BbkDatabaseConnector();
		
		//Test for ...
		
//		ArrayList<Component> componentList = new ArrayList<Component>();
//		componentList.add(new Label(0, "Lable text", 
//				new Point(5, 5), new Font("Times Roman", 10, 3), new Color(0, 0, 0)));
//		componentList.add(new BioBrick(1, BbkType.Sketch.BioBrick.PROMOTER, 
//				new Point(10, 10), null));
//		componentList.add(new Protein(2, BbkType.Sketch.Protein.FACTOR, 
//				new Point(20, 20), Color.BLUE));
//		componentList.add(new BackBone(3, new Point(50, 50), new Point(100, 50)));
//		componentList.add(new Relation(4, BbkType.Sketch.Relation.SUPPRESS, 
//				new ArrayList<Point>(), new Color(50, 50, 50), 10));
//		componentList.add(new Vector(5, BbkType.Sketch.Vector.BACTERIA, 
//				new Point(300, 300), 3));
		// 假设现在有这么一个componentList，需要将整个列表写入XML文件并后续再次读取。
		// 注意里面的Color有两种表示方式Color(x, x, x)或者Color.BLACK，但记录在文件中应该
		// 		是一样的形式
		// 另外不同component类型（如列表中第1,2个加入的Label与BioBrick）也需要记录。这在
		// 		数据结构中记录在父类component的primaryType中
		// 不妨仔细看一下SketchComponent里面的结构
		// 玮哥哥请完全无视本文件中的所有内容~
	}
}
