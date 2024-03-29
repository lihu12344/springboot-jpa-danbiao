package com.example.demo.dao;

import com.example.demo.pojo.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface StudentRepository extends JpaRepository<Student,Integer>,
        JpaSpecificationExecutor<Student> {

}
