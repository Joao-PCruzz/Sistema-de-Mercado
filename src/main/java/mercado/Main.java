package main.java.mercado;

import java.util.Scanner;

import main.java.mercado.estrutura.NoCategoria;
import main.java.mercado.estrutura.NoProduto;
import main.java.mercado.model.Produto;
import main.java.mercado.service.CategoriaService;
import main.java.mercado.service.OrdenacaoService;
import main.java.mercado.service.ProdutoSevice;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final CategoriaService categoriaService = new CategoriaService();
    private static final ProdutoSevice produtoService = new ProdutoSevice();
    private static final OrdenacaoService ordenacaoService = new OrdenacaoService();

    public static void main(String[] args) {
        int opcao;

        do {
            exibirMenuPrincipal();
            opcao = lerInteiro("Escolha uma opcao: ");
            System.out.println();

            switch (opcao) {
                case 1:
                    cadastrarCategoria();
                    break;
                case 2:
                    listarCategorias();
                    break;
                case 3:
                    removerCategoria();
                    break;
                case 4:
                    cadastrarProduto();
                    break;
                case 5:
                    listarProdutosPorCategoria();
                    break;
                case 6:
                    buscarProduto();
                    break;
                case 7:
                    removerProduto();
                    break;
                case 8:
                    alterarPreco();
                    break;
                case 9:
                    alterarQuantidade();
                    break;
                case 10:
                    ordenarProdutos();
                    break;
                case 0:
                    System.out.println("Sistema encerrado. Ate logo!");
                    break;
                default:
                    System.out.println("Opcao invalida. Tente novamente.");
            }

            pausar();
        } while (opcao != 0);

        scanner.close();
    }

    private static void exibirMenuPrincipal() {
        System.out.println("\n==================================");
        System.out.println("        SISTEMA DE MERCADO");
        System.out.println("==================================");
        System.out.println("1  - Cadastrar categoria");
        System.out.println("2  - Listar categorias");
        System.out.println("3  - Remover categoria");
        System.out.println("4  - Cadastrar produto");
        System.out.println("5  - Listar produtos por categoria");
        System.out.println("6  - Buscar produto");
        System.out.println("7  - Remover produto");
        System.out.println("8  - Alterar preco do produto");
        System.out.println("9  - Alterar quantidade do produto");
        System.out.println("10 - Ordenar produtos");
        System.out.println("0  - Sair");
        System.out.println("==================================");
    }

    private static void cadastrarCategoria() {
        String nome = lerTextoObrigatorio("Nome da categoria: ");
        categoriaService.cadastrarCategoria(nome);
    }

    private static void listarCategorias() {
        categoriaService.listarCategorias();
    }

    private static void removerCategoria() {
        String nome = lerTextoObrigatorio("Nome da categoria que deseja remover: ");
        categoriaService.removerCategoria(nome);
    }

    private static void cadastrarProduto() {
        NoCategoria categoria = selecionarCategoria();

        if (categoria == null) {
            return;
        }

        String nome = lerTextoObrigatorio("Nome do produto: ");
        double preco = lerDoublePositivo("Preco do produto: R$ ");
        int quantidade = lerInteiroNaoNegativo("Quantidade em estoque: ");
        double demanda = lerDoubleNaoNegativo("Demanda do produto: ");

        Produto produto = new Produto(
            nome,
            preco,
            quantidade,
            demanda,
            categoria.getNomeCategoria()
        );

        produtoService.cadastrarProduto(categoria, produto);
        System.out.println("Produto cadastrado com sucesso!");
    }

    private static void listarProdutosPorCategoria() {
        NoCategoria categoria = selecionarCategoria();

        if (categoria != null) {
            produtoService.listarProdutos(categoria);
        }
    }

    private static void buscarProduto() {
        NoCategoria categoria = selecionarCategoria();

        if (categoria == null) {
            return;
        }

        String nomeProduto = lerTextoObrigatorio("Nome do produto que deseja buscar: ");
        NoProduto encontrado = produtoService.buscarProduto(categoria, nomeProduto);

        if (encontrado != null) {
            System.out.println(encontrado.getProduto());
        }
    }

    private static void removerProduto() {
        NoCategoria categoria = selecionarCategoria();

        if (categoria == null) {
            return;
        }

        String nomeProduto = lerTextoObrigatorio("Nome do produto que deseja remover: ");
        NoProduto removido = produtoService.removerProduto(categoria, nomeProduto);

        if (removido != null) {
            System.out.println("Produto removido: " + removido.getProduto());
        }
    }

    private static void alterarPreco() {
        NoCategoria categoria = selecionarCategoria();

        if (categoria == null) {
            return;
        }

        String nomeProduto = lerTextoObrigatorio("Nome do produto: ");
        double novoPreco = lerDoublePositivo("Novo preco: R$ ");
        produtoService.alterarPreco(categoria, nomeProduto, novoPreco);
    }

    private static void alterarQuantidade() {
        NoCategoria categoria = selecionarCategoria();

        if (categoria == null) {
            return;
        }

        String nomeProduto = lerTextoObrigatorio("Nome do produto: ");
        int novaQuantidade = lerInteiroNaoNegativo("Nova quantidade: ");
        produtoService.alterarQauntidade(categoria, nomeProduto, novaQuantidade);
    }

    private static void ordenarProdutos() {
        NoCategoria categoria = selecionarCategoria();

        if (categoria == null) {
            return;
        }

        System.out.println("\nCriterios de ordenacao:");
        System.out.println("1 - Nome (A-Z)");
        System.out.println("2 - Preco (crescente)");
        System.out.println("3 - Preco (decrescente)");
        System.out.println("4 - Quantidade (crescente)");
        System.out.println("5 - Quantidade (decrescente)");
        System.out.println("6 - Demanda (crescente)");
        System.out.println("7 - Demanda (decrescente)");

        int criterio = lerInteiro("Escolha o criterio: ");

        if (criterio < 1 || criterio > 7) {
            System.out.println("Criterio invalido.");
            return;
        }

        ordenacaoService.ordenarProdutos(categoria, criterio);
        produtoService.listarProdutos(categoria);
    }

    private static NoCategoria selecionarCategoria() {
        if (categoriaService.getPrimCategoria() == null) {
            System.out.println("Nenhuma categoria cadastrada. Cadastre uma categoria primeiro.");
            return null;
        }

        categoriaService.listarCategorias();
        String nomeCategoria = lerTextoObrigatorio("Nome da categoria: ");
        return categoriaService.buscarCategoria(nomeCategoria);
    }

    private static String lerTexto(String mensagem) {
        System.out.print(mensagem);
        return scanner.nextLine().trim();
    }

    private static String lerTextoObrigatorio(String mensagem) {
        String texto;

        do {
            texto = lerTexto(mensagem);

            if (texto.isEmpty()) {
                System.out.println("Este campo nao pode ficar vazio.");
            }
        } while (texto.isEmpty());

        return texto;
    }

    private static int lerInteiro(String mensagem) {
        while (true) {
            System.out.print(mensagem);

            try {
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException erro) {
                System.out.println("Digite um numero inteiro valido.");
            }
        }
    }

    private static int lerInteiroNaoNegativo(String mensagem) {
        int valor;

        do {
            valor = lerInteiro(mensagem);

            if (valor < 0) {
                System.out.println("O valor nao pode ser negativo.");
            }
        } while (valor < 0);

        return valor;
    }

    private static double lerDoublePositivo(String mensagem) {
        double valor;

        do {
            valor = lerDouble(mensagem);

            if (valor <= 0) {
                System.out.println("O valor precisa ser maior que zero.");
            }
        } while (valor <= 0);

        return valor;
    }

    private static double lerDoubleNaoNegativo(String mensagem) {
        double valor;

        do {
            valor = lerDouble(mensagem);

            if (valor < 0) {
                System.out.println("O valor nao pode ser negativo.");
            }
        } while (valor < 0);

        return valor;
    }

    private static double lerDouble(String mensagem) {
        while (true) {
            System.out.print(mensagem);
            String entrada = scanner.nextLine().trim().replace(',', '.');

            try {
                return Double.parseDouble(entrada);
            } catch (NumberFormatException erro) {
                System.out.println("Digite um numero valido.");
            }
        }
    }

    private static void pausar() {
        System.out.println();
        System.out.print("Pressione ENTER para continuar...");
        scanner.nextLine();
    }
}
