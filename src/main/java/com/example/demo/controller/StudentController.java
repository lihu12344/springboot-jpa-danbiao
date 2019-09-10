package com.example.demo.controller;

import com.example.demo.dao.StudentPlusRepository;
import com.example.demo.dao.StudentRepository;
import com.example.demo.pojo.Student;
import com.example.demo.pojo.StudentCount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.Tuple;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private StudentPlusRepository studentPlusRepository;

    @RequestMapping("/save")
    public String save(){
        for(int i=0;i<100;i++){
            Student student=new Student();
            student.setName("瓜田李下"+i);
            student.setAge(i%3);

            studentRepository.save(student);
        }

        return "success";
    }

    @RequestMapping("/get")
    public List<Student> get(){
        return studentPlusRepository.find("瓜田李下"+"%",null);
    }

    @RequestMapping("/count")
    public List<Map<String,Object>> count(){
        List<Tuple> list=studentPlusRepository.count();

        List<Map<String,Object>> result=new ArrayList<>();

        for(Tuple t:list){
            System.out.println("年龄为"+t.get("age")+"的有"+t.get("count")+"人");

            Map<String,Object> map=new HashMap<>();
            map.put("age",t.get("age"));
            map.put("count",t.get("count"));
            result.add(map);
        }

        System.out.println(list);

        return result;
    }

    @RequestMapping("/count2")
    public List<StudentCount> count2(){

        List<StudentCount> list=studentPlusRepository.count2();

        for(StudentCount s:list){
            System.out.println(s.getAge()+"====>"+s.getCount());
        }

        return list;
    }
}
