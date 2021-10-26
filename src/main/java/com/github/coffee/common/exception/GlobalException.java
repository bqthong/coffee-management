package com.github.coffee.common.exception;

import com.github.coffee.common.dto.ResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@ControllerAdvice
public class GlobalException {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ResponseDto> notFoundException(HttpServletRequest request, NotFoundException e) {
        return errorResponse(HttpStatus.NOT_FOUND, e.getMessage(), request.getRequestURI());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ResponseDto> badRequestException(HttpServletRequest request, MethodArgumentNotValidException e) {
        return errorResponse(HttpStatus.BAD_REQUEST, "Bad Request Exception", request.getRequestURI());
    }

    public ResponseEntity<ResponseDto> errorResponse(HttpStatus status, String message, String path) {
        ResponseDto response = new ResponseDto();
        response.setStatus(status);
        response.setMessage(message);
        response.setPath(path);
        response.setTimestamp(new Date());
        return ResponseEntity.status(status).body(response);
    }

}
