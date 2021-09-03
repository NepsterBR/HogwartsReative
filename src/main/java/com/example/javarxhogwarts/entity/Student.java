package com.example.javarxhogwarts.entity;

import com.google.gson.annotations.SerializedName;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Student {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;

    @SerializedName("sorting-hat-choice")
    private String idHouse;

}
