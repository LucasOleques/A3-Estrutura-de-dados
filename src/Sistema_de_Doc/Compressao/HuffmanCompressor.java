package Sistema_de_Doc.Compressao;

import java.io.*;
import java.util.*;

public class HuffmanCompressor {
    private Map<Character, String> codigoHuffman = new HashMap<>();
    private Map<Character, Integer> frequencias = new HashMap<>();

    public void contarFrequencias(String texto) {
        for (char c : texto.toCharArray()) {
            frequencias.put(c, frequencias.getOrDefault(c, 0) + 1);
        }
    }

    public HuffmanNode construirArvore() {
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

    private void gerarCodigos(HuffmanNode raiz, String codigo) {
        if (raiz == null) return;

        if (raiz.isFolha()) {
            codigoHuffman.put(raiz.caractere, codigo);
        }

        gerarCodigos(raiz.esquerda, codigo + "0");
        gerarCodigos(raiz.direita, codigo + "1");
    }

    public String codificar(String texto) {
        contarFrequencias(texto);
        HuffmanNode raiz = construirArvore();
        gerarCodigos(raiz, "");

        StringBuilder codificado = new StringBuilder();
        for (char c : texto.toCharArray()) {
            codificado.append(codigoHuffman.get(c));
        }

        return codificado.toString();
    }

    public void salvarArquivoCompactado(String nomeArquivo, String textoOriginal) {
        String binario = codificar(textoOriginal);

        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("documentos/" + nomeArquivo + ".huff"))) {
            out.writeObject(frequencias); // salvar frequências para reconstruir a árvore
            out.writeObject(binario);     // salvar conteúdo codificado
            System.out.println("Documento comprimido salvo como: " + nomeArquivo + ".huff");
        } catch (IOException e) {
            System.err.println("Erro ao salvar arquivo comprimido: " + e.getMessage());
        }
    }

    public static String comprimir(String texto) {
        HuffmanCompressor compressor = new HuffmanCompressor();
        return compressor.codificar(texto);
    }

}
