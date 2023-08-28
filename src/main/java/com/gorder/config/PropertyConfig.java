package com.gorder.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;

import javax.annotation.PostConstruct;

@Configuration
public class PropertyConfig {

    private static MessageSource messageSource;

    @PostConstruct
    public void init() {
        messageSource = messageSource();
    }

    public static MessageSource getMessageSource() {
        return messageSource;
    }

    @Bean
    public MessageSource messageSource() {
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setBasename("properties/message");
        messageSource.setDefaultEncoding("UTF-8");
        return messageSource;
    }

}
