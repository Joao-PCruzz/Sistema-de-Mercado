package main.java.mercado.estrutura;
import main.java.mercado.model.Produto;

public class NoProduto {
    //Nós da lista duplamente encadeada de produtos
    private NoProduto proximo;
    private NoProduto anterior;
    private Produto produto;

    //Construtor vazio (a princípio)
    public NoProduto(){
        proximo = null;
        anterior = null;
        produto = null;
    }
    //Construtor para auxiliar operação de cadastro
    public NoProduto(NoProduto proximo, NoProduto anterior, Produto produto){
        this.anterior = anterior;
        this.produto = produto;
        this.proximo = proximo;
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

    public void setProduto(Produto produto){
        this.produto = produto;
    }

    public Produto getProduto(){
        return this.produto;
    }
    @Override
    public String toString() {
        return "NoProduto [proximo -> " + proximo + ", anterior -> " + anterior + ", produto=" + produto + "]";
    }
}
