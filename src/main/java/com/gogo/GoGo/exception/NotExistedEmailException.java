package com.gogo.GoGo.exception;

public class NotExistedEmailException extends RuntimeException{

    private static final String MESSAGE ="존재하지 않는 이메일입니다";
    public NotExistedEmailException(){
        super(MESSAGE);
    }

}

