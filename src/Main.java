import models.Account;
import models.Transaction;
import services.TransactionProcessor;
import detectors.AmountBasedDetector;
import detectors.LocationDetector;
import detectors.FrequencyDetector;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("╔═══════════════════════════════════════════════════════════╗");
        System.out.println("║     🏦 FRAUD DETECTION SYSTEM - KARNATAKA BANKING 🇮🇳     ║");
        System.out.println("╚═══════════════════════════════════════════════════════════╝\n");

        // Create a Bangalore bank account
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

        // Process transactions from TransactionData file
        System.out.println("\n💳 PROCESSING TRANSACTIONS...\n");

        // Get sample transactions from separate file
        List<Transaction> transactions = TransactionData.getSampleTransactions(account.getAccountId());

        // Process each transaction
        for (Transaction transaction : transactions) {
            processor.processTransaction(transaction, account);
        }

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

        // BONUS: You can easily test different scenarios!
        System.out.println("\n\n🎯 Want to test more scenarios?");
        System.out.println("Uncomment the sections below in Main.java:\n");

    }
}