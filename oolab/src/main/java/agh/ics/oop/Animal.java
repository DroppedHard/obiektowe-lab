package agh.ics.oop;
import javafx.scene.image.Image;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class Animal extends AbstractWorldMapElement implements IMapElement{
    private final Image imgUp = new Image(new FileInputStream("src/main/resources/cat-up.png"));
    private final Image imgdown = new Image(new FileInputStream("src/main/resources/cat-down.png"));
    private final Image imgLeft = new Image(new FileInputStream("src/main/resources/cat-left.png"));
    private final Image imgRight = new Image(new FileInputStream("src/main/resources/cat-right.png"));
    private final IWorldMap worldMap;
    public MapDirection facing = MapDirection.NORTH;
    private final List<IPositionChangeObserver> observers = new ArrayList<>();

    public Animal(IWorldMap map, Vector2d initialPosition) throws FileNotFoundException {
        super(initialPosition);
        this.worldMap = map;
        worldMap.place(this);
    }

    @Override
    public String toString() {
        return facing.toString();
    }

    public void move(MoveDirection direction) throws FileNotFoundException {
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
    void eatIfGrass(Vector2d newPos) throws FileNotFoundException {
        if (this.worldMap.objectAt(newPos) != null && this.worldMap.objectAt(newPos).getClass() == Grass.class) {
            this.worldMap.remove(newPos);
            // zwierzak zjadł trawę
        }
    }
    void addObserver(IPositionChangeObserver observer){observers.add(observer);}
    void removeObserver(IPositionChangeObserver observer) {observers.remove(observer);}
    void positionChanged(Vector2d newPos) {
        for (IPositionChangeObserver obs: observers) {
            obs.positionChanged(this.position, newPos);
        }
    }
    @Override
    public Image getImg() throws IllegalArgumentException {
        switch (this.facing) {
            case NORTH -> {return imgUp;}
            case SOUTH -> {return imgdown;}
            case WEST -> {return imgLeft;}
            case EAST -> {return imgRight;}
            default -> throw new IllegalStateException("Unexpected value: " + this.facing);
        }
    }

    @Override
    public String getLabel() {
        return "Kot " + position;
    }
}
