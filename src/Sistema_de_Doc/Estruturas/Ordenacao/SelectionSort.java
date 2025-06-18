package Sistema_de_Doc.Estruturas.Ordenacao;

import Sistema_de_Doc.Documentos.Documento;

import java.util.ArrayList;

public class SelectionSort{


    public static ArrayList<Documento> selectionSort(ArrayList<Documento> documentos) {

        if (documentos == null || documentos.size() <= 1) {
            return null;
        }

        int n = documentos.size();

        for (int i = 0; i < n - 1; i++) {
            // Encontrar o índice do maior elemento (para ordem z-a)
            int maxIndex = i;
            for (int j = i + 1; j < n; j++) {
                if (documentos.get(j).getNome().compareTo(documentos.get(maxIndex).getNome()) > 0) {
                    maxIndex = j;
                }
            }

            // Trocar o maior elemento encontrado com o elemento na posição i
            if (maxIndex != i) {
                Documento temp = documentos.get(i);
                documentos.set(i, documentos.get(maxIndex));
                documentos.set(maxIndex, temp);
            }
        }
        return documentos;
    }
}

