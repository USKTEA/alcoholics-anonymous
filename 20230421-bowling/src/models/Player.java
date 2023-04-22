package models;

import java.util.Random;

public class Player {
    private String nickname;

    public Player(String nickname) {
        this.nickname = nickname;
    }

    public void throwBall(Game game) {
        game.roll(makeBallPoint(game.remainPins()));
    }

    public int makeBallPoint(int remainPins) {
        return new Random().nextInt(remainPins + 1);
    }
}
