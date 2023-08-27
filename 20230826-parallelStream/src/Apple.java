public class Apple {
    private int id;
    private int weight;

    public Apple(int id, int weight) {
        this.id = id;
        this.weight = weight;
    }

    public int weight() {
        int a = 0;

        for (int i = 0; i < 1_000; i += 1) {
            a += i;
        }

        return this.weight;
    }
}
