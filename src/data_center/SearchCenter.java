package data_center;

public class SearchCenter
{
	public HistoryList<SearchResultList> historyList;
    
    public SearchCenter()
    {
		historyList = new HistoryList<SearchResultList>();
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
        SearchResultList currentList = BbkDatabaseConnector.search(keyword);
		historyList.putInItem(currentList);
        return historyList.getCurrentItem();
    }
    
    public SearchResultList blast(String input, int mode)
    {	
    	SearchResultList currentList = BbkBlaster.blast(input, mode);
    	historyList.putInItem(currentList);
    	return historyList.getCurrentItem();
    }
    
    public BbkDetail getDetail(String bbkName)
    {	
    	return BbkDatabaseConnector.getDetailByName(bbkName);
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
