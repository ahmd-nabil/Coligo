package nabil.coligo.repositories;

import nabil.coligo.model.Announcement;
import nabil.coligo.model.Role;
import nabil.coligo.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Ahmed Nabil
 */
@DataJpaTest
class AnnouncementRepositoryTest {

    @Autowired
    AnnouncementRepository announcementRepository;

    @Autowired
    UserRepository userRepository;

    Announcement announcement;
    User user;
    @BeforeEach
    void setUp() {
        user = User.builder()
                .email("ahmednabil@gmail.com")
                .firstName("Ahmed")
                .lastName("Nabil")
                .password("Whatever")
                .role(Role.STUDENT)
                .build();

        announcement = Announcement.builder()
                .content("Whatever announcement description")
                .build();
    }

    @Test
    void testSaveAnnouncement() {
        Announcement savedAnnouncement = announcementRepository.save(announcement);
        User savedUser = userRepository.save(user);
        savedUser.addAnnouncement(savedAnnouncement);
        userRepository.flush();
        assertThat(userRepository.findAll().get(0).getAnnouncements().size()).isEqualTo(1);
    }
}