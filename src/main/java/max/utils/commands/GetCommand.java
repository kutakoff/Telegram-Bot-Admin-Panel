package max.utils.commands;

import max.service.UserService;

import static max.utils.BotUtils.isGetEntered;

public class GetCommand {

    public static String getCommand(String text, UserService userService) {
        String message;
        if (isGetEntered) { //если второй ввод, то добавляет пользователя
            if (text.equalsIgnoreCase("all")) {
                message = userService.getAll();
            } else {
                try {
                    message = userService.getOne(Integer.parseInt(text)); //парсит String в Integer. Если выскачит ошибка, ее поймают.
                    isGetEntered = false;
                } catch (NumberFormatException e) { //если при парсинге произошла ошибка, обрабатывает ее
                    message = "Enter \"all\" or specific id.";
                }
            }
        } else { //иначе если первый ввод, то даёт доступ ко второму вводу
            isGetEntered = true;
            message = "Enter \"all\" or specific id.";
        }
        return message;
    }
}