# Component Diagram

This document describes the component architecture of the Point of Sale (POS) System.

## System Component Diagram

```mermaid
graph TB
    subgraph "Presentation Layer"
        LoginView[Login View]
        AdminView[Admin View]
        ClientView[Client View]
        CartView[Cart View]
        PaymentView[Payment View]
    end

    subgraph "Business Logic Layer"
        Main[Main Controller]
        AuthService[Authentication Service]
        CartManager[Cart Manager]
        OrderProcessor[Order Processor]
        PaymentProcessor[Payment Processor]
    end

    subgraph "Model Layer"
        UserModel[User Model]
        ProductModel[Product Model]
        CartModel[Cart Model]
        OrderModel[Order Model]
    end

    subgraph "Payment Layer"
        PaymentInterface[PaymentMethod Interface]
        CashPayment[Cash Payment]
        CardPayment[Card Payment]
        TransferPayment[Transfer Payment]
    end

    subgraph "Data Layer"
        UserData[(Users Data)]
        ProductData[(Products Data)]
        OrderData[(Orders Data)]
    end

    LoginView --> Main
    AdminView --> Main
    ClientView --> Main
    CartView --> CartManager
    PaymentView --> PaymentProcessor

    Main --> AuthService
    Main --> CartManager
    Main --> OrderProcessor

    AuthService --> UserModel
    CartManager --> CartModel
    CartManager --> ProductModel
    OrderProcessor --> OrderModel
    OrderProcessor --> PaymentProcessor

    PaymentProcessor --> PaymentInterface
    PaymentInterface --> CashPayment
    PaymentInterface --> CardPayment
    PaymentInterface --> TransferPayment

    UserModel --> UserData
    ProductModel --> ProductData
    OrderModel --> OrderData
```

## Package Structure Diagram

```mermaid
graph LR
    subgraph "project/"
        Main[Main.java]
        
        subgraph "Models/"
            User[User.java]
            Product[Product.java]
            Cart[Cart.java]
            CartItem[CartItem.java]
            Order[Order.java]
        end
        
        subgraph "Views/"
            LoginView[LoginView.java]
            AdminView[AdminView.java]
            ClientView[ClientView.java]
            CartView[CartView.java]
            PaymentView[PaymentView.java]
        end
        
        subgraph "Payments/"
            PaymentMethod[PaymentMethod.java]
            CashPayment[CashPayment.java]
            CardPayment[CardPayment.java]
            TransferPayment[TransferPayment.java]
        end
        
        subgraph "Images/"
            Assets[Image Assets]
        end
    end

    Main --> Models/
    Main --> Views/
    Views/ --> Payments/
```

## Component Dependencies

```mermaid
graph TD
    subgraph "Views Package"
        LV[LoginView]
        AV[AdminView]
        CV[ClientView]
        CaV[CartView]
        PV[PaymentView]
    end

    subgraph "Models Package"
        U[User]
        P[Product]
        C[Cart]
        CI[CartItem]
        O[Order]
    end

    subgraph "Payments Package"
        PM[PaymentMethod]
        CP[CashPayment]
        CaP[CardPayment]
        TP[TransferPayment]
    end

    LV --> U
    AV --> U
    AV --> P
    CV --> U
    CV --> P
    CV --> CI
    CaV --> C
    CaV --> CI
    PV --> PM
    PV --> CP
    PV --> CaP
    PV --> TP

    C --> CI
    C --> U
    CI --> P
    O --> U
    O --> CI

    CP -.->|implements| PM
    CaP -.->|implements| PM
    TP -.->|implements| PM
```

## Component Descriptions

### Presentation Layer
| Component | Description |
|-----------|-------------|
| LoginView | User authentication interface |
| AdminView | Administrator dashboard with product management |
| ClientView | Shopping interface for customers |
| CartView | Shopping cart management dialog |
| PaymentView | Payment processing dialog |

### Business Logic Layer
| Component | Description |
|-----------|-------------|
| Main | Application entry point and controller |
| AuthService | User authentication logic |
| CartManager | Shopping cart operations |
| OrderProcessor | Order creation and management |
| PaymentProcessor | Payment method handling |

### Model Layer
| Component | Description |
|-----------|-------------|
| User | User data model with roles |
| Product | Product inventory model |
| Cart | Shopping cart model |
| CartItem | Individual cart item |
| Order | Order transaction model |

### Payment Layer
| Component | Description |
|-----------|-------------|
| PaymentMethod | Interface for payment strategies |
| CashPayment | Cash transaction handling |
| CardPayment | Card transaction handling |
| TransferPayment | Bank transfer handling |

## Design Patterns Used

1. **MVC (Model-View-Controller)**
   - Models: Data representation
   - Views: User interface
   - Controller: Main.java handles logic

2. **Strategy Pattern**
   - PaymentMethod interface
   - Multiple payment implementations

3. **Observer Pattern (implicit)**
   - Swing event listeners
   - UI updates on data changes

## Technology Stack

- **Language**: Java 8+
- **UI Framework**: Java Swing
- **Architecture**: MVC Pattern
- **Design Patterns**: Strategy, Observer
