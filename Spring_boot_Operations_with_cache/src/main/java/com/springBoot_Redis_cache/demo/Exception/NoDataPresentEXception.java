package com.springBoot_Redis_cache.demo.Exception;

public class NoDataPresentEXception extends Exception{


    String message;

    public NoDataPresentEXception(String message)
    {
        super(message);
    }
}
