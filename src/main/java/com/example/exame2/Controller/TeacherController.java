package com.example.exame2.Controller;

import com.example.exame2.Api.ApiResponse;
import com.example.exame2.Model.Teacher;
import com.example.exame2.Service.TeacherService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/teacher")
@RequiredArgsConstructor
public class TeacherController {
    private final TeacherService teacherService;

    @GetMapping("/getAll")
    public ResponseEntity getAll(){
        if (teacherService.getTeachers().isEmpty())
            return ResponseEntity.status(400).body("No teachers found");
        return ResponseEntity.status(200).body(teacherService.getTeachers());
    }

    @PostMapping("/add")
    public ResponseEntity add(@Valid @RequestBody Teacher teacher, Errors errors){
        if (errors.hasErrors()){
            String errorMessage = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(new ApiResponse(errorMessage));
        }
        teacherService.addTeacher(teacher);
        return ResponseEntity.status(200).body("Teacher added");
    }

    @PutMapping("/update/{id}")
    public ResponseEntity update(@PathVariable String id, @Valid@RequestBody Teacher teacher, Errors errors){
        if (errors.hasErrors()){
            String errorMessage = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(new ApiResponse(errorMessage));
        }
        if (teacherService.updateTeacher(id, teacher))
            return ResponseEntity.status(200).body("Teacher updated");
        return ResponseEntity.status(404).body("Teacher not found");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity delete(@PathVariable String id){
        if (teacherService.deleteTeacher(id))
            return ResponseEntity.status(200).body("Teacher deleted");
        return ResponseEntity.status(404).body("Teacher not found");
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity getById(@PathVariable String id){
        if (teacherService.getTeacher(id) == null)
            return ResponseEntity.status(404).body("Teacher not found");
        return ResponseEntity.status(200).body(teacherService.getTeacher(id));
    }

    @GetMapping("/getBySalary/{salary}")
    public ResponseEntity getBySalary(@PathVariable int salary){
        if (teacherService.getTeacherBySalary(salary).isEmpty())
            return ResponseEntity.status(404).body("Teacher not found");
        return ResponseEntity.status(200).body(teacherService.getTeacherBySalary(salary));
    }
}
