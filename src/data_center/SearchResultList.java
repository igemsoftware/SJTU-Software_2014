package data_center;

import java.util.ArrayList;

@SuppressWarnings("serial")
public class SearchResultList extends ArrayList<BbkOutline>
{
	public SearchResultList()
	{	super(50);	}
	
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

	public SearchResultList Filter(ArrayList<Integer> typeList, int[] enterYear)
	{
		SearchResultList filteredList = new SearchResultList();

		if (typeList != null && enterYear != null)	// filter by two conditions
		{
			for (BbkOutline bbk : this)
				for (int type : typeList)
					// if is not the type, won't check the enterYear
					if (bbk.isTypeOf(type)
						&& bbk.isEnteredBetween(enterYear))
						filteredList.add(bbk);
		}
		else if (typeList != null && enterYear == null)	// filter by type
		{
			for (BbkOutline bbk : this)
				for (int type : typeList)
					if (bbk.isTypeOf(type))
						filteredList.add(bbk);
		}
		else if (typeList == null && enterYear != null)	// filter by year
		{
			for (BbkOutline bbk : this)
				if (bbk.isEnteredBetween(enterYear))
					filteredList.add(bbk);
		}
		else	// don't filter
		{
			for (BbkOutline bbk : this)
				filteredList.add(bbk);
		}
		return filteredList;
	}
	
	public void display()
	{	
		for (BbkOutline bbk : this)
			bbk.display();
	}
	
}