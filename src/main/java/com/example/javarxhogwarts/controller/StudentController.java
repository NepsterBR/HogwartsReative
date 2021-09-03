package com.example.javarxhogwarts.controller;

import com.example.javarxhogwarts.Response.StudentResponse;
import com.example.javarxhogwarts.request.StudentRequest;
import com.example.javarxhogwarts.service.StudentService;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.time.StopWatch;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.TimeUnit;

@AllArgsConstructor
@RestController
@RequestMapping("/aluno")
@Slf4j
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
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        Observable<?> all = studentService.findAll();
        stopWatch.stop();
        log.info("Executou em {}ms",stopWatch.getTime(TimeUnit.MILLISECONDS));
        return all;
    }

    @GetMapping("/findByName/{name}")
    @ResponseStatus(HttpStatus.OK)
    public Single<StudentResponse> findByName(@PathVariable String name){
        return studentService.findByName(name);
    }
}
