package io.github.sercasti.springservertimingsexample;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import io.github.sercasti.springservertimingsexample.model.Example;
import io.github.sercasti.springservertimingsexample.repository.ExampleRepository;
import io.github.sercasti.tracing.config.TracingConfig;
import io.github.sercasti.tracing.core.Tracing;
import io.github.sercasti.tracing.filter.TracingFilter;
import io.github.sercasti.tracing.interceptor.TracingInterceptor;

@SpringBootApplication
public class SpringServertimingsExampleApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringServertimingsExampleApplication.class, args);
    }

    @Bean
    public CommandLineRunner demo(ExampleRepository repository) {
        return (args) -> {
            repository.save(new Example("Jack"));
            repository.save(new Example("Chloe"));
            repository.save(new Example("Kim"));
            repository.save(new Example("David"));
            repository.save(new Example("Michelle"));
        };
    }

    @Bean
    protected Tracing tracing() {
        return TracingConfig.createTracing();
    }

    @Bean
    protected TracingFilter tracingFilter() {
        return new TracingFilter();
    }
    
    @Bean
    protected TracingInterceptor tracingInterceptor() {
        return new TracingInterceptor(tracing());
    }
}
