package Sistema_de_Doc.Indexador;

public class ResultadoBusca {
    public String nomeDocumento;
    public int frequencia;
    public String trechoRelevante;

    public ResultadoBusca(String nomeDocumento, int frequencia) {
        this.nomeDocumento = nomeDocumento;
        this.frequencia = frequencia;
        this.trechoRelevante = trechoRelevante;
    }

    public int getFrequencia() {
        return frequencia;
    }

    @Override
    public String toString() {
        return "- " + nomeDocumento + " (" + frequencia + " ocorrências)\n > " + trechoRelevante;
    }
}

