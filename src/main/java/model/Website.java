package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;
import java.util.Set;

/**
 * websiteURL is the total URL link
 * linksList is the set of all the links present in a website/document(JSoup)
 * topKeyWords is the dictionary count of all the words in the document, might be based on some filter
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Website {
	private String websiteURL;
	private Set<Link> linksList;
	private Map<String, Integer> topKeyWords;
}
