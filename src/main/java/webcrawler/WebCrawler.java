/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webcrawler;

import model.Website;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.safety.Whitelist;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 * Script to perform web-crawling using BFS algorithm
 * <p>
 * "http://(\\w+\\.)*(\\w+)"
 *
 * @author Ranjith
 */

//TODO include a start time and end time to indicate how long the application should run
//TODO refactor webcrawler to POJOs
public class WebCrawler {

	private Queue<Document> queue;
	private Set<Document> discoveredWebsiteDocument;
	private Set<String> discoveredLinks;
	private Website website;


	public WebCrawler() {
		this.queue = new LinkedList<>();
		this.discoveredWebsiteDocument = new HashSet<>();
		this.discoveredLinks = new HashSet<>();
		this.website = new Website();
	}

	private Elements getAnchorLinks(Document cleanedURL) {
		return cleanedURL.select("a[href]");
	}

	public Document getCleanDocument(String url) {
		Document unCleanDocument;
		Document cleanedDocument = null;
		try {
			unCleanDocument = Jsoup.connect(url).get();
			website.setWebsiteURL(unCleanDocument.location());
			String cleanString = Jsoup.clean(unCleanDocument.toString(), Whitelist.basic());
			cleanedDocument = Jsoup.parse(cleanString);

		} catch (IOException e) {
			e.printStackTrace();
		}
		return cleanedDocument;
	}

	public Website discoverWeb(String rootURL) {
		Document cleanDocument = getCleanDocument(rootURL);

		queue.add(cleanDocument);
		discoveredWebsiteDocument.add(cleanDocument);

		while (!queue.isEmpty()) {
			Document currentWebsite = queue.remove();
			Elements anchorLinksElements = getAnchorLinks(currentWebsite);
			for (Element element : anchorLinksElements) {
				discoveredLinks.add(element.attr("href"));
			}
		}
		// website.setAhrefLinksCount(discoveredLinks.size());
		return website;
	}


}
