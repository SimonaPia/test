package homework2;

import caso_di_studio_test.CalcolatriceAvanzata;
import net.jqwik.api.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CalcolatriceAvanzataPropertyTest {
    @Property
    void testSomma(@ForAll double a, @ForAll double b) {
        assertEquals(a + b, CalcolatriceAvanzata.somma(a, b));
    }
    @Property
    void testSottrai(@ForAll double a, @ForAll double b) {
        assertEquals(a - b, CalcolatriceAvanzata.sottrai(a, b));
    }
    @Property
    void testMoltiplica(@ForAll double a, @ForAll double b) {
        assertEquals(a * b, CalcolatriceAvanzata.moltiplica(a, b));
    }
    @Property
    @Report(Reporting.GENERATED)
    void testDividiDenominatoreDiversoDaZero(@ForAll double a, @ForAll("numeriDiversiDaZero") double b) {
        assertEquals(a / b, CalcolatriceAvanzata.dividi(a, b));
    }
    @Provide
    Arbitrary<Double> numeriDiversiDaZero() {
        return Arbitraries.doubles().filter(b -> b != 0);
    }
    @Property
    @Report(Reporting.GENERATED)
    void testDividiDenominatoreUgualeAZero(@ForAll double a, @ForAll("numeriUgualiAZero") double b) {
        assertThrows(IllegalArgumentException.class, ()->{
            CalcolatriceAvanzata.dividi(a, b);
        });
    }
    @Provide
    Arbitrary<Double> numeriUgualiAZero() {
        return Arbitraries.doubles().filter(b -> b == 0);
    }
    @Property
    @Report(Reporting.GENERATED)
    void testRadiceQuadrata(@ForAll("numeriMaggioriOUgualiAZero") double a) {
        assertEquals(Math.sqrt(a), CalcolatriceAvanzata.radiceQuadrata(a));
    }
    @Provide
    Arbitrary<Double> numeriMaggioriOUgualiAZero() {
        return Arbitraries.doubles().greaterOrEqual(0);
    }
    @Property
    @Report(Reporting.GENERATED)
    void testRadiceQuadrataMinoreDiZero(@ForAll("numeriMinoriDiZero") double a) {
        assertThrows(IllegalArgumentException.class, ()->{
           CalcolatriceAvanzata.radiceQuadrata(a);
        });
    }
    @Provide
    Arbitrary<Double> numeriMinoriDiZero() {
        return Arbitraries.doubles().lessThan(0);
    }
    @Property
    @Report(Reporting.GENERATED)
    void testElevaPotenza(@ForAll("numeroMaggioreDiZero") double base, @ForAll double esponente) {
        assertEquals(Math.pow(base, esponente), CalcolatriceAvanzata.elevaPotenza(base, esponente));
    }
    @Provide
    Arbitrary<Double> numeroMaggioreDiZero() {
        return Arbitraries.doubles().greaterThan(0);
    }
    @Property
    @Report(Reporting.GENERATED)
    void testElevaPotenzaIntero(@ForAll("numeriMinoriDiZero") double base, @ForAll int esponente) {
        assertEquals(Math.pow(base, esponente), CalcolatriceAvanzata.elevaPotenza(base, esponente));
    }
    @Property
    @Report(Reporting.GENERATED)
    void testElevaPotenzaDecimale(@ForAll("numeriMinoriDiZero") double base, @ForAll("esponenteDoubleDiversoDaZero") double esponente) {
        assertThrows(IllegalArgumentException.class, ()->{
           CalcolatriceAvanzata.elevaPotenza(base, esponente);
        });
    }
    @Provide
    Arbitrary<Double> esponenteDoubleDiversoDaZero() {
        return Arbitraries.doubles().filter(e -> e != 0 && e != e.intValue());
    }
    @Property
    @Report(Reporting.GENERATED)
    void testElevaPotenzaBaseZero(@ForAll("numeriUgualiAZero") double base, @ForAll("numeroMaggioreDiZero") double esponente) {
        assertEquals(Math.pow(base, esponente), CalcolatriceAvanzata.elevaPotenza(base, esponente));
    }
    @Property
    @Report(Reporting.GENERATED)
    void testElevaPotenzaEsponenteMinore(@ForAll("numeriUgualiAZero") double base, @ForAll("numeroMinoreOUgualeAZero") double esponente) {
        assertThrows(IllegalArgumentException.class, ()->{
           CalcolatriceAvanzata.elevaPotenza(base, esponente);
        });
    }
    @Provide
    Arbitrary<Double> numeroMinoreOUgualeAZero() {
        return Arbitraries.doubles().lessOrEqual(0);
    }
}
