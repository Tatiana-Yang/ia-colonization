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
 * @file Food.java
 * @author Thomas FILLION (thomas.fillion@ecole.ensicaen.fr)
 * @author Mathilde LERAY (mathilde.leray@ecole.ensicaen.fr)
 * @author Tatiana YANG (tatiana.yang@ecole.ensicaen.fr)
 * @brief This file permit to initialize a parcel of food
 * @version 1.0
 * @date 2022-03-06
 *
 * @copyright Copyright (c) 2021
 *
 */

package com.example.taticolonization.planet.Entity;

public class Food extends Parcel {
    private int amount;
    private String urlImage0 = "src/main/resources/pictures/grass.png";

    public String getUrlImage0() {
        return urlImage0;
    }

    public Food(int row, int col) {
        super(row, col);
        this.amount = 10;
        setUrlImage("src/main/resources/pictures/food.png");
        setType(9);
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    @Override
    public Parcel metamorphosisField(int row, int col){
        double prob = Math.random();
        if(prob < 0.1) {
            double r = Math.random();
            if(r < 0.5) {
                return new Grassland(row, col, 4);
            } else {
                return new Forest(row, col);
            }
        } else if(prob < 0.3) {
            return new Grassland(row, col, 5);
        } else if(prob < 0.5) {
            return new Grassland(row, col, 6);
        }
        return this;
    }

    @Override
    public void extract(){
        setQuantite(getQuantite() - 10);
        System.out.println(getQuantite());
    }

    @Override
    public Parcel qtEmpty(){
        if(getQuantite() == 0){
            return new Grassland(getRow(), getCol(), Grassland.DRY);
        }
        return this;
    }

    @Override
    public String toString() {
        return "*";
    }

    public int collectFood() {
        this.amount -= 5;
        return 5;
    }
}