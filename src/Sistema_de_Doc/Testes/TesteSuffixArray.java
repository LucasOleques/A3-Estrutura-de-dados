package Sistema_de_Doc.Testes;

import Sistema_de_Doc.Indexador.SuffixArray;

import java.util.List;

public class TesteSuffixArray {
    public static void main(String[] args) {
        String texto = "A raposa rápida pula sobre o cão preguiçoso";
        SuffixArray sa = new SuffixArray(texto.toLowerCase());

        String padrao = "raposa";
        List<Integer> ocorrencias = sa.buscarPadrao(padrao);

        System.out.println("Ocorrências de \"" + padrao + "\":");
        for (int idx : ocorrencias) {
            System.out.println("... " + texto.substring(idx));
        }
    }
}

