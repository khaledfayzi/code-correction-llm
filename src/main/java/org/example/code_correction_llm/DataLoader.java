package org.example.code_correction_llm;

import org.example.code_correction_llm.model.Task;
import org.example.code_correction_llm.repository.TaskRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataLoader {

    @Bean
    CommandLineRunner init(TaskRepository repo) {
        return args -> {
            repo.save(new Task("Hello World Aufgabe", "Hello World"));
            repo.save(new Task("Schleifen Aufgabe", "1 2 3 4 5"));


            repo.findAll().forEach(System.out::println);
        };
    }
}