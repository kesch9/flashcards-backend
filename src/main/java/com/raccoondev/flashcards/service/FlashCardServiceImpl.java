package com.raccoondev.flashcards.service;

import com.raccoondev.flashcards.document.FlashCard;
import com.raccoondev.flashcards.dto.FlashCardDto;
import com.raccoondev.flashcards.dto.WordDto;
import com.raccoondev.flashcards.repository.FlashCardsRepository;
import com.raccoondev.flashcards.repository.WordRepository;
import java.util.List;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class FlashCardServiceImpl implements FlashCardService {

    private final FlashCardsRepository repository;

    private final WordRepository wordRepository;

    @Override
    public List<FlashCard> findAll() {
        return repository.findAll();
    }

    @Override
    public FlashCard findByTheme(@NonNull String theme) {
        return repository.findByTheme(theme);
    }

    @Override
    public FlashCard insert(final FlashCardDto dto) {
        var allByWord = wordRepository.findAllByWordIn(dto.getWords().stream().map(WordDto::getWord).toList());
        var flashCard = FlashCard.builder().theme(dto.getTheme()).words(allByWord).build();
        return repository.insert(flashCard);
    }

    @Override
    public FlashCard update(final FlashCardDto dto) {
        log.info("input {}", dto);
        var byId = repository.findById(dto.getId());
        if (byId.isEmpty()) {
            return null;
        }
        var flashCard = byId.get();
        flashCard.setTheme(dto.getTheme());
        var allByWord = wordRepository.findAllByWordIn(dto.getWords().stream().map(WordDto::getWord).toList());
        flashCard.setWords(allByWord);
        return flashCard;
    }
}
