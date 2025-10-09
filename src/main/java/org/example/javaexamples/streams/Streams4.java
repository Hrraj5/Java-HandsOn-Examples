package org.example.javaexamples.streams;

import lombok.Getter;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Streams4 {

    public static void main(String[] args) {
        List<User> users = generateUsers(1000, 0.1); // 10% duplicate emails
        //users.forEach(u -> System.out.println(u.toJson()));

       // 1. Find top 10 countries by user count (memory-efficient)

        Map<String,Long> top10UserCount = users.stream().collect(Collectors.groupingBy(User::getCountry,Collectors.counting())).entrySet().stream()
                .sorted(Map.Entry.<String,Long>comparingByValue().reversed()).limit(10).collect(Collectors.toMap(Map.Entry::getKey,Map.Entry::getValue,(u1,u2)->u1));
        System.out.println(top10UserCount);

        //Set<String> distinctEmails  = users.stream().map(user-> user.email).collect(Collectors.toSet());
        //List<User> sortedUsers = users.stream().sorted(Comparator.comparing(User::getEmail)).toList();
        Set<String> duplicateEmails =  new HashSet<>();
        List<User> duplicateUsers = new ArrayList<>();
        users.forEach(user->{
            if(!duplicateEmails.contains(user.email)){
                duplicateEmails.add(user.email);
            }else{
                duplicateUsers.add(user);
            }
        });
        //List<User> duplicateUsersList = duplicateUsers.stream().toList();
        duplicateUsers.forEach(user1-> System.out.println(user1.toJson()));

    }

    public static List<User> generateUsers(int count, double duplicateRatio) {
        Random rnd = new Random();
        String[] countries = {
                "United States", "United Kingdom", "India", "Canada", "Germany",
                "France", "Spain", "Italy", "Australia", "Brazil", "Japan", "South Korea"
        };
        String[] interestsList = {
                "sports", "music", "movies", "reading", "cooking", "travel",
                "gaming", "fitness", "technology", "photography", "gardening", "art"
        };
        String[] emailDomains = {"example.com", "testmail.com", "mail.com", "sample.org", "domain.net"};

        List<User> users = new ArrayList<>(count);

        // Step 1: Generate base emails for duplicates
        int duplicateCount = (int) (count * duplicateRatio);
        List<String> duplicateEmails = new ArrayList<>();
        for (int i = 0; i < duplicateCount; i++) {
            duplicateEmails.add("duplicate_user" + i + "@" + emailDomains[rnd.nextInt(emailDomains.length)]);
        }

        // Step 2: Create users
        for (int i = 0; i < count; i++) {
            String id = UUID.randomUUID().toString();
            String email;

            // Some users will share exact same email
            if (i < duplicateCount * 2 && !duplicateEmails.isEmpty() && rnd.nextBoolean()) {
                email = duplicateEmails.get(rnd.nextInt(duplicateEmails.size()));
            } else {
                // Generate unique email
                email = "user" + id.substring(0, 6) + "@" + emailDomains[rnd.nextInt(emailDomains.length)];
            }

            String country = countries[rnd.nextInt(countries.length)];
            int age = 18 + rnd.nextInt(63);

            // Random subset of interests
            int interestCount = 1 + rnd.nextInt(4);
            Set<String> interests = new HashSet<>();
            while (interests.size() < interestCount) {
                interests.add(interestsList[rnd.nextInt(interestsList.length)]);
            }

            BigDecimal accountBalance = BigDecimal.valueOf(rnd.nextDouble() * 20000)
                    .setScale(2, RoundingMode.HALF_UP);

            users.add(new User(id, email, country, age, new ArrayList<>(interests), accountBalance));
        }

        return users;
    }
    @Getter
    public static class User {
        private final String id;
        private final String email;
        private final String country;
        private final int age;
        private final List<String> interests;
        private final BigDecimal accountBalance;

        public User(String id, String email, String country, int age, List<String> interests, BigDecimal accountBalance) {
            this.id = id;
            this.email = email;
            this.country = country;
            this.age = age;
            this.interests = interests;
            this.accountBalance = accountBalance;
        }

        public String toJson() {
            return String.format(
                    "{\"id\":\"%s\",\"email\":\"%s\",\"country\":\"%s\",\"age\":%d,\"interests\":%s,\"accountBalance\":%s}",
                    escape(id),
                    escape(email),
                    escape(country),
                    age,
                    toJsonArray(interests),
                    accountBalance
            );
        }

        private String toJsonArray(List<String> list) {
            return "[" + String.join(",", list.stream().map(s -> "\"" + escape(s) + "\"").toList()) + "]";
        }

        private String escape(String s) {
            return s.replace("\\", "\\\\").replace("\"", "\\\"");
        }
    }
}
