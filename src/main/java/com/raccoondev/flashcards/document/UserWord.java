package com.raccoondev.flashcards.document;

import com.raccoondev.flashcards.dto.WordDto;
import java.util.List;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserWord extends WordDto {
    private boolean isLearned;
    List<UserWord> words;
}
