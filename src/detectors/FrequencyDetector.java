package detectors;

import models.Transaction;
import models.Account;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

public class FrequencyDetector implements FraudDetector {
    private static final int MAX_TRANSACTIONS_PER_HOUR = 5;
    private String fraudReason;

    @Override
    public boolean detectFraud(Transaction transaction, Account account) {
        List<Transaction> history = account.getTransactionHistory();

        if (history.isEmpty()) {
            return false;
        }

        LocalDateTime oneHourAgo = transaction.getTimestamp().minus(1, ChronoUnit.HOURS);
        long recentTransactions = history.stream()
                .filter(t -> t.getTimestamp().isAfter(oneHourAgo))
                .count() + 1;

        if (recentTransactions > MAX_TRANSACTIONS_PER_HOUR) {
            fraudReason = String.format(
                    "Too many transactions in 1 hour: %d (max allowed: %d)",
                    recentTransactions, MAX_TRANSACTIONS_PER_HOUR);
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
        return "Frequency-Based Detector";
    }
}