package com.task.utils;

import static com.task.utils.ReadProp.getProperties;

public class Constans {

    //VALIDATIONS
    public static final String EXISTS_BD = getProperties().getProperty("prop.exists.bd");
    public static final String ERROR_INSERT_STATUS = getProperties().getProperty("prop.error.insert.status");
    public static final String ERROR_EXCEPTION_THROWN = getProperties().getProperty("prop.error.exception.thrown");
    public static final String NOT_EXISTS_BD = getProperties().getProperty("prop.not.exists.bd");

}
