//1. 선입선출 정수 큐를 구현하라.
//2. 정수는 고정 길이 배열에 저장해야한다.
//3. circular buffer는 head, rear 2개의 포인터로 구현한 선형큐이다.

public class Queue<E> {
    private Object[] values;
    private int head;
    private int rear;

    public Queue(int storageSize) {
        this.values = new Object[storageSize];
        head = 0;
        rear = 0;
    }

    public void add(E i) {
        if (size() == values.length) {
            throw new QueueIsFull();
        }

        if (rear == values.length) {
            rear = 0;

            values[rear] = i;
            rear += 1;

            return;
        }

        values[rear] = i;
        rear += 1;
    }

    public E remove() {
        if (size() == 0) {
            throw new QueueIsEmpty();
        }

        if (head == values.length) {
            head = 0;
        }

        Object removed = values[head];

        head += 1;

        return (E) removed;
    }

    public int size() {
        if (head <= rear) {
            return rear - head;
        }

        return rear + values.length - head;
    }
}
