package Payments;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Bank Transfer payment implementation.
 * Handles electronic bank transfer transactions.
 */
public class TransferPayment implements PaymentMethod {
    private String sourceAccount;
    private String destinationAccount;
    private String bankName;
    private String referenceNumber;
    private double amountPaid;
    private LocalDateTime transactionTime;
    private boolean processed;

    // Default store bank account
    private static final String STORE_ACCOUNT = "1234567890";
    private static final String STORE_BANK = "Bank of Commerce";

    /**
     * Creates a new TransferPayment instance.
     */
    public TransferPayment() {
        this.destinationAccount = STORE_ACCOUNT;
        this.processed = false;
    }

    /**
     * Creates a TransferPayment with source account details.
     * 
     * @param sourceAccount Customer's bank account number
     * @param bankName      Customer's bank name
     */
    public TransferPayment(String sourceAccount, String bankName) {
        this.sourceAccount = sourceAccount;
        this.bankName = bankName;
        this.destinationAccount = STORE_ACCOUNT;
        this.processed = false;
    }

    // Setters
    public void setSourceAccount(String sourceAccount) {
        this.sourceAccount = sourceAccount;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    /**
     * Gets the reference number for the transfer.
     * 
     * @return Reference number
     */
    public String getReferenceNumber() {
        return referenceNumber;
    }

    /**
     * Gets the store's bank account for display.
     * 
     * @return Store account information
     */
    public static String getStoreAccountInfo() {
        return String.format("Bank: %s\nAccount: %s", STORE_BANK, STORE_ACCOUNT);
    }

    @Override
    public boolean processPayment(double amount) {
        if (!isValid()) {
            return false;
        }
        
        // Simulate transfer processing
        this.amountPaid = amount;
        this.transactionTime = LocalDateTime.now();
        this.referenceNumber = generateReferenceNumber();
        this.processed = true;
        
        return true;
    }

    /**
     * Generates a unique reference number.
     * 
     * @return Reference number
     */
    private String generateReferenceNumber() {
        return "REF" + System.currentTimeMillis();
    }

    @Override
    public String getPaymentMethodName() {
        return "Bank Transfer";
    }

    @Override
    public boolean isValid() {
        // Basic validation
        if (sourceAccount == null || sourceAccount.length() < 8) {
            return false;
        }
        if (bankName == null || bankName.trim().isEmpty()) {
            return false;
        }
        return true;
    }

    @Override
    public String getTransactionDetails() {
        if (!processed) {
            return "No transaction processed";
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return String.format(
            "Payment Method: Bank Transfer\n" +
            "From Account: %s (%s)\n" +
            "To Account: %s (%s)\n" +
            "Amount: $%.2f\n" +
            "Reference Number: %s\n" +
            "Transaction Time: %s",
            sourceAccount, bankName, STORE_ACCOUNT, STORE_BANK, 
            amountPaid, referenceNumber, transactionTime.format(formatter)
        );
    }

    @Override
    public String toString() {
        return "TransferPayment{from=" + sourceAccount + ", bank=" + bankName + "}";
    }
}
