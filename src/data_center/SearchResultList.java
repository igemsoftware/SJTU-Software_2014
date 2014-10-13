package data_center;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/** This class is a list for BbkOutline instance. Extending from ArrayList, the class
 * also provides filter and sort function, and a field to store the keyword previously
 * specified.  */
@SuppressWarnings("serial")
public class SearchResultList extends ArrayList<BbkOutline>
{
	public String keyword = null;
	
	public SearchResultList()
	{	super(100);	}
	
	public SearchResultList(String keyword)
	{	
		super(100);
		this.keyword = keyword;
	}
	
	public boolean has(String bbkName)
	{	
		for (BbkOutline bbkOutline : this)
			if (bbkOutline.name.equals(bbkName))
				return true;
		return false;
	}
	
	public BbkOutline getByName(String bbkName)
	{	
		for (BbkOutline bbkOutline : this)
			if (bbkOutline.name.equals(bbkName))
				return bbkOutline;
		return null;
	}
	
	/** Able to be use like "listToShow = rawList.filterByType().filterByEnterYear()...;" 
	 * Please use the String in the SearchResultList.Filter as the filter strings.  */
	public SearchResultList filterByType(ArrayList<String> typeList)
	{	
		if (typeList == null)	// don't filter
			return (SearchResultList) this.clone();
		// else
		SearchResultList filteredList = new SearchResultList(this.keyword);
		boolean pickOutUnmatchedTypes = typeList.contains(Filter.Type.OTHER);
		boolean addedIntoFilteredList;
		for (BbkOutline bbk : this)
		{	addedIntoFilteredList = false;
			for (String type : typeList)
				if (bbk.type.equals(type))
				{	filteredList.add(bbk);
					addedIntoFilteredList = true;
					break;
				}
			if ( !addedIntoFilteredList && pickOutUnmatchedTypes )
				filteredList.add(bbk);
		}
		return filteredList;
	}
	
	/** The parameter enterYear is a int array which has a length of 2. The 2 nums
	 * specify the earliest and the latest year to be kept in the filtered list. 
	 * Note that both of the years are judged by "<=" or ">=".  */
	public SearchResultList filterByEnterYear(int[] enterYear)
	{
		if (enterYear == null)	// don't filter
			return (SearchResultList) this.clone();
		if (enterYear.length != 2)
		{	System.out.println("Invalid filter enterYear... ");
			return (SearchResultList) this.clone();
		}
		// else
		SearchResultList filteredList = new SearchResultList(this.keyword);
		for (BbkOutline bbk : this)
		{	int theYear = Integer.parseInt(bbk.enterDate.substring(0, 4));
			if ((enterYear[0] <= theYear) && (enterYear[1] >= theYear))
				filteredList.add(bbk);;
		}
		return filteredList;
	}
	
	/** Please use the String in the SearchResultList.Filter */
	public SearchResultList filterByRelaseStatus(String status)
	{
		if (status == null)	// don't filter
			return (SearchResultList) this.clone();
		if ( !status.equals(Filter.ReleaseStatus.RELEASED) &&
			 !status.equals(Filter.ReleaseStatus.NOT_RELEASED) &&
			 !status.equals(Filter.ReleaseStatus.DELETED))
		{	System.out.println("Invalid release status input... 0.0");
			return (SearchResultList) this.clone();
		}
		// else
		SearchResultList filteredList = new SearchResultList(this.keyword);
		for (BbkOutline bbk : this)
			if (bbk.releaseStatus.equals(status))
				filteredList.add(bbk);
		return filteredList;
	}
	
