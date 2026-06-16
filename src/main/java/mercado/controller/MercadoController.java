package main.java.mercado.controller;

import javax.swing.*;
import java.io.*;
import java.nio.charset.StandardCharsets;
import main.java.mercado.estrutura.NoCategoria;
import main.java.mercado.estrutura.NoProduto;
import main.java.mercado.model.Produto;
import main.java.mercado.service.CategoriaService;
import main.java.mercado.service.ProdutoSevice;
import main.java.mercado.service.OrdenacaoService;
import main.java.mercado.service.RelatorioService;

public class MercadoController {
    private final CategoriaService categoriaService;
    private final ProdutoSevice produtoService;
    private final OrdenacaoService ordenacaoService;
    private final RelatorioService relatorioService;

    public MercadoController() {
        this.categoriaService = new CategoriaService();
        this.produtoService = new ProdutoSevice();
        this.ordenacaoService = new OrdenacaoService();
        this.relatorioService = new RelatorioService();
        carregarDados();
        // configurar para salvar dados quando fechar o programa
        Runtime.getRuntime().addShutdownHook(new Thread(this::salvarDados));
    }

    public void cadastrarCategoria(JFrame parente) {
        String nome = JOptionPane.showInputDialog(parente, "Digite o nome da nova categoria:");
        if (nome != null && !nome.trim().isEmpty()) {
            categoriaService.cadastrarCategoria(nome.trim());
            JOptionPane.showMessageDialog(parente, "Categoria cadastrada com sucesso!");
        }
    }

