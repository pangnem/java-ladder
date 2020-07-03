package ladder.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Ladder {
    private static final int MIN_START_POSITION_COUNT = 2;

    private final List<HorizonLine> horizonLines = new ArrayList<>();
    private final int positionCount;

    public Ladder(LadderHeight height, Participants participants) {
       this(height, participants, () -> new Random().nextBoolean());
    }

    public Ladder(LadderHeight height,
                  Participants participants,
                  ShortLineEnableJudge shortLineEnableJudge) {
        validateMinPositionCount(participants.size());
        this.positionCount = participants.size();
        createHorizonLine(height, positionCount, shortLineEnableJudge);
    }

    private void validateMinPositionCount(int startPositionCount) {
        if (startPositionCount < MIN_START_POSITION_COUNT) {
            throw new IllegalArgumentException("사다리를 시작하기 위해서는 2명 이상의 사용자가 필요합니다.");
        }
    }

    public int getHeight() {
        return horizonLines.size();
    }

    private void createHorizonLine(LadderHeight height, int statPositionCount, ShortLineEnableJudge shortLineEnableJudge) {
        for (int i = 0; i < height.get(); i++) {
            horizonLines.add(new HorizonLine(statPositionCount, shortLineEnableJudge));
        }
    }

    public boolean isEnabledShortLineOfRight(int heightIndex, Position position) {
        return horizonLines.get(heightIndex).isEnabledShortLineOfRight(position);
    }

    public int getShortLineCountInHorizonLine(int heightIndex) {
        return horizonLines.get(heightIndex).getShortLineCount();
    }

    protected Position play(Position startPosition) {
        for (int i = 0; i < getHeight(); i++) {
            startPosition = startPosition.next(horizonLines.get(i));
        }
        return startPosition;
    }

    protected List<Position> playAll() {
        List<Position> resultIndex = new ArrayList<>();
        for (int i = 0; i < positionCount ; i++) {
            resultIndex.add(play(Position.of(i)));
        }
        return resultIndex;
    }

}