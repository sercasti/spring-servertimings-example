package io.github.sercasti.springservertimingsexample.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Example {

    @Id
    @GeneratedValue
    private Long id;
    private String name;

    protected Example() {
    }

    public Example(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return String.format("Example[id=%d, name='%s']", id, name);
    }
}
