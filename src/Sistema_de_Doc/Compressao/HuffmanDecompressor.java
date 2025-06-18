package Sistema_de_Doc.Compressao;

import java.io.*;
import java.util.*;

public class HuffmanDecompressor {

    // Método para reconstruir a árvore de Huffman a partir das frequências
    private static HuffmanNode reconstruirArvore(Map<Character, Integer> frequencias) {
        PriorityQueue<HuffmanNode> fila = new PriorityQueue<>();
        for (Map.Entry<Character, Integer> entry : frequencias.entrySet()) {
            fila.add(new HuffmanNode(entry.getKey(), entry.getValue()));
        }

        while (fila.size() > 1) {
            HuffmanNode esq = fila.poll();
            HuffmanNode dir = fila.poll();
            HuffmanNode pai = new HuffmanNode('\0', esq.frequencia + dir.frequencia);
            pai.esquerda = esq;
            pai.direita = dir;
            fila.add(pai);
        }

        return fila.poll();
    }

    // Método para decodificar o texto binário usando a árvore de Huffman
    public String decodificar(String binario, HuffmanNode raiz) {
        StringBuilder textoDecodificado = new StringBuilder();
        HuffmanNode atual = raiz;
        for (char bit : binario.toCharArray()) {
            if (bit == '0') {
                atual = atual.esquerda;
            } else if (bit == '1') {
                atual = atual.direita;
            }

            if (atual.isFolha()) {
                textoDecodificado.append(atual.caractere);
                atual = raiz; // Volta para a raiz para o próximo caractere
            }
        }
        return textoDecodificado.toString();
    }

    // Método para descompactar o arquivo .huff
    public String descompactarArquivo(String nomeArquivo) {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream("documentos/" + nomeArquivo))) {
            // Ler as frequências salvas
            @SuppressWarnings("unchecked")
            Map<Character, Integer> frequencias = (Map<Character, Integer>) in.readObject();

            // Ler o conteúdo binário codificado
            String binario = (String) in.readObject();

            // Reconstruir a árvore de Huffman
            HuffmanNode raiz = reconstruirArvore(frequencias);

            // Decodificar o texto
            String textoOriginal = decodificar(binario, raiz);
            System.out.println("Arquivo descompactado com sucesso!");
            return textoOriginal;

        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Erro ao descompactar arquivo: " + e.getMessage());
            return null;
        }
    }
    // Método para salvar o texto descompactado em um arquivo
    public void salvarTextoDescompactado(String nomeArquivo, String texto) {
        try (FileWriter writer = new FileWriter("documentos/" + nomeArquivo)) {
            writer.write(texto);
            System.out.println("Texto descompactado salvo como: " + nomeArquivo);
        } catch (IOException e) {
            System.err.println("Erro ao salvar texto descompactado: " + e.getMessage());
        }
    }
}