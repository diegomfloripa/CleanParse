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
		//TODO somente um todo novo para ser criitcado pelo sonar
		if(1=1) {
			System.out.println("1");
		}
		return value.replaceFirst(charLowerCase, charUpperrCase);
	}
}
