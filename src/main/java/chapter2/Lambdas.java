package chapter2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;

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

        inventory.set(1, new Apple(20, Color.GREEN));

        inventory.sort((a1, a2) -> a2.getWeight() - a1.getWeight());
        System.out.println(inventory);

        /*
        inventory.sort(comparing(Apple::getWeight)
                .reversed()
                .thenComparing()
        );

         */
        Function<Integer, Integer> f = x -> x + 1;
        Function<Integer, Integer> g = x -> x * 2;
        Function<Integer, Integer> h = f.andThen(g);
        int result = h.apply(1);
        System.out.println(result); //4

        Function<Integer, Integer> a = x -> x + 1;
        Function<Integer, Integer> b = x -> x * 2;
        Function<Integer, Integer> c = a.compose(b);
        int result1 = c.apply(1);
        System.out.println(result1); //3

        Function<String, String> addHeader = Lambdas::addHeader;
        Function<String, String> transformPipeline = addHeader.andThen(Lambdas::checkSpelling)
                .andThen(Lambdas::addFooter);
        String result2 = transformPipeline.apply("testtt labda");
        System.out.println(result2);

        List<Integer> l = map(Arrays.asList("test", "zaaaaa"), (String s) -> s.length());
        System.out.println(l);



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


   // The java.util.function.Function<T, R> interface defines an abstract method
   // named apply that takes an object of generic type T as input and returns an object of
   // generic type R


    public static <T, R> List<R> map(List<T> list, Function<T, R> f) {
        List<R> res = new ArrayList<>();
        for(T t: list) {
            res.add(f.apply(t));
        }
        return res;
    }


    interface ApplePredicate {
        boolean apply(Apple a);
    }

    public static String addFooter(String text) {
        return text + " Kind regards";
    }
    public static String checkSpelling(String text) {
        return text.replaceAll("labda", "lambda");
    }
    public static String addHeader(String text) {
        return "From Raoul, Mario and Alan: " + text;
    }
}
