# 🛒 Point of Sale (POS) System

<p align="center">
  <img src="https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white" alt="Java"/>
  <img src="https://img.shields.io/badge/Swing-GUI-blue?style=for-the-badge" alt="Swing"/>
  <img src="https://img.shields.io/badge/License-MIT-green?style=for-the-badge" alt="MIT License"/>
</p>

A comprehensive **Point of Sale System** developed in Java with a graphical user interface (Swing). Features role-based login (Administrator and Client), inventory management, shopping cart functionality, and multiple payment methods. Perfect for learning object-oriented programming principles.

---

## 📋 Table of Contents

- [Features](#-features)
- [User Roles](#-user-roles)
- [Payment Methods](#-payment-methods)
- [Project Structure](#-project-structure)
- [Requirements](#-requirements)
- [How to Run](#-how-to-run)
- [Default Credentials](#-default-credentials)
- [Screenshots](#-screenshots)
- [Documentation](#-documentation)
- [Design Patterns](#-design-patterns)
- [Contributing](#-contributing)
- [License](#-license)

---

## ✨ Features

### Core Features
- 🔐 **User Authentication** - Secure login with role-based access control
- 📦 **Product Management** - Add, edit, delete, and view products (Admin)
- 🛒 **Shopping Cart** - Add products, modify quantities, remove items
- 💳 **Multiple Payment Methods** - Cash, Credit/Debit Card, Bank Transfer
- 🧾 **Receipt Generation** - Automatic receipt with transaction details
- 📊 **Reports** - Sales analytics and reports (Admin)

### Technical Features
- Clean MVC architecture
- Strategy pattern for payment processing
- Responsive Swing GUI
- Input validation
- Stock management

---

## 👥 User Roles

The system supports two user roles with different permissions:

### 🔴 Administrator (Admin)
| Permission | Description |
|------------|-------------|
| View Products | Access complete product inventory |
| Add Products | Create new products in the system |
| Edit Products | Modify product details |
| Delete Products | Remove products from inventory |
| View Reports | Access sales reports and analytics |
| Logout | End session |

### 🟢 Client
| Permission | Description |
|------------|-------------|
| View Products | Browse available products |
| Add to Cart | Select products for purchase |
| Manage Cart | Update quantities, remove items |
| Checkout | Proceed to payment |
| Make Payment | Pay using available methods |
| Logout | End session |

---

## 💰 Payment Methods

The system supports three payment methods:

### 💵 Cash Payment
- Enter amount received
- Automatic change calculation
- Transaction confirmation

### 💳 Credit/Debit Card
- Card number input
- Cardholder name
- Expiry date (MM/YY)
- CVV validation
- Masked card number display

### 🏦 Bank Transfer
- Store account display
- Customer account input
- Bank name verification
- Reference number generation

---

## 📁 Project Structure

```
Proyecto-JAVA-Punto-de-Venta/
├── 📂 project/                    # Source code
│   ├── 📄 Main.java               # Application entry point
│   ├── 📂 Models/                 # Data models
│   │   ├── Product.java           # Product entity
│   │   ├── User.java              # User entity with roles
│   │   ├── Cart.java              # Shopping cart
│   │   ├── CartItem.java          # Cart item
│   │   └── Order.java             # Order entity
│   ├── 📂 Views/                  # GUI components
│   │   ├── LoginView.java         # Login screen
│   │   ├── AdminView.java         # Admin dashboard
│   │   ├── ClientView.java        # Client shop view
│   │   ├── CartView.java          # Cart dialog
│   │   └── PaymentView.java       # Payment dialog
│   ├── 📂 Payments/               # Payment implementations
│   │   ├── PaymentMethod.java     # Payment interface
│   │   ├── CashPayment.java       # Cash implementation
│   │   ├── CardPayment.java       # Card implementation
│   │   └── TransferPayment.java   # Transfer implementation
│   └── 📂 Images/                 # UI images and icons
│       └── README.md              # Image assets guide
├── 📂 documentation/              # UML diagrams
│   ├── UseCaseDiagram.md          # Use case diagram
│   ├── ClassDiagram.md            # Class diagram
│   ├── ActivityDiagram.md         # Activity diagrams
│   └── ComponentDiagram.md        # Component diagram
├── 📄 README.md                   # This file
└── 📄 LICENSE                     # MIT License
```

---

## 📋 Requirements

- **Java Development Kit (JDK)** 8 or higher
- **Java Runtime Environment (JRE)** 8 or higher
- Any operating system (Windows, macOS, Linux)

---

## 🚀 How to Run

### Option 1: Using Command Line

1. **Clone the repository**
   ```bash
   git clone https://github.com/LeonelFigueroaJauregui/Proyecto-JAVA-Punto-de-Venta-con-Usuarios-y-M-todos-de-Pago-.git
   cd Proyecto-JAVA-Punto-de-Venta-con-Usuarios-y-M-todos-de-Pago-/project
   ```

2. **Compile the source files**
   ```bash
   javac -d out Main.java Models/*.java Views/*.java Payments/*.java
   ```

3. **Run the application**
   ```bash
   java -cp out Main
   ```

### Option 2: Using an IDE

1. Open the project in your favorite IDE (IntelliJ IDEA, Eclipse, NetBeans)
2. Set `project/` as the source folder
3. Run `Main.java`

### Option 3: Quick Start (One Command)

```bash
cd project && javac Main.java Models/*.java Views/*.java Payments/*.java && java Main
```

---

## 🔑 Default Credentials

The system comes with pre-configured sample users:

| Role | Username | Password | Full Name |
|------|----------|----------|-----------|
| Admin | `admin` | `admin123` | System Administrator |
| Client | `client` | `client123` | John Doe |
| Client | `maria` | `maria123` | Maria Garcia |

---

## 📸 Screenshots

### Login Screen
The login interface allows users to authenticate with their credentials.

### Admin Dashboard
Administrators can manage products and view reports.

### Client Shop View
Clients can browse products, add to cart, and checkout.

### Payment View
Multiple payment options with validation.

---

## 📖 Documentation

Detailed UML diagrams are available in the `documentation/` folder:

| Diagram | Description | File |
|---------|-------------|------|
| Use Case | Actor interactions and system functions | [UseCaseDiagram.md](documentation/UseCaseDiagram.md) |
| Class | Class structure and relationships | [ClassDiagram.md](documentation/ClassDiagram.md) |
| Activity | Workflow and process flows | [ActivityDiagram.md](documentation/ActivityDiagram.md) |
| Component | System architecture | [ComponentDiagram.md](documentation/ComponentDiagram.md) |

All diagrams are written in Mermaid format and can be rendered directly on GitHub.

---

## 🎨 Design Patterns

This project implements several design patterns:

### 1. MVC (Model-View-Controller)
- **Models**: `User`, `Product`, `Cart`, `Order`
- **Views**: `LoginView`, `AdminView`, `ClientView`, `PaymentView`
- **Controller**: `Main.java`

### 2. Strategy Pattern
- **Interface**: `PaymentMethod`
- **Implementations**: `CashPayment`, `CardPayment`, `TransferPayment`

### 3. Observer Pattern (implicit)
- Swing event listeners for UI updates

---

## 🤝 Contributing

Contributions are welcome! Please feel free to submit a Pull Request.

1. Fork the repository
2. Create your feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

---

## 📄 License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

---

## 👨‍💻 Author

**Leonel Figueroa Jauregui**

---

<p align="center">
  Made with ❤️ in Java
</p>
