package nabil.coligo.repositories;

import nabil.coligo.model.Announcement;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Ahmed Nabil
 */
public interface AnnouncementRepository extends JpaRepository<Announcement, Long> {
}
