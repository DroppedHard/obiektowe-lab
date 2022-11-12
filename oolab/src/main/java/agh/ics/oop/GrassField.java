package agh.ics.oop;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.sqrt;

public class GrassField extends AbstractWorldMap implements IWorldMap{
    public final List<AbstractWorldMapElement> things = new ArrayList<>();
    private final int n;
    public GrassField(int n){
        this.n = n;
        lowerLeft = new Vector2d(Integer.MAX_VALUE,Integer.MAX_VALUE);
        upperRight = new Vector2d(-Integer.MAX_VALUE,-Integer.MAX_VALUE);
        addGrass(n);
    }
    @Override
    public boolean canMoveTo(Vector2d position) {
        for (AbstractWorldMapElement rzecz: things) {
            if (rzecz.position.equals(position)){
                return rzecz.getClass() != Animal.class;
            }
        }
        return true;
    }

    @Override
    public boolean place(Animal animal) {
        if (isOccupied(animal.position)){
            this.remove(animal.position);
        }
        things.add(0, animal);
        this.adaptCorners(animal.position);
        return true;
    }

    @Override
    public boolean isOccupied(Vector2d position) {
        for (AbstractWorldMapElement thing: things) {
            if (thing.position.equals(position)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Object objectAt(Vector2d position) {
        for (AbstractWorldMapElement rzecz: things) {
            if (rzecz.position.equals(position)){
                return rzecz;
            }
        }
        return null;
    }

    @Override
    public void remove(Vector2d position) { // pozycja będzie aktualizowana z dodaniem elementu
        for (AbstractWorldMapElement rzecz: things) {
            if (rzecz.position.equals(position)){
                if (rzecz.getClass() == Grass.class) {
                    addGrass(1);
                }
                things.remove(rzecz);
                break;
            }
        }
    }
    private void adaptCorners(Vector2d pos){
        this.upperRight = pos.upperRight(this.upperRight);
        this.lowerLeft = pos.lowerLeft(this.lowerLeft);
    }
    public void addGrass(int ile){
        int size = (int) sqrt(n*10);
        int i=0;
        while (i<ile){
            int x = (int) (Math.random()*size);
            int y = (int) (Math.random()*size);
            Vector2d pos = new Vector2d(x,y);
            if (!isOccupied(pos)){
                things.add(new Grass(pos));
                this.adaptCorners(pos);
                i++;
            }
        }
    }
}
