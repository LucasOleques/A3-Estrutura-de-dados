package Sistema_de_Doc.Testes;

import Sistema_de_Doc.Estruturas.Arvores.Palavras.AVLTree;

public class TesteAVL {
    public static void main(String[] args) {
        AVLTree arvore = new AVLTree();

        arvore.inserir("raposa");
        arvore.inserir("cachorro");
        arvore.inserir("gato");
        arvore.inserir("zebra");
        arvore.inserir("abelha");

        System.out.println("Palavras em ordem:");
        arvore.imprimirEmOrdem();

        System.out.println("\nContém 'gato'? " + arvore.contem("gato"));
        System.out.println("Contém 'urso'? " + arvore.contem("urso"));
    }
}
