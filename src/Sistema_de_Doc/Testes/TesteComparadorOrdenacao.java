package Sistema_de_Doc.Testes;


import Sistema_de_Doc.Estruturas.Ordenacao.*;
import Sistema_de_Doc.Util.*;

public class TesteComparadorOrdenacao {

    public static void main(String[] args) {
        int[] pequeno = gerarArrayAleatorio(1000);
        int[] medio = gerarArrayAleatorio(10000);
        int[] grande = gerarArrayAleatorio(100000);

        System.out.println("📦 Comparação com 1.000 elementos:");
        testarTodos(pequeno);
        System.out.println("\n📦 Comparação com 10.000 elementos:");
        testarTodos(medio);
        System.out.println("\n📦 Comparação com 100.000 elementos:");
        testarTodos(grande);
    }

    private static void testarTodos(int[] dados) {
        Benchmark.medirTempo("SelectionSort", dados, SelectionSort::ordenar);
        Benchmark.medirTempo("MergeSort", dados, MergeSort::ordenar);
        Benchmark.medirTempo("QuickSort", dados, QuickSort::ordenar);
        Benchmark.medirTempo("HeapSort", dados, HeapSort::ordenacao);
    }

    private static int[] gerarArrayAleatorio(int tamanho) {
        int[] array = new int[tamanho];
        for (int i = 0; i < tamanho; i++) {
            array[i] = (int) (Math.random() * 10000);
        }
        return array;
    }
}
