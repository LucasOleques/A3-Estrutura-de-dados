package Sistema_de_Doc.Estruturas.Ordenacao;


public class MergeSort {
    public static void ordenar(int[] arr) {
        mergeSort(arr, 0, arr.length - 1);
    }

    private static void mergeSort(int[] arr, int esq, int dir) {
        if (esq < dir) {
            int meio = (esq + dir) / 2;
            mergeSort(arr, esq, meio);
            mergeSort(arr, meio + 1, dir);
            intercalar(arr, esq, meio, dir);
        }
    }

    private static void intercalar(int[] arr, int esq, int meio, int dir) {
        int[] temp = new int[dir - esq + 1];
        int i = esq, j = meio + 1, k = 0;

        while (i <= meio && j <= dir)
            temp[k++] = (arr[i] <= arr[j]) ? arr[i++] : arr[j++];

        while (i <= meio) temp[k++] = arr[i++];
        while (j <= dir) temp[k++] = arr[j++];

        System.arraycopy(temp, 0, arr, esq, temp.length);
    }
}
