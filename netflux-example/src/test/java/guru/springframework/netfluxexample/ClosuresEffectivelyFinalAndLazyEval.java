package guru.springframework.netfluxexample;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;

public class ClosuresEffectivelyFinalAndLazyEval {

    @Test
    public void lambdaExample() throws Exception {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6);

        numbers.stream()
                .map(number -> number * 2) //lambda is stateless
                .forEach(System.out::println);
    }

    @Test
    public void closureExample() throws Exception {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6);

        Integer multiplier = 2; // lexical scope

        numbers.stream()
                .map(number -> number * multiplier)
                 //lambda 'closes over' variable in lexical scope
                // i.e. 'closure'
                .forEach(System.out::println);
    }

    @Test
    public void closureUsingFinal() throws Exception {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6);

        final Integer multiplier = 2;

        Stream<Integer> numberStream = numbers.stream()
                .map(number -> number * multiplier);

        //multiplier = 3;

        numberStream.forEach(System.out::println);
    }

    @Test
    public void closureEffectivelyFinal() throws Exception {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6);

        Integer multiplier = 2; //effectively final

        Stream<Integer> numberStream = numbers.stream()
                .map(number -> number * multiplier);

        //multiplier = 3;

        numberStream.forEach(System.out::println);
    }

    @Test
    public void breakingFinal() throws Exception {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6);

        final Integer[] multiplier = {2};

        Stream<Integer> numberStream = numbers.stream()
                .map(number -> number * multiplier[0]);

        multiplier[0] = 0;

        numberStream.forEach(System.out::println);
    }

}
