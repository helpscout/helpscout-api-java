package net.helpscout.api.exception;

import net.helpscout.api.ApiException;

public class AccessDeniedException extends ApiException {

    private static final long serialVersionUID = 1L;

    public AccessDeniedException(String mesg) {
        super(mesg);    
    }

}
