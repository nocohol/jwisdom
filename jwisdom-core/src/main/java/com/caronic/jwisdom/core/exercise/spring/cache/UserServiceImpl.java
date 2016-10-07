package com.caronic.jwisdom.core.exercise.spring.cache;

import com.caronic.jwisdom.core.exercise.exception.MyRuntimeException;
import com.caronic.jwisdom.core.exercise.spring.data.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by caronic on 2016/10/7.
 */
@Service
@CacheConfig(cacheManager = "simpleCacheManager")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    @Cacheable(cacheNames = {"users"})
    public List<User> getAllUsers() throws MyRuntimeException {
        System.out.println("Going to fetch users from database");
        try {
            List<User> users = userDao.getAllUsers();
            System.out.println("Fetched users.");
            return users;
        } catch (InterruptedException e) {
            throw new MyRuntimeException("Fetch users failed.", e);
        }
    }

    @Override
    @Cacheable(cacheNames = {"user"}, key = "#id", condition = "#id==1")
    public User getUser(Long id) throws MyRuntimeException {
        System.out.println("Going to fetch user " + id + " from database");
        try {
            User user = userDao.getUser(id);
            System.out.println("Fetched user " + id);
            return user;
        } catch (InterruptedException e) {
            throw new MyRuntimeException("Fetch user " + id + " failed.", e);
        }
    }

    @Override
    @CacheEvict(cacheNames = "users", allEntries = true)
    public User saveUser(User user) throws MyRuntimeException {
        System.out.println("Going to save user " + user.getName() + " into database");
        try {
            return userDao.saveUser(user);
        } catch (InterruptedException e) {
            throw new MyRuntimeException("Save user failed", e);
        }
    }

    @Override
    @CachePut(cacheNames = "user", key = "#user.id")
    public User updateUser(User user) throws MyRuntimeException {
        System.out.println("Going to update user " + user.getName() + " into database");
        try {
            return userDao.updateUser(user);
        } catch (InterruptedException e) {
            throw new MyRuntimeException("Update user failed.", e);
        }
    }
}
