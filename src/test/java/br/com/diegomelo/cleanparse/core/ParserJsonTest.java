package br.com.diegomelo.cleanparse.core;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import br.com.diegomelo.cleanparse.core.ParserFactory;
import br.com.diegomelo.cleanparse.exception.ParserException;
import br.com.diegomelo.cleanparse.model.AttributeListPojo;
import br.com.diegomelo.cleanparse.model.AttributeObjectPojo;
import br.com.diegomelo.cleanparse.model.CustomAttributeListPojo;
import br.com.diegomelo.cleanparse.model.Pojo;
import br.com.diegomelo.cleanparse.model.PojoWithoutMehotdGet;
import br.com.diegomelo.cleanparse.model.PrimitivePojo;
import br.com.diegomelo.cleanparse.model.SimplePojo;
import br.com.diegomelo.cleanparse.model.SimplePojoWithAnnotationParseTransient;


public class ParserJsonTest {
	private OutputStream outputStream;

	@Before
	public void getOutputStream() {
		this.outputStream = new ByteArrayOutputStream();
	}

	@After
	public void closeOutputStream() throws IOException, ParserException {
		if(outputStream != null) {
			outputStream.flush();
			outputStream.close();
		}
	}

	@Test(expected = ParserException.class)
	public void parseJsonWithPojoInvalidTest() throws IOException, ParserException {
		String expected = "{}";
		PojoWithoutMehotdGet invalidPojo = new PojoWithoutMehotdGet();
		new ParserFactory().parserJson().parse(invalidPojo, outputStream);
		assertEquals(expected, outputStream.toString());
	}
	
	@Test(expected = IOException.class)
	public void parseJsonWithOutputStreamNullTest() throws IOException, ParserException {
		SimplePojo simplePojo = new SimplePojo();
		new ParserFactory().parserJson().parse(simplePojo, null);
	}

	//Null
	@Test
	public void parserJsonWithPojoNullTest() throws IOException, ParserException {
		String expected = "{}";
		Pojo pojo = null;
		new ParserFactory().parserJson().parse(pojo, outputStream);
		assertEquals(expected, outputStream.toString());
	}

	//Simple Pojo
	@Test
	public void parserJsonWithSimplePojoTest() throws IOException, ParserException {
		SimplePojo simplePojo = new SimplePojo();
		simplePojo.setSimpleValue("test");
		String expected = "{\"simpleValue\":\"test\"}";
		new ParserFactory().parserJson().parse(simplePojo, outputStream);
		assertEquals(expected, outputStream.toString());
	}

	@Test
	public void parserJsonWithSimplePojoWithAnnotationParseTransientTest() throws IOException, ParserException {
		SimplePojoWithAnnotationParseTransient simplePojo = new SimplePojoWithAnnotationParseTransient();
		simplePojo.setSimpleValue("test");
		simplePojo.setNumber(1000);
		String expected = "{\"number\":1000}";
		new ParserFactory().parserJson().parse(simplePojo, outputStream);
		assertEquals(expected, outputStream.toString());
	}

	@Test 
	public void paserJsonPojoPrimitiveTest() throws IOException, ParserException {
		PrimitivePojo primitivePojo = new PrimitivePojo();
		String expected = "{\"primBoolean\":false,\"primChar\":\"\u0000\",\"primByte\":0,\"primShort\":0,\"primInt\":0,\"primLong\":0,\"primFloat\":0.0,\"primDouble\":0.0}";
		new ParserFactory().parserJson().parse(primitivePojo, outputStream);
		assertEquals(expected, outputStream.toString());
	}

	@Test
	public void parserJsonWithSimplePojoEmptyTest() throws IOException, ParserException {
		SimplePojo simplePojo = new SimplePojo();
		String expected = "{\"simpleValue\":null}";
		new ParserFactory().parserJson().parse(simplePojo, outputStream);
		assertEquals(expected, outputStream.toString());
	}

