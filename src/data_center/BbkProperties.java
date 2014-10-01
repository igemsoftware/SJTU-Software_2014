package data_center;

import java.sql.ResultSet;
import java.sql.SQLException;

/** The propertied in the other 7 tables exclude main */
public abstract class BbkProperties
{
	public static abstract class Category
	{	
		public String category;
		
		public Category() {}
		
		public Category(ResultSet resultSet) throws SQLException
		{
			category = resultSet.getString(DBConsts.Header.Category.CATEGORY);
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
			ID = resultSet.getString(DBConsts.Header.DeepSub.ID);
			name = resultSet.getString(DBConsts.Header.DeepSub.NAME);
			shortDesc = resultSet.getString(DBConsts.Header.DeepSub.SHORT_DESC);
			type = resultSet.getString(DBConsts.Header.DeepSub.TYPE);
			nickname = resultSet.getString(DBConsts.Header.DeepSub.NICKNAME);
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
			ID = resultSet.getString(DBConsts.Header.Feature.ID);
			title = resultSet.getString(DBConsts.Header.Feature.TITLE);
			type = resultSet.getString(DBConsts.Header.Feature.TYPE);
			direction = resultSet.getString(DBConsts.Header.Feature.DIRECTION);
			startPos = resultSet.getString(DBConsts.Header.Feature.START_POS);
			endPos = resultSet.getString(DBConsts.Header.Feature.END_POS);
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
			name = resultSet.getString(DBConsts.Header.Parameter.NAME);
			value = resultSet.getString(DBConsts.Header.Parameter.VALUE);
			units = resultSet.getString(DBConsts.Header.Parameter.UNITS);
			url = resultSet.getString(DBConsts.Header.Parameter.URL);
			ID = resultSet.getString(DBConsts.Header.Parameter.ID);
			m_date = resultSet.getString(DBConsts.Header.Parameter.M_DATE);
			userID = resultSet.getString(DBConsts.Header.Parameter.USER_ID);
			userName = resultSet.getString(DBConsts.Header.Parameter.USER_NAME);
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
			ID = resultSet.getString(DBConsts.Header.SpecSub.ID);
			name = resultSet.getString(DBConsts.Header.SpecSub.NAME);
			shortDesc = resultSet.getString(DBConsts.Header.SpecSub.SHORT_DESC);
			type = resultSet.getString(DBConsts.Header.SpecSub.TYPE);
			nickname = resultSet.getString(DBConsts.Header.SpecSub.NICKNAME);
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
			ID = resultSet.getString(DBConsts.Header.SpecScar.ID);
			standard = resultSet.getString(DBConsts.Header.SpecScar.STANDARD);
			type = resultSet.getString(DBConsts.Header.SpecScar.TYPE);
			name = resultSet.getString(DBConsts.Header.SpecScar.NAME);
			nickname = resultSet.getString(DBConsts.Header.SpecScar.NICK_NAME);
			comments = resultSet.getString(DBConsts.Header.SpecScar.COMMENTS);
			sequence = resultSet.getString(DBConsts.Header.SpecScar.SEQUENCE);
		}
	}
	
	public static abstract class Twin
	{	
		public String twin;
		
		public Twin() {}
		
		public Twin(ResultSet resultSet) throws SQLException
		{	
			twin = resultSet.getString(DBConsts.Header.Twin.TWIN);
		}
	}
}
