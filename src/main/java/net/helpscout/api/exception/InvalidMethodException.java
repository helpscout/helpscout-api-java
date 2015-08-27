package net.helpscout.api.exception;

import net.helpscout.api.ApiException;

public class InvalidMethodException extends ApiException {

    private static final long serialVersionUID = 1L;

    public InvalidMethodException(String mesg) {
        super(mesg);    
    }

}
