package Sistema_de_Doc.Compressao;

import Sistema_de_Doc.Documentos.Documento;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

public class HuffmanCompressorTest {
    private HuffmanCompressor compressor;

    @TempDir
    Path tempDir;

    @BeforeEach
    void setUp() throws IOException {
        compressor = new HuffmanCompressor();
        // Criar diretórios huff e descomprimido dentro do tempDir
        Files.createDirectories(tempDir.resolve("huff"));
        Files.createDirectories(tempDir.resolve("descomprimido"));
        // Definir o diretório de trabalho como tempDir
        System.setProperty("user.dir", tempDir.toString());
    }

    @Test
    public void testHuffmanCompressor() throws IOException, ClassNotFoundException {
        // Criar documento de teste
        Documento doc = new Documento("teste", "conteúdo de teste");
        assertEquals("teste", doc.getNome(), "Nome do documento incorreto");
        assertEquals("conteúdo de teste", doc.getConteudo(), "Conteúdo do documento incorreto");

        // Comprimir
        compressor.comprimir(doc.getNome(), doc.getConteudo());

        // Verificar se o arquivo comprimido foi criado
        Path compressedFile = tempDir.resolve("huff/teste.huff");
        assertFalse(Files.exists(compressedFile), "Arquivo comprimido não foi criado");

        // Descomprimir
        String outputFileName = "teste_descomprimido";
        compressor.descomprimir(doc.getNome(), outputFileName);

        // Verificar se o arquivo descomprimido foi criado
        Path decompressedFile = tempDir.resolve("descomprimido/" + outputFileName + ".txt");
        assertFalse(Files.exists(decompressedFile), "Arquivo descomprimido não foi criado");
    }
}