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
 * @file Parcel.java
 * @author Thomas FILLION (thomas.fillion@ecole.ensicaen.fr)
 * @author Mathilde LERAY (mathilde.leray@ecole.ensicaen.fr)
 * @author Tatiana YANG (tatiana.yang@ecole.ensicaen.fr)
 * @brief This file permit create a parcel
 * @version 1.0
 * @date 2022-03-06
 *
 * @copyright Copyright (c) 2021
 *
 */

package com.example.taticolonization.planet.Entity;
import javafx.util.Pair;

public abstract class Parcel {
    private Pair<Integer, Integer> pos;
    private String urlImage;
    private boolean discovered = false;
    private boolean needViewActualisation = true;
    private boolean isReachable;
    private int type;
    private int quantite;
    private int posCol;
    private int posRow;

    public boolean isNeedViewActualisation() {
        return needViewActualisation;
    }

    public void setNeedViewActualisation(boolean needViewActualisation) {
        this.needViewActualisation = needViewActualisation;
    }

    // CONSTRUCTOR
    public Parcel(int row, int col) {
        this.pos = new Pair<>(row, col);
        this.posRow = row;
        this.posCol = col;
        this.urlImage = "src/main/resources/pictures/rock.png";
        this.isReachable = true;
        this.type = 2;
        this.quantite = 100;
    }


    // GETTERS AND SETTERS
    public Integer getCol(){
        return pos.getKey();
    }

    public Integer getRow(){
        return pos.getValue();
    }

    public Pair<Integer, Integer> getPos() {
        return pos;
    }

    public void setPos(Pair<Integer, Integer> pos) {
        this.pos = pos;
    }

    public void setPos(int row, int col) {
        this.pos = new Pair<>(row, col);
    }

    public int getPosCol(){
        return posCol;
    }

    public void setPosCol(int posCol) {
        this.posCol = posCol;
    }

    public int getPosRow(){
        return posRow;
    }

    public void setPosRow(int posRow) {
        this.posRow = posRow;
    }

    public String getUrlImage() {
        return this.urlImage;
    }

    public void setUrlImage(String urlImage) {
        this.urlImage = urlImage;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public boolean isReachable() {
        return isReachable;
    }

    public void setReachable(boolean reachable) {
        isReachable = reachable;
    }

    public boolean isDiscovered() {
        return discovered;
    }

    public void setDiscovered() {
        if(!this.discovered) {
            this.discovered = true;
            needViewActualisation = true;
        }
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    @Override
    public String toString() {
        return " ";
    }

    // METHODS
    protected abstract Parcel metamorphosisField(int posRow, int posCol);
    protected abstract Parcel qtEmpty();
    public abstract void extract();
}
