package com.raccoondev.flashcards.repository;

import org.assertj.core.api.Assertions;
import org.bson.Document;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.data.mongodb.core.MongoTemplate;

@DataMongoTest
class MongoTemplateTests {
    @Test
    void example(@Autowired final MongoTemplate mongoTemplate) {
        Assertions.assertThat(mongoTemplate.getDb()).isNotNull();
        mongoTemplate.getDb().createCollection("deleteMe");
        long count = mongoTemplate.getDb().getCollection("deleteMe").countDocuments(Document.parse("{}"));
        Assertions.assertThat(mongoTemplate.getDb()).isNotNull();
        Assertions.assertThat(count).isZero();
    }
}
