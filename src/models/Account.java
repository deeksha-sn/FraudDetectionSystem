package models;

import java.util.ArrayList;
import java.util.List;
import java.text.NumberFormat;
import java.util.Locale;

public class Account {
    private String accountId;
    private String customerName;
    private String email;
    private String mobileNumber;
    private double accountBalance;
    private String homeLocation;
    private String bankBranch;
    private List<Transaction> transactionHistory;
    private double averageTransactionAmount;

    public Account(String accountId, String customerName, String email,
            String mobileNumber, double accountBalance, String homeLocation, String bankBranch) {
        this.accountId = accountId;
        this.customerName = customerName;
        this.email = email;
        this.mobileNumber = mobileNumber;
        this.accountBalance = accountBalance;
        this.homeLocation = homeLocation;
        this.bankBranch = bankBranch;
        this.transactionHistory = new ArrayList<>();
        this.averageTransactionAmount = 0.0;
    }

    public void addTransaction(Transaction transaction) {
        transactionHistory.add(transaction);
        updateAverageTransaction();
    }

    private void updateAverageTransaction() {
        if (transactionHistory.isEmpty()) {
            averageTransactionAmount = 0.0;
            return;
        }
        double total = 0.0;
        for (Transaction t : transactionHistory) {
            total += t.getAmount();
        }
        averageTransactionAmount = total / transactionHistory.size();
    }

    public String getAccountId() {
        return accountId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getEmail() {
        return email;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public double getAccountBalance() {
        return accountBalance;
    }

    public String getHomeLocation() {
        return homeLocation;
    }

    public String getBankBranch() {
        return bankBranch;
    }

    public List<Transaction> getTransactionHistory() {
        return new ArrayList<>(transactionHistory);
    }

    public double getAverageTransactionAmount() {
        return averageTransactionAmount;
    }

    public void setAccountBalance(double accountBalance) {
        this.accountBalance = accountBalance;
    }

    private String formatIndianCurrency(double amount) {
        NumberFormat formatter = NumberFormat.getCurrencyInstance(new Locale("en", "IN"));
        return formatter.format(amount);
    }

    @Override
    public String toString() {
        return String.format(
                "Account[ID=%s, Customer=%s, Mobile=%s, Balance=%s, Location=%s, Branch=%s, Transactions=%d, Avg=%s]",
                accountId, customerName, mobileNumber, formatIndianCurrency(accountBalance),
                homeLocation, bankBranch, transactionHistory.size(),
                formatIndianCurrency(averageTransactionAmount));
    }
}