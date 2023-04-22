package models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {
    private Player player;

    @BeforeEach
    void setup() {
        player = new Player("황금오른손");
    }

    @Test
    void ballPointIsAlwaysBelowRemainPins() {
        int remainPins = 10;

        assertTrue(player.makeBallPoint(remainPins) <= 10);
    }
}
