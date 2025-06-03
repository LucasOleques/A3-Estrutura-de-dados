package Sistema_de_Doc.Testes;

import Sistema_de_Doc.Estruturas.Arvores.Usuarios.RedBlackTree;
import Sistema_de_Doc.Usuario.Usuario;

public class TesteRubroNegra {
    public static void main(String[] args) {
        RedBlackTree usuarios = new RedBlackTree();

        usuarios.inserir(new Usuario("João", "joao@email.com"));
        usuarios.inserir(new Usuario("Maria", "maria@email.com"));
        usuarios.inserir(new Usuario("Carlos", "carlos@email.com"));

        System.out.println("Usuários cadastrados:");
        usuarios.imprimir();

        System.out.println("\nBusca por Maria:");
        Usuario u = usuarios.buscar("Maria");
        System.out.println(u != null ? u : "Usuário não encontrado");
    }
}
