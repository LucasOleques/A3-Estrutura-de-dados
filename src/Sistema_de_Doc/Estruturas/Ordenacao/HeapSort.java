package Sistema_de_Doc.Estruturas.Ordenacao;

import Sistema_de_Doc.Documentos.Documento;
import Sistema_de_Doc.Indexador.ResultadoBusca;

import java.util.*;

public class HeapSort{
    // Metodo principal para ordenar o ArrayList de Documentos
    public static ArrayList<Documento> heapSort(ArrayList<Documento> documentos) {
        if (documentos == null || documentos.isEmpty()){
            System.out.println("Erro: documentos nao pode ser vazio");
            return null;
        }

        int n = documentos.size();

        // Constrói o max-heap
        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(documentos, n, i);
        }

        // Extrai elementos do heap um a um
        for (int i = n - 1; i > 0; i--) {
            // Move a raiz (maior elemento) para o final
            swap(documentos, 0, i);

            // Chama heapify no heap reduzido
            heapify(documentos, i, 0);
        }
        return documentos;
    }

    // Metodo para manter a propriedade do max-heap
    private static void heapify(ArrayList<Documento> documentos, int n, int i) {
        int largest = i; // Inicializa o maior como raiz
        int left = 2 * i + 1; // Filho esquerdo
        int right = 2 * i + 2; // Filho direito

        // Compara o filho esquerdo com a raiz
        if (left < n && compareDocumentos(documentos.get(left), documentos.get(largest)) > 0) {
            largest = left;
        }

        // Compara o filho direito com o maior até agora
        if (right < n && compareDocumentos(documentos.get(right), documentos.get(largest)) > 0) {
            largest = right;
        }

        // Se o maior não for a raiz
        if (largest != i) {
            swap(documentos, i, largest);
            // Recursivamente heapify a subárvore afetada
            heapify(documentos, n, largest);
        }
    }

    // Metodo para trocar dois documentos no ArrayList
    private static void swap(ArrayList<Documento> documentos, int i, int j) {
        Documento temp = documentos.get(i);
        documentos.set(i, documentos.get(j));
        documentos.set(j, temp);
    }

    // Metodo para comparar dois documentos pelo nome
    private static int compareDocumentos(Documento a, Documento b) {
        return a.getNome().compareToIgnoreCase(b.getNome());
    }
}