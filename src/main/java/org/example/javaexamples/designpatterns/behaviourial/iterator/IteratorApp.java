package org.example.javaexamples.designpatterns.behaviourial.iterator;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class IteratorApp {

    public static void main(String[] args) {
        List<Books> bookList  = Arrays.asList(new Books("Harry Potter","fictional"),
                new Books("Shiva Trilogy","fictional"),
                new Books("Time","non-fictional"));
        List<String> booksName = bookList.stream().map(Books::getName).toList();
        Library lib = new Library(booksName);
        BookIterator bookIterator =  lib.createIterator();

        while(bookIterator.hasNext()){
            System.out.println(bookIterator.next());
        }
    }
}
