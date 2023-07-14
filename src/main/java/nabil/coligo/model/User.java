package nabil.coligo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.NaturalId;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;

/**
 * @author Ahmed Nabil
 */

@Entity
@Data
@NoArgsConstructor
@Builder
@Table(name = "app_user")
public class User implements UserDetails {
    public User(Long id, String email, String firstName, String lastName, String password, byte[] image, Role role, Set<Announcement> announcements) {
        this.id = id;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.image = image;
        setAnnouncements(announcements);
        this.role = role;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Email
    @Column(nullable = false, updatable = false, unique = true)
    @NaturalId
    private String email;

    @NotEmpty
    @NotNull
    @Size(min = 3, max = 50)
    @Column(nullable = false, length = 50)
    private String firstName;


    @NotEmpty
    @NotNull
    @Size(min = 3, max = 50)
    @Column(nullable = false, length = 50)
    private String lastName;

    @NotEmpty
    @NotNull
    @Size(min = 5, max = 255)
    @Column(nullable = false, length = 255)
    private String password;

    @Lob
    private byte[] image;

    @Column(nullable = false)
    private Role role;

    @JsonIgnore
    @Builder.Default
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Announcement> announcements = new HashSet<>();

    public void setAnnouncements(Set<Announcement> announcements) {
        this.announcements = new HashSet<>();
        announcements.forEach(this::addAnnouncement);
    }

    public void addAnnouncement(Announcement announcement) {
        announcement.setUser(this);
        announcements.add(announcement);
    }

    public void removeAnnouncement(Announcement announcement) {
        announcements.remove(announcement);
        announcement.setUser(null);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(new SimpleGrantedAuthority("ROLE_" + role.name()));
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User that = (User) o;
        return Objects.equals(email, that.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + email + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
