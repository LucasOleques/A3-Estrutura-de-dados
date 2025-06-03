package Sistema_de_Doc.Estruturas.Arvores.Metadados;

import Sistema_de_Doc.Documentos.MetadadosDocumento;

import java.util.TreeMap;

public class ArvoreBMetadados {
    private TreeMap<String, MetadadosDocumento> btree = new TreeMap<>();

    public void adicionar(MetadadosDocumento meta) {
        btree.put(meta.getNome(), meta);
    }

    public MetadadosDocumento buscar(String nome) {
        return btree.get(nome);
    }

    public void remover(String nome) {
        btree.remove(nome);
    }

    public void listarTodos() {
        for (MetadadosDocumento meta : btree.values()) {
            System.out.println(meta);
            System.out.println("================================");
        }
    }
}

