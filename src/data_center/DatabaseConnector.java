package data_center;

import java.sql.*;
import java.util.ArrayList;

import data_center.BbkUpload.Twin;

/** The class to connect the EasyBbk and the database by SJTU-software.  */
public class DatabaseConnector
{
	@SuppressWarnings("unused")
	private final static String DRIVER = "com.mysql.jdbc.Driver";
	private final static String URL_SERVER = "jdbc:mysql://202.120.45.101:3306/igem14";
	private final static String USER_NAME = "igem14";
	private final static String PASSWORD = "bio34204348;";
	
    private static Connection connection = null;
    
    /** Connect to the database, don't need to be called in under 
     * normal circumstances.  */
    static void connect()
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

    /** Display the first column(min index is 1) and the column interested(the colNum) 
     * Current itemNum: Main(27027) Categories(11100) DeepSubparts(79843) 
     * 					Features(273151) Parameters(16731) Subparts(42029)
     * 					Subscars(23976) Twins(9055) */
    static void displayTable(String tableName, int colNum)
    {
        checkConnection();
    	
    	String cmdStr = "select * from " + tableName;
        System.out.println(cmdStr);
        int count = 0;
        try 
		{	Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(cmdStr);
	        while (resultSet.next())
	        {	System.out.println(resultSet.getString(1) + "\t" + resultSet.getString(colNum));
	        	++count;
	        }
	        resultSet.close(); 
	        System.out.println("\nTotal item: " + count);
		} catch (SQLException e) {e.printStackTrace();}
    }

