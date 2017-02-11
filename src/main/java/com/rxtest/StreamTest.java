package com.rxtest;

public class StreamTest {

    public static void main(String[] args) {

        MyNumber myNumber;
        myNumber = ()->123.45;

        System.out.println("fix value: " +myNumber.getValue());

        myNumber = ()->Math.random()*100;
        System.out.println("random value: " +myNumber.getValue());
        System.out.println("another random value: " +myNumber.getValue());




    }
}
