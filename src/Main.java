import models.Account;
import models.Transaction;
import services.TransactionProcessor;
import detectors.AmountBasedDetector;
import detectors.LocationDetector;
import detectors.FrequencyDetector;

public class Main {
    public static void main(String[] args) {
        System.out.println("╔═══════════════════════════════════════════════════════════╗");
        System.out.println("║     🏦 FRAUD DETECTION SYSTEM - INDIAN BANKING 🇮🇳        ║");
        System.out.println("╚═══════════════════════════════════════════════════════════╝\n");

        // Create an Indian bank account
        Account account = new Account(
                "ACC789456",
                "Suresh Kumar",
                "suresh.kumar@email.com",
                "+91-9876543210",
                250000.00,
                "Bangalore",
                "SBI - Koramangala Branch");
        System.out.println("📋 Account Created:");
        System.out.println(account);
        System.out.println("\n" + "=".repeat(65));

        // Create transaction processor
        TransactionProcessor processor = new TransactionProcessor();

        // Add fraud detectors
        processor.addDetector(new AmountBasedDetector());
        processor.addDetector(new LocationDetector());
        processor.addDetector(new FrequencyDetector());

        System.out.println("🔧 Fraud Detection Rules Activated:");
        System.out.println("   ✓ Amount-Based Detection");
        System.out.println("   ✓ Location-Based Detection");
        System.out.println("   ✓ Frequency-Based Detection");
        System.out.println("=".repeat(65));

        // Process normal transactions
        System.out.println("\n💳 PROCESSING TRANSACTIONS...\n");

        Transaction t1 = new Transaction("TXN001", "ACC789456", 5000.00, "Mumbai", "Reliance Digital", "Electronics");
        processor.processTransaction(t1, account);

        Transaction t2 = new Transaction("TXN002", "ACC789456", 2500.00, "Mumbai", "Big Bazaar", "Groceries");
        processor.processTransaction(t2, account);

        Transaction t3 = new Transaction("TXN003", "ACC789456", 3000.00, "Mumbai", "Cafe Coffee Day", "Food");
        processor.processTransaction(t3, account);

        // Suspicious transaction - High amount
        Transaction t4 = new Transaction("TXN004", "ACC789456", 150000.00, "Mumbai", "Apple Store", "Electronics");
        processor.processTransaction(t4, account);

        // Suspicious transaction - Different location
        Transaction t5 = new Transaction("TXN005", "ACC789456", 8000.00, "Delhi", "Flipkart", "Online Shopping");
        processor.processTransaction(t5, account);

        // Suspicious transaction - Too many transactions (frequency)
        Transaction t6 = new Transaction("TXN006", "ACC789456", 4000.00, "Mumbai", "DMart", "Groceries");
        processor.processTransaction(t6, account);

        Transaction t7 = new Transaction("TXN007", "ACC789456", 3500.00, "Mumbai", "Shoppers Stop", "Clothing");
        processor.processTransaction(t7, account);

        Transaction t8 = new Transaction("TXN008", "ACC789456", 2000.00, "Mumbai", "Pantaloons", "Clothing");
        processor.processTransaction(t8, account);

        // Display final summary
        System.out.println("\n" + "=".repeat(65));
        System.out.println("📊 FINAL ACCOUNT SUMMARY");
        System.out.println("=".repeat(65));
        System.out.println(account);

        // Display all fraud alerts
        processor.getAlertService().displayAllAlerts();

        System.out.println("\n" + "=".repeat(65));
        System.out.println("✅ Fraud Detection System - Process Complete");
        System.out.println("=".repeat(65));
    }
}