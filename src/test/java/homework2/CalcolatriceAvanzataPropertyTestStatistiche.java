package homework2;

import caso_di_studio_test.CalcolatriceAvanzata;
import net.jqwik.api.ForAll;
import net.jqwik.api.Property;
import net.jqwik.api.Report;
import net.jqwik.api.Reporting;
import net.jqwik.api.statistics.Histogram;
import net.jqwik.api.statistics.Statistics;
import net.jqwik.api.statistics.StatisticsReport;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CalcolatriceAvanzataPropertyTestStatistiche {
    @Property
    @StatisticsReport (format= Histogram.class)
    void testSomma(@ForAll double a, @ForAll double b) {
        String posOrNeg = a > 0 ? "positive" : "negative";
        String posOrNeg1 = b > 0 ? "positive" : "negative";
        Statistics.collect(posOrNeg, posOrNeg1);
    }
    @Property
    @StatisticsReport (format= Histogram.class)
    void testSottrai(@ForAll double a,@ForAll double b) {
        String posOrNeg = a > 0 ? "positive" : "negative";
        String posOrNeg1 = b > 0 ? "positive" : "negative";
        Statistics.collect(posOrNeg,posOrNeg1);
    }
    @Property
    @StatisticsReport (format= Histogram.class)
    void testMoltiplica(@ForAll double a, @ForAll double b) {
        String posOrNeg = a > 0 ? "positive" : "negative";
        String posOrNeg1 = b > 0 ? "positive" : "negative";
        Statistics.collect(posOrNeg,posOrNeg1);
    }
    @Property  //manca la parte che riguarda quante volte viene generato 0
    @Report(Reporting.GENERATED)
    @StatisticsReport (format= Histogram.class)
    void testDividiDenominatoreDiversoDaZero(@ForAll double a, @ForAll double b) {
        String posOrNeg = a > 0 ? "positive" : "negative";
        String posOrNeg1 = b != 0 ? "positive" : "negative";
        Statistics.collect(posOrNeg,posOrNeg1);

    }
    @Property
    @Report(Reporting.GENERATED)
    @StatisticsReport (format = Histogram.class)
    void testDividiDenominatoreUgualeAZero (@ForAll double a, @ForAll double b){
        try {
            CalcolatriceAvanzata.dividi(a, b);
        } catch (IllegalArgumentException e) {
            // Se l'eccezione viene lanciata, conta come un fallimento
            Statistics.collect("denominatoreUgualeAZero", 1);
        }
    }


}
