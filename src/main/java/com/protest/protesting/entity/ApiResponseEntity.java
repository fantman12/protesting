package com.protest.protesting.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.text.SimpleDateFormat;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class ApiResponseEntity {
    private Date timestamp;
    private int status;
    private String error;
    private String message;
    private String path;

}
