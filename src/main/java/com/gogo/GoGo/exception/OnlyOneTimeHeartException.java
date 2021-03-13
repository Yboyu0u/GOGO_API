package com.gogo.GoGo.exception;

public class OnlyOneTimeHeartException extends RuntimeException{

    private static final String MESSAGE ="한 유저당 한번만 좋아해 주세요";
    public OnlyOneTimeHeartException(){
        super(MESSAGE);
    }

}
