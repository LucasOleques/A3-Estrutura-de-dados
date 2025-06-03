package Sistema_de_Doc.Testes;

import Sistema_de_Doc.Estruturas.Ordenacao.HeapSort;
import Sistema_de_Doc.Indexador.ResultadoBusca;

import java.util.ArrayList;
import java.util.List;

public class TesteHeapSort {
    public static void main(String[] args) {
        List<ResultadoBusca> resultados = new ArrayList<>();

        resultados.add(new ResultadoBusca("doc1.txt", 3));
        resultados.add(new ResultadoBusca("doc2.txt", 5));
        resultados.add(new ResultadoBusca("doc3.txt", 2));

        System.out.println("Antes da ordenação:");
        resultados.forEach(System.out::println);

        HeapSort.ordenacao(resultados);

        System.out.println("\nDepois da ordenação por frequência:");
        resultados.forEach(System.out::println);
    }
}

