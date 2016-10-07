package com.caronic.jwisdom.core.exercise.spring.aop;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Created by caronic on 2016/9/25.
 */
//@SpringBootApplication
public class Application{

    public static void main(String[] args) {
        //test git
//        SpringApplication.run(Application.class, args);
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        TargetObject object = context.getBean(TargetObject.class);
        object.doSomething();
        try {object.doSomethingWrong();}
        catch (Exception e) {
            System.out.println("Exception is still thrown to the caller");
        }

    }

//    @Override
//    public void run(String... args) throws Exception {
//        targetObject.doSomething();
//    }
}
