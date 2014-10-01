package data_center;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

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
