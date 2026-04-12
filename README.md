# 🏦 Fraud Detection System (ವಂಚನೆ ಪತ್ತೆ ವ್ಯವಸ್ಥೆ)

A web-based Fraud Detection System for Karnataka Banking built using Java.  
This system analyzes transactions and detects fraudulent activities using multiple detection rules.

---

## ✨ Features

### 🔍 Multi-Algorithm Fraud Detection

- 💰 Amount-based detection (high transaction amounts)
- 📍 Location-based detection (unusual locations)
- ⏱️ Frequency-based detection (too many transactions)

### 🌐 Web Interface

- Clean and responsive UI  
- Real-time transaction processing  
- Color-coded results  
  - ✅ Green → Approved  
  - 🚨 Red → Fraud  

### 🌏 Bilingual Support

- English + Kannada (ವಂಚನೆ ಎಚ್ಚರಿಕೆ)  
- Indian currency format (₹)  
- Indian date format (dd-MM-yyyy)  

### 🏦 Karnataka Banking Context

- Cities: Bangalore, Chennai, Mysore  
- Realistic merchant names  

---

## 🧠 How It Works

The system analyzes each transaction using three detection techniques:

### Amount-Based Detection
- Flags transactions above ₹1,00,000  
- Flags transactions significantly higher than average  

### Location-Based Detection
- Compares transaction location with home location  
- Flags transactions from different cities  

### Frequency-Based Detection
- Monitors number of transactions per hour  
- Flags more than 5 transactions in short time  

---

## 📁 Project Structure

```
FraudDetectionSystem/
├── src/
│   ├── models/
│   │   ├── Transaction.java
│   │   ├── Account.java
│   │   └── Alert.java
│   ├── detectors/
│   │   ├── FraudDetector.java
│   │   ├── AmountBasedDetector.java
│   │   ├── LocationDetector.java
│   │   └── FrequencyDetector.java
│   ├── services/
│   │   ├── TransactionProcessor.java
│   │   └── AlertService.java
│   ├── Main.java
│   ├── TransactionData.java
│   └── WebServer.java
├── README.md
└── LICENSE
```

---

## 🔧 Prerequisites

- Java Development Kit (JDK 11 or higher)
- Git
- Web browser

### Check Java Installation

```bash
java --version
javac --version
```

---

## 🚀 Installation & Setup

### 1. Clone the Repository

```bash
git clone https://github.com/deeksha-sn/FraudDetectionSystem.git
cd FraudDetectionSystem
```

### 2. Navigate to Source Directory

```bash
cd src
```

### 3. Compile the Project

```bash
javac WebServer.java models/*.java detectors/*.java services/*.java TransactionData.java
```

### 4. Run the Web Server

```bash
java WebServer
```

### 5. Open in Browser

```
http://localhost:8080
```

---

## 💻 Usage

### 🌐 Web Interface

1. Open browser → `http://localhost:8080`
2. Click "🔍 Process Transactions"
3. View:
   - Account details
   - Transactions
   - Fraud alerts

### 💻 Console Version (Optional)

```bash
javac Main.java models/*.java detectors/*.java services/*.java TransactionData.java
java Main
```

---

## 🛠️ Technologies Used

- Java (Core Java)
- Java HTTP Server (`com.sun.net.httpserver`)
- HTML + CSS (embedded in Java)
- Object-Oriented Programming (OOP)

---

## 🎓 OOP Concepts Used

### 🔒 Encapsulation
- Private variables in classes
- Getter/setter methods

### 🧩 Inheritance
- Detector classes implement common interface

### 🔄 Polymorphism
- Multiple detectors used through one interface

### 🎭 Abstraction
- FraudDetector interface hides implementation details

---

## 🔮 Future Enhancements

- 📊 Add charts (fraud vs normal)
- 🗄️ Add database (MySQL/PostgreSQL)
- 🔐 Add login system
- 📁 Upload CSV transactions
- 🤖 Add machine learning model
- 📱 Mobile app version

---

## 👩‍💻 Author

**Deeksha SN**  
CSE[AIML] Engineering Student

---

## 📝 License

This project is open source and available under the MIT License