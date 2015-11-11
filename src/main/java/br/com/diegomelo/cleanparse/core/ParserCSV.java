package br.com.diegomelo.cleanparse.core;

import java.io.OutputStream;
import java.util.List;

import br.com.diegomelo.cleanparse.exception.ParserException;

/**
 * @author Diego Melo
 * @since 08/11/2015
 */
public class ParserCSV extends Parser {
	
	@Override
	public OutputStream parse(Object obj, OutputStream os) throws ParserException {
		throw new ParserException("Not Implemented Yet");
	}

	@Override
	public OutputStream parse(List<?> listObject, OutputStream os) throws ParserException {
		throw new ParserException("Not Implemented Yet");
	}
}
