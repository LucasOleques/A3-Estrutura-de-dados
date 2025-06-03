package Sistema_de_Doc.Documentos;

import java.time.LocalDateTime;

public class Documento {
    private String nome;
    private String conteudo;
    private LocalDateTime dataCriacao;

    public Documento(String nome, String conteudo) {
        this.nome = nome;
        this.conteudo = conteudo;
        this.dataCriacao = LocalDateTime.now();
    }

    public String getNome() {
        return nome;
    }

    public String getConteudo() {
        return conteudo;
    }

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }
}
