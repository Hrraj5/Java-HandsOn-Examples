package org.example.javaexamples.designpatterns.behaviourial.iterator;

import java.util.List;

public class Library {

    private final List<String> bookList;

    public Library(List<String> bookList){
        this.bookList = bookList;
    }

    public BookIterator createIterator(){
        return new BookIterator(bookList);
    }
}
