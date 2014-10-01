package data_center;

import java.sql.ResultSet;
import java.sql.SQLException;

/** Can both fill data into database and into the bbks */
public class DBDataFiller
{
	public static void dbIntoMain(ResultSet resultSet, BbkOutline bbkOutline) throws SQLException
    {	
		bbkOutline.name = resultSet.getString(DBConsts.Header.Main.NAME);
		bbkOutline.type = resultSet.getString(DBConsts.Header.Main.TYPE);
		bbkOutline.author = resultSet.getString(DBConsts.Header.Main.AUTHOR);
		bbkOutline.enterDate = resultSet.getString(DBConsts.Header.Main.ENTER_DATE);
		bbkOutline.shortDesc = resultSet.getString(DBConsts.Header.Main.SHORT_DESC);
		bbkOutline.url = resultSet.getString(DBConsts.Header.Main.URL);
	    
		bbkOutline.rating = new BbkOutline.Rating(resultSet);
	    
		bbkOutline.ID = resultSet.getString(DBConsts.Header.Main.ID);
		bbkOutline.shortName = resultSet.getString(DBConsts.Header.Main.SHORT_NAME);
		bbkOutline.releaseStatus = resultSet.getString(DBConsts.Header.Main.RELEASE_STATUS);
		bbkOutline.sampleStatus = resultSet.getString(DBConsts.Header.Main.SAMPLE_STATUS);
		bbkOutline.results = resultSet.getString(DBConsts.Header.Main.RESULTS);
		bbkOutline.nickname = resultSet.getString(DBConsts.Header.Main.NICKNAME);
		bbkOutline.part_rating = resultSet.getString(DBConsts.Header.Main.PART_RATING);
		bbkOutline.sequence = resultSet.getString(DBConsts.Header.Main.SEQUENCE);
		bbkOutline.samples = resultSet.getString(DBConsts.Header.Main.SAMPLES);
		bbkOutline.references = resultSet.getString(DBConsts.Header.Main.REFERENCES);
		bbkOutline.groups = resultSet.getString(DBConsts.Header.Main.GROUPS);
		bbkOutline.DNA_status = resultSet.getString(DBConsts.Header.Main.DNA_STATUS);
		bbkOutline.groupFavorite = resultSet.getString(DBConsts.Header.Main.GROUP_FAVOURITE);
    }
	
	/** Fill data from database, don't need url, rating parts etc..., cause
	 * they are initialized when newed, and are constants */
	public static void dbIntoMain(ResultSet resultSet, BbkUpload bbkUpload) throws SQLException
    {	
		bbkUpload.fillPrivateData(resultSet);
		bbkUpload.type = resultSet.getString(DBConsts.Header.Main.TYPE);
		bbkUpload.author = resultSet.getString(DBConsts.Header.Main.AUTHOR);
		bbkUpload.shortDesc = resultSet.getString(DBConsts.Header.Main.SHORT_DESC);
		bbkUpload.nickname = resultSet.getString(DBConsts.Header.Main.NICKNAME);
		bbkUpload.groupFavorite = resultSet.getString(DBConsts.Header.Main.GROUP_FAVOURITE);
		bbkUpload.delete_this_part = resultSet.getString(DBConsts.Header.Main.DELETE_THIS_PART);
    }
	
	
	
	// resultSet WILL have an result if enters these functions, checked outside
	public static void dbIntoCategories(ResultSet resultSet, BbkDetail bbkDetail) throws SQLException
	{	
		do
			bbkDetail.categories.add(new BbkDetail.Category(resultSet));
		while (resultSet.next());
	}
	
	public static void dbIntoCategories(ResultSet resultSet, BbkUpload bbkUpload) throws SQLException
	{	
		do
			bbkUpload.categories.add(new BbkUpload.Category(resultSet));
		while (resultSet.next());
	}
	
	public static void dbIntoDeepSubparts(ResultSet resultSet, BbkDetail bbkDetail) throws SQLException
	{	
		do
			bbkDetail.deepSubparts.add(new BbkDetail.DeepSubpart(resultSet));
		while (resultSet.next());
	}
	
	public static void dbIntoDeepSubparts(ResultSet resultSet, BbkUpload bbkUpload) throws SQLException
	{	
		do
			bbkUpload.deepSubparts.add(new BbkUpload.DeepSubpart(resultSet));
		while (resultSet.next());
	}
	
	public static void dbIntoFeatures(ResultSet resultSet, BbkDetail bbkDetail) throws SQLException
	{	
		do
			bbkDetail.features.add(new BbkDetail.Feature(resultSet));
		while (resultSet.next());
	}
	
	public static void dbIntoFeatures(ResultSet resultSet, BbkUpload bbkUpload) throws SQLException
	{	
		do
			bbkUpload.features.add(new BbkUpload.Feature(resultSet));
		while (resultSet.next());
	}
	
	public static void dbIntoParameters(ResultSet resultSet, BbkDetail bbkDetail) throws SQLException
	{	
		do
			bbkDetail.parameters.add(new BbkDetail.Parameter(resultSet));
		while (resultSet.next());
	}
	
	public static void dbIntoParameters(ResultSet resultSet, BbkUpload bbkUpload) throws SQLException
	{	
		do
			bbkUpload.parameters.add(new BbkUpload.Parameter(resultSet));
		while (resultSet.next());
	}
	
	public static void dbIntoSpecifiedSubparts(ResultSet resultSet, BbkDetail bbkDetail) throws SQLException
	{	
		do
			bbkDetail.specifiedSubparts.add(new BbkDetail.SpecifiedSubpart(resultSet));
		while (resultSet.next());
	}
	
	public static void dbIntoSpecifiedSubparts(ResultSet resultSet, BbkUpload bbkUpload) throws SQLException
	{	
		do
			bbkUpload.specifiedSubparts.add(new BbkUpload.SpecifiedSubpart(resultSet));
		while (resultSet.next());
	}
	
	public static void dbIntoSpecifiedSubscars(ResultSet resultSet, BbkDetail bbkDetail) throws SQLException
	{	
		do
			bbkDetail.specifiedSubscars.add(new BbkDetail.SpecifiedSubscar(resultSet));
		while (resultSet.next());
	}
	
	public static void dbIntoSpecifiedSubscars(ResultSet resultSet, BbkUpload bbkUpload) throws SQLException
	{	
		do
			bbkUpload.specifiedSubscars.add(new BbkUpload.SpecifiedSubscar(resultSet));
		while (resultSet.next());
	}
	
	public static void dbIntoTwins(ResultSet resultSet, BbkDetail bbkDetail) throws SQLException
	{	
		do
			bbkDetail.twins.add(new BbkDetail.Twin(resultSet));
		while (resultSet.next());
	}
	
	public static void dbIntoTwins(ResultSet resultSet, BbkUpload bbkUpload) throws SQLException
	{	
		do
			bbkUpload.twins.add(new BbkUpload.Twin(resultSet));
		while (resultSet.next());
	}
	
	
	
}
