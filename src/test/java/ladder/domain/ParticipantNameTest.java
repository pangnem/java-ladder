package ladder.domain;

import ladder.exception.InvalidParticipantNameException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;

import static org.assertj.core.api.Assertions.*;

@DisplayName("사다리 게임 참가자의 이름을 가지는 Name 클래스 테스트")
public class ParticipantNameTest {
    @DisplayName("비어있는 String은 유효한 이름이 아니다.")
    @ParameterizedTest(name = "{arguments} : {displayName}")
    @NullAndEmptySource
    void isBlank(String name) {
        assertThatThrownBy(() -> new ParticipantName(name)).isInstanceOf(InvalidParticipantNameException.class);
    }

    @DisplayName("이름 길이가 5글자 이상이면 유효한 이름이 아니다.")
    @Test
    void nameSize() {
        assertThat(new ParticipantName("abcde"));
        assertThatThrownBy(() ->  new ParticipantName("abcdef")).isInstanceOf(InvalidParticipantNameException.class);
    }
}