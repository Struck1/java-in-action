package chapter1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;

public class CustomFilter {

    public static void main(String... args) {
        List<Apple> inventory = Arrays.asList(new Apple(80, Color.GREEN), new Apple(155, Color.GREEN), new Apple(120, Color.RED));

        // [Apple{color='green', weight=80}, Apple{color='green', weight=155}]
        List<Apple> greenApples = filterApples(inventory, CustomFilter::isGreenApple);
        System.out.println(greenApples);

        // [Apple{color='green', weight=155}]
        List<Apple> heavyApples = filterApples(inventory, CustomFilter::isHeavyApple);
        System.out.println(heavyApples);

        // [Apple{color='green', weight=80}, Apple{color='green', weight=155}]
        List<Apple> greenApples2 = filterApples(inventory, (Apple a) -> Color.GREEN.equals(a.getColor()));
        System.out.println(greenApples2);

        // [Apple{color='green', weight=155}]
        List<Apple> heavyApples2 = filterApples(inventory, (Apple a) -> a.getWeight() > 150);
        System.out.println(heavyApples2);

        // []
        // List<Apple> weirdApples = filterApples(inventory, (Apple a) -> a.getWeight() < 80 || "brown".equals(a.getColor()));
        //System.out.println(weirdApples);

        List<Apple> filterEx = filter(inventory, new AppleWeightPredicate());
        System.out.println(filterEx);

        List<Apple> filterEx1 = filter(inventory, new AppleColorPredicate());
        System.out.println(filterEx1);

        List<Apple> filterEx2 = filter(inventory, new AppleColorAndWeightPredicate());
        System.out.println(filterEx2);


    }

    public static List<Apple> filterGreenApples(List<Apple> inventory) {
        List<Apple> result = new ArrayList<>();
        for (Apple apple : inventory) {
            if (Color.GREEN.equals(apple.getColor())) {
                result.add(apple);
            }
        }
        return result;
    }

    public static List<Apple> filterHeavyApples(List<Apple> inventory) {
        List<Apple> result = new ArrayList<>();
        for (Apple apple : inventory) {
            if (apple.getWeight() > 150) {
                result.add(apple);
            }
        }
        return result;
    }

    public static boolean isGreenApple(Apple apple) {
        return Color.GREEN.equals(apple.getColor());
    }

    public static boolean isHeavyApple(Apple apple) {
        return apple.getWeight() > 150;
    }

    public static List<Apple> filterApples(List<Apple> inventory, Predicate<Apple> p) {
        List<Apple> result = new ArrayList<>();
        for (Apple apple : inventory) {
            if (p.test(apple)) {
                result.add(apple);
            }
        }
        return result;
    }

    public static List<Apple> filter(List<Apple> invertory, ApplePredice p) {
        List<Apple> result = new ArrayList<>();
        for (Apple a : invertory) {
            if (p.test(a)) {
                result.add(a);
            }
        }
        return result;
    }

    public static class Apple {

        private int weight;
        private Color color;

        public Apple(int weight, Color color) {
            this.weight = weight;
            this.color = color;
        }

        public int getWeight() {
            return weight;
        }

        public void setWeight(int weight) {
            this.weight = weight;
        }

        public Color getColor() {
            return color;
        }

        public void setColor(Color color) {
            this.color = color;
        }

        @Override
        public String toString() {
            return String.format("Apple{color='%s', weight=%d}", color, weight);
        }

    }

    enum Color {
        RED, GREEN
    }

    interface ApplePredice {
        boolean test(Apple a);
    }

    static class AppleWeightPredicate implements ApplePredice {
        @Override
        public boolean test(Apple a) {
            return a.getWeight() > 50;
        }
    }

    static class AppleColorPredicate implements ApplePredice {

        @Override
        public boolean test(Apple apple) {
            return apple.getColor().equals(Color.GREEN);
        }
    }

    static class AppleColorAndWeightPredicate implements ApplePredice {

        @Override
        public boolean test(Apple a) {
            return a.getColor().equals(Color.GREEN) && a.getWeight() > 80;
        }
    }
}
