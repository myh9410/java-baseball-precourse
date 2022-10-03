package baseball;

import camp.nextstep.edu.missionutils.test.NsTest;
import org.junit.jupiter.api.Test;

import static camp.nextstep.edu.missionutils.test.Assertions.assertRandomNumberInRangeTest;
import static camp.nextstep.edu.missionutils.test.Assertions.assertSimpleTest;
import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ApplicationTest extends NsTest {
    @Test
    void 게임종료_후_재시작() {
        assertRandomNumberInRangeTest(
                () -> {
                    run("246", "135", "1", "597", "589", "2");
                    assertThat(output()).contains("낫싱", "3스트라이크", "1볼 1스트라이크", "3스트라이크", "게임 종료");
                },
                1, 3, 5, 5, 8, 9
        );
    }

    @Test
    void 예외_테스트() {
        assertSimpleTest(() ->
                assertThatThrownBy(() -> runException("1234"))
                        .isInstanceOf(IllegalArgumentException.class)
        );
    }

    @Test
    void 사용자_숫자입력_검증성공_테스트() {
        String numberInput = "123";

        assertThatNoException().isThrownBy(() -> {
            Application.numberInputValidation(numberInput);
        });
    }

    @Test
    void 숫자야구_볼과스트라이크_테스트() {
        String answer = "123";
        String userInput = "132";

        String result = Application.checkAnswer(answer, userInput);

        assertEquals(result, "2볼 1스트라이크");
    }

    @Test
    void 숫자야구_낫싱_테스트() {
        String answer = "123";
        String userInput = "456";

        String result = Application.checkAnswer(answer, userInput);

        assertEquals(result, "낫싱");
    }

    @Test
    void 숫자야구_3스트라이크_테스트() {
        String answer = "123";
        String userInput = "123";

        String result = Application.checkAnswer(answer, userInput);

        assertEquals(result, "3스트라이크");
    }

    @Test
    void 재시작_검증성공_테스트() {
        String retryInput = "1";

        assertThatNoException().isThrownBy(() -> {
            Application.retryInputValidation(retryInput);
        });
    }

    @Test
    void 재시작_검증실패_테스트() {
        String retryInput = "3";

        assertThatIllegalArgumentException().isThrownBy(() -> {
            Application.retryInputValidation(retryInput);
        });
    }

    @Test
    void 세자리_고유한_숫자_테스트() {
        String input = "123";

        boolean result = Application.checkUniqueNumber(input);

        assertTrue(result);

    }

    @Override
    public void runMain() {
        Application.main(new String[]{});
    }
}
