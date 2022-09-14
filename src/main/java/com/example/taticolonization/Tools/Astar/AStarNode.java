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
 * @file AstarNode.java
 * @author Thomas FILLION (thomas.fillion@ecole.ensicaen.fr)
 * @author Mathilde LERAY (mathilde.leray@ecole.ensicaen.fr)
 * @author Tatiana YANG (tatiana.yang@ecole.ensicaen.fr)
 * @brief This file permit to create Node for A*
 * @version 1.0
 * @date 2022-03-06
 *
 * @copyright Copyright (c) 2021
 *
 */

package com.example.taticolonization.Tools.Astar;

import com.example.taticolonization.planet.Entity.Parcel;

public class AStarNode {
    private final int col;
    private final int row;
    private final Parcel parcel;
    private double heuristic=0;
    private double cost=0;
    private AStarNode father;

    public AStarNode getFather() {
        return father;
    }

    @Override
    public String toString() {
        return "AStarNode{" +
                "col=" + col +
                ", row=" + row +
                ", heuristic=" + heuristic +
                ", cost=" + cost +
                '}';
    }

    public void setFather(AStarNode father) {
        this.father = father;
    }

    public Parcel getParcel() {
        return parcel;
    }

    public double getHeuristicAndCost(){
        return heuristic+cost;
    }

    public void setHeuristic(double heuristic) {
        this.heuristic = heuristic;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public double getHeuristic() {
        return heuristic;
    }

    public double getCost() {
        return cost;
    }

    public Integer getCol() {
        return col;
    }

    public int getRow() {
        return row;
    }

    public AStarNode(Parcel parcel){
        this.col =parcel.getCol();
        this.row =parcel.getRow();
        this.parcel=parcel;
    }
}
