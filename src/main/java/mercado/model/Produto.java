package main.java.mercado.model;

public class Produto {
    private String nome;
    private double preco;
    private String imagem;
    private int quantidade;
    private double demanda;

    //Construtor da clase
    public Produto(String nome, double preco, String imagem, int quantidade, double demanda) {
        this.nome = nome;
        this.preco = preco;
        this.imagem = imagem;
        this.quantidade = quantidade;
        this.demanda = demanda;
    }

    //Getters and Setters
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public double getPreco() {
        return preco;
    }
    public void setPreco(double preco) {
        this.preco = preco;
    }
    public String getImagem() {
        return imagem;
    }
    public void setImagem(String imagem) {
        this.imagem = imagem;
    }
    public double getDemanda() {
        return demanda;
    }
    public void setDemanda(double demanda) {
        this.demanda = demanda;
    }
    
    //To String
    @Override
    public String toString(){
        return String.format("%s (R$ %.2f) - Qtd: %d | Demanda: %d", nome, preco, quantidade, demanda);
    }
}
