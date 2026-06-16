package main.java.mercado.ui;

import javax.swing.*;
import java.awt.*;

public class PainelCategoriaTop extends JPanel {
    private JLabel lblCategoria;

    public PainelCategoriaTop(boolean isDono, Runnable onAnt, Runnable onProx,
            Runnable onAdd, Runnable onEdit, Runnable onDel) {
        setLayout(new GridLayout(isDono ? 2 : 1, 1));

        JPanel panelNav = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 5));
        JButton btnAnt = new JButton("< Categoria Anterior");
        JButton btnProx = new JButton("Próxima Categoria >");
        lblCategoria = new JLabel("Categoria: Carregando...");
        lblCategoria.setFont(new Font("Arial", Font.BOLD, 14));

        btnAnt.addActionListener(e -> onAnt.run());
        btnProx.addActionListener(e -> onProx.run());

        panelNav.add(btnAnt);
        panelNav.add(lblCategoria);
        panelNav.add(btnProx);
        add(panelNav);

        if (isDono) {
            JPanel panelAcoes = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 5));
            JButton btnAdd = new JButton("Nova Categoria");
            JButton btnEdit = new JButton("Editar Categoria");
            JButton btnDel = new JButton("Excluir Categoria");

            btnAdd.addActionListener(e -> onAdd.run());
            btnEdit.addActionListener(e -> onEdit.run());
            btnDel.addActionListener(e -> onDel.run());

            panelAcoes.add(btnAdd);
            panelAcoes.add(btnEdit);
            panelAcoes.add(btnDel);
            add(panelAcoes);
        }
    }

    public void atualizarCategoriaInfo(String nomeCategoria) {
        lblCategoria.setText("Categoria: " + nomeCategoria);
    }
}