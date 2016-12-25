package kr.pe.courage.common.net.rss;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import kr.pe.courage.common.utils.Util;

import org.apache.log4j.Logger;

import com.sun.syndication.feed.synd.SyndContent;
import com.sun.syndication.feed.synd.SyndContentImpl;
import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndEntryImpl;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.feed.synd.SyndFeedImpl;
import com.sun.syndication.io.SyndFeedOutput;

public class RssHandler {
	private static final Logger logger = Logger.getLogger(RssHandler.class);
	
	public static String writeRssFeed(RssHeaderParameter rssHeaderParameter, List<RssParameter> rssParamList) {
		String rssStr = "";
		SyndFeed feed = null;
		SyndEntry entry = null;
		SyndContent description = null;
		List<SyndEntry> entries = null;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");
		
		try {
			entries = new ArrayList<SyndEntry>();
			
			feed = new SyndFeedImpl();
			feed.setFeedType(rssHeaderParameter.getFeedType());
			feed.setTitle(rssHeaderParameter.getTopTitle());
			feed.setLink(rssHeaderParameter.getTopLink());
			feed.setDescription(rssHeaderParameter.getTopDesc());
			feed.setLanguage(rssHeaderParameter.getLang());
			feed.setEncoding(rssHeaderParameter.getEncoding());
			
			Iterator rssIter = rssParamList.iterator();
			
			while (rssIter.hasNext()) {
				RssParameter rssParameter = (RssParameter) rssIter.next();
				entry = new SyndEntryImpl();
				description = new SyndContentImpl();
				
				rssParameter.setEntryTitle(Util.nvl(rssParameter.getEntryTitle()).replace("<", "["));
				rssParameter.setEntryTitle(Util.nvl(rssParameter.getEntryTitle()).replace(">", "]"));
				entry.setTitle(Util.nvl(rssParameter.getEntryTitle()));
				entry.setLink(Util.nvl(rssParameter.getEntryLink()));
				
				if(!Util.nvl(rssParameter.getEntryPubDate()).equals("")) {
					entry.setPublishedDate(sdf.parse(rssParameter.getEntryPubDate()));
				}
				
				entry.setAuthor(Util.nvl(rssParameter.getEntryAuthor()));
				
				description.setType("text/plain");
				String value = entryDescValue(Util.nvl(rssParameter.getDescValue()));
				value = value.replace("\n", "<br />");
				description.setValue(value);
				
				entry.setDescription(description);
				
				entries.add(entry);
			}
			
			feed.setEntries(entries);
			SyndFeedOutput output = new SyndFeedOutput();
			
			rssStr = output.outputString(feed);
			rssStr = rssStr.replace("&lt;", "<");
			rssStr = rssStr.replace("&gt;", ">");
		} catch (Exception ex) {
			logger.error(ex);
		}
		
		return rssStr;
	}
	
	private static String entryDescValue(String value) {
		return "<![CDATA[ <p>" + value + "</p> ]]>";
	}
}
