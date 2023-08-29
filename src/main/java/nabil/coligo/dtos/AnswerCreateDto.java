package nabil.coligo.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Ahmed Nabil
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AnswerCreateDto {
    private String answer;
    private Boolean correct = false;
}
