package com.sajjadamin.calculator;

public class Main {
    public static void main(String[] args) {
        new Thread(GUI::new).start();
    }
}
