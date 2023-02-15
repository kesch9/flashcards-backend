package com.raccoondev.flashcards.document;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "english")
public class WordItem {

    @Id
    private String id;

    private String word;

    private String transcription;

    private String[] translates;
}
