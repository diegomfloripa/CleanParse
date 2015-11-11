package br.com.diegomelo.cleanparse.utils;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import br.com.diegomelo.cleanparse.utils.TypeUtils;

/**
 * @author Diego Melo
 * @since 07/11/2015
 */
public class TypeUtilsTest {

	@Test
	public void isObjectNumberTeste() {
		assertTrue(TypeUtils.isNumber(Byte.class));
		assertTrue(TypeUtils.isNumber(Short.class));
		assertTrue(TypeUtils.isNumber(Integer.class));
		assertTrue(TypeUtils.isNumber(Long.class));
		assertTrue(TypeUtils.isNumber(Float.class));
		assertTrue(TypeUtils.isNumber(Double.class));
		assertTrue(TypeUtils.isNumber(Number.class));
		assertTrue(TypeUtils.isNumber(byte.class));
		assertTrue(TypeUtils.isNumber(short.class));
		assertTrue(TypeUtils.isNumber(int.class));
		assertTrue(TypeUtils.isNumber(long.class));
		assertTrue(TypeUtils.isNumber(float.class));
		assertTrue(TypeUtils.isNumber(double.class));
		assertFalse(TypeUtils.isNumber(Character.class));
		assertFalse(TypeUtils.isNumber(Boolean.class));
		assertFalse(TypeUtils.isNumber(String.class));
		assertFalse(TypeUtils.isNumber(Object.class));
		assertFalse(TypeUtils.isNumber(char.class));
		assertFalse(TypeUtils.isNumber(boolean.class));
		assertFalse(TypeUtils.isNumber(null));
	}

	@Test
	public void isBooleanTest() {
		assertTrue(TypeUtils.isBoolean(Boolean.class));
		assertTrue(TypeUtils.isBoolean(boolean.class));
		assertFalse(TypeUtils.isBoolean(null));
	}

	@Test
	public void isArrayTest() {
		assertTrue(TypeUtils.isArray(byte[].class));
		assertTrue(TypeUtils.isArray(short[].class));
		assertTrue(TypeUtils.isArray(int[].class));
		assertTrue(TypeUtils.isArray(long[].class));
		assertTrue(TypeUtils.isArray(float[].class));
		assertTrue(TypeUtils.isArray(double[].class));
		assertTrue(TypeUtils.isArray(Object[].class));
		assertTrue(TypeUtils.isArray(String[].class));
		assertTrue(TypeUtils.isArray(Byte[].class));
		assertTrue(TypeUtils.isArray(Short[].class));
		assertTrue(TypeUtils.isArray(Integer[].class));
		assertTrue(TypeUtils.isArray(Long[].class));
		assertTrue(TypeUtils.isArray(Float[].class));
		assertTrue(TypeUtils.isArray(Double[].class));
		assertTrue(TypeUtils.isArray(Number[].class));
		assertFalse(TypeUtils.isArray(byte.class));
		assertFalse(TypeUtils.isArray(short.class));
		assertFalse(TypeUtils.isArray(int.class));
		assertFalse(TypeUtils.isArray(long.class));
		assertFalse(TypeUtils.isArray(float.class));
		assertFalse(TypeUtils.isArray(double.class));
		assertFalse(TypeUtils.isArray(Object.class));
		assertFalse(TypeUtils.isArray(String.class));
		assertFalse(TypeUtils.isArray(Byte.class));
		assertFalse(TypeUtils.isArray(Short.class));
		assertFalse(TypeUtils.isArray(Integer.class));
		assertFalse(TypeUtils.isArray(Long.class));
		assertFalse(TypeUtils.isArray(Float.class));
		assertFalse(TypeUtils.isArray(Double.class));
		assertFalse(TypeUtils.isArray(Number.class));
		assertFalse(TypeUtils.isArray(null));
	}
}
