package data_center;

import java.sql.ResultSet;
import java.sql.SQLException;

/** The class used to store the data to be shown in search result. All of the 
 * data are fetched from EasyBbk database and stored as Strings. Also contains
 * the getScore() method to get the score evaluated by SJTU-software team.  */
public class BbkOutline
{
	// all from table main
	public String name;
    public String type;
    public String author;
    public String enterDate;
    public String shortDesc;   // short description
    public String url;
    
    public Rating rating = null;
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
    	DBDataFiller.dbIntoMain(resultSet, this);
    }
    
    /** Score at default weight.  */
    public double getScore()
    {	
    	return getScore(DBConsts.Rating.STATUS_DEFAULT_WEIGHT, 
				DBConsts.Rating.QUALITY_DEFAULT_WEIGHT,
				DBConsts.Rating.FEEDBACKS_DEFAULT_WEIGHT,
				DBConsts.Rating.PUBLICATION_DEFAULT_WEIGHT);
    }
    
    /** Score at custom weight.  */
    public double getScore(double status_weight, double quality_weight, 
						double feedbacks_weight, double publication_weight)
    {	
    	double totalPoints = DBConsts.Rating.TOTAL_POINTS;
		double points = 0;
		try
		{	points = Double.parseDouble(rating.status) / DBConsts.Rating.STATUS_DEFAULT_WEIGHT * status_weight
				   + Double.parseDouble(rating.quality) / DBConsts.Rating.QUALITY_DEFAULT_WEIGHT * quality_weight
				   + Double.parseDouble(rating.feedbacks) / DBConsts.Rating.FEEDBACKS_DEFAULT_WEIGHT * feedbacks_weight
				   + Double.parseDouble(rating.publication) / DBConsts.Rating.PUBLICATION_DEFAULT_WEIGHT * publication_weight;
		} catch (Exception e) {points = 0;}
		double score = points / totalPoints * 100;
		return score;
    }
    
    /** Print main attributes in cmd for testing.  */
    public void display()
    {
        System.out.println( "********\n" + 
				   "Name: " + name + "\n" + 
                   "Type: " + type + "\n" + 
                   "Author: " + author + "\n" + 
                   "EnterDate: " + enterDate + "\n" + 
                   "ShortDescription: " + shortDesc + "\n" +
                   "Url: " + url);
        System.out.println("********\n");
    }
    
    public void displayFilteringConditions()
    {	
    	System.out.println( "********\n" + 
	   			"Name: " + name + "\n" + 
                "Type: " + type + "\n" + 
                "EnterDate: " + enterDate + "\n" + 
                "DeletedOrNot: " + rating.delete_this_part + "\n" + 
                "DNAStatus: " + DNA_status + "\n" + 
                "ReleaseStatus: " + releaseStatus + "\n" + 
                "AverageStars: " + rating.average_stars);
    	System.out.println("********\n");
    }
    
    public void displaySortingConditions()
    {	
    	System.out.println( "********\n" + 
	   			"Name: " + name + "\n" + 
                "EnterDate: " + enterDate + "\n" + 
                "GoogleItems: " + rating.google_items + "\n" + 
                "AverageStars: " + rating.average_stars + "\n" + 
                "ConfirmedTimes: " + rating.tot_confirmed + "\n" + 
                "BlastingEValue: " + (blasting == null ? blasting : blasting.eValue) + "\n" + 
                "Total Score: " + getScore());
    	System.out.println("********\n");
    }
    
    public void displayRating()
    {	
    	System.out.println( "********\n" + 
	   			"Name: " + name + "\n" + 
	   			"Total Score: " + getScore());
    			rating.display();
    	System.out.println("********\n");
    }
    
    
    
    
    /** The rating by EasyBbk, evaluation criteria from both official web page 
     * and criteria by SJTU-software.  */
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
     	public String documentation;
     	public String used_times;
    	
    	public Rating(ResultSet resultSet) throws SQLException
    	{	
    		rating = resultSet.getString(DBConsts.Header.Main.RATING);
    		status = resultSet.getString(DBConsts.Header.Main.STATUS);
    		quality = resultSet.getString(DBConsts.Header.Main.QUALITY);
    		feedbacks = resultSet.getString(DBConsts.Header.Main.FEEDBACKS);
    		publication = resultSet.getString(DBConsts.Header.Main.PUBLICATION);
    		// rating details
    		delete_this_part = resultSet.getString(DBConsts.Header.Main.DELETE_THIS_PART);
    		tot_confirmed = resultSet.getString(DBConsts.Header.Main.TOT_CONFIRMED);
    		detail_not_confirmed = resultSet.getString(DBConsts.Header.Main.DETAIL_NOT_CONFIRMED);
    		average_stars = resultSet.getString(DBConsts.Header.Main.AVERAGE_STARS);
    		tot_commets = resultSet.getString(DBConsts.Header.Main.TOT_COMMENTS);
    		google_items = resultSet.getString(DBConsts.Header.Main.GOOGLE_ITEMS);
    		first_url = resultSet.getString(DBConsts.Header.Main.FIRST_URL);
    		documentation = resultSet.getString(DBConsts.Header.Main.DOCUMENTATION);
    		used_times = resultSet.getString(DBConsts.Header.Main.USED_TIMES);
    	}
    	
    	/** Print main attributes in cmd for testing.  */
    	public void display()
    	{	
    		System.out.println("Rating: "+ rating + "\n" + 
    						   "Status: " + status + "\n" + 
    						   "Quality: " + quality + "\n" + 
    						   "Feedbacks: " + feedbacks + "\n" +
    						   "Publication: " + publication);
    	}
    }
    
    /** The evaluation criteria stored when blasting.  */
    public static class Blasting
    {	
    	public double score;
    	public double eValue;
    }
}
