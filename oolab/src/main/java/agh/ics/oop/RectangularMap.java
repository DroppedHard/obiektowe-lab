package agh.ics.oop;


public class RectangularMap extends AbstractWorldMap implements IWorldMap{

    private final int width;
    private final int height;
    private final Object[][] mapa;

    public RectangularMap(int width, int height) {
        this.width=width;
        this.height=height;
        this.lowerLeft = new Vector2d(0,0);
        this.upperRight = new Vector2d(width-1, height-1);
        mapa = new Object[height][width];
    }
    @Override
    public boolean canMoveTo(Vector2d position) {
        if (position.x < 0 || position.x >= width || position.y < 0 || position.y >= height) {
            return false;
        }
        return mapa[position.y][position.x] == null;
    }

    @Override
    public boolean place(Animal animal) {
        if (canMoveTo(animal.position)){
            mapa[animal.position.y][animal.position.x] = animal;
            return true;
        }
        return false;
    }

    @Override
    public boolean isOccupied(Vector2d position) {
        return mapa[position.y][position.x] != null;
    }

    @Override
    public Object objectAt(Vector2d position) {
        return mapa[position.y][position.x];
    }

    @Override
    public void remove(Vector2d position){
        mapa[position.y][position.x] = null;
    }
}
