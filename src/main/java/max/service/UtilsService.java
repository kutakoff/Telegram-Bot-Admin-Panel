package max.service;

import max.repository.UtilsRepository;
import org.springframework.stereotype.Service;

@Service
public class UtilsService {

    private final UtilsRepository utilsRepository;

    public UtilsService(UtilsRepository utilsRepository) {
        this.utilsRepository = utilsRepository;
    }

    public String getBotToken() {
        return utilsRepository.findAll().toString().split("/")[0].split("\\[")[1];
    }

    public String getPassword() {
        return utilsRepository.findAll().toString().split("/")[1].split("]")[0];
    }
}