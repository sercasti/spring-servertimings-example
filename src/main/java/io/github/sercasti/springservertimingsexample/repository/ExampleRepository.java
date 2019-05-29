package io.github.sercasti.springservertimingsexample.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import io.github.sercasti.springservertimingsexample.model.Example;

public interface ExampleRepository extends CrudRepository<Example, Long> {

    List<Example> findByName(String name);
}