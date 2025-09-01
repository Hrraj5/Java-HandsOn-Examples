package org.example.javaexamples;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class EqualsAndHashCodeExample {
    static class User{
        private String name;
        private int roll;

        User(String name, int roll) {
            this.name = name;
            this.roll = roll;
        }

        @Override
        public boolean equals(Object obj) {
            if(this == obj) return true;
            if(obj == null || getClass()!= obj.getClass()) return false;
            User that = (User) obj;
            return Objects.equals(that.name,name) && Objects.equals(that.roll,roll);
        }

        @Override
        public int hashCode() {
            return Objects.hash(name,roll);
        }
    }
    public static void main(String[] args) {
        User u1 = new User("Hrithik",1);
        User u2 = new User("Hrithik", 1);
        Map<User,String> map = new HashMap<>();
        map.put(u1,"Topper");
        System.out.println(map.get(u1));
        System.out.println(map.get(u2));
    }
}
