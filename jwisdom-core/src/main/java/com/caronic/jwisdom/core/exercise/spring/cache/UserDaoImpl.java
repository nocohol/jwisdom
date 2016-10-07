package com.caronic.jwisdom.core.exercise.spring.cache;

import com.caronic.jwisdom.core.exercise.spring.data.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by caronic on 2016/10/7.
 */
@Repository
public class UserDaoImpl implements UserDao {
    @Override
    public List<User> getAllUsers() throws InterruptedException {
        //Mock waiting long time to fetch users from database
        TimeUnit.SECONDS.sleep(3);
        User user1 = new User();
        user1.setId(1);
        user1.setName("user 1");

        User user2 = new User();
        user2.setId(2);
        user2.setName("user 2");

        List<User> users = new ArrayList<>();
        users.add(user1);
        users.add(user2);
        return users;
    }

    @Override
    public User getUser(Long id) throws InterruptedException {
        //Mock waiting long time to fetch user from database
        TimeUnit.SECONDS.sleep(3);
        User user = new User();
        user.setId(id);
        user.setName("user " + id);
        return user;
    }

    @Override
    public User saveUser(User user) throws InterruptedException {
        //Mock waiting long time to save user into database
        TimeUnit.SECONDS.sleep(2);
        return user;
    }

    @Override
    public User updateUser(User user) throws InterruptedException {
        //Mock waiting long time to save user into database
        TimeUnit.SECONDS.sleep(2);
        return user;
    }
}
