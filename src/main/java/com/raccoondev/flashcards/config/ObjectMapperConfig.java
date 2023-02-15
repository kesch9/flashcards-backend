/*
 *  =======================================================================
 *
 *  Copyright (c) 2021 Northern Capital Gateway, LLC. All rights reserved.
 *
 *  This software is the confidential and proprietary information of
 *  Northern Capital Gateway, LLC.
 *  You shall not disclose such Confidential Information and shall use it only in
 *  accordance with the terms of the license agreement you entered into with
 *  Northern Capital Gateway, LLC
 *
 *  =======================================================================
 */

package com.raccoondev.flashcards.config;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@AllArgsConstructor
public class ObjectMapperConfig {
    @Bean
    public ObjectMapper objectMapper() {
        final ObjectMapper result = new ObjectMapper();
        result.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        result.configure(DeserializationFeature.ACCEPT_FLOAT_AS_INT, false);
        result.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        result.registerModule(new Jdk8Module());
        result.registerModule(new JavaTimeModule());
        result.registerModule(new ParameterNamesModule());
        return result;
    }
}
