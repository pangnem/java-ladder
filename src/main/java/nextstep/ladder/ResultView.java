package nextstep.ladder;

import java.util.List;

public class ResultView {

    private static final String EXISTED_LINE = "|-----";
    private static final String NOT_EXISTED_LINE = "|     ";
    private static final String BLANK = "    ";

    public void printPlayersName(Players players) {
        StringBuilder sb = new StringBuilder();
        System.out.println("실행 결과");
        players.getPlayers()
                .forEach(playerName -> {
                    sb.append(playerName).append(BLANK);
                });
        System.out.println(sb);
    }

    public void printLadders(Ladders ladders) {
        ladders.getLines()
                .stream()
                .map(Line::getPoints)
                .forEach(ResultView::printLines);
    }

    private static void printLines(List<Boolean> booleans) {
        booleans.forEach(line -> {
            if (line) {
                System.out.print(EXISTED_LINE);
                return;
            }
            System.out.print(NOT_EXISTED_LINE);
        });
        System.out.println();
    }
}
