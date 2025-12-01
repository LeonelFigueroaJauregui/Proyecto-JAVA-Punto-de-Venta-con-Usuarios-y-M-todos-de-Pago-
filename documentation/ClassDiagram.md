# Class Diagram

This document describes the class structure for the Point of Sale (POS) System.

## Diagram (Mermaid)

```mermaid
classDiagram
    class User {
        -int id
        -String username
        -String password
        -String fullName
        -Role role
        -String email
        +getId() int
        +getUsername() String
        +getPassword() String
        +getFullName() String
        +getRole() Role
        +getEmail() String
        +setId(int id)
        +setUsername(String username)
        +setPassword(String password)
        +setFullName(String fullName)
        +setRole(Role role)
        +setEmail(String email)
        +isAdmin() boolean
        +validatePassword(String password) boolean
    }

    class Role {
        <<enumeration>>
        ADMIN
        CLIENT
    }

    class Product {
        -int id
        -String name
        -String description
        -double price
        -int stock
        -String category
        +getId() int
        +getName() String
        +getDescription() String
        +getPrice() double
        +getStock() int
        +getCategory() String
        +setId(int id)
        +setName(String name)
        +setDescription(String description)
        +setPrice(double price)
        +setStock(int stock)
        +setCategory(String category)
        +reduceStock(int quantity) boolean
        +addStock(int quantity)
    }

    class CartItem {
        -Product product
        -int quantity
        +getProduct() Product
        +getQuantity() int
        +setProduct(Product product)
        +setQuantity(int quantity)
        +getSubtotal() double
        +addQuantity(int amount)
        +removeQuantity(int amount) boolean
    }

    class Cart {
        -List~CartItem~ items
        -User user
        +addItem(Product product, int quantity) boolean
        +removeItem(int productId) boolean
        +updateQuantity(int productId, int newQuantity) boolean
        +getTotal() double
        +getItemCount() int
        +getTotalQuantity() int
        +clear()
        +isEmpty() boolean
        +getItems() List~CartItem~
        +getUser() User
    }

    class Order {
        -int id
        -User user
        -List~CartItem~ items
        -double total
        -String paymentMethod
        -Status status
        -LocalDateTime createdAt
        -LocalDateTime updatedAt
        +getId() int
        +getUser() User
        +getItems() List~CartItem~
        +getTotal() double
        +getPaymentMethod() String
        +getStatus() Status
        +markAsPaid(String paymentMethod)
        +cancel()
        +complete()
        +generateReceipt() String
    }

    class Status {
        <<enumeration>>
        PENDING
        PAID
        CANCELLED
        COMPLETED
    }

    class PaymentMethod {
        <<interface>>
        +processPayment(double amount) boolean
        +getPaymentMethodName() String
        +isValid() boolean
        +getTransactionDetails() String
    }

    class CashPayment {
        -double amountReceived
        -double change
        -double amountPaid
        -LocalDateTime transactionTime
        -boolean processed
        +setAmountReceived(double amount)
        +getAmountReceived() double
        +getChange() double
        +processPayment(double amount) boolean
        +getPaymentMethodName() String
        +isValid() boolean
        +getTransactionDetails() String
    }

    class CardPayment {
        -String cardNumber
        -String cardHolderName
        -String expiryDate
        -String cvv
        -double amountPaid
        -LocalDateTime transactionTime
        -String transactionId
        -boolean processed
        +setCardNumber(String cardNumber)
        +setCardHolderName(String name)
        +setExpiryDate(String expiry)
        +setCvv(String cvv)
        +getMaskedCardNumber() String
        +processPayment(double amount) boolean
        +getPaymentMethodName() String
        +isValid() boolean
        +getTransactionDetails() String
    }

    class TransferPayment {
        -String sourceAccount
        -String destinationAccount
        -String bankName
        -String referenceNumber
        -double amountPaid
        -LocalDateTime transactionTime
        -boolean processed
        +setSourceAccount(String account)
        +setBankName(String bank)
        +getReferenceNumber() String
        +getStoreAccountInfo()$ String
        +processPayment(double amount) boolean
        +getPaymentMethodName() String
        +isValid() boolean
        +getTransactionDetails() String
    }

    User "1" --> "1" Role
    Cart "1" --> "1" User
    Cart "1" --> "*" CartItem
    CartItem "*" --> "1" Product
    Order "1" --> "1" User
    Order "1" --> "*" CartItem
    Order "1" --> "1" Status

    PaymentMethod <|.. CashPayment
    PaymentMethod <|.. CardPayment
    PaymentMethod <|.. TransferPayment
```

## Class Descriptions

### Models Package

#### User
Represents a system user with authentication and role information.
- Supports two roles: ADMIN and CLIENT
- Provides password validation

#### Product
Represents a product in the inventory.
- Includes stock management methods
- Categorized for filtering

#### CartItem
Represents a product in the shopping cart with quantity.
- Calculates subtotal automatically

#### Cart
Shopping cart for a user session.
- Manages cart items
- Calculates total

#### Order
Represents a completed order.
- Tracks order status
- Generates receipt

### Payments Package

#### PaymentMethod (Interface)
Contract for all payment implementations.
- Strategy pattern for payment processing

#### CashPayment
Handles cash transactions.
- Calculates change

#### CardPayment
Handles credit/debit card payments.
- Validates card information
- Masks card number for security

#### TransferPayment
Handles bank transfer payments.
- Generates reference numbers
