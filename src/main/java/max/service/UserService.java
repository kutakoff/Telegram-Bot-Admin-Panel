package max.service;

import max.entity.User;
import max.repository.UserRepository;
import org.springframework.stereotype.Service;

import static max.utils.BotUtils.availableAdminPanelMessage;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public String getAll() {
        String message;
        if (!userRepository.findAll().isEmpty()) {
            message = userRepository.findAll().toString();
        } else {
            message = "The table is empty.";
        }
        return message + "\n\n" + availableAdminPanelMessage;
    }

    public String getOne(int id) {
        String message;
        if (userRepository.existsById(id)) {
            message = userRepository.findById(id).toString();
        } else {
            message = "User by " + id + " does not exist.";
        }
        return message + "\n\n" + availableAdminPanelMessage;
    }

    public String addUser(User user, String name) {
        userRepository.save(user);
        return "User \"" + name + "\" was added." + "\n\n" + availableAdminPanelMessage;
    }

    public String deleteUser(int id) {
        String message;
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
            message = "User by " + id + " id was deleted.";
        } else {
            message = "User by " + id + " does not exist.";
        }
        return message + "\n\n" + availableAdminPanelMessage;
    }
}