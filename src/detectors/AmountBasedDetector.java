package detectors;

import models.Transaction;
import models.Account;
import java.text.NumberFormat;
import java.util.Locale;

public class AmountBasedDetector implements FraudDetector {
    private static final double THRESHOLD_MULTIPLIER = 3.0;
    private static final double HIGH_AMOUNT_THRESHOLD = 100000.0;
    private String fraudReason;

    @Override
    public boolean detectFraud(Transaction transaction, Account account) {
        double amount = transaction.getAmount();
        double avgAmount = account.getAverageTransactionAmount();

        NumberFormat formatter = NumberFormat.getCurrencyInstance(new Locale("en", "IN"));

        if (avgAmount > 0 && amount > (avgAmount * THRESHOLD_MULTIPLIER)) {
            fraudReason = String.format(
                    "Transaction amount (%s) is %.1fx higher than average (%s)",
                    formatter.format(amount), amount / avgAmount, formatter.format(avgAmount));
            return true;
        }

        if (amount > HIGH_AMOUNT_THRESHOLD) {
            fraudReason = String.format(
                    "Transaction amount (%s) exceeds high threshold (%s)",
                    formatter.format(amount), formatter.format(HIGH_AMOUNT_THRESHOLD));
            return true;
        }

        return false;
    }

    @Override
    public String getFraudReason() {
        return fraudReason;
    }

    @Override
    public String getDetectorName() {
        return "Amount-Based Detector";
    }
}