package com.mycompany.app;

/**
 * Hello world!
 */
public class App {

    private static final String MESSAGE = "Hello World!!";

    public App() {}

    public static void main(String[] args) {
        try {
            //int i = 0;
            //while(true)
            for(int i = 0; i<= 100 ; i++)
            {
                System.out.println(MESSAGE + " - v2- " + i);
                Thread.sleep(5000);
            //    i++;
            }
        }catch(InterruptedException e) {
			System.out.println(e);
            Thread.currentThread().interrupt();
		}
    }

    public String getMessage() {
        return MESSAGE;
    }
}
