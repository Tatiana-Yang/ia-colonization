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
 * @file TatiCentralisateur.java
 * @author Thomas FILLION (thomas.fillion@ecole.ensicaen.fr)
 * @author Mathilde LERAY (mathilde.leray@ecole.ensicaen.fr)
 * @author Tatiana YANG (tatiana.yang@ecole.ensicaen.fr)
 * @brief This file permit to create the centralize robot
 * @version 1.0
 * @date 2022-03-06
 *
 * @copyright Copyright (c) 2021
 *
 */

package com.example.taticolonization.TatiBot;

import com.example.taticolonization.Tools.Astar.AStar;
import com.example.taticolonization.Tools.QLearning.QLearningAgent;
import com.example.taticolonization.Tools.QLearning.Qvalues;
import com.example.taticolonization.environment.Environment;
import com.example.taticolonization.planet.Entity.*;
import javafx.scene.image.Image;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class TatiCentralisateur extends ITatiBot{
    private Image avatar = new Image(new FileInputStream("src/main/resources/pictures/tatibot.png"));

    //Vision du monde selon le centralisateur
    private ArrayList<ArrayList<TatiMemoryCell>> worldSave;
    private AStar FlyingAStar = new AStar(1, true, true);
    private AStar WalkingAstar = new AStar(1, true, false);

    public AStar getFlyingAStar() {
        return FlyingAStar;
    }

    public AStar getWalkingAstar() {
        return WalkingAstar;
    }

    private static TatiCentralisateur instance;

    static {
        try {
            instance = new TatiCentralisateur();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public TatiCentralisateur() throws FileNotFoundException {
    }


    public static TatiCentralisateur getInstance() throws FileNotFoundException {
        if (instance == null) {
            instance = new TatiCentralisateur();
        }
        return instance;
    }

    public ArrayList<Parcel> getFoodEmplacement(){
        ArrayList<Parcel> res =  new ArrayList<>();
        for(int i=0;i<worldSave.size();i++){
            for(int j=0; j<worldSave.get(i).size(); j++){
                if(worldSave.get(i).get(j).getParcel() instanceof Food){
                    if(worldSave.get(i).get(j).getParcel().getQuantite()>0){
                        res.add(worldSave.get(i).get(j).getParcel());
                    }
                }
            }
        }
        return res;
    }

    public ArrayList<Parcel> getMineralEmplacement(){
        ArrayList<Parcel> res =  new ArrayList<>();
        for(int i=0;i<worldSave.size();i++){
            for(int j=0; j<worldSave.get(i).size(); j++){
                if(worldSave.get(i).get(j).getParcel() instanceof Mineral){
                    if(worldSave.get(i).get(j).getParcel().getQuantite()>0){
                        res.add(worldSave.get(i).get(j).getParcel());
                    }
                }
            }
        }
        return res;
    }


    private void init_mind() throws FileNotFoundException {
        worldSave = new ArrayList<>();
        for(int i = 0; i < Environment.getInstance().getPlanet().getHeight(); i++) {
            ArrayList<TatiMemoryCell> pRow = new ArrayList<>();
            for(int j = 0; j < Environment.getInstance().getPlanet().getWidth(); j++) {
                pRow.add(new TatiMemoryCell(null, 1000,new Qvalues(),false));
            }
            worldSave.add(pRow);
        }
    }

    public void update_Centralized_memory(int row, int col, Parcel parcel) throws FileNotFoundException {
        if(worldSave == null){
            init_mind();
        }
        worldSave.get(row).get(col).setParcel(parcel);
    }

    public void update_visited(int row, int col){
        worldSave.get(row).get(col).setLastViste(0);
    }

    public ArrayList<ArrayList<TatiMemoryCell>> getWorldSave() {
        return worldSave;
    }

    @Override
    public Image getAvatar() {
        return avatar;
    }

    @Override
    public void move() throws FileNotFoundException {
        if(worldSave==null){
           init_mind();
        }
        //On parcourt la mémoire
        for(int i=0;i<worldSave.size();i++) {
            for (int j = 0; j < worldSave.get(i).size(); j++) {
                //Si la case a changé par rapport à celle en mémoire, alors cela veut dire qu'il y a eu un changement
                //Ce qui veut dire un potentiel dommage !
                if(worldSave.get(i).get(j).HasChanged()){
                    //On place donc une recompense à -10 sur cette case
                    worldSave.get(i).get(j).getQvalues().setReward(-10.0);
                    worldSave.get(i).get(j).setHasChanged(false);
                }
            }
        }
        //On lance ensuite un agent virtuel qui explore la memoire et mets à jour les Qvalues
        QLearningAgent ql = new QLearningAgent(1000,null, 10,10);
        ql.explore_with_no_goal();
    }

    public void printMind(){
        if(this.worldSave!=null){
            String acc="";
            for(int i=0;i<worldSave.size();i++){
                for(int j=0; j<worldSave.get(i).size(); j++){
                    acc+=worldSave.get(i).get(j).toString();
                }
                acc+="\n";
            }
            //System.out.println(acc);
        }
    }

    @Override
    public void moveCorrect() throws FileNotFoundException {

    }

    public void updateVisit() throws FileNotFoundException {
        if(worldSave == null){
            init_mind();
        }
        for(int i=0;i<worldSave.size();i++) {
            for (int j = 0; j < worldSave.get(i).size(); j++) {
                worldSave.get(i).get(j).incrementLastVisit();
            }
        }
    }
}
