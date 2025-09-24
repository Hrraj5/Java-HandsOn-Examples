package org.example.javaexamples.lld.tictactoe.entities;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Matrix {
    private int row;
    private int col;

    public Matrix(int row, int col){
        this.row = row;
        this.col = col;
    }
}
