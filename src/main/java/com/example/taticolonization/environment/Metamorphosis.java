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
 * @file Metamorphosis.java
 * @author Thomas FILLION (thomas.fillion@ecole.ensicaen.fr)
 * @author Mathilde LERAY (mathilde.leray@ecole.ensicaen.fr)
 * @author Tatiana YANG (tatiana.yang@ecole.ensicaen.fr)
 * @brief This file permit to calculate the metamorphosis of the planet
 * @version 1.0
 * @date 2022-03-06
 *
 * @copyright Copyright (c) 2021
 *
 */
package com.example.taticolonization.environment;

import net.sourceforge.jFuzzyLogic.FIS;
import net.sourceforge.jFuzzyLogic.FunctionBlock;
import net.sourceforge.jFuzzyLogic.rule.Variable;

public class Metamorphosis {

    private FunctionBlock functionBlock;
    private Variable metamorphosisValue;

    public Metamorphosis() {
        functionBlock = loadFisFile();
    }

    private FunctionBlock loadFisFile() {
        String filename = "conf/planet.fcl";
        FIS fis = FIS.load(filename, true);

        if (fis == null) {
            System.err.println("Impossible de charger le fichier '" + filename + "'");
            System.exit(1);
        }
        return fis.getFunctionBlock(null);
    }

    public double calculateMetamorphosis(double mineral, double lake) {
        functionBlock.setVariable("mineral", mineral);
        functionBlock.setVariable("lake", lake);

        functionBlock.evaluate();
        metamorphosisValue = functionBlock.getVariable("metamorphosis");
        //System.out.println("Calculated Matamorphosis " + metamorphosisValue.getValue());
        return metamorphosisValue.getValue();
    }


}

