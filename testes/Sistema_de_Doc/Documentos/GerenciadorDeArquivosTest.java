package Sistema_de_Doc.Documentos;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class GerenciadorDeArquivosTest {

    @Test
    public void testSalvarDocumento() {
        GerenciadorDeArquivos ger = new GerenciadorDeArquivos();
        Documento doc = new Documento("docTeste", "exemplo de conteúdo");

        String caminho = ger.salvarDocumento(doc);
        assertNotNull(caminho);
        assertTrue(caminho.endsWith(".txt"));
    }
}
