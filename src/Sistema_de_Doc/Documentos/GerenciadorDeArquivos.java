package Sistema_de_Doc.Documentos;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.*;
import java.util.*;

public class GerenciadorDeArquivos {

    private static final String DIRETORIO_BASE = "documentos/";
    private List<Documento> documentos;

    public GerenciadorDeArquivos() {
        documentos = new ArrayList<>();
        Path path = Paths.get(DIRETORIO_BASE);
        if (!Files.exists(path)) {
            try {
                Files.createDirectory(path);
            } catch (IOException e) {
                System.err.println("Erro ao criar diretório base: " + e.getMessage());
            }
        }
        // Carregar arquivos existentes
        File diretorio = new File(DIRETORIO_BASE);
        if (diretorio.exists() && diretorio.isDirectory()) {
            File[] arquivos = diretorio.listFiles();
            if (arquivos != null) {
                for (File arquivo : arquivos) {
                    if (arquivo.getName().endsWith(".txt")) {
                        try {
                            String nome = arquivo.getName().substring(0, arquivo.getName().length() - 4);
                            String conteudo = Files.readString(arquivo.toPath());
                            documentos.add(new Documento(nome, conteudo));
                        } catch (IOException e) {
                            System.err.println("Erro ao carregar arquivo " + arquivo.getName() + ": " + e.getMessage());
                        }
                    }
                }
            }
        }
    }

    public String salvarDocumento(Documento documento) {
        documentos.add(documento); // Armazena em memória também

        String caminho = DIRETORIO_BASE + documento.getNome() + ".txt";
        try (FileWriter writer = new FileWriter(caminho)) {
            writer.write(documento.getConteudo());
            System.out.println("Documento salvo com sucesso em: " + caminho);
            return caminho;
        } catch (IOException e) {
            System.err.println("Erro ao salvar o documento: " + e.getMessage());
            return null;
        }
    }

    public String obterListaDocumentos() {
        // Usar TreeSet para armazenar nomes em ordem alfabética e evitar duplicatas
        TreeSet<String> nomesDocumentos = new TreeSet<>(String.CASE_INSENSITIVE_ORDER);

        // Obter documentos em disco
        File diretorio = new File(DIRETORIO_BASE);
        if (diretorio.exists() && diretorio.isDirectory()) {
            File[] arquivos = diretorio.listFiles();
            if (arquivos != null) {
                for (File arquivo : arquivos) {
                    String nome = arquivo.getName();
                    if (nome.endsWith(".txt") || nome.endsWith(".huff")) {
                        nome = nome.substring(0, nome.length() - 4); // Remove .txt
                        nomesDocumentos.add(nome);
                    }
                }
            }
        }

        // Adicionar documentos em memória
        for (Documento doc : documentos) {
            nomesDocumentos.add(doc.getNome());
        }

        // Construir a saída
        StringBuilder sb = new StringBuilder();
        if (nomesDocumentos.isEmpty()) {
            sb.append("Nenhum documento encontrado.\n");
        } else {
            for (String nome : nomesDocumentos) {
                boolean emDisco = new File(DIRETORIO_BASE + nome + ".txt").exists();
                boolean emMemoria = documentos.stream().anyMatch(doc -> doc.getNome().equalsIgnoreCase(nome));

                sb.append("\nNome: ").append(nome);
                if (emDisco && emMemoria) {
                    sb.append(" (em disco e memória)");
                } else if (emDisco) {
                    sb.append(" (em disco)");
                } else {
                    sb.append(" (em memória)");
                }
                //sb.append("\n");
            }
        }

        return sb.toString();
    }

    public void ordenarDocumentosPorNome() {
        documentos.sort(Comparator.comparing(Documento::getNome, String.CASE_INSENSITIVE_ORDER));
        // Exibir documentos em memória
        System.out.println("Documentos em memória ordenados:");
        if (documentos.isEmpty()) {
            System.out.println("  Nenhum documento em memória.");
        } else {
            for (Documento doc : documentos) {
                System.out.println("  Nome: " + doc.getNome());
            }
        }
        // Obter e ordenar documentos em disco
        TreeSet<String> nomesEmDisco = new TreeSet<>(String.CASE_INSENSITIVE_ORDER);
        File diretorio = new File(DIRETORIO_BASE);
        if (diretorio.exists() && diretorio.isDirectory()) {
            File[] arquivos = diretorio.listFiles();
            if (arquivos != null) {
                for (File arquivo : arquivos) {
                    String nome = arquivo.getName();
                    if (nome.endsWith(".txt") || nome.endsWith(".huff")) {
                        nome = nome.substring(0, nome.lastIndexOf(".")); // Remove .txt ou .huff
                        nomesEmDisco.add(nome);
                    }
                }
            }
        }

        // Exibir documentos em disco
        System.out.println("\nDocumentos em disco ordenados:");
        if (nomesEmDisco.isEmpty()) {
            System.out.println("  Nenhum documento em disco.");
        } else {
            for (String nome : nomesEmDisco) {
                boolean ehTxt = new File(DIRETORIO_BASE + nome + ".txt").exists();
                boolean ehHuff = new File(DIRETORIO_BASE + nome + ".huff").exists();
                String tipo = ehTxt && ehHuff ? " (.txt e .huff)" : ehTxt ? " (.txt)" : " (.huff)";
                System.out.println("  Nome: " + nome + tipo);
            }
        }
    }

    public Documento buscarDocumento(String nome) {
        for (Documento doc : documentos) {
            if (doc.getNome().equalsIgnoreCase(nome)) {
                return doc;
            }
        }
        return null;
    }

    public String buscarPalavraEmDocumentos(String palavra) {
        StringBuilder resultado = new StringBuilder();
        for (Documento doc : documentos) {
            if (doc.getConteudo().toLowerCase().contains(palavra.toLowerCase())) {
                resultado.append("Encontrada em: ").append(doc.getNome()).append("\n");
            }
        }
        return resultado.length() > 0 ? resultado.toString() : "Palavra não encontrada em nenhum documento.";
    }

    public List<Documento> getDocumentos() {
        return documentos;
    }
}