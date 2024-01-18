package homework1;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static caso_di_studio_test.CalcolatriceAvanzata.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.params.provider.Arguments.of;

public class CalcolatriceAvanzataTest {
    @ParameterizedTest
    @MethodSource("generatorSomma")
    void testSomma(double a, double b, double expectedValue) {
        assertEquals(somma(a, b), expectedValue);
    }

    @ParameterizedTest
    @MethodSource("generatorSottrai")
    void testSottrai(double a, double b, double expectedValue) {
        assertEquals(sottrai(a, b), expectedValue);
    }

    @ParameterizedTest
    @MethodSource("generatorMoltiplica")
    void testMoltiplica(double a, double b, double expectedValue) {
        assertEquals(moltiplica(a, b), expectedValue);
    }

    @ParameterizedTest
    @MethodSource("generatorDividi")
    void testDividi(double a, double b, double expectedResult) {
        assertEquals(dividi(a, b), expectedResult);
    }

    @Test
    void testDividiWithException() {
        assertThrows(IllegalArgumentException.class, ()->{
            dividi(5, 0);
        }); //T5
    }

    @Test
    void testRadiceQuadrata() {
        assertEquals(radiceQuadrata(0), 0); //T1
    }

    @Test
    void testRadiceQuadrataWithException() {
        assertThrows(IllegalArgumentException.class, ()->{
            radiceQuadrata(-2);
        }); //T2
    }

    @ParameterizedTest
    @MethodSource("generatorPotenza")
    void testElevaPotenza(double base, double esponente, double expectedValue) {
        assertEquals(elevaPotenza(base, esponente), expectedValue);
    }

    @Test
    void testElevaPotenzaWithException() {
        assertThrows(IllegalArgumentException.class, ()->{
            elevaPotenza(0, -2);
        }); //T12
        assertThrows(IllegalArgumentException.class, ()->{
            elevaPotenza(0, 0);
        }); //T13
        assertThrows(IllegalArgumentException.class, ()->{
            elevaPotenza(0, -0.5);
        }); //T14
        assertThrows(IllegalArgumentException.class, ()->{
            elevaPotenza(-3, 1.8);
        });
    }

    @ParameterizedTest
    @MethodSource("generatorRadici")
    void testCalcolaRadiciEquazioneQuadratica(double a, double b, double c, List<Double> expectedValue) {
        assertEquals(calcolaRadiciEquazioneQuadratica(a, b, c), expectedValue);
    }

    @Test
    void testCalcolaRadiciEquazioneQuadraticaWithException() {
        assertThrows(IllegalArgumentException.class, ()->{
           calcolaRadiciEquazioneQuadratica(0, 2, 3);
        }); //T6
        assertThrows(IllegalArgumentException.class, ()->{
            calcolaRadiciEquazioneQuadratica(0, 0, 0);
        }); //T7
    }

    public static Stream<Arguments> generatorSomma() {
        return Stream.of(
                of(-5, -2.3, -7.3), //T1
                of(15, -50, -35), //T2
                of(0, -3.2, -3.2), //T3
                of(-10, 5, -5) //T4
        );
    }

    public static Stream<Arguments> generatorSottrai() {
        return Stream.of(
                of(8, -5, 13), //T1
                of(6.3, 12.4, -6.1000000000000005), //T2
                of(-25, -7, -18), //T3
                of(0, -9, 9) //T4
        );
    }

    public static Stream<Arguments> generatorMoltiplica() {
        return Stream.of(
                of(-8.25, 3.2, -26.400000000000002), //T1
                of(-12, -6.3, 75.6), //T2
                of(13, 0, 0) //T3
        );
    }

    public static Stream<Arguments> generatorDividi() {
        return Stream.of(
                of(2.5, 3.2, 0.78125), //T1
                of(2.5, -3.2, -0.78125), //T2
                of(-2.5, -3.2, 0.78125), //T3
                of(0, 5, 0) //T4
        );
    }

    public static Stream<Arguments> generatorPotenza() {
        return Stream.of(
                of(2.5, 3, 15.625), //T1
                of(-2.5, 3, -15.625), //T2
                of(-2.5, 2, 6.25), //T3
                of(-3.6, 0, 1), //T4
                of(4, -2, 0.0625), //T5
                of(-4, -2, 0.0625), //T6
                of(-4, -3, -0.015625), //T7
                of(6, 0.5, 2.449489742783178), //T8
                //test che non possiamo fare perché non supportato dal metodo pow della libreria math
                //of(-3, 1.8, 7.2246740558420761388565258425735), //T9
                of(0, 5, 0), //T10
                of(0, 0.6, 0) //T11
        );
    }

    public static Stream<Arguments> generatorRadici() {
        return Stream.of(
                of(1, 2, 1, new ArrayList<>() {{add(-1.0);}}), //T1
                of(-2, -6, -4, new ArrayList<>() {{add(-2.0); add(-1.0);}}), //T2
                of(1, 0, -121, new ArrayList<>() {{add(11.0); add(-11.0);}}), //T3
                of(5, 20, 0, new ArrayList<>() {{add(0.0); add(-4.0);}}), //T4
                of(1, -3, 10, new ArrayList<>()) //T5
        );
    }
}
