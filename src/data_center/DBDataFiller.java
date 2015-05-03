package data_center;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/** The class to fill data between database and local biobrick instances like 
 * BbkOutline, BbkDetail, BbkUpload. 
 * Can both fill data into database and into the bbks */
class DBDataFiller
{
	/** The main of bbkOutline or bbkDetail.  */
	public static void dbIntoMain(ResultSet resultSet, BbkOutline bbkOutline) throws SQLException
    {	
		bbkOutline.name = resultSet.getString(Consts_DB.Header.Main.NAME);
		bbkOutline.type = resultSet.getString(Consts_DB.Header.Main.TYPE);
		bbkOutline.author = resultSet.getString(Consts_DB.Header.Main.AUTHOR);
		bbkOutline.enterDate = resultSet.getString(Consts_DB.Header.Main.ENTER_DATE);
		bbkOutline.shortDesc = resultSet.getString(Consts_DB.Header.Main.SHORT_DESC);
		bbkOutline.url = resultSet.getString(Consts_DB.Header.Main.URL);
	    
		bbkOutline.rating = new BbkOutline.Rating(resultSet);
	    
		bbkOutline.ID = resultSet.getString(Consts_DB.Header.Main.ID);
		bbkOutline.shortName = resultSet.getString(Consts_DB.Header.Main.SHORT_NAME);
		bbkOutline.releaseStatus = resultSet.getString(Consts_DB.Header.Main.RELEASE_STATUS);
		bbkOutline.sampleStatus = resultSet.getString(Consts_DB.Header.Main.SAMPLE_STATUS);
		bbkOutline.results = resultSet.getString(Consts_DB.Header.Main.RESULTS);
		bbkOutline.nickname = resultSet.getString(Consts_DB.Header.Main.NICKNAME);
		bbkOutline.part_rating = resultSet.getString(Consts_DB.Header.Main.PART_RATING);
		bbkOutline.sequence = resultSet.getString(Consts_DB.Header.Main.SEQUENCE);
		bbkOutline.samples = resultSet.getString(Consts_DB.Header.Main.SAMPLES);
		bbkOutline.references = resultSet.getString(Consts_DB.Header.Main.REFERENCES);
		bbkOutline.groups = resultSet.getString(Consts_DB.Header.Main.GROUPS);
		bbkOutline.DNA_status = resultSet.getString(Consts_DB.Header.Main.DNA_STATUS);
		bbkOutline.groupFavorite = resultSet.getString(Consts_DB.Header.Main.GROUP_FAVOURITE);
    }
	
	/** Fill data from database, don't need url, rating parts etc..., cause
	 * they are initialized when newed, and are constants */
	public static void dbIntoMain(ResultSet resultSet, BbkUpload bbkUpload) throws SQLException
    {	
		bbkUpload.fillPrivateData(resultSet);
		bbkUpload.type = resultSet.getString(Consts_DB.Header.Main.TYPE);
		bbkUpload.author = resultSet.getString(Consts_DB.Header.Main.AUTHOR);
		bbkUpload.shortDesc = resultSet.getString(Consts_DB.Header.Main.SHORT_DESC);
		bbkUpload.nickname = resultSet.getString(Consts_DB.Header.Main.NICKNAME);
		bbkUpload.groupFavorite = resultSet.getString(Consts_DB.Header.Main.GROUP_FAVOURITE);
		bbkUpload.delete_this_part = resultSet.getString(Consts_DB.Header.Main.DELETE_THIS_PART);
    }
	
	
	

	public static void dbIntoCategories(ResultSet resultSet, Object bbk) throws SQLException
	{	
		if (bbk.getClass().equals(BbkDetail.class))
		{	BbkDetail bbkDetail = (BbkDetail) bbk;
			while (resultSet.next())
				bbkDetail.categories.add(new BbkDetail.Category(resultSet));
		}
		else if (bbk.getClass().equals(BbkUpload.class))
		{	BbkUpload bbkUpload = (BbkUpload) bbk;
			while (resultSet.next())
				bbkUpload.categories.add(new BbkUpload.Category(resultSet));
		}
	}
	
	public static void dbIntoDeepSubparts(ResultSet resultSet, Object bbk) throws SQLException
	{	
		if (bbk.getClass().equals(BbkDetail.class))
		{	BbkDetail bbkDetail = (BbkDetail) bbk;
			while (resultSet.next())
				bbkDetail.deepSubparts.add(new BbkDetail.DeepSubpart(resultSet));
		}
		else if (bbk.getClass().equals(BbkUpload.class))
		{	BbkUpload bbkUpload = (BbkUpload) bbk;
			while (resultSet.next())
				bbkUpload.deepSubparts.add(new BbkUpload.DeepSubpart(resultSet));
		}
	}
	
