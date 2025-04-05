package st10101766.gui;

import st10101766.core.Registration;
import st10101766.core.User;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class RegistrationPanel extends JPanel {
    private User user;
    private CardLayout cardLayout;
    private JPanel mainPanel;

    // Components
    private JPanel headerPanel;
    private JLabel logoLabel;
    private JLabel titleLabel;
    private JPanel formPanel;
    private JLabel usernameLabel;
    private JTextField usernameField;
    private JLabel passwordLabel;
    private JPasswordField passwordField;
    private JLabel cellphoneLabel;
    private JTextField cellphoneField;
    private JLabel firstnameLabel;
    private JTextField firstnameField;
    private JLabel lastnameLabel;
    private JTextField lastnameField;
    private JButton registerButton;
    private JLabel feedbackLabel;

    public RegistrationPanel() {
        setBackground(new Color(236, 229, 221)); // WhatsApp light gray
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
        headerPanel.setBackground(new Color(7, 94, 84)); // WhatsApp teal
        headerPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        logoLabel = new JLabel(new ImageIcon(getClass().getResource("/logo.png")));
        logoLabel.setPreferredSize(new Dimension(100, 100)); // Smaller logo
        titleLabel = new JLabel("Register", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 18));
        titleLabel.setForeground(Color.WHITE);

        formPanel = new JPanel();
        formPanel.setBackground(Color.WHITE);
        formPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
        formPanel.setMaximumSize(new Dimension(400, Integer.MAX_VALUE)); // Constrain width

        usernameLabel = new JLabel("Username:");
        usernameField = new JTextField(20);
        passwordLabel = new JLabel("Password:");
        passwordField = new JPasswordField(20);
        cellphoneLabel = new JLabel("Phone Number:");
        cellphoneField = new JTextField(20);
        firstnameLabel = new JLabel("First Name:");
        firstnameField = new JTextField(20);
        lastnameLabel = new JLabel("Last Name:");
        lastnameField = new JTextField(20);
        registerButton = new JButton("Register");
        feedbackLabel = new JLabel("");

        // Styling
        Font labelFont = new Font("Segoe UI", Font.PLAIN, 14);
        Font fieldFont = new Font("Segoe UI", Font.PLAIN, 14);
        Color textColor = new Color(33, 33, 33); // Darker gray
        Color hintColor = new Color(120, 120, 120); // Subtle hint gray

        for (JLabel label : new JLabel[]{usernameLabel, passwordLabel, cellphoneLabel, firstnameLabel, lastnameLabel}) {
            label.setFont(labelFont);
            label.setForeground(textColor);
        }

        for (JTextField field : new JTextField[]{usernameField, cellphoneField, firstnameField, lastnameField}) {
            field.setFont(fieldFont);
            field.setForeground(textColor);
            field.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(new Color(200, 200, 200), 1, true),
                    new EmptyBorder(8, 8, 8, 8)));
        }
        passwordField.setFont(fieldFont);
        passwordField.setForeground(textColor);
        passwordField.setBorder(usernameField.getBorder());

        registerButton.setFont(new Font("Segoe UI", Font.BOLD, 14));
        registerButton.setBackground(new Color(37, 211, 102)); // WhatsApp green
        registerButton.setForeground(Color.WHITE);
        registerButton.setFocusPainted(false);
        registerButton.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        registerButton.setCursor(new Cursor(Cursor.HAND_CURSOR));

        feedbackLabel.setFont(new Font("Segoe UI", Font.ITALIC, 12));
        feedbackLabel.setForeground(new Color(244, 67, 54)); // Red for errors

        // Placeholders
        setupPlaceholder(usernameField, "Enter username (e.g., Just_)");
        setupPlaceholder(passwordField, "Enter password (e.g., Med-Lemon$69");
        setupPlaceholder(cellphoneField, "Enter phone (e.g., +27697948993)");
        setupPlaceholder(firstnameField, "Enter first name");
        setupPlaceholder(lastnameField, "Enter last name");
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

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.NONE;
        gbc.weightx = 0.0;
        formPanel.add(cellphoneLabel, gbc);

        gbc.gridx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        formPanel.add(cellphoneField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.fill = GridBagConstraints.NONE;
        gbc.weightx = 0.0;
        formPanel.add(firstnameLabel, gbc);

        gbc.gridx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        formPanel.add(firstnameField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.fill = GridBagConstraints.NONE;
        gbc.weightx = 0.0;
        formPanel.add(lastnameLabel, gbc);

        gbc.gridx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        formPanel.add(lastnameField, gbc);

        gbc.gridx = 1;
        gbc.gridy = 5;
        gbc.fill = GridBagConstraints.NONE;
        gbc.weightx = 0.0;
        formPanel.add(registerButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        formPanel.add(feedbackLabel, gbc);

        centerPanel.add(formPanel, new GridBagConstraints());
        add(centerPanel, BorderLayout.CENTER);
    }

    private void addEventListeners() {
        registerButton.addActionListener(e -> {
            String username = usernameField.getText().equals("Enter username (e.g., Just_)") ? "" : usernameField.getText();
            String password = new String(passwordField.getPassword()).equals("Enter password (e.g., Med-Lemon$69") ? "" : new String(passwordField.getPassword());
            String cellPhone = cellphoneField.getText().equals("Enter phone (e.g., +27697948993)") ? "" : cellphoneField.getText();
            String firstName = firstnameField.getText().equals("Enter first name") ? "" : firstnameField.getText();
            String lastName = lastnameField.getText().equals("Enter last name") ? "" : lastnameField.getText();

            Registration reg = new Registration();
            String[] feedback = reg.registerUser(username, password, cellPhone, firstName, lastName);
            if (feedback[0].equals("You have been successfully registered")) {
                user.registerUser(username, password, cellPhone, firstName, lastName); // Store in User
                clearFields();
                feedbackLabel.setForeground(new Color(37, 211, 102)); // WhatsApp green
                feedbackLabel.setText(feedback[0]);
                Timer timer = new Timer(2000, evt -> cardLayout.show(mainPanel, "login"));
                timer.setRepeats(false);
                timer.start();
            } else {
                feedbackLabel.setForeground(new Color(244, 67, 54)); // Red
                feedbackLabel.setText("<html>" + String.join("<br>", feedback) + "</html>");
            }
        });
    }

    private void clearFields() {
        usernameField.setText("Enter username (e.g., Just_)");
        usernameField.setForeground(new Color(120, 120, 120));
        passwordField.setText("Enter password (e.g., Med-Lemon$69");
        passwordField.setEchoChar((char) 0);
        passwordField.setForeground(new Color(120, 120, 120));
        cellphoneField.setText("Enter phone (e.g., +27697948993)");
        cellphoneField.setForeground(new Color(120, 120, 120));
        firstnameField.setText("Enter first name");
        firstnameField.setForeground(new Color(120, 120, 120));
        lastnameField.setText("Enter last name");
        lastnameField.setForeground(new Color(120, 120, 120));
    }
}