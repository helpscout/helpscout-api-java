package net.helpscout.api.json;

import static org.hamcrest.core.Is.*;
import static org.junit.Assert.*;
import lombok.val;

import org.junit.Test;

public class JsonFormatterTest {
    
    @Test
    public void formatsJson() {
        val rawJson = "{\"key\":\"value\"}";
        val formattedJson = "{\n  \"key\": \"value\"\n}";
        
        JsonFormatter formatter = new JsonFormatter();

        assertThat(formatter.format(rawJson), is(formattedJson));
    }
}