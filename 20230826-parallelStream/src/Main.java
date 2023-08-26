import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<Apple> inventory = new ArrayList<>();

        for (int i = 0; i < 60_000_000; i += 1) {
            inventory.add(new Apple(1, i * 100));
        }

        long filterStart = System.currentTimeMillis();

        List<Apple> heavyApples1 = inventory.stream().filter((apple -> apple.weight() > 150))
                .collect(Collectors.toList());

        long filterEnd = System.currentTimeMillis();

        long parallelStart= System.currentTimeMillis();

        List<Apple> heavyApples2 = inventory.parallelStream().filter((apple) -> apple.weight() > 150)
                .collect(Collectors.toList());

        long parallelEnd = System.currentTimeMillis();

        System.out.println(filterEnd - filterStart);
        System.out.println(parallelEnd - parallelStart);
    }
}
