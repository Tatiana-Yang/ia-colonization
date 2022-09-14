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
 * @file ITatiBot.java
 * @author Thomas FILLION (thomas.fillion@ecole.ensicaen.fr)
 * @author Mathilde LERAY (mathilde.leray@ecole.ensicaen.fr)
 * @author Tatiana YANG (tatiana.yang@ecole.ensicaen.fr)
 * @brief This file is an abstract class of bot it is the super class of all the robots
 * @version 1.0
 * @date 2022-03-06
 *
 * @copyright Copyright (c) 2021
 *
 */

package com.example.taticolonization.TatiBot;

import com.example.taticolonization.environment.BotHealth;
import com.example.taticolonization.environment.Environment;
import com.example.taticolonization.environment.Metamorphosis;
import com.example.taticolonization.planet.Entity.Planet;
import javafx.scene.image.Image;

import java.io.FileNotFoundException;

public abstract class ITatiBot {
    private Integer health;
    private Integer turn_to_wait=0;
    private Integer row = 10;
    private Integer col = 10;
    private boolean broken = false;
    private boolean isDie = false;
    private int timeRepair = 0;
    private int turnPerCell = 1; //Le nb de case qu'il peut avancé par tour

    public Integer getTurn_to_wait() {
        return turn_to_wait;
    }

    public void setTurn_to_wait(Integer turn_to_wait) {
        this.turn_to_wait = turn_to_wait;
    }


    public Integer getRow() {
        return row;
    }

    public void setRow(Integer row) {
        this.row = row;
    }

    public Integer getCol() {
        return col;
    }

    public void setCol(Integer col) {
        this.col = col;
    }

    public void setDie(boolean die) {
        isDie = die;
    }

    public int getTimeRepair() {
        return timeRepair;
    }

    public void setTimeRepair(int timeRepair) {
        this.timeRepair = timeRepair;
    }

    public void robotHealth() throws FileNotFoundException {
        BotHealth health = new BotHealth();
        Metamorphosis pC = new Metamorphosis();
        Planet planet = Environment.getInstance().getPlanet();
        double metamorphosisValue = pC.calculateMetamorphosis(planet.percentExtractMineral(), (planet.getExtractWater() / 1000)); //should not be value that we insert but the qt of extraction
        double botHealth = health.calculateHealth(metamorphosisValue, Environment.getInstance().getPlanet().getParcel(getRow(), getCol()));
        if (botHealth >= 45 && botHealth < 60) {
            this.timeRepair = 2;
            this.turnPerCell = 2;
            this.broken = true;
        } else if (botHealth >= 30 && botHealth < 45) {
            this.turnPerCell = 3;
            this.timeRepair = 7;
            this.broken = true;
        } else if (botHealth >= 15 && botHealth < 30) {
            this.turnPerCell = 5;
            this.timeRepair = 7 * 4;
            this.broken = true;
        } else if (botHealth < 15) {
            this.isDie = true;
        }
    }

    public abstract Image getAvatar();

    public abstract void move() throws FileNotFoundException;

    public abstract void moveCorrect() throws FileNotFoundException;
}
