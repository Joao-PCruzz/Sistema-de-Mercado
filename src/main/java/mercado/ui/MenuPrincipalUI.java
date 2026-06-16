package main.java.mercado.ui;

import javax.swing.*;
import java.awt.*;
import main.java.mercado.controller.MercadoController;

public class MenuPrincipalUI {
    private final MercadoController controller;

    public MenuPrincipalUI(MercadoController controller) {
        this.controller = controller;
    }

    public void exibir() {
        JFrame frame = new JFrame("Sistema de Mercado");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new BorderLayout());

        JLabel lblTitulo = new JLabel("Selecione o painel desejado:", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 16));
        lblTitulo.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
        frame.add(lblTitulo, BorderLayout.NORTH);

        JPanel painelBotoes = new JPanel(new GridLayout(2, 1, 10, 10));
        painelBotoes.setBorder(BorderFactory.createEmptyBorder(0, 50, 50, 50));

        JButton btnDono = new JButton("Painel de Gerenciamento");
        btnDono.setFont(new Font("Arial", Font.PLAIN, 14));
        btnDono.addActionListener(e -> {
            frame.dispose();
            // true para exibir menu do dono
            new MenuCatalogoUI(controller, true).exibir();
        });

        JButton btnCliente = new JButton("Painel do Cliente");
        btnCliente.setFont(new Font("Arial", Font.PLAIN, 14));
        btnCliente.addActionListener(e -> {
            frame.dispose();
            // false para menu do cliente
            new MenuCatalogoUI(controller, false).exibir();
        });

        painelBotoes.add(btnDono);
        painelBotoes.add(btnCliente);

        frame.add(painelBotoes, BorderLayout.CENTER);
        frame.setVisible(true);
    }
}