	//Custom Object in Pojo(Complex Type)
	@Test
	public void parserJsonWithCustomObjectInPojoTest() throws IOException, ParserException {
		AttributeObjectPojo attributeObjectPojo = new AttributeObjectPojo();
		SimplePojo simplePojo = new SimplePojo();
		simplePojo.setSimpleValue("test");
		attributeObjectPojo.setSimplePojo(simplePojo);
		String expected = "{\"simplePojo\":{\"simpleValue\":\"test\"}}";
		new ParserFactory().parserJson().parse(attributeObjectPojo, outputStream);
		assertEquals(expected, outputStream.toString());
	}

	@Test
	public void parserJsonWithCustomObjectEmptyInPojoTest() throws IOException, ParserException {
		AttributeObjectPojo attributeObjectPojo = new AttributeObjectPojo();
		attributeObjectPojo.setSimplePojo(new SimplePojo());
		String expected = "{\"simplePojo\":{\"simpleValue\":null}}";
		new ParserFactory().parserJson().parse(attributeObjectPojo, outputStream);
		assertEquals(expected, outputStream.toString());
	}

	@Test
	public void parserJsonWithCustomObjectNullInPojoTest() throws IOException, ParserException {
		AttributeObjectPojo attributeObjectPojo = new AttributeObjectPojo();
		String expected = "{\"simplePojo\":null}";
		new ParserFactory().parserJson().parse(attributeObjectPojo, outputStream);
		assertEquals(expected, outputStream.toString());
	}

	//List with simple elements in Pojo
	@Test
	public void parserJsonWithOneSimpleElementAtListInPojoTest() throws IOException, ParserException {
		AttributeListPojo attributeListPojo = new AttributeListPojo();
		List<String> list = new ArrayList<>();
		list.add("element 1");
		attributeListPojo.setListValue(list);
		String expected = "{\"listValue\":[\"element 1\"]}";
		new ParserFactory().parserJson().parse(attributeListPojo, outputStream);
		assertEquals(expected, outputStream.toString());
	}

	@Test
	public void parserJsonWithThreeSimpleElementsAtListInPojoTest() throws IOException, ParserException {
		AttributeListPojo attributeListPojo = new AttributeListPojo();
		List<String> list = new ArrayList<>();
		list.add("element 1");
		list.add("element 2");
		list.add("element 3");
		attributeListPojo.setListValue(list);
		String expected = "{\"listValue\":[\"element 1\",\"element 2\",\"element 3\"]}";
		new ParserFactory().parserJson().parse(attributeListPojo, outputStream);
		assertEquals(expected, outputStream.toString());
	}

	@Test
	public void parserJsonWithThreeSimpleElementInTheListBeingOneNullInPojoTest() throws IOException, ParserException {
		AttributeListPojo attributeListPojo = new AttributeListPojo();
		List<String> list = new ArrayList<>();
		list.add("element 1");
		list.add(null);
		list.add("element 3");
		attributeListPojo.setListValue(list);
		String expected = "{\"listValue\":[\"element 1\",null,\"element 3\"]}";
		new ParserFactory().parserJson().parse(attributeListPojo, outputStream);
		assertEquals(expected, outputStream.toString());
	}

	@Test
	public void parserJsonWithListSimpleEmptyInPojoTest() throws IOException, ParserException {
		AttributeListPojo attributeListPojo = new AttributeListPojo();
		attributeListPojo.setListValue(new ArrayList<String>());
		String expected = "{\"listValue\":[]}";
		new ParserFactory().parserJson().parse(attributeListPojo, outputStream);
		assertEquals(expected, outputStream.toString());
	}

	@Test
	public void parserJsonWithListSimpleNullInPojoTest() throws IOException, ParserException {
		AttributeListPojo attributeListPojo = new AttributeListPojo();
		String expected = "{\"listValue\":[]}";
		new ParserFactory().parserJson().parse(attributeListPojo, outputStream);
		assertEquals(expected, outputStream.toString());
	}

