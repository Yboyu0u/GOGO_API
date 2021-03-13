package com.gogo.GoGo.exception;

public class NotExistedCommunityException extends RuntimeException{

    private static final String MESSAGE ="존재하지 않는 게시글입니다";
    public NotExistedCommunityException(){
        super(MESSAGE);
    }

}
