package com.example.demo.controller;

import com.example.demo.dao.PersonRepository;
import com.example.demo.pojo.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PersonController {

    @Autowired
    private PersonRepository personRepository;

    @RequestMapping("/save2")
    public String save(){
        Person person=new Person();
        person.setName("瓜田李下"+1);
        person.setAge(24);
        person.setSort1(1);
        person.setSort2(2);

        personRepository.save(person);

        Person person2=new Person();
        person2.setName("瓜田李下"+2);
        person2.setAge(24);
        person2.setSort1(2);
        person2.setSort2(1);

        personRepository.save(person2);

        return "success";
    }

    @RequestMapping("/save3")
    public String save3(){
        for(int i=0;i<100;i++){
            Person person=new Person();
            person.setName("瓜田李下"+i);
            person.setAge(i);
            person.setSort1(i);
            person.setSort2(i);

            personRepository.save(person);
        }

        return "success";
    }

    @RequestMapping("/get")
    public List<Person> get(){
        Sort sort=Sort.by(Sort.Direction.ASC,"sort1")
                .and(Sort.by(Sort.Direction.ASC,"sort2"));

        return personRepository.findAll(sort);
    }

    @RequestMapping("/get2")
    public List<Person> get2(){
        Sort sort=Sort.by(Sort.Direction.ASC,"sort2")
                .and(Sort.by(Sort.Direction.ASC,"sort1"));

        return personRepository.findAll(sort);
    }

    @RequestMapping("/get3")
    public List<Person> get3(){
        Sort sort=Sort.by("sort1").ascending();
        Sort sort2=Sort.by("sort2").ascending();
        Sort s=sort.and(sort2);

        return personRepository.findAll(s);
    }

    @RequestMapping("/get4/{pageNum}/{pageSize}")
    public List<Person> get4(@PathVariable("pageNum")Integer pageNum,@PathVariable("pageSize")Integer pageSize){
        Sort sort=Sort.by("sort1").descending();
        PageRequest pageRequest=PageRequest.of(pageNum,pageSize,sort);

        Page<Person> page=personRepository.findAll(pageRequest);

        return page.getContent();
    }
}
