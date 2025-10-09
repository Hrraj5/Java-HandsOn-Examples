package org.example.javaexamples.lld.tictactoe;

import org.example.javaexamples.lld.tictactoe.algo.CheckWinner;
import org.example.javaexamples.lld.tictactoe.entities.Player;
import org.example.javaexamples.lld.tictactoe.entities.ShapeEnum;

import java.awt.*;

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


    public void checkWinner(Player[][] playerBoard,Player player){
        CheckWinner checkWinner = new CheckWinner();
        if(checkWinner.checkWinner(playerBoard,player)){
            System.out.println("Player "+ player.getName()+ " is winner!");
        }
    }
}
