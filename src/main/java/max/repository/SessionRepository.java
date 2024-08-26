package max.repository;

import max.entity.Session;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SessionRepository extends JpaRepository<Session, Integer> {

    /**
     *
     * @param chatId
     * @return
     */
    Session findSessionByChatId(String chatId);
}