package config;

import logic.model.GameFactory;
import logic.storage.WordStorage;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Bean;
import storage.FileWordStorage;
import view.ConsoleView;

@Configuration
public class SpringConfig {
    @Bean
    public ConsoleView viewBean() {
        return new ConsoleView();
    }

    @Bean
    public GameFactory gameBean() {
        return new GameFactory(storageBean());
    }

    @Bean
    public WordStorage storageBean() {
        return new FileWordStorage();
    }
}
