package main.java.config;

import main.java.logic.factory.GameFactory;
import main.java.logic.storage.WordStorage;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Bean;
import main.java.storage.FileWordStorage;
import main.java.view.ConsoleView;

import java.util.Scanner;

@Configuration
public class SpringConfig {
    @Bean
    public Scanner scannerBean() {
        return new Scanner(System.in);
    }

    @Bean
    public GameFactory gameBean() {
        return new GameFactory(storageBean());
    }

    @Bean
    public ConsoleView viewBean() {
        return new ConsoleView(gameBean(), scannerBean());
    }

    @Bean
    public WordStorage storageBean() {
        return new FileWordStorage();
    }
}
