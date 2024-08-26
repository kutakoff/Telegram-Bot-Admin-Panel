package max.repository;

import max.entity.Utils;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UtilsRepository extends JpaRepository<Utils, Integer> {
}