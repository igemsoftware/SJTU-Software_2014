package data_center;

import java.sql.*;

public class BbkDatabaseConnector
{
	public final static String DRIVER = "com.mysql.jdbc.Driver";
	public static String user = "root";
	public static String password = "root";
	public final static String URL_SERVER_CW = "jdbc:mysql://192.168.191.1/mydb";
	public final static String URL_SERVER_YJB = "jdbc:mysql://192.168.191.1/mydb";
	public final static String URL_SERVER_LOCAL = "jdbc:mysql://localhost:3306/mydb";
	
    private static Connection connection;
    
    public static void connect()
    {
    	try 
    	{	Class.forName(DRIVER);}
    	catch (ClassNotFoundException e) { e.printStackTrace(); }
        try 
        {	connection 
        		= DriverManager.getConnection(URL_SERVER_LOCAL, user, password);}
        catch (SQLException e) { e.printStackTrace(); }
    }

    public static void displayTable(String tableName)
    {
        String cmdStr = "select * from " + tableName;
        System.out.println(cmdStr);
        
        Statement statement;
		try 
		{	statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(cmdStr);

	        while (resultSet.next())
	        {	
	        	// fix me
	        	BbkOutline bbkOutline = new BbkOutline(resultSet);
	            bbkOutline.display();
	        }
	        resultSet.close(); 
		} catch (SQLException e) {e.printStackTrace();}

        System.out.println(cmdStr);
    }


    public static BbkOutline getOutlineByName(String name)
    {
        String cmdStr = "select * from " + BbkDB.TABLE_MAIN + 
        		" where " + BbkDB.Header.Main.NAME + " = " + "'" + name + "'";
        BbkOutline bbkOutline = new BbkOutline();
        try 
		{	Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(cmdStr);
			bbkOutline.fillData_main(resultSet);	// resultSet.next() in this function
			resultSet.close();
		} catch (SQLException e) {e.printStackTrace();}

        return bbkOutline;
    }

    public static BbkDetail getDetailByName(String name)
    {
        BbkDetail bbkDetail = new BbkDetail();
        
        try 
		{	Statement statement = connection.createStatement();
			ResultSet resultSet;
			
			resultSet = statement.executeQuery("select * from " + BbkDB.TABLE_MAIN + 
					" where " + BbkDB.Header.Main.NAME + " = " + "'" + name + "'");
			bbkDetail.fillData_main(resultSet);
			resultSet = statement.executeQuery("select * from " + BbkDB.TABLE_CATEGORIES + 
	        		" where " + BbkDB.Header.Main.NAME + " = " + "'" + name + "'");
			bbkDetail.fillData_categories(resultSet);
			resultSet = statement.executeQuery("select * from " + BbkDB.TABLE_DEEP_SUBPARTS + 
	        		" where " + BbkDB.Header.Main.NAME + " = " + "'" + name + "'");
			bbkDetail.fillData_deepSubparts(resultSet);
			resultSet = statement.executeQuery("select * from " + BbkDB.TABLE_FEATURES + 
	        		" where " + BbkDB.Header.Main.NAME + " = " + "'" + name + "'");
			bbkDetail.fillData_features(resultSet);
			resultSet = statement.executeQuery("select * from " + BbkDB.TABLE_PARAMETERS + 
	        		" where " + BbkDB.Header.Main.NAME + " = " + "'" + name + "'");
			bbkDetail.fillData_parameters(resultSet);
			resultSet = statement.executeQuery("select * from " + BbkDB.TABLE_SPECIFIED_SUBSCARS + 
	        		" where " + BbkDB.Header.Main.NAME + " = " + "'" + name + "'");
			bbkDetail.fillData_specifiedSubscars(resultSet);
			resultSet = statement.executeQuery("select * from " + BbkDB.TABLE_SPECIFIED_SUBPARTS + 
	        		" where " + BbkDB.Header.Main.NAME + " = " + "'" + name + "'");
			bbkDetail.fillData_specifiedSubparts(resultSet);
			resultSet = statement.executeQuery("select * from " + BbkDB.TABLE_TWINS + 
	        		" where " + BbkDB.Header.Main.NAME + " = " + "'" + name + "'");
			bbkDetail.fillData_twins(resultSet);
			
			resultSet.close();
		} catch (SQLException e) {e.printStackTrace();}

        return bbkDetail;
    }


    //Search for a keyword without any limitation
    public static SearchResultList search(String keyword)
    {
        SearchResultList result = new SearchResultList();
        try 
		{	
        	Statement statement = connection.createStatement();
        	ResultSet resultSet;
			
        	//Actually, I try to realize FULLTEXT INDEX search.
        	//But it can only search word like 'apple', or slice like 'pple' will failed.
        	//So, I realize the simple 'like' matching, you can add any other condition by using 'OR...'
			resultSet = statement.executeQuery("select * from " + BbkDB.TABLE_MAIN + 
        		" where " + BbkDB.Header.Main.NAME + " like " + "'%" + keyword + "%'" +
				" OR " + BbkDB.Header.Main.SHORT_DESC + " like " + "'%" + keyword + "%'" +
				" OR " + BbkDB.Header.Main.ID + " like " + "'%" + keyword + "%'");
			while (resultSet.next())
				result.add(new BbkOutline(resultSet));
		} catch (SQLException e) {e.printStackTrace();}
        return result;
    }

    public static SearchResultList search(String keyword, String type)
    {
        // fix me
        SearchResultList result = new SearchResultList();
        try 
		{	
        	Statement statement = connection.createStatement();
        	ResultSet resultSet;
			
			resultSet = statement.executeQuery("select * from " + BbkDB.TABLE_MAIN + 
        		" where " + type + " like " + "'%" + keyword + "%'");
			while (resultSet.next())
				result.add(new BbkOutline(resultSet));
		} catch (SQLException e) {e.printStackTrace();}
        return result;
    }
    
    /** Upload a new bbk and get the odd num used to modify it later */
    public static String upload(BbkUpload bbkUpload)
    {	
    	// fix me
    	return null;
    }
    
    public static BbkUpload getUploadedBbkByOddNum(String oddNum)
    {	
    	// fix me
    	return null;
    }

}

