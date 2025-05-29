import view.ConsoleView;

import java.io.IOException;

public class WordleApp {
    public static void main(String args[]) {
        start();
    }

    static void start() {
        try {
            ConsoleView view = new ConsoleView();
            view.menu();
        }  catch (IOException e) {
            System.out.println("Input Error! Ending game...");
        } catch (Exception e) {
            System.out.println("Something went wrong... " + e.getMessage());
        }
    }
}