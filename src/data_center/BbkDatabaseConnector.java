package data_center;

import java.sql.*;
import java.util.ArrayList;

import data_center.BbkUpload.Twin;

public class BbkDatabaseConnector
{
	public final static String DRIVER = "com.mysql.jdbc.Driver";
	public final static String URL_SERVER = "jdbc:mysql://202.120.45.101:3306/igem14";
	public final static String USER_NAME = "igem14";
	public final static String PASSWORD = "bio34204348;";
	
    private static Connection connection = null;
    
    public static void connect()
    {
    	try 
        {	connection 
        		= DriverManager.getConnection(URL_SERVER, USER_NAME, PASSWORD);}
        catch (SQLException e) { e.printStackTrace(); }
    }
    
    private static void checkConnection()
    {	
    	try
    	{	if (connection == null || connection.isClosed())
				connect();
		} catch (SQLException e) {e.printStackTrace();}
    }

    /** Display the first column(min index is 1) and the column interested(the colNum) */
    public static void displayTable(String tableName, int colNum)
    {
        checkConnection();
    	
    	String cmdStr = "select * from " + tableName;
        System.out.println(cmdStr);
        try 
		{	Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(cmdStr);
	        while (resultSet.next())
	        	System.out.println(resultSet.getString(1) + "\t" + resultSet.getString(colNum));
	        resultSet.close(); 
		} catch (SQLException e) {e.printStackTrace();}
    }


    public static BbkOutline getOutlineByName(String name)
    {
    	checkConnection();
    	
    	String cmdStr = "select * from " + BbkDB.TABLE_MAIN + 
        		" where " + BbkDB.Header.Main.NAME + " = " + "'" + name + "'";
        BbkOutline bbkOutline = null;
        try 
		{	Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(cmdStr);
			if (resultSet.next())
				bbkOutline = new BbkOutline(resultSet);
			resultSet.close();
		} catch (SQLException e) {e.printStackTrace();}

        return bbkOutline;
    }

    public static BbkDetail getDetailByName(String name)
    {
    	checkConnection();
    	
    	BbkDetail bbkDetail = new BbkDetail();
        try 
		{	Statement statement = connection.createStatement();
			ResultSet resultSet;
			// main
			resultSet = statement.executeQuery("select * from " + BbkDB.TABLE_MAIN + 
					" where " + BbkDB.Header.Main.NAME + " = " + "'" + name + "'");
			if (resultSet.next())
				bbkDetail.fillData_main(resultSet);
			else
				return null;	// if don't have it in main, make it a null
			// category
			resultSet = statement.executeQuery("select * from " + BbkDB.TABLE_CATEGORIES + 
	        		" where " + BbkDB.Header.Main.NAME + " = " + "'" + name + "'");
			if (resultSet.next())
				bbkDetail.fillData_categories(resultSet);
			// deep_subparts
			resultSet = statement.executeQuery("select * from " + BbkDB.TABLE_DEEP_SUBPARTS + 
	        		" where " + BbkDB.Header.Main.NAME + " = " + "'" + name + "'");
			if (resultSet.next())
				bbkDetail.fillData_deepSubparts(resultSet);
			// features
			resultSet = statement.executeQuery("select * from " + BbkDB.TABLE_FEATURES + 
	        		" where " + BbkDB.Header.Main.NAME + " = " + "'" + name + "'");
			if (resultSet.next())
				bbkDetail.fillData_features(resultSet);
			// parameters
			resultSet = statement.executeQuery("select * from " + BbkDB.TABLE_PARAMETERS + 
	        		" where " + BbkDB.Header.Main.NAME + " = " + "'" + name + "'");
			if (resultSet.next())
				bbkDetail.fillData_parameters(resultSet);
			// specified_subparts
			resultSet = statement.executeQuery("select * from " + BbkDB.TABLE_SPECIFIED_SUBSCARS + 
	        		" where " + BbkDB.Header.Main.NAME + " = " + "'" + name + "'");
			if (resultSet.next())
				bbkDetail.fillData_specifiedSubscars(resultSet);
			// specified_subscars
			resultSet = statement.executeQuery("select * from " + BbkDB.TABLE_SPECIFIED_SUBPARTS + 
	        		" where " + BbkDB.Header.Main.NAME + " = " + "'" + name + "'");
			if (resultSet.next())
				bbkDetail.fillData_specifiedSubparts(resultSet);
			// twins
			resultSet = statement.executeQuery("select * from " + BbkDB.TABLE_TWINS + 
	        		" where " + BbkDB.Header.Main.NAME + " = " + "'" + name + "'");
			if (resultSet.next())
				bbkDetail.fillData_twins(resultSet);
			
			resultSet.close();
		} catch (SQLException e) {e.printStackTrace();}

        return bbkDetail;
    }
    
    public static BbkUpload.SpecifiedSubscar getScarByName(String name)
    {	
    	checkConnection();
    	
    	BbkUpload.SpecifiedSubscar subscar = null;
        try 
		{	Statement statement = connection.createStatement();
			ResultSet resultSet;
			resultSet = statement.executeQuery("select * from " + BbkDB.TABLE_SPECIFIED_SUBSCARS + 
					" where " + BbkDB.Header.SpecScar.NAME + " = " + "'" + name + "'");
			if (resultSet.next())
				subscar = new BbkUpload.SpecifiedSubscar(resultSet);
		} catch (SQLException e) {e.printStackTrace();}
        
        return subscar;
    }
    
