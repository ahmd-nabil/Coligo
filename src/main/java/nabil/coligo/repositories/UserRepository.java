package nabil.coligo.repositories;

import nabil.coligo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Ahmed Nabil
 */
public interface UserRepository extends JpaRepository<User, Long> {
}
