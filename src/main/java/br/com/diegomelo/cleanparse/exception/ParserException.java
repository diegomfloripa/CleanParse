package br.com.diegomelo.cleanparse.exception;

public class ParserException extends ReflectiveOperationException {
	private static final long serialVersionUID = -7935058365165609264L;

	public ParserException() {
		super();
	}
	
	public ParserException(String message) {
		super(message);
	}
	
	 public ParserException(Throwable cause) {
	        super(cause);
	    }
}
