package com.raccoondev.flashcards.controller;

import com.raccoondev.flashcards.dto.WordDto;
import com.raccoondev.flashcards.service.WordService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/english")
public class EnglishController {

    private final WordService englishWordService;

    private final ModelMapper modelMapper;

    @GetMapping(value = "/word")
    public WordDto getWord(@RequestParam String name) {
        return modelMapper.map(englishWordService.findByWord(name), WordDto.class);
    }
}
