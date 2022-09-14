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
 * @file ImpassableZone.java
 * @author Thomas FILLION (thomas.fillion@ecole.ensicaen.fr)
 * @author Mathilde LERAY (mathilde.leray@ecole.ensicaen.fr)
 * @author Tatiana YANG (tatiana.yang@ecole.ensicaen.fr)
 * @brief This file permit to initialize a parcel of impassable zone
 * @version 1.0
 * @date 2022-03-06
 *
 * @copyright Copyright (c) 2021
 *
 */

package com.example.taticolonization.planet.Entity;

public class ImpassableZone extends Parcel {

    public ImpassableZone(int row, int col) {
        super(row, col);
        setReachable(false);
        setUrlImage("src/main/resources/pictures/impassable.png");
        setType(10);
    }

    @Override
    public Parcel metamorphosisField(int row, int col){
        double prob = Math.random();
        if(prob < 0.0001) {
            double r = Math.random();
            if(r < 0.5) {
                new Mineral(row, col);
            } else {
                new Rock(row, col);
            }
        }
        return this;
    }

    @Override
    protected Parcel qtEmpty() {
        return null;
    }

    @Override
    public void extract() {

    }

    @Override
    public String toString() {
        return "X";
    }
}
