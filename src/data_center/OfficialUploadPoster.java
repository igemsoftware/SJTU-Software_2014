package data_center; 

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;

import data_center.BbkUpload.Category;

public class OfficialUploadPoster
{
	private static CloseableHttpClient httpClient;
	private static HttpClientContext context;
	
	public static boolean login(String username, String password) throws Exception
	{
		httpClient = HttpClients.createDefault();
		context = HttpClientContext.create();
		context.setCookieStore(new BasicCookieStore());
	    
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
	    nvps.add(new BasicNameValuePair("Login", "Login"));
	    nvps.add(new BasicNameValuePair("username", username));
	    nvps.add(new BasicNameValuePair("password", password));
	    
	    HttpPost httpost = new HttpPost("https://igem.org/Login2");
	    httpost.setEntity(new UrlEncodedFormEntity(nvps));
		httpClient.execute(httpost, context);
	
	    HttpGet httpget = new HttpGet("http://igem.org/Login_Confirmed");
	    CloseableHttpResponse response = httpClient.execute(httpget, context);
		
		BufferedReader reader = new BufferedReader
				(new InputStreamReader(response.getEntity().getContent(), "utf-8"));
		
		String line;
		while ( (line = reader.readLine()) != null )
			if (line.contains("You have successfully logged into the iGEM and Registry web sites."))
				return true;
		reader.close();
		// else... 
		return false;
	}
	
	public static String getNextAvailablePartName() throws Exception
	{	
		HttpGet httpget = new HttpGet("http://parts.igem.org/cgi/partsdb/add_part_b.cgi");
	    CloseableHttpResponse response = httpClient.execute(httpget, context);
	    BufferedReader reader = new BufferedReader
				(new InputStreamReader(response.getEntity().getContent(), "utf-8"));
		
		String line;
		// the next available name is surrounded like "<TD>BBa_XXXX<TR>"
		// 	before it there is a "<TD>BBa_XXX to BBa_XXX<TD>" token to be excluded. 
		while ( (line = reader.readLine()) != null )
			if (line.contains("Next Available Part"))
			{	String[] rawTokens = line.split("<TD>");
				for (String rawToken : rawTokens)
					if (rawToken.startsWith("BBa_") && !rawToken.contains(" to BBa_"))
					{	String[] tokens = rawToken.split("<TR>");
						return tokens[0];
					}
			}
		reader.close();
		// not found the string that fits the conditions
		return null;
	}
	
	public static String[] getAvailablePartNameRegion() throws Exception
	{	
		HttpGet httpget = new HttpGet("http://parts.igem.org/cgi/partsdb/add_part_b.cgi");
	    CloseableHttpResponse response = httpClient.execute(httpget, context);
	    BufferedReader reader = new BufferedReader
				(new InputStreamReader(response.getEntity().getContent(), "utf-8"));
		
		String line;
		// the available name region is surrounded like "<TD>BBa_XXX to BBa_XXX<TD>"
		while ( (line = reader.readLine()) != null )
			if (line.contains("Next Available Part"))
				for (String token : line.split("<TD>"))
					if (token.startsWith("BBa_") && token.contains(" to BBa_"))
						return token.split(" to ");
		reader.close();
		// not found the string that fits the conditions
		return null;
	}
	
	public static String createNewPart(String part_name, BbkUpload bbkUpload) throws Exception
	{	
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
	    nvps.add(new BasicNameValuePair("id", "0"));
	    nvps.add(new BasicNameValuePair("group_1859", "on"));
	    nvps.add(new BasicNameValuePair("part_name", part_name));
	    nvps.add(new BasicNameValuePair("type", bbkUpload.type));
	    nvps.add(new BasicNameValuePair("short_description", bbkUpload.shortDesc));
	    nvps.add(new BasicNameValuePair("long_description", bbkUpload.longDesc));
	    nvps.add(new BasicNameValuePair("source", bbkUpload.source));
	    nvps.add(new BasicNameValuePair("notes", bbkUpload.notes));
	    nvps.add(new BasicNameValuePair("proceed", "Go on to enter the sequence and add feature annotations"));
	    
	    HttpPost httpost = new HttpPost("http://parts.igem.org/cgi/partsdb/add_part_b.cgi");
	    httpost.setEntity(new UrlEncodedFormEntity(nvps));
		httpClient.execute(httpost, context);
		
		HttpGet httpget = new HttpGet("http://parts.igem.org/cgi/xml/part.cgi?part=" + part_name);
		CloseableHttpResponse response = httpClient.execute(httpget, context);
	    BufferedReader reader = new BufferedReader
				(new InputStreamReader(response.getEntity().getContent(), "utf-8"));
	    // the part_id is surrounded like "<part_id>XXXXX</part_id>"
	    String line;
	    while ( (line = reader.readLine()) != null )
	    	if (line.contains("part_id"))
	    	{	String[] tokens = line.split("</part_id>")[0].split("<part_id>");
	    		return tokens[tokens.length - 1];
	    	}
		return null;
	}
	
