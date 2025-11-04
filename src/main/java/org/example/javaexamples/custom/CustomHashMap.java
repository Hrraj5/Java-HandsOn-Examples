package org.example.javaexamples.custom;

class Entry<K, V> {
    K key;
    V value;
    Entry<K, V> next;

    Entry(K key, V value, Entry<K, V> next) {
        this.key = key;
        this.value = value;
        this.next = null;
    }

    public K getKey() {
        return this.key;
    }

    public V getValue() {
        return this.value;
    }

    public Entry<K, V> getNext() {
        return this.next;
    }
    @Override
    public String toString() {
        return "Entry [key=" + key + ", value=" + value + ", next=" + next + "]";
    }
}

class CustomHashMapCompute<K, V> {
    private final int MAX_CAPACITY = 16;
    private final Entry[] buckets;

    CustomHashMapCompute() {
        this.buckets = new Entry[MAX_CAPACITY];
    }

    public void put(K key, V value) {
        Entry<K, V> newEntry = new Entry<>(key, value, null);
        int index = this.getHashCode(key) % this.getBucketLength();
        Entry<K, V> current = buckets[index];

        if (current == null) {
            buckets[index] = newEntry;
        } else {
            while (current.next != null) {
                if (current.getKey().equals(key)) {
                    current.value = value;
                }
                current = current.next;
            }
            if (current.getKey().equals(key)) {
                current.value = value;
            } else {
                current.next = newEntry;
            }
        }
    }

    public V get(K key) {
        int index = this.getHashCode(key) % this.getBucketLength();
        Entry<K, V> current = buckets[index];
        while (current != null) {
            if (current.getKey().equals(key)) {
                return current.value;
            }
            current = current.next;
        }
        return null;
    }

    public V remove(K key) {
        int index = this.getHashCode(key) % this.getBucketLength();
        Entry<K, V> current = buckets[index];
        Entry<K, V> prev = null;
        while (current != null) {
            if (current.getKey().equals(key)) {
                if (prev == null) {
                    buckets[index] = current.next;
                } else {
                    prev.next = current.next;
                }
                return current.value;
            }
            prev = current;
            current = current.next;
        }
        return null;
    }

    public int getHashCode(K key) {
        return key == null ? 0 : Math.abs(key.hashCode());
    }

    public int getBucketLength() {
        return this.buckets.length;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < getBucketLength(); i++) {
            if (this.buckets[i] != null) {
                sb.append(i + " " + this.buckets[i] + "\n");
            } else {
                sb.append(i + " " + "null" + "\n");
            }
        }
        return sb.toString();
    }
}

public class CustomHashMap {
    public static void main(String[] args) {
        CustomHashMapCompute<String,String> map = new CustomHashMapCompute<>();
        map.put("1", "One");
        map.put("2", "Two");
        map.put("3", "Three");
        map.put("4", "Four");
        map.put("17", "Seventeen");
        map.put("33", "Thirtythree");
        System.out.println(map.toString());

        System.out.println("After collision...");
        map.put("A", "Letter A");
        map.put("Q", "Letter Q");

        map.put("Aa", "First");
        map.put("BB", "Second");

        System.out.println(map.toString());

        System.out.println("Value for key 2: " + map.get("2"));

        System.out.println("Removing key 3: " + map.remove("Q"));

        System.out.println("After removal....");
        System.out.println(map.toString());
    }


}
