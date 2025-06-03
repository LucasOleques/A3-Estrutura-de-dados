package Sistema_de_Doc.Indexador;

import java.util.*;

public class SuffixArray {
    private final String texto;
    private final int[] sufixos;
    private final int[] lcp;

    public SuffixArray(String texto) {
        this.texto = texto;
        this.sufixos = construirSuffixArray(texto);
        this.lcp = construirLCPArray(texto, sufixos);
    }

    private int[] construirSuffixArray(String texto) {
        int n = texto.length();
        Suffix[] sufixos = new Suffix[n];

        for (int i = 0; i < n; i++) {
            sufixos[i] = new Suffix(i, texto.substring(i));
        }

        Arrays.sort(sufixos, Comparator.comparing(s -> s.sufixo));

        int[] resultado = new int[n];
        for (int i = 0; i < n; i++) {
            resultado[i] = sufixos[i].indice;
        }

        return resultado;
    }

    private int[] construirLCPArray(String texto, int[] sufixos) {
        int n = texto.length();
        int[] lcp = new int[n];
        lcp[0] = 0;

        for (int i = 1; i < n; i++) {
            int len = 0;
            int a = sufixos[i - 1];
            int b = sufixos[i];

            while (a + len < n && b + len < n && texto.charAt(a + len) == texto.charAt(b + len)) {
                len++;
            }

            lcp[i] = len;
        }

        return lcp;
    }

    public List<Integer> buscarPadrao(String padrao) {
        List<Integer> ocorrencias = new ArrayList<>();
        int esq = 0, dir = texto.length() - 1;

        while (esq <= dir) {
            int meio = (esq + dir) / 2;
            String sufixo = texto.substring(sufixos[meio]);

            int cmp = sufixo.compareTo(padrao);
            if (sufixo.startsWith(padrao)) {
                // Encontrado, busca múltiplas ocorrências ao redor
                for (int i = meio; i >= 0 && texto.substring(sufixos[i]).startsWith(padrao); i--) {
                    ocorrencias.add(sufixos[i]);
                }
                for (int i = meio + 1; i < texto.length() && texto.substring(sufixos[i]).startsWith(padrao); i++) {
                    ocorrencias.add(sufixos[i]);
                }
                break;
            } else if (cmp < 0) {
                esq = meio + 1;
            } else {
                dir = meio - 1;
            }
        }

        return ocorrencias;
    }

    private static class Suffix {
        int indice;
        String sufixo;

        public Suffix(int indice, String sufixo) {
            this.indice = indice;
            this.sufixo = sufixo;
        }
    }

    public int[] getSuffixArray() {
        return sufixos;
    }

    public int[] getLCPArray() {
        return lcp;
    }
}

