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
 * @file Grassland.java
 * @author Thomas FILLION (thomas.fillion@ecole.ensicaen.fr)
 * @author Mathilde LERAY (mathilde.leray@ecole.ensicaen.fr)
 * @author Tatiana YANG (tatiana.yang@ecole.ensicaen.fr)
 * @brief This file permit to initialize a parcel of grassland it could be dry, normale or thick
 * @version 1.0
 * @date 2022-03-06
 *
 * @copyright Copyright (c) 2021
 *
 */

package com.example.taticolonization.planet.Entity;

public class Grassland extends Parcel{
    static final int DRY = 4;
    static final int NORMALE = 5;
    static final int THICK = 6;
    private int irigation = 0;
    private int fertilize = 0;

    public Grassland(int row, int col, int type) {
        super(row, col);
        setType(type);
        switch (getType()) {
            case DRY:
                setUrlImage("src/main/resources/pictures/dry.jpeg");
                break;
            case NORMALE:
                setUrlImage("src/main/resources/pictures/grass.png");
                break;
            default:
                setUrlImage("src/main/resources/pictures/thick.jpg");
                break;
        }
    }

    public int getIrigation() {
        return irigation;
    }

    public void setIrigation(int irigation) {
        this.irigation = irigation;
    }

    public int getFertilize() {
        return fertilize;
    }

    public void setFertilize(int fertilize) {
        this.fertilize = fertilize;
    }

    @Override
    public Parcel metamorphosisField(int row, int col){
        double prob = Math.random();
        switch (getType()){
            case DRY:
                if(prob < 0.19) {
                    return new Food(row, col);
                } else if(prob < 0.8) {
                    return new Desert(row, col);
                }
                return this;
            case NORMALE:
                if(prob < 0.1) {
                    new Desert(row, col);
                } else if(prob < 0.3) {
                    new Food(row, col);
                } else if(prob < 0.6) {
                    new Grassland(row, col, DRY);
                }
                return this;
            case THICK:
                if(prob < 0.05) {
                    new Desert(row, col);
                } else if(prob < 0.25) {
                    new Food(row, col);
                } else if(prob < 0.3) {
                    new Grassland(row, col, DRY);
                } else if(prob < 0.4) {
                    new Grassland(row, col, NORMALE);
                }
                return this;
            default:
                return this;
        }
    }

    @Override
    public void extract(){
    }

    @Override
    public Parcel qtEmpty(){
        return this;
    }

    @Override
    public String toString() {
        switch (getType()){
            case DRY:
                return ".";
            case NORMALE :
                return ";";
            default:
                return "\"";
        }
    }
}