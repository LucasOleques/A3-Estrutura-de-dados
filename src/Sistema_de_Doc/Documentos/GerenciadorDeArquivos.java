package Sistema_de_Doc.Documentos;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class GerenciadorDeArquivos {

    private static final String DIRETORIO_BASE = "documentos/";

    public GerenciadorDeArquivos() {
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
}
