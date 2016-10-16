package com.caronic.jwisdom.core.exercise.base.java8.lambda;

import java.lang.annotation.*;

/**
 * Created by caronic on 2016/10/16.
 */
public class RepeatAnnotations {

    @Target(ElementType.TYPE)
    @Retention(RetentionPolicy.RUNTIME)
    public @interface Filters {
        Filter[] value();
    }

    @Target(ElementType.TYPE)
    @Retention(RetentionPolicy.RUNTIME)
    @Repeatable(Filters.class)
    public @interface Filter {
        String value();
    }

    @Filter(value = "filter1")
    @Filter(value = "filter2")
    public class MyClass {

    }

    public static void main(String[] args) {
        Filter[] annotations = MyClass.class.getAnnotationsByType(Filter.class);
        for(Filter filter : annotations) {
            System.out.println(filter.value());
        }
    }

}
