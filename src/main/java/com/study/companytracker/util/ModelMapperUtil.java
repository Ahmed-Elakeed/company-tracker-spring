package com.study.companytracker.util;

import org.modelmapper.ModelMapper;

public class ModelMapperUtil {

    private static final ModelMapper modelMapper = new ModelMapper();

    public static ModelMapper MAPPER(){
        return modelMapper;
    }
}
