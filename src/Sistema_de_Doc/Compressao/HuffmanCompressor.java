package Sistema_de_Doc.Compressao;

import java.util.*;
import java.io.*;

public class HuffmanCompressor {
    private Map<Character, String> huffmanCodes = new HashMap<>();
    private Map<String, Character> reverseHuffmanCodes = new HashMap<>();

    // Metodo para construir a árvore de Huffman
    private HuffmanNode buildHuffmanTree(String content) {
        // Contar frequência de cada caractere
        Map<Character, Integer> frequencyMap = new HashMap<>();
        for (char c : content.toCharArray()) {
            frequencyMap.put(c, frequencyMap.getOrDefault(c, 0) + 1);
        }

        // Criar fila de prioridade
        PriorityQueue<HuffmanNode> priorityQueue = new PriorityQueue<>();
        for (Map.Entry<Character, Integer> entry : frequencyMap.entrySet()) {
            priorityQueue.add(new HuffmanNode(entry.getKey(), entry.getValue()));
        }

        // Construir a árvore
        while (priorityQueue.size() > 1) {
            HuffmanNode left = priorityQueue.poll();
            HuffmanNode right = priorityQueue.poll();
            priorityQueue.add(new HuffmanNode('\0', left.frequency + right.frequency, left, right));
        }

        return priorityQueue.poll();
    }

    // Metodo para gerar os códigos Huffman
    private void generateHuffmanCodes(HuffmanNode root, String code) {
        if (root == null) return;

        if (root.isLeaf()) {
            huffmanCodes.put(root.character, code);
            reverseHuffmanCodes.put(code, root.character);
            return;
        }

        generateHuffmanCodes(root.left, code + "0");
        generateHuffmanCodes(root.right, code + "1");
    }

    // Metodo para comprimir
    public void comprimir(String nome, String conteudo) throws IOException {
        // Construir árvore e códigos
        HuffmanNode root = buildHuffmanTree(conteudo);
        huffmanCodes.clear();
        reverseHuffmanCodes.clear();
        generateHuffmanCodes(root, "");

        // Converter o conteúdo em string binária
        StringBuilder compressedData = new StringBuilder();
        for (char c : conteudo.toCharArray()) {
            compressedData.append(huffmanCodes.get(c));
        }

        // Salvar em arquivo .huff
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("huff/" + nome + ".huff"))) {
            // Salvar os códigos Huffman
            oos.writeObject(huffmanCodes);
            // Salvar os dados comprimidos como string binária
            oos.writeObject(compressedData.toString());
        }
    }

    // Metodo para descomprimir
    public void descomprimir(String nome, String outputFileName) throws IOException, ClassNotFoundException {
        // Ler o arquivo .huff
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("huff/" + nome + ".huff"))) {
            // Ler os códigos Huffman
            @SuppressWarnings("unchecked")
            Map<Character, String> huffmanCodes = (Map<Character, String>) ois.readObject();
            // Ler os dados comprimidos
            String compressedData = (String) ois.readObject();

            // Reconstruir reverseHuffmanCodes
            reverseHuffmanCodes.clear();
            for (Map.Entry<Character, String> entry : huffmanCodes.entrySet()) {
                reverseHuffmanCodes.put(entry.getValue(), entry.getKey());
            }

            // Descomprimir
            StringBuilder decompressedData = new StringBuilder();
            StringBuilder currentCode = new StringBuilder();
            for (char bit : compressedData.toCharArray()) {
                currentCode.append(bit);
                if (reverseHuffmanCodes.containsKey(currentCode.toString())) {
                    decompressedData.append(reverseHuffmanCodes.get(currentCode.toString()));
                    currentCode = new StringBuilder();
                }
            }

            // Salvar em arquivo .txt
            try (BufferedWriter writer = new BufferedWriter(new FileWriter("descomprimido/" + outputFileName + ".txt"))) {
                writer.write(decompressedData.toString());
            }
        }
    }
}