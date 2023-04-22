package models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class NormalScoreCalculatorTest {
    private NormalScoreCalculator scoreCalculator;

    @BeforeEach
    void setup() {
        scoreCalculator = new NormalScoreCalculator();
    }

    @Test
    void calculateOneNormalFrame() {
        List<Integer> scores = scoreCalculator.calculate(List.of(Frame.normal(1, 2)));

        assertEquals(List.of(3), scores);
    }

    @Test
    void calculateTwoNormalFrames() {
        List<Integer> scores = scoreCalculator.calculate(
                List.of(Frame.normal(1, 2), Frame.normal(3, 4)));

        assertEquals(List.of(3, 7), scores);
    }

    @Test
    void calculateOneStrikeOneNormalFrame() {
        List<Integer> scores = scoreCalculator.calculate(
                List.of(Frame.strike(), Frame.normal(3, 4)));

        assertEquals(List.of(17, 7), scores);
    }

    @Test
    void calculateThreeStrikesInRow() {
        List<Integer> scores = scoreCalculator.calculate(
                List.of(Frame.strike(), Frame.strike(), Frame.strike()));

        assertEquals(List.of(30, 20, 10), scores);
    }

    @Test
    void calculateOneSpareOneNormalFrame() {
        List<Integer> scores = scoreCalculator.calculate(
                List.of(Frame.spare(2, 8), Frame.normal(3, 1)));

        assertEquals(List.of(13, 4), scores);
    }

    @Test
    void calculateBonus() {
        List<Integer> bonuses = scoreCalculator.calculateBonus(
                List.of(Frame.strike(), Frame.normal(3, 4)), new ArrayList<>(), 0);

        assertEquals(List.of(7, 0), bonuses);
    }

    @Test
    void getNextTwoThrows() {
        int score = scoreCalculator.getNextTwoThrowScores(List.of(Frame.normal(1, 1), Frame.normal(1, 2)), 0);

        assertEquals(3, score);
    }

    @Test
    void getNextOneThrows() {
        int score = scoreCalculator.getNextOneThrowScores(List.of(Frame.normal(1, 1), Frame.normal(1, 5)), 0);

        assertEquals(1, score);
    }
}
