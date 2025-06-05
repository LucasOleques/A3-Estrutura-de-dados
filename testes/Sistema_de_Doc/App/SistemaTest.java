package Sistema_de_Doc.App;

import Sistema_de_Doc.Documentos.Documento;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class SistemaTest {

    @Test
    public void testAdicionarEBuscarDocumento() {
        Sistema sistema = new Sistema();
        sistema.adicionarDocumento("doc1", "conteúdo do documento");

        Documento doc = sistema.buscarDocumento("doc1");
        assertNotNull(doc);
        assertEquals("conteúdo do documento", doc.getConteudo());
    }
}
