package Sistema_de_Doc.Estruturas.Ordenacao;

public class SelectionSort {

    public static void ordenar(int[] arr) {
        int n = arr.length;

        for (int i = 0; i < n - 1; i++) {
            int indiceMenor = i;
            for (int j = i + 1; j < n; j++) {
                if (arr[j] < arr[indiceMenor]) {
                    indiceMenor = j;
                }
            }

            if (indiceMenor != i) {
                int temp = arr[i];
                arr[i] = arr[indiceMenor];
                arr[indiceMenor] = temp;
            }
        }
    }
}

