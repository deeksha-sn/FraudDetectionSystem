package detectors;

import models.Transaction;
import models.Account;

public interface FraudDetector {
    boolean detectFraud(Transaction transaction, Account account);

    String getFraudReason();

    String getDetectorName();
}