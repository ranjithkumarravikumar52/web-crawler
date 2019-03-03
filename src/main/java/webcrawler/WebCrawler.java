/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webcrawler;

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
 * @author Ranjith
 */
//TODO can use Set interface for unique website list
//TODO include a start time and end time to indicate how long the application should run
//TODO refactor webcrawler to POJOs
public class WebCrawler {

    private Queue<String> queue;
    private List<String> discoveredWebSitesList;


    public WebCrawler() {
        this.queue = new LinkedList<String>();
        this.discoveredWebSitesList = new ArrayList<String>();
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
            String rawHTML = getRawHTML(currentWebsiteURL);

            //regular expression to get http links
            String regExpression = "http://(\\w+\\.)*(\\w+)";

            //compile the regular expression
            Pattern pattern = Pattern.compile(regExpression);
            Matcher matcher = pattern.matcher(rawHTML);

            //find the HTTP links
            while (matcher.find()) {
                String actualURL = matcher.group();

                if (!discoveredWebSitesList.contains(actualURL)) {
                    //if website not visited earlier then add it to the discoveredList
                    discoveredWebSitesList.add(actualURL);
                    System.out.println("Website found " + websiteCount++ + " -> " + actualURL);
                    //to find neighbors later
                    queue.add(actualURL);
                }
            }

        }

    }

    private String getRawHTML(String currentWebsiteURL) {

        String rawHTML = "";

        try {

            //get the code object of the given URL
            URL url = new URL(currentWebsiteURL);

            //open the stream for the URL object
            InputStreamReader inputStreamReader = new InputStreamReader(url.openStream());

            //buffer the input the stream (efficient than using only a readLine() method)
            BufferedReader br = new BufferedReader(inputStreamReader);

            //reading line by line to get the entire raw HTML from the URL object
            String inputLine = "";
            while ((inputLine = br.readLine()) != null) { //read till the end of the line
                rawHTML += inputLine;
            }

            //close the stream
            br.close();

        } catch (MalformedURLException e) {
            // e.printStackTrace();
        } catch (IOException e) {
            // e.printStackTrace();
        }
        return rawHTML;
    }

}