	/** Please use the String in the SearchResultList.Filter */
	public SearchResultList filterByDNAStatus(String status)
	{
		if (status == null)	// don't filter
			return (SearchResultList) this.clone();
		if ( !status.equals(Filter.DNAStatus.AVAILABLE) &&
			 !status.equals(Filter.DNAStatus.DELETED) &&
			 !status.equals(Filter.DNAStatus.INFORMATIONAL) &&
			 !status.equals(Filter.DNAStatus.PLANNING) &&
			 !status.equals(Filter.DNAStatus.UNAVAILABLE))
		{	System.out.println("Invalid DNA status input... 0.0");
			return (SearchResultList) this.clone();
		}
		// else
		SearchResultList filteredList = new SearchResultList(this.keyword);
		for (BbkOutline bbk : this)
			if (bbk.DNA_status.equals(status))
				filteredList.add(bbk);
		return filteredList;
	}
	
	public SearchResultList filterByDeletedOrNot(Boolean deleted)
	{
		if (deleted == null)	// don't filter
			return (SearchResultList) this.clone();
		// else
		SearchResultList filteredList = new SearchResultList(this.keyword);
		for (BbkOutline bbk : this)
			if ( (bbk.rating.delete_this_part.equals(Filter.DeletedOrNot.DELETED)) == deleted)
				filteredList.add(bbk);
		return filteredList;
	}
	
	/** 4 in starNumList means star number is [4, 5) */
	public SearchResultList filterByStarNum(ArrayList<Integer> starNumList)
	{
		SearchResultList filteredList = new SearchResultList(this.keyword);
		for (BbkOutline bbk : this)
			for (Integer starNum : starNumList)
				if ( bbk.rating.average_stars.startsWith(starNum.toString()) )
				{	filteredList.add(bbk);	break;	}
		return filteredList;
	}
	
	/** Change the list in situ, DESC means sort descending (½µÐò in Chinese), 
	 * which put the latest biobrick in the head of the list.  */
	public void sortByEnterDate(final boolean DESC)
	{	
		Comparator<BbkOutline> comparator = new Comparator<BbkOutline>()
		{
			@Override
			public int compare(BbkOutline bbk1, BbkOutline bbk2)
			{
				String[] YMD1 = bbk1.enterDate.split("/");	// year, month, date
				String[] YMD2 = bbk2.enterDate.split("/");
				int year1 = Integer.parseInt(YMD1[0]), year2 = Integer.parseInt(YMD2[0]);
				int month1 = Integer.parseInt(YMD1[1]), month2 = Integer.parseInt(YMD2[1]);
				int date1 = Integer.parseInt(YMD1[2]), date2 = Integer.parseInt(YMD2[2]);
				if (DESC)
				{	if (year1 != year2)
						return year2 - year1;
					else if (month1 != month2)
						return month2 - month1;
					else
						return date2 - date1;
				}
				else
				{	if (year1 != year2)
						return year1 - year2;
					else if (month1 != month2)
						return month1 - month2;
					else
						return date1 - date2;
				}
			}
		};
		Collections.sort(this, comparator);
	}
	
	/** Change the list in situ, DESC means sort descending (½µÐò in Chinese), 
	 * which put the most quoted biobrick in the head of the list.  */
	public void sortByGoogleQuoteNum(final boolean DESC)
	{	
		Comparator<BbkOutline> comparator = new Comparator<BbkOutline>()
		{
			@Override
			public int compare(BbkOutline bbk1, BbkOutline bbk2)
			{
				int quoteNum1, quoteNum2;
				try
				{	quoteNum1 = Integer.parseInt(bbk1.rating.google_items);	}
				catch (Exception e) {	quoteNum1 = 0;	}
				try
				{	quoteNum2 = Integer.parseInt(bbk2.rating.google_items);	}
				catch (Exception e) {	quoteNum2 = 0;	}
				if (DESC)
					return quoteNum2 - quoteNum1;
				else
					return quoteNum1 - quoteNum2;
			}
		};
		Collections.sort(this, comparator);
	}
	
