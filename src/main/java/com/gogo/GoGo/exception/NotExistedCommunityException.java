package com.gogo.GoGo.exception;

public class NotExistedCommunityException extends RuntimeException{

    private static final String MESSAGE ="존재하지 않는 구인글입니다";
    public NotExistedCommunityException(){
        super(MESSAGE);
    }

}
