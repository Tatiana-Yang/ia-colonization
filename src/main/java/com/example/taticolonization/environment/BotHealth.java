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
 * @file BotHealth.java
 * @author Thomas FILLION (thomas.fillion@ecole.ensicaen.fr)
 * @author Mathilde LERAY (mathilde.leray@ecole.ensicaen.fr)
 * @author Tatiana YANG (tatiana.yang@ecole.ensicaen.fr)
 * @brief This file permit to calculate the health of the robot
 * @version 1.0
 * @date 2022-03-06
 *
 * @copyright Copyright (c) 2021
 *
 */
package com.example.taticolonization.environment;

import com.example.taticolonization.planet.Entity.ImpassableZone;
import com.example.taticolonization.planet.Entity.Mineral;
import com.example.taticolonization.planet.Entity.Parcel;
import com.example.taticolonization.planet.Entity.Rock;
import net.sourceforge.jFuzzyLogic.FIS;
import net.sourceforge.jFuzzyLogic.FunctionBlock;
import net.sourceforge.jFuzzyLogic.rule.Variable;

public class BotHealth {
    private FunctionBlock functionBlock;
    private Variable robotHealthValue;

    public BotHealth() {
        functionBlock = loadFisFile();
    }

    private FunctionBlock loadFisFile() {
        String filename = "conf/bot.fcl";
        FIS fis = FIS.load(filename, true);

        if (fis == null) {
            System.err.println("Impossible de charger le fichier '" + filename + "'");
            System.exit(1);
        }
        return fis.getFunctionBlock(null);
    }

    public double calculateHealth(double metamorphosisValue, Parcel field) {
        functionBlock.setVariable("metamorphosis", metamorphosisValue);
        double fieldValue = impassableZone(field);
        functionBlock.setVariable("field", fieldValue);

        functionBlock.evaluate();
        robotHealthValue = functionBlock.getVariable("robotHealth");
        //System.out.println("Calculated Robot Health " + robotHealthValue.getValue());
        return robotHealthValue.getValue();
    }

    public double impassableZone(Parcel field){
        if(field instanceof ImpassableZone){
            return 100;
        }else if (field instanceof Rock){
            return 80;
        }else if (field instanceof Mineral){
            return 50;
        }else{
            return 20;
        }
    }

}