	/** Change the list in situ, DESC means sort descending (½µÐò in Chinese), 
	 * which put the biobrick with most stars in the head of the list.  */
	public void sortByAverageStars(final boolean DESC)
	{	
		Comparator<BbkOutline> comparator = new Comparator<BbkOutline>()
		{
			@Override
			public int compare(BbkOutline bbk1, BbkOutline bbk2)
			{
				double star1, star2;
				try
				{	star1 = Double.parseDouble(bbk1.rating.average_stars);	}
				catch (Exception e) {	star1 = 0;	}
				try
				{	star2 = Double.parseDouble(bbk2.rating.average_stars);	}
				catch (Exception e) {	star2 = 0;	}
				if (DESC)
				{	if (star1 > star2)	return -1;
					else if (star1 < star2) return 1;
					else return 0;
				}
				else
				{	if (star1 > star2)	return 1;
					else if (star1 < star2) return -1;
					else return 0;
				}
			}
		};
		Collections.sort(this, comparator);
	}
	
	/** Change the list in situ, DESC means sort descending (½µÐò in Chinese), 
	 * which put the biobrick with most confirmed times in the head of the list.  */
	public void sortByConfrimedTimes(final boolean DESC)
	{	
		Comparator<BbkOutline> comparator = new Comparator<BbkOutline>()
		{
			@Override
			public int compare(BbkOutline bbk1, BbkOutline bbk2)
			{
				int confrimedTimes1, confrimedTimes2;
				try
				{	confrimedTimes1 = Integer.parseInt(bbk1.rating.tot_confirmed);	}
				catch (Exception e) {	confrimedTimes1 = 0;	}
				try
				{	confrimedTimes2 = Integer.parseInt(bbk2.rating.tot_confirmed);	}
				catch (Exception e) {	confrimedTimes2 = 0;	}
				if (DESC)
					return confrimedTimes2 - confrimedTimes1;
				else
					return confrimedTimes1 - confrimedTimes2;
			}
		};
		Collections.sort(this, comparator);
	}
	
	/** Change the list in situ, DESC means sort descending (½µÐò in Chinese), 
	 * which put the biobrick with the most similar sequence in the head of the list.  */
	public void sortByBlastResult(final boolean DESC)
	{	
		Comparator<BbkOutline> comparator = new Comparator<BbkOutline>()
		{
			@Override
			public int compare(BbkOutline bbk1, BbkOutline bbk2)
			{
				if (bbk1.blasting == null || bbk2.blasting == null) // not blasted
					return 0;
				// else
				if (DESC)
				{	if (bbk1.blasting.eValue < bbk2.blasting.eValue) // 1 should close to the top of the list
						return 1;
					else if (bbk1.blasting.eValue > bbk2.blasting .eValue)
						return -1;
					else if (bbk1.blasting.score > bbk2.blasting.score)
						return 1;
					else if (bbk1.blasting.score < bbk2.blasting.score)
						return -1;
					else	// all equal
						return 0;
				}
				else
				{	if (bbk1.blasting.eValue < bbk2.blasting.eValue)
						return -1;
					else if (bbk1.blasting.eValue > bbk2.blasting .eValue)
						return 1;
					else if (bbk1.blasting.score > bbk2.blasting.score)
						return -1;
					else if (bbk1.blasting.score < bbk2.blasting.score)
						return 1;
					else	// all equal
						return 0;
				}
			}
		};
		Collections.sort(this, comparator);
	}
	
	/** Change the list in situ, DESC means sort descending (½µÐò in Chinese), 
	 * which put the biobrick with highest score in the head of the list. 
	 * The score is under default weight defined by SJTU-software team.  */
	public void sortByTotalScore(final boolean DESC)
	{	
		Comparator<BbkOutline> comparator = new Comparator<BbkOutline>()
		{
			@Override
			public int compare(BbkOutline bbk1, BbkOutline bbk2)
			{
				double score1 = bbk1.getScore(), score2 = bbk2.getScore();
				if (DESC)
					return (int)((score2 - score1) * 100);
				else
					return (int)((score1 - score2) * 100);
			}
		};
		Collections.sort(this, comparator);
	}
	