    /** Specify the part_name as the parameter name, get the outline of a 
     * unique biobrick.  */
    public static BbkOutline getOutlineByName(String name)
    {
    	checkConnection();
    	
    	String cmdStr = 
    			" select * from " + DBConsts.Table.MAIN + 
        		" where " + DBConsts.Header.Main.NAME + " = " + "'" + name + "'";
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

    /** Specify the part_name as the parameter name, get the detail of a 
     * unique biobrick.  */
    public static BbkDetail getDetailByName(String name)
    {
    	checkConnection();
    	BbkDetail bbkDetail = new BbkDetail();
        try 
		{	Statement statement = connection.createStatement();
			ResultSet resultSet;
			// main
			resultSet = statement.executeQuery("select * from " + DBConsts.Table.MAIN + 
					" where " + DBConsts.Header.Main.NAME + " = " + "'" + name + "'");
			if (resultSet.next())
				DBDataFiller.dbIntoMain(resultSet, bbkDetail);
			else
				return null;	// if don't have it in main, make it a null
			// category
			resultSet = statement.executeQuery("select * from " + DBConsts.Table.CATEGORIES + 
	        		" where " + DBConsts.Header.Main.NAME + " = " + "'" + name + "'");
			DBDataFiller.dbIntoCategories(resultSet, bbkDetail);
			// deep_subparts
			resultSet = statement.executeQuery("select * from " + DBConsts.Table.DEEP_SUBPARTS + 
	        		" where " + DBConsts.Header.Main.NAME + " = " + "'" + name + "'");
			DBDataFiller.dbIntoDeepSubparts(resultSet, bbkDetail);
			// features
			resultSet = statement.executeQuery("select * from " + DBConsts.Table.FEATURES + 
	        		" where " + DBConsts.Header.Main.NAME + " = " + "'" + name + "'");
			DBDataFiller.dbIntoFeatures(resultSet, bbkDetail);
			// parameters
			resultSet = statement.executeQuery("select * from " + DBConsts.Table.PARAMETERS + 
	        		" where " + DBConsts.Header.Main.NAME + " = " + "'" + name + "'");
			DBDataFiller.dbIntoParameters(resultSet, bbkDetail);
			// specified_subparts
			resultSet = statement.executeQuery("select * from " + DBConsts.Table.SPECIFIED_SUBPARTS + 
	        		" where " + DBConsts.Header.Main.NAME + " = " + "'" + name + "'");
			DBDataFiller.dbIntoSpecifiedSubparts(resultSet, bbkDetail);
			// specified_subscars
			resultSet = statement.executeQuery("select * from " + DBConsts.Table.SPECIFIED_SUBSCARS + 
	        		" where " + DBConsts.Header.Main.NAME + " = " + "'" + name + "'");
			DBDataFiller.dbIntoSpecifiedSubscars(resultSet, bbkDetail);
			// twins
			resultSet = statement.executeQuery("select * from " + DBConsts.Table.TWINS + 
	        		" where " + DBConsts.Header.Main.NAME + " = " + "'" + name + "'");
			DBDataFiller.dbIntoTwins(resultSet, bbkDetail);
			
			resultSet.close();
		} catch (SQLException e) {e.printStackTrace();}

        return bbkDetail;
    }
    
    /** Used when fill the scar in the BbkUpload.  */
    public static BbkUpload.SpecifiedSubscar getScarByName(String name)
    {	
    	checkConnection();
    	
    	BbkUpload.SpecifiedSubscar subscar = null;
        try 
		{	Statement statement = connection.createStatement();
			ResultSet resultSet;
			resultSet = statement.executeQuery("select * from " + DBConsts.Table.SPECIFIED_SUBSCARS + 
					" where " + DBConsts.Header.SpecScar.NAME + " = " + "'" + name + "'");
			if (resultSet.next())	// just need one
				subscar = new BbkUpload.SpecifiedSubscar(resultSet);
		} catch (SQLException e) {e.printStackTrace();}
        
        return subscar;
    }
    
    /** Used when specifying a sequence to a bbkUpload. Can also used to perform
     * a sequence matching.  */
    public static ArrayList<BbkUpload.Twin> findTwinsBySequence(String sequence)
    {	
    	checkConnection();
    	
    	ArrayList<BbkUpload.Twin> twins = new ArrayList<BbkUpload.Twin>();
    	try
    	{	Statement statement = connection.createStatement();
			ResultSet resultSet;
			resultSet = statement.executeQuery("select * from " + DBConsts.Table.MAIN + 
				" where " + DBConsts.Header.Main.SEQUENCE + " = " + "'" + sequence + "'");
    		while (resultSet.next())
    		{	BbkUpload.Twin twin = new Twin();
    			twin.twin = resultSet.getString(DBConsts.Header.Main.NAME);
    			twins.add(twin);
    		}
    	} catch (SQLException e) {e.printStackTrace();}
    	
    	return twins;
    }


    /** Search for a keyword in the database by SJTU-software without any limitation. 
     * Keyword sensitive in: 
     * part_name, short_desc, part_type, part_author, part_nickname.  */
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
	        		"select * from " + DBConsts.Table.MAIN + " where "
	        			   + DBConsts.Header.Main.NAME + " like " + "'%" + token + "%'" +
					" OR " + DBConsts.Header.Main.SHORT_DESC + " like " + "'%" + token + "%'" +
					" OR " + DBConsts.Header.Main.TYPE + " like " + "'%" + token + "%'" + 
					" OR " + DBConsts.Header.Main.AUTHOR + " like " + "'%" + token + "%'" + 
					" OR " + DBConsts.Header.Main.NICKNAME + " like " + "'%" + token + "%'");
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
        	}
		} catch (SQLException e) {e.printStackTrace();}
        
        resultList.keyword = keyword;
        return resultList;
    }
    
    public static boolean isUploadingBbkNameNotOccupied(String rawName)
    {	
    	checkConnection();
    	
    	String nameByIgemOrg = "BBa_" + rawName;
		String nameByEasyBbk = "BBa_" + rawName + "_EasyBbk";
		boolean occupied = true;
		try 
		{	Statement statement = connection.createStatement();
			ResultSet resultSet_org, resultSet_EasyBbk;
			// main
			resultSet_org = statement.executeQuery("select * from " + DBConsts.Table.MAIN + 
					" where " + DBConsts.Header.Main.NAME + " = " + "'" + nameByIgemOrg + "'");
			occupied = resultSet_org.next();
			resultSet_org.close();
			resultSet_EasyBbk = statement.executeQuery("select * from " + DBConsts.Table.MAIN_UPLOAD + 
					" where " + DBConsts.Header.Main.NAME + " = " + "'" + nameByEasyBbk + "'");
			occupied =  (occupied || resultSet_EasyBbk.next());
			resultSet_EasyBbk.close();
		} catch (Exception e) {e.printStackTrace();}
		return !occupied;
    }
    
    /** Upload a new biobrick and get the odd number used to modify it later. 
     * The oddNum is stored in the ID of an biobrick */
    public static String upload(BbkUpload bbkUpload)
    {	
    	// prevent writing the bbk not generated by EasyBbk
    	if ( !bbkUpload.getName().endsWith("_EasyBbk") )
    		return null;
    	
    	checkConnection();
    	
    	try 
    	{	Statement statement = connection.createStatement();
    		DBDataFiller.mainIntoDb(bbkUpload, statement);
    		DBDataFiller.categoriesIntoDb(bbkUpload, statement);
    		DBDataFiller.deepSubpartsIntoDb(bbkUpload, statement);
    		DBDataFiller.featuresIntoDb(bbkUpload, statement);
    		DBDataFiller.parametersIntoDb(bbkUpload, statement);
    		DBDataFiller.specifiedSubpartsIntoDb(bbkUpload, statement);
    		DBDataFiller.specifiedSubscarsIntoDb(bbkUpload, statement);
    		DBDataFiller.twinsIntoDb(bbkUpload, statement);
		} catch (SQLException e) {e.printStackTrace();}
    	
    	
    	return bbkUpload.getID();
    }
    
    /** Use the part_name and the oddNum to find a previously uploaded biobrick. 
     * The oddNum is stored as the ID of an bbk */
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
			resultSet = statement.executeQuery("select *" + 
					" from " + DBConsts.Table.MAIN_UPLOAD + 
					" where " + DBConsts.Header.Main.NAME + " = " + "'" + name + "'" + 
					" AND " + DBConsts.Header.Main.ID + " = " + "'" + oddNum + "'");
			if (resultSet.next())
				DBDataFiller.dbIntoMain(resultSet, bbkUpload);
			else
				return null;	// if don't have it in main, make it a null
			// category
			resultSet = statement.executeQuery("select *" + 
					" from " + DBConsts.Table.CATEGORIES_UPLOAD + 
	        		" where " + DBConsts.Header.Main.NAME + " = " + "'" + name + "'");
			DBDataFiller.dbIntoCategories(resultSet, bbkUpload);
			// deep_subparts
			resultSet = statement.executeQuery("select *" + 
					" from " + DBConsts.Table.DEEP_SUBPARTS_UPLOAD + 
	        		" where " + DBConsts.Header.Main.NAME + " = " + "'" + name + "'");
			DBDataFiller.dbIntoDeepSubparts(resultSet, bbkUpload);
			// features
			resultSet = statement.executeQuery("select *" + 
					" from " + DBConsts.Table.FEATURES_UPLOAD + 
	        		" where " + DBConsts.Header.Main.NAME + " = " + "'" + name + "'");
			DBDataFiller.dbIntoFeatures(resultSet, bbkUpload);
			// parameters
			resultSet = statement.executeQuery("select *" + 
					" from " + DBConsts.Table.PARAMETERS_UPLOAD + 
	        		" where " + DBConsts.Header.Main.NAME + " = " + "'" + name + "'");
			DBDataFiller.dbIntoParameters(resultSet, bbkUpload);
			// specified_subparts
			resultSet = statement.executeQuery("select *" + 
					" from " + DBConsts.Table.SPECIFIED_SUBPARTS_UPLOAD + 
	        		" where " + DBConsts.Header.Main.NAME + " = " + "'" + name + "'");
			DBDataFiller.dbIntoSpecifiedSubparts(resultSet, bbkUpload);
			// specified_subscars
			resultSet = statement.executeQuery("select *" + 
					" from " + DBConsts.Table.SPECIFIED_SUBSCARS_UPLOAD + 
	        		" where " + DBConsts.Header.Main.NAME + " = " + "'" + name + "'");
			DBDataFiller.dbIntoSpecifiedSubscars(resultSet, bbkUpload);
			// twins
			resultSet = statement.executeQuery("select *" + 
					" from " + DBConsts.Table.TWINS_UPLOAD + 
	        		" where " + DBConsts.Header.Main.NAME + " = " + "'" + name + "'");
			DBDataFiller.dbIntoTwins(resultSet, bbkUpload);
			
			resultSet.close();
		} catch (SQLException e) {e.printStackTrace();}
    	
    	return bbkUpload;
    }

}

