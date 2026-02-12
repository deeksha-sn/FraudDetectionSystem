package models;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Alert {
    private String alertId;
    private Transaction transaction;
    private String alertType;
    private String reason;
    private LocalDateTime alertTime;
    private String severity;

    public Alert(String alertId, Transaction transaction, String alertType,
            String reason, String severity) {
        this.alertId = alertId;
        this.transaction = transaction;
        this.alertType = alertType;
        this.reason = reason;
        this.severity = severity;
        this.alertTime = LocalDateTime.now();
    }

    public String getAlertId() {
        return alertId;
    }

    public Transaction getTransaction() {
        return transaction;
    }

    public String getAlertType() {
        return alertType;
    }

    public String getReason() {
        return reason;
    }

    public LocalDateTime getAlertTime() {
        return alertTime;
    }

    public String getSeverity() {
        return severity;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy hh:mm:ss a");
        return String.format(
                "\n╔════════════════ धोखाधड़ी अलर्ट / FRAUD ALERT ════════════════╗\n" +
                        "║ Alert ID: %-50s ║\n" +
                        "║ Type: %-54s ║\n" +
                        "║ Severity: %-50s ║\n" +
                        "║ Time: %-54s ║\n" +
                        "║ Reason: %-52s ║\n" +
                        "║ Transaction: %-47s ║\n" +
                        "╚══════════════════════════════════════════════════════════════╝",
                alertId, alertType, severity, alertTime.format(formatter), reason,
                transaction.getTransactionId());
    }
}