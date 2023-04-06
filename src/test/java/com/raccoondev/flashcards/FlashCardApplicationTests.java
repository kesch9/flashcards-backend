package com.raccoondev.flashcards;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.autoconfigure.data.mongo.AutoConfigureDataMongo;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;

@AutoConfigureDataMongo
@SpringBootTest(
    properties = "de.flapdoodle.mongodb.embedded.version=5.0.5",
    classes = {FlashCardsApplication.class}
)
@EnableAutoConfiguration()
@DirtiesContext
@ActiveProfiles({"test"})
public class FlashCardApplicationTests {

}
