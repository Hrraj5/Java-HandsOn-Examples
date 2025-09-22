package org.example.javaexamples.designpatterns.behaviourial.iterator;

public class Books {
    private String name;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String type;

    public Books(String name, String type) {
        this.name = name;
        this.type = type;
    }


}
