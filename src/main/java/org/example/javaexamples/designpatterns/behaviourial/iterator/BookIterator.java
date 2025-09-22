package org.example.javaexamples.designpatterns.behaviourial.iterator;

import java.util.Iterator;
import java.util.List;

public class BookIterator implements Iterator {

    private List<String> bookList;
    private int size = 0;

    public BookIterator(List<String> bookList){
        this.bookList = bookList;
    }

    @Override
    public boolean hasNext() {
        return size < this.bookList.size();
    }

    @Override
    public Object next() {
       if(this.hasNext()){
           return this.bookList.get(size++);
       }
       return null;
    }
}
