package main.java.mercado.ui;

import javax.swing.*;
import java.awt.*;
import main.java.mercado.controller.MercadoController;
import main.java.mercado.estrutura.NoCategoria;
import main.java.mercado.estrutura.NoProduto;

public class MenuCatalogoUI {
    private final MercadoController controller;
    private final boolean isDono;
    private JFrame frame;

    private NoCategoria categoriaAtual;
    private NoProduto produtoAtual;

    private PainelCategoriaTop painelCategoriaTop;
    private PainelProdutoCentro painelProdutoCentro;
    private CatalogoAcoesServico acoesServico;

    public MenuCatalogoUI(MercadoController controller, boolean isDono) {
        this.controller = controller;
        this.isDono = isDono;
    }

    public void exibir() {
        this.categoriaAtual = controller.getPrimeiraCategoria();

        if (!isDono && this.categoriaAtual == null) {
            JOptionPane.showMessageDialog(null, "Nenhuma categoria cadastrada.", "Aviso", JOptionPane.WARNING_MESSAGE);
            new MenuPrincipalUI(controller).exibir();
            return;
        }

        if (this.categoriaAtual != null) {
            this.produtoAtual = this.categoriaAtual.getPrimProduto();
        }

        this.frame = new JFrame(isDono ? "Painel de Gerenciamento" : "Painel do Cliente");
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.frame.setSize(750, 450);
        this.frame.setLocationRelativeTo(null);
        this.frame.setLayout(new BorderLayout(10, 10));

        this.acoesServico = new CatalogoAcoesServico(controller, frame);
        montarLayout();
        atualizarView();

        this.frame.setVisible(true);
    }

    private void montarLayout() {
        painelCategoriaTop = new PainelCategoriaTop(isDono,
                () -> mudarCategoria(false), () -> mudarCategoria(true),
                this::adicionarCategoria, this::editarCategoria, this::excluirCategoria);

        painelProdutoCentro = new PainelProdutoCentro(isDono,
                () -> mudarProduto(false), () -> mudarProduto(true));

        PainelRodape painelRodape = new PainelRodape(isDono,
                this::adicionarProduto, this::editarProduto, this::excluirProduto,
                () -> {
                    frame.dispose();
                    new MenuPrincipalUI(controller).exibir();
                });

        JPanel painelTopoCompleto = new JPanel(new BorderLayout());
        painelTopoCompleto.add(painelCategoriaTop, BorderLayout.CENTER);

        JPanel painelAcoesExibicao = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton btnOrdenar = new JButton("Ordenar Produtos");
        btnOrdenar.addActionListener(e -> {
            controller.ordenarProdutosGui(frame, categoriaAtual);
            recalcularEstadoAposAcao(); // atualiza a tela
        });
        JButton btnRelatorios = new JButton("Ver Relatórios");
        btnRelatorios.addActionListener(e -> controller.exibirRelatoriosGui(frame, categoriaAtual));

        painelAcoesExibicao.add(btnOrdenar);
        painelAcoesExibicao.add(btnRelatorios);
        painelTopoCompleto.add(painelAcoesExibicao, BorderLayout.SOUTH);

        frame.add(painelTopoCompleto, BorderLayout.NORTH);
        frame.add(painelProdutoCentro, BorderLayout.CENTER);
        frame.add(painelRodape, BorderLayout.SOUTH);
    }

    private void mudarCategoria(boolean proxima) {
        if (categoriaAtual == null)
            return;
        categoriaAtual = proxima ? categoriaAtual.getProxCategoria() : categoriaAtual.getAntCategoria();
        produtoAtual = categoriaAtual.getPrimProduto();
        atualizarView();
    }

    private void mudarProduto(boolean proximo) {
        if (produtoAtual == null)
            return;
        produtoAtual = proximo ? produtoAtual.getProximo() : produtoAtual.getAnterior();
        atualizarView();
    }

    private void atualizarView() {
        String nomeCat = (categoriaAtual != null) ? categoriaAtual.getNomeCategoria() : "Nenhuma";
        painelCategoriaTop.atualizarCategoriaInfo(nomeCat);
        painelProdutoCentro.atualizarProdutoInfo(produtoAtual == null ? null : produtoAtual.getProduto());
    }

    private void recalcularEstadoAposAcao() {
        if (categoriaAtual == null) {
            categoriaAtual = controller.getPrimeiraCategoria();
        }
        if (categoriaAtual != null && produtoAtual == null) {
            produtoAtual = categoriaAtual.getPrimProduto();
        }
        atualizarView();
    }

    private void adicionarCategoria() {
        if (acoesServico.adicionarCategoria(categoriaAtual))
            recalcularEstadoAposAcao();
    }

    private void editarCategoria() {
        acoesServico.editarCategoria(categoriaAtual);
        atualizarView();
    }

    private void excluirCategoria() {
        if (acoesServico.excluirCategoria(categoriaAtual)) {
            categoriaAtual = null;
            recalcularEstadoAposAcao();
        }
    }

    private void adicionarProduto() {
        if (acoesServico.adicionarProduto(categoriaAtual, produtoAtual))
            recalcularEstadoAposAcao();
    }

    private void editarProduto() {
        acoesServico.editarProduto(produtoAtual);
        atualizarView();
    }

    private void excluirProduto() {
        if (acoesServico.excluirProduto(categoriaAtual, produtoAtual)) {
            produtoAtual = null;
            recalcularEstadoAposAcao();
        }
    }
}