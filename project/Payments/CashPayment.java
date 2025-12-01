package Payments;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Cash payment implementation.
 * Handles cash transactions including change calculation.
 */
public class CashPayment implements PaymentMethod {
    private double amountReceived;
    private double change;
    private double amountPaid;
    private LocalDateTime transactionTime;
    private boolean processed;

    /**
     * Creates a new CashPayment instance.
     */
    public CashPayment() {
        this.processed = false;
    }

    /**
     * Creates a CashPayment with the amount received from customer.
     * 
     * @param amountReceived The cash amount received from the customer
     */
    public CashPayment(double amountReceived) {
        this.amountReceived = amountReceived;
        this.processed = false;
    }

    /**
     * Sets the amount received from the customer.
     * 
     * @param amountReceived The cash amount received
     */
    public void setAmountReceived(double amountReceived) {
        this.amountReceived = amountReceived;
    }

    /**
     * Gets the amount received from the customer.
     * 
     * @return Amount received
     */
    public double getAmountReceived() {
        return amountReceived;
    }

    /**
     * Gets the change to return to the customer.
     * 
     * @return Change amount
     */
    public double getChange() {
        return change;
    }

    @Override
    public boolean processPayment(double amount) {
        if (amountReceived >= amount) {
            this.amountPaid = amount;
            this.change = amountReceived - amount;
            this.transactionTime = LocalDateTime.now();
            this.processed = true;
            return true;
        }
        return false;
    }

    @Override
    public String getPaymentMethodName() {
        return "Cash";
    }

    @Override
    public boolean isValid() {
        return amountReceived > 0;
    }

    @Override
    public String getTransactionDetails() {
        if (!processed) {
            return "No transaction processed";
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return String.format(
            "Payment Method: Cash\n" +
            "Amount Paid: $%.2f\n" +
            "Amount Received: $%.2f\n" +
            "Change: $%.2f\n" +
            "Transaction Time: %s",
            amountPaid, amountReceived, change, transactionTime.format(formatter)
        );
    }

    @Override
    public String toString() {
        return "CashPayment{amountReceived=" + amountReceived + ", change=" + change + "}";
    }
}
