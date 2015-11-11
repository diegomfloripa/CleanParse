package br.com.diegomelo.cleanparse.core;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;

import br.com.diegomelo.cleanparse.annotation.ParseTransient;
import br.com.diegomelo.cleanparse.exception.ParserException;
import br.com.diegomelo.cleanparse.utils.TypeUtils;
import br.com.diegomelo.cleanparse.utils.Wrapper;

public class ParserJson extends Parser {
	private static final String TOKEN_OBJECT_START = "{";
	private static final String TOKEN_OBJECT_END = "}";
	private static final String TOKEN_ARRAY_START = "[";
	private static final String TOKEN_ARRAY_END = "]";
	private static final String TOKEN_QUOTES = "\"";
	private static final String TOKEN_COLON = ":";
	private static final String TOKEN_COMMA = ",";

	/**
	 * Serializes the specified object into its equivalent Json.
	 * 
	 * @param Object POJO
	 * @param OutputStream 
	 * @return OutputStream
	 * @throws IOException
	 * @throws ParserException 
	 */
	@Override
	public OutputStream parse(Object obj, OutputStream os) throws IOException, ParserException {
		validate(obj);
		String json = parse(obj);
		write(json, os);
		return os;
	}

	/**
	 * Serializes the specified objects into a list equivalent Json.
	 * 
	 * @param List<Object> List of POJO
	 * @param OutputStream
	 * @return OutputStream
	 * @throws IOException 
	 * @throws ParserException 
	 */
	@Override
	public OutputStream parse(List<?> listObject, OutputStream os) throws IOException, ParserException {
		if( isNullOrEmpty(listObject) ) {
			write(emptyJsonArray(), os);
			return os;
		}
		validate(listObject.get(0));
		StringBuilder sb = new StringBuilder(TOKEN_ARRAY_START);
		for (Object object : listObject) {
			sb.append(parse(object));
			sb.append(TOKEN_COMMA);
		}
		normalizeLastToken(sb, TOKEN_ARRAY_END);
		write(sb.toString(), os);
		return os;
	}

	private String parse(Object obj){
		try {
			return toJson(obj);
		} catch (Exception e) {
			log.severe("Error to parse json >> " + e);
		}
		return emptyJsonObject();
	}

	private String toJson(Object obj) throws ReflectiveOperationException {
		if(isNull(obj)) return emptyJsonObject();
		StringBuilder sb = new StringBuilder(TOKEN_OBJECT_START);
		Field[] fields = obj.getClass().getDeclaredFields();
		for (Field field : fields) {
			if( field.isAnnotationPresent(ParseTransient.class) ) 
				continue;
			sb.append(getKey(field));
			sb.append(getValue(field, obj));
		}
		normalizeLastToken(sb, TOKEN_OBJECT_END);
		return sb.toString();
	}

	public String getKey(Field field) {
		return addQuotes(field.getName()) + TOKEN_COLON;
	}

	private String getValue(Field field, Object obj) throws ReflectiveOperationException {
		Method methodGet = createMethodGet(obj.getClass(), field.getName());
		Object invokedObj = methodGet.invoke(obj);
		if( TypeUtils.isList(field.getType()) ) {
			return getValuesFromListElement(field, (List<?>) invokedObj);
		} else {
			return getValueFromElement(field, invokedObj);
		}
	}

	private String getValuesFromListElement(Field field, List<?> listObject) throws ReflectiveOperationException {
		if( isNullOrEmpty(listObject) ) return emptyJsonArray() + TOKEN_COMMA;
		StringBuilder sb = new StringBuilder(TOKEN_ARRAY_START);
		for (Object object : listObject) {
			if( isComplexType(object) ) {
				sb.append(getJsonFromComplexType(object));
			} else {
				sb.append(getValueFromElement(field, object));
			}
		}
		normalizeLastToken(sb, TOKEN_ARRAY_END);
		return sb.toString() + TOKEN_COMMA;
	}

	private String getValueFromElement(Field field, Object object) throws ReflectiveOperationException  {
		if( isComplexType(object) ) return getJsonFromComplexType(object);
		if(TypeUtils.isArray(field.getType()) ) {
			object = getJsonArray(object, field);
		} else if ( isText(field) ) {
			object = addQuotes(object);
		} 
		return object + TOKEN_COMMA;
	}

	private String getJsonFromComplexType(Object object) throws ReflectiveOperationException {
		return toJson(object) + TOKEN_COMMA;
	}

	private String getJsonArray(Object obj, Field field) throws ReflectiveOperationException {
		if(isNull(obj)) return emptyJsonArray();
		Object[] objectArray = Wrapper.toObject(obj);
		StringBuilder sb = new StringBuilder(TOKEN_ARRAY_START);
		for(Object object : objectArray) {
			if( isText(field) ) {
				object = addQuotes(object);
			}
			sb.append(object + TOKEN_COMMA);
		}
		normalizeLastToken(sb, TOKEN_ARRAY_END);
		return sb.toString();
	}

	private void normalizeLastToken(StringBuilder sb, String token) {
		if(sb.toString().endsWith(TOKEN_COMMA))
			sb.deleteCharAt( sb.length()-1 );
		sb.append(token);
	}

	private Object addQuotes(Object value) {
		if( isNull(value) ) return value;
		return TOKEN_QUOTES + value + TOKEN_QUOTES;
	}

	private String emptyJsonObject() {
		return TOKEN_OBJECT_START + TOKEN_OBJECT_END;
	}

	private String emptyJsonArray() {
		return TOKEN_ARRAY_START + TOKEN_ARRAY_END;
	}

	private boolean isText(Field field) {
		return !isNotText(field);
	}

	private boolean isNotText(Field field) {
		return TypeUtils.isNumber(field.getType()) || TypeUtils.isBoolean(field.getType());
	}
}
