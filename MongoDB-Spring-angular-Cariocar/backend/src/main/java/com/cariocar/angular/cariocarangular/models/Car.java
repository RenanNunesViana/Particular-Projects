package com.cariocar.angular.cariocarangular.models;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(value = "Car")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Car {

    @Id
    private Long plate;
    private String model;
    private Integer age;
    private String ownerCpf;
}
