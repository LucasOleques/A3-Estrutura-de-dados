package Sistema_de_Doc.Estruturas.Ordenacao;

import Sistema_de_Doc.Documentos.Documento;

import java.util.ArrayList;

public class QuickSort{

    // Metodo principal para chamar o QuickSort
    public static ArrayList<Documento> ordenarPorDataMaisRecente(ArrayList<Documento> documentos) {
        if (documentos == null || documentos.size() <= 1) return null;

        quickSort(documentos, 0, documentos.size() - 1);
        return documentos;
    }

    public static void quickSort(ArrayList<Documento> documentos, int low, int high) {
        if (low < high) {
            int pi = partition(documentos, low, high);
            quickSort(documentos, low, pi - 1);
            quickSort(documentos, pi + 1, high);
        }
    }

    private static int partition(ArrayList<Documento> documentos, int low, int high) {
        Documento pivot = documentos.get(high);
        int i = low - 1;

        for (int j = low; j < high; j++) {
            // Compara datas: mais recente vem primeiro
            if (documentos.get(j).getDataCriacao().isAfter(pivot.getDataCriacao())) {
                i++;
                // Troca elementos
                Documento temp = documentos.get(i);
                documentos.set(i, documentos.get(j));
                documentos.set(j, temp);
            }
        }

        // Coloca o pivô na posição correta
        Documento temp = documentos.get(i + 1);
        documentos.set(i + 1, documentos.get(high));
        documentos.set(high, temp);

        return i + 1;
    }
}