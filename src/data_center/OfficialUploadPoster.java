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
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

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
		//httpClient.close();
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
		httpClient = HttpClients.createDefault();
		HttpGet httpget = new HttpGet("http://parts.igem.org/cgi/partsdb/add_part_b.cgi");
	    CloseableHttpResponse response = httpClient.execute(httpget, context);
	    
	    BufferedReader reader = new BufferedReader
				(new InputStreamReader(response.getEntity().getContent(), "utf-8"));
		
		String line;
		//httpClient.close();
		// the next available name is surrounded like "<TD>BBa_XXXX<TR>"
		// 	before it there is a "<TD>BBa_XXX to BBa_XXX<TD>" token to be excluded. 
		while ( (line = reader.readLine()) != null )
			if (line.contains("Next Available Part"))
			{	String[] rawTokens = line.split("<TD>");
				for (String rawToken : rawTokens)
					if (rawToken.startsWith("BBa_") && !rawToken.contains(" to BBa_"))
					{	String[] tokens = rawToken.split("<TR>");
					System.out.println(tokens[0]);
						return tokens[0];
					}
			}
		reader.close();
		// not found the string that fits the conditions
		return null;
	}
	
	public static String[] getAvailablePartNameRegion() throws Exception
	{	
		//httpClient = HttpClients.createDefault();
		HttpGet httpget = new HttpGet("http://parts.igem.org/cgi/partsdb/add_part_b.cgi");
	    CloseableHttpResponse response = httpClient.execute(httpget, context);
	    BufferedReader reader = new BufferedReader
				(new InputStreamReader(response.getEntity().getContent(), "utf-8"));
		
		String line;
		//httpClient.close();
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
		//httpClient = HttpClients.createDefault();
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
				
		String url = "http://parts.igem.org/cgi/xml/part.cgi?part=" + part_name;
		System.out.println(url);
		Document doc=null;  
		try{  
			doc = Jsoup.connect(url).get();
		}  
		catch(Exception e){  
			e.printStackTrace();
		}
		Elements elem = doc.select("part_id");
		System.out.println(elem.html());
		httpClient.close();
		return elem.html();
	}
	
	public static void modifyPrimaryInfo(BbkUpload bbkUpload) throws Exception
	{
		httpClient = HttpClients.createDefault();
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
		httpClient.close();
	}

	public static void modifySequence(BbkUpload bbkUpload) throws Exception
	{	
		httpClient = HttpClients.createDefault();
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
		httpClient.close();
	}
	
	public static void modifyCategories(BbkUpload bbkUpload) throws Exception
	{		
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();		
		ArrayList<data_center.BbkUpload.Category> uploadCategory = bbkUpload.categories;
		uploadCategory.sort(new CatogoryComparator(1));
		nvps.add(new BasicNameValuePair("id", bbkUpload.getID()));
		nvps.add(new BasicNameValuePair("categories_center", ""));
	    nvps.add(new BasicNameValuePair("categories_right", ""));
	    nvps.add(new BasicNameValuePair("hidden_categories", "999"));
		HttpPost httpost = new HttpPost("http://parts.igem.org/cgi/partsdb/part_info.cgi?part_name=" + bbkUpload.getName());	
		for (int i=0; i<uploadCategory.size(); i++)
		{
			httpClient = HttpClients.createDefault();
			List<NameValuePair> localnvps = new ArrayList<NameValuePair>();
			localnvps = new ArrayList<NameValuePair>(nvps);
			localnvps.add(new BasicNameValuePair("categories_999", uploadCategory.get(i).category));
			httpost.setEntity(new UrlEncodedFormEntity(localnvps));
			httpClient.execute(httpost, context);
			httpClient.close();
			nvps.add(new BasicNameValuePair("categories_"+(i+1), uploadCategory.get(i).category));
		}
	}
	
	public static void modifyParameters(BbkUpload bbkUpload) throws Exception
	{	
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();		
		ArrayList<data_center.BbkUpload.Parameter> uploadParam = bbkUpload.parameters;
		uploadParam.sort(new ParamComparator(1));
		nvps.add(new BasicNameValuePair("id", bbkUpload.getID()));
		nvps.add(new BasicNameValuePair("parameters_center", ""));
	    nvps.add(new BasicNameValuePair("parameters_right", ""));
	    nvps.add(new BasicNameValuePair("hidden_parameters", "999"));
	    System.out.println(uploadParam.size());
		HttpPost httpost = new HttpPost("http://parts.igem.org/cgi/partsdb/part_info.cgi?part_name=" + bbkUpload.getName());
		System.out.println(httpost);
		for (int i=0; i<uploadParam.size(); i++)
		{
			httpClient = HttpClients.createDefault();
			List<NameValuePair> localnvps = new ArrayList<NameValuePair>();
			localnvps = new ArrayList<NameValuePair>(nvps);
			localnvps.add(new BasicNameValuePair("parameter_name_999", uploadParam.get(i).name));
			localnvps.add(new BasicNameValuePair("parameter_value_999", uploadParam.get(i).value));
			httpost.setEntity(new UrlEncodedFormEntity(localnvps));
			httpClient.execute(httpost, context);
			httpClient.close();
			nvps.add(new BasicNameValuePair("parameter_name_"+(i+1), uploadParam.get(i).name));
			nvps.add(new BasicNameValuePair("parameter_value_"+(i+1), uploadParam.get(i).value));
		}
	}
	
	
	public static void modifyFeatures(BbkUpload bbkUpload) throws Exception
	{	
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();		
		ArrayList<data_center.BbkUpload.Feature> uploadFeature = bbkUpload.features;
		ArrayList<String> featuresId = new ArrayList<String>();
		nvps.add(new BasicNameValuePair("id", bbkUpload.getID()));
		nvps.add(new BasicNameValuePair("features_center", ""));
	    nvps.add(new BasicNameValuePair("features_right", ""));
	    nvps.add(new BasicNameValuePair("hidden_features", ""));
	    HttpPost httpost = new HttpPost("http://parts.igem.org/partsdb/edit_seq.cgi?part="+bbkUpload.getName());
		for (int i=0;i<uploadFeature.size();i++)
		{
			httpClient = HttpClients.createDefault();
			String url = "http://parts.igem.org/partsdb/edit_seq.cgi?id="+bbkUpload.getID()+"&New_feature=1";
			System.out.println(url);
			Document doc=null;  
			try{  
				doc = Jsoup.connect(url).get();  
			}  
			catch(Exception e){  
				e.printStackTrace();
			}
			HttpGet httpget = new HttpGet(url);
			httpClient.execute(httpget, context);
			httpClient.close();
			httpClient = HttpClients.createDefault();
			url = "http://parts.igem.org/cgi/xml/part.cgi?part="+bbkUpload.getName();
			//System.out.println(url);
			try{  
				doc = Jsoup.connect(url).get();
			}  
			catch(Exception e){  
				e.printStackTrace();
			}
			Elements elem = doc.select("features").select("id");
			//System.out.println(elem.size());
			for (int  j=0;j<elem.size();j++)
			{
				String currentId = elem.get(j).html();
				if (!featuresId.contains(currentId))
				{
					//System.out.println(currentId);
					featuresId.add(currentId);
					nvps.set(3, new BasicNameValuePair("hidden_features", currentId));
					nvps.add(new BasicNameValuePair("feature_type_"+currentId, bbkUpload.features.get(i).type));
					nvps.add(new BasicNameValuePair("feature_label_"+currentId, bbkUpload.features.get(i).title));
					nvps.add(new BasicNameValuePair("feature_start_"+currentId, bbkUpload.features.get(i).startPos));
					nvps.add(new BasicNameValuePair("feature_end_"+currentId, bbkUpload.features.get(i).endPos));
					nvps.add(new BasicNameValuePair("feature_reverse_"+currentId, bbkUpload.features.get(i).direction));
					httpost.setEntity(new UrlEncodedFormEntity(nvps));
					httpClient.execute(httpost, context);
					httpClient.close();
				}
			}
		}
	}
	
	
	
	static void testing() throws Exception
	{	
		BbkUpload bbkUpload = new BbkUpload();
		bbkUpload.setID("33789");
		bbkUpload.setName("BBa_K1479008");
		bbkUpload.shortDesc = "0.0 Testing the UE of the bio-brick upload page, not designing a real one";
		bbkUpload.type = "Terminator";
		bbkUpload.nickname = "LaLaLa";
		bbkUpload.author = "YiBai Chen";
		bbkUpload.groupFavorite = "No";
		bbkUpload.categories.add(new Category("//cds/enzyme/endonuclease/restriction"));
		bbkUpload.categories.add(new Category("//rnap/bacteriophage/t7"));
		bbkUpload.categories.add(new Category("//plasmidbackbone/copynumber"));
		bbkUpload.categories.add(new Category("//collections/biofab"));
		bbkUpload.parameters.add(new BbkUpload.Parameter("biology","1","","","",""));
		bbkUpload.parameters.add(new BbkUpload.Parameter("color","red","","","",""));
		bbkUpload.parameters.add(new BbkUpload.Parameter("i_l","1","","","",""));
		bbkUpload.parameters.add(new BbkUpload.Parameter("direction","1","","","",""));
		bbkUpload.features.add(new BbkUpload.Feature("","label1","misc","Fwd","2","3"));
		bbkUpload.features.add(new BbkUpload.Feature("","label2","rbs","Fwd","1","2"));
		bbkUpload.features.add(new BbkUpload.Feature("","label3","cds","Rev","3","4"));
		//modifyFeatures(bbkUpload);
		getNextAvailablePartName();
		createNewPart("BBa_K1479008",bbkUpload);
	}
	
	public static class CatogoryComparator implements Comparator<BbkUpload.Category>  
	{
		public final static int ASC = 1;  
	    public final static int DESC = -1;  
	  
	    private int NOA = 1;  
	    private int NOB = -1;  
	  
	    public CatogoryComparator()  
	    {  
	    }  
	  
	    public CatogoryComparator(int order)  
	    {  
	        if (CatogoryComparator.DESC == order)  
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
	
	public static class ParamComparator implements Comparator<BbkUpload.Parameter>  
	{
		public final static int ASC = 1;  
	    public final static int DESC = -1;  
	  
	    private int NOA = 1;  
	    private int NOB = -1;  
	  
	    public ParamComparator()  
	    {  
	    }  
	  
	    public ParamComparator(int order)  
	    {  
	        if (ParamComparator.DESC == order)  
	        {  
	            NOA = -1;  
	            NOB = 1;  
	        }  
	    }  
	  
	    public int compare(BbkUpload.Parameter o1, BbkUpload.Parameter o2)  
	    {  
	        if (o1.name.compareTo(o2.name) > 0)  
	        {  
	            return NOA;  
	        } else if (o1.name.compareTo(o2.name) < 0)
	        {  
	            return NOB;  
	        } else  
	        {  
	            return 0;  
	        }  
	    }  
	}
}