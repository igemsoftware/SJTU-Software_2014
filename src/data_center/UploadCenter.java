package data_center;

import data_center.BbkUpload.*;

/** Provide function used when uploading a biobrick into database by SJTU-software. 
 * Also include validation check of the part_name, sequence the subparts and the subscars
 * to be filled into BbkUpload instance to be uploaded.  */
public class UploadCenter
{
	public UploadCenter() {}
	
	/** rawName is also known as the shortName
	 * @return true for the name is not occupied, so that it can be used as the 
	 * part_name to be uploaded.  */
	public boolean isBbkNameNotOccupied(String rawName)
	{	
		return DatabaseConnector.isUploadingBbkNameNotOccupied(rawName);
	}
	
	/** Upload a new biobrick and get the odd number used to modify it later.
	 *  The oddNum is stored in the part_id of an biobrick.  */
	public String uploadAndGetOddNum(BbkUpload bbkUpload)
	{	
		return DatabaseConnector.upload(bbkUpload);
	}
	
	/** Use the part_name and the oddNum to find a previously uploaded biobrick.
	The oddNum is stored as the part_id of an bbk */
	public BbkUpload getBbkUploadByNameAndOddNum(String name, String oddNum)
	{	
		return DatabaseConnector.getBbkUploadByNameAndOddNum(name, oddNum);
	}
	
	/** Check if the sequence string is only consist if base group, not other chars.  */
	public boolean isSequanceValid(String sequenceToken)
	{	
		sequenceToken = sequenceToken.toLowerCase();
		for (int i = 0; i < sequenceToken.length(); ++i)
		{	char ch = sequenceToken.charAt(i);
			if (ch != 'a' && ch != 't' && ch != 'c' && ch != 'g')
				return false;
		}
		return true;
	}
	
	/** Used for subpart validation check. 
	 * @return null if no such part_name is found, thus it can't be a subpart.  */
	public BbkDetail getSubpartForSequenceToken(String bbkName)
	{	
		//return DatabaseConnector.getDetailByName(bbkName);
		return new RegistrySearcher().searchDetailByName(bbkName);
	}
	
	/** Used for subscar validation check. 
	 * @return null if no such scar name is found, thus it can't be a subscar.  */
	public SpecifiedSubscar getSubscarForSequenceToken(String scarName)
	{	
		//return DatabaseConnector.getScarByName(scarName);
		return locallySearchSubscar(scarName);
	}
	
	/** Subscar info can hardly be queried through web page search, 
	 * so a temporarily local search is provided, including one and the most
	 *  popular subscar: RFC[10].  */
	private SpecifiedSubscar locallySearchSubscar(String scarName)
	{	
		if (scarName.equals("RFC[10]"))
		{	SpecifiedSubscar scar = new SpecifiedSubscar();
			scar.name = "RFC[10]";
			scar.ID = "1";
			scar.standard = "10";
			scar.type = "Scar";
			scar.nickname = "[10]";
			scar.comments = "";
			scar.sequence = "tactagag";
			return scar;
		}
		else 
			return null;
	}
	
	
}
