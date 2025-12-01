package Views;

import Payments.PaymentMethod;
import Payments.CashPayment;
import Payments.CardPayment;
import Payments.TransferPayment;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * Payment view for processing payments.
 * Provides interface for selecting and completing payment methods.
 */
public class PaymentView extends JDialog {
    private double totalAmount;
    private PaymentMethod selectedPaymentMethod;
    
    // Payment type selection
    private JRadioButton cashRadio;
    private JRadioButton cardRadio;
    private JRadioButton transferRadio;
    private ButtonGroup paymentGroup;
    
    // Cash payment fields
    private JPanel cashPanel;
    private JTextField amountReceivedField;
    private JLabel changeLabel;
    
    // Card payment fields
    private JPanel cardPanel;
    private JTextField cardNumberField;
    private JTextField cardHolderField;
    private JTextField expiryField;
    private JPasswordField cvvField;
    
    // Transfer payment fields
    private JPanel transferPanel;
    private JTextField accountNumberField;
    private JTextField bankNameField;
    private JLabel storeAccountLabel;
    
    // Cards layout for payment panels
    private JPanel paymentDetailsPanel;
    private CardLayout cardLayout;
    
    // Labels
    private JLabel totalLabel;
    
    // Buttons
    private JButton processButton;
    private JButton cancelButton;
    
    // Result
    private boolean paymentProcessed = false;

    /**
     * Creates a new PaymentView.
     * 
     * @param parent      Parent frame
     * @param totalAmount Total amount to pay
     */
    public PaymentView(JFrame parent, double totalAmount) {
        super(parent, "Payment", true);
        this.totalAmount = totalAmount;
        initializeComponents();
        setupLayout();
        configureDialog();
        setupListeners();
    }

    /**
     * Initializes the UI components.
     */
    private void initializeComponents() {
        // Payment type radio buttons
        cashRadio = new JRadioButton("Cash", true);
        cardRadio = new JRadioButton("Credit/Debit Card");
        transferRadio = new JRadioButton("Bank Transfer");
        
        paymentGroup = new ButtonGroup();
        paymentGroup.add(cashRadio);
        paymentGroup.add(cardRadio);
        paymentGroup.add(transferRadio);
        
        // Cash payment components
        amountReceivedField = new JTextField(15);
        changeLabel = new JLabel("Change: $0.00");
        changeLabel.setFont(new Font("Arial", Font.BOLD, 14));
        
        // Card payment components
        cardNumberField = new JTextField(20);
        cardHolderField = new JTextField(20);
        expiryField = new JTextField(7);
        cvvField = new JPasswordField(5);
        
        // Transfer payment components
        accountNumberField = new JTextField(15);
        bankNameField = new JTextField(15);
        storeAccountLabel = new JLabel("<html><b>Store Account:</b><br>" + 
                TransferPayment.getStoreAccountInfo().replace("\n", "<br>") + "</html>");
        
        // Total label
        totalLabel = new JLabel(String.format("Total Amount: $%.2f", totalAmount));
        totalLabel.setFont(new Font("Arial", Font.BOLD, 18));
        
        // Buttons
        processButton = new JButton("Process Payment");
        cancelButton = new JButton("Cancel");
        
        // Card layout for payment panels
        cardLayout = new CardLayout();
        paymentDetailsPanel = new JPanel(cardLayout);
    }

