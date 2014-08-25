package data_center;

import java.util.ArrayList;

public class HistoryList<T>
{
	public int currentItemNo = -1;
	private ArrayList<T> dataList = new ArrayList<T>();

	public void putInItem(T item)
	{
		if (dataList.size() == 0)	// namely currentPageNo == -1
		{
			dataList.add(item);
			currentItemNo = 0;
		}

		// else... 
		for (int i = dataList.size() - 1; i >= currentItemNo; ++i)
			dataList.remove(i);
		dataList.add(item);		// currentPageNo not changed when adding
	}

	public T getCurrentItem()
	{
		if (dataList.size() == 0)
			return null;
		else
			return dataList.get(currentItemNo);
	}

	public boolean canRollBack()
	{	
		return currentItemNo > 0;
	}

	public boolean canGoForward()
	{	
		return currentItemNo < dataList.size();
	}


	public T getCurrentRawSearchResult()
	{
		if (dataList.size() == 0)
			return null;
		else
			return dataList.get(currentItemNo);
	}

	public T rollBack()
	{
		if (canRollBack())
		{
			--currentItemNo;
			return dataList.get(currentItemNo);
		}
		else
		{
			System.out.println
				("Already in the head of the list, can't roll back... ");
			return null;
		}
	}

	public T goForward()
	{
		if (canGoForward())
		{
			++currentItemNo;
			return dataList.get(currentItemNo);
		}
		else
		{
			System.out.println
				("Already in the tail of the list, can't go forward... ");
			return null;
		}
	}
}
