package com.gogo.GoGo.exception.handler;

import com.gogo.GoGo.exception.AlreadyExistedEmailException;
import com.gogo.GoGo.exception.NotExistedEmailException;
import com.gogo.GoGo.exception.PasswordWrongException;
import com.gogo.GoGo.exception.dto.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(AlreadyExistedEmailException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleAlreadyExistedEmailException(AlreadyExistedEmailException ex){
        return ErrorResponse.of(HttpStatus.BAD_REQUEST,ex.getMessage());
    }

    @ExceptionHandler(PasswordWrongException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handlePasswordWrongException(PasswordWrongException ex){
        return ErrorResponse.of(HttpStatus.BAD_REQUEST,ex.getMessage());
    }

    @ExceptionHandler(NotExistedEmailException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleNotExistedEmailException(NotExistedEmailException ex){
        return ErrorResponse.of(HttpStatus.BAD_REQUEST,ex.getMessage());
    }

    //위의 exception handler중에서 가장 적합한 것을 handling하게 되고 만약 세개가 다 아니라면 이쪽으로 handling이 진행 된다.
    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponse handleRuntimeException(RuntimeException ex){
        log.error("서버오류: {}",ex.getMessage(),ex);
        return ErrorResponse.of(HttpStatus.INTERNAL_SERVER_ERROR, "알 수 없는 서버 오류가 발생하였습니다");
    }




}
