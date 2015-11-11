package br.com.diegomelo.cleanparse.model;

import br.com.diegomelo.cleanparse.annotation.ParseTransient;

public class SimplePojoWithAnnotationParseTransient {
	@ParseTransient
	private String simpleValue;
	private Integer number;

	public String getSimpleValue() {
		return simpleValue;
	}

	public void setSimpleValue(String simpleValue) {
		this.simpleValue = simpleValue;
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}
	
	
}
