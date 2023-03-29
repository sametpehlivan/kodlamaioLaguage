package com.kodlamaio.language.responses;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Response<T> {

    private HttpStatus status;
    private String message;
    T data;
}