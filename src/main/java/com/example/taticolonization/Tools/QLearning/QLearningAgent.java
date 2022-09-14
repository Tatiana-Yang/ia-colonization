/*
 * ENSICAEN
 * 6 Boulevard Maréchal Juin
 * F-14050 Caen Cedex
 *
 * This file is owned by ENSICAEN students. No portion of this
 * document may be reproduced, copied or revised without written
 * permission of the authors.
 */

/**
 * @file QLearningAgent.java
 * @author Thomas FILLION (thomas.fillion@ecole.ensicaen.fr)
 * @author Mathilde LERAY (mathilde.leray@ecole.ensicaen.fr)
 * @author Tatiana YANG (tatiana.yang@ecole.ensicaen.fr)
 * @brief This file permit to do the Qlearning
 * @version 1.0
 * @date 2022-03-06
 *
 * @copyright Copyright (c) 2021
 *
 */

package com.example.taticolonization.Tools.QLearning;

import com.example.taticolonization.TatiBot.TatiCentralisateur;
import com.example.taticolonization.environment.Environment;
import com.example.taticolonization.planet.Entity.Parcel;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;

public class QLearningAgent {
    private final double gamma;
    private final double alpha;
    private final Integer iteration;
    private final Parcel goalParcel;
    private final Random random = new Random();
    private Integer row;
    private Integer col;

    public QLearningAgent(Integer iteration, Parcel goalParcel, Integer col, Integer row){
        this.col = col;
        this.row = row;
        this.gamma = 0.9;
        this.alpha = 0.1;
        this.iteration = iteration;
        this.goalParcel = goalParcel;
    }
    public QLearningAgent(Integer iteration, Parcel goalParcel, Double gamma, Double alpha, Integer col, Integer row){
        this.col = col;
        this.row = row;
        this.gamma = gamma;
        this.alpha = alpha;
        this.iteration = iteration;
        this.goalParcel = goalParcel;
    }

    public ArrayList<Integer> random_move() throws FileNotFoundException {
        int valueCol = random.nextInt(3)-1;
        int valueRow = random.nextInt(3)-1;
        while(isOutofBound(valueCol,valueRow)){
            valueCol = random.nextInt(3)-1;
            valueRow = random.nextInt(3)-1;
        }
        ArrayList<Integer> res = new ArrayList<>();
        res.add(valueCol);
        res.add(valueRow);
        return res;
    }

    private boolean isOutofBound(int valueCol, int valueRow) throws FileNotFoundException {
        if(col + valueCol >= TatiCentralisateur.getInstance().getWorldSave().size() ||
                valueRow + row >= TatiCentralisateur.getInstance().getWorldSave().get(0).size() ||
                col +valueCol<0 || valueRow+ row <0 ) {
                return true;
        }
        /*
        if ((TatiCentralisateur.getInstance().getWorldSave().get(valueRow + row).get(valueCol + col) == null)) {
            return true;
        }
         */
        return false;

    }

    public void doMove(ArrayList<Integer> move){
        col +=move.get(0);
        row += move.get(1);
    }

    //exploration with no goal, allow bots to void damage
    public void explore_with_no_goal() throws FileNotFoundException {
        for(int i=0; i<iteration; i++) {
            ArrayList<Integer> move = null;
            if (Math.random() < gamma) {
                move = random_move();
                while (isOutofBound(move.get(0), move.get(1))) {
                    move = random_move();
                }
            } else {
                //TODO Not implemented (Choose best action)
                move = random_move();
                while (isOutofBound(move.get(0), move.get(1))) {
                    move = random_move();
                }
            }
            changeQvalue(move);
            doMove(move);

        }
    }

    private void changeQvalue(ArrayList<Integer> move) throws FileNotFoundException {
        Qvalues source = TatiCentralisateur.getInstance().getWorldSave().get(row).get(col).getQvalues();
        Qvalues destination = TatiCentralisateur.getInstance().getWorldSave().get(row+move.get(1)).get(col+move.get(0)).getQvalues();
        switch(move.get(0)){
            case -1:
                switch(move.get(1)) {
                    case -1:
                        source.setDown_left( (source.getDown_left() + alpha * (destination.getReward() + gamma * destination.sumValues() - source.getDown_left())));
                        break;
                    case 0:
                        source.setDown( (source.getDown() + alpha * (destination.getReward() + gamma * destination.sumValues() - source.getDown())));
                        break;
                    case 1:
                        source.setDown_right( (source.getDown_right() + alpha * (destination.getReward() + gamma * destination.sumValues() - source.getDown_right())));
                        break;
                }
                break;
            case 0:
                switch(move.get(1)) {
                    case -1:
                        source.setLeft( (source.getLeft() + alpha * (destination.getReward() + gamma * destination.sumValues() - source.getLeft())));
                        break;
                    case 0:
                        break;
                    case 1:
                        source.setRight( (source.getRight() + alpha * (destination.getReward() + gamma * destination.sumValues() - source.getRight())));
                        break;
                }
                break;
            case 1:
                switch(move.get(1)) {
                    case -1:
                        source.setUp_left( (source.getUp_left() + alpha * (destination.getReward() + gamma * destination.sumValues() - source.getUp_left())));
                        break;
                    case 0:
                        source.setUp( (source.getUp() + alpha * (destination.getReward() + gamma * destination.sumValues() - source.getUp())));
                        break;
                    case 1:
                        source.setUp_right( (source.getUp_right() + alpha * (destination.getReward() + gamma * destination.sumValues() - source.getUp_right())));
                        break;
                }
                break;
        }
    }

}
