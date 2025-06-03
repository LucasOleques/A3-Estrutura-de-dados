package Sistema_de_Doc.Util;

import java.util.function.Consumer;

public class Benchmark {

    public static void medirTempo(String nomeAlgoritmo, int[] dados, Consumer<int[]> algoritmo) {
        int[] copia = dados.clone();
        long inicio = System.nanoTime();
        algoritmo.accept(copia);
        long fim = System.nanoTime();

        double tempoMs = (fim - inicio) / 1_000_000.0;
        System.out.printf("%s demorou %.4f ms%n", nomeAlgoritmo, tempoMs);
    }
}
