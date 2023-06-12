package com.johnenad.enadspring1.exception;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ErrorResponse implements Serializable {

    private static final long serialVersionUID = 1L;

    @Size(max = 100)
    private String code;
    @Size(max = 250)
    private String fieldName;
    @Size(max = 250)
    private String description;
}
