package jsoup;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.safety.Whitelist;
import org.jsoup.select.Elements;

import java.io.IOException;

public class JSoupTutorial {
	static final String url = "https://www.bbc.com/";

	public static void main(String[] args) {
		/**
		 * Get all the data from a site
		 */
		Document document = null;
		Document parse = null;
		try {
			document = Jsoup.connect("https://www.bbc.com/").get();
			String s = document.toString();
			String clean = Jsoup.clean(s, Whitelist.basic());
			parse = Jsoup.parse(clean);

		} catch (IOException e) {
			e.printStackTrace();
		}
		// System.out.println("document = " + document); //print everything
		Elements anchorLinks = parse.select("a[href]");
		for (Element element : anchorLinks) {
			System.out.println(element);
		}

	}

}
