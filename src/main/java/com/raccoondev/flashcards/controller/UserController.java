package com.raccoondev.flashcards.controller;

import com.raccoondev.flashcards.document.User;
import com.raccoondev.flashcards.service.UserService;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @PostMapping(value = "/addUser")
    public User addUser(@RequestBody User user) {
        return userService.addUser(user);
    }

    @GetMapping(value = "/{email}")
    public User addUser(@PathVariable String email) {
        return userService.getUserByEmail(email);
    }

    @GetMapping(value = "/{email}/learn")
    public User learn(@PathVariable String email, @RequestParam Optional<String> flashCardTheme,
        @RequestParam Optional<String> word) {
        if (flashCardTheme.isPresent()) {
            return userService.learnFlashCard(email, flashCardTheme.get());
        }
        if (word.isPresent()) {
            return userService.learnWord(email, word.get());
        }
        return userService.getUserByEmail(email);
    }
}
