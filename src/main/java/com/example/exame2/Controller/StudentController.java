package com.example.exame2.Controller;

import com.example.exame2.Api.ApiResponse;
import com.example.exame2.Model.Student;
import com.example.exame2.Service.StudentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/student")
@RequiredArgsConstructor
public class StudentController {
    private final StudentService studentService;

    @GetMapping("/getAll")
    public ResponseEntity getAll(){
        if (studentService.getStudents().isEmpty())
            return ResponseEntity.status(400).body(new ApiResponse("No students found"));
        return ResponseEntity.status(200).body(studentService.getStudents());
    }

    @PostMapping("/add")
    public ResponseEntity addStudent(@Valid @RequestBody Student student, Errors errors){
        if (errors.hasErrors()) {
            String errorMessage = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(new ApiResponse(errorMessage));
        }
        studentService.addStudent(student);
        return ResponseEntity.status(200).body("Student added");
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateStudent(@Valid @RequestBody Student student, @PathVariable String id, Errors errors){
        if (errors.hasErrors()) {
            String errorMessage = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(new ApiResponse(errorMessage));
        }
        if (studentService.updateStudent(id, student))
            return ResponseEntity.status(200).body("Student updated");
        return ResponseEntity.status(404).body("Student not found");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteStudent(@PathVariable String id){
        if (studentService.deleteStudent(id))
            return ResponseEntity.status(200).body("Student deleted");
        return ResponseEntity.status(404).body("Student not found");
    }

    @GetMapping("/getByName/{name}")
    public ResponseEntity getStudentByName(@PathVariable String name){
        if (studentService.getStudent(name) == null)
            return ResponseEntity.status(404).body("Student not found");
        return ResponseEntity.status(200).body(studentService.getStudent(name));
    }

    @GetMapping("/getByMajor/{major}")
    public ResponseEntity getStudentByMajor(@PathVariable String major){
        if (studentService.getStudentsByMajor(major).isEmpty())
            return ResponseEntity.status(404).body("Student not found");
        return ResponseEntity.status(200).body(studentService.getStudentsByMajor(major));
    }
}