    public static ArrayList<BbkUpload.Twin> findTwinsBySequence(String sequence)
    {	
    	checkConnection();
    	
    	ArrayList<BbkUpload.Twin> twins = new ArrayList<BbkUpload.Twin>();
    	try
    	{	Statement statement = connection.createStatement();
			ResultSet resultSet;
			resultSet = statement.executeQuery("select * from " + BbkDB.TABLE_MAIN + 
				" where " + BbkDB.Header.Main.SEQUENCE + " = " + "'" + sequence + "'");
    		while (resultSet.next())
    		{	BbkUpload.Twin twin = new Twin();
    			twin.twin = resultSet.getString(BbkDB.Header.Twin.TWIN);
    			twins.add(twin);
    		}
    	} catch (SQLException e) {e.printStackTrace();}
    	
    	return twins;
    }


    /** Search for a keyword without any limitation */
    public static SearchResultList search(String keyword)
    {
    	checkConnection();
    	
    	String[] tokens = keyword.split(" ");
    	SearchResultList resultList = new SearchResultList(), 
    					 tempList = new SearchResultList();
        try 
		{	Statement statement = connection.createStatement();
        	ResultSet resultSet;
			
        	boolean firstToken = true;	// "" doesn't count as firstToken
        	for (String token : tokens)
        	{	if (token.equals(""))
        			continue;	// skip the ""s
	        	resultSet = statement.executeQuery(
	        		"select * from " + BbkDB.TABLE_MAIN + " where "
	        			   + BbkDB.Header.Main.NAME + " like " + "'%" + token + "%'" +
					" OR " + BbkDB.Header.Main.SHORT_DESC + " like " + "'%" + token + "%'" + 
					" OR " + BbkDB.Header.Main.AUTHOR + " like " + "'%" + token + "%'" + 
					" OR " + BbkDB.Header.Main.NICKNAME + " like " + "'%" + token + "%'");
				while (resultSet.next())
				{	BbkOutline bbkOutline = new BbkOutline(resultSet);
					if (firstToken)	// 第一个关键词，只需将outline都加进列表即可
						tempList.add(bbkOutline);
					else	// 过滤，只留下前面关键词中搜到过的outline以实现关键词间的AND关系
						if (resultList.has(bbkOutline.name))
							tempList.add(bbkOutline);
				}
				resultList = tempList;
				tempList = new SearchResultList();
				firstToken = false;
				//System.out.println(resultList.size());
        	}
		} catch (SQLException e) {e.printStackTrace();}
        return resultList;
    }
    
    /** Upload a new bbk and get the odd num used to modify it later. 
     * The oddNum is stored in the ID of an bbk */
    public static String upload(BbkUpload bbkUpload)
    {	
    	// fix me
    	return null;
    }
    
    /** The oddNum is stored in the ID of an bbk */
    public static BbkUpload getBbkUploadByNameAndOddNum(String name, String oddNum)
    {	
    	// prevent getting the bbk not uploaded by EasyBbk
    	if ( !name.endsWith("_EasyBbk") )
    		return null;
    	
    	checkConnection();
    	
    	BbkUpload bbkUpload = new BbkUpload();
        try 
		{	Statement statement = connection.createStatement();
			ResultSet resultSet;
			// main
			resultSet = statement.executeQuery("select * from " + BbkDB.TABLE_MAIN + 
					" where " + BbkDB.Header.Main.NAME + " = " + "'" + name + "'" + 
					" AND " + BbkDB.Header.Main.ID + " = " + "'" + oddNum + "'");
			if (resultSet.next())
				bbkUpload.fillData_main(resultSet);
			else
				return null;	// if don't have it in main, make it a null
			// category
			resultSet = statement.executeQuery("select * from " + BbkDB.TABLE_CATEGORIES + 
	        		" where " + BbkDB.Header.Main.NAME + " = " + "'" + name + "'");
			if (resultSet.next())
				bbkUpload.fillData_categories(resultSet);
			// deep_subparts
			resultSet = statement.executeQuery("select * from " + BbkDB.TABLE_DEEP_SUBPARTS + 
	        		" where " + BbkDB.Header.Main.NAME + " = " + "'" + name + "'");
			if (resultSet.next())
				bbkUpload.fillData_deepSubparts(resultSet);
			// features
			resultSet = statement.executeQuery("select * from " + BbkDB.TABLE_FEATURES + 
	        		" where " + BbkDB.Header.Main.NAME + " = " + "'" + name + "'");
			if (resultSet.next())
				bbkUpload.fillData_features(resultSet);
			// parameters
			resultSet = statement.executeQuery("select * from " + BbkDB.TABLE_PARAMETERS + 
	        		" where " + BbkDB.Header.Main.NAME + " = " + "'" + name + "'");
			if (resultSet.next())
				bbkUpload.fillData_parameters(resultSet);
			// specified_subparts
			resultSet = statement.executeQuery("select * from " + BbkDB.TABLE_SPECIFIED_SUBSCARS + 
	        		" where " + BbkDB.Header.Main.NAME + " = " + "'" + name + "'");
			if (resultSet.next())
				bbkUpload.fillData_specifiedSubscars(resultSet);
			// specified_subscars
			resultSet = statement.executeQuery("select * from " + BbkDB.TABLE_SPECIFIED_SUBPARTS + 
	        		" where " + BbkDB.Header.Main.NAME + " = " + "'" + name + "'");
			if (resultSet.next())
				bbkUpload.fillData_specifiedSubparts(resultSet);
			// twins
			resultSet = statement.executeQuery("select * from " + BbkDB.TABLE_TWINS + 
	        		" where " + BbkDB.Header.Main.NAME + " = " + "'" + name + "'");
			if (resultSet.next())
				bbkUpload.fillData_twins(resultSet);
			
			resultSet.close();
		} catch (SQLException e) {e.printStackTrace();}
    	
    	return bbkUpload;
    }

}

