package br.com.diegomelo.cleanparse.utils;

/**
 * @author Diego Melo
 * @since 06/11/2015
 */
public final class StringUtils {
	
	private StringUtils() {}
	
	/**
	 * @param String
	 * @return value with a first capital letter
	 */
	public static String capitalizeFirst(String value) {
		if(value == null || value.isEmpty()) return value;
		String charLowerCase = String.valueOf(value.charAt(0));
		String charUpperrCase = charLowerCase.toUpperCase();
		return value.replaceFirst(charLowerCase, charUpperrCase);
	}
}
