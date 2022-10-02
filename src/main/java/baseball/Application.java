package baseball;

import camp.nextstep.edu.missionutils.Randoms;

public class Application {
    public static void main(String[] args) {
        createValidRandomNumber();
    }

    /**
     * 정답값을 생성한다.
     * @return string
     */
    private static String createValidRandomNumber() {
        String answer = Integer.toString(Randoms.pickNumberInRange(1, 9));
        answer += Integer.toString(Randoms.pickNumberInRange(1, 9));
        answer += Integer.toString(Randoms.pickNumberInRange(1, 9));

        if (!checkUniqueNumber(answer)) createValidRandomNumber();

        return answer;
    }

    /**
     * 서로 다른 수로 이루어진 3자리인지 체크한다.
     * @param answer 정답값
     * @return boolean
     */
    private static boolean checkUniqueNumber(String answer) {
        return answer.charAt(0) != answer.charAt(1) &&
                answer.charAt(0) != answer.charAt(2) &&
                answer.charAt(1) != answer.charAt(2);
    }
}
