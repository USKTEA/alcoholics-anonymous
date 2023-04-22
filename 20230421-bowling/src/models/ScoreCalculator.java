package models;

import java.util.List;

public interface ScoreCalculator {
    List<Integer> calculate(List<Frame> frames);
    List<Integer> calculateBonus(List<Frame> frames, List<Integer> bonuses, int index);
}
