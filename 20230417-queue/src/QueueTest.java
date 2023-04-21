import org.junit.jupiter.api.Test;

import java.util.Collection;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class QueueTest {
    @Test
    void creation() {
        Queue<Integer> queue = new Queue<>(1);

        assertEquals(0, queue.size());
    }

    @Test
    void addOneNumber() {
        Queue<Number> queue = new Queue<>(1);

        queue.add(1);

        assertEquals(1, queue.size());
    }

    @Test
    void addTwoNumbersToSizeOneQueue() {
        Queue<Number> queue = new Queue<>(1);

        queue.add(1);

        assertThrows(QueueIsFull.class, () -> queue.add(2));
    }

    @Test
    void addTwoNumbers() {
        Queue<Number> queue = new Queue<>(2);

        queue.add(1);
        queue.add(1);

        assertEquals(2, queue.size());
    }

    @Test
    void removeOne() {
        Queue<Number> queue = new Queue<>(1);

        queue.add(1);

        Object removed = queue.remove();

        assertEquals(1, removed);
        assertEquals(0, queue.size());
    }

    @Test
    void removeTwo() {
        Queue<Number> queue = new Queue<>(2);

        queue.add(2);
        queue.add(3);

        Object first = queue.remove();
        Object second = queue.remove();

        assertEquals(2, first);
        assertEquals(3, second);
    }

    @Test
    void removeEmptyQueue() {
        Queue<Number> queue = new Queue<>(1);

        queue.add(1);

        queue.remove();

        assertEquals(0, queue.size());

        assertThrows(QueueIsEmpty.class, () -> queue.remove());
    }

    @Test
    void addFullAndRemoveAllAndAddOne() {
        Queue<Number> queue = new Queue<>(5);

        queue.add(1);
        queue.add(2);
        queue.add(3);
        queue.add(4);
        queue.add(5);

        for (int i = 0; i < 5; i += 1) {
            queue.remove();
        }

        queue.add(6);

        assertEquals(1, queue.size());
        assertEquals(6, queue.remove());
    }

    @Test
    void addString() {
        Queue<String> stringQueue = new Queue<>(5);
        stringQueue.add("a");

        assertEquals(1, stringQueue.size());
        assertEquals("a", stringQueue.remove());
    }

    @Test
    void addLong() {
        Queue<Long> longQueue = new Queue<>(2);
        longQueue.add(1L);
        longQueue.add(2L);

        assertEquals(2, longQueue.size());
        assertEquals(1L, longQueue.remove());
    }

    @Test
    void addOtherObject() {
        Queue<Animal> animalQueue = new Queue<>(10);

        animalQueue.add(new Cat("Kitty"));

        assertEquals("meow", animalQueue.remove().shout());
    }
}
