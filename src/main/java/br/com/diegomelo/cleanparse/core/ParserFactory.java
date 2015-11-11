package br.com.diegomelo.cleanparse.core;

public class ParserFactory {

	public Parser parserJson(){
		return new ParserJson();
	}
	
	public Parser parserXML(){
		return new ParserXml();
	}

	public Parser parserCSV() {
		return new ParserCSV();
	}
	
}
