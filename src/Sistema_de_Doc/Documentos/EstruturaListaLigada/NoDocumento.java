package Sistema_de_Doc.Documentos.EstruturaListaLigada;

import Sistema_de_Doc.Documentos.Documento;

public class NoDocumento {
    public Documento documento;
    public NoDocumento proximo;

    public NoDocumento(Documento documento){
        this.documento = documento;
        this.proximo = null;
    }
}
