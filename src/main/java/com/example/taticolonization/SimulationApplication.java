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
 * @file SimulationApplication.java
 * @author Thomas FILLION (thomas.fillion@ecole.ensicaen.fr)
 * @author Mathilde LERAY (mathilde.leray@ecole.ensicaen.fr)
 * @author Tatiana YANG (tatiana.yang@ecole.ensicaen.fr)
 * @brief This file permit to start the simulation
 * @version 1.0
 * @date 2022-03-06
 *
 * @copyright Copyright (c) 2021
 *
 */

package com.example.taticolonization;

import com.example.taticolonization.TatiBot.TatiCentralisateur;
import com.example.taticolonization.animation.GameLoopTimer;
import com.example.taticolonization.environment.Metamorphosis;
import com.example.taticolonization.environment.Environment;
import com.example.taticolonization.planet.Entity.Food;
import com.example.taticolonization.planet.Entity.Mineral;
import com.example.taticolonization.planet.Entity.Parcel;
import com.example.taticolonization.planet.Entity.Planet;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class SimulationApplication extends Application {

    Planet planet = new Planet();
    GridPane root = new GridPane();
    Environment environment = new Environment(planet,root);
    private int length = Environment.getInstance().getPlanet().getHeight();
    int width = environment.getPlanet().getWidth();
    int screenLength = 800;
    int screenWidth = 800;
    boolean fullActualisation = false;
    Integer compteur = 100;

    public SimulationApplication() throws FileNotFoundException {
    }

    public void render() throws FileNotFoundException {
        TatiCentralisateur.getInstance().updateVisit();
        //TatiCentralisateur.getInstance().printMind();
        // Affichage de l'environnement
        for(int row = 0; row < length; row++) {
            for(int col = 0; col < width; col++) {
                //System.out.println(compteur);
                compteur+=1;

                if(Environment.getInstance().getPlanet().getParcel(row, col).isNeedViewActualisation() || fullActualisation==true){
                    Parcel parcel = Environment.getInstance().getPlanet().getParcel(row, col);

                    Image parcelImage = new Image(new FileInputStream(parcel.getUrlImage()));
                    if(parcel instanceof Food && parcel.getQuantite()<=0){
                        parcelImage = new Image(new FileInputStream(((Food) parcel).getUrlImage0()));
                    }else if(parcel instanceof Mineral && parcel.getQuantite()<=0){
                        parcelImage = new Image(new FileInputStream(((Mineral) parcel).getUrlImage0()));
                    }

                    ImageView imageView = new ImageView(parcelImage);
                    imageView.setFitHeight(screenLength / length);
                    imageView.setFitWidth(screenWidth / width);
                    if(!parcel.isDiscovered()){
                        ColorAdjust shadow = new ColorAdjust();
                        shadow.setBrightness(-0.6);
                        imageView.setEffect(shadow);
                    }
                    root.setRowIndex(imageView, row);
                    root.setColumnIndex(imageView, col);
                    root.getChildren().add(imageView);
                    parcel.setNeedViewActualisation(false);
                }
            }
        }

        // Affichage des bots
        for(int i = 0; i < Environment.getInstance().getAgents().size(); i++) {
            Integer col = Environment.getInstance().getAgents().get(i).getCol();
            Integer row = Environment.getInstance().getAgents().get(i).getRow();

            ImageView imageView = new ImageView(Environment.getInstance().getAgents().get(i).getIa().getAvatar());
            imageView.setFitHeight(screenLength / length);
            imageView.setFitWidth(screenWidth / width);
            root.setRowIndex(imageView, row);
            root.setColumnIndex(imageView, col);
            root.getChildren().add(imageView);
        }

        if(fullActualisation){
            fullActualisation=false;
        }
    }


    @Override
    public void start(Stage stage) throws IOException {

        render();

        GameLoopTimer timer = new GameLoopTimer() {
            private double acc = 0;
            @Override
            public void tick(float secondsSinceLastFrame) throws FileNotFoundException {
                //System.out.println(secondsSinceLastFrame);
                acc += secondsSinceLastFrame;
                if(acc>0.01) {
                    if(compteur==100){
                        root.getChildren().clear();
                        fullActualisation = true;
                        compteur=0;
                    }
                    compteur+=1;
                    //System.out.println(acc);
                    acc = 0;
                    Environment.getInstance().triggerAgent();
                    render();
                }
            }
        };
        timer.start();

        Scene scene = new Scene(root, 800, 800);
        scene.setFill(Color.web("#4d634b"));
        stage.setTitle("Colonization");
        stage.setScene(scene);
        stage.show();
    }


    public static void main(String[] args) {
        launch();
    }
}