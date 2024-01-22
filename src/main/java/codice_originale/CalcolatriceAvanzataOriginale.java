package codice_originale;

import java.util.ArrayList;
import java.util.List;

public class CalcolatriceAvanzataOriginale {

    public double somma(double a, double b) {
        return a + b;
    }

    public double sottrai(double a, double b) {
        return a - b;
    }

    public double moltiplica(double a, double b) {
        return a * b;
    }

    public static double dividi(double a, double b) {
        if (b == 0) {
            throw new IllegalArgumentException("Impossibile dividere per zero");
        }
        return a / b;
    }

    public static double radiceQuadrata(double a) {
        if (a < 0) {
            throw new IllegalArgumentException("Impossibile calcolare la radice quadrata di un numero negativo");
        }
        return Math.sqrt(a);
    }

    public static double elevaPotenza(double base, double esponente) {
        return Math.pow(base, esponente);
    }

    public static List<Double> calcolaRadiciEquazioneQuadratica(double a, double b, double c) {
        List<Double> radici = new ArrayList<>();
        double discriminante = b * b - 4 * a * c;

        if (discriminante > 0) {
            double radice1 = (-b + Math.sqrt(discriminante)) / (2 * a);
            double radice2 = (-b - Math.sqrt(discriminante)) / (2 * a);
            radici.add(radice1);
            radici.add(radice2);
        } else if (discriminante == 0) {
            double radiceUnica = -b / (2 * a);
            radici.add(radiceUnica);
        }

        return radici;
    }
}

