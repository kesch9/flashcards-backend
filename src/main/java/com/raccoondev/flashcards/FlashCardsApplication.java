package com.raccoondev.flashcards;

import de.flapdoodle.embed.mongo.spring.autoconfigure.EmbeddedMongoAutoConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(exclude = {EmbeddedMongoAutoConfiguration.class})
public class FlashCardsApplication {

    public static void main(String[] args) {
        SpringApplication.run(FlashCardsApplication.class, args);
    }
}
