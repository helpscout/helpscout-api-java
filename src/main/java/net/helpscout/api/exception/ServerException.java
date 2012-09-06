package net.helpscout.api.exception;

import net.helpscout.api.ApiException;

public class ServerException extends ApiException {

	private static final long serialVersionUID = 1L;

	public ServerException(String mesg) {
		super(mesg);	
	}

}
