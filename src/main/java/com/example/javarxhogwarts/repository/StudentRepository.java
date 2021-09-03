package com.example.javarxhogwarts.repository;

import com.example.javarxhogwarts.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.stream.Stream;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {

    Student findByName(String name);
    Stream<Student> findByNameIsNotNull();

}
