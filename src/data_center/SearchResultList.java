package data_center;

import java.util.ArrayList;

@SuppressWarnings("serial")
public class SearchResultList extends ArrayList<BbkOutline>
{
	public SearchResultList()
	{	super(100);	}
	
	public String[][] showSearchResult()
	{
		String[][] rows=null;
		int rowsize = this.size();
		System.out.println(rowsize);
		rows = new String[rowsize][6];
		int rowcount=0;
		for (int i=0;i<rowsize;i++)
		{
			rows[rowcount][0]=this.get(i).name;
			rows[rowcount][1]=this.get(i).type;
			rows[rowcount][2]=this.get(i).author;
			rows[rowcount][3]=this.get(i).enterDate;
			rows[rowcount][4]=this.get(i).shortDesc;
			rows[rowcount][5]=this.get(i).url;
			rowcount++;
	    }
		return rows;
	}

	/** Use like "listToShow = rawList.filterByType().filterByEnterYear()...;" */
	public SearchResultList filterByType(ArrayList<String> typeList)
	{	
		if (typeList == null)	// don't filter
			return (SearchResultList) this.clone();
		// else
		SearchResultList filteredList = new SearchResultList();
		for (BbkOutline bbk : this)
			for (String type : typeList)
				if (bbk.type.equals(type))
					filteredList.add(bbk);
		return filteredList;
	}
	
	public SearchResultList filterByEnterYear(int[] enterYear)
	{
		if (enterYear == null)	// don't filter
			return (SearchResultList) this.clone();
		if (enterYear.length != 2)
		{	System.out.println("Invalid filter enterYear... ");
			return (SearchResultList) this.clone();
		}
		// else
		SearchResultList filteredList = new SearchResultList();
		for (BbkOutline bbk : this)
		{	int theYear = Integer.parseInt(bbk.enterDate.substring(0, 4));
			if ((enterYear[0] <= theYear) && (enterYear[1] >= theYear))
				filteredList.add(bbk);;
		}
		return filteredList;
	}
	
	public SearchResultList filterBySthInRating1()
	{
		// fix me
		return null;
	}
	
	public SearchResultList filterBySthInRating2()
	{
		// fix me
		return null;
	}
	
	public SearchResultList filterBySthInRating3()
	{
		// fix me
		return null;
	}
	
	public SearchResultList filterBySthInRating4()
	{
		// fix me
		return null;
	}
	
	public void display()
	{	
		for (BbkOutline bbk : this)
			bbk.display();
	}
	
}