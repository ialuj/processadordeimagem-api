package br.com.bixtecnologia.processadordeimagem.services.util;

public class BusinessException extends RuntimeException {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -1717075312754212125L;
	
	public BusinessException(String message) {
        super(message);
    }

}
