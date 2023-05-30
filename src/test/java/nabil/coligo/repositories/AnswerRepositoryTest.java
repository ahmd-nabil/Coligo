package nabil.coligo.repositories;

import nabil.coligo.model.Answer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Ahmed Nabil
 */
@DataJpaTest
class AnswerRepositoryTest {

    @Autowired
    AnswerRepository answerRepository;

    @Test
    void testSaveAnswer() {
        answerRepository.save(Answer.builder().build());
        assertThat(answerRepository.count()).isEqualTo(1);
    }
}