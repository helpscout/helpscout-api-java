package net.helpscout.api.exception;

import net.helpscout.api.ApiException;

public class NotFoundException extends ApiException {

    private static final long serialVersionUID = 1L;

    public NotFoundException(String mesg) {
        super(mesg);    
    }

}
