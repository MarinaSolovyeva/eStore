package com.example.estote.service;

import com.example.estote.config.TelegramConfig;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.jdt.internal.compiler.IErrorHandlingPolicy;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Slf4j
@Component
public class TelegramBotService extends TelegramLongPollingBot {

    private final TelegramConfig telegramConfig;

    public TelegramBotService(TelegramConfig telegramConfig) {
        this.telegramConfig = telegramConfig;
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            String messageText = update.getMessage().getText();
            long chatID = update.getMessage().getChatId();

            switch (messageText) {
                case "/start":
                    startCommandReceived(chatID, update.getMessage().getChat().getFirstName());
                    break;
                default:
                    sendMessage(chatID, "Извини, но на данный момент я не могу тебе помочь :( ");
                    break;
            }
        }
    }

    public String getBotToken() {
        return telegramConfig.getToken();
    }

    @Override
    public String getBotUsername() {
        return telegramConfig.getName();
    }

    private void startCommandReceived(long chatID, String name) {
        String answer = "Привет, " + name + "! Я чат-бот магазина DweN. Чем я могу тебе помочь?";

        sendMessage(chatID, answer);
    }

    private void sendMessage(long chatId, String textToSend) {
        SendMessage message = new SendMessage();
        message.setChatId(String.valueOf(chatId));

        message.setText(textToSend);

        try {
            execute(message);
        } catch (TelegramApiException e) {
            log.error("Error occurred: " + e.getMessage());
        }
    }
}
