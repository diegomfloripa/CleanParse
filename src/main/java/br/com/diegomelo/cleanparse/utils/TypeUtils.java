package br.com.diegomelo.cleanparse.utils;

import java.util.List;

/**
 * @author Diego Melo
 * @since 07/11/2015
 */
public final class TypeUtils {
	private static final String TYPE_DOUBLE = "double";
	private static final String TYPE_FLOAT = "float";
	private static final String TYPE_LONG = "long";
	private static final String TYPE_INT = "int";
	private static final String TYPE_SHORT = "short";
	private static final String TYPE_BYTE = "byte";
	private static final String TYPE_BOOLEAN = "boolean";
	
	private TypeUtils() {}

	/**
	 * @param cls a Class object that identifies the type of a Field object.
	 * @return true if the type represents a subclass of Number class or is a primitive type of byte, short, int, long, float or double; false otherwise. 
	 */
	public static boolean isNumber(Class<?> cls) {
		cls = getComponentType(cls);
		return cls != null ? Number.class.isAssignableFrom(cls) || isPrimitiveNumber(cls) : false;
	}

	/**
	 * @param cls a Class object that identifies the type of a Field object.
	 * @return true if the type represents a subclass of Boolean class or is a primitive type of boolean; false otherwise.
	 */
	public static boolean isBoolean(Class<?> cls) {
		cls = getComponentType(cls);
		return cls != null ? Boolean.class.isAssignableFrom(cls) || TYPE_BOOLEAN.equals(cls.getName()) : false;
	}
	
	/**
	 * @param cls a Class object that identifies the type of a Field object.
	 * @return true if this object represents an array class; false otherwise. 
	 */
	public static boolean isArray(Class<?> cls) {
		return cls != null ? cls.isArray() : false;
	}

	/**
	 * @param cls a Class object that identifies the type of a Field object.
	 * @return true if the type is a class that implements the interface List; false otherwise.
	 */
	public static boolean isList(Class<?> cls) {
		return cls != null ? List.class.isAssignableFrom(cls) : false;
	}
	
	private static boolean isPrimitiveNumber(Class<?> cls) {
		switch (cls.getName()) {
		case TYPE_BYTE:
			return true;
		case TYPE_SHORT:
			return true;
		case TYPE_INT:
			return true;
		case TYPE_LONG:
			return true;
		case TYPE_FLOAT:
			return true;
		case TYPE_DOUBLE:
			return true;
		default:
			return false;
		}
	}
	
	private static Class<?> getComponentType(Class<?> cls) {
		if( cls != null && cls.getComponentType() != null ) {
			return cls.getComponentType();
		}
		return cls;
	}
}