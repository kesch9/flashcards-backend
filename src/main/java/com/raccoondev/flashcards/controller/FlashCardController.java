package com.raccoondev.flashcards.controller;

import com.raccoondev.flashcards.document.FlashCard;
import com.raccoondev.flashcards.dto.FlashCardDto;
import com.raccoondev.flashcards.service.FlashCardService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/cards")
public class FlashCardController {

    private final FlashCardService flashCardService;

    @GetMapping
    public List<FlashCard> getCard() {
        return flashCardService.findAll();
    }

    @GetMapping(value = "/{theme}")
    public FlashCard getCardByTheme(@PathVariable String theme) {
        return flashCardService.findByTheme(theme);
    }

    @PostMapping(value = "/addCard")
    public FlashCard addFlashCard(@RequestBody FlashCardDto flashCard) {
        return flashCardService.insert(flashCard);
    }

    @PutMapping(value = "/updateCard")
    public FlashCard updateFlashCard(@RequestBody FlashCardDto flashCard) {
        return flashCardService.update(flashCard);
    }
}
