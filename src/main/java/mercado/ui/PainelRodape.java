package main.java.mercado.ui;

import javax.swing.*;
import java.awt.*;

public class PainelRodape extends JPanel {
    public PainelRodape(boolean isDono, Runnable onAdd, Runnable onEdit, Runnable onDel, Runnable onSair) {
        setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));

        if (isDono) {
            JButton btnAdd = new JButton("Novo Produto");
            JButton btnEdit = new JButton("Editar Produto");
            JButton btnDel = new JButton("Excluir Produto");

            btnAdd.addActionListener(e -> onAdd.run());
            btnEdit.addActionListener(e -> onEdit.run());
            btnDel.addActionListener(e -> onDel.run());

            add(btnAdd);
            add(btnEdit);
            add(btnDel);
        }

        JButton btnSair = new JButton("Voltar ao Início");
        btnSair.addActionListener(e -> onSair.run());
        add(btnSair);
    }
}