	/** Change the list in situ, DESC means sort descending (½µÐò in Chinese), 
	 * which put the biobrick with highest score in the head of the list. 
	 * The score is under the user defined weight.  */
	public void sortByTotalScore(final boolean DESC, 
			final double status_weight, final double quality_weight, 
			final double feedbacks_weight, final double publication_weight)
	{	
		Comparator<BbkOutline> comparator = new Comparator<BbkOutline>()
		{
			@Override
			public int compare(BbkOutline bbk1, BbkOutline bbk2)
			{
				double score1 = bbk1.getScore(status_weight, quality_weight, 
						feedbacks_weight, publication_weight);
				double	score2 = bbk2.getScore(status_weight, quality_weight, 
						feedbacks_weight, publication_weight);
				if (DESC)
					return (int)((score2 - score1) * 100);
				else
					return (int)((score1 - score2) * 100);
			}
		};
		Collections.sort(this, comparator);
	}
	
	public void display()
	{	
		for (BbkOutline bbk : this)
			bbk.display();
	}
	
	public void displayFilteringConditions()
	{	
		for (BbkOutline bbk : this)
			bbk.displayFilteringConditions();
	}
	
	public void displaySortingConditions()
	{	
		for (BbkOutline bbk : this)
			bbk.displaySortingConditions();
	}
	
	public void displayRating()
	{	
		for (BbkOutline bbk : this)
			bbk.displayRating();
	}
	
	/** Instead of print, providing another way to display the content of the list */
	public String[][] toTwoDimensionalArray()
	{
		String[][] rows = null;
		int rowsize = this.size();
		rows = new String[rowsize][6];
		int rowcount = 0;
		for (int i = 0; i < rowsize; i++)
		{
			rows[rowcount][0] = this.get(i).name;
			rows[rowcount][1] = this.get(i).type;
			rows[rowcount][2] = this.get(i).author;
			rows[rowcount][3] = this.get(i).enterDate;
			rows[rowcount][4] = this.get(i).shortDesc;
			rows[rowcount][5] = this.get(i).url;
			rowcount++;
	    }
		return rows;
	}
	
	
	
	/** Constants used when filtering.  */
	public static class Filter
	{	
		public static class Type
		{	
			public final static String PROMOTER = "Regulatory";
			public final static String RBS = "RBS";
			public final static String PROTEIN_DOMAIN = "Tag";
			public final static String PROTEIN_CODING_SEQUENCE = "Coding";
			public final static String TRANSLATIONAL_UNIT = "Translational_Unit";
			public final static String TERMINATOR = "Terminator";
			public final static String DNA = "DNA";
			public final static String PLASMID_BACKBONE = "Plasmid_Backbone";
			public final static String PLASMID = "Plasmid";
			public final static String PRIMER = "Primer";
			public final static String COMPOSITE = "Composite";
			public final static String PROTEIN_GENERATOR = "Generator";
			public final static String REPORTER = "Reporter";
			public final static String INVERTER = "Inverter";
			public final static String SIGNALLING = "Signalling";
			public final static String MEASUREMENT = "Measurement";
			public final static String OTHER = "Other";
		}
		
		public static class ReleaseStatus
		{	
			public final static String RELEASED = "Released HQ 2013";
			public final static String NOT_RELEASED = "Not Released";
			public final static String DELETED = "Deleted";
		}
		
		public static class DNAStatus
		{	
			public final static String AVAILABLE = "Available";
			public final static String DELETED = "Deleted";
			public final static String INFORMATIONAL = "Informational";
			public final static String PLANNING = "Planning";
			public final static String UNAVAILABLE = "Unavailable";
		}
		
		public static class DeletedOrNot
		{	
			public final static String DELETED = "Deleted";
			public final static String NOT_DELETED = "Not Deleted";
		}
	}
	
	
}