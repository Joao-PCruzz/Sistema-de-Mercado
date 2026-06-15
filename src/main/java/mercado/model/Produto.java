package main.java.mercado.model;

public class Produto {

    private String nome;
    private double preco;
    private int quantidade;
    private double demanda;
    private String categoria;//"setar" a categoria para poder navegar melhpr depois.

    //Construtor da clase
    public Produto(String nome, double preco, int quantidade, double demanda, String categoria) {
        this.nome = nome;
        this.preco = preco;
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
        return String.format("%s (R$ %.2f) - Qtd: %d | Demanda: %.2f", nome, preco, quantidade, demanda);
    }
}
