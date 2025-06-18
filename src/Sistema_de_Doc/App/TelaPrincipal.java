package Sistema_de_Doc.App;

import Sistema_de_Doc.Documentos.Documento;
import Sistema_de_Doc.Compressao.HuffmanCompressor;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class TelaPrincipal extends JFrame {

    private Sistema sistema = new Sistema();

    private JTextArea campoNome = new JTextArea(3,20);
    private JTextArea campoConteudo = new JTextArea(5, 20);
    private JTextField campoBusca = new JTextField(15);
    private JTextArea areaSaida = new JTextArea(10, 40);

    public TelaPrincipal() {
        super("Sistema de Documentos");

        setLayout(new BorderLayout());

        // Painel de entrada de dados
        JPanel painelEntrada = new JPanel(new GridLayout(5, 1));
        painelEntrada.setBorder(BorderFactory.createTitledBorder("Documento"));

        painelEntrada.add(new JLabel("Nome:"));
        campoNome.setLineWrap(true);
        campoNome.setWrapStyleWord(true);
        painelEntrada.add(new JScrollPane(campoNome));

        painelEntrada.add(new JLabel("Conteúdo:"));
        campoConteudo.setLineWrap(true);
        campoConteudo.setWrapStyleWord(true);
        painelEntrada.add(new JScrollPane(campoConteudo));

        JButton botaoAdicionar = new JButton("Adicionar Documento");
        botaoAdicionar.addActionListener(e -> adicionarDocumento());
        painelEntrada.add(botaoAdicionar);

        // Painel de ações
        JPanel painelAcoes = new JPanel(new FlowLayout());

        JButton botaoListar = new JButton("Listar");
        botaoListar.addActionListener(e -> listarDocumentos());

        JButton botaoOrdenar = new JButton("Ordenar");
        botaoOrdenar.addActionListener(e -> ordenarDocumentos());

        JButton botaoComprimir = new JButton("Comprimir");
        botaoComprimir.addActionListener(e -> comprimirDocumento());

        JButton botaoDescomprimir = new JButton("Descomprimir");
        botaoDescomprimir.addActionListener(e -> descomprimirDocumento());

        JButton botaoBuscar = new JButton("Buscar Palavra");
        botaoBuscar.addActionListener(e -> buscarPalavra());

        painelAcoes.add(botaoListar);
        painelAcoes.add(botaoOrdenar);
        painelAcoes.add(botaoComprimir);
        painelAcoes.add(botaoDescomprimir);
        painelAcoes.add(new JLabel("Palavra:"));
        painelAcoes.add(campoBusca);
        painelAcoes.add(botaoBuscar);

        // Área de saída
        areaSaida.setEditable(false);
        JScrollPane scrollSaida = new JScrollPane(areaSaida);

        // Adiciona ao layout
        add(painelEntrada, BorderLayout.NORTH);
        add(painelAcoes, BorderLayout.CENTER);
        add(scrollSaida, BorderLayout.SOUTH);

        pack();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Centraliza
        setVisible(true);
    }

    private void adicionarDocumento() {
        String nome = campoNome.getText();
        String conteudo = campoConteudo.getText();
        if (!nome.isEmpty() && !conteudo.isEmpty()) {
            sistema.adicionarDocumento(nome, conteudo);
            areaSaida.setText("Documento adicionado: " + nome);
        } else {
            areaSaida.setText("Preencha nome e conteúdo.");
        }
    }

    private void listarDocumentos() {
        StringBuilder lista = new StringBuilder(sistema.listarDocumentos());
        areaSaida.setText(String.valueOf(lista));
        if(lista.length() == 0){
            areaSaida.setText("Lista de documentos ainda não foi carregada, tente ordenar primeiro.");
        }

    }

    private void ordenarDocumentos() {
        String[] options = {"heap", "merge", "quick", "selection"};
        int opcao = JOptionPane.showOptionDialog(null, "Selecione:", "Método de ordenação",
                0, 3, null, options, options[0]);
        sistema.ordenarDocumentos(opcao);
        switch (opcao) {
            case 0:
                areaSaida.setText("Documentos ordenados alfabéticamente utilizando heap sort.");
                break;
            case 1:
                areaSaida.setText("Documentos ordenados pelo tamanho do conteúdo utilizando merge sort.");
                break;
            case 2:
                areaSaida.setText("Documentos ordenados pela data de criação utilizando quick sort.");
                break;
            case 3:
                areaSaida.setText("Documentos ordenados em ordem alfabética inversa utilizando selection sort.");
                break;
            default:
                break;
        }
    }

    private void comprimirDocumento(){
        String nome = campoNome.getText();
        Documento doc = sistema.buscarDocumento(nome,".txt");
        HuffmanCompressor huffman = new HuffmanCompressor();
        if (doc != null) {
            try {
                huffman.comprimir(nome, doc.getConteudo());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            areaSaida.setText(nome + " foi comprimido com sucesso.");
        } else {
            areaSaida.setText("Documento não encontrado.");
        }
    }

    private void descomprimirDocumento(){
        String nome = campoNome.getText();
        Documento doc = sistema.buscarDocumento(nome,".huff");
        HuffmanCompressor huffman = new HuffmanCompressor();
        if (doc != null) {
            try {
                huffman.descomprimir(nome, nome + "_descomprimido");
            } catch (IOException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
            //String comprimido = String.valueOf(CompressorHuffman.comprimir(nome, doc.getConteudo()));
            areaSaida.setText(nome + " foi descomprimido com sucesso.");
        } else {
            areaSaida.setText("Documento não encontrado.");
        }
    }

    private void buscarPalavra() {
        String palavra = campoBusca.getText();
        if (!palavra.isEmpty()) {
            String resultado = sistema.buscarPalavra(palavra);
            areaSaida.setText(resultado);
        } else {
            areaSaida.setText("Digite uma palavra para buscar.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(TelaPrincipal::new);
    }
}
