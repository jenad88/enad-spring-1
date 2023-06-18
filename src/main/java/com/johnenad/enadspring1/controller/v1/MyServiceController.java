package com.johnenad.enadspring1.controller.v1;

import com.johnenad.enadspring1.dto.MyServiceResponse;
import com.johnenad.enadspring1.service.MyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.johnenad.enadspring1.util.DemoUtil.PATH_V1;

@RestController
@RequestMapping(PATH_V1 + "/myservice")
public class MyServiceController implements MyServiceApi {

    private final MyService myService;

    @Autowired
    public MyServiceController(MyService myService) {
        this.myService = myService;
    }

    @Override
    public ResponseEntity<MyServiceResponse> doSomething() {
        try {
            String result = myService.doSomething();

            if (result == null || result.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(MyServiceResponse.builder().data(result).build(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
