package com.gogo.GoGo.exception;

public class AlreadyExistedNicknameException extends RuntimeException{
    private static final String MESSAGE ="이미 존재하는 사용자 이름입니다";
    public AlreadyExistedNicknameException(){
        super(MESSAGE);
    }
}
