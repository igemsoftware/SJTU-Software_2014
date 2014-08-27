package data_center;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BbkOutline
{
	public String name;
    public String type;
    public String author;
    public String enterDate;
    public String shortDesc;   // short description
    public String url;

    public BbkOutline()
    { }

    public BbkOutline(String name, String type, String enterDate, 
    		String author, String shortDesc, String url)
    {
        this.name = name;
        this.type = type;
        this.author = author;
        this.enterDate = enterDate;
        this.shortDesc = shortDesc;
        this.url = url;
    }
    
    public BbkOutline(ResultSet resultSet) throws SQLException
    {	
    	name = resultSet.getString(TableHeader.NAME);
	    type = resultSet.getString(TableHeader.TYPE);
	    author = resultSet.getString(TableHeader.AUTHOR);
	    enterDate = resultSet.getString(TableHeader.ENTER_DATE);
	    shortDesc = resultSet.getString(TableHeader.SHORT_DESC);
	    url = resultSet.getString(TableHeader.URL);
    }

	public boolean isTypeOf(int typeNo)
	{
		switch (typeNo)
		{
			case BbkType.PROMOTER:
				return type.equals(BbkType.strOf_PROMOTER);
			case BbkType.RBS:
				return type.equals(BbkType.strOf_RBS);
			case BbkType.PROTEIN_DOMAIN:
				return type.equals(BbkType.strOf_PROTEIN_DOMAIN);
			case BbkType.PROTEIN_CODING_SEQUENCE:
				return type.equals(BbkType.strOf_PROTEIN_CODING_SEQUENCE);
			case BbkType.TRANSLATIONAL_UNIT:
				return type.equals(BbkType.strOf_TRANSLATIONAL_UNIT);
			case BbkType.TERMINATOR:
				return type.equals(BbkType.strOf_TERMINATOR);
			case BbkType.DNA:
				return type.equals(BbkType.strOf_DNA);
			case BbkType.PLASMID_BACKBONE:
				return type.equals(BbkType.strOf_PLASMID_BACKBONE);
			case BbkType.PLASMID:
				return type.equals(BbkType.strOf_PLASMID);
			case BbkType.PRIMER:
				return type.equals(BbkType.strOf_PRIMER);
			case BbkType.COMPOSITE:
				return type.equals(BbkType.strOf_COMPOSITE);
			case BbkType.PROTEIN_GENERATOR:
				return type.equals(BbkType.strOf_PROTEIN_GENERATOR);
			case BbkType.REPORTER:
				return type.equals(BbkType.strOf_REPORTER);
			case BbkType.INVERTER:
				return type.equals(BbkType.strOf_INVERTER);
			case BbkType.SIGNALLING:
				return type.equals(BbkType.strOf_SIGNALLING);
			case BbkType.MEASUREMENT:
				return type.equals(BbkType.strOf_MEASUREMENT);
			case BbkType.OTHER:
				// if code reaches here, it must be an other
				return true;
			default:
				return false; 
		}
	}

	public boolean isEnteredBetween(int[] enterYear)
	{
		if (enterYear.length != 2)
		{	System.out.println("Invalid enter year filter... ");
			return false;
		}
		// else
		int theYear = Integer.parseInt(enterDate.substring(0, 4));
		return (enterYear[0] <= theYear) && (enterYear[1] >= theYear);
	}

    public void display()
    {
        System.out.println( "Name: " + name + "\n" + 
                            "Type: " + type + "\n" + 
                            "Author: " + author + "\n" + 
                            "EnterDate: " + enterDate + "\n" + 
                            "ShortDescription: " + shortDesc + "\n" +
                            "url" + url + "\n");
    }
}