	//List With CUSTOM List
	@Test
	public void parserJsonWithOneCustomElementAtListInPojoTest() throws IOException, ParserException {
		CustomAttributeListPojo customAttributeListPojo = new CustomAttributeListPojo();
		List<SimplePojo> list = new ArrayList<>();
		SimplePojo simplePojo = new SimplePojo();
		simplePojo.setSimpleValue("test");
		list.add(simplePojo);
		customAttributeListPojo.setListSimplePojo(list);
		String expected = "{\"listSimplePojo\":[{\"simpleValue\":\"test\"}]}";
		new ParserFactory().parserJson().parse(customAttributeListPojo, outputStream);
		assertEquals(expected, outputStream.toString());
	}

	@Test
	public void parserJsonWithOneCustomElementEmptyAtListInPojoTest() throws IOException, ParserException {
		CustomAttributeListPojo customAttributeListPojo = new CustomAttributeListPojo();
		List<SimplePojo> list = new ArrayList<>();
		list.add(new SimplePojo());
		customAttributeListPojo.setListSimplePojo(list);
		String expected = "{\"listSimplePojo\":[{\"simpleValue\":null}]}";
		new ParserFactory().parserJson().parse(customAttributeListPojo, outputStream);
		assertEquals(expected, outputStream.toString());
	}

	@Test
	public void parserJsonWithOneCustomElementNullAtListInPojoTest() throws IOException, ParserException {
		CustomAttributeListPojo customAttributeListPojo = new CustomAttributeListPojo();
		List<SimplePojo> list = new ArrayList<>();
		list.add(null);
		customAttributeListPojo.setListSimplePojo(list);
		String expected = "{\"listSimplePojo\":[null]}";
		new ParserFactory().parserJson().parse(customAttributeListPojo, outputStream);
		assertEquals(expected, outputStream.toString());
	}

	@Test
	public void parserJsonWithThreeCustomElementsAtListInPojoTest() throws IOException, ParserException {
		CustomAttributeListPojo customAttributeListPojo = new CustomAttributeListPojo();
		List<SimplePojo> list = new ArrayList<>();
		SimplePojo simplePojo = new SimplePojo();
		list.add(simplePojo);
		list.add(simplePojo);
		list.add(simplePojo);
		customAttributeListPojo.setListSimplePojo(list);
		String expected = "{\"listSimplePojo\":[{\"simpleValue\":null},{\"simpleValue\":null},{\"simpleValue\":null}]}";
		new ParserFactory().parserJson().parse(customAttributeListPojo, outputStream);
		assertEquals(expected, outputStream.toString());
	}

	@Test
	public void parserJsonWithThreeCustomElementInTheListBeingOneNullInPojoTest() throws IOException, ParserException {
		CustomAttributeListPojo customAttributeListPojo = new CustomAttributeListPojo();
		List<SimplePojo> list = new ArrayList<>();
		SimplePojo simplePojo = new SimplePojo();
		list.add(simplePojo);
		list.add(null);
		list.add(simplePojo);
		customAttributeListPojo.setListSimplePojo(list);
		String expected = "{\"listSimplePojo\":[{\"simpleValue\":null},null,{\"simpleValue\":null}]}";
		new ParserFactory().parserJson().parse(customAttributeListPojo, outputStream);
		assertEquals(expected, outputStream.toString());
	}

	@Test
	public void parserJsonWithListCustomEmptyInPojoTest() throws IOException, ParserException {
		CustomAttributeListPojo customAttributeListPojo = new CustomAttributeListPojo();
		customAttributeListPojo.setListSimplePojo(new ArrayList<SimplePojo>());
		String expected = "{\"listSimplePojo\":[]}";
		new ParserFactory().parserJson().parse(customAttributeListPojo, outputStream);
		assertEquals(expected, outputStream.toString());
	}

