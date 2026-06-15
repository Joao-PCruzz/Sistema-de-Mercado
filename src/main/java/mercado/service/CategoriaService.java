package main.java.mercado.service;
import main.java.mercado.estrutura.*;

public class CategoriaService {
    //Essa classe será responsável pelas operações envolvendo a categoria.
    //Ponteiro para guardar a primeira categoria e ser possível realizar as operações
    private NoCategoria primCategoria;

    public void setPrimCategoria(NoCategoria primCategoria){
        this.primCategoria = primCategoria;
    }
    public NoCategoria getPrimCategoria(){
        return this.primCategoria;
    }

    // == Método para cadastrar Categoria == 
    public void cadastrarCategoria(String nome){
        //Veirifcar se é a primeira categoria a ser cadastrara
        if(primCategoria == null){
            //Fazer o ponteiro da primeira categoria apontar para a nova categoria
            NoCategoria novaCategoria = new NoCategoria(nome);
            novaCategoria.setProxCategoria(novaCategoria);
            novaCategoria.setAntCategoria(novaCategoria);
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

    // == Método para buscar categoria
    public NoCategoria buscarCategoria(String nomeCategoria){
        //Caso 1: Não existem categorias cadastradas
        if(primCategoria == null){
            System.out.println("A categoria esta vazia, nao existem produtos para buscar.");
            return null;
        }
        //Caso 2: Existem categorias
        else{
            //Nós auxiliares para percorrer as categorias
            NoCategoria inicio = primCategoria;
            NoCategoria atual = inicio;
            do{
                //Buscar a categoria pelo nome
                if(atual.getNomeCategoria().equalsIgnoreCase(nomeCategoria)){
                    System.out.println("Categoria encontrada com sucesso!");
                    return atual;
                }
                atual = atual.getProxCategoria();
            }while(atual != inicio);
            //Se chegar aqui, é porque não achou
            System.out.println("A categoria nao foi encontrada!");
            return null;
        }
    }

    // == Método para remover categoria ==
    public NoCategoria removerCategoria(String nomeCategoria){
        //Tratamento para se não houver categorias
        if(primCategoria == null){
            System.out.println("A categoria esta vazia, nao ha produtos para remover.");
            return null;
        }
        else{
            //Instanciando ponteiro de remoção
            NoCategoria removido;
            //Utilizando o método de busca criado anteriormente
            removido = buscarCategoria(nomeCategoria);
            //Se não encontrou a categoria
            if(removido == null){
                //Talvez esse print fique duplicado, nos testes irei observar isso e talvez remover essa linha
                System.out.println("A categoria não foi encontrada para remoção ");
                return null;
            }
            //Instanciando ponteiro auxiliar
            NoCategoria inicio = primCategoria;
            //Se houver somente um elemento na lista
            if(removido.getProxCategoria() == removido && removido.getAntCategoria() == removido){
                setPrimCategoria(null);
                return removido;
            }
            //Se não for a única categoria
            else{
                //Pega o anterior e coloca para apontar para o próximo do removido
                removido.getAntCategoria().setProxCategoria(removido.getProxCategoria());
                //Pega o proximo e coloca para apontar para o anterior do removido
                removido.getProxCategoria().setAntCategoria(removido.getAntCategoria());
                //Se a categoria removida for a primeira
                if(removido == inicio){
                    setPrimCategoria(removido.getProxCategoria());
                }
                removido.setProxCategoria(null);
                removido.setAntCategoria(null);
                System.out.println("Categoria removida com sucesso");
                return removido;
            }
        }
    }

    // == Méotodo para listar categorias ==
    public void listarCategorias() {
        //Caso não existam categorias cadastradas
        if (primCategoria == null) {
            System.out.println("Nenhuma categoria cadastrada no sistema.");
            return;
        }
        System.out.println("\n--- LISTA DE CATEGORIAS ---");
        //Nó auxilair para viajar pelas categorias
        NoCategoria atual = primCategoria;
        int contador = 1;
        do {
            System.out.println(contador + ". " + atual.getNomeCategoria());
            atual = atual.getProxCategoria();
            contador++;
        } while (atual != primCategoria); //Condição de parada da lista
        System.out.println("---------------------------");
    }
}
