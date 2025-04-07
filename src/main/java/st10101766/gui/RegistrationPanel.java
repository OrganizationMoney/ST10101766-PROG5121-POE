package st10101766.gui;

import st10101766.core.User;
import st10101766.core.Registration;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;

public class RegistrationPanel extends JPanel
{
    private User user;
    private QuickChat quickChat;

    private JPanel headerPanel;
    private JLabel logoLabel;
    private JLabel titleLabel;
    private JButton exitButton;
    private JPanel formPanel;
    private JLabel usernameLabel;
    private JTextField usernameField;
    private JLabel passwordLabel;
    private JPasswordField passwordField;
    private JLabel confirmPasswordLabel;
    private JPasswordField confirmPasswordField;
    private JLabel phoneLabel;
    private JTextField phoneField;
    private JLabel firstNameLabel;
    private JTextField firstNameField;
    private JLabel lastNameLabel;
    private JTextField lastNameField;
    private JButton registerButton;
    private JTextArea feedbackArea;

    public RegistrationPanel()
    {
        setBackground(UIConstants.BACKGROUND_COLOR);
        initializeComponents();
        setupLayout();
        addEventListeners();
    }

    public void setUser(User user, QuickChat quickChat)
    {
        this.user = user;
        this.quickChat = quickChat;
    }

