package Sistema_de_Doc.App;

import Sistema_de_Doc.Documentos.*;
import Sistema_de_Doc.Persistencia.IndiceSimuladoBMais;

public class Sistema {
    private GerenciadorDeArquivos gerenciador = new GerenciadorDeArquivos();
    private IndiceSimuladoBMais indice = new IndiceSimuladoBMais();

    public void adicionarDocumento(String nome, String conteudo) {
        Documento doc = new Documento(nome, conteudo);
        String caminho = gerenciador.salvarDocumento(doc);
        if (caminho != null) {
            indice.adicionarDocumento(doc.getNome(), caminho);
        }
    }

    public String listarDocumentos() {
        return gerenciador.obterListaDocumentos();
    }

    public void ordenarDocumentos() {
        gerenciador.ordenarDocumentosPorNome();
    }

    public Documento buscarDocumento(String nome) {
        return gerenciador.buscarDocumento(nome);
    }

    public String buscarPalavra(String palavra) {
        return gerenciador.buscarPalavraEmDocumentos(palavra);
    }
}
