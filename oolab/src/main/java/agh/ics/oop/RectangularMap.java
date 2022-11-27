package agh.ics.oop;


import java.util.NoSuchElementException;

public class RectangularMap extends AbstractWorldMap implements IWorldMap, IPositionChangeObserver{

    private final int width;
    private final int height;

    public RectangularMap(int width, int height) {
        this.width=width;
        this.height=height;
    }
    @Override
    public boolean canMoveTo(Vector2d position) {
        if (position.x < 0 || position.x >= width || position.y < 0 || position.y >= height) {
            return false;
        }
        return elements.get(position) == null;
    }

    @Override
    public void place(Animal animal) throws IllegalArgumentException{
        if (canMoveTo(animal.position)){
            elements.put(animal.position, animal);
            animal.addObserver(boundary);
            BoundaryElement el = new BoundaryElement(animal.position, Animal.class);
            boundary.addBoundaryElement(el);
            adaptCorners();
        } else {
            throw new IllegalArgumentException("Nie można ruszyć się na pozycję " + animal.position);
        }
    }

    @Override
    public void remove(Vector2d position){
        elements.remove(position);
        BoundaryElement el = new BoundaryElement(position, Animal.class);
        boundary.removeBoundaryElement(el);
        try {
            adaptCorners();
        } catch (NoSuchElementException exception) {
            upperRight = new Vector2d(width-1, height-1);
            lowerLeft = new Vector2d(0,0);
        }
    }
}
