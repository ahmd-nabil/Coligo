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
public class QuizAllDto {
    private Long id;
    private String courseName;
    private String topic;
    private LocalDateTime dueTo;
}
