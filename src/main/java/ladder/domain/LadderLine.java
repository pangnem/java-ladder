package ladder.domain;

import java.util.ArrayList;
import java.util.List;

public class LadderLine {
    private final List<Point> points;

    public LadderLine(int playerCount) {
        this(new PlayerCount(playerCount));
    }

    public LadderLine(PlayerCount count) {
        this(init(count));
    }

    public LadderLine(List<Point> points) {
        this.points = points;
    }

    private static List<Point> init(PlayerCount count) {
        List<Point> points = new ArrayList<>();

        Point point = Point.first();
        points.add(point);

        for (int i = 1; i < count.value() - 1; i++) {
            point = point.next();
            points.add(point);
        }

        points.add(point.last());
        return points;
    }

    public Position move(int count) {
        return points.get(count).move();
    }
}
