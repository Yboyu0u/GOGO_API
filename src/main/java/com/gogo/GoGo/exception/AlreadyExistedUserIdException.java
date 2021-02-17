package com.gogo.GoGo.exception;

public class AlreadyExistedUserIdException extends RuntimeException{

    private static final String MESSAGE ="이미 존재하는 계정입니다";
    public AlreadyExistedUserIdException(){
        super(MESSAGE);
    }

}
