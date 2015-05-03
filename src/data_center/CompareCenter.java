package data_center;

import java.awt.Color;
import java.awt.Rectangle;

/** Provide a comparison between up to 3 bbkDetails, pos == 0, 1, 2 */
public class CompareCenter
{
	public BbkDetail[] compareList = {null, null, null};
	
	public CompareCenter() {}
	
	/** Specify a bbkName to inquire in the database, set it in the list and return. 
	 * @return The BbkDetail instance just assigned, null if the bbkName is not in 
	 * database or the pos is not 0, 1 or 2.  */
	public BbkDetail assignDetail(String bbkName, int pos)
	{	
		if (pos < 0 || pos > 2)
		{	System.out.println("Invalid position... 0.0");
			return null;
		}
		// else... 
		BbkDetail bbkDetail = DatabaseConnector.getDetailByName(bbkName);
		compareList[pos] = bbkDetail;
		return bbkDetail;
	}
	
	/** Check the detail stored in the 3 slots in the compareCenter.  */
	public BbkDetail getDetail(int pos)
	{	
		if (pos < 0 || pos > 2)
		{	System.out.println("Invalid position... 0.0");
			return null;
		}
		// else... 
		return compareList[pos];
	}
	
	public SketchComponent.BioBrick addSearchedBbkToSketch
		(int pos, SketchCenter sketchCenter)
	{	
		BbkOutline bbkOutline = getDetail(pos);
		if (bbkOutline == null || sketchCenter.currentProject == null)
			return null;
		
		int ID = sketchCenter.currentProject.getMaxID() + 1;
		SketchComponent.BioBrick bbkInSketch = new SketchComponent.BioBrick
				(ID, BbkType.bbkTypeToSketchBbkType(bbkOutline.type), 
						new Rectangle(10, 10, 10, 10), Color.BLUE);
		bbkInSketch.bbkOutline = bbkOutline;
		sketchCenter.currentProject.addComponent(bbkInSketch);
		return bbkInSketch;
	}
}
