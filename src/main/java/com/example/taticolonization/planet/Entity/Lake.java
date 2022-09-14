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
 * @file Lake.java
 * @author Thomas FILLION (thomas.fillion@ecole.ensicaen.fr)
 * @author Mathilde LERAY (mathilde.leray@ecole.ensicaen.fr)
 * @author Tatiana YANG (tatiana.yang@ecole.ensicaen.fr)
 * @brief This file permit create a parcel of Lake that is on the exosquelette
 * @version 1.0
 * @date 2022-03-06
 *
 * @copyright Copyright (c) 2021
 *
 */

package com.example.taticolonization.planet.Entity;

public class Lake extends FieldMetamorphosis{

    public Lake(int row, int col) {
        super(row, col);
        setQuantite(200000);
        setUrlImage("src/main/resources/pictures/water.png");
        setType(7);
        this.speed = 0;
        this.amplitude = 0;
    }

    @Override
    public void propagation(){
        if(this.qtExtractParcel >= 69000) {
            this.amplitude = 2;
            this.speed = 2;
        } else {
            this.amplitude = 1;
            this.speed = 1;
        }
    }

    @Override
    public Parcel metamorphosisField(int row, int col) {
        if(getQuantite() == 0) {
            return new Desert(row, col);
        }
        return this;
    }

    @Override
    public void extract(){
        int newQt = getQuantite();
        setQuantite(getQuantite() - 1000);
        this.qtExtractParcel += 1000;
    }

    @Override
    public Parcel qtEmpty(){
        if(getQuantite() == 0){
            return new Desert(getRow(), getCol());
        }
        return this;
    }

    @Override
    public String toString() {
        return "~";
    }
}