    public void cadastrarProduto(JFrame parente) {
        NoCategoria categoria = selecionarCategoria(parente);
        if (categoria == null)
            return;

        try {
            String nome = JOptionPane.showInputDialog(parente, "Nome do Produto:");
            if (nome == null)
                return;

            String precoStr = JOptionPane.showInputDialog(parente, "Preço do Produto:");
            if (precoStr == null)
                return;
            double preco = Double.parseDouble(precoStr.replace(',', '.'));

            String qtdStr = JOptionPane.showInputDialog(parente, "Quantidade em Estoque:");
            if (qtdStr == null)
                return;
            int qtd = Integer.parseInt(qtdStr);

            String demStr = JOptionPane.showInputDialog(parente, "Demanda do Produto (Opcional):", "0");
            if (demStr == null)
                return;
            double demanda = Double.parseDouble(demStr.replace(',', '.'));

            Produto p = new Produto(nome, preco, qtd, demanda, categoria.getNomeCategoria());
            produtoService.cadastrarProduto(categoria, p);

            JOptionPane.showMessageDialog(parente, "Produto cadastrado com sucesso!");
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(parente, "Erro: Digite apenas números válidos para preço e quantidade.",
                    "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    public boolean cadastrarCategoriaRelativa(JFrame parente, NoCategoria atual) {
        String nome = JOptionPane.showInputDialog(parente, "Digite o nome da nova categoria:");
        if (nome != null && !nome.trim().isEmpty()) {
            if (atual != null) {
                categoriaService.inserirCategoriaApos(nome.trim(), atual);
            } else {
                categoriaService.cadastrarCategoria(nome.trim());
            }
            JOptionPane.showMessageDialog(parente, "Categoria cadastrada com sucesso!");
            return true;
        }
        return false; // operação cancelada
    }

    public boolean cadastrarProdutoNaCategoriaRelativo(JFrame parente, NoCategoria categoria, NoProduto produtoAtual) {
        if (categoria == null) {
            JOptionPane.showMessageDialog(parente, "Selecione ou crie uma categoria primeiro.", "Aviso",
                    JOptionPane.WARNING_MESSAGE);
            return false;
        }

        try {
            String nome = JOptionPane.showInputDialog(parente,
                    "Nome do Produto (na categoria " + categoria.getNomeCategoria() + "):");
            if (nome == null || nome.trim().isEmpty())
                return false;

            String precoStr = JOptionPane.showInputDialog(parente, "Preço do Produto:");
            if (precoStr == null)
                return false;
            double preco = Double.parseDouble(precoStr.replace(',', '.'));

            String qtdStr = JOptionPane.showInputDialog(parente, "Quantidade em Estoque:");
            if (qtdStr == null)
                return false;
            int qtd = Integer.parseInt(qtdStr);

            String demStr = JOptionPane.showInputDialog(parente, "Demanda do Produto (Opcional):", "0");
            if (demStr == null)
                return false;
            double demanda = Double.parseDouble(demStr.replace(',', '.'));

            Produto p = new Produto(nome, preco, qtd, demanda, categoria.getNomeCategoria());
            if (produtoAtual != null) {
                produtoService.inserirProdutoApos(categoria, produtoAtual, p);
            } else {
                produtoService.cadastrarProduto(categoria, p);
            }

            JOptionPane.showMessageDialog(parente, "Produto cadastrado com sucesso!");
            return true;
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(parente, "Erro: Digite apenas números válidos para preço e quantidade.",
                    "Erro", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    public void listarProdutosPorCategoria(JFrame parente) {
        NoCategoria categoria = selecionarCategoria(parente);
        if (categoria == null)
            return;

        produtoService.listarProdutos(categoria);
        JOptionPane.showMessageDialog(parente, "Verifique o console temporariamente para ver os produtos!");
    }

    public void removerProduto(NoCategoria categoria, String nomeProduto) {
        produtoService.removerProduto(categoria, nomeProduto);
    }

    public boolean removerCategoria(String nomeCategoria) {
        return categoriaService.removerCategoria(nomeCategoria) != null;
    }

    public void editarCategoria(NoCategoria categoria, String novoNome) {
        if (categoria != null && novoNome != null && !novoNome.trim().isEmpty()) {
            categoria.setNomeCategoria(novoNome.trim());
        }
    }

    public void ordenarProdutosGui(JFrame parente, NoCategoria categoriaAtual) {
        if (categoriaAtual == null) {
            JOptionPane.showMessageDialog(parente, "Nenhuma categoria selecionada.", "Aviso",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        String[] opcoes = {
                "1 - Nome (A-Z)",
                "2 - Preço (Crescente)",
                "3 - Preço (Decrescente)",
                "4 - Quantidade (Crescente)",
                "5 - Quantidade (Decrescente)",
                "6 - Demanda (Crescente)",
                "7 - Demanda (Decrescente)"
        };

        String escolha = (String) JOptionPane.showInputDialog(parente,
                "Selecione o critério de ordenação:", "Ordenar Produtos",
                JOptionPane.QUESTION_MESSAGE, null, opcoes, opcoes[0]);

        if (escolha != null) {
            int criterio = Integer.parseInt(escolha.substring(0, 1));
            ordenacaoService.ordenarProdutos(categoriaAtual, criterio);
            JOptionPane.showMessageDialog(parente, "Produtos ordenados com sucesso! Verifique a listagem.");
        }
    }

    public void exibirRelatoriosGui(JFrame parente, NoCategoria categoriaAtual) {
        if (categoriaAtual == null) {
            JOptionPane.showMessageDialog(parente, "Nenhuma categoria selecionada.", "Aviso",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        String[] opcoes = {
                "1 - 10 Produtos Mais Baratos",
                "2 - Produto Mais Caro",
                "3 - 10 Produtos com Menor Quantidade (Estoque Baixo)",
                "4 - 10 Produtos com Maior Quantidade",
                "5 - 10 Produtos com Maior Demanda",
                "6 - 10 Produtos com Menor Demanda"
        };

        String escolha = (String) JOptionPane.showInputDialog(parente,
                "Selecione o relatório desejado:", "Relatórios e Rankings",
                JOptionPane.QUESTION_MESSAGE, null, opcoes, opcoes[0]);

        if (escolha != null) {
            String resultado = "";

            if (escolha.startsWith("1"))
                resultado = relatorioService.gerarRelatorio10MaisBaratos(categoriaAtual);
            else if (escolha.startsWith("2"))
                resultado = relatorioService.gerarRelatorioProdutoMaisCaro(categoriaAtual);
            else if (escolha.startsWith("3"))
                resultado = relatorioService.gerarRelatorio10ComQuantidadeMaisBaixa(categoriaAtual);
            else if (escolha.startsWith("4"))
                resultado = relatorioService.gerarRelatorio10ComQuantidadeMaisAlta(categoriaAtual);
            else if (escolha.startsWith("5"))
                resultado = relatorioService.gerarRelatorio10ComMaiorDemanda(categoriaAtual);
            else if (escolha.startsWith("6"))
                resultado = relatorioService.gerarRelatorio10ComMenorDemanda(categoriaAtual);

            // Exibe a lista bonita montada pelo RelatorioService na tela do usuário
            JOptionPane.showMessageDialog(parente, resultado, "Resultado do Relatório",
                    JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private NoCategoria selecionarCategoria(JFrame parente) {
        if (categoriaService.getPrimCategoria() == null) {
            JOptionPane.showMessageDialog(parente, "Nenhuma categoria cadastrada. Cadastre uma categoria primeiro.",
                    "Aviso", JOptionPane.WARNING_MESSAGE);
            return null;
        }

        String nomeCategoria = JOptionPane.showInputDialog(parente, "Digite o nome da Categoria:");
        if (nomeCategoria == null || nomeCategoria.trim().isEmpty())
            return null;

        NoCategoria cat = categoriaService.buscarCategoria(nomeCategoria);
        if (cat == null) {
            JOptionPane.showMessageDialog(parente, "Categoria não encontrada!", "Erro", JOptionPane.ERROR_MESSAGE);
        }
        return cat;
    }

    public NoCategoria getPrimeiraCategoria() {
        return categoriaService.getPrimCategoria();
    }

    private void carregarDados() {
        File file = new File("dados_mercado.txt");
        if (!file.exists())
            return;

        try (BufferedReader br = new BufferedReader(
                new InputStreamReader(new FileInputStream(file), StandardCharsets.UTF_8))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] partes = linha.split("\\|");
                if (partes[0].equals("C") && partes.length >= 2) {
                    categoriaService.cadastrarCategoria(partes[1]);
                } else if (partes[0].equals("P") && partes.length >= 6) {
                    NoCategoria cat = categoriaService.buscarCategoria(partes[5]);
                    if (cat != null) {
                        Produto p = new Produto(partes[1], Double.parseDouble(partes[2]), Integer.parseInt(partes[3]),
                                Double.parseDouble(partes[4]), partes[5]);
                        produtoService.cadastrarProduto(cat, p);
                    }
                }
            }
        } catch (Exception e) {
            System.err.println("Erro ao carregar dados: " + e.getMessage());
        }
    }

    private void salvarDados() {
        try (PrintWriter out = new PrintWriter(
                new OutputStreamWriter(new FileOutputStream("dados_mercado.txt"), StandardCharsets.UTF_8))) {
            NoCategoria catAtual = categoriaService.getPrimCategoria();
            if (catAtual != null) {
                NoCategoria c = catAtual;
                do {
                    out.println("C|" + c.getNomeCategoria());
                    NoProduto prodAtual = c.getPrimProduto();
                    if (prodAtual != null) {
                        NoProduto p = prodAtual;
                        do {
                            Produto prod = p.getProduto();
                            double demanda = prod.getDemanda();
                            out.println("P|" + prod.getNome() + "|" + prod.getPreco() + "|" + prod.getQuantidade() + "|"
                                    + demanda + "|" + c.getNomeCategoria());
                            p = p.getProximo();
                        } while (p != prodAtual);
                    }
                    c = c.getProxCategoria();
                } while (c != catAtual);
            }
        } catch (Exception e) {
            System.err.println("Erro ao salvar dados: " + e.getMessage());
        }
    }
}