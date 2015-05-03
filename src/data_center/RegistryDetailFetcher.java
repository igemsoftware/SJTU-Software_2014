package data_center;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import data_center.BbkDetail.*;

class RegistryDetailFetcher
{
	private static Document getDoc(String url)
	{	
		Document doc = null;
		for (int i = 0; i < 3; ++i)
			try {doc = Jsoup.connect(url).timeout(100000).get();} 
			catch (IOException e) {System.out.println(url + "Timeout" + i);continue;}
		return doc;
	}
	
	private static void allQueriedCheck(RegistrySearcher searcher)
	{	
		synchronized (searcher)
		{	--searcher.queryRemains;
			if (searcher.queryRemains == 0)
				searcher.notify();
		}
	}
	
	static class XMLFetcher extends Thread
	{	
		public static final String URL_PREFIX = "http://parts.igem.org/cgi/xml/part.cgi?part=";
		
		public BbkDetail bbkDetail;
		public RegistrySearcher searcher;
		
		public XMLFetcher(BbkDetail bbkDetail, RegistrySearcher searcher)
		{	
			this.bbkDetail = bbkDetail;
			this.searcher = searcher;
		}
		
		public void run()
		{	
			Document doc = null;
			if (bbkDetail.name != null && !bbkDetail.name.equals("")) 
			{	String url = URL_PREFIX + bbkDetail.name;
				doc = getDoc(url);
			}
			if (doc == null)
			{	allQueriedCheck(searcher);	return;	}
			
			Elements elements = null;
			// filling main
			bbkDetail.name = doc.getElementsByTag(Consts_Registry.NAME).get(0).text();
			bbkDetail.type = doc.getElementsByTag(Consts_Registry.TYPE).get(0).text();
			bbkDetail.author = doc.getElementsByTag(Consts_Registry.AUTHOR).get(0).text();
			bbkDetail.enterDate = doc.getElementsByTag(Consts_Registry.ENTER_DATE).get(0).text();
			bbkDetail.shortDesc = doc.getElementsByTag(Consts_Registry.SHORT_DESC).get(0).text();
			bbkDetail.url = doc.getElementsByTag(Consts_Registry.URL).get(0).text();
			bbkDetail.ID = doc.getElementsByTag(Consts_Registry.ID).get(0).text();
			bbkDetail.shortName = doc.getElementsByTag(Consts_Registry.SHORT_NAME).get(0).text();
			bbkDetail.releaseStatus = doc.getElementsByTag(Consts_Registry.RELEASE_STATUS).get(0).text();
			bbkDetail.sampleStatus = doc.getElementsByTag(Consts_Registry.SAMPLE_STATUS).get(0).text();
			bbkDetail.results = doc.getElementsByTag(Consts_Registry.RESULTS).get(0).text();
			bbkDetail.nickname = doc.getElementsByTag(Consts_Registry.NICKNAME).get(0).text();
			bbkDetail.part_rating = doc.getElementsByTag(Consts_Registry.PART_RATING).get(0).text();
			bbkDetail.sequence = doc.getElementsByTag(Consts_Registry.SEQUENCE).get(0).text();
			bbkDetail.samples = doc.getElementsByTag(Consts_Registry.SAMPLES).get(0).text();
			bbkDetail.references = doc.getElementsByTag(Consts_Registry.REFERENCES).get(0).text();
			bbkDetail.groups = doc.getElementsByTag(Consts_Registry.GROUPS).get(0).text();
			// filling categories
			elements = doc.getElementsByTag(Consts_Registry.Category.CATEGORY);
			for (Element element : elements)
			{	Category category = new Category();
				category.category = element.text();
				bbkDetail.categories.add(category);
			}
			
			// filling deep_subparts
			elements = doc.getElementsByTag(Consts_Registry.DeepSub.DEEP_SUBPARTS)
					.get(0).children();	// deepSub & specSub both use "subpart" as child
			for (Element element : elements)
			{	DeepSubpart subpart = new DeepSubpart();
				subpart.ID = element.getElementsByTag(Consts_Registry.DeepSub.ID).text();
				subpart.name = element.getElementsByTag(Consts_Registry.DeepSub.NAME).text();
				subpart.shortDesc = element.getElementsByTag(Consts_Registry.DeepSub.SHORT_DESC).text();
				subpart.type = element.getElementsByTag(Consts_Registry.DeepSub.TYPE).text();
				subpart.nickname = element.getElementsByTag(Consts_Registry.DeepSub.NICKNAME).text();
				bbkDetail.deepSubparts.add(subpart);
			}
			// filling spec_subparts
			elements = doc.getElementsByTag(Consts_Registry.SpecSub.SPECFIED_SUBPARTS)
					.get(0).children();
			for (Element element : elements)
			{	SpecifiedSubpart subpart = new SpecifiedSubpart();
				subpart.ID = element.getElementsByTag(Consts_Registry.SpecSub.ID).text();
				subpart.name = element.getElementsByTag(Consts_Registry.SpecSub.NAME).text();
				subpart.shortDesc = element.getElementsByTag(Consts_Registry.SpecSub.SHORT_DESC).text();
				subpart.type = element.getElementsByTag(Consts_Registry.SpecSub.TYPE).text();
				subpart.nickname = element.getElementsByTag(Consts_Registry.SpecSub.NICKNAME).text();
				bbkDetail.specifiedSubparts.add(subpart);
			}
			// filling spec_subscars
			elements = doc.getElementsByTag(Consts_Registry.SpecScar.SCAR);
			for (Element element : elements)
			{	SpecifiedSubscar scar = new SpecifiedSubscar();
				scar.ID = element.getElementsByTag(Consts_Registry.SpecScar.ID).text();
				scar.standard = element.getElementsByTag(Consts_Registry.SpecScar.STANDARD).text();
				scar.type = element.getElementsByTag(Consts_Registry.SpecScar.TYPE).text();
				scar.name = element.getElementsByTag(Consts_Registry.SpecScar.NAME).text();
				scar.nickname = element.getElementsByTag(Consts_Registry.SpecScar.NICK_NAME).text();
				scar.comments = element.getElementsByTag(Consts_Registry.SpecScar.COMMENTS).text();
				scar.sequence = element.getElementsByTag(Consts_Registry.SpecScar.SEQUENCE).text();
				bbkDetail.specifiedSubscars.add(scar);
			}
			// filling features
			elements = doc.getElementsByTag(Consts_Registry.Feature.FEATURE);
			for (Element element : elements)
			{	Feature feature = new Feature();
				feature.ID = element.getElementsByTag(Consts_Registry.Feature.ID).text();
				feature.title = element.getElementsByTag(Consts_Registry.Feature.TITLE).text();
				feature.type = element.getElementsByTag(Consts_Registry.Feature.TYPE).text();
				feature.direction = element.getElementsByTag(Consts_Registry.Feature.DIRECTION).text();
				feature.startPos = element.getElementsByTag(Consts_Registry.Feature.START_POS).text();
				feature.endPos = element.getElementsByTag(Consts_Registry.Feature.END_POS).text();
				bbkDetail.features.add(feature);
			}
			// filling parameters
			elements = doc.getElementsByTag(Consts_Registry.Parameter.PARAMENER);
			for (Element element : elements)
			{	Parameter para = new Parameter();
				para.name = element.getElementsByTag(Consts_Registry.Parameter.NAME).text();
				para.value = element.getElementsByTag(Consts_Registry.Parameter.VALUE).text();
				para.units = element.getElementsByTag(Consts_Registry.Parameter.UNITS).text();
				para.url = element.getElementsByTag(Consts_Registry.Parameter.URL).text();
				para.ID = element.getElementsByTag(Consts_Registry.Parameter.ID).text();
				para.m_date = element.getElementsByTag(Consts_Registry.Parameter.M_DATE).text();
				para.userID = element.getElementsByTag(Consts_Registry.Parameter.USER_ID).text();
				para.userName = element.getElementsByTag(Consts_Registry.Parameter.USER_NAME).text();
				bbkDetail.parameters.add(para);
			}
			// filling twins
			elements = doc.getElementsByTag(Consts_Registry.Twin.TWIN);
			for (Element element : elements)
			{	Twin twin = new Twin();
				twin.twin = element.text();
				bbkDetail.twins.add(twin);
			}
			allQueriedCheck(searcher);
		}
	}
	
