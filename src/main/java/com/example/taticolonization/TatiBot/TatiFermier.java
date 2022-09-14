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
 * @file TatiFermier.java
 * @author Thomas FILLION (thomas.fillion@ecole.ensicaen.fr)
 * @author Mathilde LERAY (mathilde.leray@ecole.ensicaen.fr)
 * @author Tatiana YANG (tatiana.yang@ecole.ensicaen.fr)
 * @brief This file permit to create robot who cultivate the field
 * @version 1.0
 * @date 2022-03-06
 *
 * @copyright Copyright (c) 2021
 *
 */

package com.example.taticolonization.TatiBot;

import javafx.scene.image.Image;
import javafx.util.Pair;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class TatiFermier extends ITatiBot{
    private Image avatar = null;

    @Override
    public Image getAvatar() {
        return avatar;
    }

    @Override
    public void move() {
    }

    @Override
    public void moveCorrect() throws FileNotFoundException {

    }
}
