package com.johnenad.enadspring1.controller.v1;

import com.johnenad.enadspring1.dto.MyServiceResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

public interface MyServiceApi {

    @GetMapping("/doSomething")
    ResponseEntity<MyServiceResponse> doSomething();
}
