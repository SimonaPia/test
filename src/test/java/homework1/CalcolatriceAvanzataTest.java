package homework1;

import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static caso_di_studio_test.CalcolatriceAvanzata.dividi;
import static caso_di_studio_test.CalcolatriceAvanzata.radiceQuadrata;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.params.provider.Arguments.of;

public class CalcolatriceAvanzataTest {
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

    public static @NotNull Stream<Arguments> generatorDividi() {
        return Stream.of(
                of(2.5, 3.2, 0.78125), //T1
                of(2.5, -3.2, -0.78125), //T2
                of(-2.5, -3.2, 0.78125), //T3
                of(0, 5, 0) //T4
        );
    }
}
