package homework1;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static caso_di_studio_test.CalcolatriceAvanzataOriginale.*;

public class CalcolatriceAvanzataTestDiProva {
    @Test
    void testDividi1() {
        assertEquals(dividi(5, 2), 2.5);
    }

    @Test
    void testDividi2() {
        assertEquals(dividi(0, 2), 0);
    }

    @Test
    void testDividi3() {
        assertEquals(dividi(-5, 2.3), -2.173913043478261);
    }

    @Test
    void testRadiceQaudrata1() {
        assertEquals(radiceQuadrata(4), 2);
    }

    @Test
    void testRadiceQaudrata2() {
        assertEquals(radiceQuadrata(17), 4.123105625617661);
    }

    @Test
    void testRadiceQaudrata3() {
        assertEquals(radiceQuadrata(2.3), 1.51657508881031);
    }

    @Test
    void testElevaPotenza1() {
        assertEquals(elevaPotenza(2, 5.2), 36.75834735990512);
    }

    @Test
    void testElevaPotenza2() {
        assertEquals(elevaPotenza(-5, 8), 390625);
    }

    @Test
    void testElevaPotenza3() {
        assertEquals(elevaPotenza(2.3, 3), 12.166999999999998);
    }
}
