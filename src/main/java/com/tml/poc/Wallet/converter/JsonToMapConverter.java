package com.tml.poc.Wallet.converter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * JSON Mapper to convert from String to JSON MAP
 */
@Converter
public class JsonToMapConverter
                    implements AttributeConverter<String, Map<String, Object>>
{
    private static final Logger LOGGER = LoggerFactory.getLogger(JsonToMapConverter.class);

    @Override
    @SuppressWarnings("unchecked")
    public Map<String, Object> convertToDatabaseColumn(String attribute)
    {
        if (attribute == null) {
           return new HashMap<>();
        }
        try
        {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(attribute, HashMap.class);
        }
        catch (IOException e) {
            LOGGER.error("Convert error while trying to convert string(JSON) to map data structure.");
        }
        return new HashMap<>();
    }

    @Override
    public String convertToEntityAttribute(Map<String, Object> dbData)
    {
        try
        {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.writeValueAsString(dbData);
        }
        catch (JsonProcessingException e)
        {
            LOGGER.error("Could not convert map to json string.");
            return null;
        }
    }
}