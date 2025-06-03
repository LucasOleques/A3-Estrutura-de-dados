package Sistema_de_Doc.Documentos;

import java.time.LocalDateTime;

public class MetadadosDocumento {
    private String nome;
    private String caminho;
    private long tamanhoOriginal;
    private long tamanhoComprimido;
    private LocalDateTime dataCriacao;
    private LocalDateTime dataModificacao;

    public MetadadosDocumento(String nome, String caminho, long tamanhoOriginal, long tamanhoComprimido) {
        this.nome = nome;
        this.caminho = caminho;
        this.tamanhoOriginal = tamanhoOriginal;
        this.tamanhoComprimido = tamanhoComprimido;
        this.dataCriacao = LocalDateTime.now();
        this.dataModificacao = LocalDateTime.now();
    }

    public void atualizarModificacao() {
        this.dataModificacao = LocalDateTime.now();
    }

    public String getNome() {
        return nome;
    }

    public String getCaminho() {
        return caminho;
    }

    public long getTamanhoOriginal() {
        return tamanhoOriginal;
    }

    public long getTamanhoComprimido() {
        return tamanhoComprimido;
    }

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }

    public LocalDateTime getDataModificacao() {
        return dataModificacao;
    }

    @Override
    public String toString() {
        return String.format(
                "Documento: %s\nCaminho: %s\nOriginal: %d bytes\nComprimido: %d bytes\nCriado: %s\nModificado: %s",
                nome, caminho, tamanhoOriginal, tamanhoComprimido, dataCriacao, dataModificacao
        );
    }
}

