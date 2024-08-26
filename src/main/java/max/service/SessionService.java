package max.service;

import max.entity.Session;
import max.repository.SessionRepository;
import org.springframework.stereotype.Service;

@Service
public class SessionService {

    private final SessionRepository sessionRepository;

    public SessionService(SessionRepository sessionRepository) {
        this.sessionRepository = sessionRepository;
    }

    public boolean isSessionActive(Session thisSession) {
        if (sessionRepository.findSessionByChatId(thisSession.getChatId()) != null) { //проверяет, есть ли сессия по chatId
            Session dbSession = sessionRepository.findSessionByChatId(thisSession.getChatId()); //сессия по chatId в БД
            if ((thisSession.getDate().getTime() - dbSession.getDate().getTime()) / 1000 / 60 < 10) { //если от старой сессии в бд не прошло 10 минут, то возвращаем true и пересоздаём сессию в бд, чтоб обноввить время
                sessionRepository.deleteById(sessionRepository.findSessionByChatId(thisSession.getChatId()).getId());
                createSession(thisSession);
                return true;
            } else {
                sessionRepository.deleteById(sessionRepository.findSessionByChatId(thisSession.getChatId()).getId()); //если прошло больше 10 минут с момента прошлой сессии, то сессия удаляется
                return false;
            }
        } else {
            return false; //если нет в бд нет сессии по chatId, возвращает false
        }
    }

    public void createSession(Session session) {
        sessionRepository.save(session);
    }
}