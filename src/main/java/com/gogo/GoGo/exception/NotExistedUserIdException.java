package com.gogo.GoGo.exception;

public class NotExistedUserIdException extends RuntimeException{

    private static final String MESSAGE ="존재하지 않는 계정입니다";
    public NotExistedUserIdException(){
        super(MESSAGE);
    }

}

