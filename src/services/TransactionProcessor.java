package services;

import models.Transaction;
import models.Account;
import models.Alert;
import detectors.FraudDetector;
import java.util.ArrayList;
import java.util.List;

public class TransactionProcessor {
    private List<FraudDetector> detectors;
    private AlertService alertService;
    private int alertCounter;

    public TransactionProcessor() {
        this.detectors = new ArrayList<>();
        this.alertService = new AlertService();
        this.alertCounter = 1;
    }

    public void addDetector(FraudDetector detector) {
        detectors.add(detector);
    }

    public void processTransaction(Transaction transaction, Account account) {
        System.out.println("\n🔍 Processing: " + transaction);

        boolean fraudDetected = false;

        for (FraudDetector detector : detectors) {
            if (detector.detectFraud(transaction, account)) {
                fraudDetected = true;

                transaction.setFlagged(true);

                String alertId = "ALERT" + String.format("%04d", alertCounter++);
                Alert alert = new Alert(
                        alertId,
                        transaction,
                        detector.getDetectorName(),
                        detector.getFraudReason(),
                        "HIGH");

                alertService.addAlert(alert);
                System.out.println("⚠️  FRAUD DETECTED by " + detector.getDetectorName());
                System.out.println("    Reason: " + detector.getFraudReason());
            }
        }

        if (!fraudDetected) {
            System.out.println("✅ Transaction approved - No fraud detected");
        }

        account.addTransaction(transaction);
    }

    public AlertService getAlertService() {
        return alertService;
    }
}