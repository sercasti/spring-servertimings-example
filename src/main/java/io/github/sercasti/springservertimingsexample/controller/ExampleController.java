package io.github.sercasti.springservertimingsexample.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.github.sercasti.springservertimingsexample.service.ExampleService;
import io.github.sercasti.tracing.Traceable;

@RestController
public class ExampleController {

    @Autowired
    private ExampleService exampleService;

    //(a) Using the annotation to wrap the whole method with the tracing aspect
    @RequestMapping("/")
    @Traceable
    public String controllerMethodForIndex() throws InterruptedException {
        return exampleService.welcomeUser();
    }

}