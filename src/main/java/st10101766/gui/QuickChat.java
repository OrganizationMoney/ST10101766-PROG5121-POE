package st10101766.gui;

import st10101766.core.User;
import javax.swing.*;
import java.awt.*;

public class QuickChat extends JFrame {
    private User user;
    private CardLayout cardLayout;
    private JPanel mainPanel;
    private RegistrationPanel registrationPanel;
    private LoginPanel loginPanel;

    public QuickChat() {
        initializeComponents();
        setupLayout();
        configureFrame();
    }

    private void initializeComponents() {
        user = new User();
        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);
        mainPanel.setBackground(new Color(236, 229, 221));
        registrationPanel = new RegistrationPanel();
        loginPanel = new LoginPanel();

        registrationPanel.setUser(user, cardLayout, mainPanel);
        loginPanel.setUser(user);
    }

    private void setupLayout() {
        mainPanel.add(registrationPanel, "registration");
        mainPanel.add(loginPanel, "login");
        cardLayout.show(mainPanel, "registration");
        add(mainPanel);
    }

    private void configureFrame() {
        setTitle("QuickChat");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1280, 720); // WhatsApp-like mobile aspect
        setLocationRelativeTo(null);
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new QuickChat().setVisible(true));
    }
}