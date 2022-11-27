package agh.ics.oop;

import static java.lang.Math.sqrt;

public class GrassField extends AbstractWorldMap implements IWorldMap, IPositionChangeObserver{
//    public final List<AbstractWorldMapElement> things = new ArrayList<>();
    private final int n;
    public GrassField(int n){
        this.n = n;
        lowerLeft = new Vector2d(Integer.MAX_VALUE,Integer.MAX_VALUE);
        upperRight = new Vector2d(-Integer.MAX_VALUE,-Integer.MAX_VALUE);
        addGrass(n);
    }
    @Override
    public boolean canMoveTo(Vector2d position) {
        AbstractWorldMapElement el = elements.get(position);
        return el == null || el.getClass() != Animal.class;
    }

    @Override
    public void place(Animal animal) throws IllegalArgumentException{
        if (isOccupied(animal.position)) {
            AbstractWorldMapElement el = elements.get(animal.position);
            if (el.getClass() == Animal.class) {
                throw new IllegalArgumentException("Nie można ruszyć się na pozycję " + animal.position);
            }
            remove(animal.position);
        }
        elements.put(animal.position, animal);
        animal.addObserver(boundary);
        BoundaryElement el = new BoundaryElement(animal.position, Animal.class);
        boundary.addBoundaryElement(el);
        adaptCorners();
    }

    @Override
    public void remove(Vector2d position) { // pozycja będzie aktualizowana z dodaniem elementu
        AbstractWorldMapElement el = elements.get(position);
        if (el.getClass() == Grass.class) {
            addGrass(1);    // liczba kępków trawy na mapie musi się zgadzać
        }
        elements.remove(position);
    }

    public void addGrass(int ile){
        int size = (int) sqrt(n*10);
        int i=0;
        while (i<ile){
            int x = (int) (Math.random()*size);
            int y = (int) (Math.random()*size);
            Vector2d pos = new Vector2d(x,y);
            if (!isOccupied(pos)){
                Grass grass = new Grass(pos);
                elements.put(pos, new Grass(pos));
                BoundaryElement el = new BoundaryElement(grass.position, Grass.class);
                boundary.addBoundaryElement(el);
                adaptCorners();
                i++;
            }
        }
    }
}
