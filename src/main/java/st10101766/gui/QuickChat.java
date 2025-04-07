package st10101766.gui;

import st10101766.core.Login;
import st10101766.core.User;
import javax.swing.*;
import java.awt.*;
// Hi

public class QuickChat extends JFrame
{
    private User user;
    private RegistrationPanel registrationPanel;
    private LoginPanel loginPanel;

    public QuickChat()
    {
        initializeComponents();
        configureFrame();
    }

    private void initializeComponents()
    {
        user = new User();
        registrationPanel = new RegistrationPanel();
        loginPanel = new LoginPanel();

        registrationPanel.setUser(user, this);
        loginPanel.setUser(user, this);

        setContentPane(registrationPanel);
        System.out.println("Components initialized");
    }

    private void configureFrame()
    {
        setTitle(UIConstants.APP_TITLE);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1280, 720);
        setResizable(false);
        setLocationRelativeTo(null);
        try
        {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        setVisible(true);
        System.out.println("Frame configured");
    }

    public void switchToLogin()
    {
        setContentPane(loginPanel);
        revalidate();
        repaint();
        System.out.println("Switched to LoginPanel");
    }

    public void switchToMessage(Login login)
    {
        JOptionPane.showMessageDialog(this,
            "Messaging feature coming soon in Part II.",
            "Success",
            JOptionPane.INFORMATION_MESSAGE);
        System.out.println("Login successful for user: " + login.getUsername());
        loginPanel.reset();
    }

    public static void main(String[] args)
    {
        SwingUtilities.invokeLater(() -> new QuickChat());
    }
}