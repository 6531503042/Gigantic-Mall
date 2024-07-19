package com.gigantic.Configs;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;

public class FloatDeserializer extends JsonDeserializer<Float> {
    @Override
    public Float deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        //Declare variable
        String value = p.getText();

        //Remove non-numeric characters
        value = value.replaceAll("[^\\d.]", "");
        return Float.parseFloat(value);
    }
}
