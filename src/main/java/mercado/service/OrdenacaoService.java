package main.java.mercado.service;

import main.java.mercado.estrutura.*;
import main.java.mercado.model.*;

public class OrdenacaoService {
    //Essa classe será responsável por fazer as ordenações no projeto

    // == Método principal de Ordenação ==
    //O dono do mercado escolhe a categoria e o critério (1 para Alfabética, 2 e 3 para Preço, 
    // 4 e 5 para quantidade, 6 e 7 para demanda)
    public void ordenarProdutos(NoCategoria categoria, int criterio) {
        //Validações iniciais
        if (categoria == null || categoria.estaVazia()) {
            System.out.println("Não há produtos nesta categoria para ordenar.");
            return;
        }
        NoProduto inicio = categoria.getPrimProduto();
        //Se só tem 1 produto, já está ordenado
        if (inicio.getProximo() == inicio) {
            System.out.println("Já está ordenado!");
            return;
        }

        //Variável apra checar se houve movimento durante as trocas
        boolean houveTroca;
        do {
            //Variável auxiliar
            houveTroca = false;
            NoProduto atual = inicio;

            //Percorre a lista circular duplamente encadeada até o último elemento
            while (atual.getProximo() != inicio) {
                NoProduto proximoNo = atual.getProximo();
                boolean precisaTrocar = false;

                //Critério 1: Ordem Alfabética (Nome)
                if (criterio == 1) {
                    String nomeAtual = atual.getProduto().getNome();
                    String nomeProximo = proximoNo.getProduto().getNome();
                    if (nomeAtual.compareToIgnoreCase(nomeProximo) > 0) {
                        precisaTrocar = true;
                    }
                } 
                //Critério 2: Por Preço (Crescente)
                else if (criterio == 2) {
                    double precoAtual = atual.getProduto().getPreco();
                    double precoProximo = proximoNo.getProduto().getPreco();
                    if (precoAtual > precoProximo) {
                        precisaTrocar = true;
                    }
                }
                //Critério 3: Por preço (Decrescente)
                else if(criterio == 3){
                    double precoAtual = atual.getProduto().getPreco();
                    double precoProximo = proximoNo.getProduto().getPreco();
                    if(precoAtual < precoProximo){
                        precisaTrocar = true;
                    }
                }
                //Critério 4: Quantidade (crescente)
                else if(criterio == 4){
                    int quantidadeAtual = atual.getProduto().getQuantidade();
                    int quantidadeProximo = proximoNo.getProduto().getQuantidade();
                    if(quantidadeAtual > quantidadeProximo){
                        precisaTrocar = true;
                    }
                }
                //Critério 5: Quantidade (decrescente)
                else if(criterio == 5){
                    int quantidadeAtual = atual.getProduto().getQuantidade();
                    int quantidadeProximo = proximoNo.getProduto().getQuantidade();
                    if(quantidadeAtual < quantidadeProximo){
                        precisaTrocar = true;
                    }
                }
                //Critério 6: Demanda (crescente)
                else if(criterio == 6){
                    double demandaAtual = atual.getProduto().getDemanda();
                    double demandaProximo = proximoNo.getProduto().getDemanda();
                    if(demandaAtual > demandaProximo){
                        precisaTrocar = true;
                    }
                }
                //Critério 6: Demanda (decrescente)
                else if(criterio == 7){
                    double demandaAtual = atual.getProduto().getDemanda();
                    double demandaProximo = proximoNo.getProduto().getDemanda();
                    if(demandaAtual < demandaProximo){
                        precisaTrocar = true;
                    }
                }

                //Se o critério foi atendido, troca os objetos de lugar dentro dos nós
                if (precisaTrocar) {
                    Produto temp = atual.getProduto();
                    atual.setProduto(proximoNo.getProduto());
                    proximoNo.setProduto(temp);
                    houveTroca = true;
                }

                atual = atual.getProximo();
            }
        } while (houveTroca); // Repete o ciclo se alguma troca foi feita na última passada

        System.out.println("Produtos ordenados com sucesso!");
    }
}

