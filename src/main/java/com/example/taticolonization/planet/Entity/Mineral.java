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
 * @file Mineral.java
 * @author Thomas FILLION (thomas.fillion@ecole.ensicaen.fr)
 * @author Mathilde LERAY (mathilde.leray@ecole.ensicaen.fr)
 * @author Tatiana YANG (tatiana.yang@ecole.ensicaen.fr)
 * @brief This file permit to initialize a parcel of mineral that is a parcel of the exosquelette
 * @version 1.0
 * @date 2022-03-06
 *
 * @copyright Copyright (c) 2021
 *
 */

package com.example.taticolonization.planet.Entity;

public class Mineral extends FieldMetamorphosis {

    private boolean mark;

    public Mineral(int row, int col) {
        super(row, col);
        setUrlImage("src/main/resources/pictures/mineral.png");
        setType(1);
        this.speed = 0;
        this.amplitude = 0;
    }

    public boolean isMark() {
        return mark;
    }

    public void setMark(boolean mark) {
        this.mark = mark;
    }

    // should we mark the extraction on Parcel around ?
    @Override
    public void propagation(){
        if(this.qtExtractParcel >= 0 && this.qtExtractParcel < 15){
            this.amplitude = 2;
            this.speed = 2;
        }
        else if(this.qtExtractParcel >= 15 && this.qtExtractParcel <50){
            this.amplitude = 3;
            this.speed = 2;
        }
        else if(this.qtExtractParcel >= 50){
            this.amplitude = 4;
            this.speed = 3;
        }
    }

    @Override
    public Parcel metamorphosisField(int row, int col) {
        double prob = Math.random();
        if(prob < 0.05) {
            return new Rock(row, col);
        }
        return this;
    }

    @Override
    public void extract(){
        this.qtExtractParcel += 2;
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
        return "#";
    }

    public String getUrlImage0() {
        return "src/main/resources/pictures/grass.png";
    }
}
