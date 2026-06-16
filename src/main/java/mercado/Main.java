package main.java.mercado;

import javax.swing.*;
import main.java.mercado.controller.MercadoController;
import main.java.mercado.ui.MenuPrincipalUI;

/*
 Componentes do Grupo G7:
 1- Alan Cristian de Jesus Dias;
 2- Breno de Jesus Silva;
 3- João Pedro Costa Cruz;
 4- Wagner Kauê Martins dos Santos.
*/

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