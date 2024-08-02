package max.service;

import org.springframework.beans.factory.annotation.Autowired;

import static max.utils.BotUtils.isStartEntered;
import static max.utils.BotUtils.isPasswordEntered;
import static max.utils.BotUtils.PASSWORD;
import static max.utils.BotUtils.isGetEntered;
import static max.utils.BotUtils.isAddEntered;
import static max.utils.BotUtils.isDeleteEntered;
import static max.utils.BotUtils.availableAdminPanelMessage;
import static max.utils.commands.AddCommand.addCommand;
import static max.utils.commands.DeleteCommand.deleteCommand;
import static max.utils.commands.GetCommand.getCommand;
import static max.utils.commands.PasswordCommand.passwordCommand;
import static max.utils.commands.StartCommand.startCommand;

public class CommandService {

    @Autowired
    private UserService userService;

    public String getCommandMessage(String text) {
        String message;
        if (text.equalsIgnoreCase("/start")) {
            message = startCommand();
        } else if (isStartEntered) { //проверяет, был ли ввод "/start". Если был -> требует ввести пароль. Если не был -> требует ввести "/start"
            if (!isPasswordEntered && !text.equals(PASSWORD)) { //если пароль не был введен и пароль неправильный, требует ввести его ещё раз.
                message = "Invalid password. Try again.";
            } else { //если пароль был введён, открывает доступ к админ панели
                if ((text.equalsIgnoreCase("/get") || isGetEntered) && (!isAddEntered && !isDeleteEntered)) { //т.к требует 2 ввода: /get и (all или id), то требуется внести проверку, что был первый ввод(isGetEntered). Если он был, то второй раз заходит сюда же и заканчивает ввод. Так же проверяет, что не активен второй ввод у /add и /delete.
                    message = getCommand(text, userService);
                } else if ((text.equalsIgnoreCase("/add") || isAddEntered) && (!isGetEntered && !isDeleteEntered)) { //т.к требует 2 ввода: /add и (name и goldCount), то требуется внести проверку, что был первый ввод(isAddEntered). Если он был, то второй раз заходит сюда же и заканчивает ввод. Так же проверяет, что не активен второй ввод у /get и /delete.
                    message = addCommand(text, userService);
                } else if ((text.equalsIgnoreCase("/delete") || isDeleteEntered) && (!isGetEntered && !isAddEntered)) { //т.к требует 2 ввода: /delete и id, то требуется внести проверку, что был первый ввод(isDeleteEntered). Если он был, то второй раз заходит сюда же и заканчивает ввод. Так же проверяет, что не активен второй ввод у /get и /add.
                    message = deleteCommand(text, userService);
                } else if (isPasswordEntered) { // если юзер не ввел ни 1 из вариантов: /get, /add, /delete, то проверяет, был ли введен пароль. Если был -> отправляет сообщение с доступными командами. Если не был -> даёт ввести пароль.
                    message = availableAdminPanelMessage;
                } else {
                    message = passwordCommand();
                }
            }
        } else {
            message = "Enter the command \"/start\""; //если пользователь не запускает программу при помощи /start, то просит ввести /start.
        }
        return message;
    }
}