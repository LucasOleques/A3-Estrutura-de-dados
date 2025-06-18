package Sistema_de_Doc.Estruturas.Ordenacao;


import Sistema_de_Doc.Documentos.Documento;

import java.util.ArrayList;

public class MergeSort{
    public static ArrayList<Documento> mergeSort(ArrayList<Documento> lista) {
        if (lista == null || lista.size() <= 1) {
            return null;
        }
        mergeSortRecursivo(lista, 0, lista.size() - 1);
        return lista;
    }

    private static void mergeSortRecursivo(ArrayList<Documento> lista, int inicio, int fim) {
        if (inicio < fim) {
            int meio = (inicio + fim) / 2;
            mergeSortRecursivo(lista, inicio, meio);
            mergeSortRecursivo(lista, meio + 1, fim);
            merge(lista, inicio, meio, fim);
        }
    }

    private static void merge(ArrayList<Documento> lista, int inicio, int meio, int fim) {
        ArrayList<Documento> temp = new ArrayList<>();

        // Copia os elementos para um array temporário
        for (int i = inicio; i <= fim; i++) {
            temp.add(lista.get(i));
        }

        int i = 0; // Índice para a primeira metade
        int j = meio - inicio + 1; // Índice para a segunda metade
        int k = inicio; // Índice para a lista original

        // Mescla as duas metades ordenando pelo tamanho do conteúdo
        while (i <= meio - inicio && j <= fim - inicio) {
            if (temp.get(i).getConteudo().length() <= temp.get(j).getConteudo().length()) {
                lista.set(k, temp.get(i));
                i++;
            } else {
                lista.set(k, temp.get(j));
                j++;
            }
            k++;
        }

        // Copia os elementos restantes da primeira metade, se houver
        while (i <= meio - inicio) {
            lista.set(k, temp.get(i));
            i++;
            k++;
        }

        // Copia os elementos restantes da segunda metade, se houver
        while (j <= fim - inicio) {
            lista.set(k, temp.get(j));
            j++;
            k++;
        }
    }
}
