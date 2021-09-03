package com.example.javarxhogwarts.controller;

import com.example.javarxhogwarts.repository.StudentRepository;
import com.example.javarxhogwarts.request.StudentRequest;
import com.example.javarxhogwarts.service.ConsumingApi;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/aluno")
public class StudentController {

    private final StudentRepository studentRepository;

    @PostMapping("/cadastrar")
    public ResponseEntity<?> registerStudent(@RequestBody StudentRequest studentRequest) {
        var student = ConsumingApi.sortingHat();
        student.setName(studentRequest.getName());
        studentRepository.save(student);
        return ResponseEntity.ok(studentRequest);
    }

    @GetMapping("/alunos")
    public ResponseEntity<?> findAll() {
        return ResponseEntity.ok().body(studentRepository.findAll());
    }

    @GetMapping("/findByName/{name}")
    public ResponseEntity<?> findByName(@PathVariable String name){
        var student = studentRepository.findByName(name);
        var response = ConsumingApi.findHouse(student);
        return ResponseEntity.ok().body(response);
    }
}
