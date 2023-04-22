package models;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Game {
    private final ScoreCalculator scoreCalculator;
    private List<Frame> frames;
    private List<Integer> scorePerFrames;
    private int current;

    public Game(ScoreCalculator scoreCalculator) {
        this.scoreCalculator = scoreCalculator;
        this.frames = setFrames();
        this.scorePerFrames = new ArrayList<>();
        this.current = 1;
    }

    public void roll(int pin) {
        Frame frame = currentFrame();

        frame.mark(pin);

        calculateScore();

        if (frame.finished() && current != 10) {
            current += 1;
        }
    }

    private void calculateScore() {
        this.scorePerFrames = scoreCalculator.calculate(frames);
    }

    public boolean finished() {
        return this.current == 10 && currentFrame().finished();
    }

    public Frame currentFrame() {
        return this.frames.get(current - 1);
    }

    private List<Frame> setFrames() {
        return IntStream.range(0, 10)
                .mapToObj((number) -> {
                    if (number == 9) {
                        return new TenthFrame(number + 1);
                    }

                    return new Frame(number + 1);
                })
                .collect(Collectors.toList());
    }

    public int remainPins() {
        return currentFrame().remainPins();
    }

    public List<Frame> frames() {
        return frames;
    }

    public int score() {
        return scorePerFrames.stream()
                .mapToInt(Integer::intValue)
                .sum();
    }
}
