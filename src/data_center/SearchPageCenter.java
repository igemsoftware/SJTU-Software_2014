package data_center;

public class SearchPageCenter
{
	public HistoryList<SearchResultList> historyList;
    public BbkDatabaseConnector connector;

    public SearchPageCenter()
    {
		historyList = new HistoryList<SearchResultList>();
        connector = new BbkDatabaseConnector();
    }


    public boolean canRollBack()
	{	
		return historyList.canRollBack();
	}

    public boolean canGoForward()
	{	
		return historyList.canGoForward();
	}


	public SearchResultList getCurrentRawSearchResultList()
	{
		return historyList.getCurrentItem();
	}

    public SearchResultList search(String keyword)
    {
        SearchResultList currentList = connector.search(keyword);
		historyList.putInItem(currentList);
        return historyList.getCurrentItem();
    }

	public SearchResultList rollBack()
    {
		return historyList.rollBack();
    }

	public SearchResultList goForward()
    {
		return historyList.goForward();
    }
}
