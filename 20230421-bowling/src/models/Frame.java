package models;

import java.util.ArrayList;
import java.util.List;

public class Frame {
    protected final static int INITIAL_PINS = 10;
    protected final static int STRIKE = 10;
    protected final static int SPARE = 10;

    protected int number;
    protected int remainPins;
    protected List<Integer> scores;
    protected Status status;

    public Frame(int number) {
        this.number = number;
        this.remainPins = INITIAL_PINS;
        this.scores = new ArrayList<>();
        this.status = Status.NORMAL;
    }

    public Frame(int number, int firstThrow, int secondThrow) {
        this.number = number;
        this.remainPins = INITIAL_PINS;
        this.scores = List.of(firstThrow, secondThrow);
        this.status = Status.NORMAL;
    }

    public Frame(int number, int firstThrow) {
        this.number = number;
        this.remainPins = 0;
        this.scores = List.of(firstThrow);
        this.status = Status.STRIKE;
    }

    public Frame(int number, int firstThrow, int secondThrow, Status status) {
        this.number = number;
        this.remainPins = 0;
        this.scores = List.of(firstThrow, secondThrow);
        this.status = status;
    }

    public static Frame normal(int firstThrow, int secondThrow) {
        return new Frame(1, firstThrow, secondThrow);
    }

    public static Frame strike() {
        return new Frame(1, 10);
    }

    public static Frame spare(int firstThrow, int secondThrow) {
        return new Frame(1, firstThrow, secondThrow, Status.SPARE);
    }

    public void mark(int pin) {
        this.scores.add(pin);
        this.remainPins -= pin;

        if (this.status == Status.NORMAL) {
            checkIsStrike();
            checkIsSpare();
        }
    }

    public boolean finished() {
        return this.scores.size() == 2 || this.status == Status.STRIKE;
    }

    protected void checkIsSpare() {
        if (this.scores.size() == 2 && this.status != Status.STRIKE) {
            if (this.scores.get(0) + this.scores.get(1) == SPARE) {
                this.status = Status.SPARE;
            }
        }
    }

    protected void checkIsStrike() {
        if (this.scores.get(0) == STRIKE) {
            this.status = Status.STRIKE;
        }
    }

    public Status status() {
        return this.status;
    }

    public int number() {
        return this.number;
    }

    public int remainPins() {
        return this.remainPins;
    }

    public boolean isNormal() {
        return this.status == Status.NORMAL;
    }

    public boolean isStrike() {
        return this.status == Status.STRIKE;
    }

    public List<Integer> scores() {
        return this.scores;
    }

    @Override
    public String toString() {
        return "Status:" + status + " / Score:" + scores.stream().mapToInt(Integer::intValue).sum();
    }

    public boolean isSpare() {
        return this.status == Status.SPARE;
    }
}
