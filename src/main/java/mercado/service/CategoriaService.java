package main.java.mercado.service;
import main.java.mercado.estrutura.*;

public class CategoriaService {
    //Essa classe será responsável pelas operações envolvendo a categoria.
    //Ponteiro para guardar a primeira categoria e ser possível realizar as operações
    private NoCategoria primCategoria;

    public void setPrimCategoria(NoCategoria primCategoria){
        this.primCategoria = primCategoria;
    }
    public NoCategoria getPrimCategroia(){
        return this.primCategoria;
    }

    // == Método para cadastrar Categoria == 
    public void cadastrarCategoria(String nome){
        //Veirifcar se é a primeira cegotira a ser cadastrara
        if(primCategoria == null){
            //Fazer o ponteiro da primeira categoria apontar para a nova categoria
            NoCategoria novaCategoria = new NoCategoria(nome);
            primCategoria = novaCategoria;
            System.out.println("Primeira categoria adicionada: " + nome);
        }
        //Se não é a primeira categoria a ser cadastrada
        else{
            //Criação de um nó auxiliar
            NoCategoria aux = primCategoria;
            //Laço para viajar até a ultima categpria da lista
            while(aux.getProxCategoria() != primCategoria){
                aux = aux.getProxCategoria();
            }
            //Realização das atribuições e aritmetica de ponteiros (aux representa a última categoria)
            NoCategoria novaCategoria = new NoCategoria(nome, primCategoria, aux);
            aux.setProxCategoria(novaCategoria);
            primCategoria.setAntCategoria(novaCategoria);
            System.out.println("Categoria adicionada com sucesso: " + nome);
        }
    }
}
