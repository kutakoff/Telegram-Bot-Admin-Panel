package max.service;

import max.entity.Session;
import max.entity.User;

import static max.utils.BotUtils.*;
import static max.utils.BotUtils.isGetEntered;

public class Commands {

    private final UserService userService;

    private final SessionService sessionService;

    public Commands(UserService userService, SessionService sessionService) {
        this.userService = userService;
        this.sessionService = sessionService;
    }

    public String addCommand(String text) {
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

    public String deleteCommand(String text) {
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

    public String getCommand(String text) {
        String message;
        if (isGetEntered) { //если второй ввод, то добавляет пользователя
            if (text.equalsIgnoreCase("all")) {
                message = userService.getAll();
                isGetEntered = false;
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

    public String passwordCommand(Session session) {
        sessionService.createSession(session);
        return "Welcome to the admin panel. " + availableAdminPanelMessage;
    }

/*    public String startCommand(Session session) {
        String message;
        if (sessionService.isSessionActive(session)) {
            message = "You are already logged in.";
        } else {
            message = "Please, enter the password.";
        }
        return message;
    }*/
}