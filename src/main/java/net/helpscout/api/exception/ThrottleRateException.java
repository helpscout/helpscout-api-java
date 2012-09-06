package net.helpscout.api.exception;

import net.helpscout.api.ApiException;

public class ThrottleRateException extends ApiException {

	private static final long serialVersionUID = 1L;

	public ThrottleRateException(String mesg) {
		super(mesg);	
	}

}
