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
	
	// resultSet WILL have an result if enters these functions, checked outside
	public void fillData_categories(ResultSet resultSet) throws SQLException
	{	
		do
			categories.add(new Category(resultSet));
		while (resultSet.next());
	}
	
	public void fillData_deepSubparts(ResultSet resultSet) throws SQLException
	{	
		do
			deepSubparts.add(new DeepSubpart(resultSet));
		while (resultSet.next());
	}
	
	public void fillData_features(ResultSet resultSet) throws SQLException
	{	
		do
			features.add(new Feature(resultSet));
		while (resultSet.next());
	}
	
	public void fillData_parameters(ResultSet resultSet) throws SQLException
	{	
		do
			parameters.add(new Parameter(resultSet));
		while (resultSet.next());
	}
	
	public void fillData_specifiedSubparts(ResultSet resultSet) throws SQLException
	{	
		do
			specifiedSubparts.add(new SpecifiedSubpart(resultSet));
		while (resultSet.next());
	}
	
	public void fillData_specifiedSubscars(ResultSet resultSet) throws SQLException
	{	
		do
			specifiedSubscars.add(new SpecifiedSubscar(resultSet));
		while (resultSet.next());
	}
	
	public void fillData_twins(ResultSet resultSet) throws SQLException
	{	
		do
			twins.add(new Twin(resultSet));
		while (resultSet.next());
	}
	
	
	
	
	
	
	public static class Category
	{	
		public String category;
		
		public Category(ResultSet resultSet) throws SQLException
		{
			category = resultSet.getString(BbkDB.Header.Category.CATEGORY);
		}
	}
	
	public static class DeepSubpart
	{	
		public String ID;
		public String name;
		public String shortDesc;
		public String type;
		public String nickname;
		
		public DeepSubpart(ResultSet resultSet) throws SQLException
		{	
			ID = resultSet.getString(BbkDB.Header.DeepSub.ID);
			name = resultSet.getString(BbkDB.Header.DeepSub.NAME);
			shortDesc = resultSet.getString(BbkDB.Header.DeepSub.SHORT_DESC);
			type = resultSet.getString(BbkDB.Header.DeepSub.TYPE);
			nickname = resultSet.getString(BbkDB.Header.DeepSub.NICKNAME);
		}
	}
	
	public static class Feature
	{	
		public String ID;	// not part_ID in the main
		public String title;
		public String type;
		public String direction;
		public String startPos;
		public String endPos;
		
		public Feature(ResultSet resultSet) throws SQLException
		{	
			ID = resultSet.getString(BbkDB.Header.Feature.ID);
			title = resultSet.getString(BbkDB.Header.Feature.TITLE);
			type = resultSet.getString(BbkDB.Header.Feature.TYPE);
			direction = resultSet.getString(BbkDB.Header.Feature.DIRECTION);
			startPos = resultSet.getString(BbkDB.Header.Feature.START_POS);
			endPos = resultSet.getString(BbkDB.Header.Feature.END_POS);
		}
	}
	
	public static class Parameter
	{	
		public String name;
		public String value;
		public String units;
		public String url;
		public String ID;
		public String m_date;
		public String userID;
		public String userName;
		
		public Parameter(ResultSet resultSet) throws SQLException
		{	
			name = resultSet.getString(BbkDB.Header.Parameter.NAME);
			value = resultSet.getString(BbkDB.Header.Parameter.VALUE);
			units = resultSet.getString(BbkDB.Header.Parameter.UNITS);
			url = resultSet.getString(BbkDB.Header.Parameter.URL);
			ID = resultSet.getString(BbkDB.Header.Parameter.ID);
			m_date = resultSet.getString(BbkDB.Header.Parameter.M_DATE);
			userID = resultSet.getString(BbkDB.Header.Parameter.USER_ID);
			userName = resultSet.getString(BbkDB.Header.Parameter.USER_NAME);
		}
	}
	
	public static class SpecifiedSubpart
	{	
		public String ID;
		public String name;
		public String shortDesc;
		public String type;
		public String nickname;
		
		public SpecifiedSubpart(ResultSet resultSet) throws SQLException
		{	
			ID = resultSet.getString(BbkDB.Header.SpecSub.ID);
			name = resultSet.getString(BbkDB.Header.SpecSub.NAME);
			shortDesc = resultSet.getString(BbkDB.Header.SpecSub.SHORT_DESC);
			type = resultSet.getString(BbkDB.Header.SpecSub.TYPE);
			nickname = resultSet.getString(BbkDB.Header.SpecSub.NICKNAME);
		}
	}
	
	public static class SpecifiedSubscar
	{	
		public String ID;
		public String standard;
		public String type;
		public String name;
		public String nickname;
		public String comments;
		public String sequence;
		
		public SpecifiedSubscar(ResultSet resultSet) throws SQLException
		{	
			ID = resultSet.getString(BbkDB.Header.SpecScar.ID);
			standard = resultSet.getString(BbkDB.Header.SpecScar.STANDARD);
			type = resultSet.getString(BbkDB.Header.SpecScar.TYPE);
			name = resultSet.getString(BbkDB.Header.SpecScar.NAME);
			nickname = resultSet.getString(BbkDB.Header.SpecScar.NICK_NAME);
			comments = resultSet.getString(BbkDB.Header.SpecScar.COMMENTS);
			sequence = resultSet.getString(BbkDB.Header.SpecScar.SEQUENCE);
		}
	}
	
	public static class Twin
	{	
		public String twin;
		
		public Twin(ResultSet resultSet) throws SQLException
		{	
			twin = resultSet.getString(BbkDB.Header.Twin.TWIN);
		}
	}
	

}
