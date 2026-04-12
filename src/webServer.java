import com.sun.net.httpserver.*;
import java.io.*;
import java.net.*;
import java.util.List;
import models.*;
import services.*;
import detectors.*;

public class webServer {

    private static StringBuilder outputLog = new StringBuilder();

    public static void main(String[] args) throws Exception {

        HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);

        server.createContext("/", new PageHandler());
        server.createContext("/run", new RunHandler());

        server.setExecutor(null);
        server.start();

        System.out.println("Server running at http://localhost:8080");
    }

    // 🌐 Main Page
    static class PageHandler implements HttpHandler {
        public void handle(HttpExchange exchange) throws IOException {

            String html = """
                    <!DOCTYPE html>
                    <html>
                    <head>
                    <meta charset='UTF-8'>
                    <title>Fraud Detection System</title>

                    <style>
                        body {
                            font-family: Arial;
                            background: linear-gradient(to right, #cd5ac0ff, #ff6ffaff);
                            margin: 0;
                            padding: 20px;
                        }

                        .container {
                            max-width: 1000px;
                            margin: auto;
                        }

                        .card {
                            background: white;
                            padding: 20px;
                            border-radius: 12px;
                            margin-bottom: 20px;
                            box-shadow: 0 4px 10px rgba(0,0,0,0.2);
                        }

                        h1 {
                            text-align: center;
                        }

                        button {
                            width: 100%;
                            padding: 12px;
                            background: #5acdc5ff;
                            color: black
                            border: none;
                            border-radius: 8px;
                            font-size: 16px;
                            cursor: pointer;
                        }

                        .ok { color: green; }
                        .fraud { color: red; font-weight: bold; }

                        .alert-card {
                            border-left: 5px solid red;
                            padding: 15px;
                            background: #f9ebdaff;
                            margin: 10px 0;
                            border-radius: 10px;
                        }
                    </style>
                    </head>

                    <body>
                    <div class='container'>

                        <div class='card'>
                            <h1>Fraud Detection System (ವಂಚನೆ ಪತ್ತೆ ವ್ಯವಸ್ಥೆ)</h1>
                            <p style='text-align:center;'>Karnataka Banking (ಕರ್ನಾಟಕ ಬ್ಯಾಂಕಿಂಗ್)</p>
                        </div>

                        <div class='card'>
                            <button onclick="run()">🔍 Process Transactions</button>
                        </div>

                        <div class='card' id='output'>
                            Click button to run...
                        </div>

                    </div>

                    <script>
                    function run() {
                        document.getElementById("output").innerHTML = "Processing...";
                        fetch("/run")
                        .then(res => res.text())
                        .then(data => {
                            document.getElementById("output").innerHTML = data;
                        });
                    }
                    </script>

                    </body>
                    </html>
                    """;

            byte[] bytes = html.getBytes("UTF-8");
            exchange.getResponseHeaders().set("Content-Type", "text/html");
            exchange.sendResponseHeaders(200, bytes.length);

            OutputStream os = exchange.getResponseBody();
            os.write(bytes);
            os.close();
        }
    }

    // ⚙️ Run Processing
    static class RunHandler implements HttpHandler {
        public void handle(HttpExchange exchange) throws IOException {

            outputLog = new StringBuilder();

            runFraudDetection();

            byte[] bytes = outputLog.toString().getBytes("UTF-8");

            exchange.getResponseHeaders().set("Content-Type", "text/html");
            exchange.sendResponseHeaders(200, bytes.length);

            OutputStream os = exchange.getResponseBody();
            os.write(bytes);
            os.close();
        }
    }

    // 🔥 Main Logic
    static void runFraudDetection() {

        Account account = new Account(
                "ACC789456",
                "Suresh Kumar",
                "suresh.kumar@email.com",
                "+91-9876543210",
                250000.00,
                "Bangalore",
                "SBI - Koramangala Branch");

        TransactionProcessor processor = new TransactionProcessor();
        processor.addDetector(new AmountBasedDetector());
        processor.addDetector(new LocationDetector());
        processor.addDetector(new FrequencyDetector());

        log("<h2>Account Info</h2>");
        log("<div style='background:white; padding:15px; border-radius:10px;'>");
        log("<b>Customer:</b> " + account.getCustomerName() + "<br>");
        log("<b>Balance:</b> ₹" + account.getAccountBalance() + "<br>");
        log("<b>Location:</b> " + account.getHomeLocation() + "<br>");
        log("<b>Branch:</b> " + account.getBankBranch() + "<br>");
        log("</div>");

        log("<h2>Transactions</h2>");

        log("<div style='display:flex; flex-direction:column; gap:10px;'>");

        List<Transaction> transactions = TransactionData.getSampleTransactions(account.getAccountId());

        for (Transaction t : transactions) {

            int before = processor.getAlertService().getAlertCount();
            processor.processTransaction(t, account);
            int after = processor.getAlertService().getAlertCount();

            boolean fraud = after > before;

            log("<div style='background:white; padding:15px; border-radius:10px; box-shadow:0 2px 8px rgba(0,0,0,0.1);'>");

            log("<b>Transaction ID:</b> " + t.getTransactionId() + "<br>");
            log("<b>Amount:</b> ₹" + t.getAmount() + "<br>");
            log("<b>Location:</b> " + t.getLocation() + "<br>");
            log("<b>Merchant:</b> " + t.getMerchantName() + "<br>");
            log("<b>Time:</b> " + t.getTimestamp() + "<br>");

            if (fraud) {
                log("<span style='color:red; font-weight:bold;'>🚨 FRAUD DETECTED</span>");
            } else {
                log("<span style='color:green; font-weight:bold;'>✅ Approved</span>");
            }

            log("</div>");
        }

        log("</div>");
        log("<h2>Fraud Alerts</h2>");

        if (processor.getAlertService().getAlertCount() == 0) {
            log("<p class='ok'>✅ No fraud alerts</p>");
        } else {
            for (models.Alert alert : processor.getAlertService().getAllAlerts()) {

                log("<div class='alert-card'>");

                log("<h3>🚨 Fraud Alert (ವಂಚನೆ ಎಚ್ಚರಿಕೆ)</h3>");
                log("<p><b>Alert ID:</b> " + alert.getAlertId() + "</p>");
                log("<p><b>Type:</b> " + alert.getAlertType() + "</p>");
                log("<p><b>Severity:</b> " + alert.getSeverity() + "</p>");
                log("<p><b>Time:</b> " +
                        alert.getAlertTime().format(
                                java.time.format.DateTimeFormatter.ofPattern("dd-MM-yyyy hh:mm:ss a"))
                        + "</p>");
                log("<p><b>Reason:</b> " + alert.getReason() + "</p>");
                log("<p><b>Transaction:</b> " + alert.getTransaction().getTransactionId() + "</p>");

                log("</div>");
            }
        }
    }

    static void log(String message) {
        outputLog.append(message).append("\n");
    }
}