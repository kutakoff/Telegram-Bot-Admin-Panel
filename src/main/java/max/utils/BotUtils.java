package max.utils;

import max.service.UtilsService;
import org.springframework.beans.factory.annotation.Autowired;

public class BotUtils {

    @Autowired
    private UtilsService utilsService;

    public String getToken() {
        return utilsService.getBotToken();
    }

    public String getPassword() {
        return utilsService.getPassword();
    }


    public static String availableAdminPanelMessage =
            """
                    Valid commands:\s
                    /get
                    /add
                    /delete""";

    public static boolean isGetEntered = false; //т.к /get требует 2 ввода: /get и (all или id), то требуется внести проверку, что был первый ввод
    public static boolean isAddEntered = false; //т.к /add требует 2 ввода: /add и (name и goldCount), то требуется внести проверку, что был первый ввод
    public static boolean isDeleteEntered = false; //т.к /delete требует 2 ввода: /delete и id, то требуется внести проверку, что был первый ввод
    public static boolean isStartEntered = false; //проверяет, был ли прописан /start
    public static boolean isPasswordEntered = false; //проверяет, был ли введён пароль

}