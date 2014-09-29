package data_center;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class BbkUpload
{
	// those init with null can't be set twice
	private String name = null;
    private String enterDate = null;
    private String ID = null;
 	private String shortName = null;
 	private String sequence;
 	
 	public String type;
    public String author;
    public String shortDesc;   // short description
 	public String nickname;
 	public String groupFavorite;
 	public String delete_this_part;
	
	public ArrayList<Category> categories = new ArrayList<Category>();
	public ArrayList<Feature> features = new ArrayList<Feature>();
	public ArrayList<Parameter> parameters = new ArrayList<Parameter>();
	ArrayList<DeepSubpart> deepSubparts = new ArrayList<DeepSubpart>();
	ArrayList<SpecifiedSubpart> specifiedSubparts = new ArrayList<SpecifiedSubpart>();
	ArrayList<SpecifiedSubscar> specifiedSubscars = new ArrayList<SpecifiedSubscar>();
	ArrayList<Twin> twins = new ArrayList<Twin>();
	
	public BbkUpload() {}

	public String getName()
	{	return name;	}

	/** rawName is sth without "BBa_", sth like "B0012" */
	public void setName(String rawName)
	{	
		if (name == null)
		{	name = "BBa_" + rawName + "_EasyBbk";
			shortName = rawName + "_EasyBbk";
		}
	}

	public String getEnterDate()
	{	return enterDate;	}

	public void setEnterDate()
	{	
		if (enterDate == null)
			enterDate = TimeTagGenerator.generateHourMinStr();
	}

	public String getID() 
	{	return ID;	}

	public void setID(String ID)
	{
		if (ID == null)
			ID = TimeTagGenerator.generateTimeTag();
	}

	/** setShortName in setName() */
	public String getShortName()
	{	return shortName;	}

	public String getSequence()
	{	return sequence;	}

	/** Also set the subscar, subpart, deepsubpart and twins at the same time 
	 * Please call uploadCenter.checkUploadSequance() first. 
	 * useDefaultScar means use RFC[10] to fill all unspecified scars 
	 * between bio-bricks */
	public void setSequence(ArrayList<Object> sequenceTokens, boolean useDefaultScar)
	{
		sequence = "";
		Object preToken = new Object();
		for (Object token : sequenceTokens)
		{	if (token.getClass().equals(BbkDetail.class))	// subpart
			{	// check if need to add default scar
				if (useDefaultScar && preToken.getClass().equals(BbkDetail.class))
				{	BbkUpload.SpecifiedSubscar subscar = new BbkUpload.SpecifiedSubscar();
					sequence += subscar.sequence;
					specifiedSubscars.add(subscar);
				}
				// then...	
				BbkDetail bbkDetail = (BbkDetail) token;
				sequence += bbkDetail.sequence;
				specifiedSubparts.add(new SpecifiedSubpart(bbkDetail));
				for (BbkDetail.DeepSubpart subpart : bbkDetail.deepSubparts)
					deepSubparts.add(new DeepSubpart(subpart));
			}
			else if (token.getClass().equals(BbkUpload.SpecifiedSubscar.class))
			{	SpecifiedSubscar subscar = (SpecifiedSubscar) token;
				sequence += subscar.sequence;
				specifiedSubscars.add(subscar);
			}
			else	// user defined sequence
				sequence += token;
			
			preToken = token;
		}
		// check for twins
		twins = BbkDatabaseConnector.findTwinsBySequence(sequence);
	}

	
	
	
	
	public static class Category
	{	
		public String category;
		
		public Category(String category)
		{	
			this.category = category;
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
		
		public Feature(String ID, String title, String type, String direction, 
				String startPos, String endPos)
		{	
			this.ID = ID;
			this.title = title;
			this.type = type;
			this.direction = direction;
			this.startPos = startPos;
			this.endPos = endPos;
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
		
		public Parameter(String name, String value, String units, String url, 
				String ID)
		{	
			this.name = name;
			this.value = value;
			this.units = units;
			this.url = url;
			this.ID = ID;
			this.m_date = TimeTagGenerator.generateYearMonthDateStr() + " " 
						+ TimeTagGenerator.generateHourMinStr();
			this.userID = "";
			this.userName = "";
		}
	}

	public static class DeepSubpart
	{	
		public String ID;
		public String name;
		public String shortDesc;
		public String type;
		public String nickname;
		
		public DeepSubpart(BbkDetail.DeepSubpart subpart)
		{	
			ID = subpart.ID;
			name = subpart.name;
			shortDesc = subpart.shortDesc;
			type = subpart.type;
			nickname = subpart.nickname;
		}
	}
	
	public static class SpecifiedSubpart
	{	
		public String ID;
		public String name;
		public String shortDesc;
		public String type;
		public String nickname;
		
		/** Data collected from main */
		public SpecifiedSubpart(BbkDetail bbkDetail)
		{	
			ID = bbkDetail.ID;
			name = bbkDetail.name;
			shortDesc = bbkDetail.shortDesc;
			type = bbkDetail.type;
			nickname = bbkDetail.nickname;
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
		
		/** Default subscar */
		public SpecifiedSubscar()
		{	
			ID = "1";
			standard = "10";
			type = "Scar";
			name = "RFC[10]";
			nickname = "[10]";
			comments = "";
			sequence = "tactagag";
		}
		
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
		
		/** Get twin by sequence from main */
		public Twin(ResultSet resultSet) throws SQLException
		{	
			twin = resultSet.getString(BbkDB.Header.Main.NAME);
		}
	}
	
	
	
	// BbkUpload 小黑屋，与outline相比没有但是还是要填进main表的项目在这里：
	String url = "";
	String releaseStatus = "";
	String sampleStatus = "";
	String results = "";
	String part_rating = "";
	String references = "";
	String groups = "";
	String DNA_status = "";
	String samples = "";
	// About rating
	String rating = "-1";
	String status = "-1";
	String quality = "-1";
	String feedbacks = "-1";
	String publication = "-1";
	// rating details
	String tot_confirmed = "-1";
	String detail_not_confirmed = "-1";
	String average_stars = "-1";
	String tot_commets = "-1";
	String google_items = "-1";
	String first_url = "";
	
	
}
