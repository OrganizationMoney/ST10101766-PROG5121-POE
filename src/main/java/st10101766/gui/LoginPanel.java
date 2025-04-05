package st10101766.gui;

import st10101766.core.Login;
import st10101766.core.Registration;
import st10101766.core.User;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class LoginPanel extends JPanel {
    private User user;

    // Components
    private JPanel headerPanel;
    private JLabel logoLabel;
    private JLabel titleLabel;
    private JPanel formPanel;
    private JLabel usernameLabel;
    private JTextField usernameField;
    private JLabel passwordLabel;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JLabel feedbackLabel;

    public LoginPanel() {
        setBackground(new Color(236, 229, 221));
        initializeComponents();
        setupLayout();
        addEventListeners();
    }

    public void setUser(User user) {
        this.user = user;
    }

    private void initializeComponents() {
        headerPanel = new JPanel();
        headerPanel.setBackground(new Color(7, 94, 84));
        headerPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        logoLabel = new JLabel(new ImageIcon(getClass().getResource("/logo.png")));
        logoLabel.setPreferredSize(new Dimension(40, 40));
        titleLabel = new JLabel("Login", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 18));
        titleLabel.setForeground(Color.WHITE);

        formPanel = new JPanel();
        formPanel.setBackground(Color.WHITE);
        formPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
        formPanel.setMaximumSize(new Dimension(400, Integer.MAX_VALUE));

        usernameLabel = new JLabel("Username:");
        usernameField = new JTextField(20);
        passwordLabel = new JLabel("Password:");
        passwordField = new JPasswordField(20);
        loginButton = new JButton("Login");
        feedbackLabel = new JLabel("");

        // Styling
        Font labelFont = new Font("Segoe UI", Font.PLAIN, 14);
        Font fieldFont = new Font("Segoe UI", Font.PLAIN, 14);
        Color textColor = new Color(33, 33, 33);
        Color hintColor = new Color(120, 120, 120);

        for (JLabel label : new JLabel[]{usernameLabel, passwordLabel}) {
            label.setFont(labelFont);
            label.setForeground(textColor);
        }

        usernameField.setFont(fieldFont);
        usernameField.setForeground(textColor);
        usernameField.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(200, 200, 200), 1, true),
                new EmptyBorder(8, 8, 8, 8)));
        passwordField.setFont(fieldFont);
        passwordField.setForeground(textColor);
        passwordField.setBorder(usernameField.getBorder());

        loginButton.setFont(new Font("Segoe UI", Font.BOLD, 14));
        loginButton.setBackground(new Color(37, 211, 102));
        loginButton.setForeground(Color.WHITE);
        loginButton.setFocusPainted(false);
        loginButton.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        loginButton.setCursor(new Cursor(Cursor.HAND_CURSOR));

        feedbackLabel.setFont(new Font("Segoe UI", Font.ITALIC, 12));
        feedbackLabel.setForeground(new Color(244, 67, 54));

        // Placeholders
        setupPlaceholder(usernameField, "Enter username");
        setupPlaceholder(passwordField, "Enter password");
    }

    private void setupPlaceholder(JTextField field, String hint) {
        field.setText(hint);
        field.setForeground(new Color(120, 120, 120));
        field.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (field.getText().equals(hint)) {
                    field.setText("");
                    if (field instanceof JPasswordField) {
                        ((JPasswordField) field).setEchoChar('*');
                    }
                    field.setForeground(new Color(33, 33, 33));
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (field.getText().isEmpty()) {
                    field.setText(hint);
                    if (field instanceof JPasswordField) {
                        ((JPasswordField) field).setEchoChar((char) 0);
                    }
                    field.setForeground(new Color(120, 120, 120));
                }
            }
        });
    }

    private void setupLayout() {
        setLayout(new BorderLayout());
        headerPanel.setLayout(new BorderLayout());
        headerPanel.add(logoLabel, BorderLayout.WEST);
        headerPanel.add(titleLabel, BorderLayout.CENTER);
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
        formPanel.add(feedbackLabel, gbc);

        centerPanel.add(formPanel, new GridBagConstraints());
        add(centerPanel, BorderLayout.CENTER);
    }

    private void addEventListeners() {
        loginButton.addActionListener(e -> {
            String username = usernameField.getText().equals("Enter username") ? "" : usernameField.getText();
            String password = new String(passwordField.getPassword()).equals("Enter password") ? "" : new String(passwordField.getPassword());

            Registration reg = user.getUser(username);
            if (reg != null) {
                Login login = new Login(reg);
                if (login.loginUser(username, password)) {
                    feedbackLabel.setForeground(new Color(37, 211, 102));
                    feedbackLabel.setText("<html>" + login.returnLoginStatus().replace("\n", "<br>") + "</html>");
                } else {
                    feedbackLabel.setForeground(new Color(244, 67, 54));
                    feedbackLabel.setText(login.returnLoginStatus());
                }
            } else {
                feedbackLabel.setForeground(new Color(244, 67, 54));
                feedbackLabel.setText("Username not found, please register first.");
            }
        });
    }
}