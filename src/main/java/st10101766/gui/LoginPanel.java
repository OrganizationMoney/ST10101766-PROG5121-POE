package st10101766.gui;

import st10101766.core.Login;
import st10101766.core.Registration;
import st10101766.core.User;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;

public class LoginPanel extends JPanel {
    private User user;
    private CardLayout cardLayout;
    private JPanel mainPanel;

    private JPanel headerPanel;
    private JLabel logoLabel;
    private JLabel titleLabel;
    private JButton exitButton;
    private JPanel formPanel;
    private JLabel usernameLabel;
    private JTextField usernameField;
    private JLabel passwordLabel;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JTextArea feedbackArea;

    public LoginPanel() {
        setBackground(UIConstants.BACKGROUND_COLOR);
        initializeComponents();
        setupLayout();
        addEventListeners();
    }

    public void setUser(User user, CardLayout cardLayout, JPanel mainPanel) {
        this.user = user;
        this.cardLayout = cardLayout;
        this.mainPanel = mainPanel;
    }

    private void initializeComponents() {
        headerPanel = new JPanel();
        headerPanel.setBackground(UIConstants.HEADER_COLOR);
        headerPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        logoLabel = new JLabel(new ImageIcon(getClass().getResource(UIConstants.LOGO_PATH)));
        logoLabel.setPreferredSize(new Dimension(100, 100));
        titleLabel = new JLabel(UIConstants.LOGIN_TITLE, SwingConstants.CENTER);
        titleLabel.setFont(UIConstants.TITLE_FONT);
        titleLabel.setForeground(Color.WHITE);
        exitButton = new JButton("Exit");
        exitButton.setFont(UIConstants.BUTTON_FONT);
        exitButton.setBackground(Color.RED.darker());
        exitButton.setForeground(Color.WHITE);
        exitButton.setFocusPainted(false);
        exitButton.setBorder(new EmptyBorder(5, 15, 5, 15));

        formPanel = new JPanel();
        formPanel.setBackground(UIConstants.FORM_COLOR);
        formPanel.setBorder(new EmptyBorder(20, 20, 20, 20));

        usernameLabel = new JLabel("Username:");
        usernameField = new JTextField(20);
        passwordLabel = new JLabel("Password:");
        passwordField = new JPasswordField(20);
        loginButton = new JButton("Login");
        feedbackArea = new JTextArea(3, 20);
        feedbackArea.setEditable(false);
        feedbackArea.setLineWrap(true);
        feedbackArea.setWrapStyleWord(true);
        feedbackArea.setFont(UIConstants.FEEDBACK_FONT);
        feedbackArea.setForeground(UIConstants.ERROR_COLOR);
        feedbackArea.setBackground(UIConstants.FORM_COLOR);
        feedbackArea.setBorder(BorderFactory.createEmptyBorder());

        for (JLabel label : new JLabel[]{usernameLabel, passwordLabel}) {
            label.setFont(UIConstants.LABEL_FONT);
            label.setForeground(UIConstants.TEXT_COLOR);
        }
        usernameField.setFont(UIConstants.FIELD_FONT);
        usernameField.setForeground(UIConstants.TEXT_COLOR);
        usernameField.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(200, 200, 200), 1, true),
                new EmptyBorder(8, 8, 8, 8)));
        passwordField.setFont(UIConstants.FIELD_FONT);
        passwordField.setForeground(UIConstants.TEXT_COLOR);
        passwordField.setBorder(usernameField.getBorder());

        loginButton.setFont(UIConstants.BUTTON_FONT);
        loginButton.setBackground(UIConstants.BUTTON_COLOR);
        loginButton.setForeground(Color.WHITE);
        loginButton.setFocusPainted(false);
        loginButton.setBorder(new EmptyBorder(10, 20, 10, 20));
        loginButton.setCursor(new Cursor(Cursor.HAND_CURSOR));

        setupPlaceholder(usernameField, UIConstants.USERNAME_HINT);
        setupPlaceholder(passwordField, UIConstants.PASSWORD_HINT);
    }

    private void setupPlaceholder(JTextField field, String hint) {
        field.setText(hint);
        field.setForeground(UIConstants.HINT_COLOR);
        field.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (field.getText().equals(hint)) {
                    field.setText("");
                    if (field instanceof JPasswordField) {
                        ((JPasswordField) field).setEchoChar('*');
                    }
                    field.setForeground(UIConstants.TEXT_COLOR);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (field.getText().isEmpty()) {
                    field.setText(hint);
                    if (field instanceof JPasswordField) {
                        ((JPasswordField) field).setEchoChar((char) 0);
                    }
                    field.setForeground(UIConstants.HINT_COLOR);
                }
            }
        });
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
        formPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.EAST;
        formPanel.add(usernameLabel, gbc);

        gbc.gridx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        formPanel.add(usernameField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.NONE;
        gbc.weightx = 0.0;
        formPanel.add(passwordLabel, gbc);

        gbc.gridx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        formPanel.add(passwordField, gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.NONE;
        gbc.weightx = 0.0;
        formPanel.add(loginButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        formPanel.add(feedbackArea, gbc);

        centerPanel.add(formPanel, new GridBagConstraints());
        add(centerPanel, BorderLayout.CENTER);
    }

    private void addEventListeners() {
        loginButton.addActionListener(e -> handleLogin());
        exitButton.addActionListener(e -> System.exit(0));

        KeyAdapter enterKeyListener = new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    handleLogin();
                }
            }
        };
        usernameField.addKeyListener(enterKeyListener);
        passwordField.addKeyListener(enterKeyListener);

        loginButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                loginButton.setBackground(UIConstants.BUTTON_COLOR.brighter());
            }

            @Override
            public void mouseExited(MouseEvent e) {
                loginButton.setBackground(UIConstants.BUTTON_COLOR);
            }
        });
    }

    private void handleLogin() {
        String username = usernameField.getText().equals(UIConstants.USERNAME_HINT) ? "" : usernameField.getText();
        String password = new String(passwordField.getPassword()).equals(UIConstants.PASSWORD_HINT) ? "" : new String(passwordField.getPassword());

        Registration reg = user.getUser(username);
        if (reg != null) {
            Login login = new Login(reg);
            if (login.loginUser(username, password)) {
                feedbackArea.setForeground(UIConstants.SUCCESS_COLOR);
                feedbackArea.setText(login.returnLoginStatus());
                JOptionPane.showMessageDialog(this, login.returnLoginStatus(), "Success", JOptionPane.INFORMATION_MESSAGE);
                new Timer(2000, e -> {
                    cardLayout.show(mainPanel, UIConstants.MESSAGE_PANEL);
                    QuickChat parent = (QuickChat) SwingUtilities.getWindowAncestor(this);
                    parent.setCurrentLogin(login);
                }).start();
            } else {
                feedbackArea.setForeground(UIConstants.ERROR_COLOR);
                feedbackArea.setText(login.returnLoginStatus());
            }
        } else {
            feedbackArea.setForeground(UIConstants.ERROR_COLOR);
            feedbackArea.setText("Username not found, please register first.");
        }
    }
}