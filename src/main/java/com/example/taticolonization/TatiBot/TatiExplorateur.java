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
 * @file TatiBotExplorateur.java
 * @author Thomas FILLION (thomas.fillion@ecole.ensicaen.fr)
 * @author Mathilde LERAY (mathilde.leray@ecole.ensicaen.fr)
 * @author Tatiana YANG (tatiana.yang@ecole.ensicaen.fr)
 * @brief This file permit to create robot who explore the planet
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

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class TatiExplorateur extends ITatiBot {
    private Image avatar = new Image(new FileInputStream("src/main/resources/pictures/drone.png"));
    private int fuel = 50;
    Random random = new Random();
    private ArrayList<ArrayList<Integer>> path;

    public TatiExplorateur() throws FileNotFoundException {
    }

    @Override
    public Image getAvatar() {
        return avatar;
    }

    public void informCentral() throws FileNotFoundException {
        TatiCentralisateur.getInstance().update_visited(getRow(),getCol());
        for(int i=-1;i<=1;i++){
            for(int j=-1;j<=1;j++){
                if(getRow()+i<Environment.getInstance().getPlanet().getHeight() &&
                        getCol()+j<Environment.getInstance().getPlanet().getWidth() &&
                        getRow()+i>0 && getCol()+j>0){
                    TatiCentralisateur.getInstance().update_Centralized_memory(getRow()+i,getCol()+j, Environment.getInstance().getPlanet().getParcel(getRow()+i,getCol()+j));
                }
            }
        }
    }

    public ArrayList<Integer> random_move(){
        int valueX = random.nextInt(3)-1;
        int valueY = random.nextInt(3)-1;
        ArrayList<Integer> res = new ArrayList<>();
        res.add(valueY);
        res.add(valueX);
        return res;
    }

    public ArrayList<Integer> moveBasedOnVisited() throws FileNotFoundException {
        ArrayList<ArrayList<Integer>> moves = new ArrayList<>();
        for(int i=-1;i<=1;i++) {
            for (int j = -1; j <= 1; j++) {
                if (getRow() + i < Environment.getInstance().getPlanet().getHeight() &&
                        getCol() + j < Environment.getInstance().getPlanet().getWidth() &&
                        getRow() + i >= 0 && getCol() + j >= 0) {
                    ArrayList<Integer> move = new ArrayList<>();
                    move.add(i);
                    move.add(j);
                    moves.add(move);
                }
            }
        }

        int lastVisit = 0;
        ArrayList<ArrayList<Integer>> ress = new ArrayList<>();
        for(int i=0;i<moves.size();i++){
            ArrayList<Integer> move = moves.get(i);
            int moveLastVisit = TatiCentralisateur.getInstance().getWorldSave().get(getRow() + move.get(0)).get(getCol() + move.get(1)).getLastViste();
            if(moveLastVisit>lastVisit){
                lastVisit=moveLastVisit;
                ress = new ArrayList<>();
                ArrayList<Integer> res = new ArrayList<>();
                res.add(move.get(1));
                res.add(move.get(0));
                ress.add(res);
            }else if(moveLastVisit==lastVisit){
                ArrayList<Integer> res = new ArrayList<>();
                res.add(move.get(1));
                res.add(move.get(0));
                ress.add(res);
            }
        }
        int index = (int)(Math.random() * ress.size());
        return ress.get(index);
    }

    public boolean isOutofBound(ArrayList<Integer> move) throws FileNotFoundException {
        if(move.get(0)+getCol()>Environment.getInstance().getPlanet().getHeight() ||
                move.get(1)+getRow()>Environment.getInstance().getPlanet().getWidth() ||
                move.get(0)+getCol()<0 || move.get(1)+getRow()<0 )
            return true;
        else
            return false;
    }

    @Override
    public void move() throws FileNotFoundException {
        if(getTurn_to_wait()<=0) {
            if (getCol() == 10 && getRow() == 10 && fuel<10) {
                fuel = 50;
                setTurn_to_wait(getTurn_to_wait()+3);
            }
            else if (fuel < 10) {
                if (fuel > 0) {
                    if (path == null || path.isEmpty()) {
                        Parcel currentPlace = Environment.getInstance().getPlanet().getParcel(getRow(), getCol());
                        Parcel base = Environment.getInstance().getPlanet().getParcel(10, 10);
                        this.path = TatiCentralisateur.getInstance().getFlyingAStar().computeBestWay(currentPlace, base);
                        Collections.reverse(path);
                    } else {
                        this.setCol(path.get(0).get(1));
                        this.setRow(path.get(0).get(0));
                        path.remove(0);
                    }
                }
            } else {
                ArrayList<Integer> move = moveBasedOnVisited();
                while (isOutofBound(move)) {
                    move = random_move();
                }
                this.setCol((getCol() + move.get(0)));
                this.setRow((getRow() + move.get(1)));
                fuel -= 1;
            }

        }else{
            setTurn_to_wait(getTurn_to_wait()-1);
        }
    }

    @Override
    public void moveCorrect() throws FileNotFoundException {
        informCentral();
    }
}
