package com.mycompany.app;

/**
 * Hello world!
 */
public class App {

    private static final String MESSAGE = "Hello World!";

    public App() {}

    public static void main(String[] args) {
        int i = 0;
        while(true)
        {
            System.out.println(MESSAGE + " - " + i);
            i++;
            Thread.sleep(5000);
        }

    }

    public String getMessage() {
        return MESSAGE;
    }
}
