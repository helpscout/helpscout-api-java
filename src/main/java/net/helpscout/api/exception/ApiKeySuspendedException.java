package net.helpscout.api.exception;

import net.helpscout.api.ApiException;

public class ApiKeySuspendedException extends ApiException {

	private static final long serialVersionUID = 1L;

	public ApiKeySuspendedException(String mesg) {
		super(mesg);	
	}

}
