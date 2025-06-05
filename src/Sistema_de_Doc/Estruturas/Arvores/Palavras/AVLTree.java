package Sistema_de_Doc.Estruturas.Arvores.Palavras;

public class AVLTree {

    class Node {
        String palavra;
        int altura;
        Node esquerda, direita;

        Node(String palavra) {
            this.palavra = palavra;
            altura = 1;
        }
    }

    private Node raiz;

    // Função pública para inserir
    public void inserir(String palavra) {
        raiz = inserir(raiz, palavra);
    }

    // Inserção recursiva
    private Node inserir(Node no, String palavra) {
        if (no == null)
            return new Node(palavra);

        if (palavra.compareTo(no.palavra) < 0)
            no.esquerda = inserir(no.esquerda, palavra);
        else if (palavra.compareTo(no.palavra) > 0)
            no.direita = inserir(no.direita, palavra);
        else
            return no; // palavras duplicadas não são inseridas

        no.altura = 1 + Math.max(altura(no.esquerda), altura(no.direita));
        int balanceamento = getBalanceamento(no);

        // Balanceamentos
        if (balanceamento > 1 && palavra.compareTo(no.esquerda.palavra) < 0)
            return rotacaoDireita(no);

        if (balanceamento < -1 && palavra.compareTo(no.direita.palavra) > 0)
            return rotacaoEsquerda(no);

        if (balanceamento > 1 && palavra.compareTo(no.esquerda.palavra) > 0) {
            no.esquerda = rotacaoEsquerda(no.esquerda);
            return rotacaoDireita(no);
        }

        if (balanceamento < -1 && palavra.compareTo(no.direita.palavra) < 0) {
            no.direita = rotacaoDireita(no.direita);
            return rotacaoEsquerda(no);
        }

        return no;
    }

    private int altura(Node no) {
        return (no == null) ? 0 : no.altura;
    }

    private int getBalanceamento(Node no) {
        return (no == null) ? 0 : altura(no.esquerda) - altura(no.direita);
    }

    private Node rotacaoDireita(Node y) {
        Node x = y.esquerda;
        Node T2 = x.direita;

        x.direita = y;
        y.esquerda = T2;

        y.altura = Math.max(altura(y.esquerda), altura(y.direita)) + 1;
        x.altura = Math.max(altura(x.esquerda), altura(x.direita)) + 1;

        return x;
    }

    private Node rotacaoEsquerda(Node x) {
        Node y = x.direita;
        Node T2 = y.esquerda;

        y.esquerda = x;
        x.direita = T2;

        x.altura = Math.max(altura(x.esquerda), altura(x.direita)) + 1;
        y.altura = Math.max(altura(y.esquerda), altura(y.direita)) + 1;

        return y;
    }

    // Busca
    public boolean contem(String palavra) {
        return contem(raiz, palavra);
    }

    private boolean contem(Node no, String palavra) {
        if (no == null) return false;
        if (palavra.equals(no.palavra)) return true;
        if (palavra.compareTo(no.palavra) < 0) return contem(no.esquerda, palavra);
        return contem(no.direita, palavra);
    }

    // Impressão em ordem
    public void imprimirEmOrdem() {
        imprimir(raiz);
    }

    private void imprimir(Node no) {
        if (no != null) {
            imprimir(no.esquerda);
            System.out.println(no.palavra);
            imprimir(no.direita);
        }
    }

    public boolean buscar(String palavra) {
        return buscarRec(palavra, raiz);
    }

    private boolean buscarRec(String palavra, Node atual) {
        if (atual == null) return false;
        int cmp = palavra.compareToIgnoreCase(atual.palavra);
        if (cmp == 0) return true;
        else if (cmp < 0) return buscarRec(palavra, atual.esquerda);
        else return buscarRec(palavra, atual.direita);
    }

}
