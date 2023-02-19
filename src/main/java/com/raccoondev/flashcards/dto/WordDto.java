package com.raccoondev.flashcards.dto;

import lombok.Data;

@Data
public class WordDto {
    private String id;
    private String word;
    private String transcription;
    private String[] translates;
}
