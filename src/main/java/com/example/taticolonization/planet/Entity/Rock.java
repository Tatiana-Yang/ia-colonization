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
 * @file Rock.java
 * @author Thomas FILLION (thomas.fillion@ecole.ensicaen.fr)
 * @author Mathilde LERAY (mathilde.leray@ecole.ensicaen.fr)
 * @author Tatiana YANG (tatiana.yang@ecole.ensicaen.fr)
 * @brief This file permit to initialize a parcel of rock
 * @version 1.0
 * @date 2022-03-06
 *
 * @copyright Copyright (c) 2021
 *
 */

package com.example.taticolonization.planet.Entity;

public class Rock extends Parcel {

    public Rock(int x, int y) {
        super(x, y);
        setUrlImage("src/main/resources/pictures/rock.png");
        setType(2);
    }

    @Override
    public Parcel metamorphosisField(int i, int j){
        double prob = Math.random();
        if(prob < 0.0001) {
            return new Mineral(i, j);
        } else if(prob < 0.005) {
            return new ImpassableZone(i, j);
        }
        return this;
    }

    @Override
    public void extract(){
        int newQt = getQuantite();
        setQuantite(getQuantite() - 2);
    }

    @Override
    public Parcel qtEmpty(){
        if(getQuantite() == 0){
            return new Rock(getRow(), getCol());
        }
        return this;
    }

    @Override
    public String toString() {
        return "0";
    }
}
