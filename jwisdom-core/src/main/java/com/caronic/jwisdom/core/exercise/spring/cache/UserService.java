package com.caronic.jwisdom.core.exercise.spring.cache;

import com.caronic.jwisdom.core.exercise.exception.MyRuntimeException;
import com.caronic.jwisdom.core.exercise.spring.data.User;

import java.util.List;

/**
 * Created by caronic on 2016/10/7.
 */
public interface UserService {
    List<User> getAllUsers() throws MyRuntimeException;
    User getUser(Long id) throws MyRuntimeException;
    User saveUser(User user) throws MyRuntimeException;
    User updateUser(User user) throws MyRuntimeException;
}
