package com.nekochanoide.univerrestexam.configurations;

import com.nekochanoide.univerrestexam.controllers.WidgetController;
import com.nekochanoide.univerrestexam.models.Widget;
import com.nekochanoide.univerrestexam.repositories.WidgetRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataMocker {
    @Bean
    CommandLineRunner initDatabase(WidgetRepository repository) {
        return args -> {
            EnsureBasicWidget(repository, WidgetController.WeatherWidget, "-30", "Солнце");
            EnsureBasicWidget(repository, WidgetController.SeriesWidget, "Friends", "season 2");
            EnsureBasicWidget(repository, WidgetController.CongratulationWidget, "Новый год", "Счастья! Здоровья!");
        };
    }

    void EnsureBasicWidget(WidgetRepository repository, String name, String value1, String value2) {
        if (repository.findWidgetByName(name).isEmpty()) {
            repository.save(new Widget(name, value1, value2));
        }
    }
}