	public static void dbIntoFeatures(ResultSet resultSet, Object bbk) throws SQLException
	{	
		if (bbk.getClass().equals(BbkDetail.class))
		{	BbkDetail bbkDetail = (BbkDetail) bbk;
			while (resultSet.next())
				bbkDetail.features.add(new BbkDetail.Feature(resultSet));
		}
		else if (bbk.getClass().equals(BbkUpload.class))
		{	BbkUpload bbkUpload = (BbkUpload) bbk;
			while (resultSet.next())
				bbkUpload.features.add(new BbkUpload.Feature(resultSet));
		}
	}
	
	public static void dbIntoParameters(ResultSet resultSet, Object bbk) throws SQLException
	{	
		if (bbk.getClass().equals(BbkDetail.class))
		{	BbkDetail bbkDetail = (BbkDetail) bbk;
			while (resultSet.next())
				bbkDetail.parameters.add(new BbkDetail.Parameter(resultSet));
		}
		else if (bbk.getClass().equals(BbkUpload.class))
		{	BbkUpload bbkUpload = (BbkUpload) bbk;
			while (resultSet.next())
				bbkUpload.parameters.add(new BbkUpload.Parameter(resultSet));
		}
	}
	
	public static void dbIntoSpecifiedSubparts(ResultSet resultSet, Object bbk) throws SQLException
	{	
		if (bbk.getClass().equals(BbkDetail.class))
		{	BbkDetail bbkDetail = (BbkDetail) bbk;
			while (resultSet.next())
				bbkDetail.specifiedSubparts.add(new BbkDetail.SpecifiedSubpart(resultSet));
		}
		else if (bbk.getClass().equals(BbkUpload.class))
		{	BbkUpload bbkUpload = (BbkUpload) bbk;
			while (resultSet.next())
				bbkUpload.specifiedSubparts.add(new BbkUpload.SpecifiedSubpart(resultSet));
		}
	}
	
	public static void dbIntoSpecifiedSubscars(ResultSet resultSet, Object bbk) throws SQLException
	{	
		if (bbk.getClass().equals(BbkDetail.class))
		{	BbkDetail bbkDetail = (BbkDetail) bbk;
			while (resultSet.next())
				bbkDetail.specifiedSubscars.add(new BbkDetail.SpecifiedSubscar(resultSet));
		}
		else if (bbk.getClass().equals(BbkUpload.class))
		{	BbkUpload bbkUpload = (BbkUpload) bbk;
			while (resultSet.next())
				bbkUpload.specifiedSubscars.add(new BbkUpload.SpecifiedSubscar(resultSet));
		}
	}
	
	public static void dbIntoTwins(ResultSet resultSet, Object bbk) throws SQLException
	{	
		if (bbk.getClass().equals(BbkDetail.class))
		{	BbkDetail bbkDetail = (BbkDetail) bbk;
			while (resultSet.next())
				bbkDetail.twins.add(new BbkDetail.Twin(resultSet));
		}
		else if (bbk.getClass().equals(BbkUpload.class))
		{	BbkUpload bbkUpload = (BbkUpload) bbk;
			while (resultSet.next())
				bbkUpload.twins.add(new BbkUpload.Twin(resultSet));
		}
	}
	
	public static void dbIntoHalfFilledSearchResultList
		(ResultSet resultSet, SearchResultList list) throws SQLException
	{	
		while (resultSet.next())
		{	BbkOutline bbkOutline = 
				list.getByName(resultSet.getString(Consts_DB.Header.Main.NAME));
			if (bbkOutline == null)
				continue;
			else
				dbIntoMain(resultSet, bbkOutline);
		}
	}
	
	
	
	
	
	
	public static void mainIntoDb(BbkUpload bbkUpload, Statement statement) throws SQLException
	{	
		statement.execute("delete from " + Consts_DB.Table.MAIN_UPLOAD + " where " + 
				Consts_DB.Header.Main.NAME + " = " + "'" + bbkUpload.getName() + "'");
		statement.execute("insert into " + Consts_DB.Table.MAIN_UPLOAD + " " +
			"(" + Consts_DB.Header.Main.NAME + ", " +
				  Consts_DB.Header.Main.ID + ", " +
				  Consts_DB.Header.Main.ENTER_DATE + ", " + 
				  Consts_DB.Header.Main.SHORT_NAME + ", " +
				  Consts_DB.Header.Main.SEQUENCE + ", " + 
				  Consts_DB.Header.Main.TYPE + ", " + 
				  Consts_DB.Header.Main.AUTHOR + ", " + 
				  Consts_DB.Header.Main.SHORT_DESC + ", " + 
				  Consts_DB.Header.Main.NICKNAME + ", " + 
				  Consts_DB.Header.Main.GROUP_FAVOURITE + ", " +
				  Consts_DB.Header.Main.DELETE_THIS_PART + ")" + 
			" values " + 
			"(" + "'" + bbkUpload.getName() + "'" + ", " + 
				  "'" + bbkUpload.getID() + "'" + ", " +
				  "'" + bbkUpload.getEnterDate() + "'" + ", " + 
				  "'" + bbkUpload.getShortName() + "'" + ", " +
				  "'" + bbkUpload.getSequence() + "'" + ", " + 
				  "'" + bbkUpload.type + "'" + ", " + 
				  "'" + bbkUpload.author + "'" + ", " + 
				  "'" + bbkUpload.shortDesc + "'" + ", " + 
				  "'" + bbkUpload.nickname + "'" + ", " + 
				  "'" + bbkUpload.groupFavorite + "'" + ", " + 
				  "'" + bbkUpload.delete_this_part + "'" + ")");
	}
	
