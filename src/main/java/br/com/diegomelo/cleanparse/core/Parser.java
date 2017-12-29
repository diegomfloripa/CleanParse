package br.com.diegomelo.cleanparse.core;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import br.com.diegomelo.cleanparse.annotation.ParseTransient;
import br.com.diegomelo.cleanparse.exception.ParserException;
import br.com.diegomelo.cleanparse.utils.StringUtils;

/**
 * Base class for the creation of other serializers
 * 
 * @author Diego Melo
 * @since 08/11/2015
 */
public abstract class Parser {
	private static final String JAVA_LANG = "java.lang";
	private static final String GET = "get";
	protected static Logger log = Logger.getLogger(ParserJson.class.getName());

	public abstract OutputStream parse(Object obj, OutputStream os) throws IOException, ParserException;
	public abstract OutputStream parse(List<?> listObject, OutputStream os) throws IOException, ParserException;

	/**
	 * Checks whether the object has methods to get all its attributes
	 * 
	 * @param the object
	 * @throws ParserException
	 */
	protected void validate(Object obj) throws ParserException {
		if(isNull(obj)) return;
		boolean invalid = false;
		List<String> fieldNames = new ArrayList<>();
		Class<?> cls = obj.getClass();
		Field[] fields = cls.getDeclaredFields();

//test sonar
Field test;
		for (Field field : fields) {
			if( field.isAnnotationPresent(ParseTransient.class) ) continue;
			try {
				createMethodGet(cls, field.getName());
			} catch (NoSuchMethodException e) {
				fieldNames.add(field.getName());
				invalid = true;
			}
		}
		if(invalid) {
			log.severe("invalid pojo: Attributes without get methods >> " + fieldNames.toString());
			throw new ParserException(fieldNames.toString());
		}
	}

	/**
	 * @param cls
	 * @param fieldName
	 * @return method to invoke and obtain values ​​of an instance of the class.
	 * @throws NoSuchMethodException
	 */
	protected Method createMethodGet(Class<?> cls, String fieldName) throws NoSuchMethodException {
		return cls.getMethod(GET + StringUtils.capitalizeFirst(fieldName));
	}

	/**
	 * Objetivo do Metodo:
	 *
	 * @param object
	 * @return true if the object is customized and not part of the default classes of the java; false otherwise;
	 */
	protected boolean isComplexType(Object object) {
		return object != null 
				&& object.getClass() != null 
				&& object.getClass().getPackage() != null 
				&& ! JAVA_LANG.equals(object.getClass().getPackage().getName());
	}

	/**
	 * Objetivo do Metodo:
	 *
	 * @param listObject
	 * @return true if the list is null or is empty; false otherwise.
	 */
	protected boolean isNullOrEmpty(List<?> listObject) {
		return isNull(listObject) || listObject.isEmpty();
	}

	/**
	 * Objetivo do Metodo:
	 *
	 * @param obj
	 * @return true if the object is null; false otherwise.
	 */
	protected boolean isNull(Object obj) {
		return obj == null;
	}

	/**
	 * Write the value of String in the OutputStream
	 *
	 * @param String with the value
	 * @param OutputStream 
	 * @throws IOException
	 * @author Diego Melo
	 */
	protected void write(String str, OutputStream os) throws IOException {
		if( isNull(os)) {
			log.severe("IOException ==> OutputStream is null");
			throw new IOException("OutputStream is null"); 
		}
		os.write(str.getBytes());
		log.info("json >> " + str);
	}
}
