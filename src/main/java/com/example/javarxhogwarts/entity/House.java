package com.example.javarxhogwarts.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.List;

@Getter
@Setter
public class House {

    private String id;

    private String name;
    private String animal;
    private String founder;
    private List<Object> values;

}
