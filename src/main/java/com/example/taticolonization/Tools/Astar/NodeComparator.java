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
 * @file NodeComparator.java
 * @author Thomas FILLION (thomas.fillion@ecole.ensicaen.fr)
 * @author Mathilde LERAY (mathilde.leray@ecole.ensicaen.fr)
 * @author Tatiana YANG (tatiana.yang@ecole.ensicaen.fr)
 * @brief This file permit to compare A* node
 * @version 1.0
 * @date 2022-03-06
 *
 * @copyright Copyright (c) 2021
 *
 */

package com.example.taticolonization.Tools.Astar;

import java.util.Comparator;

public class NodeComparator implements Comparator<AStarNode> {
    @Override
    public int compare(AStarNode o1, AStarNode o2) {
        if(o1.getHeuristicAndCost()<o2.getHeuristicAndCost()){
            return -1;
        }else if(o1.getHeuristicAndCost()==o2.getHeuristicAndCost()){
            return 0;
        }else{
            return 1;
        }
    }
}
