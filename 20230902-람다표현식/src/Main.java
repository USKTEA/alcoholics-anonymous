import kotlin.jvm.functions.Function3;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;

@FunctionalInterface
interface JavaTriFunction<T, U, V, R> {
    R apply(T t, U u, V v);
}

public class Main {
    public static void main(String[] args) {
        JavaTriFunction<Integer, Integer, Integer, JavaColor> colorFactory = JavaColor::new;
        JavaColor black = colorFactory.apply(255, 255, 255);

        Function3<Integer, Integer, Integer, JavaColor> colorFactory2 = JavaColor::new;
        JavaColor white = colorFactory2.invoke(0, 0, 0);

        List<JavaApple> inventory = Arrays.asList(
                new JavaApple(30), new JavaApple(20), new JavaApple(10)
        );

        inventory.sort(Comparator.comparing(JavaApple::weight));

        Function<Integer, Integer> f = x -> x + 1;
        Function<Integer, Integer> g = x -> x * 2;
        Function<Integer, Integer> h = f.andThen(g);
        Function<Integer, Integer> c = f.compose(g);

        int result1 = h.apply(10);
        int result2 = c.apply(10);

        System.out.println(result1);
        System.out.println(result2);
    }

    public <T> T pipe(T input, Function<T, T>...functions) {
        return Arrays.stream(functions).reduce(Function.identity(), Function::andThen).apply(input);
    }
}

class JavaApple {
    private int weight;

    public JavaApple(int weight) {
        this.weight = weight;
    }

    public int weight() {
        return this.weight;
    }

    @Override
    public String toString() {
        return "Apple: " + this.weight;
    }
}

class JavaColor {
    private Integer red;
    private Integer green;
    private Integer blue;

    public JavaColor(Integer red, Integer green, Integer blue) {
        this.red = red;
        this.green = green;
        this.blue = blue;
    }
}
