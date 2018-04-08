package com.caronic.jwisdom.core.exercise.base;

/**
 * This is demo is to show that java passes variable parameters as their value.
 * There are two kinds of variables in java, one is primitive such like int, long, float ...
 * the other one is reference, such like String, MyClass ...
 * When pass with primitive variable, java will copy its value in method call frame,
 * When pass with reference variable, java will copy its reference address in method call frame.
 * Created by caronic on 2016/9/3.
 */
public class ReferenceParameterDemo {

    public static void main(String[] args) {
        MyClass myClass = new MyClass("Nicholas", 31);
        changeValue(myClass);
        System.out.println(myClass.getName() + " " + myClass.getAge()); //Expected output is "He Fei 30"

        changeValueWithFinal(myClass);

        MyClass myClass1 = new MyClass("nocohol", 30);
        swap(myClass, myClass1);
        System.out.println(myClass.getName() + " " + myClass.getAge()); //Expected output is "He Fei 30";
        System.out.println(myClass1.getName() + " " + myClass1.getAge()); //Expected output is "nocohol 30";

        myClass = changeAndReturn(myClass);
        System.out.println(myClass.getName() + " " + myClass.getAge()); //Expected output is "He Fei 30";

        MyClass myClass2 = null;
        MyClass myClass3 = changeAndReturn(myClass2);
        System.out.println(myClass2 == null);
        System.out.println(myClass3 == null);
    }

    public static void changeValue(MyClass myClass) {
        if (myClass == null)
            myClass = new MyClass();
        myClass.setName("He Fei");
        myClass.setAge(30);
    }

    public static void changeValueWithFinal(final MyClass myClass) {
        myClass.setAge(29);
        myClass.setName("Nicholas He");
    }

    public static MyClass changeAndReturn(MyClass myClass) {
        if(myClass == null)
            myClass = new MyClass();
        myClass.setName("He Fei");
        myClass.setAge(30);
        return myClass;
    }

    public static void swap(MyClass myClass1, MyClass myClass2) {
        MyClass tmp = myClass1;
        myClass1 = myClass2;
        myClass2 = tmp;
    }

    static class MyClass {
        private String name;
        private int age;

        public MyClass() {

        }

        public MyClass(String name, int age) {
            this.name = name;
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }
    }

}
