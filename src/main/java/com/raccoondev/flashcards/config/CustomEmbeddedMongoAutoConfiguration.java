package com.raccoondev.flashcards.config;

import de.flapdoodle.embed.mongo.spring.autoconfigure.EmbeddedMongoAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Profile("test")
@Configuration
public class CustomEmbeddedMongoAutoConfiguration extends EmbeddedMongoAutoConfiguration {
}
