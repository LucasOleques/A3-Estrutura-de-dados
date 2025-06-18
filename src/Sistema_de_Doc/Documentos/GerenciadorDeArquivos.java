package Sistema_de_Doc.Documentos;

import Sistema_de_Doc.Estruturas.Ordenacao.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.*;
import java.util.*;

public class GerenciadorDeArquivos {

    private static final String DIRETORIO_BASE = "documentos/";
    private static final String DIRETORIO_HUFF = "huff/";
    private ArrayList<Documento> documentos;
    private List<Documento> documentos2;

    public GerenciadorDeArquivos() {
        documentos = new ArrayList<>();
        documentos2 = new ArrayList<>();
        Path path = Paths.get(DIRETORIO_BASE);
        if (!Files.exists(path)) {
            try {
                Files.createDirectory(path);
            } catch (IOException e) {
                System.err.println("Erro ao criar diretório base: " + e.getMessage());
            }
        }
    }

    public String salvarDocumento(Documento documento) {
        documentos2.add(documento); // Armazena em memória também

        String caminho = DIRETORIO_BASE + documento.getNome() + ".txt";
        try (FileWriter writer = new FileWriter(caminho)) {
            writer.write(documento.getConteudo());
            return caminho;
        } catch (IOException e) {
            System.err.println("Erro ao salvar o documento: " + e.getMessage());
            return null;
        }
    }

    public void carregarDocumentos(){
        File doc1 = new File(DIRETORIO_BASE);
        List<File> arquivos = null;
        if (doc1.exists() && doc1.isDirectory()) {
            arquivos = List.of(doc1.listFiles());
        }
        documentos.clear();
        for (File arquivo : arquivos) {
            try {
                String nome = arquivo.getName();
                String conteudo = Files.readString(arquivo.toPath());

                documentos.add(new Documento(nome, conteudo));

            } catch (IOException e) {
                System.err.println("Erro ao ler o arquivo: " + arquivo.getName());
                e.printStackTrace();
            }
        }
    }

    public void carregarDocumentosHuff(){
        File doc1 = new File(DIRETORIO_HUFF);
        List<File> arquivos = null;
        if (doc1.exists() && doc1.isDirectory()) {
            arquivos = List.of(doc1.listFiles());
        }
        documentos.clear();
        for (File arquivo : arquivos) {
            String nome = arquivo.getName();
            documentos.add(new Documento(nome));
        }
    }

    public String obterListaDocumentos() {
        File doc1 = new File(DIRETORIO_BASE);
        StringBuilder sb = new StringBuilder();

        if (doc1.exists() && doc1.isDirectory()) {
            File[] arquivos = doc1.listFiles();            //obter documentos em disco
            if (arquivos != null) {
                for (File temp : arquivos) {
                    String nome = temp.getName();
                    if (nome.endsWith(".txt")) nome = nome.substring(0, nome.length() - 4);  //pra remover o txt
                    sb.append("Nome: ").append(nome).append("\n");
                }
            }
        }else return "\nNenhum documento encontrado.";

        if (documentos2 == null || documentos2.isEmpty()) {
            sb.append("Nenhum documento criado nesta sessão.");    //obter documentos em memoria
        }
        else {
            sb.append("\n");
            for (Documento doc2 : documentos2) {
                sb.append("Documento criado nesta sessão: ").append(doc2.getNome()).append("\n");
            }
        }
        return sb.toString();
    }

    public String buscarPalavraEmDocumentos(String palavra) {
        StringBuilder resultado = new StringBuilder();
        carregarDocumentos();
        for (Documento doc : documentos) {
            if (doc.getConteudo().toLowerCase().contains(palavra.toLowerCase())) {
                resultado.setLength(0);
                resultado.append("Encontrada em: ").append(doc.getNome()).append("\n");
            }
        }
        return resultado.length() > 0 ? resultado.toString() : "Palavra não encontrada em nenhum documento.";
    }

    public void ordenarDocumentosPorNome() {
        carregarDocumentos();
        documentos.sort(Comparator.comparing(Documento::getNome));
    }

    public void ordenarPorHeap(){
        carregarDocumentos();
        documentos = HeapSort.heapSort(documentos);
    }

    public void ordenarPorMerge(){
        carregarDocumentos();
        documentos = MergeSort.mergeSort(documentos);
    }

    public void ordenarPorQuick(){
        carregarDocumentos();
        documentos = QuickSort.ordenarPorDataMaisRecente(documentos);
    }

    public void ordenarPorSelection(){
        carregarDocumentos();
        documentos = SelectionSort.selectionSort(documentos);
    }

    public Documento buscarDocumento(String nome, String tipo) {
        if(tipo.equals(".txt")){
            carregarDocumentos();
        }else{
            carregarDocumentosHuff();
        }
        for (Documento doc : documentos) {
            if (doc.getNome().equalsIgnoreCase(nome + ".txt")) {
                return doc;
            }else if(doc.getNome().equalsIgnoreCase(nome + ".huff")){
                return doc;
            }
        }
        return null;
    }

    public StringBuilder getDocumentos() {
        StringBuilder sb = new StringBuilder();
        //carregarDocumentos();
        for(Documento doc : documentos){
            sb.append("Nome: ").append(doc.getNome()).append("\n");
        }
        return sb;
    }
}