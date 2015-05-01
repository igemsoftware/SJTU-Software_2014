package data_center;

import java.sql.ResultSet;
import java.sql.SQLException;

/** The properties in the other 7 tables exclude main, used to be extended in 
 * BbkDetail and BbkUpload to store the details.  */
abstract class BbkProperties
{
	public static abstract class Category
	{	
		public String category;
		
		public Category() {}
		
		public Category(ResultSet resultSet) throws SQLException
		{
			category = resultSet.getString(Consts_DB.Header.Category.CATEGORY);
		}
	}
	
	public static abstract class DeepSubpart
	{	
		public String ID;
		public String name;
		public String shortDesc;
		public String type;
		public String nickname;
		
		public DeepSubpart() {}
		
		public DeepSubpart(ResultSet resultSet) throws SQLException
		{	
			ID = resultSet.getString(Consts_DB.Header.DeepSub.ID);
			name = resultSet.getString(Consts_DB.Header.DeepSub.NAME);
			shortDesc = resultSet.getString(Consts_DB.Header.DeepSub.SHORT_DESC);
			type = resultSet.getString(Consts_DB.Header.DeepSub.TYPE);
			nickname = resultSet.getString(Consts_DB.Header.DeepSub.NICKNAME);
		}
	}
	
	public static abstract class Feature
	{	
		public String ID;	// not part_ID in the main
		public String title;
		public String type;
		public String direction;
		public String startPos;
		public String endPos;
		
		public Feature() {}
		
		public Feature(ResultSet resultSet) throws SQLException
		{	
			ID = resultSet.getString(Consts_DB.Header.Feature.ID);
			title = resultSet.getString(Consts_DB.Header.Feature.TITLE);
			type = resultSet.getString(Consts_DB.Header.Feature.TYPE);
			direction = resultSet.getString(Consts_DB.Header.Feature.DIRECTION);
			startPos = resultSet.getString(Consts_DB.Header.Feature.START_POS);
			endPos = resultSet.getString(Consts_DB.Header.Feature.END_POS);
		}
	}
	
	public static abstract class Parameter
	{	
		public String name;
		public String value;
		public String units;
		public String url;
		public String ID;
		public String m_date;
		public String userID;
		public String userName;
		
		public Parameter() {}
		
		public Parameter(ResultSet resultSet) throws SQLException
		{	
			name = resultSet.getString(Consts_DB.Header.Parameter.NAME);
			value = resultSet.getString(Consts_DB.Header.Parameter.VALUE);
			units = resultSet.getString(Consts_DB.Header.Parameter.UNITS);
			url = resultSet.getString(Consts_DB.Header.Parameter.URL);
			ID = resultSet.getString(Consts_DB.Header.Parameter.ID);
			m_date = resultSet.getString(Consts_DB.Header.Parameter.M_DATE);
			userID = resultSet.getString(Consts_DB.Header.Parameter.USER_ID);
			userName = resultSet.getString(Consts_DB.Header.Parameter.USER_NAME);
		}
	}
	
	public static abstract class SpecifiedSubpart
	{	
		public String ID;
		public String name;
		public String shortDesc;
		public String type;
		public String nickname;
		
		public SpecifiedSubpart() {}
		
		public SpecifiedSubpart(ResultSet resultSet) throws SQLException
		{	
			ID = resultSet.getString(Consts_DB.Header.SpecSub.ID);
			name = resultSet.getString(Consts_DB.Header.SpecSub.NAME);
			shortDesc = resultSet.getString(Consts_DB.Header.SpecSub.SHORT_DESC);
			type = resultSet.getString(Consts_DB.Header.SpecSub.TYPE);
			nickname = resultSet.getString(Consts_DB.Header.SpecSub.NICKNAME);
		}
	}
	
	public static abstract class SpecifiedSubscar
	{	
		public String ID;
		public String standard;
		public String type;
		public String name;
		public String nickname;
		public String comments;
		public String sequence;
		
		public SpecifiedSubscar() {}
		
		public SpecifiedSubscar(ResultSet resultSet) throws SQLException
		{	
			ID = resultSet.getString(Consts_DB.Header.SpecScar.ID);
			standard = resultSet.getString(Consts_DB.Header.SpecScar.STANDARD);
			type = resultSet.getString(Consts_DB.Header.SpecScar.TYPE);
			name = resultSet.getString(Consts_DB.Header.SpecScar.NAME);
			nickname = resultSet.getString(Consts_DB.Header.SpecScar.NICK_NAME);
			comments = resultSet.getString(Consts_DB.Header.SpecScar.COMMENTS);
			sequence = resultSet.getString(Consts_DB.Header.SpecScar.SEQUENCE);
		}
	}
	
	public static abstract class Twin
	{	
		public String twin;
		
		public Twin() {}
		
		public Twin(ResultSet resultSet) throws SQLException
		{	
			twin = resultSet.getString(Consts_DB.Header.Twin.TWIN);
		}
	}
}
