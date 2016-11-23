package com.caronic.jwisdom.core.exercise.algorithm;

import java.util.HashSet;
import java.util.Random;

/**
 * Created by caronic on 2016/11/7.
 */
public class OrderRandomCharacters {
    public static void main(String[] args) {
        Random random = new Random();
        HashSet<Character> characters = new HashSet<>();
        while (characters.size() < 20) {
            int randomInt = Math.abs(random.nextInt()%26);
            System.out.println(randomInt);
            char c = (char)(randomInt + 97);
            characters.add(c);
        }
        characters.forEach((e) -> {
            System.out.println(e);
        });
    }
}
