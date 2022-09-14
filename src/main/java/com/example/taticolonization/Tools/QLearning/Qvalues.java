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
 * @file Qvalues.java
 * @author Thomas FILLION (thomas.fillion@ecole.ensicaen.fr)
 * @author Mathilde LERAY (mathilde.leray@ecole.ensicaen.fr)
 * @author Tatiana YANG (tatiana.yang@ecole.ensicaen.fr)
 * @brief This file permit to initialize the Qvalues use for Qlearning
 * @version 1.0
 * @date 2022-03-06
 *
 * @copyright Copyright (c) 2021
 *
 */

package com.example.taticolonization.Tools.QLearning;

public class Qvalues {
    private Double up= Double.valueOf(0);
    private Double up_right= Double.valueOf(0);
    private Double up_left= Double.valueOf(0);
    private Double down= Double.valueOf(0);
    private Double down_right= Double.valueOf(0);
    private Double down_left= Double.valueOf(0);
    private Double right= Double.valueOf(0);
    private Double left= Double.valueOf(0);
    private Double reward= Double.valueOf(0);


    public Double sumValues(){
        return up+up_left+up_right+down+down_left+down_right+left+right;
    }

    public Double getReward() {
        return reward;
    }

    public void setReward(Double reward) {
        this.reward = reward;
    }

    public Double getUp_right() {
        return up_right;
    }

    public void setUp_right(Double up_right) {
        this.up_right = up_right;
    }

    public Double getUp_left() {
        return up_left;
    }

    public void setUp_left(Double up_left) {
        this.up_left = up_left;
    }

    public Double getDown_right() {
        return down_right;
    }

    public void setDown_right(Double down_right) {
        this.down_right = down_right;
    }

    public Double getDown_left() {
        return down_left;
    }

    public void setDown_left(double down_left) {
        this.down_left = down_left;
    }

    public Double getUp() {
        return up;
    }

    public void setUp(Double up) {
        this.up = up;
    }

    public Double getDown() {
        return down;
    }

    public void setDown(double down) {
        this.down = down;
    }

    public Double getRight() {
        return right;
    }

    public void setRight(Double right) {
        this.right = right;
    }

    public Double getLeft() {
        return left;
    }

    public void setLeft(Double left) {
        this.left = left;
    }
}
