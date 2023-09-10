package javaStream;

import java.util.Arrays;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class JavaStream {
    public static void main(String[] args) {
        List<Dish> menu = Arrays.asList(
                new Dish("pork", false, 800, Type.MEAT),
                new Dish("beef", false, 700, Type.MEAT),
                new Dish("chicken", false, 400, Type.MEAT),
                new Dish("french fries", true, 530, Type.OTHER),
                new Dish("rice", true, 350, Type.OTHER),
                new Dish("season fruit", true, 120, Type.OTHER),
                new Dish("pizza", true, 550, Type.OTHER),
                new Dish("prawns", false, 300, Type.FISH),
                new Dish("salmon", false, 450, Type.FISH)
        );

        List<Dish> firstTwoMeats = menu.stream()
                .filter(dish -> dish.getType() == Type.MEAT)
                .limit(2)
                .collect(toList());

        List<Integer> numbers = Arrays.asList(
                1, 2, 3, 4, 5
        );

        List<Integer> squared = numbers.stream()
                .map(integer -> integer * integer)
                .collect(toList());

        List<Integer> numberOneTwoThree = Arrays.asList(1, 2, 3);
        List<Integer> numberThreeFour = Arrays.asList(3, 4);

        List<int[]> combined = numberOneTwoThree.stream().flatMap(
                outer -> numberThreeFour.stream()
                        .filter(inner -> (inner + outer) % 3 == 0)
                        .map(inner -> new int[]{outer, inner}
                )
        ).collect(toList());


        combined.stream().forEach(it -> {
            System.out.print(it[0] + ", ");
            System.out.println(it[1]);
        });

        //map과 reduce를 사용해 stream 요리의 갯수를 계산하자
        menu.stream().map(dish -> 1).reduce(0, Integer::sum);
        menu.stream().count();
    }
}