	public static void categoriesIntoDb(BbkUpload bbkUpload, Statement statement) throws SQLException
	{	
		statement.execute("delete from " + Consts_DB.Table.CATEGORIES_UPLOAD + " where " + 
				Consts_DB.Header.Main.NAME + " = " + "'" + bbkUpload.getName() + "'");
		for (BbkUpload.Category category : bbkUpload.categories)	
			statement.execute("insert into " + Consts_DB.Table.CATEGORIES_UPLOAD + " " +
				"(" + Consts_DB.Header.Category.CATEGORY + ")" + 
				" values " + 
				"(" + "'" + category.category + "'" + ")");
	}
	
	public static void deepSubpartsIntoDb(BbkUpload bbkUpload, Statement statement) throws SQLException
	{	
		statement.execute("delete from " + Consts_DB.Table.DEEP_SUBPARTS_UPLOAD + " where " + 
				Consts_DB.Header.Main.NAME + " = " + "'" + bbkUpload.getName() + "'");
		for (BbkUpload.DeepSubpart deepSubpart : bbkUpload.deepSubparts)	
			statement.execute("insert into " + Consts_DB.Table.DEEP_SUBPARTS_UPLOAD + " " +
				"(" + Consts_DB.Header.DeepSub.ID + ", " + 
					  Consts_DB.Header.DeepSub.NAME + ", " + 
					  Consts_DB.Header.DeepSub.NICKNAME + ", " + 
					  Consts_DB.Header.DeepSub.SHORT_DESC + ", " + 
					  Consts_DB.Header.DeepSub.TYPE + ")" + 
				" values " + 
				"(" + "'" + deepSubpart.ID + "'" + ", " +
					  "'" + deepSubpart.name + "'" + ", " +
					  "'" + deepSubpart.nickname + "'" + ", " +
					  "'" + deepSubpart.shortDesc + "'" + ", " +
					  "'" + deepSubpart.type + "'" + ")");
	}
	
	public static void featuresIntoDb(BbkUpload bbkUpload, Statement statement) throws SQLException
	{	
		statement.execute("delete from " + Consts_DB.Table.FEATURES_UPLOAD + " where " + 
				Consts_DB.Header.Main.NAME + " = " + "'" + bbkUpload.getName() + "'");
		for (BbkUpload.Feature feature : bbkUpload.features)	
			statement.execute("insert into " + Consts_DB.Table.FEATURES_UPLOAD + " " +
				"(" + Consts_DB.Header.Feature.DIRECTION + ", " + 
					  Consts_DB.Header.Feature.ID + ", " + 
					  Consts_DB.Header.Feature.START_POS + ", " + 
					  Consts_DB.Header.Feature.END_POS + ", " + 
					  Consts_DB.Header.Feature.TITLE + ", " + 
					  Consts_DB.Header.Feature.TYPE + ")" + 
				" values " + 
				"(" + "'" + feature.direction + "'" + ", " + 
					  "'" + feature.ID + "'" + ", " + 
					  "'" + feature.startPos + "'" + ", " + 
					  "'" + feature.endPos + "'" + ", " + 
					  "'" + feature.title + "'" + ", " + 
					  "'" + feature.type + "'" + ")");
	}
	
