package com.johnenad.enadspring1.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@ToString
public class MyServiceResponse {
    private String message;
    private Object data;

    public MyServiceResponse(String message, Object data) {
        this.message = (message == null || message.isEmpty() || message.trim().isEmpty()) ? "OK" : message;
        this.data = data;
    }
}
