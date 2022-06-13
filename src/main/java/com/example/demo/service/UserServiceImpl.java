package com.example.demo.service;

import com.example.demo.dao.UserDao;
import com.example.demo.dto.UserDto;
import com.example.demo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    @Override
    public int add(UserDto userDto) {
        return userDao.create(userDto);
    }

    @Override
    public List<User> getAll() {
        return null;
    }

    @Override
    public Optional<User> get(String s) {
        return Optional.empty();
    }

    @Override
    public int update(UserDto user, String s) {
        return 0;
    }

    @Override
    public int delete(String s) {
        return 0;
    }
}
