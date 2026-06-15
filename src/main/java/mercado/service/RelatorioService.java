package main.java.mercado.service;

import java.util.ArrayList; //ArrayList para criar a lista dinâmica
import java.util.Comparator; //Comparator para realizar as comparações
import java.util.List; //List para fazer as cópias necessárias

import main.java.mercado.estrutura.*;
import main.java.mercado.model.Produto;

public class RelatorioService {
    //Essa classe e responsavel por apresentar relatorios referentes aos produtos.

    // == Método para mostrar os 10 mais baratos ==
    public void mostrar10MaisBaratos(NoCategoria primCategoria) {
        //Primeiro, todos os produtos de todas as categorias sao copiados para uma lista comum.
        List<Produto> produtos = coletarProdutos(primCategoria);
        //Em seguida, essa lista comum e ordenada pelo preco em ordem crescente.
        //O Comparator compara cada produto usando o retorno do metodo getPreco().
        produtos.sort(Comparator.comparingDouble(Produto::getPreco));
        // Por fim, imprime apenas os 10 primeiros produtos ja ordenados.
        imprimirRanking("10 PRODUTOS MAIS BARATOS", produtos, 10);
    }

    // == Método para mostrar o produto mais caro
    public void mostrarProdutoMaisCaro(NoCategoria primCategoria) {
        //Coleta dos produtos
        List<Produto> produtos = coletarProdutos(primCategoria);
        //Ordena pelo preco e usa reversed() para inverter a ordem, ficando do maior preco para o menor.
        produtos.sort(Comparator.comparingDouble(Produto::getPreco).reversed());
        //Como o produto mais caro ficara na primeira posicao, o limite de impressao e 1.
        imprimirRanking("PRODUTO MAIS CARO", produtos, 1);
    }

    // == Método para mostrar 10 com menor quantidade ==
    public void mostrar10ComQuantidadeMaisBaixa(NoCategoria primCategoria) {
        //Coleta os produtos
        List<Produto> produtos = coletarProdutos(primCategoria);
        //Ordena pela quantidade em ordem crescente
        produtos.sort(Comparator.comparingInt(Produto::getQuantidade));
        //Imprime no maximo 10 produtos depois da ordenacao.
        imprimirRanking("10 PRODUTOS COM QUANTIDADE MAIS BAIXA", produtos, 10);
    }

    // == Método para mostrar os 10 com maior estoque == 
    public void mostrar10ComQuantidadeMaisAlta(NoCategoria primCategoria) {
        //Coleta os produtos
        List<Produto> produtos = coletarProdutos(primCategoria);
        //Ordena pela quantidade e inverte o resultado
        produtos.sort(Comparator.comparingInt(Produto::getQuantidade).reversed());
        //Exibe os 10 primeiros produtos da lista ja ordenada
        imprimirRanking("10 PRODUTOS COM QUANTIDADE MAIS ALTA", produtos, 10);
    }

    // == Método para mostrar os 10 com maior demanda ==
    public void mostrar10ComMaiorDemanda(NoCategoria primCategoria) {
        //Coleta dos produtos
        List<Produto> produtos = coletarProdutos(primCategoria);
        //Ordena pela demanda em ordem decrescente
        produtos.sort(Comparator.comparingDouble(Produto::getDemanda).reversed());
        //Imprime o ranking limitado aos 10 primeiros itens
        imprimirRanking("10 PRODUTOS COM MAIOR DEMANDA", produtos, 10);
    }

    // == Mostrar os 10 com menor demanda ==
    public void mostrar10ComMenorDemanda(NoCategoria primCategoria) {
        //Coleta os produtos
        List<Produto> produtos = coletarProdutos(primCategoria);
        //Ordena pela demanda em ordem crescente
        produtos.sort(Comparator.comparingDouble(Produto::getDemanda));
        //Exibe os 10 primeiros produtos depois da ordenacao
        imprimirRanking("10 PRODUTOS COM MENOR DEMANDA", produtos, 10);
    }

    //Metodo auxiliar que transforma a estrutura em uma lista temporaria
    //Isso facilita a ordenacao dos relatorios e preserva os ponteiros originais das listas
    private List<Produto> coletarProdutos(NoCategoria primCategoria) {
        //Cria uma lista dinamica vazia para armazenar referencias aos produtos encontrados
        List<Produto> produtos = new ArrayList<>();
        //Se primCategoria for null, significa que nenhuma categoria foi cadastrada, então retorna lista vazia
        if(primCategoria == null) {
            return produtos;
        }

        // Guarda a categoria atual da navegacao
        NoCategoria categoriaAtual = primCategoria;
        //Laço para viajar pelas categorias
        do{
            //Copia todos os produtos da categoria atual para a lista temporaria
            adicionarProdutosDaCategoria(categoriaAtual, produtos);
            categoriaAtual = categoriaAtual.getProxCategoria();
        //O laco termina quando volta para a primeira categoria
        }while(categoriaAtual != primCategoria);
        return produtos;
    }

    //Metodo auxiliar que percorre os produtos de uma unica categoria
    //Ele recebe a categoria de origem e a lista temporaria onde os produtos serao adicionados
    private void adicionarProdutosDaCategoria(NoCategoria categoria, List<Produto> produtos) {
        //Se a categoria for nula, nao ha como acessar seus produtos.
        if(categoria == null || categoria.estaVazia()){
            return;
        }
        //Guarda o primeiro produto da categoria.
        NoProduto produtoAtual = categoria.getPrimProduto();
        //Laço para percorrer os produtos
        do{
            //Pega o produto que esta dentro do NoProduto atual e adiciona na lista temporaria
            produtos.add(produtoAtual.getProduto());
            produtoAtual = produtoAtual.getProximo();
        //Quando o proximo produto for novamente o primeiro, todos ja foram visitados
        }while(produtoAtual != categoria.getPrimProduto());
    }

    //Metodo auxiliar responsavel apenas por imprimir o resultado ja ordenado
    //Ele recebe o titulo do relatorio, a lista de produtos e o limite de itens a mostrar
    private void imprimirRanking(String titulo, List<Produto> produtos, int limite) {
        //Se a lista esta vazia, nao existe produto cadastrado para exibir
        if(produtos.isEmpty()) {
            System.out.println("Nao existem produtos cadastrados para gerar este relatorio.");
            return;
        }
        //Se existirem menos produtos do que o limite, mostra apenas a quantidade existente.
        int totalExibido = Math.min(limite, produtos.size());

        System.out.println("\n--- " + titulo + " ---");
        //Percorre somente a parte da lista que deve aparecer no ranking
        for(int i = 0; i < totalExibido; i++) {
            //Imprime a posicao do ranking comecando em 1 e os dados do produto pelo toString()
            System.out.println((i + 1) + ". " + produtos.get(i));
        }
        //Imprime uma linha final apenas para separar visualmente
        System.out.println("-------------------------------");
    }
}
