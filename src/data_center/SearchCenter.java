package data_center;

/** Provide functions about searching database by SJTU-software. Can search by keyword, 
 * blast by sequence, get detail by name.  */
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
        SearchResultList currentList = DatabaseConnector.search(keyword);
		historyList.putInItem(currentList);
        return historyList.getCurrentItem();
    }
    
    public SearchResultList blast(String input, int mode)
    {	
    	SearchResultList currentList = BlastingSearcher.blast(input, mode);
    	historyList.putInItem(currentList);
    	return historyList.getCurrentItem();
    }
    
    public BbkDetail getDetail(String bbkName)
    {	
    	return DatabaseConnector.getDetailByName(bbkName);
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
