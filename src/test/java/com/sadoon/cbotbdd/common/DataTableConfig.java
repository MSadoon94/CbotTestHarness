package com.sadoon.cbotbdd.common;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.java.DefaultDataTableCellTransformer;
import io.cucumber.java.DefaultDataTableEntryTransformer;
import io.cucumber.java.DefaultParameterTransformer;

import java.lang.reflect.Type;

public class DataTableConfig {

    private final ObjectMapper mapper;

    public DataTableConfig(){
        mapper = new ObjectMapper();
    }

    @DefaultDataTableCellTransformer
    @DefaultDataTableEntryTransformer
    @DefaultParameterTransformer
    public Object transform(Object from, Type to){
        return mapper.convertValue(from, mapper.constructType(to));
    }
}