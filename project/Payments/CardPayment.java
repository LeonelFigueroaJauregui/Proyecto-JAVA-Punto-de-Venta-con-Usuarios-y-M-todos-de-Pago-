package Payments;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Credit/Debit Card payment implementation.
 * Handles card transactions with validation.
 */
public class CardPayment implements PaymentMethod {
    private String cardNumber;
    private String cardHolderName;
    private String expiryDate;
    private String cvv;
    private double amountPaid;
    private LocalDateTime transactionTime;
    private String transactionId;
    private boolean processed;

    /**
     * Creates a new CardPayment instance.
     */
    public CardPayment() {
        this.processed = false;
    }

    /**
     * Creates a CardPayment with card details.
     * 
     * @param cardNumber     The card number
     * @param cardHolderName Name on the card
     * @param expiryDate     Expiry date (MM/YY format)
     * @param cvv            Card verification value
     */
    public CardPayment(String cardNumber, String cardHolderName, String expiryDate, String cvv) {
        this.cardNumber = cardNumber;
        this.cardHolderName = cardHolderName;
        this.expiryDate = expiryDate;
        this.cvv = cvv;
        this.processed = false;
    }

    // Setters for card details
    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public void setCardHolderName(String cardHolderName) {
        this.cardHolderName = cardHolderName;
    }

    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }

    public void setCvv(String cvv) {
        this.cvv = cvv;
    }

    /**
     * Gets the masked card number (showing only last 4 digits).
     * 
     * @return Masked card number
     */
    public String getMaskedCardNumber() {
        if (cardNumber == null || cardNumber.length() < 4) {
            return "****";
        }
        return "**** **** **** " + cardNumber.substring(cardNumber.length() - 4);
    }

    @Override
    public boolean processPayment(double amount) {
        if (!isValid()) {
            return false;
        }
        
        // Simulate card processing
        this.amountPaid = amount;
        this.transactionTime = LocalDateTime.now();
        this.transactionId = generateTransactionId();
        this.processed = true;
        
        return true;
    }

    /**
     * Generates a unique transaction ID.
     * 
     * @return Transaction ID
     */
    private String generateTransactionId() {
        return "TXN" + System.currentTimeMillis();
    }

    @Override
    public String getPaymentMethodName() {
        return "Credit/Debit Card";
    }

    @Override
    public boolean isValid() {
        // Basic validation
        if (cardNumber == null || cardNumber.length() < 13 || cardNumber.length() > 19) {
            return false;
        }
        if (cardHolderName == null || cardHolderName.trim().isEmpty()) {
            return false;
        }
        if (expiryDate == null || !expiryDate.matches("\\d{2}/\\d{2}")) {
            return false;
        }
        if (cvv == null || cvv.length() < 3 || cvv.length() > 4) {
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
            "Payment Method: Credit/Debit Card\n" +
            "Card: %s\n" +
            "Card Holder: %s\n" +
            "Amount Paid: $%.2f\n" +
            "Transaction ID: %s\n" +
            "Transaction Time: %s",
            getMaskedCardNumber(), cardHolderName, amountPaid, transactionId, transactionTime.format(formatter)
        );
    }

    @Override
    public String toString() {
        return "CardPayment{card=" + getMaskedCardNumber() + ", holder=" + cardHolderName + "}";
    }
}
