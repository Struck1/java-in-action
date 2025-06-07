package chapter3;

import java.util.List;
import java.util.function.IntSupplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.toList;

public class StreamBasic {

    public static void main(String[] args) {
        getLowCaloricDishesName(Dish.menu).forEach(System.out::println);
        System.out.println("---------------------------");
        getThreeHighCaloricDishesName(Dish.menu).forEach(System.out::println);


    }

    public static List<String> getLowCaloricDishesName(List<Dish> dishes){
            return  dishes.stream()
                    .filter(d -> d.getCalories() < 400)
                    .sorted(comparing(Dish::getCalories))
                    .map(Dish::getName)
                    .collect(toList());
    }

    public static List<String> getThreeHighCaloricDishesName(List<Dish> dishes){
        return  dishes.stream()
                .filter(d -> d.getCalories() > 400)
                .sorted(comparing(Dish::getCalories))
                .limit(3)
                .map(Dish::getName)
                .collect(toList());
    }
}
