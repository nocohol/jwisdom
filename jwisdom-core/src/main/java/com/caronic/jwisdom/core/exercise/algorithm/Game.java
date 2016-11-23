package com.caronic.jwisdom.core.exercise.algorithm;

import java.util.LinkedList;

/**
 * 50 people sit in a cycle. Begin from position 0, when someone whose order is 3 or times of 3,
 * then quit from the cycle. Finally get the position still in the cycle.
 * Created by caronic on 2016/11/7.
 */
public class Game {

    public static void main(String[] args) {
        LinkedList<Integer> cycle = new LinkedList<>();
        for (int i=0; i<50; i++) {
            cycle.add(i);
        }

        int quitPosition = -1;
        while (cycle.size() > 1) {
            quitPosition = (quitPosition + 3) % cycle.size();
            cycle.remove(quitPosition++);
        }

        System.out.println(cycle.get(0));
    }

}
