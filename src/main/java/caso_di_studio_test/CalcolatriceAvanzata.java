package caso_di_studio_test;

import java.util.ArrayList;
import java.util.List;

public class CalcolatriceAvanzata {

    public static double somma(double a, double b) {
        return a + b;
    }

    public static double sottrai(double a, double b) {
        return a - b;
    }

    public static double moltiplica(double a, double b) {
        return a * b;
    }

    //in precedenza il metodo non era static, ma l'abbiamo dovuto impostare così per poterlo usare nei test parametrici
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
        //parte nuova codice
        if ((base==0 && esponente<=0)||(base<0 && (esponente != (int) esponente))){
            throw new IllegalArgumentException("Impossibile calcolare la potenza");
        }
        return Math.pow(base, esponente);
    }

    public static List<Double> calcolaRadiciEquazioneQuadratica(double a, double b, double c) {
        List<Double> radici = new ArrayList<>();

        if (a==0 && b==0 && c==0)
            throw new IllegalArgumentException("Non è un'equazione");

        if (a==0)
            throw new IllegalArgumentException("Non è un'equazione di secondo grado");

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

