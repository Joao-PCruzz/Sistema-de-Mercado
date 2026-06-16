package main.java.mercado.ui;

import javax.swing.*;
import java.awt.*;
import main.java.mercado.controller.MercadoController;
import main.java.mercado.estrutura.NoCategoria;
import main.java.mercado.estrutura.NoProduto;
import main.java.mercado.model.Produto;

public class CatalogoAcoesServico {
    private final MercadoController controller;
    private final Component parentComponent;

    public CatalogoAcoesServico(MercadoController controller, Component parentComponent) {
        this.controller = controller;
        this.parentComponent = parentComponent;
    }

    public boolean adicionarCategoria(NoCategoria categoriaAtual) {
        return controller.cadastrarCategoriaRelativa(null, categoriaAtual);
    }

    public void editarCategoria(NoCategoria categoriaAtual) {
        if (categoriaAtual == null) {
            mostrarAviso("Nenhuma categoria selecionada.");
            return;
        }
        String novoNome = JOptionPane.showInputDialog(parentComponent, "Novo nome para a categoria:",
                categoriaAtual.getNomeCategoria());
        if (novoNome != null && !novoNome.trim().isEmpty()) {
            controller.editarCategoria(categoriaAtual, novoNome);
            JOptionPane.showMessageDialog(parentComponent, "Categoria renomeada com sucesso!");
        }
    }

    public boolean excluirCategoria(NoCategoria categoriaAtual) {
        if (categoriaAtual == null) {
            mostrarAviso("Nenhuma categoria selecionada.");
            return false;
        }
        int confirm = JOptionPane.showConfirmDialog(parentComponent,
                "Deseja realmente excluir a categoria '" + categoriaAtual.getNomeCategoria()
                        + "' e todos os seus produtos?",
                "Confirmar Exclusão", JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION) {
            if (controller.removerCategoria(categoriaAtual.getNomeCategoria())) {
                JOptionPane.showMessageDialog(parentComponent, "Categoria removida com sucesso!");
                return true;
            }
        }
        return false;
    }

    public boolean adicionarProduto(NoCategoria categoriaAtual, NoProduto produtoAtual) {
        return controller.cadastrarProdutoNaCategoriaRelativo(null, categoriaAtual, produtoAtual);
    }

    public void editarProduto(NoProduto produtoAtual) {
        if (produtoAtual == null) {
            mostrarAviso("Nenhum produto selecionado para editar.");
            return;
        }

        Produto p = produtoAtual.getProduto();
        JPanel panel = new JPanel(new GridLayout(4, 2, 5, 5));

        JTextField txtNome = new JTextField(p.getNome());
        txtNome.setEditable(false);
        JTextField txtPreco = new JTextField(String.valueOf(p.getPreco()));
        JTextField txtQuantidade = new JTextField(String.valueOf(p.getQuantidade()));
        JTextField txtDemanda = new JTextField(String.valueOf(p.getDemanda()));

        panel.add(new JLabel("Nome (não editável):"));
        panel.add(txtNome);
        panel.add(new JLabel("Preço:"));
        panel.add(txtPreco);
        panel.add(new JLabel("Quantidade:"));
        panel.add(txtQuantidade);
        panel.add(new JLabel("Demanda:"));
        panel.add(txtDemanda);

        if (JOptionPane.showConfirmDialog(parentComponent, panel, "Editar Produto",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE) == JOptionPane.OK_OPTION) {
            try {
                p.setPreco(Double.parseDouble(txtPreco.getText().replace(',', '.')));
                p.setQuantidade(Integer.parseInt(txtQuantidade.getText()));
                p.setDemanda(Double.parseDouble(txtDemanda.getText().replace(',', '.')));
                JOptionPane.showMessageDialog(parentComponent, "Produto atualizado com sucesso!", "Sucesso",
                        JOptionPane.INFORMATION_MESSAGE);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(parentComponent, "Valores numéricos inválidos.", "Erro",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public boolean excluirProduto(NoCategoria categoriaAtual, NoProduto produtoAtual) {
        if (produtoAtual == null) {
            mostrarAviso("Nenhum produto selecionado para exclusão.");
            return false;
        }

        if (JOptionPane.showConfirmDialog(parentComponent, "Deseja realmente excluir este produto?",
                "Confirmar Exclusão", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
            try {
                controller.removerProduto(categoriaAtual, produtoAtual.getProduto().getNome());
                JOptionPane.showMessageDialog(parentComponent, "Produto removido com sucesso!", "Sucesso",
                        JOptionPane.INFORMATION_MESSAGE);
                return true;
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(parentComponent, "Erro ao excluir o produto: " + ex.getMessage(), "Erro",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
        return false;
    }

    private void mostrarAviso(String msg) {
        JOptionPane.showMessageDialog(parentComponent, msg, "Aviso", JOptionPane.WARNING_MESSAGE);
    }
}
