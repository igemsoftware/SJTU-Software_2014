package data_center;

public class BbkDB
{
	public final static String TABLE_MAIN = "main_new";
	public final static String TABLE_CATEGORIES = "categories";
	public final static String TABLE_DEEP_SUBPARTS = "deep_subparts";
	public final static String TABLE_FEATURES = "features";
	public final static String TABLE_PARAMETERS = "parameters";
	public final static String TABLE_SPECIFIED_SUBPARTS = "specified_subparts";
	public final static String TABLE_SPECIFIED_SUBSCARS = "specified_subscars";
	public final static String TABLE_TWINS = "twins";
	
	
	public static class Header
	{
		public static class Main
		{
			public final static String NAME = "part_name";
		    public final static String TYPE = "part_type";
		    public final static String AUTHOR = "part_author";
		    public final static String ENTER_DATE = "part_entered";
		    public final static String SHORT_DESC = "part_short_desc";
		    public final static String URL = "part_url";
		    
		    public final static String ID = "part_id";
		    public final static String SHORT_NAME = "part_short_name";
		    public final static String RELEASE_STATUS = "release_status";
		    public final static String SAMPLE_STATUS = "sample_status";
		    public final static String RESULTS = "part_results";
		    public final static String NICKNAME = "part_nickname";
		    public final static String RATING = "part_rating";
		    public final static String SEQUENCE = "sequence";
		    public final static String SAMPLES = "samples";
		    public final static String REFERENCES = "references";
		    public final static String GROUPS = "groups";
		    public final static String DNA_STATUS = "DNA_status";
		    public final static String GROUP_FAVOURITE = "Group_favourite";
		    
		    public final static String DELETE_THIS_PART = "delete_this_part";
		    public final static String TOT_CONFIRMED = "tot_confirmed";
		    public final static String DETAIL_NOT_CONFIRMED = "detail_not_confirmed";
		    public final static String AVERAGE_STARS = "average_stars";
		    public final static String TOT_COMMENTS = "tot_comments";
		}
		
	    public static class Category
		{	
			public final static String CATEGORY = "category";
		}
		
	    /**
	     * DeepSubpart.ID != ID
	     */
		public static class DeepSub	// deep_subpart
		{	
			public final static String ID = "subpart_id";
			public final static String NAME = "subpart_name";
			public final static String SHORT_DESC = "subpart_short_desc";
			public final static String TYPE = "subpart_type";
			public final static String NICKNAME = "subpart_nickname";
		}
		
		public static class Feature
		{	
			public final static String ID = "id";
			public final static String TITLE = "title";
			public final static String TYPE = "type";
			public final static String DIRECTION = "direction";
			public final static String START_POS = "startpos";
			public final static String END_POS = "endpos";
		}
		
		public static class Parameter
		{	
			public final static String NAME = "name";
			public final static String VALUE = "value";
			public final static String UNITS = "units";
			public final static String URL = "url";
			public final static String ID = "id";
			public final static String M_DATE = "m_date";
			public final static String USER_ID = "user_id";
			public final static String USER_NAME = "user_name";
		}
		
		public static class SpecScar	// specified_scar
		{	
			public final static String ID = "scar_id";
			public final static String STANDARD = "scar_standard";
			public final static String TYPE = "scar_type";
			public final static String NAME = "scar_name";
			public final static String NICK_NAME = "scar_nickname";
			public final static String COMMENTS = "scar_comments";
			public final static String SEQUENCE = "scar_sequence";
		}
		
		public static class SpecSub	// specified_subpart
		{	
			public final static String ID = "subpart_id";
			public final static String NAME = "subpart_name";
			public final static String SHORT_DESC = "subpart_short_desc";
			public final static String TYPE = "subpart_type";
			public final static String NICKNAME = "subpart_nickname";
		}
		
		public static class Twin
		{	
			public final static String TWIN = "twin";
		}
	    
	    
	    
	    
	}
}
