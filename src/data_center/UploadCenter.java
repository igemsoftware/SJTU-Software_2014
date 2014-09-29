package data_center;

public class UploadCenter
{
	public UploadCenter() {}
	
	public boolean isSequanceValid(String sequence)
	{	
		sequence = sequence.toLowerCase();
		for (int i = 0; i < sequence.length(); ++i)
		{	char ch = sequence.charAt(i);
			if (ch != 'a' || ch != 't' || ch != 'c' || ch != 'g')
				return false;
		}
		return true;
	}
}
