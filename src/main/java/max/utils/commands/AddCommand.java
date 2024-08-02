package max.utils.commands;

import max.entity.User;
import max.service.UserService;

import static max.utils.BotUtils.isAddEntered;

public class AddCommand {

    public static String addCommand(String text, UserService userService) {
        String message;
        if (isAddEntered) { //если второй ввод, то добавляет пользователя
            String[] components = text.split(" "); //разбивает текст юзера на 2 компонента: name и goldCount
            try {
                if (components.length > 2) {
                    throw new ArrayIndexOutOfBoundsException(); //если юзер ввел более двух компонентов, кидает рандомную ошибку. Ее поймают.
                }
                User user = new User(components[0], Integer.parseInt(components[1])); //парсит String в Integer. Если выскачит ошибка, ее поймают.
                message = userService.addUser(user, components[0]);
                isAddEntered = false;
            } catch (NumberFormatException e) { //если при парсинге произошла ошибка, обрабатывает ее
                message = "Enter the amount of gold in numbers";
            } catch (Exception e) {
                message = "Write 2 components for the supplement separated by a space: name goldCount. For example: maxim 10435";
            }
        } else { //иначе если первый ввод, то даёт доступ ко второму вводу
            isAddEntered = true;
            message = "Write 2 components for the supplement separated by a space: name goldCount. For example: maxim 10435";
        }
        return message;
    }
}