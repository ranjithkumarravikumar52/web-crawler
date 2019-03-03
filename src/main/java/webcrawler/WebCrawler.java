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

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Script to perform web-crawling using BFS algorithm
 *
 *  "http://(\\w+\\.)*(\\w+)"
 * @author Ranjith
 */

//TODO include a start time and end time to indicate how long the application should run
//TODO refactor webcrawler to POJOs
public class WebCrawler {

	private Queue<String> queue;
	private List<String> discoveredWebSitesList;


	public WebCrawler() {
		this.queue = new LinkedList<>();
		this.discoveredWebSitesList = new ArrayList<>();
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

		//add root to the queue
		queue.add(rootURL);
		//since root is known, add to discoveredWebsites
		discoveredWebSitesList.add(rootURL);

		//BFS implementation
		int websiteCount = 0;
		while (!queue.isEmpty()) {
			String currentWebsiteURL = queue.remove();

			//get all the rawHTML content embedded in the currentWebsite
			Document cleanDocument = getCleanDocument(currentWebsiteURL);
			String rawHTML = cleanDocument.toString();

			Elements anchorLinks = getAnchorLinks(rawHTML);
			for(Element element: anchorLinks){
				if (!discoveredWebSitesList.contains(element.toString())){ //TODO can use Set interface for unique website list
					queue.add(element.toString());
					System.out.println("Website found " + websiteCount++ + " -> " + element.toString());
				}

			}

		}

	}


}
