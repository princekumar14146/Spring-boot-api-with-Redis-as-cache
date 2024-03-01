package com.springBoot_Redis_cache.demo.Exception;

public class DataAlreadyPresentException extends Exception{

    String message;

    public DataAlreadyPresentException(String message)
    {
        super(message);
    }
}
