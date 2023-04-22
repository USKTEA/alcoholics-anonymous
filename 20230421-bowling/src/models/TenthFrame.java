package models;

public class TenthFrame extends Frame {
    public TenthFrame(int number) {
        super(number);
    }

    @Override
    public boolean finished() {
        if (this.status == Status.STRIKE || this.status == Status.SPARE) {
            if (this.scores.size() == 3) {
                return true;
            }

            return false;
        }

        return this.scores.size() == 2;
    }

    @Override
    protected void checkIsSpare() {
        if (this.scores.size() == 2 && this.status != Status.STRIKE) {
            if (this.scores.get(0) + this.scores.get(1) == SPARE) {
                this.status = Status.SPARE;
                resetPins();
            }
        }
    }

    @Override
    protected void checkIsStrike() {
        if (this.scores.get(0) == STRIKE) {
            this.status = Status.STRIKE;
            resetPins();
        }
    }

    private void resetPins() {
        this.remainPins = INITIAL_PINS;
    }
}