	@Test
	public void parserJsonWithListCustomNullInPojoTest() throws IOException, ParserException {
		CustomAttributeListPojo customAttributeListPojo = new CustomAttributeListPojo();
		String expected = "{\"listSimplePojo\":[]}";
		new ParserFactory().parserJson().parse(customAttributeListPojo, outputStream);
		assertEquals(expected, outputStream.toString());
	}

	//Teste Full Pojo
	@Test
	public void parserJsonWithPojoWithSeveralAttributesOfManyTypesTest() throws IOException, ParserException {
		Pojo pojo = new Pojo();
		pojo.setObjBoolean(true);
		pojo.setArrayObjBoolean(new Boolean[]{true,false});
		pojo.setObjString("Fulano");
		pojo.setArrayObjString(new String[]{"value 1", "value 2"});
		pojo.setObjInteger(18);
		pojo.setArrayObjInteger(new Integer[]{18,20});
		pojo.setObjDouble(10.5);
		pojo.setArrayObjDouble(new Double[]{10.5, 11.45});
		pojo.setObjFloat(123.456f);
		pojo.setArrayObjFloat(new Float[]{123.456f,78.96f});
		pojo.setObjLong(1l);
		pojo.setArrayObjLong(new Long[]{1l, 2l});
		pojo.setPrimBoolean(true);
		pojo.setArrayPrimBoolean(new boolean[]{true, false}); 
		pojo.setPrimChar('a');
		pojo.setArrayPrimChar(new char[]{'a','b','c','d','e','f','g',});
		pojo.setPrimByte((byte)1);
		pojo.setArrayPrimByte(new byte[]{0,1});
		pojo.setPrimShort((short)1);
		pojo.setArrayPrimShort(new short[]{0,1});
		pojo.setPrimInt(18);
		pojo.setArrayPrimInt(new int[]{25,30});
		pojo.setPrimLong(1l);
		pojo.setArrayPrimLong(new long[]{1l, 2l});
		pojo.setPrimFloat(1.5f);
		pojo.setArrayPrimFloat(new float[]{1.5f, 2.9f});
		pojo.setPrimDouble(1.8);
		pojo.setArrayPrimDouble(new double[]{1.8, 2.9});

		Pojo elementPojo = new Pojo();
		pojo.setElementPojo(elementPojo);

		ArrayList<String> listStringPojo = new ArrayList<String>();
		listStringPojo.add("Element 1");
		listStringPojo.add("Element 2");
		listStringPojo.add("Element 3");
		pojo.setListStringPojo(listStringPojo);

		ArrayList<Pojo> listElementPojo = new ArrayList<Pojo>();
		Pojo elementListPojo = new Pojo();
		listElementPojo.add(elementListPojo);
		pojo.setListElementPojo(listElementPojo);

		String expected = "{\"elementPojo\":{\"elementPojo\":null,\"listElementPojo\":[],\"listStringPojo\":[],\"objLong\":null,\"objString\":null,\"objInteger\":null,\"objDouble\":null,\"objFloat\":null,\"objBoolean\":null,\"arrayObjLong\":[],\"arrayObjString\":[],\"arrayObjInteger\":[],\"arrayObjDouble\":[],\"arrayObjFloat\":[],\"arrayObjBoolean\":[],\"primBoolean\":false,\"primChar\":\"\u0000\",\"primByte\":0,\"primShort\":0,\"primInt\":0,\"primLong\":0,\"primFloat\":0.0,\"primDouble\":0.0,\"arrayPrimBoolean\":[],\"arrayPrimChar\":[],\"arrayPrimByte\":[],\"arrayPrimShort\":[],\"arrayPrimInt\":[],\"arrayPrimLong\":[],\"arrayPrimFloat\":[],\"arrayPrimDouble\":[]},\"listElementPojo\":[{\"elementPojo\":null,\"listElementPojo\":[],\"listStringPojo\":[],\"objLong\":null,\"objString\":null,\"objInteger\":null,\"objDouble\":null,\"objFloat\":null,\"objBoolean\":null,\"arrayObjLong\":[],\"arrayObjString\":[],\"arrayObjInteger\":[],\"arrayObjDouble\":[],\"arrayObjFloat\":[],\"arrayObjBoolean\":[],\"primBoolean\":false,\"primChar\":\"\u0000\",\"primByte\":0,\"primShort\":0,\"primInt\":0,\"primLong\":0,\"primFloat\":0.0,\"primDouble\":0.0,\"arrayPrimBoolean\":[],\"arrayPrimChar\":[],\"arrayPrimByte\":[],\"arrayPrimShort\":[],\"arrayPrimInt\":[],\"arrayPrimLong\":[],\"arrayPrimFloat\":[],\"arrayPrimDouble\":[]}],\"listStringPojo\":[\"Element 1\",\"Element 2\",\"Element 3\"],\"objLong\":1,\"objString\":\"Fulano\",\"objInteger\":18,\"objDouble\":10.5,\"objFloat\":123.456,\"objBoolean\":true,\"arrayObjLong\":[1,2],\"arrayObjString\":[\"value 1\",\"value 2\"],\"arrayObjInteger\":[18,20],\"arrayObjDouble\":[10.5,11.45],\"arrayObjFloat\":[123.456,78.96],\"arrayObjBoolean\":[true,false],\"primBoolean\":true,\"primChar\":\"a\",\"primByte\":1,\"primShort\":1,\"primInt\":18,\"primLong\":1,\"primFloat\":1.5,\"primDouble\":1.8,\"arrayPrimBoolean\":[true,false],\"arrayPrimChar\":[\"a\",\"b\",\"c\",\"d\",\"e\",\"f\",\"g\"],\"arrayPrimByte\":[0,1],\"arrayPrimShort\":[0,1],\"arrayPrimInt\":[25,30],\"arrayPrimLong\":[1,2],\"arrayPrimFloat\":[1.5,2.9],\"arrayPrimDouble\":[1.8,2.9]}";
		new ParserFactory().parserJson().parse(pojo, outputStream);
		assertEquals(expected, outputStream.toString());
	}

