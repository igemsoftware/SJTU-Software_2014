package data_center;

import java.util.ArrayList;

@SuppressWarnings("serial")
public class SearchResultList extends ArrayList<BbkOutline>
{
	public SearchResultList()
	{	super(50);	}

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
}