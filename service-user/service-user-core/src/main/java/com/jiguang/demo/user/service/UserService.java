package com.jiguang.demo.user.service;

import com.jiguang.demo.user.api.entity.User;
import com.jiguang.demo.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author liups
 * @create 2017/11/27
 */
@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Transactional( propagation = Propagation.REQUIRES_NEW,rollbackFor = Exception.class)
    public void insert(Long id, String name, String password) {
        userRepository.insert(new User(id,name,password));
    }

    public void delete(Long id) {
        userRepository.delete(id);
    }

    public User findById(Long id) {

        User user = userRepository.findById(id);


        return user;
    }

    public User findByName(String name) {
        User user = userRepository.findByName(name);
        return user;
    }
}
