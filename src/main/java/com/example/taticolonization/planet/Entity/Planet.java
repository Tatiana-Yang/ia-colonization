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
 * @file Planet.java
 * @author Thomas FILLION (thomas.fillion@ecole.ensicaen.fr)
 * @author Mathilde LERAY (mathilde.leray@ecole.ensicaen.fr)
 * @author Tatiana YANG (tatiana.yang@ecole.ensicaen.fr)
 * @brief This file permit create a planet that contains parcels
 * @version 1.0
 * @date 2022-03-06
 *
 * @copyright Copyright (c) 2021
 *
 */

package com.example.taticolonization.planet.Entity;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Planet {
    private ArrayList<ArrayList<Parcel>> parcels;
    private int width, height;
    static final int SIZE = 21;
    private double extractWater = 0;
    private double extractMineral = 0;
    private double qtInitMineral;
    private double qtInitWater;
    private Parcel colony;

    // CONSTRUCTOR
    public Planet() {
        this.width = SIZE;
        this.height = SIZE;
        this.parcels = this.initPlanet();
    }

    // GETTERS AND SETTERS
    public ArrayList<ArrayList<Parcel>> getParcels() {
        return parcels;
    }

    public void setParcels(ArrayList<ArrayList<Parcel>> parcels) {
        this.parcels = parcels;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public Parcel getParcel(int row, int col) {
        return this.parcels.get(row).get(col);
    }

    public Parcel setParcel(int row, int col, Parcel parcel) {
        return this.parcels.get(row).set(col, parcel);
    }

    public void setDiscovered(int row, int col){
        parcels.get(row).get(col).setDiscovered();
    }

    public double getExtractWater() {
        return extractWater;
    }

    public void setExtractWater(double extractWater) {
        this.extractWater = extractWater;
    }

    public double getExtractMineral() {
        return extractMineral;
    }

    public void setExtractMineral(double extractMineral) {
        this.extractMineral = extractMineral;
    }

    public double getQtInitMineral() {
        return qtInitMineral;
    }

    public void setQtInitMineral(double qtInitMineral) {
        this.qtInitMineral = qtInitMineral;
    }

    public double getQtInitWater() {
        return qtInitWater;
    }

    public void setQtInitWater(double qtInitWater) {
        this.qtInitWater = qtInitWater;
    }

    public Parcel getColony() {
        return colony;
    }

    @Override
    public String toString() {
        String acc = "";
        for(int row = 0; row < height; row++){
            for(int col = 0; col < width; col++){
                acc += this.parcels.get(row).get(col).toString() + " ";
            }
            acc += "\n";
        }
        return acc;
    }

    // METHODS
    public ArrayList<Parcel> parcelsToMetamorphosis(int distance, int posRow, int posCol) {
        ArrayList<Parcel> listParcel = new ArrayList<>();
        for(int dRow = posRow - distance; dRow <= posRow + distance; dRow++) {
            for(int dCol = posCol - distance; dCol <= posCol + distance; dCol++) {
                if(dRow != posRow && dCol != posCol && dRow >= 0 && dCol >= 0 && dRow < 21 && dCol < 21) {
                    listParcel.add(getParcel(dRow, dCol));
                }
            }
        }
        return listParcel;
    }

    public ArrayList<ArrayList<Integer>> getExoConf() {
        ArrayList<ArrayList<Integer>> records = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("./conf/planetInit.csv"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] l = line.split(",");
                ArrayList<Integer> values = new ArrayList<>();
                for(String s : l) {
                    values.add(Integer.parseInt(s));
                }
                records.add(values);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return records;
    }

    public ArrayList<ArrayList<Parcel>> initPlanet() {
        ArrayList<ArrayList<Parcel>> p = new ArrayList<>();
        ArrayList<ArrayList<Integer>> exo = getExoConf();
        for(int row = 0; row < this.height; row++) {
            ArrayList<Parcel> pRow = new ArrayList<>();
            for(int col = 0; col < this.width; col++) {
                Parcel parcel;
                switch (exo.get(row).get(col)) {
                    case 0:
                        parcel = new Colony(row, col);
                        this.colony = parcel;
                        break;
                    case 1:
                        parcel = new Mineral(row, col);
                        setQtInitMineral(getQtInitMineral() + parcel.getQuantite());
                        break;
                    case 2:
                        parcel = new Rock(row, col);
                        break;
                    case 3:
                        parcel = new Forest(row, col);
                        break;
                    case 4:
                        parcel = new Grassland(row, col,4);
                        break;
                    case 5:
                        parcel = new Grassland(row, col,5);
                        break;
                    case 6:
                        parcel = new Grassland(row, col,6);
                        break;
                    case 7:
                        parcel = new Lake(row, col);
                        setQtInitWater(getQtInitWater() + parcel.getQuantite());
                        break;
                    case 8:
                        parcel = new Desert(row, col);
                        break;
                    case 9:
                        parcel = new Food(row, col);
                        break;
                    default:
                        parcel = new ImpassableZone(row, col);
                }
                pRow.add(parcel);
            }
            p.add(pRow);
        }
        return p;
    }

    //TEST metamorphosis
    public void updatePlanet(){
        for(int row = 0; row < this.height; row++) {
            for(int col = 0; col < this.width; col++) {
                Parcel p = getParcel(row, col);
                if(p instanceof FieldMetamorphosis){
                    ((FieldMetamorphosis)p).extractionExo(this);
                }
            }
        }
    }

    public double percentExtractMineral() {
        return (getExtractMineral() * 100) / getQtInitMineral();
    }

    public void updatePlanet(int row, int col) {
        if(getParcel(row, col) instanceof Mineral) {
            //((Mineral) p).extract(60, this);
            Mineral newParcel = (Mineral) getParcel(row, col);
            newParcel.extractionExo(this);
            setParcel(row, col, newParcel);
        }
        else if(getParcel(row, col) instanceof Lake) {
            //((Lake) p).extract(70, this);
            Lake newParcel = (Lake) getParcel(row, col);
            newParcel.extractionExo(this);
            setParcel(row, col, newParcel);
        }
    }
}