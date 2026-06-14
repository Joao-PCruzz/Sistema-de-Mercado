package main.java.mercado.service;
import main.java.mercado.model.Produto;
import main.java.mercado.estrutura.*;
import javax.swing.*;

public class ProdutoSevice {
    //Neste arquivo ficarão salvas as alterações que serão feitas no produto.

    // == Lógica para cadastrar produtos ==
    public void cadastrarProduto(NoCategoria categoria, Produto produto){
        //Nó auxiliar para ajudar na viagem pelos nós de maneira coerente
        NoProduto inicio = categoria.getPrimProduto();
        //Se a catgoria estiver vazia
        if(inicio == null){
            NoProduto novoNo = new NoProduto(null, null, produto);
            novoNo.setProximo(novoNo);
            novoNo.setAnterior(novoNo);
            categoria.setPrimProduto(novoNo);
        }
        else{
            //Nó auxiliar para percorrer a lista
            NoProduto atual = inicio;
            //Indo até o ultimo produto
            while(atual.getProximo() != inicio){
                atual = atual.getProximo();
            }
            //Instanciando o novo nó já deifnindo o próximo e o anterior
            NoProduto novoNo = new NoProduto(inicio, atual, produto);
            //Fazendo a aritmética com os ponterios
            atual.setProximo(novoNo);
            inicio.setAnterior(novoNo);
            //printando na tela o novo nó
            System.out.println(novoNo);
        }
    }

    // == Lógica para Buscar produtos == (pretendo otimizar remover produto implementando ela dentro dele)
    public void buscarProduto(){

    }

    // == Lógica para remover produtos ==
    public NoProduto removerProduto(NoCategoria categoria, String nome){
        //Tratamento para se estiver vazia.
        if(categoria.estaVazia()){
            System.out.println("A categoria esta vazia, nao ha produtos para remover.");
            return null;
        }
        else{
            //Instanciando ponteiros auxiliares.
            NoProduto removido;
            NoProduto inicio = categoria.getPrimProduto();
            NoProduto  atual = inicio;
            do{
                if(atual.getProduto().getNome().equalsIgnoreCase(nome)){
                    //Se houver somente um elemento na lista
                    if(atual.getProximo() == atual && atual.getAnterior() == atual){
                        categoria.setPrimProduto(null);
                    }
                    //Se o produto removido for o primeiro
                    else if(atual == inicio){
                        removido = atual;
                        removido.getAnterior().setProximo(removido.getProximo());
                        removido.getProximo().setAnterior(removido.getAnterior());
                        categoria.setPrimProduto(removido.getProximo());
                        System.out.println("Produto removido com sucesso");
                        return removido;
                    } 
                    //Se nenhum dos casos se aplicar
                    else{
                    removido = atual;
                    removido.getAnterior().setProximo(removido.getProximo());
                    removido.getProximo().setAnterior(removido.getAnterior());
                    System.out.println("Produto removido com sucesso");
                    return removido;
                    }
                }
                atual = atual.getProximo();
            }while(atual.getProximo() != inicio);
            System.out.println("O produto não foi encontrado.");
            return null;
        }
    }
}
