package st10101766.gui;

import st10101766.core.Login;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * Placeholder panel post-login, shows user details and "Coming Soon".
 */
public class MessagePanel extends JPanel {
    private Login login; // Holds logged-in user details
    private JPanel headerPanel;
    private JLabel logoLabel;
    private JLabel titleLabel;
    private JButton exitButton;
    private JLabel userLabel;
    private JLabel messageLabel;

    public MessagePanel(Login login) {
        this.login = login;
        setBackground(UIConstants.BACKGROUND_COLOR);
        initializeComponents();
        setupLayout();
        addEventListeners();
    }

    private void initializeComponents() {
        headerPanel = new JPanel();
        headerPanel.setBackground(UIConstants.HEADER_COLOR);
        headerPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        logoLabel = new JLabel(new ImageIcon(getClass().getResource(UIConstants.LOGO_PATH)));
        logoLabel.setPreferredSize(new Dimension(100, 100));
        titleLabel = new JLabel(UIConstants.MESSAGE_TITLE, SwingConstants.CENTER);
        titleLabel.setFont(UIConstants.TITLE_FONT);
        titleLabel.setForeground(Color.WHITE);
        exitButton = new JButton("Exit");
        exitButton.setFont(UIConstants.BUTTON_FONT);
        exitButton.setBackground(Color.RED.darker());
        exitButton.setForeground(Color.WHITE);
        exitButton.setFocusPainted(false);
        exitButton.setBorder(new EmptyBorder(5, 15, 5, 15));

        // Display logged-in username from Login object
        String username = login != null ? login.getUsername() : "Unknown User";
        userLabel = new JLabel("Welcome, " + username, SwingConstants.CENTER);
        userLabel.setFont(UIConstants.MESSAGE_FONT);
        userLabel.setForeground(UIConstants.TEXT_COLOR);

        messageLabel = new JLabel("Coming Soon in Part 2", SwingConstants.CENTER);
        messageLabel.setFont(UIConstants.MESSAGE_FONT);
        messageLabel.setForeground(UIConstants.TEXT_COLOR);
    }

    private void setupLayout() {
        setLayout(new BorderLayout());
        headerPanel.setLayout(new BorderLayout(10, 0));
        headerPanel.add(logoLabel, BorderLayout.WEST);
        headerPanel.add(titleLabel, BorderLayout.CENTER);
        headerPanel.add(exitButton, BorderLayout.EAST);
        add(headerPanel, BorderLayout.NORTH);

        JPanel centerPanel = new JPanel(new GridBagLayout());
        centerPanel.setBackground(getBackground());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 0, 10, 0);

        gbc.gridx = 0;
        gbc.gridy = 0;
        centerPanel.add(userLabel, gbc);

        gbc.gridy = 1;
        centerPanel.add(messageLabel, gbc);

        add(centerPanel, BorderLayout.CENTER);
    }

    private void addEventListeners() {
        exitButton.addActionListener(e -> System.exit(0));
    }

    /**
     * Updates the login object if changed (e.g., via QuickChat).
     */
    public void setLogin(Login login) {
        this.login = login;
        String username = login != null ? login.getUsername() : "Unknown User";
        userLabel.setText("Welcome, " + username);
    }
}