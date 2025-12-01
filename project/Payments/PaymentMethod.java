package Payments;

/**
 * Interface defining the contract for all payment methods.
 * Implements the Strategy Pattern for payment processing.
 */
public interface PaymentMethod {
    
    /**
     * Processes the payment for the specified amount.
     * 
     * @param amount The amount to process
     * @return true if payment was successful, false otherwise
     */
    boolean processPayment(double amount);
    
    /**
     * Gets the name of the payment method.
     * 
     * @return Name of the payment method
     */
    String getPaymentMethodName();
    
    /**
     * Validates if the payment method is ready to process payments.
     * 
     * @return true if the payment method is valid
     */
    boolean isValid();
    
    /**
     * Gets the transaction details after processing.
     * 
     * @return Transaction details as a string
     */
    String getTransactionDetails();
}
