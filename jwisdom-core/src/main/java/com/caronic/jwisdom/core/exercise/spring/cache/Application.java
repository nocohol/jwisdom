package com.caronic.jwisdom.core.exercise.spring.cache;

import com.caronic.jwisdom.core.exercise.spring.data.User;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

/**
 * Created by caronic on 2016/10/7.
 */
public class Application {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        UserService userService = context.getBean(UserService.class);
        userService.getAllUsers();
        List<User> users = userService.getAllUsers();
        for (User u : users) {
            System.out.println(u.getId() + ":" + u.getName());
        }

        userService.getUser(1l);
        User user = userService.getUser(1l);
        System.out.println(user.getId() + ":" + user.getName());

        user.setName("user 1 updated");
        userService.updateUser(user);
        user = userService.getUser(1l);
        System.out.println(user.getId() + ":" + user.getName());

        User newUser = new User();
        newUser.setId(3);
        newUser.setName("User 3");
        userService.saveUser(newUser);
        users = userService.getAllUsers();
        for (User u : users) {
            System.out.println(u.getId() + ":" + u.getName());
        }
    }
}
