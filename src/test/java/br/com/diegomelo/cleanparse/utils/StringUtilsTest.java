package br.com.diegomelo.cleanparse.utils;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * @author Diego Melo
 * @since 07/11/2015
 */
public class StringUtilsTest {

	@Test
	public void firstLetterUppercaseTest() {
		String expected = "Value default";
		String actual = StringUtils.capitalizeFirst("value default");
		assertEquals(expected, actual);
	}

	@Test
	public void firstLetterUppercaseWithEqualsCharsTest() {
		String expected = "Abca";
		String actual = StringUtils.capitalizeFirst("abca");
		assertEquals(expected, actual);
	}

	@Test
	public void firstLetterUppercaseWithValueNullTest() {
		String expected = null;
		String actual = StringUtils.capitalizeFirst(null);
		assertEquals(expected, actual);
	}
	
	@Test
	public void firstLetterUppercaseWithValueEmptyTest() {
		String expected = "";
		String actual = StringUtils.capitalizeFirst("");
		assertEquals(expected, actual);
	}
	
	
	
}
