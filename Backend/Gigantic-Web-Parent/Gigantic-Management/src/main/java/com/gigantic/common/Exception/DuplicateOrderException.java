package com.gigantic.admin.Exception;

public class DuplicateOrderException extends Exception{
    public DuplicateOrderException(Exception message) {
        super(message);
    }
}
