package com.example.javarxhogwarts.service;

import com.example.javarxhogwarts.Response.StudentResponse;
import com.example.javarxhogwarts.entity.Student;
import com.example.javarxhogwarts.repository.StudentRepository;
import com.example.javarxhogwarts.request.StudentRequest;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

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

    public Observable<?> findAll() {
        Single<?> find = Single.create(single ->
            single.onSuccess(
                    studentRepository.findAll().stream().map(this::convertToOne).collect(Collectors.toList()))
        );
        return find.toObservable();
    }

    public StudentResponse convertToOne(Student student) {
        var house = ConsumingApi.findHouse(student);
        return new StudentResponse(student, house);
    }
}
