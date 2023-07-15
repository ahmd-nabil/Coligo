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
class UserRepositoryTest {
    @Autowired
    UserRepository userRepository;

    @Autowired
    AnnouncementRepository announcementRepository;

    User user;
    Announcement announcement;
    @BeforeEach
    void setUp() {
        user = User.builder()
                .email("AhmedNabil@gmail.com")
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
    void testUserAndAnnouncementMapping() {
        User savedUser = userRepository.save(user);
        Announcement savedAnnouncement = announcementRepository.save(announcement);
        savedUser.addAnnouncement(savedAnnouncement);
        assertThat(userRepository.findAll().get(0).getAnnouncements().size()).isEqualTo(1);
        assertThat(userRepository.findAll().get(0).getAnnouncements().contains(savedAnnouncement)).isTrue();
        assertThat(announcementRepository.findAll().get(0).getUser()).isNotNull();
    }


    @Test
    void testDeleteUserCascade() {
        // given
        User savedUser = userRepository.save(user);
        Announcement savedAnnouncement = announcementRepository.save(announcement);
        savedUser.addAnnouncement(savedAnnouncement);
        savedUser.getAnnouncements().forEach(System.out::println);
        userRepository.flush();
        // when
        assertThat(announcementRepository.findAll().size()).isEqualTo(1);
        userRepository.delete(savedUser);
        userRepository.flush();
        //then
        assertThat(announcementRepository.findAll().size()).isEqualTo(0);
    }

    @Test
    void testRemoveAnnouncementLinkOrphanRemoval() {
        // given
        User savedUser = userRepository.save(user);
        Announcement savedAnnouncement = announcementRepository.save(announcement);
        savedUser.addAnnouncement(savedAnnouncement);
        userRepository.flush();
        // when
        assertThat(savedUser.getAnnouncements().size()).isEqualTo(1);
        assertThat(announcementRepository.findAll().size()).isEqualTo(1);
        savedUser.removeAnnouncement(savedAnnouncement);
        userRepository.flush();
        //then
        assertThat(announcementRepository.findAll().size()).isEqualTo(0);
    }
}