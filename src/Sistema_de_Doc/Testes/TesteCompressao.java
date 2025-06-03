package Sistema_de_Doc.Testes;

import Sistema_de_Doc.Compressao.HuffmanCompressor;
import Sistema_de_Doc.Documentos.Documento;

public class TesteCompressao {
    public static void main(String[] args) {
        Documento doc = new Documento("raposa", "A raposa rápida pula sobre o cão preguiçoso.");

        HuffmanCompressor compressor = new HuffmanCompressor();
        compressor.salvarArquivoCompactado(doc.getNome(), doc.getConteudo());
    }
}
