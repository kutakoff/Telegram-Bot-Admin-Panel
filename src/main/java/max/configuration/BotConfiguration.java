package max.configuration;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.request.SendMessage;
import max.service.CommandService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static max.utils.BotUtils.TOKEN;

@Configuration
public class BotConfiguration {

    @Bean
    public TelegramBot telegramBot(CommandService commandService) {
        TelegramBot telegramBot = new TelegramBot(TOKEN);
        telegramBot.setUpdatesListener(updates -> {
            updates.forEach(update -> {
                if (update.message() != null && update.message().chat() != null) {
                    Long chatId = update.message().chat().id();
                    String text = update.message().text();
                    String message;
                    if (text != null) {
                        message = commandService.getCommandMessage(text);
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
}