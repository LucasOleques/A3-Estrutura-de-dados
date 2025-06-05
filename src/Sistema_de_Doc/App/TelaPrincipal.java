package Sistema_de_Doc.App;

import Sistema_de_Doc.Documentos.Documento;
import Sistema_de_Doc.Compressao.HuffmanCompressor;

import javax.swing.*;
import java.awt.*;

public class TelaPrincipal extends JFrame {

    private Sistema sistema = new Sistema();

    private JTextField campoNome = new JTextField(20);
    private JTextArea campoConteudo = new JTextArea(5, 20);
    private JTextField campoBusca = new JTextField(15);
    private JTextArea areaSaida = new JTextArea(10, 40);

    public TelaPrincipal() {
        super("Sistema de Documentos - GUI");

        setLayout(new BorderLayout());

        // Painel de entrada de dados
        JPanel painelEntrada = new JPanel(new GridLayout(5, 1));
        painelEntrada.setBorder(BorderFactory.createTitledBorder("Documento"));

        painelEntrada.add(new JLabel("Nome:"));
        painelEntrada.add(campoNome);

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

        JButton botaoBuscar = new JButton("Buscar Palavra");
        botaoBuscar.addActionListener(e -> buscarPalavra());

        painelAcoes.add(botaoListar);
        painelAcoes.add(botaoOrdenar);
        painelAcoes.add(botaoComprimir);
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
        String lista = sistema.listarDocumentos();
        areaSaida.setText(lista);
    }

    private void ordenarDocumentos() {
        sistema.ordenarDocumentos();
        areaSaida.setText("Documentos ordenados por nome.");
    }

    private void comprimirDocumento() {
        String nome = campoNome.getText();
        Documento doc = sistema.buscarDocumento(nome);
        if (doc != null) {
            String comprimido = HuffmanCompressor.comprimir(doc.getConteudo());
            areaSaida.setText("Comprimido: " + comprimido);
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
