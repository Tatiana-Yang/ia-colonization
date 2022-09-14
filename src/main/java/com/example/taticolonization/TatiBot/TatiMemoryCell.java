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
 * @file TatiMemoryCell.java
 * @author Thomas FILLION (thomas.fillion@ecole.ensicaen.fr)
 * @author Mathilde LERAY (mathilde.leray@ecole.ensicaen.fr)
 * @author Tatiana YANG (tatiana.yang@ecole.ensicaen.fr)
 * @brief This file permit to memorize the parcel discovered
 * @version 1.0
 * @date 2022-03-06
 *
 * @copyright Copyright (c) 2021
 *
 */

package com.example.taticolonization.TatiBot;

import com.example.taticolonization.Tools.QLearning.Qvalues;
import com.example.taticolonization.planet.Entity.Parcel;

public class TatiMemoryCell {
    private Parcel parcel;
    private Integer lastViste;
    private Qvalues qvalues;
    private boolean hasChanged;

    public TatiMemoryCell(Parcel parcel, Integer lastViste, Qvalues qvalues, boolean hasChanged) {
        this.parcel = parcel;
        this.lastViste = lastViste;
        this.qvalues = qvalues;
        this.hasChanged = hasChanged;
    }

    public Parcel getParcel() {
        return parcel;
    }

    public void setParcel(Parcel parcel) {

        if(this.parcel != null && parcel.getType() != this.parcel.getType()){
            setHasChanged(true);
        }
        this.parcel = parcel;
    }

    public Integer getLastViste() {
        return lastViste;
    }

    public void setLastViste(Integer lastViste) {
        this.lastViste = lastViste;
    }

    public Qvalues getQvalues() {
        return qvalues;
    }

    public void setQvalues(Qvalues qvalues) {
        this.qvalues = qvalues;
    }

    public boolean HasChanged() {
        return hasChanged;
    }

    public void setHasChanged(boolean hasChanged) {
        this.hasChanged = hasChanged;
    }

    public void incrementLastVisit() {
        lastViste+=1;
    }

    @Override
    public String toString() {
        if(parcel != null){
            return parcel.toString();
        }else{
            return " ";
        }
    }
}
