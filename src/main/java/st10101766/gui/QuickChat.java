package st10101766.gui;

import st10101766.core.Login;
import st10101766.core.User;
import javax.swing.*;
import java.awt.*;

public class QuickChat extends JFrame {
    private User user;
    private CardLayout cardLayout;
    private JPanel mainPanel;
    private RegistrationPanel registrationPanel;
    private LoginPanel loginPanel;
    private MessagePanel messagePanel;
    private Login currentLogin;

    public QuickChat() {
        initializeComponents();
        setupLayout();
        configureFrame();
    }

    private void initializeComponents() {
        user = new User();
        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);
        mainPanel.setBackground(UIConstants.BACKGROUND_COLOR);
        registrationPanel = new RegistrationPanel();
        loginPanel = new LoginPanel();
        messagePanel = new MessagePanel(null);

        registrationPanel.setUser(user, cardLayout, mainPanel);
        loginPanel.setUser(user, cardLayout, mainPanel);
    }

    private void setupLayout() {
        mainPanel.add(registrationPanel, UIConstants.REGISTRATION_PANEL);
        mainPanel.add(loginPanel, UIConstants.LOGIN_PANEL);
        mainPanel.add(messagePanel, UIConstants.MESSAGE_PANEL);
        cardLayout.show(mainPanel, UIConstants.REGISTRATION_PANEL);
        add(mainPanel);
    }

    private void configureFrame() {
        setTitle(UIConstants.APP_TITLE);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1280, 720); // Fixed HD size
        setResizable(false); // Prevent resizing
        setLocationRelativeTo(null);
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setCurrentLogin(Login login) {
        this.currentLogin = login;
        messagePanel.setLogin(login);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new QuickChat().setVisible(true));
    }
}