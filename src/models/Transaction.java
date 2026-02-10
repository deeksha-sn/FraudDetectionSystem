package models;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.text.NumberFormat;
import java.util.Locale;

import javax.print.DocFlavor.STRING;

public class Transaction {
    private String transactionId;
    private String accountId;
    private double amount;
    private String location;
    private LocalDateTime timestamp;
    private String merchantName;
    private String category;
    private boolean isFlagged;

    public Transaction(String transactionId, String accountId, double amount, String location, String merchantName,
            String category) {
        this.transactionId = transactionId;
        this.accountId = accountId;
        this.amount = amount;
        this.location = location;
        this.merchantName = merchantName;
        this.category = category;
        this.timestamp = LocalDateTime.now();
        this.isFlagged = false;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public String getAccountId() {
        return accountId;
    }

    public double getAmount() {
        return amount;
    }

    public String getLocation() {
        return location;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public String getMerchantName() {
        return merchantName;
    }

    public String getCategory() {
        return category;
    }

    public boolean isFlagged() {
        return isFlagged;
    }

    public void setFlagged(boolean flagged) {
        isFlagged = flagged;
    }

    private String formatIndianCurrency(double amount) {
        NumberFormat formatter = NumberFormat.getCurrencyInstance(new Locale("en", "IN"));
        return formatter.format(amount);

    @Override
    public String toString(){
        DateTimeFormatter formatter=DateTimeFormatter.ofPattern("yy"))
    }
}
