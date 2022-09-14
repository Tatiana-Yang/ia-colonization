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
 * @file Forest.java
 * @author Thomas FILLION (thomas.fillion@ecole.ensicaen.fr)
 * @author Mathilde LERAY (mathilde.leray@ecole.ensicaen.fr)
 * @author Tatiana YANG (tatiana.yang@ecole.ensicaen.fr)
 * @brief This file permit to initialize a parcel of forest
 * @version 1.0
 * @date 2022-03-06
 *
 * @copyright Copyright (c) 2021
 *
 */
package com.example.taticolonization.planet.Entity;

public class Forest extends Parcel {

    public Forest(int row, int col) {
        super(row, col);
        setUrlImage("src/main/resources/pictures/forest.png");
        setType(3);
    }

    @Override
    public Parcel metamorphosisField(int row, int col){
        double prob = Math.random();
        if(prob < 0.09) {
            return new Desert(row, col);
        } else if(prob < 0.2) {
            return new Grassland(row, col, 4);
        } else if(prob < 0.3) {
            return new Grassland(row, col, 5);
        } else if(prob < 0.4) {
            return new Grassland(row, col, 6);
        }
        return this;
    }

    @Override
    protected Parcel qtEmpty() {
        return this;
    }

    @Override
    public void extract() {

    }

    @Override
    public String toString() {
        return "^";
    }
}