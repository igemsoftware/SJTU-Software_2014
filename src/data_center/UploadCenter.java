package data_center;

public class UploadCenter
{
	public UploadCenter() {}
	
	/** rawName is also known as the shortName */
	public boolean isBbkNameNotOccupied(String rawName)
	{	
		String nameByIgemOrg = "BBa_" + rawName;
		String nameByEasyBbk = "BBa_" + rawName + "_EasyBbk";
		return  BbkDatabaseConnector.getOutlineByName(nameByIgemOrg) == null && 
				BbkDatabaseConnector.getOutlineByName(nameByEasyBbk) == null;
	}
	
	public String uploadAndGetOddNum(BbkUpload bbkUpload)
	{	
		return BbkDatabaseConnector.upload(bbkUpload);
	}
	
	public BbkUpload getBbkUploadByNameAndOddNum(String name, String oddNum)
	{	
		return BbkDatabaseConnector.getBbkUploadByNameAndOddNum(name, oddNum);
	}
	
	public boolean isSequanceValid(String sequenceToken)
	{	
		sequenceToken = sequenceToken.toLowerCase();
		for (int i = 0; i < sequenceToken.length(); ++i)
		{	char ch = sequenceToken.charAt(i);
			if (ch != 'a' || ch != 't' || ch != 'c' || ch != 'g')
				return false;
		}
		return true;
	}
	
	public BbkDetail getSubpartForSequenceToken(String bbkName)
	{	
		return BbkDatabaseConnector.getDetailByName(bbkName);
	}
	
	public BbkUpload.SpecifiedSubscar getSubscarForSequenceToken(String scarName)
	{	
		return BbkDatabaseConnector.getScarByName(scarName);
	}
	
	
	
}
