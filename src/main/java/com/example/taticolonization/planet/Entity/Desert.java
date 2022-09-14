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
 * @file Desert.java
 * @author Thomas FILLION (thomas.fillion@ecole.ensicaen.fr)
 * @author Mathilde LERAY (mathilde.leray@ecole.ensicaen.fr)
 * @author Tatiana YANG (tatiana.yang@ecole.ensicaen.fr)
 * @brief This file permit to initialize a parcel of desert
 * @version 1.0
 * @date 2022-03-06
 *
 * @copyright Copyright (c) 2021
 *
 */
package com.example.taticolonization.planet.Entity;

public class Desert extends Parcel{

    public Desert(int row, int col) {
        super(row, col);
        setUrlImage("src/main/resources/pictures/sand.jpg");
        setType(8);
    }

    @Override
    public Parcel metamorphosisField(int row, int col){
        double prob = Math.random();
        if(prob < 0.65) {
            return new Grassland(row, col, 4);
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
        return "_";
    }
}
