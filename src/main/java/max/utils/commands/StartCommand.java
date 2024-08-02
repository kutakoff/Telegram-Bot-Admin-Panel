package max.utils.commands;

import static max.utils.BotUtils.isPasswordEntered;
import static max.utils.BotUtils.isStartEntered;

public class StartCommand {

    public static String startCommand() {
        isStartEntered = true;
        String message;
        if (!isPasswordEntered) { //проверяет, был ли введен пароль. Если не был -> требует ввести его
            message = "Enter the password to log in to the admin panel.";
        } else { //если был -> сообщает, что юзер уже авторизирован
            message = "You are already logged in.";
        }
        return message;
    }
}