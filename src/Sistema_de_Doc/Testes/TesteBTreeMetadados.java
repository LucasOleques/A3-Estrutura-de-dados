package Sistema_de_Doc.Testes;

import Sistema_de_Doc.Documentos.MetadadosDocumento;
import Sistema_de_Doc.Estruturas.Arvores.Metadados.ArvoreBMetadados;

public class TesteBTreeMetadados {
    public static void main(String[] args) {
        ArvoreBMetadados arvore = new ArvoreBMetadados();

        arvore.adicionar(new MetadadosDocumento("doc1.txt", "/docs/doc1.txt", 1024, 420));
        arvore.adicionar(new MetadadosDocumento("doc2.txt", "/docs/doc2.txt", 2048, 550));

        System.out.println("Todos os metadados:");
        arvore.listarTodos();

        System.out.println("\nBuscar doc2.txt:");
        MetadadosDocumento meta = arvore.buscar("doc2.txt");
        System.out.println(meta);
    }
}
