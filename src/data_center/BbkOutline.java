package data_center;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BbkOutline
{
	// all from table main
	public String name;
    public String type;
    public String author;
    public String enterDate;
    public String shortDesc;   // short description
    public String url;
    
    public Rating rating = new Rating();
    public Blasting blasting = null;	// not new here cause not always blasting
    
 	public String ID;
 	public String shortName;
 	public String releaseStatus;
 	public String sampleStatus;
 	public String results;
 	public String nickname;
 	public String part_rating;
 	public String sequence;
 	public String samples;
 	public String references;
 	public String groups;
 	public String DNA_status;
 	public String groupFavorite;

    public BbkOutline()
    { }
    
    public BbkOutline(ResultSet resultSet) throws SQLException
    {	
    	this.fillData_main(resultSet);
    }
    
    public void fillData_main(ResultSet resultSet) throws SQLException
    {	
    	resultSet.next();
    	name = resultSet.getString(BbkDB.Header.Main.NAME);
	    type = resultSet.getString(BbkDB.Header.Main.TYPE);
	    author = resultSet.getString(BbkDB.Header.Main.AUTHOR);
	    enterDate = resultSet.getString(BbkDB.Header.Main.ENTER_DATE);
	    shortDesc = resultSet.getString(BbkDB.Header.Main.SHORT_DESC);
	    url = resultSet.getString(BbkDB.Header.Main.URL);
	    
	    rating.fillData_Rating(resultSet);
	    
	    ID = resultSet.getString(BbkDB.Header.Main.ID);
		shortName = resultSet.getString(BbkDB.Header.Main.SHORT_NAME);
		releaseStatus = resultSet.getString(BbkDB.Header.Main.RELEASE_STATUS);
		sampleStatus = resultSet.getString(BbkDB.Header.Main.SAMPLE_STATUS);
		results = resultSet.getString(BbkDB.Header.Main.RESULTS);
		nickname = resultSet.getString(BbkDB.Header.Main.NICKNAME);
		part_rating = resultSet.getString(BbkDB.Header.Main.PART_RATING);
		sequence = resultSet.getString(BbkDB.Header.Main.SEQUENCE);
		samples = resultSet.getString(BbkDB.Header.Main.SAMPLES);
		references = resultSet.getString(BbkDB.Header.Main.REFERENCES);
		groups = resultSet.getString(BbkDB.Header.Main.GROUPS);
		DNA_status = resultSet.getString(BbkDB.Header.Main.DNA_STATUS);
		groupFavorite = resultSet.getString(BbkDB.Header.Main.GROUP_FAVOURITE);
    }
    
    /** Score at default weight */
    public int getScore()
    {	
    	return this.rating.getScore();
    }
    
    public int getScore(double status_weight, double quality_weight, 
						double feedbacks_weight, double publication_weight)
    {	
    	return this.rating.getScore(status_weight, quality_weight, 
    								feedbacks_weight, publication_weight);
    }
    
    public void display()
    {
        System.out.println( "********\n" + 
				   			"Name: " + name + "\n" + 
                            "Type: " + type + "\n" + 
                            "Author: " + author + "\n" + 
                            "EnterDate: " + enterDate + "\n" + 
                            "ShortDescription: " + shortDesc + "\n" +
                            "Url: " + url + "\n");
        this.rating.display();
        System.out.println("********\n");
    }
    
    
    
    
    /** The rating by EasyBbk */
    public static class Rating
    {	
    	public String rating;
    	public String status;
    	public String quality;
    	public String feedbacks;
    	public String publication;
    	// rating details
    	public String delete_this_part;
     	public String tot_confirmed;
     	public String detail_not_confirmed;
     	public String average_stars;
     	public String tot_commets;
     	public String google_items;
     	public String first_url;
    	
    	public void fillData_Rating(ResultSet resultSet) throws SQLException
    	{	
    		rating = resultSet.getString(BbkDB.Header.Main.RATING);
    		status = resultSet.getString(BbkDB.Header.Main.STATUS);
    		quality = resultSet.getString(BbkDB.Header.Main.QUALITY);
    		feedbacks = resultSet.getString(BbkDB.Header.Main.FEEDBACKS);
    		publication = resultSet.getString(BbkDB.Header.Main.PUBLICATION);
    		// rating details
    		delete_this_part = resultSet.getString(BbkDB.Header.Main.DELETE_THIS_PART);
    		tot_confirmed = resultSet.getString(BbkDB.Header.Main.TOT_CONFIRMED);
    		detail_not_confirmed = resultSet.getString(BbkDB.Header.Main.DETAIL_NOT_CONFIRMED);
    		average_stars = resultSet.getString(BbkDB.Header.Main.AVERAGE_STARS);
    		tot_commets = resultSet.getString(BbkDB.Header.Main.TOT_COMMENTS);
    		google_items = resultSet.getString(BbkDB.Header.Main.GOOGLE_ITEMS);
    		first_url = resultSet.getString(BbkDB.Header.Main.FIRST_URL);
    	}
    	
    	public int getScore()
    	{
    		return getScore(BbkDB.Rating.STATUS_DEFAULT_WEIGHT, 
    						BbkDB.Rating.QUALITY_DEFAULT_WEIGHT,
    						BbkDB.Rating.FEEDBACKS_DEFAULT_WEIGHT,
    						BbkDB.Rating.PUBLICATION_DEFAULT_WEIGHT);
    	}
    	
    	public int getScore(double status_weight, double quality_weight, 
    						double feedbacks_weight, double publication_weight)
    	{	
    		double totalPoints = BbkDB.Rating.TOTAL_POINTS;
    		double points = Double.parseDouble(status) / BbkDB.Rating.STATUS_DEFAULT_WEIGHT * status_weight
    					  + Double.parseDouble(quality) / BbkDB.Rating.QUALITY_DEFAULT_WEIGHT * quality_weight
    					  + Double.parseDouble(feedbacks) / BbkDB.Rating.FEEDBACKS_DEFAULT_WEIGHT * feedbacks_weight
    					  + Double.parseDouble(publication) / BbkDB.Rating.PUBLICATION_DEFAULT_WEIGHT * publication_weight;
    		int score = (int)(points / totalPoints * 100);
    		return score;
    	}
    	
    	public void display()
    	{	
    		System.out.println("Rating: "+ rating + "\n" + 
    						   "Status: " + status + "\n" + 
    						   "Quality: " + quality + "\n" + 
    						   "Feedbacks: " + feedbacks + "\n" +
    						   "Publication: " + publication);
    	}
    }
    
    public static class Blasting
    {	
    	int score;
    	double eValue;
    }
}

