package org.statisticModelling;

import java.io.BufferedReader;
import java.io.InputStreamReader;


//ВАРИАНТ : 8


public class Main {
    public static void main(String[] args) {
        System.out.println("Starting lab2 program...");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        new ProgramScheduler(System.out, br).start();
    }
}