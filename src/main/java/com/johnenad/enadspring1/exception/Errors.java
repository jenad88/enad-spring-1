package com.johnenad.enadspring1.exception;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class Errors implements Serializable {

    private static final long serialVersionUID = 1L;

    private List<ErrorResponse> errorResponses = null;
}
