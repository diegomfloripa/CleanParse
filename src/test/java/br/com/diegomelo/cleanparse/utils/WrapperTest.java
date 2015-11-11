package br.com.diegomelo.cleanparse.utils;

import static org.junit.Assert.assertTrue;

import java.util.Arrays;

import org.junit.Test;

import br.com.diegomelo.cleanparse.utils.Wrapper;


/**
 * @author Diego Melo
 * @since 07/11/2015 13:25:49
 */
public class WrapperTest {
	
	@Test
	public void toObjectTest() {
		Object[] expectedBoolean = new Boolean[]{false,true};
		Object[] actualBoolean = Wrapper.toObject(new boolean[]{false, true});
		assertTrue(Arrays.equals(expectedBoolean, actualBoolean));
		
		Object[] expectedChar = new Character[]{'a', 'b'};
		Object[] actualChar = Wrapper.toObject(new char[]{'a', 'b'});
		assertTrue(Arrays.equals(expectedChar, actualChar));
		
		Object[] expectedByte = new Byte[]{0,1};
		Object[] actualByte = Wrapper.toObject(new byte[]{0, 1});
		assertTrue(Arrays.equals(expectedByte, actualByte));
		
		Object[] expectedShort = new Short[]{0,1};
		Object[] actualShort = Wrapper.toObject(new short[]{0,1});
		assertTrue(Arrays.equals(expectedShort, actualShort));
		
		Object[] expectedInteger = new Integer[]{0,1};
		Object[] actualInteger = Wrapper.toObject(new int[]{0,1});
		assertTrue(Arrays.equals(expectedInteger, actualInteger));
		
		Object[] expectedLong = new Long[]{0L,1L};
		Object[] actualLong = Wrapper.toObject(new long[]{0,1});
		assertTrue(Arrays.equals(expectedLong, actualLong));
		
		Object[] expectedFloat = new Float[]{1.2F,2.3F};
		Object[] actualFloat = Wrapper.toObject(new float[]{1.2F,2.3F});
		assertTrue(Arrays.equals(expectedFloat, actualFloat));
		
		Object[] expectedDouble = new Double[]{1.2,2.3};
		Object[] actualDouble = Wrapper.toObject(new double[]{1.2,2.3});
		assertTrue(Arrays.equals(expectedDouble, actualDouble));
	}
	
	@Test
	public void toObjectWithNullValueTest() {
		Object[] expected = null;
		Object[] actual = Wrapper.toObject(null);
		assertTrue(Arrays.equals(expected, actual));
	}
}
