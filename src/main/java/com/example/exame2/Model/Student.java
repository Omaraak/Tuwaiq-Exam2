package com.example.exame2.Model;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Student {
    @NotEmpty(message = "id can't be empty")
    private String id;
    @NotEmpty(message = "name can't be empty")
    private String name;
    @NotNull(message = "age can't be empty")
    private int age;
    @NotEmpty(message = "major can't be empty")
    private String major;
}
