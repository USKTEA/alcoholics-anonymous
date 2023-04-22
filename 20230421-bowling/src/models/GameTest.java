package models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class GameTest {
    private static final int STRIKE = 10;
    private Game game;

    @BeforeEach
    void setup() {
        game = new Game(new NormalScoreCalculator());
    }

    @Test
    void whenCurrentFrameIsOne() {
        assertEquals(1, game.currentFrame().number());
        assertFalse(game.finished());
    }

    @Test
    void whenRollTwoTimesCurrentFrameWillBeIncreased() {
        assertEquals(1, game.currentFrame().number());

        for (int i = 0; i < 2; i += 1) {
            game.roll(0);
        }

        assertEquals(2, game.currentFrame().number());
    }

    @Test
    void whenRollStrikeCurrentFrameWillBeIncreased() {
        assertEquals(1, game.currentFrame().number());

        game.roll(STRIKE);
        game.roll(STRIKE);

        assertEquals(3, game.currentFrame().number());
    }

    @Test
    void whenRollStrikeAtLastFrameCanThrowTwoMoreTime() {
        assertFalse(game.finished());

        for (int i = 0; i < 18; i += 1) {
            game.roll(0);
        }

        game.roll(STRIKE);

        assertEquals(10, game.currentFrame().number());
        assertFalse(game.finished());

        game.roll(1);

        assertFalse(game.finished());

        game.roll(1);

        assertTrue(game.finished());
    }

    @Test
    void whenRollSpareAtLastFrameCanThrowOneMoreTime() {
        assertFalse(game.finished());

        for (int i = 0; i < 18; i += 1) {
            game.roll(0);
        }

        game.roll(2);
        game.roll(8);

        assertEquals(10, game.currentFrame().number());
        assertFalse(game.finished());

        game.roll(1);

        assertTrue(game.finished());
    }

    @Test
    void whenThrowZeroTwentyTimes() {
        for (int i = 0; i < 20; i += 1) {
            game.roll(0);
        }

        assertEquals(0, game.score());
    }

    @Test
    void whenThrowOneTwentyTimes() {
        for (int i = 0; i < 20; i += 1) {
            game.roll(1);
        }

        assertEquals(20, game.score());
    }

    @Test
    void whenThrowStrikeAtFinalFrame() {
        for (int i = 0; i < 18; i += 1) {
            game.roll(0);
        }

        game.roll(STRIKE);
        game.roll(STRIKE);
        game.roll(STRIKE);

        assertEquals(30, game.score());
    }

    @Test
    void whenGameIsPerfect() {
        //터진다 
        for (int i = 0; i < 12; i += 1) {
            game.roll(STRIKE);
        }

        assertEquals(300, game.score());
    }
}
