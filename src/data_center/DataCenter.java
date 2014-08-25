package data_center;

public class DataCenter
{
	public SearchPageCenter SPC;
    public DetailPageCenter DPC;
    public SketchProject paintingProject;

    public DataCenter()
    {
        SPC = new SearchPageCenter();
        DPC = new DetailPageCenter();
        paintingProject = new SketchProject();
    }
}
