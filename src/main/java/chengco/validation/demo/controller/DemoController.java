package chengco.validation.demo.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class DemoController {

    @PostMapping(value = "/testValidation", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void testValidation(@Valid @RequestBody DemoResource resource){
        //noop
    }
}
