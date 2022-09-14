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
 * @file ITatiOuvrier.java
 * @author Thomas FILLION (thomas.fillion@ecole.ensicaen.fr)
 * @author Mathilde LERAY (mathilde.leray@ecole.ensicaen.fr)
 * @author Tatiana YANG (tatiana.yang@ecole.ensicaen.fr)
 * @brief This file is the super class of robot who extract mineral or water
 * @version 1.0
 * @date 2022-03-06
 *
 * @copyright Copyright (c) 2021
 *
 */

package com.example.taticolonization.TatiBot;

import com.example.taticolonization.environment.Environment;
import com.example.taticolonization.planet.Entity.Parcel;
import javafx.scene.image.Image;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public class ITatiOuvrier extends ITatiBot{


    @Override
    public Image getAvatar() {
        return null;
    }

    @Override
    public void move() throws FileNotFoundException {

    }

    @Override
    public void moveCorrect() throws FileNotFoundException {

    }

    public boolean isOutofBound(int row, int col) throws FileNotFoundException {
        if(col> Environment.getInstance().getPlanet().getHeight() ||
                row>Environment.getInstance().getPlanet().getWidth() ||
                col<0 || row<0 )
            return true;
        else
            return false;
    }
}
