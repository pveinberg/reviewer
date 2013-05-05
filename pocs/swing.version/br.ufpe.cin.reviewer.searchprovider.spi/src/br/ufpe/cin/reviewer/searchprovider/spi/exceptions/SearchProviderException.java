package br.ufpe.cin.reviewer.searchprovider.spi.exceptions;

public class SearchProviderException extends Exception {

	private static final long serialVersionUID = -2370996967700016761L;

	public SearchProviderException(String message, Throwable cause) {
		super(message, cause);
	}

	public SearchProviderException(String message) {
		super(message);
	}
	
}
