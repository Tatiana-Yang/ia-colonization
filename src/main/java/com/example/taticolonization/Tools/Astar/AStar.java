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
 * @file AStar.java
 * @author Thomas FILLION (thomas.fillion@ecole.ensicaen.fr)
 * @author Mathilde LERAY (mathilde.leray@ecole.ensicaen.fr)
 * @author Tatiana YANG (tatiana.yang@ecole.ensicaen.fr)
 * @brief This file is the A* search
 * @version 1.0
 * @date 2022-03-06
 *
 * @copyright Copyright (c) 2021
 *
 */

package com.example.taticolonization.Tools.Astar;

import com.example.taticolonization.TatiBot.TatiCentralisateur;
import com.example.taticolonization.environment.Environment;
import com.example.taticolonization.planet.Entity.Parcel;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Queue;

public class AStar {

    private final float damageImportance;
    private final boolean flying;
    private boolean diagonal=false;

    public AStar(float damageImportance, boolean diagonal, boolean flying){
        this.damageImportance =damageImportance;
        this.diagonal=diagonal;
        this.flying=flying;
    }

    public ArrayList<ArrayList<Integer>> computeBestWay(Parcel start, Parcel end) throws FileNotFoundException {
        AStarNode endNode = new AStarNode(end);
        ArrayList<ArrayList<Integer>> closedList = new ArrayList<>();
        Queue<AStarNode> openList = new PriorityQueue<>(new NodeComparator());
        AStarNode as = new AStarNode(start);
        Double cost=0.0;
        //System.out.println(start.getCol().toString() + " " + start.getRow().toString());
        //System.out.println(as);
        openList.add(as);
        while(!openList.isEmpty()){
            AStarNode u = openList.poll();
            if(u.getCol()==end.getCol() && u.getRow()==end.getRow()){
                return getWay(u);
            }
            ArrayList<AStarNode> g = getNeighboors(u);
            for(int i=0;i<g.size();i++){
                AStarNode v = g.get(i);
                ArrayList<Integer> coordneigboor = new ArrayList<>();
                coordneigboor.add(v.getCol());
                coordneigboor.add(v.getRow());

                if(!(closedList.contains(coordneigboor))){
                    if(flying){
                        //SI le robot vole, pas de dommage, on ne prend pas en compte les qvalues
                        cost=1.0;
                    }else{
                        //Si il ne vole pas, il peut prendre des dommages, on regarde donc les qvalues
                        cost= 1+ TatiCentralisateur.getInstance().getWorldSave().get(v.getRow()).get(v.getCol()).getQvalues().sumValues();
                    }
                    v.setCost(u.getCost()+cost);
                    v.setHeuristic(distance(v,endNode));
                    openList.add(v);
                }
            }
            ArrayList<Integer> coordsU = new ArrayList<>();
            coordsU.add(u.getCol());
            coordsU.add(u.getRow());
            closedList.add(coordsU);
        }
        System.out.println("RETOUR NUL");
        return null;
    }

    private ArrayList<ArrayList<Integer>> getWay(AStarNode u) {
        ArrayList<ArrayList<Integer>> way = new ArrayList<>();
        while(u.getFather()!=null){
            ArrayList<Integer> coordsU = new ArrayList<>();
            coordsU.add(u.getCol());
            coordsU.add(u.getRow());
            way.add(coordsU);
            u=u.getFather();
        }
        return way;
    }

    static public double sqr(double a) {
        return a*a;
    }

    private double distance(AStarNode u, AStarNode v){
        return Math.sqrt(sqr(v.getRow() - u.getRow()) + sqr(v.getCol() - u.getCol()));
    }

    private ArrayList<AStarNode> getNeighboors(AStarNode u) throws FileNotFoundException {
        ArrayList<AStarNode> res = new ArrayList<>();
        for(int i=-1;i<=1;i++) {
            for (int j = -1; j <= 1; j++) {
                if (u.getRow() + i < Environment.getInstance().getPlanet().getHeight() &&
                        u.getCol() + j < Environment.getInstance().getPlanet().getWidth() &&
                        u.getRow() + i >= 0 && u.getCol() + j >= 0) {
                    //Si le deplacement en diagnonal est autorisé ou que le voisin n'est pas en diagonale
                    if(diagonal || (i+j != -2 && i+j !=2 && i+j!=0)){
                        //Si le bot peut passer les zones infranchissables ( car il vole ) ou que ce n'est pas une zone infranchissable, ou un lac
                        if(flying || (Environment.getInstance().getPlanet().getParcel(u.getCol() + j, u.getRow() + i).getType()!=10 && Environment.getInstance().getPlanet().getParcel(u.getCol() + j, u.getRow() + i).getType()!=7) ){
                            AStarNode as = new AStarNode(Environment.getInstance().getPlanet().getParcel(u.getCol() + j, u.getRow() + i));
                            as.setFather(u);
                            if(as.getCol()!=u.getCol() || as.getRow()!=u.getRow()){
                                res.add(as);
                            }
                        }
                    }
                }
            }
        }
        return res;
    }
}
