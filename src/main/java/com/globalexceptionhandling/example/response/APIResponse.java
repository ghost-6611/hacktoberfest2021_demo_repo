package com.globalexceptionhandling.example.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class APIResponse
{
    private String message;
    private boolean status;
    private Object entity;
}
