package data_center;

import java.sql.ResultSet;
import java.sql.SQLException;

/** The class used to store the data to be shown in search result. All of the 
 * data are fetched from EasyBbk database and stored as Strings. Also contains
 * the getScore() method to get the score evaluated by SJTU-software team.  */
public class BbkOutline
{
	/*	Attributes below are shown in
		search result page and design BBK property panel:
	
		BBa_XXXXXX - name
		Type - type
		Entered Date - enterDate
		Short Description - shortDesc
		Author - author
		Main Page of This Part - url
		Part Status - releasedStatus
		Used times - rating.usedTimes
		Average Rating - rating.averageStars
		Number of Related Results on Google Scholar - rating.google_items
	*/
	
	public String name = "";
    public String type = "";
    public String author = "";
    public String enterDate = "";
    public String shortDesc = "";   // short description
    public String url = "";
    public String releaseStatus = "";
 	
    public Rating rating = null;
    public Blasting blasting = null;	// not new here cause not always blasting
    
 	public String ID = "";
 	public String shortName = "";
 	public String sampleStatus = "";
 	public String results = "";
 	public String nickname = "";
 	public String part_rating = "";
 	public String sequence = "";
 	public String samples = "";
 	public String references = "";
 	public String groups = "";
 	public String DNA_status = "";
 	public String groupFavorite = "";

    public BbkOutline()
    { }
    
    public BbkOutline(ResultSet resultSet) throws SQLException
    {	
    	DBDataFiller.dbIntoMain(resultSet, this);
    }
    
    /** Score at default weight.  */
    public double getScore()
    {	
    	return getScore(Consts_DB.Rating.STATUS_DEFAULT_WEIGHT, 
				Consts_DB.Rating.QUALITY_DEFAULT_WEIGHT,
				Consts_DB.Rating.FEEDBACKS_DEFAULT_WEIGHT,
				Consts_DB.Rating.PUBLICATION_DEFAULT_WEIGHT);
    }
    
    /** Score at custom weight.  */
    public double getScore(double status_weight, double quality_weight, 
						double feedbacks_weight, double publication_weight)
    {	
    	gatherScoreComponents();
    	double totalPoints = Consts_DB.Rating.TOTAL_POINTS;
		double points = 0;
		try
		{	points = Double.parseDouble(rating.status) / Consts_DB.Rating.STATUS_DEFAULT_WEIGHT * status_weight
				   + Double.parseDouble(rating.quality) / Consts_DB.Rating.QUALITY_DEFAULT_WEIGHT * quality_weight
				   + Double.parseDouble(rating.feedbacks) / Consts_DB.Rating.FEEDBACKS_DEFAULT_WEIGHT * feedbacks_weight
				   + Double.parseDouble(rating.publication) / Consts_DB.Rating.PUBLICATION_DEFAULT_WEIGHT * publication_weight;
		} catch (Exception e) {points = 0;}
		double score = points / totalPoints * 100 + 15;
		return score <= 100 ? score : 100;
    }
    
    private void gatherScoreComponents()
    {	
    	double status = 0, quality = 0, feedbacks = 0, publication = 0;
    	if (releaseStatus.equals("Released HQ 2013"))
    		status += 1.6 / 100;
    	if (sampleStatus.equals("In stock"))
    		status += 6.9 / 100;
    	if (DNA_status.equals("Available"))
    		status += 8.3 / 100;
    	else if (DNA_status.equals("Informational") || DNA_status.equals("Planning"))
    		status += 0.5 * 8.3 / 100;
    	if (rating.delete_this_part.equals("Not Deleted"))
    		status += 6.6 / 100;
    	status += getValue_underRoof(rating.tot_confirmed, 20) / 20 * 11.4 / 100;
    	rating.status = Double.toString(status);
    	
    	if (results.equals("Works"))
    		quality += 11.9 / 100;
    	quality += getValue_underRoof(rating.average_stars, 5) / 5 * 10.2 / 100;
    	if (groupFavorite.equals("Yes"))
    		quality += 1.2 / 100;
    	rating.quality = Double.toString(quality);
    	
    	feedbacks += getValue_underRoof(rating.used_times, 100) / 100 * 11.5 / 100;
    	feedbacks += getValue_underRoof(part_rating, 5) / 5 * 2.2 / 100;
    	feedbacks += getValue_underRoof(rating.tot_commets, 10) / 10 * 10.9 / 100;
    	rating.feedbacks = Double.toString(feedbacks);
    	
    	publication += (getValue_underRoof(rating.google_items, 10) + getValue_underRoof(rating.NCBI_quoteNum, 10)) / 20 * 11.7 / 100;
    	rating.publication = Double.toString(publication);
    }
    
    private static Double getValue_underRoof(String input, int max)
    {	
    	try {
    		Double value = Double.parseDouble(input);
    		return value <= max ? value : max;}
    	catch (Exception e) {return 0.0;}
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
    	//public String rating;
    	public String status = "0";
    	public String quality = "0";
    	public String feedbacks = "0";
    	public String publication = "0";
    	// rating details
    	public String delete_this_part = "Deleted";
     	public String tot_confirmed = "0";
     	public String average_stars = "0";
     	public String tot_commets = "0";
     	public String google_items = "0";
     	public String NCBI_quoteNum = "0";
     	public String google_query_link = "";
     	public String used_times = "0";
     	
     	//public String detail_not_confirmed;
     	//public String documentation;
    	
     	public Rating() {}
     	
    	public Rating(ResultSet resultSet) throws SQLException
    	{	
    		//rating = resultSet.getString(Consts_DB.Header.Main.RATING);
    		status = resultSet.getString(Consts_DB.Header.Main.STATUS);
    		quality = resultSet.getString(Consts_DB.Header.Main.QUALITY);
    		feedbacks = resultSet.getString(Consts_DB.Header.Main.FEEDBACKS);
    		publication = resultSet.getString(Consts_DB.Header.Main.PUBLICATION);
    		// rating details
    		delete_this_part = resultSet.getString(Consts_DB.Header.Main.DELETE_THIS_PART);
    		tot_confirmed = resultSet.getString(Consts_DB.Header.Main.TOT_CONFIRMED);
    		average_stars = resultSet.getString(Consts_DB.Header.Main.AVERAGE_STARS);
    		tot_commets = resultSet.getString(Consts_DB.Header.Main.TOT_COMMENTS);
    		google_items = resultSet.getString(Consts_DB.Header.Main.GOOGLE_ITEMS);
    		google_query_link = resultSet.getString(Consts_DB.Header.Main.FIRST_URL);
    		//detail_not_confirmed = resultSet.getString(Consts_DB.Header.Main.DETAIL_NOT_CONFIRMED);
    		//documentation = resultSet.getString(Consts_DB.Header.Main.DOCUMENTATION);
    		used_times = resultSet.getString(Consts_DB.Header.Main.USED_TIMES);
    	}
    	
    	/** Print main attributes in cmd for testing.  */
    	public void display()
    	{	
    		System.out.println("Status: " + status + "\n" + 
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