    private void initializeComponents()
    {
        headerPanel = new JPanel();
        headerPanel.setBackground(UIConstants.HEADER_COLOR);
        headerPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        logoLabel = new JLabel(new ImageIcon(getClass().getResource(UIConstants.LOGO_PATH)));
        logoLabel.setPreferredSize(new Dimension(100, 100));
        titleLabel = new JLabel(UIConstants.REGISTRATION_TITLE, SwingConstants.CENTER);
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
        confirmPasswordLabel = new JLabel("Confirm Password:");
        confirmPasswordField = new JPasswordField(20);
        phoneLabel = new JLabel("Phone Number:");
        phoneField = new JTextField(20);
        firstNameLabel = new JLabel("First Name:");
        firstNameField = new JTextField(20);
        lastNameLabel = new JLabel("Last Name:");
        lastNameField = new JTextField(20);
        registerButton = new JButton("Register");
        feedbackArea = new JTextArea(3, 20);
        feedbackArea.setEditable(false);
        feedbackArea.setLineWrap(true);
        feedbackArea.setWrapStyleWord(true);
        feedbackArea.setFont(UIConstants.FEEDBACK_FONT);
        feedbackArea.setForeground(UIConstants.ERROR_COLOR);
        feedbackArea.setBackground(UIConstants.FORM_COLOR);
        feedbackArea.setBorder(BorderFactory.createEmptyBorder());

        for (JLabel label : new JLabel[]{usernameLabel, passwordLabel, confirmPasswordLabel, phoneLabel, firstNameLabel, lastNameLabel})
        {
            label.setFont(UIConstants.LABEL_FONT);
            label.setForeground(UIConstants.TEXT_COLOR);
        }
        for (JTextField field : new JTextField[]{usernameField, phoneField, firstNameField, lastNameField})
        {
            field.setFont(UIConstants.FIELD_FONT);
            field.setForeground(UIConstants.TEXT_COLOR);
            field.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(new Color(200, 200, 200), 1, true),
                    new EmptyBorder(8, 8, 8, 8)));
        }
        passwordField.setFont(UIConstants.FIELD_FONT);
        passwordField.setForeground(UIConstants.TEXT_COLOR);
        passwordField.setBorder(usernameField.getBorder());
        confirmPasswordField.setFont(UIConstants.FIELD_FONT);
        confirmPasswordField.setForeground(UIConstants.TEXT_COLOR);
        confirmPasswordField.setBorder(usernameField.getBorder());

        registerButton.setFont(UIConstants.BUTTON_FONT);
        registerButton.setBackground(UIConstants.BUTTON_COLOR);
        registerButton.setForeground(Color.WHITE);
        registerButton.setFocusPainted(false);
        registerButton.setBorder(new EmptyBorder(10, 20, 10, 20));
        registerButton.setCursor(new Cursor(Cursor.HAND_CURSOR));

        setupPlaceholder(usernameField, UIConstants.USERNAME_HINT);
        setupPlaceholder(passwordField, UIConstants.PASSWORD_HINT);
        setupPlaceholder(confirmPasswordField, UIConstants.CONFIRM_PASSWORD_HINT);
        setupPlaceholder(phoneField, UIConstants.PHONE_HINT);
        setupPlaceholder(firstNameField, UIConstants.FIRST_NAME_HINT);
        setupPlaceholder(lastNameField, UIConstants.LAST_NAME_HINT);
    }

    private void setupPlaceholder(JTextField field, String hint)
    {
        field.setText(hint);
        field.setForeground(UIConstants.HINT_COLOR);
        field.addFocusListener(new FocusAdapter()
        {
            @Override
            public void focusGained(FocusEvent e)
            {
                if (field.getText().equals(hint))
                {
                    field.setText("");
                    if (field instanceof JPasswordField)
                    {
                        ((JPasswordField) field).setEchoChar('*');
                    }
                    field.setForeground(UIConstants.TEXT_COLOR);
                }
            }

            @Override
            public void focusLost(FocusEvent e)
            {
                if (field.getText().isEmpty())
                {
                    field.setText(hint);
                    if (field instanceof JPasswordField)
                    {
                        ((JPasswordField) field).setEchoChar((char) 0);
                    }
                    field.setForeground(UIConstants.HINT_COLOR);
                }
            }
        });
    }

    private void setupLayout()
    {
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

        gbc.gridx = 0;
        gbc.gridy = 2;
        formPanel.add(confirmPasswordLabel, gbc);

        gbc.gridx = 1;
        formPanel.add(confirmPasswordField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        formPanel.add(phoneLabel, gbc);

        gbc.gridx = 1;
        formPanel.add(phoneField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        formPanel.add(firstNameLabel, gbc);

        gbc.gridx = 1;
        formPanel.add(firstNameField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        formPanel.add(lastNameLabel, gbc);

        gbc.gridx = 1;
        formPanel.add(lastNameField, gbc);

        gbc.gridx = 1;
        gbc.gridy = 6;
        formPanel.add(registerButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 7;
        gbc.gridwidth = 2;
        formPanel.add(feedbackArea, gbc);

        centerPanel.add(formPanel, new GridBagConstraints());
        add(centerPanel, BorderLayout.CENTER);
    }

    private void addEventListeners()
    {
        registerButton.addActionListener(e -> handleRegistration());
        exitButton.addActionListener(e -> System.exit(0));

        KeyAdapter enterKeyListener = new KeyAdapter()
        {
            @Override
            public void keyPressed(KeyEvent e)
            {
                if (e.getKeyCode() == KeyEvent.VK_ENTER)
                {
                    handleRegistration();
                }
            }
        };
        usernameField.addKeyListener(enterKeyListener);
        passwordField.addKeyListener(enterKeyListener);
        confirmPasswordField.addKeyListener(enterKeyListener);
        phoneField.addKeyListener(enterKeyListener);
        firstNameField.addKeyListener(enterKeyListener);
        lastNameField.addKeyListener(enterKeyListener);
    }

    private void handleRegistration()
{
    String username = usernameField.getText().equals(UIConstants.USERNAME_HINT) ? "" : usernameField.getText();
    String password = new String(passwordField.getPassword()).equals(UIConstants.PASSWORD_HINT) ? "" : new String(passwordField.getPassword());
    String confirmPassword = new String(confirmPasswordField.getPassword()).equals(UIConstants.CONFIRM_PASSWORD_HINT) ? "" : new String(confirmPasswordField.getPassword());
    String phone = phoneField.getText().equals(UIConstants.PHONE_HINT) ? "" : phoneField.getText();
    String firstName = firstNameField.getText().equals(UIConstants.FIRST_NAME_HINT) ? "" : firstNameField.getText();
    String lastName = lastNameField.getText().equals(UIConstants.LAST_NAME_HINT) ? "" : lastNameField.getText();

    if (!password.equals(confirmPassword))
    {
        JOptionPane.showMessageDialog(this, "Passwords do not match.", "Registration Error", JOptionPane.ERROR_MESSAGE);
        return;
    }

    String[] result = user.registerUser(username, password, phone, firstName, lastName);
    for (String message : result)
    {
        if (message.equals("You have been successfully registered"))
        {
            JOptionPane.showMessageDialog(this, message, "Registration Success", JOptionPane.INFORMATION_MESSAGE);
            quickChat.switchToLogin();
        }
        else
        {
            JOptionPane.showMessageDialog(this, message, "Registration Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
}