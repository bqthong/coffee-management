package com.github.coffee.common.dto;

import lombok.*;
import org.springframework.http.HttpStatus;

import java.util.Date;

@Getter
@Setter
public class ResponseDto {
    private HttpStatus status;
    private String message;
    private String path;
    private Date timestamp;
}
