package Sistema_de_Doc.Documentos;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class DocumentoTest {

    @Test
    public void testCriacaoDocumento() {
        Documento doc = new Documento("teste", "conteúdo");
        assertEquals("teste", doc.getNome());
        assertEquals("conteúdo", doc.getConteudo());
    }
}
