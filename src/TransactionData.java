import models.Transaction;
import java.util.ArrayList;
import java.util.List;

public class TransactionData {

    public static List<Transaction> getSampleTransactions(String accountId) {
        List<Transaction> transactions = new ArrayList<>();

        transactions.add(new Transaction(
                "TXN001",
                accountId,
                5000.00,
                "Bangalore",
                "Reliance Digital",
                "Electronics"));

        transactions.add(new Transaction(
                "TXN002",
                accountId,
                2500.00,
                "Bangalore",
                "More Megastore",
                "Groceries"));

        transactions.add(new Transaction(
                "TXN003",
                accountId,
                3000.00,
                "Bangalore",
                "Cafe Coffee Day",
                "Food"));

        // FRAUD: High amount transaction
        transactions.add(new Transaction(
                "TXN004",
                accountId,
                150000.00,
                "Bangalore",
                "Croma Electronics",
                "Electronics"));

        // FRAUD: Different location (Chennai instead of Bangalore)
        transactions.add(new Transaction(
                "TXN005",
                accountId,
                8000.00,
                "Chennai",
                "Saravana Stores",
                "Shopping"));

        // More transactions to test frequency detector
        transactions.add(new Transaction(
                "TXN006",
                accountId,
                4000.00,
                "Bangalore",
                "Big Bazaar",
                "Groceries"));

        transactions.add(new Transaction(
                "TXN007",
                accountId,
                3500.00,
                "Bangalore",
                "Lifestyle",
                "Clothing"));

        transactions.add(new Transaction(
                "TXN008",
                accountId,
                2000.00,
                "Bangalore",
                "Pantaloons",
                "Clothing"));

        return transactions;
    }

    public static List<Transaction> getHighRiskTransactions(String accountId) {
        List<Transaction> transactions = new ArrayList<>();

        // All high-risk transactions
        transactions.add(new Transaction(
                "TXN101",
                accountId,
                200000.00,
                "Bangalore",
                "Luxury Store",
                "Jewelry"));

        transactions.add(new Transaction(
                "TXN102",
                accountId,
                50000.00,
                "Chennai",
                "Tamil Store",
                "Electronics"));

        return transactions;
    }

    // Add transactions from different cities
    public static List<Transaction> getMultiCityTransactions(String accountId) {
        List<Transaction> transactions = new ArrayList<>();

        transactions.add(new Transaction(
                "TXN201",
                accountId,
                5000.00,
                "Bangalore",
                "Local Shop",
                "Groceries"));

        transactions.add(new Transaction(
                "TXN202",
                accountId,
                6000.00,
                "Chennai",
                "Chennai Silks",
                "Clothing"));

        transactions.add(new Transaction(
                "TXN203",
                accountId,
                7000.00,
                "Mysore",
                "Mysore Palace Shop",
                "Souvenirs"));

        transactions.add(new Transaction(
                "TXN204",
                accountId,
                4500.00,
                "Hyderabad",
                "Charminar Market",
                "Shopping"));

        return transactions;
    }
}