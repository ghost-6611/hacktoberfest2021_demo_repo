package com.globalexceptionhandling.example.response;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ExceptionResponse {

    private String message;
    private String ExceptionName;
}
