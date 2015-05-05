package data_center;

import java.awt.Color;
import java.awt.Rectangle;

/** Provide functions about searching database by SJTU-software. Can search by keyword, 
 * blast by sequence, get detail by name.  */
public class SearchCenter
{
	public static final int BLAST_MODE_INPUT_SEQUENCE = 0;
	public static final int BLAST_MODE_INPUT_FILE_PATH = 1;
	
	public SearchHistory historyList;
    
    public SearchCenter()
    {
		historyList = new SearchHistory();
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
        //SearchResultList currentList = DatabaseConnector.search(keyword);
    	RegistrySearcher searcher = new RegistrySearcher();
    	SearchResultList currentList = searcher.search(keyword);
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
    	//return DatabaseConnector.getDetailByName(bbkName);
    	return RegistrySearcher.getDetailFromSearchResultList
    			(bbkName, getCurrentRawSearchResultList());
    }

    /** Also can add bbkDetail to Sketch */
    public SketchComponent.BioBrick addSearchedBbkToSketch
		(BbkOutline bbkOutline, SketchCenter sketchCenter)
	{	
		if (sketchCenter.currentProject == null)
			return null;
		int ID = sketchCenter.currentProject.getMaxID() + 1;
		SketchComponent.BioBrick bbkInSketch = new SketchComponent.BioBrick
				(ID, BbkType.bbkTypeToSketchBbkType(bbkOutline.type), 
						new Rectangle(10, 10, 10, 10), Color.BLUE);
		bbkInSketch.bbkOutline = bbkOutline;
		sketchCenter.currentProject.addComponent(bbkInSketch);
		return bbkInSketch;
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
