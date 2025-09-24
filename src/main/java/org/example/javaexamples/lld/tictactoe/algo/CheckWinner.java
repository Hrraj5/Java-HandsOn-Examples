package org.example.javaexamples.lld.tictactoe.algo;

import org.example.javaexamples.lld.tictactoe.entities.Player;

public class CheckWinner {

    public boolean checkWinner(Player[][] playerBoard, Player player) {
        // Columns
       if(playerBoard[0][0].getShapeEnum().compareTo(player.getShapeEnum()) == 0
                && playerBoard[1][0].getShapeEnum().compareTo(player.getShapeEnum()) == 0
                && playerBoard[2][0].getShapeEnum().compareTo(player.getShapeEnum()) == 0){
           return true;
       }
       if(playerBoard[0][1].getShapeEnum().compareTo(player.getShapeEnum()) == 0
                && playerBoard[1][1].getShapeEnum().compareTo(player.getShapeEnum()) == 0
                && playerBoard[2][1].getShapeEnum().compareTo(player.getShapeEnum()) == 0){
            return true;
        }
        if(playerBoard[0][2].getShapeEnum().compareTo(player.getShapeEnum()) == 0
                && playerBoard[1][2].getShapeEnum().compareTo(player.getShapeEnum()) == 0
                && playerBoard[2][2].getShapeEnum().compareTo(player.getShapeEnum()) == 0){
            return true;
        }
        // Rows
        if(playerBoard[0][0].getShapeEnum().compareTo(player.getShapeEnum()) == 0
                && playerBoard[0][1].getShapeEnum().compareTo(player.getShapeEnum()) == 0
                && playerBoard[0][2].getShapeEnum().compareTo(player.getShapeEnum()) == 0){
            return true;
        }
        if(playerBoard[1][0].getShapeEnum().compareTo(player.getShapeEnum()) == 0
                && playerBoard[1][1].getShapeEnum().compareTo(player.getShapeEnum()) == 0
                && playerBoard[1][2].getShapeEnum().compareTo(player.getShapeEnum()) == 0){
            return true;
        }
        if(playerBoard[2][0].getShapeEnum().compareTo(player.getShapeEnum()) == 0
                && playerBoard[2][1].getShapeEnum().compareTo(player.getShapeEnum()) == 0
                && playerBoard[2][2].getShapeEnum().compareTo(player.getShapeEnum()) == 0){
            return true;
        }
        // diagonal
        return false;
    }
}
