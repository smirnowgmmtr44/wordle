
import config.SpringConfig;
import view.ConsoleView;

import java.io.IOException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class WordleApp {
    public static void main(String args[]) {
        start();
    }

    static void start() {
        ApplicationContext ctx = new AnnotationConfigApplicationContext(SpringConfig.class);
        try {
            ConsoleView view = (ConsoleView) ctx.getBean(ConsoleView.class);
            view.menu();
        }  catch (IOException e) {
            System.out.println("Input Error! Ending game...");
        } catch (Exception e) {
            System.out.println("Something went wrong... " + e.getMessage());
        }
    }
}