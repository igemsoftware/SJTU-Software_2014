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
    	}
    	
    	public int getScore()
    	{
    		return getScore(DBConsts.Rating.STATUS_DEFAULT_WEIGHT, 
    						DBConsts.Rating.QUALITY_DEFAULT_WEIGHT,
    						DBConsts.Rating.FEEDBACKS_DEFAULT_WEIGHT,
    						DBConsts.Rating.PUBLICATION_DEFAULT_WEIGHT);
    	}
    	
    	public int getScore(double status_weight, double quality_weight, 
    						double feedbacks_weight, double publication_weight)
    	{	
    		double totalPoints = DBConsts.Rating.TOTAL_POINTS;
    		double points = Double.parseDouble(status) / DBConsts.Rating.STATUS_DEFAULT_WEIGHT * status_weight
    					  + Double.parseDouble(quality) / DBConsts.Rating.QUALITY_DEFAULT_WEIGHT * quality_weight
    					  + Double.parseDouble(feedbacks) / DBConsts.Rating.FEEDBACKS_DEFAULT_WEIGHT * feedbacks_weight
    					  + Double.parseDouble(publication) / DBConsts.Rating.PUBLICATION_DEFAULT_WEIGHT * publication_weight;
    		int score = (int)(points / totalPoints * 100);
    		return score;
    	}
    	
    	public void display()
    	{	
    		System.out.println("Rating: "+ rating + "\n" + 
    						   "Status: " + status + "\n" + 
    						   "Quality: " + quality + "\n" + 
    						   "Feedbacks: " + feedbacks + "\n" +
    						   "Publication: " + publication + "\n" +
    						   "Total Score: " + getScore());
    	}
    }
    
    public static class Blasting
    {	
    	int score;
    	double eValue;
    }
}