	static class PartInfoFetcher extends Thread
	{	
		public static final String URL_PREFIX = "http://parts.igem.org/cgi/partsdb/part_info.cgi?part_name=";
		public static final String QUERY_KEY = "style";
		public static final String QUERY_VALUE = "width:200px;font-weight:bold ! important";
		
		public static final String DNA_STATUS = "DNA Status";
		public static final String GROUPE_FAVOURITE = "Group Favorite";
		public static final String DELETE_THIS_PART = "Delete This Part";
		
		public BbkDetail bbkDetail;
		public RegistrySearcher searcher;
		
		public PartInfoFetcher(BbkDetail bbkDetail, RegistrySearcher searcher)
		{	
			this.bbkDetail = bbkDetail;
			this.searcher = searcher;
		}
		
		public void run()
		{	
			Document doc = null;
			if (bbkDetail.name != null && !bbkDetail.name.equals("")) 
			{	String url = URL_PREFIX + bbkDetail.name;
				doc = getDoc(url);
			}
			if (doc == null)
			{	allQueriedCheck(searcher);	return;	}
			
			Elements elements = null;
			elements = doc.getElementsByAttributeValue(QUERY_KEY, QUERY_VALUE);
			for (Element element : elements)
			{	if (element.text().equals(DNA_STATUS))
					bbkDetail.DNA_status = element.parent().child(1).text();
				else if (element.text().equals(GROUPE_FAVOURITE))
					bbkDetail.groupFavorite = element.parent().child(1).text();
				else if (element.text().equals(DELETE_THIS_PART))
					bbkDetail.rating.delete_this_part = element.parent().child(1).text();
			}
			
			String USED_TIMES_KEY = "href";
			String USED_TIMES_VALUE = "http://parts.igem.org/partsdb/uses.cgi?part=" + bbkDetail.name;
			elements = doc.getElementsByAttributeValue(USED_TIMES_KEY, USED_TIMES_VALUE);
			String rawUsedTimes = elements.text();	// "" if elements.size() == 0
			int usedTimes = 0;
			try	{usedTimes = Integer.parseInt(rawUsedTimes.replace(" Uses", ""));}
			catch (NumberFormatException e) {}
			bbkDetail.rating.used_times = String.valueOf(usedTimes);
			allQueriedCheck(searcher);
		}
	}
	
