package models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TenthFrameTest {
    private TenthFrame tenthFrame;
    private final static int STRIKE = 10;

    @BeforeEach
    void setup() {
        tenthFrame = new TenthFrame(1);
    }

    @Test
    void whenThereIsNoSpare() {
        assertFalse(tenthFrame.finished());

        tenthFrame.mark(1);
        tenthFrame.mark(2);

        assertTrue(tenthFrame.finished());
    }

    @Test
    void whenThrowSpareCanThrowOneMoreTime() {
        assertFalse(tenthFrame.finished());

        tenthFrame.mark(2);
        tenthFrame.mark(8);

        assertFalse(tenthFrame.finished());

        tenthFrame.mark(2);

        assertTrue(tenthFrame.finished());
    }

    @Test
    void whenThrowStrikeCanThrowTwoMoreTime() {
        assertFalse(tenthFrame.finished());

        tenthFrame.mark(STRIKE);

        assertFalse(tenthFrame.finished());

        tenthFrame.mark(2);

        assertFalse(tenthFrame.finished());

        tenthFrame.mark(2);

        assertTrue(tenthFrame.finished());
    }

    @Test
    void whenThrowStrikePinWillBeReset() {
        assertEquals(10, tenthFrame.remainPins());

        tenthFrame.mark(STRIKE);

        assertEquals(10, tenthFrame.remainPins());
    }

    @Test
    void whenThrowSparePinWilLBeReset() {
        assertEquals(10, tenthFrame.remainPins());

        tenthFrame.mark(2);
        tenthFrame.mark(8);

        assertEquals(10, tenthFrame.remainPins());
    }
}
