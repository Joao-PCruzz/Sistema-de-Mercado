package main.java.mercado.estrutura;

//O nó da categoria se comporta como uma lista duplamente encadeada circular.

public class NoCategoria {

    private String nomeCategoria; //Nome da categoria
    private NoCategoria proxCategoria; //Proxima categoria 
    private NoProduto  primProduto; //Primeiro produto da categoria

    //A princípio um construtor nulo
    public NoCategoria(){
        nomeCategoria = null;
        proxCategoria = null;
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
    
}
