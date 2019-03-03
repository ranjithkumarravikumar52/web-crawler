package webcrawler;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

public class WebCrawlerTest {

	private WebCrawler webCrawler;

	@Before
	public void setUp() throws Exception {
		webCrawler = new WebCrawler();
	}

	@Test
	public void getAnchorLinks() {
	}

	@Test
	public void getCleanDocument() {
	}

	@Test
	public void discoverWeb() {
	}

	@Test
	public void checkDocumentIsAHitAganistFailCondition() {
		String checkUrl = "https://www.bbc.com/";
		String query = "Java";
		int hitCount = 5;
		try {
			assertFalse(checkUrl+" contains "+query, webCrawler.checkDocumentHeadIsAHit(Jsoup.connect(checkUrl).get(), query));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	@Test
	public void checkDocumentIsAHitAganistTrueCondition() {
		String checkUrl = "https://www.indeed.com/jobs?q=java&l=United%20States&advn=1003648525147881&vjk=11e4801f66a6b1aa";
		String query = "Java";
		try {
			Document cleanedDocument = Jsoup.connect(checkUrl).get();
			assertTrue("head doesn't contain "+query, webCrawler.checkDocumentHeadIsAHit(cleanedDocument, query));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void checkDocumentIsNotNull(){
		String checkUrl = "https://www.indeed.com/jobs?q=java&l=United%20States&advn=1003648525147881&vjk=11e4801f66a6b1aa";
		try {
			Document cleanedDocument = Jsoup.connect(checkUrl).get();
			assertNotNull(cleanedDocument);
		} catch (IOException e) {
			System.out.println("failed to connect");
			e.printStackTrace();
		}
	}
}