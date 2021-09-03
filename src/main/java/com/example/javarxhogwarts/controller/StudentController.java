package com.example.javarxhogwarts.controller;

import com.example.javarxhogwarts.Response.StudentResponse;
import com.example.javarxhogwarts.repository.StudentRepository;
import com.example.javarxhogwarts.request.StudentRequest;
import com.example.javarxhogwarts.service.ConsumingApi;
import com.example.javarxhogwarts.service.StudentService;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/aluno")
public class StudentController {

    private final StudentService studentService;

    @PostMapping("/cadastrar")
    @ResponseStatus(HttpStatus.CREATED)
    public Single<StudentResponse> registerStudent(@RequestBody StudentRequest studentRequest) {
        return studentService.registerStudent(studentRequest);
    }

    @GetMapping("/alunos")
    @ResponseStatus(HttpStatus.OK)
    public Observable<?> findAll() {
        return studentService.findAll();
    }

    @GetMapping("/findByName/{name}")
    @ResponseStatus(HttpStatus.OK)
    public Single<StudentResponse> findByName(@PathVariable String name){
        return studentService.findByName(name);
    }
}
