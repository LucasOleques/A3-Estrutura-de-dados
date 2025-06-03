package Sistema_de_Doc.Estruturas.Arvores.Usuarios;

import Sistema_de_Doc.Usuario.Usuario;

public class RedBlackTree {

    private static final boolean RED = true;
    private static final boolean BLACK = false;

    private class Node {
        Usuario usuario;
        Node esquerda, direita;
        boolean cor;

        Node(Usuario usuario) {
            this.usuario = usuario;
            this.cor = RED;
        }
    }

    private Node raiz;

    private boolean isRed(Node no) {
        return no != null && no.cor == RED;
    }

    private Node rotacaoEsquerda(Node h) {
        Node x = h.direita;
        h.direita = x.esquerda;
        x.esquerda = h;
        x.cor = h.cor;
        h.cor = RED;
        return x;
    }

    private Node rotacaoDireita(Node h) {
        Node x = h.esquerda;
        h.esquerda = x.direita;
        x.direita = h;
        x.cor = h.cor;
        h.cor = RED;
        return x;
    }

    private void inverterCores(Node h) {
        h.cor = RED;
        h.esquerda.cor = BLACK;
        h.direita.cor = BLACK;
    }

    public void inserir(Usuario usuario) {
        raiz = inserir(raiz, usuario);
        raiz.cor = BLACK;
    }

    private Node inserir(Node h, Usuario usuario) {
        if (h == null)
            return new Node(usuario);

        int cmp = usuario.getNome().compareToIgnoreCase(h.usuario.getNome());
        if (cmp < 0) h.esquerda = inserir(h.esquerda, usuario);
        else if (cmp > 0) h.direita = inserir(h.direita, usuario);
        else h.usuario = usuario; // Atualiza info se já existir

        if (isRed(h.direita) && !isRed(h.esquerda)) h = rotacaoEsquerda(h);
        if (isRed(h.esquerda) && isRed(h.esquerda.esquerda)) h = rotacaoDireita(h);
        if (isRed(h.esquerda) && isRed(h.direita)) inverterCores(h);

        return h;
    }

    public Usuario buscar(String nome) {
        Node atual = raiz;
        while (atual != null) {
            int cmp = nome.compareToIgnoreCase(atual.usuario.getNome());
            if (cmp < 0) atual = atual.esquerda;
            else if (cmp > 0) atual = atual.direita;
            else return atual.usuario;
        }
        return null;
    }

    public void imprimir() {
        imprimir(raiz);
    }

    private void imprimir(Node no) {
        if (no != null) {
            imprimir(no.esquerda);
            System.out.println(no.usuario);
            imprimir(no.direita);
        }
    }
}
