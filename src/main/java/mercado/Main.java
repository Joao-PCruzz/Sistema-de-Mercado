package main.java.mercado;

import javax.swing.*;
import main.java.mercado.controller.MercadoController;
import main.java.mercado.ui.MenuPrincipalUI;

public class Main {
    public static void main(String[] args) {
        //configura a janela
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        //inicia o controlador
        MercadoController controller = new MercadoController();

        //cria e exibe o menu principal
        SwingUtilities.invokeLater(() -> {
            MenuPrincipalUI menuPrincipal = new MenuPrincipalUI(controller);
            menuPrincipal.exibir();
        });
    }
}