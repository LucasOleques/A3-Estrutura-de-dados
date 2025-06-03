package Sistema_de_Doc.Persistencia;

import java.util.TreeMap;

public class IndiceSimuladoBMais {
    private TreeMap<String, String> indice; // nome -> caminho do arquivo

    public IndiceSimuladoBMais() {
        indice = new TreeMap<>();
    }

    public void adicionarDocumento(String nome, String caminho) {
        indice.put(nome, caminho);
    }

    public String buscarCaminho(String nome) {
        return indice.get(nome);
    }

    public void listarDocumentos() {
        for (String nome : indice.keySet()) {
            System.out.println("Documento: " + nome + " | Caminho: " + indice.get(nome));
        }
    }

    public boolean contem(String nome) {
        return indice.containsKey(nome);
    }
}
