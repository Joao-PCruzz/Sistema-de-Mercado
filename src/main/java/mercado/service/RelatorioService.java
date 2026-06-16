package main.java.mercado.service;

import java.util.ArrayList; //ArrayList para criar a lista dinâmica
import java.util.Comparator; //Comparator para realizar as comparações
import java.util.List; //List para fazer as cópias necessárias

import main.java.mercado.estrutura.*;
import main.java.mercado.model.Produto;

public class RelatorioService {
    // Essa classe e responsavel por apresentar relatorios referentes aos produtos.

    // == Método para mostrar os 10 mais baratos ==
    public String gerarRelatorio10MaisBaratos(NoCategoria primCategoria) {
        // Primeiro, todos os produtos de todas as categorias sao copiados para uma
        // lista comum.
        List<Produto> produtos = coletarProdutos(primCategoria);
        // Em seguida, essa lista comum e ordenada pelo preco em ordem crescente.
        // O Comparator compara cada produto usando o retorno do metodo getPreco().
        produtos.sort(Comparator.comparingDouble(Produto::getPreco));
        // Por fim, imprime apenas os 10 primeiros produtos ja ordenados.
        return construirRanking("10 PRODUTOS MAIS BARATOS", produtos, 10);
    }

    // == Método para mostrar o produto mais caro
    public String gerarRelatorioProdutoMaisCaro(NoCategoria primCategoria) {
        // Coleta dos produtos
        List<Produto> produtos = coletarProdutos(primCategoria);
        // Ordena pelo preco e usa reversed() para inverter a ordem, ficando do maior
        // preco para o menor.
        produtos.sort(Comparator.comparingDouble(Produto::getPreco).reversed());
        // Como o produto mais caro ficara na primeira posicao, o limite de impressao e
        // 1.
        return construirRanking("PRODUTO MAIS CARO", produtos, 1);
    }

    // == Método para mostrar 10 com menor quantidade ==
    public String gerarRelatorio10ComQuantidadeMaisBaixa(NoCategoria primCategoria) {
        // Coleta os produtos
        List<Produto> produtos = coletarProdutos(primCategoria);
        // Ordena pela quantidade em ordem crescente
        produtos.sort(Comparator.comparingInt(Produto::getQuantidade));
        // Imprime no maximo 10 produtos depois da ordenacao.
        return construirRanking("10 PRODUTOS COM QUANTIDADE MAIS BAIXA", produtos, 10);
    }

    // == Método para mostrar os 10 com maior estoque ==
    public String gerarRelatorio10ComQuantidadeMaisAlta(NoCategoria primCategoria) {
        // Coleta os produtos
        List<Produto> produtos = coletarProdutos(primCategoria);
        // Ordena pela quantidade e inverte o resultado
        produtos.sort(Comparator.comparingInt(Produto::getQuantidade).reversed());
        // Exibe os 10 primeiros produtos da lista ja ordenada
        return construirRanking("10 PRODUTOS COM QUANTIDADE MAIS ALTA", produtos, 10);
    }

    // == Método para mostrar os 10 com maior demanda ==
    public String gerarRelatorio10ComMaiorDemanda(NoCategoria primCategoria) {
        // Coleta dos produtos
        List<Produto> produtos = coletarProdutos(primCategoria);
        // Ordena pela demanda em ordem decrescente
        produtos.sort(Comparator.comparingDouble(Produto::getDemanda).reversed());
        // Imprime o ranking limitado aos 10 primeiros itens
        return construirRanking("10 PRODUTOS COM MAIOR DEMANDA", produtos, 10);
    }

    // == Mostrar os 10 com menor demanda ==
    public String gerarRelatorio10ComMenorDemanda(NoCategoria primCategoria) {
        // Coleta os produtos
        List<Produto> produtos = coletarProdutos(primCategoria);
        // Ordena pela demanda em ordem crescente
        produtos.sort(Comparator.comparingDouble(Produto::getDemanda));
        // Exibe os 10 primeiros produtos depois da ordenacao
        return construirRanking("10 PRODUTOS COM MENOR DEMANDA", produtos, 10);
    }

    // Metodo auxiliar que transforma a estrutura em uma lista temporaria
    // Isso facilita a ordenacao dos relatorios e preserva os ponteiros originais
    // das listas
    private List<Produto> coletarProdutos(NoCategoria categoriaAtual) {
        // Cria uma lista dinamica vazia para armazenar referencias aos produtos
        // encontrados
        List<Produto> produtos = new ArrayList<>();
        if (categoriaAtual != null) {
            adicionarProdutosDaCategoria(categoriaAtual, produtos);
        }
        return produtos;
    }

    // Metodo auxiliar que percorre os produtos de uma unica categoria
    // Ele recebe a categoria de origem e a lista temporaria onde os produtos serao
    // adicionados
    private void adicionarProdutosDaCategoria(NoCategoria categoria, List<Produto> produtos) {
        // Se a categoria for nula, nao ha como acessar seus produtos.
        if (categoria == null || categoria.estaVazia()) {
            return;
        }
        // Guarda o primeiro produto da categoria.
        NoProduto produtoAtual = categoria.getPrimProduto();
        // Laço para percorrer os produtos
        do {
            // Pega o produto que esta dentro do NoProduto atual e adiciona na lista
            // temporaria
            produtos.add(produtoAtual.getProduto());
            produtoAtual = produtoAtual.getProximo();
            // Quando o proximo produto for novamente o primeiro, todos ja foram visitados
        } while (produtoAtual != categoria.getPrimProduto());
    }

    // Metodo auxiliar responsavel apenas por imprimir o resultado ja ordenado
    // Ele recebe o titulo do relatorio, a lista de produtos e o limite de itens a
    // mostrar
    private String construirRanking(String titulo, List<Produto> produtos, int limite) {
        // Se a lista esta vazia, nao existe produto cadastrado para exibir
        if (produtos.isEmpty()) {
            return "Nao existem produtos cadastrados para gerar este relatorio.";
        }
        // Se existirem menos produtos do que o limite, mostra apenas a quantidade
        // existente.
        int totalExibido = Math.min(limite, produtos.size());

        StringBuilder sb = new StringBuilder("--- " + titulo + " ---\n\n");
        // Percorre somente a parte da lista que deve aparecer no ranking
        for (int i = 0; i < totalExibido; i++) {
            // Imprime a posicao do ranking comecando em 1 e os dados do produto pelo
            // toString()
            sb.append(i + 1).append(". ").append(produtos.get(i).toString()).append("\n");
        }
        // Imprime uma linha final apenas para separar visualmente
        return sb.toString();
    }
}
