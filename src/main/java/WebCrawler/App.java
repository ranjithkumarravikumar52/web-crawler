/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package WebCrawler;

/**
 *
 * @author Ranjith
 */
public class App {

    public static void main(String[] args) {
        WebCrawler webCrawler = new WebCrawler();
        //change the below link to discover other links
        String rootURL = "http://www.bbc.com";
        webCrawler.discoverWeb(rootURL);
    }

}
