package com.caronic.jwisdom.core.exercise.spring.data;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Created by caronic on 2016/10/5.
 */
public class Application {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        UserRepository userRepository = context.getBean(UserRepository.class);
        User user1 = new User();
        user1.setId(2);
        user1.setName("he fei");
        userRepository.saveUser(user1);
        User user2 = userRepository.getUser(user1.getId());
        System.out.println(user2.getName());
    }
}
