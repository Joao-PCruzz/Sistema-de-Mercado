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

    // == Lógica para Buscar produtos == 
    public NoProduto buscarProduto(NoCategoria categoria, String nomeProduto){
        //Caso 1: Categoria vazia/sem produtos
        if(categoria.estaVazia()){
            System.out.println("A categoria esta vazia, nao existem produtos para buscar.");
            return null;
        }
        //Caso 2: Existem produtos na categoria
        else{
            //Nós auxiliares para percorrer a lista
            NoProduto inicio = categoria.getPrimProduto();
            NoProduto atual = inicio;
            do{
                //Buscar se o produto está na categoria pelo nome.
                //EqualsToIgnore é usado pois retorna True ou False, ignora maiúsculas e minúsculas e compara Strings
                if(atual.getProduto().getNome().equalsIgnoreCase(nomeProduto)){
                    System.out.println("Produto encontrado com sucesso!");
                    return atual;
                }
                atual = atual.getProximo();
            }while(atual != inicio);
            //Se chegar aqui, é porque não achou
            System.out.println("O produto nao foi encontrado!");
            return null;
        }
    }

    // == Lógica para remover produtos ==
    public NoProduto removerProduto(NoCategoria categoria, String nome){
        //Tratamento para se estiver vazia.
        if(categoria.estaVazia()){
            System.out.println("A categoria esta vazia, nao ha produtos para remover.");
            return null;
        }
        else{
            //Instanciando ponteiro de remoção
            NoProduto removido;
            //Utilizando o método de busca criado anteriormente
            removido = buscarProduto(categoria, nome);
            //Se não encontrou o produto
            if(removido == null){
                //Talvez esse print fique duplicado, nos testes irei observar isso e talvez remover essa linha
                System.out.println("O produto não foi encontrado para remoção ");
                return null;
            }
            //Instanciando ponteiro auxiliar
            NoProduto inicio = categoria.getPrimProduto();
            //Se houver somente um elemento na lista
            if(removido.getProximo() == removido && removido.getAnterior() == removido){
                categoria.setPrimProduto(null);
                return removido;
            }
            //Se não for o único na lista
            else{
                //Pega o anterior e coloca para apontar para o próximo do removido
                removido.getAnterior().setProximo(removido.getProximo());
                //Se a categoria removida for a primeira
                removido.getProximo().setAnterior(removido.getAnterior());
                //Se o produto removido for o primeiro
                if(removido == inicio){
                    categoria.setPrimProduto(removido.getProximo());
                }
                removido.setProximo(null);
                removido.setAnterior(null);
                System.out.println("Produto removido com sucesso");
                return removido;
            }
        }
    }

    // == Método para alterar Preço ==
    public void alterarPreco(NoCategoria categoria, String nome, double preco){
        //Se a categoria não possui produtos
        if(categoria.estaVazia()){
            System.out.println("A categoria esta vazia.");
        }
        //Se possuir produtos
        else{
            ///Nó auxiliar
            NoProduto aux = buscarProduto(categoria, nome);
            //Se não encontrar o produto
            if(aux == null){
                //Talvez essa linha fique repetitiva, irei testar
                System.out.println("O produto nao foi encontrado para alterar o seu preco.");
            }
            //Verificação se o preço digitado foi negativo ou igual a 0
            else if(preco <= 0){
                System.out.println("Preco digitado foi negativo, nao e possivel alterar!");
            }
            //Se encontrar o produto, alterar o seu preco
            else{
                aux.getProduto().setPreco(preco);
                System.out.println("O preco do produto foi alterado com sucesso!");
                System.out.println(aux.getProduto());
            }
        }
    }

    // == Método para alterar Quantidade ==
    public void alterarQauntidade(NoCategoria categoria, String nome, int quantidade){
        //Se a categoria não possui produtos
        if(categoria.estaVazia()){
            System.out.println("A categoria esta vazia.");
        }
        //Se possuir produtos
        else{
            ///Nó auxiliar
            NoProduto aux = buscarProduto(categoria, nome);
            //Se não encontrar o produto
            if(aux == null){
                //Talvez essa linha fique repetitiva, irei testar
                System.out.println("O produto nao foi encontrado para alterar o sua quantidade.");
            }
            //Verificação se a quantidade digitada foi negativa
            else if(quantidade < 0){
                System.out.println("Quantidade digitada foi negativa, nao e possivel alterar!");
            }
            //Se encontrar o produto, alterar o seu preco
            else{
                aux.getProduto().setQuantidade(quantidade);
                System.out.println("A quantidade do produto foi alterada com sucesso!");
                System.out.println(aux.getProduto());
            }
        }
    }

    // == Método para listar produtos ==
    public void listarProdutos(){
        
    }
}
