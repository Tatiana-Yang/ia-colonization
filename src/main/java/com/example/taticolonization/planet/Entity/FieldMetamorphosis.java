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
 * @file FieldMetamorphosis.java
 * @author Thomas FILLION (thomas.fillion@ecole.ensicaen.fr)
 * @author Mathilde LERAY (mathilde.leray@ecole.ensicaen.fr)
 * @author Tatiana YANG (tatiana.yang@ecole.ensicaen.fr)
 * @brief This file permit to create a parcel of our exosquelette like lake or mineral
 * @version 1.0
 * @date 2022-03-06
 *
 * @copyright Copyright (c) 2021
 *
 */
package com.example.taticolonization.planet.Entity;

import java.util.ArrayList;

public abstract class FieldMetamorphosis extends Parcel{
    protected int amplitude;
    protected int speed;
    protected double qtExtractParcel = 0;

    protected FieldMetamorphosis(int x, int y) {
        super(x, y);
    }

    public int getAmplitude() {
        return amplitude;
    }

    public void setAmplitude(int amplitude) {
        this.amplitude = amplitude;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public void extractionExo(Planet planet){
        if(this instanceof Mineral){
            double newQuantiteExtract = planet.getExtractMineral() + 2;
            planet.setExtractMineral(newQuantiteExtract);
        }
        else if(this instanceof Lake){
            double newQuantiteExtract = planet.getExtractWater() + 1000;
            planet.setExtractWater(newQuantiteExtract);
        }
        propagation();
        int posCol = this.getPosCol();
        int posRow = this.getPosRow();
        int distance = this.amplitude * this.speed;
        ArrayList<Parcel> listParcel = planet.parcelsToMetamorphosis(distance, posCol, posRow);

        for(Parcel p : listParcel) {
            Parcel newParcel = p.metamorphosisField(p.getPosCol(), p.getPosRow());
            planet.setParcel(p.getPosRow(), p.getPosCol(), newParcel);
        }
    }

    public abstract void propagation();

}
