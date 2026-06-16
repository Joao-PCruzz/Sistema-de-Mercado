package main.java.mercado.ui;


import javax.swing.*;
import java.awt.*;
import main.java.mercado.model.Produto;

public class PainelProdutoInfo extends JPanel {
    private final boolean mostrarDemanda;

    // mostrarDemanda seleciona se é menu do cliente ou do dono 
    public PainelProdutoInfo(boolean mostrarDemanda) {
        setLayout(new GridBagLayout()); // centralizar
        this.mostrarDemanda = mostrarDemanda;
    }

    public void atualizarProduto(Produto p) {
        removeAll(); // limpar tela

        JPanel conteudo = new JPanel();
        conteudo.setLayout(new BoxLayout(conteudo, BoxLayout.Y_AXIS));

        if (p == null) {
            JLabel lblVazio = new JLabel("Nenhum produto nesta categoria.");
            lblVazio.setForeground(Color.GRAY);
            lblVazio.setFont(new Font("Arial", Font.PLAIN, 16));
            lblVazio.setAlignmentX(Component.CENTER_ALIGNMENT);

            conteudo.add(lblVazio);
        } else {
            JLabel lblNome = new JLabel(p.getNome());
            lblNome.setFont(new Font("Arial", Font.BOLD, 16));
            lblNome.setAlignmentX(Component.CENTER_ALIGNMENT);

            JLabel lblPreco = new JLabel(String.format("Preço: R$ %.2f", p.getPreco()));
            lblPreco.setFont(new Font("Arial", Font.PLAIN, 16));
            lblPreco.setAlignmentX(Component.CENTER_ALIGNMENT);

            JLabel lblEstoque = new JLabel(String.format("Em estoque: %d", p.getQuantidade()));
            lblEstoque.setFont(new Font("Arial", Font.PLAIN, 16));
            lblEstoque.setAlignmentX(Component.CENTER_ALIGNMENT);

            conteudo.add(lblNome);
            conteudo.add(Box.createVerticalStrut(15));
            conteudo.add(lblPreco);
            conteudo.add(Box.createVerticalStrut(5));
            conteudo.add(lblEstoque);

            // mostrar demanda de for menu do dono
            if (mostrarDemanda) {
                JLabel lblDemanda = new JLabel(String.format("Demanda: %.2f", p.getDemanda()));
                lblDemanda.setFont(new Font("Arial", Font.PLAIN, 16));
                lblDemanda.setAlignmentX(Component.CENTER_ALIGNMENT);

                conteudo.add(Box.createVerticalStrut(5));
                conteudo.add(lblDemanda);
            }
        }

        add(conteudo);
        revalidate();
        repaint();
    }
}