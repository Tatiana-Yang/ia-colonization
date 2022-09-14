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
 * @file Colony.java
 * @author Thomas FILLION (thomas.fillion@ecole.ensicaen.fr)
 * @author Mathilde LERAY (mathilde.leray@ecole.ensicaen.fr)
 * @author Tatiana YANG (tatiana.yang@ecole.ensicaen.fr)
 * @brief This file permit to initialize the colony on the planet
 * @version 1.0
 * @date 2022-03-06
 *
 * @copyright Copyright (c) 2021
 *
 */
package com.example.taticolonization.planet.Entity;

public class Colony extends Parcel {

    private double stockFood = 0;

    public Colony(int row, int col) {
        super(row, col);
        setUrlImage("src/main/resources/pictures/colony.png");
        setType(0);
    }

    @Override
    public Parcel metamorphosisField(int row, int col){
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
        return "=";
    }
}