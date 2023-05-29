package nabil.coligo.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Ahmed Nabil
 */

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, updatable = false)
    private String username;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false)
    private String password;

    @Lob
    private byte[] image;

    @Builder.Default
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Announcement> announcements = new HashSet<>();

    public void addAnnouncement(Announcement announcement) {
        announcements.add(announcement);
        announcement.setUser(this);
    }

    public void removeAnnouncement(Announcement announcement) {
        announcements.remove(announcement);
        announcement.setUser(null);
    }
}
