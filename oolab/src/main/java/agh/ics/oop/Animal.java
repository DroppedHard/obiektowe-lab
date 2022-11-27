package agh.ics.oop;

import java.util.ArrayList;
import java.util.List;

public class Animal extends AbstractWorldMapElement {

    private final IWorldMap worldMap;
    private MapDirection facing = MapDirection.NORTH;
    private final List<IPositionChangeObserver> observers = new ArrayList<>();

    public Animal(IWorldMap map, Vector2d initialPosition){
        super(initialPosition);
        this.worldMap = map;
        addObserver((IPositionChangeObserver) map);
        worldMap.place(this);
    }

    @Override
    public String toString() {
        return facing.toString();
    }

    void move(MoveDirection direction){
        switch (direction){
            case LEFT -> facing = facing.previous();
            case RIGHT -> facing = facing.next();
            case FORWARD -> {
                Vector2d newPos = position.add(this.facing.toUnitVector());
                if (this.worldMap.canMoveTo(newPos)) {
                    eatIfGrass(newPos);
                    positionChanged(newPos);
                    this.worldMap.remove(this.position);
                    position = newPos;
                    this.worldMap.place(this);
                }
            }
            case BACKWARD -> {
                Vector2d newPos = position.substract(this.facing.toUnitVector());
                if (this.worldMap.canMoveTo(newPos)) {
                    eatIfGrass(newPos);
                    positionChanged(newPos);
                    this.worldMap.remove(this.position);
                    position = newPos;
                    this.worldMap.place(this);
                }
            }
        }
    }
    void eatIfGrass(Vector2d newPos) {
        if (this.worldMap.objectAt(newPos) != null && this.worldMap.objectAt(newPos).getClass() == Grass.class) {
            this.worldMap.remove(newPos);
            // zwierzak zjadł trawę
        }
    }
    void addObserver(IPositionChangeObserver observer){observers.add(observer);
    }
    void removeObserver(IPositionChangeObserver observer) {observers.remove(observer);}
    void positionChanged(Vector2d newPos) {
        for (IPositionChangeObserver obs: observers) {
            obs.positionChanged(this.position, newPos);
        }
    }
}
