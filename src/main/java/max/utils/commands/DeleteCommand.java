package max.utils.commands;

import max.service.UserService;

import static max.utils.BotUtils.isDeleteEntered;

public class DeleteCommand {

    public static String deleteCommand(String text, UserService userService) {
        String message;
        if (isDeleteEntered) { //если второй ввод, то добавляет пользователя
            try {
                message = userService.deleteUser(Integer.parseInt(text)); //парсит String в Integer. Если выскачит ошибка, ее поймают.
                isDeleteEntered = false;
            } catch (NumberFormatException e) { //если при парсинге произошла ошибка, обрабатывает ее
                message = "Write user id.";
            }
        } else { //иначе если первый ввод, то даёт доступ ко второму вводу
            isDeleteEntered = true;
            message = "Enter the id you want to delete.";
        }
        return message;
    }
}