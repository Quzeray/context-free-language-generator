package Application;

import Application.UI.MainFrame;

import javax.swing.*;

public class Application {
    public static void main(String[] args) {
        start();
    }

    private static void start() {
        SwingUtilities.invokeLater(() -> {
            MainFrame frame = new MainFrame();
            frame.setVisible(true);
        });
    }
}