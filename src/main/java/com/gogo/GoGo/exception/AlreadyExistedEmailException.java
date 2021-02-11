package com.gogo.GoGo.exception;

public class AlreadyExistedEmailException extends RuntimeException{
    private static final String MESSAGE ="이미 존재하는 이메일입니다";
    public AlreadyExistedEmailException(){
        super(MESSAGE);
    }

}
