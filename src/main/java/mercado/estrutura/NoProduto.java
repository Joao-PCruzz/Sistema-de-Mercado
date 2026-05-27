package main.java.mercado.estrutura;

public class NoProduto {
    //Nós da lista duplamente encadeada de produtos
    private NoProduto proximo;
    private NoProduto anterior;

    //Construtor vazio (a princípio)
    public NoProduto(){
        proximo = null;
        anterior = null;
    }

    //Getters and Setters
    public void setProximo(NoProduto novoProximo){
        this.proximo = novoProximo;
    }
    public void setAnterior(NoProduto novoAnterior){
        this.anterior = novoAnterior;
    }
    public NoProduto getProximo(){
        return this.proximo;
    }
    public NoProduto getAnterior(){
        return this.anterior;
    }
}
