package services;

import models.Alert;
import java.util.ArrayList;
import java.util.List;

public class AlertService {
    private List<Alert> alerts;

    public AlertService() {
        this.alerts = new ArrayList<>();
    }

    public void addAlert(Alert alert) {
        alerts.add(alert);
    }

    public List<Alert> getAllAlerts() {
        return new ArrayList<>(alerts);
    }

    public void displayAllAlerts() {
        if (alerts.isEmpty()) {
            System.out.println("\n✅ No fraud alerts - All transactions are clean!");
            return;
        }

        System.out.println("\n" + "=".repeat(65));
        System.out.println("📢 FRAUD ALERT SUMMARY - Total Alerts: " + alerts.size());
        System.out.println("=".repeat(65));

        for (Alert alert : alerts) {
            System.out.println(alert);
        }
    }

    public int getAlertCount() {
        return alerts.size();
    }
}