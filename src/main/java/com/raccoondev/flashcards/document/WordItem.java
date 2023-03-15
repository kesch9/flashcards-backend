package com.raccoondev.flashcards.document;

import jakarta.validation.constraints.NotNull;
import java.util.List;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.mongodb.core.index.TextIndexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

@Data
@Builder
@Document(collection = "english")
public class WordItem {

    @MongoId
    private String id;
    @TextIndexed
    @NotNull
    private String word;
    private String transcription;

    private List<String> translates;
}
