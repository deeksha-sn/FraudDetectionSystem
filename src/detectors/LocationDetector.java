package detectors;

import models.Transaction;
import models.Account;

public class LocationDetector implements FraudDetector {
    private String fraudReason;

    @Override
    public boolean detectFraud(Transaction transaction, Account account) {
        String transactionLocation = transaction.getLocation();
        String homeLocation = account.getHomeLocation();

        if (!transactionLocation.equalsIgnoreCase(homeLocation)) {
            fraudReason = String.format(
                    "Transaction location (%s) differs from home location (%s)",
                    transactionLocation, homeLocation);
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
        return "Location-Based Detector";
    }
}