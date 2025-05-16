import view.ConsoleView;

public class WordleApp {
    public static void main(String args[]) {
        start();
    }

    static void start() {
        try {
            ConsoleView view = new ConsoleView();
            view.menu();
        } catch (Exception e) {
            System.out.println("Something went wrong...");
        }
    }
}