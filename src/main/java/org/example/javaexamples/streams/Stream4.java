package org.example.javaexamples.streams;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Function;
import java.util.stream.Collectors;

class Order {
    String orderId, customerId, status;
    List<OrderItem> items;
    LocalDateTime orderDate;

    public Order(String orderId, String customerId, String status, List<OrderItem> items, LocalDateTime orderDate) {
        this.orderId = orderId;
        this.customerId = customerId;
        this.status = status;
        this.items = items;
        this.orderDate = orderDate;
    }

    public String getOrderId() {
        return orderId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public String getStatus() {
        return status;
    }

    public List<OrderItem> getItems() {
        return items;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    @Override
    public String toString() {
        return String.format("Order{id='%s', customer='%s', status='%s', items=%d, date=%s}",
                orderId, customerId, status, items.size(), orderDate.toLocalDate());
    }
}

class OrderItem {
    String productId;
    int quantity;
    BigDecimal unitPrice;

    public OrderItem(String productId, int quantity, BigDecimal unitPrice) {
        this.productId = productId;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
    }

    public String getProductId() {
        return productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public BigDecimal getTotalPrice() {
        return unitPrice.multiply(BigDecimal.valueOf(quantity));
    }

    @Override
    public String toString() {
        return String.format("OrderItem{product='%s', qty=%d, price=%s}",
                productId, quantity, unitPrice);
    }
}

public class Stream4 {
    public static void main(String[] args) {
        List<Order> orders = createOrders();

//        System.out.println("=== ORDER DATASET ===");
//        orders.forEach(order -> {
//            System.out.println(order);
//            order.getItems().forEach(item -> System.out.println("  " + item));
//            System.out.println();
//        });

         Map<String,Double> revenuePerCustomer = orders.stream().collect(Collectors.groupingBy(Order::getCustomerId,Collectors.summingDouble(order->{
             AtomicReference<Double> revenue = new AtomicReference<>((double) 0);
             order.getItems().forEach(orderItem -> {
                 revenue.updateAndGet(v -> v + Double.parseDouble(orderItem.getTotalPrice().toString()));
             });
             return revenue.get();
         })));
        //System.out.println(revenuePerCustomer);

        List<OrderItem> orderItems = orders.stream().flatMap(order -> order.getItems().stream()).toList();
        //System.out.println(orderItems);

        List<OrderItem> top5orderItems  =orderItems.stream().sorted(Comparator.comparingInt(OrderItem::getQuantity).reversed()).limit(5).toList();
        //System.out.println(top5orderItems);

        Map<Order,Double> revenuePerOrder = orders.stream().collect(Collectors.groupingBy(Function.identity(),Collectors.summingDouble(order->{
            AtomicReference<Double> revenue = new AtomicReference<>((double) 0);
            order.getItems().forEach(orderItem -> {
                revenue.updateAndGet(v -> v + Double.parseDouble(orderItem.getTotalPrice().toString()));
            });
            return revenue.get();
        })));
        System.out.println(revenuePerOrder);
        List<Map.Entry<Order,Double>> filteredOrder = revenuePerOrder.entrySet().stream().filter(value-> value.getValue() > 100 && value.getKey().getStatus().equals("PENDING")).toList();
        //System.out.println(filteredOrder);
        for(Map.Entry<Order,Double> filterOrder : filteredOrder){
            System.out.println(filterOrder.getKey());
        }

        Map<String, Double> avgOrderValuePerMonth = orders.stream()
                .collect(Collectors.groupingBy(
                        order -> order.getOrderDate().format(DateTimeFormatter.ofPattern("yyyy-MM")),
                        Collectors.averagingDouble(order -> order.getItems().stream()
                                .mapToDouble(item-> item.getUnitPrice().multiply(BigDecimal.valueOf(item.getQuantity())).doubleValue()).sum())
                ));

        System.out.println(avgOrderValuePerMonth);


    }

    public static List<Order> createOrders() {
        return Arrays.asList(
                // Order 1 - Customer A
                new Order("ORD001", "CUST001", "COMPLETED",
                        Arrays.asList(
                                new OrderItem("PROD001", 2, new BigDecimal("25.99")),
                                new OrderItem("PROD002", 1, new BigDecimal("15.50")),
                                new OrderItem("PROD003", 3, new BigDecimal("8.75"))
                        ),
                        LocalDateTime.of(2024, 9, 15, 10, 30)),

                // Order 2 - Customer B
                new Order("ORD002", "CUST002", "PENDING",
                        Arrays.asList(
                                new OrderItem("PROD004", 1, new BigDecimal("199.99")),
                                new OrderItem("PROD005", 2, new BigDecimal("45.00"))
                        ),
                        LocalDateTime.of(2024, 9, 16, 14, 15)),

                // Order 3 - Customer A (repeat customer)
                new Order("ORD003", "CUST001", "SHIPPED",
                        Arrays.asList(
                                new OrderItem("PROD001", 5, new BigDecimal("25.99")),
                                new OrderItem("PROD006", 1, new BigDecimal("75.25"))
                        ),
                        LocalDateTime.of(2024, 9, 17, 9, 45)),

                // Order 4 - Customer C
                new Order("ORD004", "CUST003", "COMPLETED",
                        Arrays.asList(
                                new OrderItem("PROD007", 3, new BigDecimal("12.50")),
                                new OrderItem("PROD008", 2, new BigDecimal("22.75")),
                                new OrderItem("PROD009", 1, new BigDecimal("89.99"))
                        ),
                        LocalDateTime.of(2024, 9, 18, 16, 20)),

                // Order 5 - Customer D
                new Order("ORD005", "CUST004", "PENDING",
                        Arrays.asList(
                                new OrderItem("PROD002", 4, new BigDecimal("15.50")),
                                new OrderItem("PROD010", 1, new BigDecimal("120.00"))
                        ),
                        LocalDateTime.of(2024, 9, 19, 11, 10)),

                // Order 6 - Customer B (repeat customer)
                new Order("ORD006", "CUST002", "CANCELLED",
                        List.of(
                                new OrderItem("PROD003", 10, new BigDecimal("8.75"))
                        ),
                        LocalDateTime.of(2024, 9, 19, 13, 30)),

                // Order 7 - Customer E (large order)
                new Order("ORD007", "CUST005", "COMPLETED",
                        Arrays.asList(
                                new OrderItem("PROD004", 2, new BigDecimal("199.99")),
                                new OrderItem("PROD006", 3, new BigDecimal("75.25")),
                                new OrderItem("PROD011", 1, new BigDecimal("299.50"))
                        ),
                        LocalDateTime.of(2024, 9, 20, 8, 15)),

                // Order 8 - Customer A (third order)
                new Order("ORD008", "CUST001", "SHIPPED",
                        Arrays.asList(
                                new OrderItem("PROD012", 1, new BigDecimal("49.99")),
                                new OrderItem("PROD013", 2, new BigDecimal("18.25"))
                        ),
                        LocalDateTime.of(2024, 9, 21, 15, 45)),

                // Order 9 - Customer F
                new Order("ORD009", "CUST006", "PENDING",
                        Arrays.asList(
                                new OrderItem("PROD007", 6, new BigDecimal("12.50")),
                                new OrderItem("PROD014", 1, new BigDecimal("67.80"))
                        ),
                        LocalDateTime.of(2024, 9, 21, 20, 10)),

                // Order 10 - Customer C (repeat customer)
                new Order("ORD010", "CUST003", "COMPLETED",
                        Arrays.asList(
                                new OrderItem("PROD001", 8, new BigDecimal("25.99")),
                                new OrderItem("PROD015", 2, new BigDecimal("35.75"))
                        ),
                        LocalDateTime.of(2024, 9, 22, 12, 25))
        );
    }
}
