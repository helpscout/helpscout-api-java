package net.helpscout.api.exception;

import net.helpscout.api.ApiException;

public class ServiceUnavailableException extends ApiException {

	private static final long serialVersionUID = 1L;

	public ServiceUnavailableException(String mesg) {
		super(mesg);	
	}

}
