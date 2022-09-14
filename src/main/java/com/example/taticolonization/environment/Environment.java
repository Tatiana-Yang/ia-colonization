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
 * @file Environment.java
 * @author Thomas FILLION (thomas.fillion@ecole.ensicaen.fr)
 * @author Mathilde LERAY (mathilde.leray@ecole.ensicaen.fr)
 * @author Tatiana YANG (tatiana.yang@ecole.ensicaen.fr)
 * @brief This file permit to initialize our planet with the different agent
 * @version 1.0
 * @date 2022-03-06
 *
 * @copyright Copyright (c) 2021
 *
 */

package com.example.taticolonization.environment;

import com.example.taticolonization.TatiBot.TatiExplorateur;
import com.example.taticolonization.TatiBot.TatiCentralisateur;
import com.example.taticolonization.TatiBot.TatiMineur;
import com.example.taticolonization.TatiBot.TatiRecolteur;
import com.example.taticolonization.planet.Entity.Planet;
import javafx.scene.layout.GridPane;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public class Environment {
    private final Planet planet;
    private final ArrayList<Agent> agents;
    private static Environment instance;

    public Planet getPlanet() {
        return planet;
    }

    public Environment(Planet planet, GridPane root) throws FileNotFoundException {
        this.planet = planet;
        this.agents = new ArrayList<>();
        this.init();
    }

    public static Environment getInstance() throws FileNotFoundException {
        if (instance == null) {
            instance = new Environment(new Planet(),null);
        }
        return instance;
    }

    private void init() throws FileNotFoundException {
        TatiExplorateur explorateur1 = new TatiExplorateur();
        TatiExplorateur explorateur2 = new TatiExplorateur();
        TatiMineur mineur1 = new TatiMineur();
        TatiRecolteur recolteur1 = new TatiRecolteur();
        TatiCentralisateur centralisateur = new TatiCentralisateur();
        agents.add(new Agent(explorateur1,10,10));
        agents.add(new Agent(explorateur2,10,10));
        agents.add(new Agent(mineur1,10,10));
        agents.add(new Agent(centralisateur,10,10));
        agents.add(new Agent(recolteur1, 10, 10));
    }

    public void triggerAgent() throws FileNotFoundException {
        for(int i = 0; i < agents.size(); i++) {
            agents.get(i).trigger();
        }
    }

    public ArrayList<Agent> getAgents() {
        return agents;
    }

    @Override
    public String toString() {
        return planet.toString();
    }


}