	//List Pojo
	@Test
	public void parserJsonWithListPojoWithOneElementTest() throws IOException, ParserException {
		SimplePojo simplePojo1 = new SimplePojo();
		simplePojo1.setSimpleValue("test pojo 1");
		List<SimplePojo> listPojo = new ArrayList<>();
		listPojo.add(simplePojo1);
		String expected = "[{\"simpleValue\":\"test pojo 1\"}]";
		new ParserFactory().parserJson().parse(listPojo, outputStream);
		assertEquals(expected, outputStream.toString());
	}
	@Test
	public void parserJsonWithListPojoWithTwoElementsTest() throws IOException, ParserException {
		SimplePojo simplePojo1 = new SimplePojo();
		simplePojo1.setSimpleValue("test pojo 1");
		SimplePojo simplePojo2 = new SimplePojo();
		simplePojo2.setSimpleValue("test pojo 2");
		List<SimplePojo> listPojo = new ArrayList<>();
		listPojo.add(simplePojo1);
		listPojo.add(simplePojo2);
		String expected = "[{\"simpleValue\":\"test pojo 1\"},{\"simpleValue\":\"test pojo 2\"}]";
		new ParserFactory().parserJson().parse(listPojo, outputStream);
		assertEquals(expected, outputStream.toString());
	}

	@Test
	public void parserJsonWithListPojoEmptyTest() throws IOException, ParserException {
		List<SimplePojo> listPojo = new ArrayList<>();
		String expected = "[]";
		new ParserFactory().parserJson().parse(listPojo, outputStream);
		assertEquals(expected, outputStream.toString());
	}

	@Test
	public void parserJsonWithListPojoNullTest() throws IOException, ParserException {
		List<SimplePojo> listPojo = null;
		String expected = "[]";
		new ParserFactory().parserJson().parse(listPojo, outputStream);
		assertEquals(expected, outputStream.toString());
	}
}
