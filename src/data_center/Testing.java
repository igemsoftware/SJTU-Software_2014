package data_center;

import javax.swing.*;

public class Testing extends JFrame
{
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
    
	public static void main(String[] args)
	{	
		new Testing();
		//BbkDatabaseConnector conncetor = new BbkDatabaseConnector();
		//BbkOutline bbkOutline = conncetor.getOutlineByName("BBa_B0014");
		//bbkOutline.display();
		//System.out.println("Hello world!");
	}
}
