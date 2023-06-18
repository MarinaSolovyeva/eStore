package com.example.estote.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@Data
@PropertySource("application.properties")
public class TelegramConfig {

    @Value("${bot.name}")
    String name;

    @Value("${bot.token}")
    String token;
}
