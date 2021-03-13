package com.gogo.GoGo.exception;

public class NotExistedBoardException extends RuntimeException{
    private static final String MESSAGE ="존재하지 않는 게시판입니다";
    public NotExistedBoardException(){
        super(MESSAGE);
    }
}
