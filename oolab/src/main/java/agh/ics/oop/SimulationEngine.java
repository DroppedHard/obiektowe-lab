package agh.ics.oop;

import agh.ics.oop.gui.App;
import javafx.application.Platform;

import java.io.FileNotFoundException;

public class SimulationEngine implements IEngine, Runnable{
    public MoveDirection[] moves;
    public final Animal[] animals;
    public final IWorldMap map;
    int moveDelay = 500;
    App app;

    public SimulationEngine(MoveDirection[] moves, IWorldMap map, Vector2d[] startingPos, App app) throws FileNotFoundException {
        this.app = app;
        this.moves = moves;
        this.map = map;
        animals = new Animal[startingPos.length];
        for (int i = 0; i < startingPos.length; i++) {
            animals[i] = new Animal(map, startingPos[i]);
        }
        System.out.println(map);
    }
    @Override
    public void run() {
        int i=0;
        for (MoveDirection move: moves) {
            try {
                Thread.sleep(this.moveDelay);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                throw new RuntimeException(e.getMessage());
            }
            try {
                animals[i%animals.length].move(move);
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
            i+=1;
            System.out.println(map);
            Platform.runLater(()-> {
                this.app.grid.setGridLinesVisible(false);
                this.app.clearMap();
                this.app.centerGrid();
                this.app.grid.setGridLinesVisible(true);
                try {
                    this.app.addElements();
                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                }
            });
        }
    }
}
