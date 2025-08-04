package config;

import logic.factory.GameFactory;
import logic.storage.WordStorage;
import storage.FileWordStorage;
import view.ConsoleView;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Bean;

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
