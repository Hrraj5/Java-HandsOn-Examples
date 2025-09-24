package org.example.javaexamples.lld.tictactoe.entities;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Player {
    private String name;
    private ShapeEnum shapeEnum;

    public Player(String name, ShapeEnum shapeEnum){
        this.name  = name;
        this.shapeEnum = shapeEnum;
    }
}
