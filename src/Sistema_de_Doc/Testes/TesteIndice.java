package Sistema_de_Doc.Testes;

import Sistema_de_Doc.Indexador.IndiceInvertido;

import java.util.Map;

public class TesteIndice {
    public static void main(String[] args) {
        IndiceInvertido indice = new IndiceInvertido();

        String doc1 = "A raposa rápida pula sobre o cão preguiçoso.";
        String doc2 = "A preguiçosa tartaruga venceu a corrida contra a lebre.";

        indice.indexarDocumento("doc1.txt", doc1);
        indice.indexarDocumento("doc2.txt", doc2);

        Map<String, Integer> resultado = indice.buscar("preguiçosa");

        System.out.println("Resultados para 'preguiçosa':");
        for (Map.Entry<String, Integer> entrada : resultado.entrySet()) {
            System.out.println("- " + entrada.getKey() + " (" + entrada.getValue() + " ocorrência(s))");
        }

        // indice.exibirIndice(); // opcional: mostrar tudo
    }
}
