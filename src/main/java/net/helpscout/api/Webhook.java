package net.helpscout.api;

import java.io.BufferedReader;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.http.HttpServletRequest;

import net.helpscout.api.model.Conversation;
import net.helpscout.api.model.Customer;

import org.apache.commons.codec.binary.Base64;

final public class Webhook {        
    private final String secretKey; 
    private final HttpServletRequest request;
            
    private String inputStr = null;
    
    public Webhook(final String secretKey, final HttpServletRequest request) {
        this.secretKey = secretKey;
        this.request   = request;
    }
    
    private String getHeader(String headerName) {
        return request.getHeader(headerName);       
    }
    
    /**
     * Get the event type
     * @return string
     */
    public String getEventType() {
        return this.getHeader("X-HELPSCOUT-EVENT");
    }   
        
    public boolean isTestEvent() {
        return "helpscout.test".equals(this.getEventType());        
    }

    private boolean isEventTypeOf(String eventType) {
        String event = this.getEventType();
        if (event != null) {
            return event.substring(0, eventType.length()).equals(eventType);            
        }
        return false;
    }
    
    /**
     * Is the current event a type of conversation event
     * @return boolean
     */
    public boolean isConversationEvent() {
        return this.isEventTypeOf("convo");
    }

    /**
     * Is the current event a type of customer event
     * @return boolean
     */
    public boolean isCustomerEvent() {
        return this.isEventTypeOf("customer");
    }

    /**
     * Returns true if the current request is a valid webhook issued from Help Scout, false otherwise.
     * @return boolean
     */
    public boolean isValid() {
        String computed = generateSignature();
        
        if (computed != null) {
            return computed.equals(getHeader("X-HELPSCOUT-SIGNATURE"));
        }
        return false;
    }

    private String generateSignature() {
        String computed = null;     
        String json     = getJsonString();
        
        if (json != null) {
            try {
                Mac mac = Mac.getInstance("HmacSHA1");
                            
                mac.init(new SecretKeySpec(secretKey.getBytes(),"HmacSHA1"));
                
                byte[] digest = json.getBytes();
    
                computed = new String(Base64.encodeBase64(mac.doFinal(digest))).trim();         
            } catch (Exception e) {
                // ignore
            }
        }       
        return computed;
    }

    /**
     * @return Conversation
     */
    public Conversation getConversation() {
        String json = getJsonString();      
        if (json != null) {
            return Parser.getInstance().getConversation(json);          
        }
        return null;
    }


    /**
     * @return Customer
     */ 
    public Customer getCustomer() {
        String json = getJsonString();      
        if (json != null) {
            return Parser.getInstance().getCustomer(json);          
        }
        return null;        
    }
    
    private String getJsonString() {
        if (inputStr == null) {         
            inputStr = this.readJsonFromRequest();  
        }       
        return inputStr;
    }
    
    private String readJsonFromRequest() {
        StringBuffer jb = new StringBuffer();
        String line = null;
        
        try {
            BufferedReader reader = request.getReader();
            while ((line = reader.readLine()) != null) {
                jb.append(line);
            }
            reader.close();
            
        } catch (Exception e) { 
            /*report an error*/ 
        }
        return jb.toString();
    }
}