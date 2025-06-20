package com.bartr.exception;

public class UserIsNotAnAdmin extends RuntimeException{
    public UserIsNotAnAdmin(String msg){
        super(msg);
    }
    public UserIsNotAnAdmin(String message, Throwable cause) {
        super(message, cause);
    }
}
