package com.example.javarxhogwarts.Response;

import com.example.javarxhogwarts.entity.House;
import com.example.javarxhogwarts.entity.Student;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StudentResponse {

    private long id;
    private String name;
    private House house;

    public StudentResponse(Student student, House house) {
        this.id = student.getId();
        this.name = student.getName();
        this.house = house;
    }
}
