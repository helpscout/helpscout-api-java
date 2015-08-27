package net.helpscout.api.model;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class Source {
    
    private String type;
    private String via;
    
    public boolean isViaCustomer() {
        return "customer".equals(getVia());
    }
}