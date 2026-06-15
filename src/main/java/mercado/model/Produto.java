package main.java.mercado.model;


//IMPORTANTES OBSERVAÇÕES:

//Cada arquivo da imagem do produto deve ter a seguinte descrição (para poder fazer um espécie de automação depois):
//imagens/categoria/produto.png.

public class Produto {

    private String nome;
    private double preco;
    private String imagem;//aqui ele terá o caminho da imagem.
    private int quantidade;
    private double demanda;
    private String categoria;//"setar" a categoria para poder navegar melhpr depois.

    //Construtor da clase
    public Produto(String nome, double preco, String imagem, int quantidade, double demanda, String categoria) {
        this.nome = nome;
        this.preco = preco;
        this.imagem = imagem;
        this.quantidade = quantidade;
        this.demanda = demanda;
        this.categoria = categoria;
    }

    //Getters and Setters


    public String getNome() {
        return nome;//retorna o nome do item.
    }

    public void setNome(String nome) {
        this.nome = nome;//"seta" o nome do item.
    }

    public double getPreco() {
        return preco;//retorna o preço do item.
    }

    public void setPreco(double preco) {
        this.preco = preco;//"seta" o preço do item.
    }

    public String getImagem() {
        return imagem;//retorna o caminho da imagem.
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;//"seta" o caminho da imagem.
    }

    public void setQuantidade(int quantidade){
        this.quantidade = quantidade;
    }

    public int getQuantidade(){
        return this.quantidade;
    }

    public double getDemanda() {
        return demanda;//retorna a demanda do item.
    }

    public void setDemanda(double demanda) {
        this.demanda = demanda;//"seta" a demanda do item.
    }

    public void setCategoria(String categoria){
        this.categoria = categoria;//"seta" a categoria do item.
    }

    public String getCategoria(){
        return categoria;//retorna a categoria.
    }
    
    //To String
    @Override
    public String toString(){
        return String.format("%s (R$ %.2f) - Qtd: %d | Demanda: %d", nome, preco, quantidade, demanda);
    }
}
