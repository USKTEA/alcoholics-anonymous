package models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class FrameTest {
    private Frame frame;

    @BeforeEach
    void setup() {
        frame = new Frame(1);
    }

    @Test
    void canMarkPin() {
        frame.mark(1);
    }

    @Test
    void whenThereIsNoStrikeOrSpare() {
        assertEquals(Status.NORMAL, frame.status());

        frame.mark(1);
        frame.mark(2);

        assertEquals(Status.NORMAL, frame.status());
    }

    @Test
    void whenThereIsStrike() {
        assertEquals(Status.NORMAL, frame.status());

        frame.mark(Frame.STRIKE);

        assertEquals(Status.STRIKE, frame.status());
    }

    @Test
    void whenThereIsSpare() {
        assertEquals(Status.NORMAL, frame.status());

        frame.mark(5);
        frame.mark(5);

        assertEquals(Status.SPARE, frame.status());
    }

    @Test
    void whenMarkTwiceFrameWillBeFinished() {
        assertFalse(frame.finished());

        frame.mark(1);
        frame.mark(2);

        assertTrue(frame.finished());
    }

    @Test
    void whenStrikeFrameWillBeFinished() {
        assertFalse(frame.finished());

        frame.mark(10);

        assertTrue(frame.finished());
    }

    @Test
    void remainPins() {
        assertEquals(10, frame.remainPins());

        frame.mark(5);

        assertEquals(5, frame.remainPins());
    }
}
