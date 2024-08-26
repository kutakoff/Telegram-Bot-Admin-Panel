package max.configuration;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.request.SendMessage;
import max.entity.Session;
import max.service.CommandService;
import max.service.SessionService;
import max.utils.BotUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalTime;

@Configuration
public class BotConfiguration {

    private final SessionService sessionService;

    public BotConfiguration(SessionService sessionService) {
        this.sessionService = sessionService;
    }

    @Bean
    public TelegramBot telegramBot(CommandService commandService, BotUtils botUtils) {
        TelegramBot telegramBot = new TelegramBot(botUtils.getToken());
        telegramBot.setUpdatesListener(updates -> {
            updates.forEach(update -> {
                if (update.message() != null && update.message().chat() != null) {
                    Long chatId = update.message().chat().id();
                    String text = update.message().text();
                    Timestamp date = new Timestamp(System.currentTimeMillis());
                    String message;
                    if (text != null) {
                        message = commandService.getCommandMessage(new Session(String.valueOf(chatId), date), text, botUtils.getPassword());
                    } else { //если пользователь сделал какое-то действие, помимо отправки текста, например: отправил стикер, аудио, видео, закрепил сообщение и т.д(обозначается, как text = null), то выводит сообщение
                        message = "Please stop breaking the bot, you won't succeed.";
                    }
                    telegramBot.execute(new SendMessage(chatId, message));
                }
            });
            return UpdatesListener.CONFIRMED_UPDATES_ALL;
        });
        return telegramBot;
    }

    @Bean
    public CommandService commandService() {
        return new CommandService();
    }

    @Bean
    public BotUtils botUtils() {
        return new BotUtils();
    }
}