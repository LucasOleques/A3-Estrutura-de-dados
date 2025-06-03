package Sistema_de_Doc.Estruturas.Ordenacao;

public class QuickSort {

    public static void ordenar(int[] arr) {
        quickSort(arr, 0, arr.length - 1);
    }

    private static void quickSort(int[] arr, int inicio, int fim) {
        if (inicio < fim) {
            int pivoIndex = particionar(arr, inicio, fim);
            quickSort(arr, inicio, pivoIndex - 1);
            quickSort(arr, pivoIndex + 1, fim);
        }
    }

    private static int particionar(int

