package net.helpscout.api.exception;

import net.helpscout.api.ApiException;

public class InvalidApiKeyException extends ApiException {

	private static final long serialVersionUID = 1L;

	public InvalidApiKeyException(String mesg) {
		super(mesg);	
	}

}
