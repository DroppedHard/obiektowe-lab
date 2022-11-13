package agh.ics.oop;


public class RectangularMap extends AbstractWorldMap implements IWorldMap, IPositionChangeObserver{

    private final int width;
    private final int height;
//    private final Object[][] mapa;

    public RectangularMap(int width, int height) {
        this.width=width;
        this.height=height;
        this.lowerLeft = new Vector2d(0,0);
        this.upperRight = new Vector2d(width-1, height-1);
//        mapa = new Object[height][width];
    }
    @Override
    public boolean canMoveTo(Vector2d position) {
        if (position.x < 0 || position.x >= width || position.y < 0 || position.y >= height) {
            return false;
        }
//        return mapa[position.y][position.x] == null;
        return elements.get(position) == null;
    }

    @Override
    public void place(Animal animal) {
        if (canMoveTo(animal.position)){
//            mapa[animal.position.y][animal.position.x] = animal;
            elements.put(animal.position, animal);
        }
    }

    @Override
    public boolean isOccupied(Vector2d position) {
//        return mapa[position.y][position.x] != null;
        return elements.get(position) != null;
    }

    @Override
    public AbstractWorldMapElement objectAt(Vector2d position) {
//        return mapa[position.y][position.x];
        return elements.get(position);
    }

    @Override
    public void remove(Vector2d position){
//        mapa[position.y][position.x] = null;
        elements.remove(position);
    }
}
