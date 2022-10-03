package baseball;

import camp.nextstep.edu.missionutils.Console;
import camp.nextstep.edu.missionutils.Randoms;

public class Application {
    public static void main(String[] args) {
        String answer = createValidRandomNumber();
        playBaseBallGame(answer);
    }

    /**
     * 야구게임을 진행하고, 정답을 못맞추면 계속 진행, 정답을 맞추면 재시작 혹은 종료한다.
     * @param answer 정답값
     */
    private static void playBaseBallGame(String answer) {
        System.out.print("숫자를 입력해주세요 : ");
        String userInput = getUserNumberInput();
        String inputResult = checkAnswer(answer, userInput);

        System.out.println(inputResult);

        if (!inputResult.equals("3스트라이크")) {
            playBaseBallGame(answer);
            return;
        }

        retryOrExit();
    }

    /**
     * 재시작한다면 랜덤하게 정답값을 생성하고, playBaseBallGame, 종료한다면 아무처리도 하지 않는다.
     */
    private static void retryOrExit() {
        System.out.println("3개의 숫자를 모두 맞히셨습니다! 게임 종료");
        System.out.println("게임을 새로 시작하려면 1, 종료하려면 2를 입력하세요.");
        String retryInput = getUserRetryInput();
        if (retryInput.equals("1")) {
            String answer = createValidRandomNumber();
            System.out.println(answer);
            playBaseBallGame(answer);
        }
    }

    /**
     * 스트라이크, 볼, 낫싱, 정답 여부를 판단한다.
     * @param answer 정답값
     * @param userInput 사용자 입력값
     * @return string
     */
    protected static String checkAnswer(String answer, String userInput) {
        StringBuilder hintBuilder = new StringBuilder();

        int[] strikeAndBall = checkStrikeOrBall(answer, userInput);

        int strike = strikeAndBall[0];
        int ball = strikeAndBall[1];

        if (strike == 0 && ball == 0) return "낫싱";
        if (ball > 0) hintBuilder.append(ball).append("볼");
        if (hintBuilder.length() > 0) hintBuilder.append(" ");
        if (strike > 0) hintBuilder.append(strike).append("스트라이크");

        return hintBuilder.toString();
    }

    /**
     * 스트라이크와 볼 갯수를 체크한다.
     * @param answer 정답값
     * @param userInput 사용자 입력값
     * @return int[] 0 : 스트라이크, 1 : 볼
     */
    private static int[] checkStrikeOrBall(String answer, String userInput) {
        int[] result = new int[2];

        if (answer.charAt(0) == userInput.charAt(0)) result[0]++;
        if (answer.charAt(0) == userInput.charAt(1) || answer.charAt(0) == userInput.charAt(2)) result[1]++;

        if (answer.charAt(1) == userInput.charAt(1)) result[0]++;
        if (answer.charAt(1) == userInput.charAt(0) || answer.charAt(1) == userInput.charAt(2)) result[1]++;

        if (answer.charAt(2) == userInput.charAt(2)) result[0]++;
        if (answer.charAt(2) == userInput.charAt(0) || answer.charAt(2) == userInput.charAt(1)) result[1]++;

        return result;
    }

    /**
     * 사용자로부터 재시작여부 숫자값을 입력받는다.
     * @return string
     */
    private static String getUserRetryInput() {
        String retryInput = Console.readLine();
        retryInputValidation(retryInput);

        return retryInput;
    }

    /**
     * 사용자로부터 숫자값을 입력받는다.
     * @return string
     */
    private static String getUserNumberInput() {
        String userInput = Console.readLine();
        numberInputValidation(userInput);

        return userInput;
    }

    /**
     * 사용자가 잘못된 값을 입력할 경우 IllegalArgumentException을 내려준다.
     * @param userInput 사용자 입력값
     */
    protected static void numberInputValidation(String userInput) {
        String numericAndLength3 = "^[0-9]{3}$";

        if (!userInput.matches(numericAndLength3)) throw new IllegalArgumentException();
    }

    /**
     * 게임 재시작(1), 종료(2) 외의 값을 입력할 경우 IllegalArgumentException을 내려준다.
     * @param retryInput 사용자 입력값
     */
    protected static void retryInputValidation(String retryInput) {
        if (!retryInput.equals("1") && !retryInput.equals("2")) throw new IllegalArgumentException();
    }

    /**
     * 정답값을 생성한다.
     * @return string
     */
    protected static String createValidRandomNumber() {
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
    protected static boolean checkUniqueNumber(String answer) {
        return answer.charAt(0) != answer.charAt(1) &&
                answer.charAt(0) != answer.charAt(2) &&
                answer.charAt(1) != answer.charAt(2);
    }
}
