package com.example;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class PomodoroTimer {

    public static int test = 0;
    public static int size = 30;

    public static void main(String[] args) throws InterruptedException {
        System.out.println("Введите время работы и отдыха");
        //test input: -w 2 -b 3 -c 3 -m 2
        String[] input = new Scanner(System.in).nextLine().split(" ");

        int workTime = 1;
        int breakTime = 1;
        int count = 1;
        int multiplier = 1;

        boolean isHelp = false;

        for(int i = 0; i < input.length; i++) {
            switch (input[i]) {
                case "-w" -> workTime = Integer.parseInt(input[++i]);
                case "-b" -> breakTime = Integer.parseInt(input[++i]);
                case "-c" -> count = Integer.parseInt(input[++i]);
                case "-m" -> multiplier = Integer.parseInt(input[++i]);
                case "--help" -> {
                    System.out.println(
                            "\n\nPomodoro - сделай свое время более эффективным\n");
                    System.out.println(
                            "	-w <workTime>: время работы (сколько хочешь работать)\n");
                    System.out.println(
                            "	-b <breakTime>: время отдыха (сколько хочешь отдыхать)\n");
                    System.out.println(
                            "	-c <count>: количество итераций.\n");
                    System.out.println(
                            "	-m <multiplier>: множитель времени работы\n");
                    System.out.println(
                            "	--help: меню помощи.\n");
                    isHelp = true;
                }
            }
        }
        if(isHelp) return;

        long startTime = System.currentTimeMillis();

        for(int i = 0; i <= count; i++) {
            timer(workTime, breakTime);
            workTime = workTime * multiplier;
        }

        long endTime = System.currentTimeMillis();

        System.out.println("Таймер работал " + (endTime - startTime) / (1000 * 60) + " min");

    }

    public static void timer(int workTime, int breakTime) throws InterruptedException {
        printProgress("Work Progress::  ", workTime, size);
        printProgress("Break Progress:: ", breakTime, size);
    }

    private static void printProgress(String process, int time, int size) throws InterruptedException {
        int length;
        int rep;
        length = 60 * time / size;
        rep = 60 * time /length;
        int stretchb = size / (3 * time);
        for(int i = 1; i <= rep; i++) {
            double x = i;
            x = 1.0/3.0 *x;
            x *= 10;
            x = Math.round(x);
            x /= 10;
            double w = time *stretchb;
            double percent = (x/w) *1000;
            x /=stretchb;
            x *= 10;
            x = Math.round(x);
            x /= 10;
            percent = Math.round(percent);
            percent /= 10;
            System.out.print(process + percent+"% " + (" ").repeat(5 - (String.valueOf(percent).length())) +"[" + ("#").repeat(i) + ("-").repeat(rep - i)+"]    ( " + x +"min / " + time +"min )"+  "\r");
            if(test == 0){
                TimeUnit.SECONDS.sleep(length);
            }
        }
        System.out.println();
    }

}