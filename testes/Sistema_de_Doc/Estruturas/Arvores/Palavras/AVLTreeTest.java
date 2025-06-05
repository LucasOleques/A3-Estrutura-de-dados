package Sistema_de_Doc.Estruturas.Arvores.Palavras;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class AVLTreeTest {

    @Test
    public void testBuscaRecursiva() {
        AVLTree arvore = new AVLTree();

        arvore.inserir("banana");
        arvore.inserir("laranja");
        arvore.inserir("abacaxi");

        assertTrue(arvore.buscar("banana"));
        assertTrue(arvore.buscar("laranja"));
        assertFalse(arvore.buscar("melancia"));
    }
}
