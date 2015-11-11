package br.com.diegomelo.cleanparse.utils;

/**
 * @author Diego Melo
 * @since 07/11/2015
 */
public final class Wrapper {
	private static final String TYPE_BOOLEAN = "boolean";
	private static final String TYPE_CHAR = "char";
	private static final String TYPE_BYTE = "byte";
	private static final String TYPE_SHORT = "short";
	private static final String TYPE_INT = "int";
	private static final String TYPE_LONG = "long";
	private static final String TYPE_FLOAT = "float";
	private static final String TYPE_DOUBLE = "double";
	
	private Wrapper() {}
	
	/**
	 * @param the array
	 * @return array of object where all elements are of type wrapper. 
	 */
	public static Object[] toObject(Object array) {
		if(array == null) return (Object[]) array;
		String typeName = array.getClass().getComponentType().getName();
		switch (typeName) {
		case TYPE_BOOLEAN:
			return Wrapper.boxing((boolean[]) array);
		case TYPE_CHAR:
			return Wrapper.boxing((char[]) array);
		case TYPE_BYTE:
			return Wrapper.boxing((byte[]) array);
		case TYPE_SHORT:
			return Wrapper.boxing((short[]) array);
		case TYPE_INT:
			return Wrapper.boxing((int[]) array);
		case TYPE_LONG:
			return Wrapper.boxing((long[]) array);
		case TYPE_FLOAT:
			return Wrapper.boxing((float[]) array);
		case TYPE_DOUBLE:
			return Wrapper.boxing((double[]) array);
		default:
			return (Object[]) array;
		}
	}
	
	private static Object[] boxing(boolean[] array) {
		Object[] arrayWrapper = new Boolean[array.length];
		for(int i = 0; i < array.length; i++)
			arrayWrapper[i] = array[i];
		return arrayWrapper;
	}

	private static Object[] boxing(char[] array) {
		Object[] arrayWrapper = new Character[array.length];
		for(int i = 0; i < array.length; i++)
			arrayWrapper[i] = array[i];
		return arrayWrapper;
	}

	private static Object[] boxing(byte[] array) {
		Object[] arrayWrapper = new Number[array.length];
		for(int i = 0; i < array.length; i++)
			arrayWrapper[i] = array[i];
		return arrayWrapper;
	}

	private static Object[] boxing(short[] array) {
		Object[] arrayWrapper = new Number[array.length];
		for(int i = 0; i < array.length; i++)
			arrayWrapper[i] = array[i];
		return arrayWrapper;
	}

	private static Object[] boxing(int[] array) {
		Object[] arrayWrapper = new Number[array.length];
		for(int i = 0; i < array.length; i++)
			arrayWrapper[i] = array[i];
		return arrayWrapper;
	}
	
	private static Object[] boxing(long[] array) {
		Object[] arrayWrapper = new Number[array.length];
		for(int i = 0; i < array.length; i++)
			arrayWrapper[i] = array[i];
		return arrayWrapper;
	}
	
	private static Object[] boxing(float[] array) {
		Object[] arrayWrapper = new Number[array.length];
		for(int i = 0; i < array.length; i++)
			arrayWrapper[i] = array[i];
		return arrayWrapper;
	}

	private static Object[] boxing(double[] array) {
		Object[] arrayWrapper = new Number[array.length];
		for(int i = 0; i < array.length; i++)
			arrayWrapper[i] = array[i];
		return arrayWrapper;
	}
}
