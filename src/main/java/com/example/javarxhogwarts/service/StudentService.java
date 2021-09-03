package com.example.javarxhogwarts.service;

import com.example.javarxhogwarts.Response.StudentResponse;
import com.example.javarxhogwarts.entity.Student;
import com.example.javarxhogwarts.repository.StudentRepository;
import com.example.javarxhogwarts.request.StudentRequest;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.time.StopWatch;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;


@Slf4j
@Service
@AllArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;

    public Single<StudentResponse> registerStudent(StudentRequest studentRequest) {
        return Single.create(single -> {
            var student = ConsumingApi.sortingHat();
            var house = ConsumingApi.findHouse(student);
            student.setName(studentRequest.getName());
            studentRepository.save(student);
            single.onSuccess(new StudentResponse(student, house));
        });
    }

    public Single<StudentResponse> findByName(String name) {
        return Single.create(single -> {
            var student = studentRepository.findByName(name);
            var house = ConsumingApi.findHouse(student);
            single.onSuccess(new StudentResponse(student, house));
        });
    }

    @Transactional(readOnly = true)
    public Observable<?> findAll() {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        Observable<StudentResponse> map = Observable.fromIterable(studentRepository.findByNameIsNotNull()
                .collect(Collectors.toList())).map(this::convertToOne);
        stopWatch.stop();
        log.info("Executou em {}ms", stopWatch.getTime(TimeUnit.MILLISECONDS));
        return map;
    }

    public StudentResponse convertToOne(Student student) {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        var house = ConsumingApi.findHouse(student);
        stopWatch.stop();
        log.info("Executou em {}ms", stopWatch.getTime(TimeUnit.MILLISECONDS));
        return new StudentResponse(student, house);
    }
}