	public static void parametersIntoDb(BbkUpload bbkUpload, Statement statement) throws SQLException
	{	
		statement.execute("delete from " + Consts_DB.Table.PARAMETERS_UPLOAD + " where " + 
				Consts_DB.Header.Main.NAME + " = " + "'" + bbkUpload.getName() + "'");
		for (BbkUpload.Parameter parameter : bbkUpload.parameters)	
			statement.execute("insert into " + Consts_DB.Table.PARAMETERS_UPLOAD + " " +
				"(" + Consts_DB.Header.Parameter.ID + ", " + 
					  Consts_DB.Header.Parameter.M_DATE + ", " + 
					  Consts_DB.Header.Parameter.NAME + ", " + 
					  Consts_DB.Header.Parameter.UNITS + ", " + 
					  Consts_DB.Header.Parameter.URL + ", " + 
					  Consts_DB.Header.Parameter.USER_ID + ", " + 
					  Consts_DB.Header.Parameter.USER_NAME + ")" + 
				" values " + 
				"(" + "'" + parameter.ID + "'" + ", " + 
					  "'" + parameter.m_date + "'" + ", " + 
					  "'" + parameter.name + "'" + ", " + 
					  "'" + parameter.units + "'" + ", " + 
					  "'" + parameter.url + "'" + ", " + 
					  "'" + parameter.userID + "'" + ", " + 
					  "'" + parameter.userName + "'" + ")");
	}
	
	public static void specifiedSubpartsIntoDb(BbkUpload bbkUpload, Statement statement) throws SQLException
	{	
		statement.execute("delete from " + Consts_DB.Table.SPECIFIED_SUBPARTS_UPLOAD + " where " + 
				Consts_DB.Header.Main.NAME + " = " + "'" + bbkUpload.getName() + "'");
		for (BbkUpload.SpecifiedSubpart subpart : bbkUpload.specifiedSubparts)	
			statement.execute("insert into " + Consts_DB.Table.SPECIFIED_SUBPARTS_UPLOAD + " " +
				"(" + Consts_DB.Header.SpecSub.ID + ", " + 
					  Consts_DB.Header.SpecSub.NAME + ", " + 
					  Consts_DB.Header.SpecSub.NICKNAME + ", " + 
					  Consts_DB.Header.SpecSub.SHORT_DESC + ", " + 
					  Consts_DB.Header.SpecSub.TYPE + ")" + 
				" values " + 
				"(" + "'" + subpart.ID + "'" + ", " + 
					  "'" + subpart.name + "'" + ", " + 
					  "'" + subpart.nickname + "'" + ", " + 
					  "'" + subpart.shortDesc + "'" + ", " + 
					  "'" + subpart.type + "'" + ")");
	}
	
	public static void specifiedSubscarsIntoDb(BbkUpload bbkUpload, Statement statement) throws SQLException
	{	
		statement.execute("delete from " + Consts_DB.Table.SPECIFIED_SUBSCARS_UPLOAD + " where " + 
				Consts_DB.Header.Main.NAME + " = " + "'" + bbkUpload.getName() + "'");
		for (BbkUpload.SpecifiedSubscar subscar : bbkUpload.specifiedSubscars)	
			statement.execute("insert into " + Consts_DB.Table.SPECIFIED_SUBSCARS_UPLOAD + " " +
				"(" + Consts_DB.Header.SpecScar.COMMENTS + ", " + 
					  Consts_DB.Header.SpecScar.ID + ", " + 
					  Consts_DB.Header.SpecScar.NAME + ", " + 
					  Consts_DB.Header.SpecScar.NICK_NAME + ", " + 
					  Consts_DB.Header.SpecScar.SEQUENCE + ", " + 
					  Consts_DB.Header.SpecScar.STANDARD + ", " + 
					  Consts_DB.Header.SpecScar.TYPE + ")" + 
				" values " + 
				"(" + "'" + subscar.comments + "'" + ", " + 
					  "'" + subscar.ID + "'" + ", " + 
					  "'" + subscar.name + "'" + ", " + 
					  "'" + subscar.nickname + "'" + ", " + 
					  "'" + subscar.sequence + "'" + ", " + 
					  "'" + subscar.standard + "'" + ", " + 
					  "'" + subscar.type + "'" + ")");
	}
	
	public static void twinsIntoDb(BbkUpload bbkUpload, Statement statement) throws SQLException
	{	
		statement.execute("delete from " + Consts_DB.Table.TWINS_UPLOAD + " where " + 
				Consts_DB.Header.Main.NAME + " = " + "'" + bbkUpload.getName() + "'");
		for (BbkUpload.Twin twin : bbkUpload.twins)	
			statement.execute("insert into " + Consts_DB.Table.TWINS_UPLOAD + " " +
				"(" + Consts_DB.Header.Twin.TWIN + ")" + 
				" values " + 
				"(" + "'" + twin.twin + "'" + ")");
	}
	
}
