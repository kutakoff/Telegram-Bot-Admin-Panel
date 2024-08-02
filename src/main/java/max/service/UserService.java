package max.service;

import max.entity.User;
import max.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public String getAll() {
        if (!userRepository.findAll().isEmpty()) {
            return userRepository.findAll().toString();
        } else {
            return "The table is empty.";
        }
    }

    public String getOne(int id) {
        if (userRepository.existsById(id)) {
            return userRepository.findById(id).toString();
        } else {
            return "User by " + id + " does not exist.";
        }
    }

    public String addUser(User user, String name) {
        userRepository.save(user);
        return "User \"" + name + "\" was added.";
    }

    public String deleteUser(int id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
            return "User by " + id + " id was deleted.";
        } else {
            return "User by " + id + " does not exist.";
        }
    }
}