package com.raccoondev.flashcards.repository;

import com.raccoondev.flashcards.document.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {
}

