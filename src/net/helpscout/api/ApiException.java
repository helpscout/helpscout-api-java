package net.helpscout.api;

public class ApiException extends Exception {
	private static final long serialVersionUID = 1L;

	public ApiException(String mesg) {
		super(mesg);
	}
}
