/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webcrawler;

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


	public WebCrawler() {
		this.queue = new LinkedList<>();
		this.discoveredWebsiteDocument = new HashSet<>();
	}

	/**
	 * Get all the data from a site
	 */
	private Elements getAnchorLinks(String url) {
		Document parse = getCleanDocument(url);
		// System.out.println("document = " + document); //print everything
		Elements anchorLinks = parse.select("a[href]");
		return anchorLinks;
	}

	private Document getCleanDocument(String url) {
		Document document = null;
		Document parse = null;
		try {
			url = "https://www.bbc.com/";
			document = Jsoup.connect(url).get();
			String s = document.toString();
			String clean = Jsoup.clean(s, Whitelist.basic());
			parse = Jsoup.parse(clean);

		} catch (IOException e) {
			e.printStackTrace();
		}
		return parse;
	}

	public void discoverWeb(String rootURL) {
		Document cleanDocument = getCleanDocument(rootURL);

		queue.add(cleanDocument);
		discoveredWebsiteDocument.add(cleanDocument);

		int websiteCount = 0;
		while (!queue.isEmpty()) {
			Document currentWebsiteDocument = queue.remove();
			Elements anchorLinks = getAnchorLinks(currentWebsiteDocument.toString());
			for(Element element: anchorLinks){
				System.out.println(element.attr("href"));
			}
		}

	}


}
