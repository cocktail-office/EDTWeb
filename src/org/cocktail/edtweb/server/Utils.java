package org.cocktail.edtweb.server;
public class Utils {

	/**
	 * @param s
	 *            String to cut
	 * @param maxLength
	 *            Maximum length for result string
	 * @return A clean string of maxLength maximum length, and certified (if bug-free) not crunching html tags
	 */
	public static String cleanCut(String s, int maxLength) {
		if (s == null || maxLength <= 0 || maxLength >= s.length()) {
			return s;
		}
		s = s.substring(0, maxLength);
		if (s.lastIndexOf("<") == -1 || s.lastIndexOf(">") > s.lastIndexOf("<")) {
			return s;
		}
		return s.substring(0, s.lastIndexOf("<"));
	}

}
