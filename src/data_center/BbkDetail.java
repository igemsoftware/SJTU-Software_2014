package data_center;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/** The class used to store the data in the detail page. All of the data are fetched 
 * from EasyBbk database and stored as Strings.  */
public class BbkDetail extends BbkOutline
{
	public ArrayList<Category> categories = new ArrayList<Category>();
	public ArrayList<DeepSubpart> deepSubparts = new ArrayList<DeepSubpart>();
	public ArrayList<Feature> features = new ArrayList<Feature>();
	public ArrayList<Parameter> parameters = new ArrayList<Parameter>();
	public ArrayList<SpecifiedSubpart> specifiedSubparts = new ArrayList<SpecifiedSubpart>();
	public ArrayList<SpecifiedSubscar> specifiedSubscars = new ArrayList<SpecifiedSubscar>();
	public ArrayList<Twin> twins = new ArrayList<Twin>();
	
	
	public BbkDetail()
    { }
	
	/** Print main attributes in cmd for testing.  */
	@Override
    public void display()
    {
        System.out.println( "********\n" + 
				   "Name: " + name + "\n" + 
                   "Type: " + type + "\n" + 
                   "Author: " + author + "\n" + 
                   "EnterDate: " + enterDate + "\n" + 
                   "ShortDescription: " + shortDesc + "\n" +
                   "Url: " + url + "\n" + 
                   "\tCategory item num: " + categories.size() + "\n" + 
                   "\tDeepSub item num: " + deepSubparts.size() + "\n" + 
                   "\tFeature item num: " + features.size() + "\n" + 
                   "\tParameter item num: " + parameters.size() + "\n" + 
                   "\tSpecSub item num: " + specifiedSubparts.size() + "\n" + 
                   "\tSpecScar item num: " + specifiedSubscars.size() + "\n" + 
                   "\tTwin item num: " + twins.size());
        System.out.println("********\n");
    }
	
	
	
	public static class Category extends BbkProperties.Category
	{	
		public Category(ResultSet resultSet) throws SQLException
		{	super(resultSet);	}
	}
	
	public static class DeepSubpart extends BbkProperties.DeepSubpart
	{	
		public DeepSubpart(ResultSet resultSet) throws SQLException
		{	super(resultSet);	}
	}
	
	public static class Feature extends BbkProperties.Feature
	{	
		public Feature(ResultSet resultSet) throws SQLException
		{	super(resultSet);	}
	}
	
	public static class Parameter extends BbkProperties.Parameter
	{	
		public Parameter(ResultSet resultSet) throws SQLException
		{	super(resultSet);	}
	}
	
	public static class SpecifiedSubpart extends BbkProperties.SpecifiedSubpart
	{	
		public SpecifiedSubpart(ResultSet resultSet) throws SQLException
		{	super(resultSet);	}
	}
	
	public static class SpecifiedSubscar extends BbkProperties.SpecifiedSubscar
	{	
		public SpecifiedSubscar(ResultSet resultSet) throws SQLException
		{	super(resultSet);	}
	}
	
	public static class Twin extends BbkProperties.Twin
	{	
		public Twin(ResultSet resultSet) throws SQLException
		{	super(resultSet);	}
	}
}
