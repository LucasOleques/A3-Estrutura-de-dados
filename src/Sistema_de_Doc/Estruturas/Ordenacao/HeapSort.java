package Sistema_de_Doc.Estruturas.Ordenacao;

import Sistema_de_Doc.Indexador.ResultadoBusca;

import java.util.*;

public class HeapSort {

    public static void ordenacao(List<ResultadoBusca> resultados) {
        int n = resultados.size();

        // Montar max heap
        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(resultados, n, i);
        }

        // Extrair elementos um a um
        for (int i = n - 1; i >= 0; i--) {
            Collections.swap(resultados, 0, i); // mover maior para o final
            heapify(resultados, i, 0); // reconstroi heap
        }
    }

    private static void heapify(List<ResultadoBusca> resultados, int n, int i) {
        int maior = i;
        int esq = 2 * i + 1;
        int dir = 2 * i + 2;

        if (esq < n && resultados.get(esq).frequencia > resultados.get(maior).frequencia)
            maior = esq;

        if (dir < n && resultados.get(dir).frequencia > resultados.get(maior).frequencia)
            maior = dir;

        if (maior != i) {
            Collections.swap(resultados, i, maior);
            heapify(resultados, n, maior);
        }
    }
    public static void ordenacao(int[] array) {
        List<ResultadoBusca> lista = new ArrayList<>();
        for (int val : array) {
            lista.add(new ResultadoBusca("doc", val)); // usa val como frequência fictícia
        }

        ordenacao(lista); // reutiliza o método existente

        for (int i = 0; i < array.length; i++) {
            array[i] = lista.get(i).getFrequencia(); // extrai valor ordenado
        }
    }
}

