package com.example.demo.dao;

import com.example.demo.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Integer> {

    void deleteByName(String name);

    Integer deleteByAge(Integer age);

    Integer deleteByNameAndAge(String name,Integer age);
    //方法的返回值为Integer，不能随便定义为User,可定义为void
}
