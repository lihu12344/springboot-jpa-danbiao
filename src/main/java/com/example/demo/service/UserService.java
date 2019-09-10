package com.example.demo.service;


public interface UserService {

    void deleteByName(String name);

    Integer deleteByAge(Integer age);

    Integer deleteByNameAndAge(String name,Integer age);
}
