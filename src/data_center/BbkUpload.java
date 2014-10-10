package data_center;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/** The data stored locally in the EasyBbk before upload. A filled BbkUpload can be
 * used to upload a biobrick directly into the database established by SJTU-software.  */
public class BbkUpload
{
	// those init with null can't change its value once set
	private String name = null;
    private String enterDate = null;
    private String ID = null;
 	private String shortName = null;
 	private String sequence;
 	
 	public String type;
    public String author;
    public String shortDesc;   // short description
    public String longDesc;		// long description
    public String source;
    public String notes;
 	public String nickname;
 	public String groupFavorite;
 	public String delete_this_part;
 	public ArrayList<Object> sequenceTokens = new ArrayList<Object>();
	
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

	/** rawName is some thing without "BBa_", like "B0012". Suffix "_EasyBbk" will
	 * be automatically appended to distinguish them from the biobricks fetched 
	 * from igem.org */
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

	public void setID()
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
	 * Please call uploadCenter.isSequanceValid() first. 
	 * useDefaultScar means use RFC[10] to fill all unspecified scars 
	 * between bio-bricks */
	public void setSequence(boolean useDefaultScar, boolean findTwins)
	{
		sequence = "";
		deepSubparts = new ArrayList<BbkUpload.DeepSubpart>();
		specifiedSubscars = new ArrayList<BbkUpload.SpecifiedSubscar>();
		specifiedSubparts = new ArrayList<BbkUpload.SpecifiedSubpart>();
		twins = null;
		
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
		if (findTwins)
			twins = DatabaseConnector.findTwinsBySequence(sequence);
	}

	
	
	/** Used when filling private from database into bbkUpload, others in DBDataFiller
	 * @throws SQLException */
	public void fillPrivateData(ResultSet resultSet) throws SQLException
	{
		name = resultSet.getString(DBConsts.Header.Main.NAME);
		enterDate = resultSet.getString(DBConsts.Header.Main.ENTER_DATE);
		ID = resultSet.getString(DBConsts.Header.Main.ID);
		shortName = resultSet.getString(DBConsts.Header.Main.SHORT_NAME);
		sequence = resultSet.getString(DBConsts.Header.Main.SEQUENCE);
	}
	
	
	
	
	public static class Category extends BbkProperties.Category
	{	
		public Category(String category)
		{	
			this.category = category;
		}
		
		/** Used to fill data from database when modify uploaded bbk */
		public Category(ResultSet resultSet) throws SQLException
		{	super(resultSet);	}
	}
	
	public static class Feature extends BbkProperties.Feature
	{	
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
		
		/** Used to fill data from database when modify uploaded bbk */
		public Feature(ResultSet resultSet) throws SQLException
		{	super(resultSet);	}
	}
	
	public static class Parameter extends BbkProperties.Parameter
	{	
		public Parameter(String name, String value, String units, String url, 
				String ID, String userName)
		{	
			this.name = name;
			this.value = value;
			this.units = units;
			this.url = url;
			this.ID = ID;
			this.m_date = TimeTagGenerator.generateYearMonthDateStr() + " " 
						+ TimeTagGenerator.generateHourMinStr();
			this.userID = "";
			this.userName = userName;
		}
		
		/** Used to fill data from database when modify uploaded bbk */
		public Parameter(ResultSet resultSet) throws SQLException
		{	super(resultSet);	}
	}

	public static class DeepSubpart extends BbkProperties.DeepSubpart
	{	
		public DeepSubpart(BbkDetail.DeepSubpart subpart)
		{	
			ID = subpart.ID;
			name = subpart.name;
			shortDesc = subpart.shortDesc;
			type = subpart.type;
			nickname = subpart.nickname;
		}
		
		/** Used to fill data from database when modify uploaded bbk */
		public DeepSubpart(ResultSet resultSet) throws SQLException
		{	super(resultSet);	}
	}
	
	public static class SpecifiedSubpart extends BbkProperties.SpecifiedSubpart
	{	
		/** Data collected from main */
		public SpecifiedSubpart(BbkDetail bbkDetail)
		{	
			ID = bbkDetail.ID;
			name = bbkDetail.name;
			shortDesc = bbkDetail.shortDesc;
			type = bbkDetail.type;
			nickname = bbkDetail.nickname;
		}
		
		public SpecifiedSubpart(ResultSet resultSet) throws SQLException
		{	super(resultSet);	}
	}
	
	public static class SpecifiedSubscar extends BbkProperties.SpecifiedSubscar
	{	
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
		
		/** Used to create a specified subscar instance. 
		 * Can also fill data from database when modify uploaded bbk. */
		public SpecifiedSubscar(ResultSet resultSet) throws SQLException
		{	super(resultSet);	}
	}
	
	public static class Twin extends BbkProperties.Twin
	{	
		/** Used when new a twin to fill in BbkDatabaseConnector.findTwinsBySequence. 
		 * Avoiding conflict from the filling when modify uploaded bbk */
		public Twin() {}
		
		/** Used to fill data from database when modify uploaded bbk */
		public Twin(ResultSet resultSet) throws SQLException
		{	super(resultSet);	}
	}
	
	public void display()
	{	
		System.out.println( "********\n" + 
	   			"Name: " + name + "\n" + 
				"ID: " + ID + "\n" +
	   			"EnterDate: " + enterDate + "\n" + 
	   			"ShortDescription: " + shortDesc + "\n" +
                "ShortName: " + shortName + "\n" + 
                "Type: " + type + "\n" + 
                "Author: " + author + "\n" + 
                "NickName: " + nickname + "\n" + 
                "GroupFavorite: " + groupFavorite + "\n" + 
                "Deleted: " + delete_this_part);
		System.out.println("********\n");
	}
	
	
	
	// BbkUpload 小黑屋，与outline相比没有的东西在这里：
	/*
	url = "";
	releaseStatus = "";
	sampleStatus = "";
	results = "";
	part_rating = "";
	references = "";
	groups = "";
	DNA_status = "";
	samples = "";
	rating = "-1";
	status = "-1";
	quality = "-1";
	feedbacks = "-1";
	publication = "-1";
	tot_confirmed = "-1";
	detail_not_confirmed = "-1";
	average_stars = "-1";
	tot_commets = "-1";
	google_items = "-1";
	first_url = "";
	*/
	
	
}
