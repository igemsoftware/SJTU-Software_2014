package data_center;

import java.util.ArrayList;

// attributes need to be further modified
public class BbkUpload extends BbkOutline
{
	public ArrayList<Category> categories = new ArrayList<Category>();
	public ArrayList<Feature> features = new ArrayList<Feature>();
	public ArrayList<Parameter> parameters = new ArrayList<Parameter>();
	public ArrayList<SpecifiedScar> specifiedScars = new ArrayList<SpecifiedScar>();
	public ArrayList<SpecifiedSubpart> specifiedSubparts = new ArrayList<SpecifiedSubpart>();
	
	
	public BbkUpload()
    {
        super();
        rating = null;
        blasting = null;	// rating and blasting is from BbkOutline, not used here
    }

	
	
	
	
	public static class Category
	{	
		public String category;
	}
	
	public static class Feature
	{	
		public String ID;	// not part_ID in the main
		public String title;
		public String type;
		public String direction;
		public String startPos;
		public String endPos;
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
	}
	
	public static class SpecifiedScar
	{	
		public String ID;
		public String standard;
		public String type;
		public String name;
		public String nickname;
		public String comments;
		public String sequence;
	}
	
	public static class SpecifiedSubpart
	{	
		public String ID;
		public String name;
		public String shortDesc;
		public String type;
		public String nickname;
	}
	
}
