package nabil.coligo.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Set;

/**
 * @author Ahmed Nabil
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class QuizGetDto {
    private Long id;
    private String courseName;
    private String topic;
    private LocalDateTime dueTo;
    private Set<QuestionGetDto> questions;
}
