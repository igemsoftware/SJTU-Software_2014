package data_center;

import java.util.ArrayList;

public class BbkDetail extends BbkOutline
{
    // from main
	public String ID;
	public String shortName;
	public String releaseStatus;
	public String sampleStatus;
	public String results;
	public String nickname;
	public String rating;
	public String sequence;
	public String sample;
	public String reference;
	public String group;
	public String DNA_status;
	public String groupFavorite;
	
	public String delete_this_part;
	public String tot_confirmed;
	public String detail_not_confirmed;
	public String average_stars;
	public String tot_commets;
	
	public ArrayList<Category> categories;
	public ArrayList<DeepSubpart> deepSubparts;
	public ArrayList<Feature> features;
	public ArrayList<Parameter> parameters;
	public ArrayList<Scar> scars;
	public ArrayList<SpecifiedSubpart> specifiedSubparts;
	public ArrayList<Twin> twins;
	
	
	public BbkDetail()
    {
        // fix me
		super();
    }

	
	
	
	
	public static class Category
	{	
		public String category;
	}
	
	public static class DeepSubpart
	{	
		public String ID;
		public String name;
		public String shortDesc;
		public String type;
		public String nickname;
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
	
	public static class Scar
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
	
	public static class Twin
	{	
		public String twin;
	}
	

}
