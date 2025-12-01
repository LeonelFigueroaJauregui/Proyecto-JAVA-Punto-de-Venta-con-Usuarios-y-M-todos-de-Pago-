package Views;

import Models.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * Login view for user authentication.
 * Provides a graphical interface for users to log in to the POS system.
 */
public class LoginView extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JLabel statusLabel;

    /**
     * Creates a new LoginView.
     */
    public LoginView() {
        initializeComponents();
        setupLayout();
        configureFrame();
    }

    /**
     * Initializes the UI components.
     */
    private void initializeComponents() {
        usernameField = new JTextField(20);
        passwordField = new JPasswordField(20);
        loginButton = new JButton("Login");
        statusLabel = new JLabel(" ");
        statusLabel.setForeground(Color.RED);
    }

    /**
     * Sets up the layout of the login form.
     */
    private void setupLayout() {
        // Main panel with padding
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(30, 40, 30, 40));
        mainPanel.setBackground(new Color(245, 245, 245));

        // Title
        JLabel titleLabel = new JLabel("Point of Sale System", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(new Color(51, 51, 51));
        mainPanel.add(titleLabel, BorderLayout.NORTH);

        // Form panel
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBackground(new Color(245, 245, 245));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        // Username
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.EAST;
        formPanel.add(new JLabel("Username:"), gbc);

        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST;
        formPanel.add(usernameField, gbc);

        // Password
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.EAST;
        formPanel.add(new JLabel("Password:"), gbc);

        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST;
        formPanel.add(passwordField, gbc);

        // Login button
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        loginButton.setPreferredSize(new Dimension(120, 35));
        loginButton.setBackground(new Color(66, 139, 202));
        loginButton.setForeground(Color.WHITE);
        formPanel.add(loginButton, gbc);

        // Status label
        gbc.gridy = 3;
        formPanel.add(statusLabel, gbc);

        mainPanel.add(formPanel, BorderLayout.CENTER);

        // Footer
        JLabel footerLabel = new JLabel("© 2025 POS System", SwingConstants.CENTER);
        footerLabel.setFont(new Font("Arial", Font.PLAIN, 11));
        footerLabel.setForeground(Color.GRAY);
        mainPanel.add(footerLabel, BorderLayout.SOUTH);

        add(mainPanel);
    }

    /**
     * Configures the frame properties.
     */
    private void configureFrame() {
        setTitle("POS System - Login");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(450, 350);
        setLocationRelativeTo(null);
        setResizable(false);
    }

    /**
     * Gets the entered username.
     * 
     * @return Username
     */
    public String getUsername() {
        return usernameField.getText();
    }

    /**
     * Gets the entered password.
     * 
     * @return Password
     */
    public String getPassword() {
        return new String(passwordField.getPassword());
    }

    /**
     * Sets the status message.
     * 
     * @param message Status message to display
     * @param isError true if it's an error message
     */
    public void setStatus(String message, boolean isError) {
        statusLabel.setText(message);
        statusLabel.setForeground(isError ? Color.RED : new Color(34, 139, 34));
    }

    /**
     * Clears the form fields.
     */
    public void clearFields() {
        usernameField.setText("");
        passwordField.setText("");
        statusLabel.setText(" ");
    }

    /**
     * Adds action listener to the login button.
     * 
     * @param listener Action listener
     */
    public void addLoginListener(ActionListener listener) {
        loginButton.addActionListener(listener);
        // Also allow Enter key to trigger login
        passwordField.addActionListener(listener);
    }

    /**
     * Shows the login view.
     */
    public void showView() {
        setVisible(true);
    }

    /**
     * Hides the login view.
     */
    public void hideView() {
        setVisible(false);
    }
}
