package homework2;

import caso_di_studio_test.CalcolatriceAvanzata;
import homework1.CalcolatriceAvanzataTest;
import net.jqwik.api.*;
import net.jqwik.api.statistics.Histogram;
import net.jqwik.api.statistics.Statistics;
import net.jqwik.api.statistics.StatisticsReport;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class CalcolatriceAvanzataPropertyTestStatistiche {

    @Property
    @Report(Reporting.GENERATED)
    @StatisticsReport (format= Histogram.class)
    void testSomma(@ForAll double a, @ForAll double b) {
        String posOrNeg = a > 0 ? "positive" : "negative";
        String posOrNeg1 = b > 0 ? "positive" : "negative";
        Statistics.collect(posOrNeg, posOrNeg1);
    }
    @Property
    @Report(Reporting.GENERATED)
    @StatisticsReport (format= Histogram.class)
    void testSottrai(@ForAll double a,@ForAll double b) {
        String posOrNeg = a > 0 ? "positive" : "negative";
        String posOrNeg1 = b > 0 ? "positive" : "negative";
        Statistics.collect(posOrNeg,posOrNeg1);
    }
    @Property
    @Report(Reporting.GENERATED)
    @StatisticsReport (format= Histogram.class)
    void testMoltiplica(@ForAll double a, @ForAll double b) {
        String posOrNeg = a > 0 ? "positive" : "negative";
        String posOrNeg1 = b > 0 ? "positive" : "negative";
        Statistics.collect(posOrNeg,posOrNeg1);
    }
    @Property  //manca la parte che riguarda quante volte viene generato 0
    @Report(Reporting.GENERATED)
    @StatisticsReport (format= Histogram.class)
    void testDividiDenominatoreDiversoDaZero(@ForAll double a, @ForAll("numeriDiversiDaZero") double b) {
        double soglia = 0.2;
        String posOrNeg = a > 0 ? "positive" : "negative";
        String posOrNeg1 = b > 0 ? "positive" : "negative";
        String numVicino0 = Math.abs(b) < soglia ? "numero vicino a 0" : "numero non vicino a 0";
        Statistics.collect(posOrNeg, posOrNeg1, numVicino0);
    }

    @Provide
    Arbitrary<Double> numeriDiversiDaZero() {
        return Arbitraries.doubles().filter(b -> b != 0);
    }
    @Property
    @Report(Reporting.GENERATED)
    @StatisticsReport(format = Histogram.class)
    void testDividiDenominatoreUgualeAZero(@ForAll double a, @ForAll("numeriUgualiAZero") double b) {
        boolean isExceptionThrown = false;
        // Verifica se l'eccezione viene lanciata
        try {
            CalcolatriceAvanzata.dividi(a,b);
        } catch (IllegalArgumentException e) {
            isExceptionThrown = true;
        }

        // Raccogli la statistica in base a se l'eccezione è stata lanciata o meno
        Statistics.collect("fallimenti", isExceptionThrown);
        Statistics.collect("successi", !isExceptionThrown);
    }

    @Provide
    Arbitrary<Double> numeriUgualiAZero() {
        return Arbitraries.doubles().filter(b -> b == 0);
    }
    @Property
    @Report(Reporting.GENERATED)
    @StatisticsReport (format = Histogram.class)
    void testRadiceQuadrata(@ForAll("numeriMaggioriOUgualiAZero") double a) {
        Statistics.collect("isZero", a == 0 ? "zero" : "non-zero");
    }
    @Provide
    Arbitrary<Double> numeriMaggioriOUgualiAZero() {
        return Arbitraries.doubles().greaterOrEqual(0);
    }
    @Property
    @Report(Reporting.GENERATED)
    @StatisticsReport (format = Histogram.class)
    void testRadiceQuadrataMinoreDiZero(@ForAll("numeriMinoriDiZero") double a) {
        boolean isExceptionThrown = false;
        // Verifica se l'eccezione viene lanciata
        try {
            CalcolatriceAvanzata.radiceQuadrata(a);
        } catch (IllegalArgumentException e) {
            isExceptionThrown = true;
        }

        // Raccogli la statistica in base a se l'eccezione è stata lanciata o meno
        Statistics.collect("fallimenti", isExceptionThrown);
        Statistics.collect("successi", !isExceptionThrown);
    }
    @Provide
    Arbitrary<Double> numeriMinoriDiZero() {
        return Arbitraries.doubles().lessThan(0);
    }
    @Property
    @Report(Reporting.GENERATED)
    @StatisticsReport (format = Histogram.class)
    void testElevaPotenza(@ForAll("numeroMaggioreDiZero") double base, @ForAll double esponente) {
        String tipoEsponente = esponente > 0 ? "positivo" : (esponente < 0 ? "negativo" : "zero");
        Statistics.collect("esponente-" + tipoEsponente);

        long mod = Math.floorMod((long) esponente, 2);
        Statistics.collect(mod == 0 ? "pari" : "dispari");
    }
    @Provide
    Arbitrary<Double> numeroMaggioreDiZero() {
        return Arbitraries.doubles().greaterThan(0);
    }
    @Property
    @Report(Reporting.GENERATED)
    @StatisticsReport (format = Histogram.class)
    void testElevaPotenzaIntero(@ForAll("numeriMinoriDiZero") double base, @ForAll("esponenteIntero") double esponente) {
        // Raccogli la statistica sull'esponente
        String tipoEsponente = (esponente < 0) ? "negativo" : (esponente > 0) ? "positivo" : "zero";
        long mod = Math.floorMod((long) esponente, 2);
        Statistics.collect("tipoEsponente", tipoEsponente, mod == 0 ? "pari" : "dispari");
    }
    @Provide
    Arbitrary<Double> esponenteIntero() {
        return Arbitraries.doubles().filter(e -> e == e.intValue());
    }

    @Property
    @Report(Reporting.GENERATED)
    @StatisticsReport (format = Histogram.class)
    void testElevaPotenzaDecimale(@ForAll("numeriMinoriDiZero") double base, @ForAll("esponenteDoubleDiversoDaZero") double esponente) {
        boolean successo = true;

        try {
            CalcolatriceAvanzata.elevaPotenza(base, esponente);
        } catch (IllegalArgumentException e) {
            successo = false;
        }
        boolean isEsponenteIntero = esponente == Math.floor(esponente);

        // Raccogli le statistiche in base al tipo di esponente e al successo/fallimento
        Statistics.collect("tipoEsponente", isEsponenteIntero);
        Statistics.collect("successo", successo);

    }
    @Provide
    Arbitrary<Double> esponenteDoubleDiversoDaZero() {
        return Arbitraries.doubles().filter(e -> e != 0 && e != e.intValue());
    }
    @Property
    @Report(Reporting.GENERATED)
    @StatisticsReport (format = Histogram.class)
    void testElevaPotenzaBaseZero(@ForAll("numeriUgualiAZero") double base, @ForAll("numeroMaggioreDiZero") double esponente) {
        double soglia = 0.2;  // Puoi regolare la soglia a seconda di quanto consideri "vicino a zero"
        String numVicino0 = Math.abs(esponente) < soglia ? "numero vicino a 0" : "numero non vicino a 0";

        // Raccogli la statistica
        Statistics.collect("esponenteVicinoZero", numVicino0);
    }
    @Property
    @Report(Reporting.GENERATED)
    @StatisticsReport (format = Histogram.class)
    void testElevaPotenzaEsponenteMinore(@ForAll("numeriUgualiAZero") double base, @ForAll("numeroMinoreOUgualeAZero") double esponente) {
        boolean isExceptionThrown = false;
        // Verifica se l'eccezione viene lanciata
        try {
            CalcolatriceAvanzata.elevaPotenza(base, esponente);
        } catch (IllegalArgumentException e) {
            isExceptionThrown = true;
        }

        // Raccogli la statistica in base a se l'eccezione è stata lanciata o meno
        Statistics.collect("fallimenti", isExceptionThrown);
    }
    @Provide
    Arbitrary<Double> numeroMinoreOUgualeAZero() {
        return Arbitraries.doubles().lessOrEqual(0);
    }
    @Property
    @Report(Reporting.GENERATED)
    @StatisticsReport (format = Histogram.class)
    void testCalcolaRadiciEquazioneQuadratica(@ForAll("numeriDiversiDaZero") double a, @ForAll double b, @ForAll double c) {
        List<Double> radici = new ArrayList<>();
        double discriminante = b * b - 4 * a * c;
       // Statistica: quante volte il discriminante è minore di 0 e non ha radici
        Statistics.collect("discriminanteMinoreDiZero_senzaRadici", discriminante < 0);

        // Statistica: quante volte il discriminante è uguale a 0
        Statistics.collect("discriminanteUgualeAZero_radiceUnica", discriminante == 0);

        // Statistica: quante volte b e c sono uguali a 0
        Statistics.collect("bECUgualiAZero_casoSpeciale", b == 0 && c == 0);
    }
    @Property
    @Report(Reporting.GENERATED)
    @StatisticsReport (format = Histogram.class)
    void testCalcolaRadiciEquazioneQuadraticaAUgualeZero(@ForAll("numeriUgualiAZero") double a, @ForAll double b, @ForAll double c) {
        boolean throwsException = false;

        try {
            CalcolatriceAvanzata.calcolaRadiciEquazioneQuadratica(a, b, c);
        } catch (IllegalArgumentException e) {
            throwsException = true;
        }

        // Statistica: quante volte il metodo lancia un'eccezione
        Statistics.collect("fallimento_calcolaRadiciEquazioneQuadraticaAUgualeZero", throwsException);
    }
    @Property
    @Report(Reporting.GENERATED)
    @StatisticsReport (format = Histogram.class)
    void testCalcolaRadiciEquazioneQuadraticaTuttiUgualeZero(@ForAll("numeriUgualiAZero") double a, @ForAll("numeriUgualiAZero") double b, @ForAll("numeriUgualiAZero") double c) {
        boolean eccezioneLanciata = false;
        try {
            CalcolatriceAvanzata.calcolaRadiciEquazioneQuadratica(a, b, c);
        } catch (IllegalArgumentException e) {
            eccezioneLanciata = true;
        }
        // Raccogli le statistiche in base alla presenza o assenza di eccezioni
        Statistics.collect("eccezioneLanciata_calcolaRadiciEquazioneQuadraticaTuttiUgualeZero", eccezioneLanciata);
    }
}
