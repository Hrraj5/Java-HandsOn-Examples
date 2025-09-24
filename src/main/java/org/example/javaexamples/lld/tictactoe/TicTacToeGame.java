package org.example.javaexamples.lld.tictactoe;

import org.example.javaexamples.lld.tictactoe.entities.Matrix;
import org.example.javaexamples.lld.tictactoe.entities.Player;
import org.example.javaexamples.lld.tictactoe.entities.ShapeEnum;

import java.util.ArrayList;
import java.util.List;

public class TicTacToeGame {
    public static void main(String[] args) {
        PlayerBoard board = new PlayerBoard();
        Player[][] playerBoard = board.initialize(3);

        PlayerManager playerManagerInstance = PlayerManager.getPlayerManager();
        Player player1 = playerManagerInstance.getNewPlayer("Hrithik",ShapeEnum.CIRCLE);
        Player player2 = playerManagerInstance.getNewPlayer("Mohit", ShapeEnum.CROSS);


        playerBoard[0][0] = player1;
        playerManagerInstance.checkWinner(playerBoard,player1);

        playerBoard[0][1] = player2;
        playerManagerInstance.checkWinner(playerBoard,player2);

        playerBoard[1][0] = player1;
        playerManagerInstance.checkWinner(playerBoard,player1);

        playerBoard[1][1] = player2;
        playerManagerInstance.checkWinner(playerBoard,player2);

        playerBoard[2][2] = player1;
        playerManagerInstance.checkWinner(playerBoard,player1);

        playerBoard[2][1] = player2;
        playerManagerInstance.checkWinner(playerBoard,player2);


    }
}
