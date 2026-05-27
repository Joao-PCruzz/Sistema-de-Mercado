package main.java.mercado.estrutura;

public class Multilista {
    private NoCategoria primCategoria;

     //Construtor
    public Multilista() {
        primCategoria = null;
    }

    public boolean estaVazia(){
        return primCategoria == null;
    }

    //Getters and Setters
    public NoCategoria getPrimCategoria() {
        return primCategoria;
    }
    public void setPrimCategoria(NoCategoria primCategoria) {
        this.primCategoria = primCategoria;
    }
}
