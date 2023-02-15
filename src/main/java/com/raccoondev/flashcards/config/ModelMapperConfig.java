package com.raccoondev.flashcards.config;

import java.util.List;
import java.util.function.Function;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper getMapper() {
        return new ModelMapper();
    }

    public static <R, E> List<R> convertList(List<E> list, Function<E, R> converter) {
        return list.stream().map(converter).toList();
    }
}
