package org.example.javaexamples.lld.tictactoe;

import org.example.javaexamples.lld.tictactoe.algo.CheckWinner;
import org.example.javaexamples.lld.tictactoe.entities.Matrix;
import org.example.javaexamples.lld.tictactoe.entities.Player;
import org.example.javaexamples.lld.tictactoe.entities.ShapeEnum;

import java.awt.*;
import java.util.List;

public class PlayerManager {

    private static PlayerManager playerManagerInstance;

    private PlayerManager(){}

    public static PlayerManager getPlayerManager() {
        if(playerManagerInstance == null){
            return new PlayerManager();
        }
        return playerManagerInstance;
    }
    public Player getNewPlayer(String name,ShapeEnum shapeEnum){
        return new Player(name,shapeEnum);
    }

//    public void setMatrixData(Player player){
//        List<Matrix> playerChance = player.getChances();
//        playerChance.add(player.getMatrix());
//    }

    public void checkWinner(Player[][] playerBoard,Player player){
        CheckWinner checkWinner = new CheckWinner();
        if(checkWinner.checkWinner(playerBoard,player)){
            System.out.println("Player "+ player.getName()+ " is winner!");
        }
    }
}
