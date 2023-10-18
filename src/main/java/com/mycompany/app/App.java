package com.mycompany.app;

/**
 * Hello world!
 */
public class App {

    private static final String MESSAGE = "Hello World!";

    public App() {}

    public static void main(String[] args) {
        try {
            int i = 0;
            while(true)
            {
                System.out.println(MESSAGE + " - v2- " + i);
                Thread.sleep(5000);
                i++;
            }
        }catch(Exception e) {
			System.out.println(e);
		}
    }

    public String getMessage() {
        return MESSAGE;
    }
}
