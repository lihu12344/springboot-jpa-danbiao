package com.example.demo.serviceImpl;

import com.example.demo.dao.UserRepository;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional
    public void deleteByName(String name) {
        userRepository.deleteByName(name);
    }

    @Override
    @Transactional
    public Integer deleteByAge(Integer age) {
        return userRepository.deleteByAge(age);
    }

    @Override
    @Transactional
    public Integer deleteByNameAndAge(String name,Integer age) {
        return userRepository.deleteByNameAndAge(name,age);
    }
}
