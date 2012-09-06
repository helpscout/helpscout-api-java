package net.helpscout.api.exception;

import net.helpscout.api.ApiException;

public class InvalidFormatException extends ApiException {

	private static final long serialVersionUID = 1L;

	public InvalidFormatException(String mesg) {
		super(mesg);	
	}

}
