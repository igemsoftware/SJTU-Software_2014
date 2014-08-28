package data_center;

import java.sql.*;

public class BbkDatabaseConnector
{
	public final static String DRIVER = "com.mysql.jdbc.Driver";
	public String user = "root";
	public String password = "123456";
	public final static String URL_SERVER_CW = "jdbc:mysql://192.168.191.1/mydb";
	
	public final static String TABLE_MAIN = "main";
	public final static String TABLE_CATEGORIES = "categories";
	public final static String TABLE_DEEP_SUBPARTS = "deep_subparts";
	public final static String TABLE_FEATURES = "features";
	public final static String TABLE_PARAMETERS = "parameters";
	public final static String TABLE_SPECIFIED_SUBPARTS = "specified_subparts";
	public final static String TABLE_SPECIFIED_SUBSCARS = "specified_subscars";
	public final static String TABLE_TWINS = "twins";
	
	/*
	// used for C#
    public final static String URL_SERVER_CWJ =
        "Database = mydb; Data Source = 192.168.137.1; "
         + "User Id = root; Password = root; pooling = false; "
         + "CharSet = utf8; port=3306";
    public final static String URL_SERVER_CW =
        "Database = mydb; Data Source = 192.168.191.1; "
         + "User Id = root; Password = 123456; pooling = false; "
         + "CharSet = utf8; port = 3306";
    public final static String URL_LOCAL = "";
    */

    Connection connection;

    public BbkDatabaseConnector()
    {
    	try 
    	{	Class.forName(DRIVER);}
    	catch (ClassNotFoundException e) {}
        try 
        {	connection = DriverManager.getConnection(URL_SERVER_CW,user,password);}
        catch (SQLException e) {}
    }

    public void displayTable(String tableName)
    {
        String cmdStr = "select * from " + tableName;
        System.out.println(cmdStr);
        
        Statement statement;
		try 
		{	statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(cmdStr);

	        while (resultSet.next())
	        {	BbkOutline bbkOutline = new BbkOutline(resultSet);
	            bbkOutline.display();
	        }
	        resultSet.close(); 
		} catch (SQLException e) {e.printStackTrace();}

        System.out.println(cmdStr);
    }


    public BbkOutline getOutlineByName(String name)
    {
        String cmdStr = "select * from " + TABLE_MAIN + 
        		" where " + TableHeader.NAME + " = " + "'" + name + "'";
        BbkOutline bbkOutline = null;
        try 
		{	Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(cmdStr);
			resultSet.next();
			bbkOutline = new BbkOutline(resultSet);
			resultSet.close();
		} catch (SQLException e) {e.printStackTrace();}

        return bbkOutline;
    }

    public BbkDetail getDetailByName(String name)
    {
        // fix me
		BbkDetail detail = new BbkDetail();

        return detail;
    }


    public SearchResultList search(String keyword)
    {
        // fix me
        SearchResultList result = new SearchResultList();
        return result;
    }

    

}

