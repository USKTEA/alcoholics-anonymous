package models;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class NormalScoreCalculator implements ScoreCalculator {
    @Override
    public List<Integer> calculate(List<Frame> frames) {
        List<Integer> score = new LinkedList<>();

        for (Frame frame : frames) {
            score.add(getScore(frame));
        }

        List<Integer> bonuses = calculateBonus(frames, new ArrayList<>(), 0);

        for (int i = 0; i < bonuses.size(); i += 1) {
            int before = score.remove(i);

            score.add(i, before + bonuses.get(i));
        }

        return score;
    }

    @Override
    public List<Integer> calculateBonus(List<Frame> frames, List<Integer> bonuses, int index) {
        if (index == frames.size()) {
            return bonuses;
        }

        if (frames.get(index).isNormal()) {
            bonuses.add(index, 0);

            return calculateBonus(frames, bonuses, index + 1);
        }

        if (frames.get(index).isStrike()) {
            int bonus = getNextTwoThrowScores(frames, index);

            bonuses.add(index, bonus);

            return calculateBonus(frames, bonuses, index + 1);
        }

        if (frames.get(index).isSpare()) {
            int bonus = getNextOneThrowScores(frames, index);

            bonuses.add(index, bonus);

            return calculateBonus(frames, bonuses, index + 1);
        }

        return bonuses;
    }

    public int getNextOneThrowScores(List<Frame> frames, int index) {
        if (frames.size() <= index + 1) {
            return 0;
        }

        Frame nextFrame = frames.get(index + 1);

        List<Integer> scores = new ArrayList<>();

        scores.add(nextFrame.scores().get(0));

        return sum(scores);
    }

    public int getNextTwoThrowScores(List<Frame> frames, int index) {
        if (frames.size() <= index + 1) {
            return 0;
        }

        Frame nextFrame = frames.get(index + 1);

        List<Integer> scores = new ArrayList<>();

        for (Integer score : nextFrame.scores()) {
            scores.add(score);
        }

        if (scores.size() == 1) {
            if (frames.size() <= index + 2) {
                return sum(scores);
            }

            Frame frameAfterNextFrame = frames.get(index + 2);

            scores.add(frameAfterNextFrame.scores().get(0));
        }

        return sum(scores);
    }

    private int sum(List<Integer> scores) {
        return scores.stream()
                .mapToInt(Integer::intValue)
                .sum();
    }

    private Integer getScore(Frame frame) {
        return frame.scores.stream()
                .mapToInt(Integer::intValue)
                .sum();
    }
}