	public static void modifyPrimaryInfo(BbkUpload bbkUpload) throws Exception
	{	
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
	    nvps.add(new BasicNameValuePair("id", bbkUpload.getID()));
	    nvps.add(new BasicNameValuePair("hidden_header", "14"));
	    nvps.add(new BasicNameValuePair("new_part_name", bbkUpload.getName()));
	    nvps.add(new BasicNameValuePair("Apply new name", "Apply new name"));
	    nvps.add(new BasicNameValuePair("short", bbkUpload.shortDesc));
	    nvps.add(new BasicNameValuePair("type", bbkUpload.type));
	    nvps.add(new BasicNameValuePair("nickname", bbkUpload.nickname));
	    nvps.add(new BasicNameValuePair("author", bbkUpload.author));
	    nvps.add(new BasicNameValuePair("works", "None"));
	    nvps.add(new BasicNameValuePair("favorite", bbkUpload.groupFavorite));
	    nvps.add(new BasicNameValuePair("delete_part", "Leave Unchanged"));
	    
	    HttpPost httpost = new HttpPost("http://parts.igem.org/cgi/partsdb/part_info.cgi?part_name=" + bbkUpload.getName());
	    httpost.setEntity(new UrlEncodedFormEntity(nvps));
		httpClient.execute(httpost, context);
	}

	public static void modifySequence(BbkUpload bbkUpload) throws Exception
	{	
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
	    nvps.add(new BasicNameValuePair("id", bbkUpload.getID()));
	    nvps.add(new BasicNameValuePair("dna_center", ""));
	    nvps.add(new BasicNameValuePair("dna_right", ""));
	    nvps.add(new BasicNameValuePair("hidden_dna", ""));
	    nvps.add(new BasicNameValuePair("Edit_dna", "1"));
	    nvps.add(new BasicNameValuePair("Save_dna.x", "1"));
	    nvps.add(new BasicNameValuePair("Cancel.x", "0"));
	    nvps.add(new BasicNameValuePair("plurality", "basic"));
	    nvps.add(new BasicNameValuePair("sequence", bbkUpload.getSequence()));
	    
	    HttpPost httpost = new HttpPost("http://parts.igem.org/partsdb/edit_seq.cgi?part=" + bbkUpload.getName());
	    httpost.setEntity(new UrlEncodedFormEntity(nvps));
		httpClient.execute(httpost, context);
	}
	
	public static void modifyCategories(BbkUpload bbkUpload) throws Exception
	{	
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();		
		ArrayList<data_center.BbkUpload.Category> uploadCategory = bbkUpload.categories;
		uploadCategory.sort(new StringComparator(1));
		nvps.add(new BasicNameValuePair("id", bbkUpload.getID()));
		nvps.add(new BasicNameValuePair("categories_center", ""));
	    nvps.add(new BasicNameValuePair("categories_right", ""));
	    nvps.add(new BasicNameValuePair("hidden_categories", "999"));
		for (int i=0; i<uploadCategory.size(); i++)
		{
			List<NameValuePair> localnvps = nvps;
			localnvps.add(new BasicNameValuePair("categories_999", uploadCategory.get(i).category));
			HttpPost httpost = new HttpPost("http://parts.igem.org/cgi/partsdb/part_info.cgi?part_name=" + bbkUpload.getName());
			httpost.setEntity(new UrlEncodedFormEntity(localnvps));
			httpClient.execute(httpost, context);
			nvps.add(new BasicNameValuePair("categories_"+(i+1), uploadCategory.get(i).category));
		}
			
//	    
//	    
//	    
//	    nvps.add(new BasicNameValuePair("Edit_dna", "1"));
//	    nvps.add(new BasicNameValuePair("Save_dna.x", "1"));
//	    nvps.add(new BasicNameValuePair("Cancel.x", "0"));
//	    nvps.add(new BasicNameValuePair("plurality", "basic"));
//	    nvps.add(new BasicNameValuePair("sequence", bbkUpload.getSequence()));
	    
//	    HttpPost httpost = new HttpPost("http://parts.igem.org/partsdb/edit_seq.cgi?part=" + bbkUpload.getName());
//	    httpost.setEntity(new UrlEncodedFormEntity(nvps));
//		httpClient.execute(httpost, context);
	}
	
	public static void modifyParameters()
	{	
		
	}
	
	
	public static void modifyFeatures()
	{	
		
	}
	
	
	
	static void testing() throws Exception
	{	
		BbkUpload bbkUpload = new BbkUpload();
		bbkUpload.setID("32081");
		bbkUpload.setName("BBa_K1479001");
		bbkUpload.shortDesc = "0.0 Testing the UE of the bio-brick upload page, not designing a real one";
		bbkUpload.type = "Terminator";
		bbkUpload.nickname = "LaLaLa";
		bbkUpload.author = "YiBai Chen";
		bbkUpload.groupFavorite = "No";
		bbkUpload.categories.add(new Category("//qwert"));
		bbkUpload.categories.add(new Category("//asdf"));
		bbkUpload.categories.add(new Category("//zxcv"));
		bbkUpload.categories.add(new Category("//qwert//q"));
		modifyCategories(bbkUpload);
	}
	
	public static class StringComparator implements Comparator<BbkUpload.Category>  
	{
		public final static int ASC = 1;  
	    public final static int DESC = -1;  
	  
	    private int NOA = 1;  
	    private int NOB = -1;  
	  
	    public StringComparator()  
	    {  
	    }  
	  
	    public StringComparator(int order)  
	    {  
	        if (StringComparator.DESC == order)  
	        {  
	            NOA = -1;  
	            NOB = 1;  
	        }  
	    }  
	  
	    public int compare(Category o1, Category o2)  
	    {  
	        if (o1.category.compareTo(o2.category) > 0)  
	        {  
	            return NOA;  
	        } else if (o1.category.compareTo(o2.category) < 0)  
	        {  
	            return NOB;  
	        } else  
	        {  
	            return 0;  
	        }  
	    }  
	}
  
}