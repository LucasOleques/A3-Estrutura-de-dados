package Sistema_de_Doc.Documentos;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.*;
import java.util.*;

public class GerenciadorDeArquivos {

    private static final String DIRETORIO_BASE = "documentos/";
    private List<Documento> documentos;

    public GerenciadorDeArquivos() {
        documentos = new ArrayList<>();
        Path path = Paths.get(DIRETORIO_BASE);
        if (!Files.exists(path)) {
            try {
                Files.createDirectory(path);
            } catch (IOException e) {
                System.err.println("Erro ao criar diretório base: " + e.getMessage());
            }
        }
    }

    public String salvarDocumento(Documento documento) {
        documentos.add(documento); // Armazena em memória também

        String caminho = DIRETORIO_BASE + documento.getNome() + ".txt";
        try (FileWriter writer = new FileWriter(caminho)) {
            writer.write(documento.getConteudo());
            System.out.println("Documento salvo com sucesso em: " + caminho);
            return caminho;
        } catch (IOException e) {
            System.err.println("Erro ao salvar o documento: " + e.getMessage());
            return null;
        }
    }

    public String obterListaDocumentos() {
        if (documentos.isEmpty()) {
            return "Nenhum documento encontrado.";
        }

        StringBuilder sb = new StringBuilder();
        for (Documento doc : documentos) {
            sb.append("Nome: ").append(doc.getNome()).append("\n");
        }
        return sb.toString();
    }

    public void ordenarDocumentosPorNome() {
        documentos.sort(Comparator.comparing(Documento::getNome));
    }

    public Documento buscarDocumento(String nome) {
        for (Documento doc : documentos) {
            if (doc.getNome().equalsIgnoreCase(nome)) {
                return doc;
            }
        }
        return null;
    }

    public String buscarPalavraEmDocumentos(String palavra) {
        StringBuilder resultado = new StringBuilder();
        for (Documento doc : documentos) {
            if (doc.getConteudo().toLowerCase().contains(palavra.toLowerCase())) {
                resultado.append("Encontrada em: ").append(doc.getNome()).append("\n");
            }
        }
        return resultado.length() > 0 ? resultado.toString() : "Palavra não encontrada em nenhum documento.";
    }

    public List<Documento> getDocumentos() {
        return documentos;
    }
}
