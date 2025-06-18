package Sistema_de_Doc.App;

import Sistema_de_Doc.Compressao.HuffmanDecompressor;
import Sistema_de_Doc.Documentos.Documento;
import Sistema_de_Doc.Documentos.GerenciadorDeArquivos;
import Sistema_de_Doc.Compressao.HuffmanCompressor;
import Sistema_de_Doc.Persistencia.IndiceSimuladoBMais;


import java.util.Scanner;

import static Sistema_de_Doc.Compressao.HuffmanCompressor.comprimir;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        GerenciadorDeArquivos gerenciador = new GerenciadorDeArquivos();
        IndiceSimuladoBMais indice = new IndiceSimuladoBMais();

        while (true) {
            System.out.println("\n=== Sistema de Documentos - CLI ===");
            System.out.println("1. Adicionar documento");
            System.out.println("2. Listar documentos");
            System.out.println("3. Ordenar documentos por nome");
            System.out.println("4. Comprimir documento");
            System.out.println("5. Buscar palavra");
            System.out.println("6. Sair");
            System.out.print("Escolha uma opção: ");

            int opcao;
            try {
                opcao = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Digite um número.");
                continue;
            }

            switch (opcao) {
                case 1:
                    System.out.print("Nome do documento: ");
                    String nome = scanner.nextLine();
                    System.out.print("Conteúdo do documento: ");
                    String conteudo = scanner.nextLine();

                    Documento doc = new Documento(nome, conteudo);
                    String caminho = gerenciador.salvarDocumento(doc);
                    if (caminho != null) {
                        indice.adicionarDocumento(doc.getNome(), caminho);
                        System.out.println("Documento salvo e indexado.");
                    } else {
                        System.out.println("Erro ao salvar documento.");
                    }
                    break;

                case 2:
                    System.out.println(gerenciador.obterListaDocumentos());
                    break;

                case 3:
                    gerenciador.ordenarDocumentosPorNome();
                    break;

                case 4:
                    while (true) {
                        int opcao2;

                        System.out.println("\nQual opção deseja?");
                        System.out.println("1- Compactar o Arquivo");
                        System.out.println("2- Descompactar o Arquivo");

                        try {
                            opcao2 = Integer.parseInt(scanner.nextLine());
                        } catch (NumberFormatException e) {
                            System.out.println("Entrada inválida. Digite um número.");
                            continue;
                        }

                        if (opcao2 == 1) {
                            System.out.print("Nome do documento a comprimir: ");
                            String nomeDoc = scanner.nextLine();
                            Documento docComprimir = gerenciador.buscarDocumento(nomeDoc);
                            if (docComprimir != null) {
                                HuffmanCompressor compressor = new HuffmanCompressor();
                                String conteudoComprimido = docComprimir.getConteudo();
                                String comprimido = compressor.codificar(conteudoComprimido);
                                System.out.println("Conteúdo comprimido: " + comprimido);
                                compressor.salvarArquivoCompactado(nomeDoc, conteudoComprimido);
                            } else {
                                System.out.println("Documento não encontrado.");
                            }
                            break;
                        } else if (opcao2 == 2) {
                            HuffmanDecompressor decompressor = new HuffmanDecompressor();
                            System.out.print("Nome do documento a descomprimir (sem extensão .huff): ");
                            String nomeDoc = scanner.nextLine();
                            String nomeArquivoHuff = nomeDoc + ".huff"; // Adiciona a extensão .huff
                            String textoDescompactado = decompressor.descompactarArquivo(nomeArquivoHuff);
                            if (textoDescompactado != null) {
                                System.out.println("Conteúdo descompactado: " + textoDescompactado);
                                // Salva o texto descompactado em um novo arquivo
                                decompressor.salvarTextoDescompactado(nomeDoc + "_descompactado.txt", textoDescompactado);
                            } else {
                                System.out.println("Erro ao descompactar ou documento não encontrado.");
                            }
                            break;
                        } else {
                            break;
                        }

                    }
                    break;
                case 5:
                    System.out.print("Palavra para buscar: ");
                    String palavra = scanner.nextLine();

                    String resultado = gerenciador.buscarPalavraEmDocumentos(palavra);
                    System.out.println(resultado);
                    break;

                case 6:
                    System.out.println("Encerrando o sistema... Até logo!");
                    return;

                default:
                    System.out.println("Opção inválida.");
            }
        }
    }
}