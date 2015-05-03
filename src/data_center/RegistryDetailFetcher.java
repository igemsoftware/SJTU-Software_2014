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
			try {doc = Jsoup.connect(url).get();} 
			catch (IOException e) {continue;}
		return doc;
	}
	
	static class XMLFetcher extends Thread
	{	
		public static final String URL_PREFIX = "http://parts.igem.org/cgi/xml/part.cgi?part=";
		
		public BbkDetail bbkDetail;
		
		public XMLFetcher(BbkDetail bbkDetail)
		{	
			this.bbkDetail = bbkDetail;
		}
		
		public void run()
		{	
			if (bbkDetail.name == null || bbkDetail.name.equals(""))
				return;
			String url = URL_PREFIX + bbkDetail.name;
			Document doc = getDoc(url);
			if (doc == null)
				return;
			
			Elements elements = null;
			// filling main
			bbkDetail.name = doc.getElementsByTag(Consts_Registry.NAME).text();
			bbkDetail.type = doc.getElementsByTag(Consts_Registry.TYPE).text();
			bbkDetail.author = doc.getElementsByTag(Consts_Registry.AUTHOR).text();
			bbkDetail.enterDate = doc.getElementsByTag(Consts_Registry.ENTER_DATE).text();
			bbkDetail.shortDesc = doc.getElementsByTag(Consts_Registry.SHORT_DESC).text();
			bbkDetail.url = doc.getElementsByTag(Consts_Registry.URL).text();
			bbkDetail.ID = doc.getElementsByTag(Consts_Registry.ID).text();
			bbkDetail.shortName = doc.getElementsByTag(Consts_Registry.SHORT_NAME).text();
			bbkDetail.releaseStatus = doc.getElementsByTag(Consts_Registry.RELEASE_STATUS).text();
			bbkDetail.sampleStatus = doc.getElementsByTag(Consts_Registry.SAMPLE_STATUS).text();
			bbkDetail.results = doc.getElementsByTag(Consts_Registry.RESULTS).text();
			bbkDetail.nickname = doc.getElementsByTag(Consts_Registry.NICKNAME).text();
			bbkDetail.part_rating = doc.getElementsByTag(Consts_Registry.PART_RATING).text();
			bbkDetail.sequence = doc.getElementsByTag(Consts_Registry.SEQUENCE).text();
			bbkDetail.samples = doc.getElementsByTag(Consts_Registry.SAMPLES).text();
			bbkDetail.references = doc.getElementsByTag(Consts_Registry.REFERENCES).text();
			bbkDetail.groups = doc.getElementsByTag(Consts_Registry.GROUPS).text();
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
		
		public PartInfoFetcher(BbkDetail bbkDetail)
		{	
			this.bbkDetail = bbkDetail;
		}
		
		public void run()
		{	
			if (bbkDetail.name == null || bbkDetail.name.equals(""))
				return;
			String url = URL_PREFIX + bbkDetail.name;
			Document doc = getDoc(url);
			if (doc == null)
				return;
			
			Elements elements = null;
			elements = doc.getElementsByAttributeValue(QUERY_KEY, QUERY_VALUE);
			for (Element element : elements)
			{	if (element.text().equals(DNA_STATUS))
					bbkDetail.DNA_status = element.parent().children().get(1).text();
				else if (element.text().equals(GROUPE_FAVOURITE))
					bbkDetail.groupFavorite = element.parent().children().get(1).text();
				else if (element.text().equals(DELETE_THIS_PART))
					bbkDetail.rating.delete_this_part = element.parent().children().get(1).text();
			}
			String USED_TIMES_KEY = "href";
			String USED_TIMES_VALUE = "http://parts.igem.org/partsdb/uses.cgi?part=" + bbkDetail.name;
			elements = doc.getElementsByAttributeValue(USED_TIMES_KEY, USED_TIMES_VALUE);
			String rawUsedTimes = elements.text();	// "" if elements.size() == 0
			if (rawUsedTimes.contains(" Uses"))
				bbkDetail.rating.used_times = rawUsedTimes.replace(" Uses", "");
			else
				bbkDetail.rating.used_times = "0";
		}
	}
	
}
