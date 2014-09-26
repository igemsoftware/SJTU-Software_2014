package data_center;

public class DataCenter
{
	public SearchCenter searchCenter;
    //public DetailPageCenter DPC;
    public SketchCenter sketchCenter;
    public CompareCenter compareCenter;

    public DataCenter()
    {
    	searchCenter = new SearchCenter();
        sketchCenter = new SketchCenter();
        compareCenter = new CompareCenter();
    }
}
