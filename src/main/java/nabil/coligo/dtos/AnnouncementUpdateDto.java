package nabil.coligo.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Ahmed Nabil
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class AnnouncementUpdateDto {
    private Long id;
    private String content;
}
