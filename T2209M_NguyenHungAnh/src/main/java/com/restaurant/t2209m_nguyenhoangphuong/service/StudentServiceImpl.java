//package com.restaurant.t2209m_nguyenhoangphuong.service;
//
//import com.restaurant.t2209m_nguyenhoangphuong.entities.Student;
//import com.restaurant.t2209m_nguyenhoangphuong.repository.StudentRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import java.util.List;
//
//@Service
//public class StudentServiceImpl implements StudentService {
//
//    @Autowired
//    private StudentRepository studentRepository;
//
//    @Override
//    public List<Student> findAllStudents() {
//        return studentRepository.findAllStudents();
//    }
//
//    @Override
//    public Student findStudentById(Integer id) {
//        return studentRepository.findById(id).orElse(null);
//    }
//
//    @Override
//    public void saveStudent(Student student) {
//        studentRepository.save(student);
//    }
//
//    @Override
//    public void deleteStudent(Integer id) {
//        studentRepository.deleteById(id);
//    }
//}
