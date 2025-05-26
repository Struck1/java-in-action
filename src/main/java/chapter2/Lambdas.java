package chapter2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import static java.util.Comparator.comparing;

public class Lambdas {
    public static void main(String[] args) {
        List<Apple> inventory = Arrays.asList(
                new Apple(22, Color.GREEN),
                new Apple(90, Color.RED),
                new Apple(77, Color.RED)
        );

        List<Apple> greenApple = filter(inventory, ((Apple apple) -> apple.getColor() == Color.GREEN));
        System.out.println(greenApple);

        //Comparator<Apple> comparator = (Apple a1, Apple a2) -> a1.getWeight() - a2.getWeight();
        Comparator<Apple> comparator = Comparator.comparingInt(Apple::getWeight);

        inventory.sort(comparator);
        System.out.println(inventory);

        inventory.set(1, new Apple(10, Color.RED));

        inventory.sort(comparing(Apple::getWeight));
        System.out.println(inventory);

        inventory.set(1, new Apple(20, Color.RED));

        inventory.sort((a1, a2) -> a2.getWeight() - a1.getWeight());
        System.out.println(inventory);
    }

    public static List<Apple> filter(List<Apple> inventory, ApplePredicate predicate) {
        List<Apple> filter = new ArrayList<>();
        for(Apple apple : inventory) {
            if(predicate.apply(apple)) {
                filter.add(apple);
            }
        }
        return filter;
    }

    interface ApplePredicate {
        boolean apply(Apple a);
    }
}
