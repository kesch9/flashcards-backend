package com.raccoondev.flashcards.document;

import jakarta.validation.constraints.NotNull;
import java.util.List;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;
import org.springframework.data.mongodb.core.mapping.MongoId;

@Data
@Builder
@Document(collection = "cards")
public class FlashCard {

    @MongoId
    private String id;

    @NotNull
    private String theme;

    @DocumentReference(lookup = "{ 'word' : ?#{#target} }")
    List<WordItem> words;
}
