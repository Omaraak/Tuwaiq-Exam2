package com.example.exame2.Service;

import com.example.exame2.Model.Student;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class StudentService {
    ArrayList<Student> students = new ArrayList<Student>();

    public ArrayList<Student> getStudents() {
        return students;
    }

    public void addStudent(Student student) {
        students.add(student);
    }

    public boolean updateStudent(String id, Student student) {
        for (int i = 0; i < students.size(); i++) {
            if (students.get(i).getId().equals(id)) {
                students.set(i, student);
                return true;
            }
        }
        return false;
    }

    public boolean deleteStudent(String id) {
        for (Student student : students) {
            if (student.getId().equals(id)) {
                students.remove(student);
                return true;
            }
        }
        return false;
    }

    public Student getStudent(String name) {
        for (Student student : students) {
            if (student.getName().equals(name)) {
                return student;
            }
        }
        return null;
    }

    public ArrayList<Student> getStudentsByMajor(String major) {
        ArrayList<Student> studentsByMajor = new ArrayList<>();
        for (Student student : students) {
            if (student.getMajor().equals(major)) {
                studentsByMajor.add(student);
            }
        }
        return studentsByMajor;
    }
}
