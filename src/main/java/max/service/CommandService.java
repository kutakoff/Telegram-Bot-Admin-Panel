package max.service;

import max.entity.Session;
import org.springframework.beans.factory.annotation.Autowired;

import static max.utils.BotUtils.isGetEntered;
import static max.utils.BotUtils.isAddEntered;
import static max.utils.BotUtils.isDeleteEntered;
import static max.utils.BotUtils.availableAdminPanelMessage;

public class CommandService {

    @Autowired
    private UserService userService;

    @Autowired
    private SessionService sessionService;

    public String getCommandMessage(Session session, String text, String password) {
        String message;
        Commands commands = new Commands(userService, sessionService);
        if (sessionService.isSessionActive(session)) { //проверяет, активна ли сессия. Если активна, даёт доступ к админ панели
            if ((text.equalsIgnoreCase("/get") || isGetEntered) && (!isAddEntered && !isDeleteEntered)) { //т.к требует 2 ввода: /get и (all или id), то требуется внести проверку, что был первый ввод(isGetEntered). Если он был, то второй раз заходит сюда же и заканчивает ввод. Так же проверяет, что не активен второй ввод у /add и /delete.
                message = commands.getCommand(text);
            } else if ((text.equalsIgnoreCase("/add") || isAddEntered) && (!isGetEntered && !isDeleteEntered)) { //т.к требует 2 ввода: /add и (name и goldCount), то требуется внести проверку, что был первый ввод(isAddEntered). Если он был, то второй раз заходит сюда же и заканчивает ввод. Так же проверяет, что не активен второй ввод у /get и /delete.
                message = commands.addCommand(text);
            } else if ((text.equalsIgnoreCase("/delete") || isDeleteEntered) && (!isGetEntered && !isAddEntered)) { //т.к требует 2 ввода: /delete и id, то требуется внести проверку, что был первый ввод(isDeleteEntered). Если он был, то второй раз заходит сюда же и заканчивает ввод. Так же проверяет, что не активен второй ввод у /get и /add.
                message = commands.deleteCommand(text);
            } else {
                message = availableAdminPanelMessage;
            }
        } else if (text.equalsIgnoreCase("/start")) {
            message = "Please, enter the password.";
        } else if (!text.equals(password)) {
            message = "Password is incorrect. Try again.";
        } else {
            message = commands.passwordCommand(session); //пользователь вводит правильный пароль
        }
        return message;
    }
}