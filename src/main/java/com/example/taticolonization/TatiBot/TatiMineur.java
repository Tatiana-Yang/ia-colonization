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
 * @file TatiRecolteur.java
 * @author Thomas FILLION (thomas.fillion@ecole.ensicaen.fr)
 * @author Mathilde LERAY (mathilde.leray@ecole.ensicaen.fr)
 * @author Tatiana YANG (tatiana.yang@ecole.ensicaen.fr)
 * @brief This file permit to get food on the parcel
 * @version 1.0
 * @date 2022-03-06
 *
 * @copyright Copyright (c) 2021
 *
 */

package com.example.taticolonization.TatiBot;

import com.example.taticolonization.environment.Environment;
import com.example.taticolonization.planet.Entity.Mineral;
import com.example.taticolonization.planet.Entity.Parcel;
import com.example.taticolonization.planet.Entity.Planet;
import javafx.scene.image.Image;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;

public class TatiMineur extends ITatiOuvrier{
    private Image avatar = new Image(new FileInputStream("src/main/resources/pictures/miner.png"));;
    private ArrayList<ArrayList<Integer>> path;
    private boolean charged;

    public TatiMineur() throws FileNotFoundException {
    }

    @Override
    public Image getAvatar() {
        return avatar;
    }

    @Override
    public void move() throws FileNotFoundException {
        if(getTurn_to_wait()<=0) {
            Parcel currentPlace = Environment.getInstance().getPlanet().getParcel(getRow(), getCol());
            Planet planet = Environment.getInstance().getPlanet();
            if(currentPlace instanceof Mineral){
                currentPlace.extract();
                ((Mineral)currentPlace).extractionExo(planet);
                //METAMORPHOSE NON FONCTIONNELLE, DECOMMENTER POUR ACTIVER
                //Environment.getInstance().getPlanet().updatePlanet(getRow(), getCol());
            }
            if(this.path ==null || this.path.isEmpty()){
                if(charged){
                    Parcel base = Environment.getInstance().getPlanet().getParcel(10, 10);
                    this.path = TatiCentralisateur.getInstance().getFlyingAStar().computeBestWay(currentPlace, base);
                    charged=false;
                    //Sinon, direction vers le minerai le plus proche/moins dangereux
                }else{
                    ArrayList<Parcel> minerals = TatiCentralisateur.getInstance().getMineralEmplacement();
                    for (int i = 0; i < minerals.size(); i++) {
                        ArrayList<ArrayList<Integer>> possibleWay = TatiCentralisateur.getInstance().getWalkingAstar().computeBestWay(currentPlace, minerals.get(i));
                        //On selectionne le chemin le plus court parmis ceux proposés
                        //Ce n'est pas forcement la meilleur solution, il faudrait plutot prendre le chemin que AStar considère comme le moins couteux
                        if (this.path == null || this.path.isEmpty() || possibleWay.size() < path.size()) {
                            this.path = possibleWay;
                            charged=true;
                        }
                    }
                }
                if(this.path !=null && !this.path.isEmpty()) {
                    Collections.reverse(path);
                }
            }else{
                this.setCol(path.get(0).get(1));
                this.setRow(path.get(0).get(0));
                path.remove(0);
            }
        }else{
            setTurn_to_wait(getTurn_to_wait()-1);
        }
    }

    @Override
    public void moveCorrect() throws FileNotFoundException {

    }
}
