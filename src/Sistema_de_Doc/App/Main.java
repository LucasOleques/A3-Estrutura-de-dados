package Sistema_de_Doc.App;

import Sistema_de_Doc.Documentos.Documento;
import Sistema_de_Doc.Documentos.GerenciadorDeArquivos;
import Sistema_de_Doc.Compressao.HuffmanCompressor;
import Sistema_de_Doc.Persistencia.IndiceSimuladoBMais;


import java.util.Scanner;

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
                    System.out.println("Documentos ordenados por nome.");
                    break;

                case 4:
                    System.out.print("Nome do documento a comprimir: ");
                    String nomeDoc = scanner.nextLine();
                    Documento docComprimir = gerenciador.buscarDocumento(nomeDoc);
                    if (docComprimir != null) {
                        String comprimido = HuffmanCompressor.comprimir(docComprimir.getConteudo());
                        System.out.println("Conteúdo comprimido: " + comprimido);
                    } else {
                        System.out.println("Documento não encontrado.");
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