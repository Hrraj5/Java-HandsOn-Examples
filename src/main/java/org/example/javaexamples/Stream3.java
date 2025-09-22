package org.example.javaexamples;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

class Transaction {
    String id, currency, type; // type: DEBIT/CREDIT
    BigDecimal amount;
    LocalDateTime timestamp;

    public void setId(String id) {
        this.id = id;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public Transaction(String id, String currency, String type, BigDecimal amount, LocalDateTime timestamp) {
        this.id = id;
        this.currency = currency;
        this.type = type;
        this.amount = amount;
        this.timestamp = timestamp;
    }


    public String getId() { return id; }
    public String getCurrency() { return currency; }
    public String getType() { return type; }
    public BigDecimal getAmount() { return amount; }
    public LocalDateTime getTimestamp() { return timestamp; }

    @Override
    public String toString() {
        return String.format("%s: %s %s %s (%s)", id, type, amount, currency, timestamp);
    }
}
public class Stream3 {

    public static void main(String[] args) {
        List<Transaction> transactions = createTransactions();
        //transactions.forEach(System.out::println);

        Map<String,Double> txNetBalance  = transactions.stream().collect(Collectors.groupingBy(Transaction::getCurrency,Collectors.summingDouble(tx-> {
            double balance = 0;
            if(tx.getType().equals("DEBIT")){
                balance+= -Double.parseDouble(tx.getAmount().toString());
            }else{
                balance+= Double.parseDouble(tx.getAmount().toString());
            }
            return balance;
        })));
        System.out.println(txNetBalance);

        List<Transaction> txList = transactions.stream().filter(tx-> new BigDecimal("10000").compareTo(tx.getAmount()) < 0).toList();
        System.out.println(txList);


        Map<String,Long> txs = transactions.stream().collect(Collectors.groupingBy(Transaction::getCurrency,Collectors.counting())).
        entrySet().stream()
                        .sorted(Map.Entry.<String,Long>comparingByValue().reversed())
                                .collect(Collectors.toMap(Map.Entry::getKey,Map.Entry::getValue,(t1,t2)->t1, LinkedHashMap::new));
        System.out.println(txs);
    }

    public static List<Transaction> createTransactions() {
        return Arrays.asList(
                new Transaction("TXN001", "USD", "DEBIT", new BigDecimal("150.50"), LocalDateTime.of(2024, 9, 19, 9, 30)),
                new Transaction("TXN002", "EUR", "CREDIT", new BigDecimal("200.00"), LocalDateTime.of(2024, 9, 19, 10, 15)),
                new Transaction("TXN003", "USD", "DEBIT", new BigDecimal("75.25"), LocalDateTime.of(2024, 9, 19, 11, 45)),
                new Transaction("TXN004", "GBP", "CREDIT", new BigDecimal("300.75"), LocalDateTime.of(2024, 9, 19, 12, 20)),
                new Transaction("TXN005", "USD", "CREDIT", new BigDecimal("120.00"), LocalDateTime.of(2024, 9, 19, 13, 10)),
                new Transaction("TXN006", "EUR", "DEBIT", new BigDecimal("50.50"), LocalDateTime.of(2024, 9, 19, 14, 30)),
                new Transaction("TXN007", "GBP", "DEBIT", new BigDecimal("175.25"), LocalDateTime.of(2024, 9, 19, 15, 45)),
                new Transaction("TXN008", "USD", "DEBIT", new BigDecimal("90.75"), LocalDateTime.of(2024, 9, 19, 16, 20)),
                new Transaction("TXN009", "JPY", "CREDIT", new BigDecimal("15000.00"), LocalDateTime.of(2024, 9, 19, 17, 15)),
                new Transaction("TXN010", "EUR", "CREDIT", new BigDecimal("125.75"), LocalDateTime.of(2024, 9, 19, 18, 30)),
                new Transaction("TXN011", "USD", "DEBIT", new BigDecimal("220.00"), LocalDateTime.of(2024, 9, 19, 19, 10)),
                new Transaction("TXN012", "GBP", "CREDIT", new BigDecimal("85.50"), LocalDateTime.of(2024, 9, 19, 20, 25)),
                new Transaction("TXN013", "EUR", "DEBIT", new BigDecimal("110.25"), LocalDateTime.of(2024, 9, 19, 21, 40)),
                new Transaction("TXN014", "USD", "CREDIT", new BigDecimal("95.00"), LocalDateTime.of(2024, 9, 19, 22, 15)),
                new Transaction("TXN015", "JPY", "DEBIT", new BigDecimal("8500.00"), LocalDateTime.of(2024, 9, 19, 23, 00))
        );
    }
}
