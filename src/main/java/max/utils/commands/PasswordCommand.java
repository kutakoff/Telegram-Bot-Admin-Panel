package max.utils.commands;

import static max.utils.BotUtils.isPasswordEntered;
import static max.utils.BotUtils.availableAdminPanelMessage;

public class PasswordCommand {

    public static String passwordCommand() {
        isPasswordEntered = true;
        return "Welcome to the admin panel. " + availableAdminPanelMessage;
    }
}