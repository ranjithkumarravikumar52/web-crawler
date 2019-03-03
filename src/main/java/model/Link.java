package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ahrefAttribute is the a href link
 * @ahrefTagValue is the text enclosed in the tag
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Link {
	private String ahrefAttribute;
}
