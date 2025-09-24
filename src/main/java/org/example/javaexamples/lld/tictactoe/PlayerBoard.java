package org.example.javaexamples.lld.tictactoe;

import org.example.javaexamples.lld.tictactoe.entities.Player;
import org.example.javaexamples.lld.tictactoe.entities.ShapeEnum;


public class PlayerBoard {

    public Player[][] initialize(int size){
        Player[][] playerBoard = new Player[size][size];
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                playerBoard[i][j] = new Player("", ShapeEnum.NONE);
            }
        }
        return playerBoard;
    }
}
