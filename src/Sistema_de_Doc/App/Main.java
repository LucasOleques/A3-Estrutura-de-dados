package Sistema_de_Doc.App;

import Sistema_de_Doc.Documentos.Documento;
import Sistema_de_Doc.Documentos.GerenciadorDeArquivos;
import Sistema_de_Doc.Persistencia.*;

public class Main {
    public static void main(String[] args) {
        Documento doc = new Documento("doc1", "Conteúdo do documento...");
        GerenciadorDeArquivos arquivo = new GerenciadorDeArquivos();
        IndiceSimuladoBMais indice = new IndiceSimuladoBMais();

        String caminho = arquivo.salvarDocumento(doc);
        if (caminho != null) {
            indice.adicionarDocumento(doc.getNome(), caminho);
        }

        indice.listarDocumentos(); // Mostra os documentos indexados
    }
}

