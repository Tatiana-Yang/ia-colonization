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
 * @file Agent.java
 * @author Thomas FILLION (thomas.fillion@ecole.ensicaen.fr)
 * @author Mathilde LERAY (mathilde.leray@ecole.ensicaen.fr)
 * @author Tatiana YANG (tatiana.yang@ecole.ensicaen.fr)
 * @brief This file permit create an Agent
 * @version 1.0
 * @date 2022-03-06
 *
 * @copyright Copyright (c) 2021
 *
 */
package com.example.taticolonization.environment;

import com.example.taticolonization.TatiBot.ITatiBot;

import java.io.FileNotFoundException;

import static java.lang.System.exit;

public class Agent {
    private final ITatiBot ia;
    private int col;
    private int row;

    public Agent(ITatiBot ia, Integer row, Integer col){
        this.ia =ia;
        this.col = col;
        this.row = row;
    }

    public ITatiBot getIa() {
        return ia;
    }

    public Integer getCol() {
        return col;
    }

    public Integer getRow() {
        return row;
    }

    public void discover() throws FileNotFoundException {
        for(int i=-1;i<=1;i++) {
            for (int j = -1; j <= 1; j++) {
                if(row+i<Environment.getInstance().getPlanet().getHeight() &&
                    col+j<Environment.getInstance().getPlanet().getWidth() &&
                    row+i>=0 && col+j>=0)
                Environment.getInstance().getPlanet().setDiscovered(col+j,row+i);
            }
        }
    }

    public void trigger() throws FileNotFoundException {
        //mise à jour des coordonnées de l'IA

        ia.move();
        Environment.getInstance().getPlanet().getParcel(row,col).setNeedViewActualisation(true);

        if(is_move_correct(ia)){
            col= ia.getCol();
            row= ia.getRow();
            Environment.getInstance().getPlanet().setDiscovered(row,col);
            ia.moveCorrect();
        }
        ia.setCol(col);
        ia.setRow(row);
    }

    private boolean is_move_correct(ITatiBot ia) {
        if(ia.getCol()<=col+1 && ia.getRow()<=row+1 && ia.getCol()>=col-1 && ia.getRow()>=row-1){
            return true;
        }
        /*System.out.println();
        System.out.println(ia.getX());
        System.out.println(ia.getY());
        System.out.println();
        System.out.println(x);
        System.out.println(y);
        System.out.println();
        exit(0);*/

        return false;
    }
}
