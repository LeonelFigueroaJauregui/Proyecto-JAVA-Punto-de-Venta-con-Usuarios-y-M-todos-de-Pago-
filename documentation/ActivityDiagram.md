# Activity Diagram

This document describes the main activity flows in the Point of Sale (POS) System.

## 1. Login Activity Flow

```mermaid
flowchart TD
    A[Start] --> B[Display Login Screen]
    B --> C[Enter Username and Password]
    C --> D{Validate Credentials}
    D -->|Valid| E{Check User Role}
    D -->|Invalid| F[Display Error Message]
    F --> C
    E -->|Admin| G[Open Admin Dashboard]
    E -->|Client| H[Open Client Shop View]
    G --> I[End]
    H --> I
```

## 2. Shopping Activity Flow (Client)

```mermaid
flowchart TD
    A[Start Shopping] --> B[View Products]
    B --> C{Select Product?}
    C -->|Yes| D[Select Quantity]
    D --> E[Add to Cart]
    E --> F[Update Cart Display]
    F --> G{Continue Shopping?}
    G -->|Yes| B
    G -->|No| H{Cart Empty?}
    H -->|Yes| I[Show Warning]
    I --> B
    H -->|No| J[Proceed to Checkout]
    J --> K[End]
    C -->|No| L{Checkout?}
    L -->|Yes| H
    L -->|No| M{Logout?}
    M -->|Yes| N[Return to Login]
    M -->|No| B
    N --> K
```

## 3. Checkout and Payment Activity Flow

```mermaid
flowchart TD
    A[Start Checkout] --> B[Display Cart Summary]
    B --> C[Calculate Total with Tax]
    C --> D[Select Payment Method]
    D --> E{Payment Type?}
    
    E -->|Cash| F[Enter Cash Amount]
    F --> G{Sufficient Amount?}
    G -->|No| H[Show Error]
    H --> F
    G -->|Yes| I[Calculate Change]
    I --> J[Process Payment]
    
    E -->|Card| K[Enter Card Details]
    K --> L{Validate Card?}
    L -->|Invalid| M[Show Error]
    M --> K
    L -->|Valid| J
    
    E -->|Transfer| N[Show Store Account]
    N --> O[Enter Bank Details]
    O --> P{Validate Account?}
    P -->|Invalid| Q[Show Error]
    Q --> O
    P -->|Valid| J
    
    J --> R{Payment Successful?}
    R -->|No| S[Show Error Message]
    S --> D
    R -->|Yes| T[Update Product Stock]
    T --> U[Create Order]
    U --> V[Generate Receipt]
    V --> W[Clear Cart]
    W --> X[Display Receipt]
    X --> Y[End]
```

## 4. Product Management Activity Flow (Admin)

```mermaid
flowchart TD
    A[Start Admin Dashboard] --> B[View Products List]
    B --> C{Select Action?}
    
    C -->|Add| D[Enter Product Details]
    D --> E{Validate Input?}
    E -->|Invalid| F[Show Error]
    F --> D
    E -->|Valid| G[Create Product]
    G --> H[Refresh Product List]
    H --> B
    
    C -->|Edit| I[Select Product]
    I --> J[Load Product Details]
    J --> K[Modify Details]
    K --> L{Validate Input?}
    L -->|Invalid| M[Show Error]
    M --> K
    L -->|Valid| N[Update Product]
    N --> H
    
    C -->|Delete| O[Select Product]
    O --> P[Confirm Deletion]
    P --> Q{Confirmed?}
    Q -->|No| B
    Q -->|Yes| R[Delete Product]
    R --> H
    
    C -->|Reports| S[View Sales Reports]
    S --> B
    
    C -->|Logout| T[Return to Login]
    T --> U[End]
```

## 5. Order Processing Flow

```mermaid
flowchart TD
    A[Order Created] --> B[Status: PENDING]
    B --> C{Payment Received?}
    C -->|Yes| D[Process Payment]
    D --> E{Payment Successful?}
    E -->|Yes| F[Status: PAID]
    E -->|No| G[Status: PENDING]
    G --> C
    C -->|Cancelled| H[Status: CANCELLED]
    H --> I[End - Order Cancelled]
    F --> J[Update Inventory]
    J --> K[Generate Receipt]
    K --> L[Status: COMPLETED]
    L --> M[End - Order Complete]
```

## Activity Descriptions

### Login Flow
1. User enters credentials
2. System validates against stored users
3. Role-based redirection to appropriate view

### Shopping Flow
1. Client browses products
2. Adds items to cart
3. Reviews cart contents
4. Proceeds to checkout

### Payment Flow
1. Select payment method
2. Enter payment details
3. Validate and process payment
4. Generate receipt

### Admin Flow
1. View and manage products
2. CRUD operations on inventory
3. View reports
