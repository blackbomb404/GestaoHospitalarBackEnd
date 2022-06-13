package com.example.demo.config;

import com.example.demo.dao.PatientDao;
import com.example.demo.dao.UserDao;
import com.example.demo.dto.UserDto;
import com.example.demo.model.Patient;
import com.example.demo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import static java.lang.String.format;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserDao userDao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User foundUser = userDao.read(username).orElseThrow(
                () -> new UsernameNotFoundException(
                        format("User with id of '%s' not found.", username)
                )
        );
        return new UserDetailsImpl(foundUser);
    }
}
