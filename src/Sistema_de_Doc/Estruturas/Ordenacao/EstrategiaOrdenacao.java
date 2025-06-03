package Sistema_de_Doc.Estruturas.Ordenacao;

public class EstrategiaOrdenacao {

    public static void ordenar(int[] arr, String criterio) {
        int tamanho = arr.length;

        if (tamanho <= 1000) {
            // Para listas pequenas, SelectionSort é simples e suficiente
            SelectionSort.ordenar(arr);
        } else if (criterio.equalsIgnoreCase("frequencia")) {
            // HeapSort é ideal para ordenar por relevância/ocorrência
            HeapSort.ordenacao(arr);
        } else if (criterio.equalsIgnoreCase("nome")) {
            // QuickSort funciona bem para dados textuais em grande volume
            QuickSort.ordenar(arr);
        } else {
            // MergeSort como fallback seguro e estável
            MergeSort.ordenar(arr);
        }
    }
}
