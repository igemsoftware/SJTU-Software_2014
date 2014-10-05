package data_center;

import java.util.ArrayList;

/** The class implements a history list contains roll back and go forward function.  */
public class HistoryList<T>
{
	private int currentItemNo = -1;
	private ArrayList<T> dataList = new ArrayList<T>();

	/** Keeps the previous items and flush the following items.  */
	public void putInItem(T item)
	{
		// also suitable for currentItemNo == -1 case
		if (currentItemNo < dataList.size() - 1) // not appending, sth to delete
			for (int i = dataList.size() - 1; i > currentItemNo; --i)
				dataList.remove(i);
		// adding the new item into the list
		++currentItemNo;
		dataList.add(item);
	}

	public int getCurrentItemNo()
	{	
		return currentItemNo;
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
		return currentItemNo < dataList.size() - 1;
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
	
	public void clear()
	{	
		currentItemNo = -1;
		dataList.clear();
	}
}
