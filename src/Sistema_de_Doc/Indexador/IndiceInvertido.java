package Sistema_de_Doc.Indexador;

import java.util.*;

public class IndiceInvertido {
    private Map<String, Map<String, Integer>> indice = new HashMap<>();

    public void indexarDocumento(String nomeDocumento, String conteudo) {
        String[] palavras = conteudo.toLowerCase().replaceAll("[^a-zà-ú0-9 ]", "").split("\\s+");

        for (String palavra : palavras) {
            if (palavra.isBlank()) continue;

            indice.putIfAbsent(palavra, new HashMap<>());
            Map<String, Integer> documentos = indice.get(palavra);
            documentos.put(nomeDocumento, documentos.getOrDefault(nomeDocumento, 0) + 1);
        }
    }

    public Map<String, Integer> buscar(String palavra) {
        return indice.getOrDefault(palavra.toLowerCase(), new HashMap<>());
    }

    public void exibirIndice() {
        for (String palavra : indice.keySet()) {
            System.out.println(palavra + " → " + indice.get(palavra));
        }
    }
}
