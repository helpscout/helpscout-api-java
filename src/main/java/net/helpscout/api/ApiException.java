package net.helpscout.api;

import static org.apache.commons.lang3.StringUtils.isNotEmpty;

import lombok.Getter;

public class ApiException extends Exception {
    private static final long serialVersionUID = 1L;
    
    @Getter
    private String details;

    public ApiException(String summary) {
        super(summary);     
    }
    
    public ApiException(String summary, String details) {
        this(summary);
        this.details = details;
    }
    
    @Override
    public String toString() {
        String message = super.toString();
        
        if(isNotEmpty(getDetails())) {
            message += ("\n" + getDetails());
        }
        
        return message;
    }
}