/*
// 返回值是boolean? 有待讨论	-cwj
// 于是我又想了一下，直接在filter函数里面判断好了，这里的就不要了,
 	// 如果你觉得ok的话就把它们删掉好了~	-chyb
public boolean isTypeOf(int typeNo)
{
	switch (typeNo)
	{
		case BbkType.PROMOTER:
			return type.equals(BbkType.strOf_PROMOTER);
		case BbkType.RBS:
			return type.equals(BbkType.strOf_RBS);
		case BbkType.PROTEIN_DOMAIN:
			return type.equals(BbkType.strOf_PROTEIN_DOMAIN);
		case BbkType.PROTEIN_CODING_SEQUENCE:
			return type.equals(BbkType.strOf_PROTEIN_CODING_SEQUENCE);
		case BbkType.TRANSLATIONAL_UNIT:
			return type.equals(BbkType.strOf_TRANSLATIONAL_UNIT);
		case BbkType.TERMINATOR:
			return type.equals(BbkType.strOf_TERMINATOR);
		case BbkType.DNA:
			return type.equals(BbkType.strOf_DNA);
		case BbkType.PLASMID_BACKBONE:
			return type.equals(BbkType.strOf_PLASMID_BACKBONE);
		case BbkType.PLASMID:
			return type.equals(BbkType.strOf_PLASMID);
		case BbkType.PRIMER:
			return type.equals(BbkType.strOf_PRIMER);
		case BbkType.COMPOSITE:
			return type.equals(BbkType.strOf_COMPOSITE);
		case BbkType.PROTEIN_GENERATOR:
			return type.equals(BbkType.strOf_PROTEIN_GENERATOR);
		case BbkType.REPORTER:
			return type.equals(BbkType.strOf_REPORTER);
		case BbkType.INVERTER:
			return type.equals(BbkType.strOf_INVERTER);
		case BbkType.SIGNALLING:
			return type.equals(BbkType.strOf_SIGNALLING);
		case BbkType.MEASUREMENT:
			return type.equals(BbkType.strOf_MEASUREMENT);
		case BbkType.OTHER:
			// if code reaches here, it must be an other
			return true;
		default:
			return false; 
	}
}

public boolean isEnteredBetween(int[] enterYear)
{
	if (enterYear.length != 2)
	{	System.out.println("Invalid enter year filter... ");
		return false;
	}
	// else
	int theYear = Integer.parseInt(enterDate.substring(0, 4));
	return (enterYear[0] <= theYear) && (enterYear[1] >= theYear);
}
*/

