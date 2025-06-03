package Sistema_de_Doc.Documentos.EstruturaListaLigada;

import Sistema_de_Doc.Documentos.Documento;

public class ListaDocumento {
    private NoDocumento inicio;

    public void adicionar(Documento documento){
        NoDocumento novo = new NoDocumento(documento);
        if(inicio == null) {
            inicio = novo;
        } else {
            NoDocumento atual = inicio;
            while (atual.proximo != null ){
                atual = atual.proximo;
            }
            atual.proximo = novo;
        }
    }

    public void listarDocumentos() {
        NoDocumento atual = inicio;
        while (atual != null) {
            System.out.println("Documento: " + atual.documento.getTitulo());
            atual = atual.proximo;
        }
    }

    public Documento buscarPorNome (String titulo) {
        NoDocumento atual = inicio;
        while (atual != null){
            if(atual.documento.getTitulo().equals(titulo)){
                return atual.documento;
            }
            atual = atual.proximo;
        }
        return null;
    }

    public NoDocumento getInicio(){
        return inicio;
    }
}
