package Sistema_de_Doc.Compressao;

public class HuffmanNode implements Comparable<HuffmanNode> {
    public char caractere;
    public int frequencia;
    public HuffmanNode esquerda, direita;

    public HuffmanNode(char caractere, int frequencia) {
        this.caractere = caractere;
        this.frequencia = frequencia;
    }

    public boolean isFolha() {
        return esquerda == null && direita == null;
    }

    @Override
    public int compareTo(HuffmanNode o) {
        return Integer.compare(this.frequencia, o.frequencia);
    }
}
