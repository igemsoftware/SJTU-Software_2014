package data_center;

/** The overall function of the package data_center. Keep the Datacenter instance to 
 * get access to the searchCenter, sketchCenter, compareCenter, uploadCenter, which
 * provide all of the main function in EasyBbk.  */
public class DataCenter
{
	public SearchCenter searchCenter;
    public SketchCenter sketchCenter;
    public CompareCenter compareCenter;
    public UploadCenter uploadCenter;

    public DataCenter()
    {
    	searchCenter = new SearchCenter();
        sketchCenter = new SketchCenter();
        compareCenter = new CompareCenter();
        uploadCenter = new UploadCenter();
    }
}
