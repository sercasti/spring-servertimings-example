package io.github.sercasti.springservertimingsexample.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.github.sercasti.springservertimingsexample.repository.ExampleRepository;
import io.github.sercasti.tracing.core.Metric;
import io.github.sercasti.tracing.core.Tracing;

@Service
public class ExampleService {

    @Autowired
    private ExampleRepository exampleRepository;
    
    //(b) Injecting the API to use at code blocks declaratively
    @Autowired
    private Tracing tracing;

    public String welcomeUser() throws InterruptedException {
        return "Greetings from Spring Boot!" + getCurrentTime() + getFindAllFromRepo();
    }

    private String getCurrentTime() throws InterruptedException {
        Metric currentTimeMetric = tracing.start("getCurrentTime");
        String result = LocalDateTime.now().format(DateTimeFormatter.BASIC_ISO_DATE);
        Thread.sleep(123);
        currentTimeMetric.stop(); 
        return result;
    }

    private String getFindAllFromRepo() {
        Metric repoMetric = tracing.start("getFindAllFromRepo");
        String names = StreamSupport.stream(exampleRepository.findAll().spliterator(), false).map(e -> e.toString())
                .collect(Collectors.joining(","));
        repoMetric.stop();
        return names;
    }

}
