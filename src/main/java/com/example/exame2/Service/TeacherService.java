package com.example.exame2.Service;

import com.example.exame2.Model.Teacher;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class TeacherService {
    ArrayList<Teacher> teachers = new ArrayList<>();

    public ArrayList<Teacher> getTeachers() {
        return teachers;
    }

    public void addTeacher(Teacher teacher) {
        teachers.add(teacher);
    }

    public boolean updateTeacher(String id, Teacher teacher) {
        for (int i = 0; i < teachers.size(); i++) {
            if (teachers.get(i).getId().equals(id)) {
                teachers.set(i, teacher);
                return true;
            }
        }
        return false;
    }

    public boolean deleteTeacher(String id) {
        for (int i = 0; i < teachers.size(); i++) {
            if (teachers.get(i).getId().equals(id)) {
                teachers.remove(i);
                return true;
            }
        }
        return false;
    }

    public Teacher getTeacher(String id) {
        for (Teacher teacher : teachers) {
            if (teacher.getId().equals(id)) {
                return teacher;
            }
        }
        return null;
    }

    public ArrayList<Teacher> getTeacherBySalary(int salary) {
        ArrayList<Teacher> TeacherBySalary = new ArrayList<>();
        for (Teacher teacher : teachers) {
            if (teacher.getSalary() >= salary) {
                TeacherBySalary.add(teacher);
            }
        }
        return TeacherBySalary;
    }
}
