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

    public StringBuilder listarDocumentos() {
        //return gerenciador.obterListaDocumentos();
        return gerenciador.getDocumentos();
    }

    public void ordenarDocumentos(int opcao) {
        switch (opcao) {
            case 0:
                gerenciador.ordenarPorHeap();
                break;
            case 1:
                gerenciador.ordenarPorMerge();
                break;
            case 2:
                gerenciador.ordenarPorQuick();
                break;
            case 3:
                gerenciador.ordenarPorSelection();
                break;
            default:
                break;
        }
        //gerenciador.ordenarDocumentosPorNome();
    }

    public Documento buscarDocumento(String nome, String tipo) {
        return gerenciador.buscarDocumento(nome, tipo);
    }

    public String buscarPalavra(String palavra) {
        return gerenciador.buscarPalavraEmDocumentos(palavra);
    }
}