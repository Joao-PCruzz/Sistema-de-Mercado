package main.java.mercado.estrutura;

//O nó da categoria se comporta como uma lista duplamente encadeada circular.

public class NoCategoria {

    private String nomeCategoria; //Nome da categoria
    private NoCategoria proxCategoria; //Proxima categoria 
    private NoCategoria antCategoria; //Categoria anterior
    private NoProduto  primProduto; //Primeiro produto da categoria

    //A princípio um construtor nulo
    public NoCategoria(){
        nomeCategoria = null;
        proxCategoria = null;
        antCategoria = null;
        primProduto = null;
    }
    //Consturtor somente com o nome da categoria para servir de apoio no cadastro
    public NoCategoria(String nomeCategoria, NoCategoria proxCategoria, NoCategoria antCategoria){
        this.nomeCategoria = nomeCategoria;
        this.proxCategoria = proxCategoria;
        this.antCategoria = antCategoria;
        primProduto = null; 
    }
    public NoCategoria(String nomeCategoria){
        this.nomeCategoria = nomeCategoria;
        proxCategoria = antCategoria = null;
        primProduto = null;
    }



    //Getters and Setters

    public String getNomeCategoria() {
        return nomeCategoria;
    }

    public void setNomeCategoria(String nomeCategoria) {
        this.nomeCategoria = nomeCategoria;
    }

    public NoCategoria getProxCategoria() {
        return proxCategoria;
    }

    public void setProxCategoria(NoCategoria proxCategoria) {
        this.proxCategoria = proxCategoria;
    }

    public NoProduto getPrimProduto() {
        return primProduto;
    }

    public void setPrimProduto(NoProduto primProduto) {
        this.primProduto = primProduto;
    }

    public NoCategoria getAntCategoria(){
        return this.antCategoria;
    }

    public void setAntCategoria(NoCategoria antCategria){
        this.antCategoria = antCategria;
    }
    
    //Checagem se está vazia
    public boolean estaVazia(){
        return primProduto == null;
    }
}