    /**
     * Sets up the layout.
     */
    private void setupLayout() {
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        // Header with total
        JPanel headerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        headerPanel.setBackground(new Color(52, 152, 219));
        headerPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        totalLabel.setForeground(Color.WHITE);
        headerPanel.add(totalLabel);
        mainPanel.add(headerPanel, BorderLayout.NORTH);

        // Center panel
        JPanel centerPanel = new JPanel(new BorderLayout(10, 10));
        
        // Payment type selection
        JPanel typePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        typePanel.setBorder(BorderFactory.createTitledBorder("Select Payment Method"));
        typePanel.add(cashRadio);
        typePanel.add(cardRadio);
        typePanel.add(transferRadio);
        centerPanel.add(typePanel, BorderLayout.NORTH);

        // Create payment detail panels
        cashPanel = createCashPanel();
        cardPanel = createCardPanel();
        transferPanel = createTransferPanel();
        
        paymentDetailsPanel.add(cashPanel, "CASH");
        paymentDetailsPanel.add(cardPanel, "CARD");
        paymentDetailsPanel.add(transferPanel, "TRANSFER");
        
        centerPanel.add(paymentDetailsPanel, BorderLayout.CENTER);
        mainPanel.add(centerPanel, BorderLayout.CENTER);

        // Buttons
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 10));
        
        cancelButton.setPreferredSize(new Dimension(100, 35));
        buttonPanel.add(cancelButton);
        
        processButton.setPreferredSize(new Dimension(150, 35));
        processButton.setBackground(new Color(46, 204, 113));
        processButton.setForeground(Color.WHITE);
        buttonPanel.add(processButton);
        
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(mainPanel);
    }

    /**
     * Creates the cash payment panel.
     * 
     * @return Cash payment panel
     */
    private JPanel createCashPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createTitledBorder("Cash Payment"));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        gbc.gridx = 0; gbc.gridy = 0; gbc.anchor = GridBagConstraints.EAST;
        panel.add(new JLabel("Amount Received:"), gbc);
        
        gbc.gridx = 1; gbc.anchor = GridBagConstraints.WEST;
        JPanel amountPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        amountPanel.add(new JLabel("$ "));
        amountPanel.add(amountReceivedField);
        panel.add(amountPanel, gbc);

        gbc.gridx = 0; gbc.gridy = 1; gbc.gridwidth = 2; gbc.anchor = GridBagConstraints.CENTER;
        changeLabel.setForeground(new Color(46, 204, 113));
        panel.add(changeLabel, gbc);

        // Add listener to calculate change
        amountReceivedField.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyReleased(java.awt.event.KeyEvent e) {
                calculateChange();
            }
        });

        return panel;
    }

    /**
     * Creates the card payment panel.
     * 
     * @return Card payment panel
     */
    private JPanel createCardPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createTitledBorder("Card Payment"));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 10, 8, 10);

        gbc.gridx = 0; gbc.gridy = 0; gbc.anchor = GridBagConstraints.EAST;
        panel.add(new JLabel("Card Number:"), gbc);
        gbc.gridx = 1; gbc.anchor = GridBagConstraints.WEST;
        panel.add(cardNumberField, gbc);

        gbc.gridx = 0; gbc.gridy = 1; gbc.anchor = GridBagConstraints.EAST;
        panel.add(new JLabel("Card Holder:"), gbc);
        gbc.gridx = 1; gbc.anchor = GridBagConstraints.WEST;
        panel.add(cardHolderField, gbc);

        gbc.gridx = 0; gbc.gridy = 2; gbc.anchor = GridBagConstraints.EAST;
        panel.add(new JLabel("Expiry (MM/YY):"), gbc);
        gbc.gridx = 1; gbc.anchor = GridBagConstraints.WEST;
        panel.add(expiryField, gbc);

        gbc.gridx = 0; gbc.gridy = 3; gbc.anchor = GridBagConstraints.EAST;
        panel.add(new JLabel("CVV:"), gbc);
        gbc.gridx = 1; gbc.anchor = GridBagConstraints.WEST;
        panel.add(cvvField, gbc);

        return panel;
    }

    /**
     * Creates the transfer payment panel.
     * 
     * @return Transfer payment panel
     */
    private JPanel createTransferPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createTitledBorder("Bank Transfer"));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 10, 8, 10);

        gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 2; gbc.anchor = GridBagConstraints.CENTER;
        storeAccountLabel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(52, 152, 219)),
                BorderFactory.createEmptyBorder(10, 10, 10, 10)));
        panel.add(storeAccountLabel, gbc);

        gbc.gridwidth = 1;
        gbc.gridx = 0; gbc.gridy = 1; gbc.anchor = GridBagConstraints.EAST;
        panel.add(new JLabel("Your Account:"), gbc);
        gbc.gridx = 1; gbc.anchor = GridBagConstraints.WEST;
        panel.add(accountNumberField, gbc);

        gbc.gridx = 0; gbc.gridy = 2; gbc.anchor = GridBagConstraints.EAST;
        panel.add(new JLabel("Your Bank:"), gbc);
        gbc.gridx = 1; gbc.anchor = GridBagConstraints.WEST;
        panel.add(bankNameField, gbc);

        return panel;
    }

    /**
     * Configures the dialog properties.
     */
    private void configureDialog() {
        setSize(450, 400);
        setLocationRelativeTo(getParent());
        setResizable(false);
    }

    /**
     * Sets up the event listeners.
     */
    private void setupListeners() {
        // Payment type selection
        cashRadio.addActionListener(e -> cardLayout.show(paymentDetailsPanel, "CASH"));
        cardRadio.addActionListener(e -> cardLayout.show(paymentDetailsPanel, "CARD"));
        transferRadio.addActionListener(e -> cardLayout.show(paymentDetailsPanel, "TRANSFER"));
        
        // Cancel button
        cancelButton.addActionListener(e -> {
            paymentProcessed = false;
            dispose();
        });
    }

    /**
     * Calculates and displays the change for cash payment.
     */
    private void calculateChange() {
        try {
            String text = amountReceivedField.getText().trim();
            if (!text.isEmpty()) {
                double received = Double.parseDouble(text);
                double change = received - totalAmount;
                if (change >= 0) {
                    changeLabel.setText(String.format("Change: $%.2f", change));
                    changeLabel.setForeground(new Color(46, 204, 113));
                } else {
                    changeLabel.setText("Insufficient amount");
                    changeLabel.setForeground(Color.RED);
                }
            } else {
                changeLabel.setText("Change: $0.00");
            }
        } catch (NumberFormatException ex) {
            changeLabel.setText("Invalid amount");
            changeLabel.setForeground(Color.RED);
        }
    }

    /**
     * Gets the selected payment method type.
     * 
     * @return "CASH", "CARD", or "TRANSFER"
     */
    public String getSelectedPaymentType() {
        if (cashRadio.isSelected()) return "CASH";
        if (cardRadio.isSelected()) return "CARD";
        return "TRANSFER";
    }

    /**
     * Creates and returns the payment method based on selected type and entered data.
     * 
     * @return PaymentMethod instance or null if validation fails
     */
    public PaymentMethod createPaymentMethod() {
        String type = getSelectedPaymentType();
        
        switch (type) {
            case "CASH":
                try {
                    double received = Double.parseDouble(amountReceivedField.getText().trim());
                    if (received < totalAmount) {
                        showMessage("Insufficient cash amount", "Error", JOptionPane.ERROR_MESSAGE);
                        return null;
                    }
                    CashPayment cashPayment = new CashPayment(received);
                    selectedPaymentMethod = cashPayment;
                    return cashPayment;
                } catch (NumberFormatException e) {
                    showMessage("Please enter a valid cash amount", "Error", JOptionPane.ERROR_MESSAGE);
                    return null;
                }
                
            case "CARD":
                CardPayment cardPayment = new CardPayment(
                        cardNumberField.getText().trim(),
                        cardHolderField.getText().trim(),
                        expiryField.getText().trim(),
                        new String(cvvField.getPassword())
                );
                if (!cardPayment.isValid()) {
                    showMessage("Please enter valid card details", "Error", JOptionPane.ERROR_MESSAGE);
                    return null;
                }
                selectedPaymentMethod = cardPayment;
                return cardPayment;
                
            case "TRANSFER":
                TransferPayment transferPayment = new TransferPayment(
                        accountNumberField.getText().trim(),
                        bankNameField.getText().trim()
                );
                if (!transferPayment.isValid()) {
                    showMessage("Please enter valid bank account details", "Error", JOptionPane.ERROR_MESSAGE);
                    return null;
                }
                selectedPaymentMethod = transferPayment;
                return transferPayment;
                
            default:
                return null;
        }
    }

    /**
     * Checks if the payment was processed successfully.
     * 
     * @return true if payment was processed
     */
    public boolean isPaymentProcessed() {
        return paymentProcessed;
    }

    /**
     * Sets the payment processed status.
     * 
     * @param processed true if payment was processed
     */
    public void setPaymentProcessed(boolean processed) {
        this.paymentProcessed = processed;
    }

    /**
     * Gets the selected payment method.
     * 
     * @return Selected payment method
     */
    public PaymentMethod getSelectedPaymentMethod() {
        return selectedPaymentMethod;
    }

    /**
     * Adds listener to the process button.
     * 
     * @param listener Action listener
     */
    public void addProcessPaymentListener(ActionListener listener) {
        processButton.addActionListener(listener);
    }

    /**
     * Shows a message dialog.
     * 
     * @param message Message to display
     * @param title   Dialog title
     * @param type    Message type
     */
    public void showMessage(String message, String title, int type) {
        JOptionPane.showMessageDialog(this, message, title, type);
    }

    /**
     * Closes the dialog.
     */
    public void closeView() {
        dispose();
    }
}
