package com.caronic.jwisdom.core.exercise.spring.cache;

import com.caronic.jwisdom.core.exercise.spring.data.User;

import java.util.List;

/**
 * Created by caronic on 2016/10/7.
 */
public interface UserDao {
    List<User> getAllUsers() throws InterruptedException;
    User getUser(Long id) throws InterruptedException;
    User saveUser(User user) throws InterruptedException;
    User updateUser(User user) throws InterruptedException;
}