	static class PartConfirmFetcher extends Thread
	{	
		public static final String URL_PREFIX = "http://parts.igem.org/partsdb/get_part.cgi?part=";
		public static final String URL_SUFFIX = "&show=1";
		public static final String QUERY_KEY = "style";
		public static final String QUERY_VALUE = "width:50px;color:#00aa00;;";
		
		public BbkDetail bbkDetail;
		public RegistrySearcher searcher;
		
		public PartConfirmFetcher(BbkDetail bbkDetail, RegistrySearcher searcher)
		{	
			this.bbkDetail = bbkDetail;
			this.searcher = searcher;
		}
		
		public void run()
		{	
			Document doc = null;
			if (bbkDetail.name != null && !bbkDetail.name.equals("")) 
			{	String url = URL_PREFIX + bbkDetail.name + URL_SUFFIX;
				doc = getDoc(url);
			}
			if (doc == null)
			{	allQueriedCheck(searcher);	return;	}
			
			int confirmedTimes = 
					doc.getElementsByAttributeValue(QUERY_KEY, QUERY_VALUE).size();
			bbkDetail.rating.tot_confirmed = String.valueOf(confirmedTimes);
			allQueriedCheck(searcher);
		}	
	}
	
	static class AvgStarTotCommentFetcher extends Thread
	{	
		public static final String URL_PREFIX = "http://parts.igem.org/Part:";
		public static final String URL_SUFFIX = ":Experience";
		public static final String QUERY_KEY = "style";
		public static final String QUERY_VALUE = "width:75px;height: 15px;border: solid 1px gray;color: green;font-size:300%; line-height:40%;";
		
		public BbkDetail bbkDetail;
		public RegistrySearcher searcher;
		
		public AvgStarTotCommentFetcher(BbkDetail bbkDetail, RegistrySearcher searcher)
		{	
			this.bbkDetail = bbkDetail;
			this.searcher = searcher;
		}
		
		public void run()
		{	
			Document doc = null;
			if (bbkDetail.name != null && !bbkDetail.name.equals("")) 
			{	String url = URL_PREFIX + bbkDetail.name + URL_SUFFIX;
				doc = getDoc(url);
			}
			if (doc == null)
			{	allQueriedCheck(searcher);	return;	}
			
			Elements comments = doc.getElementsByAttributeValue(QUERY_KEY, QUERY_VALUE);
			int commentNum = comments.size();
			int totalStarNum = 0;
			for (Element comment : comments)
				totalStarNum += comment.text().length();	// each star num
			double averageStarNum = 
					commentNum != 0 ? (1.0 * totalStarNum / commentNum) : 0;
			bbkDetail.rating.tot_commets = String.valueOf(commentNum);
			bbkDetail.rating.average_stars = String.valueOf(averageStarNum);
			allQueriedCheck(searcher);
		}
	}
	
	static class NCBIQuoteNumFetcher extends Thread
	{	
		public static final String URL_PREFIX = "http://www.ncbi.nlm.nih.gov/gquery/?term=";
		public static final String[] ID_QUERY = 
			{ "books", "mesh", "nlmcatalog", "pubmed", "pmc"};
		
		public BbkDetail bbkDetail;
		public RegistrySearcher searcher;
		
		public NCBIQuoteNumFetcher(BbkDetail bbkDetail, RegistrySearcher searcher)
		{	
			this.bbkDetail = bbkDetail;
			this.searcher = searcher;
		}
		
		public void run()
		{	
			Document doc = null;
			if (bbkDetail.name != null && !bbkDetail.name.equals("")) 
			{	String url = URL_PREFIX + bbkDetail.name;
				doc = getDoc(url);
			}
			if (doc == null)
			{	allQueriedCheck(searcher);	return;	}
			
			int totalQuoteNum = 0;
			for (String ID : ID_QUERY)
			{	String quoteNumStr = doc.getElementById(ID).parent().parent().child(1).text();
				totalQuoteNum += Integer.parseInt(quoteNumStr);
			}
			bbkDetail.rating.NCBI_quoteNum = String.valueOf(totalQuoteNum);
			allQueriedCheck(searcher);
		}
	}
	
}
