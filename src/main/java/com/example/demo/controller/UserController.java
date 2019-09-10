package com.example.demo.controller;

import com.example.demo.dao.UserRepository;
import com.example.demo.pojo.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @RequestMapping("/delete")
    public String delete(){
        userRepository.deleteByName("上官飞燕");
        //自定义的删除方法不经事务会报错
        return "success";
    }

    @RequestMapping("/delete2")
    public Integer delete2(){
        return userRepository.deleteByAge(230);
        //自定义的删除方法不经事务会报错
    }

    @RequestMapping("/delete3")
    public void delete3(){
        userRepository.deleteById(5);
        //原生接口的delete方法可以不经过事务直接调用
    }

    @RequestMapping("/delete4")
    public Integer delete4(){
        return userService.deleteByNameAndAge("上官飞燕",23);
        //自定义的方法要经过事务调用,方法的返回值为Integer，不能随便定义
    }

    @RequestMapping("/delete5")
    public void delete5(){
        userService.deleteByName("上官飞燕");
    }

    @RequestMapping("/save")
    public String save(){

        for(int i=9;i<100;i++){
            User user=new User();
            user.setName("上官飞燕"+i);
            user.setAge(i);
            userRepository.save(user);
        }

        return "success";
    }

    @RequestMapping("/findByPage")
    public List<User> find(){
        PageRequest pageRequest=PageRequest.of(0,10);
        Page<User> page=userRepository.findAll(pageRequest);
        return page.getContent();
    }

    @RequestMapping("/sort")
    public List<User> sort(){
        Sort sort=Sort.by(Sort.Direction.ASC,"age");
        Page<User> page=userRepository.findAll(PageRequest.of(0,10,sort));
        return page.getContent();
    }
}
