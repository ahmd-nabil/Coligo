package nabil.coligo.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @author Ahmed Nabil
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class AnnouncementDto {
    private Long id;
    private String content;
    private AnnouncementOwnerDto user;
    private LocalDateTime createdAt;
}
