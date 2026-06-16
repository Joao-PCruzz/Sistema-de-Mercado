package main.java.mercado.ui;

import javax.swing.*;
import java.awt.*;
import main.java.mercado.model.Produto;

public class PainelProdutoCentro extends JPanel {
    private PainelProdutoInfo painelInfo;

    public PainelProdutoCentro(boolean isDono, Runnable onAnt, Runnable onProx) {
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        painelInfo = new PainelProdutoInfo(isDono);

        JButton btnAnt = new JButton("< Produto Anterior");
        JButton btnProx = new JButton("Próximo Produto >");

        btnAnt.addActionListener(e -> onAnt.run());
        btnProx.addActionListener(e -> onProx.run());

        add(btnAnt, BorderLayout.WEST);
        add(painelInfo, BorderLayout.CENTER);
        add(btnProx, BorderLayout.EAST);
    }

    public void atualizarProdutoInfo(Produto produto) {
        painelInfo.atualizarProduto(produto);
    }
}