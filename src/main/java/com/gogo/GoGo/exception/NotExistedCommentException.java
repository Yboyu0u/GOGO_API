package com.gogo.GoGo.exception;

public class NotExistedCommentException extends RuntimeException{

    private static final String MESSAGE ="존재하지 않는 댓글입니다";
    public NotExistedCommentException(){
        super(MESSAGE);
    }

}