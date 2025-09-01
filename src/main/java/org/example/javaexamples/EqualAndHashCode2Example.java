package org.example.javaexamples;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class EqualAndHashCode2Example {
    static class User{
        private String name;
        private Address address;

        public User(String name, Address address){
            this.name = name;
            this.address = address;
        }

        @Override
        public boolean equals(Object o) {
            if( this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            User user = (User) o;
            return Objects.equals(name, user.name) && Objects.equals(address, user.address);
        }

        @Override
        public int hashCode() {
            return Objects.hash(name, address.city,address.state);
        }
    }
    static class Address{
        private String city;
        private String state;

        Address(String city, String state){
            this.city = city;
            this.state = state;
        }

        @Override
        public boolean equals(Object o) {
            if( this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Address add = (Address) o;
            return Objects.equals(city, add.city) && Objects.equals(state, add.state);
        }

        @Override
        public int hashCode() {
            return Objects.hash(city, state);
        }
    }
    public static void main(String[] args) {
        User u1 = new User("Hrithik", new Address("Patna","Bihar"));
        User u2 =  new User("Riithik", new Address("Patna","Bihar"));

        Map<User,String> map1 = new HashMap<>();
        map1.put(u1,"Engineer");
        System.out.println(map1.get(u1));
        System.out.println(map1.get(u2));
    }
}
