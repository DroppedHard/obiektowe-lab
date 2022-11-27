package agh.ics.oop;

import java.util.HashMap;
import java.util.Map;

public abstract class AbstractWorldMap implements IPositionChangeObserver, IWorldMap{
    protected MapBoundary boundary = new MapBoundary();
    public Vector2d upperRight;
    public Vector2d lowerLeft;

    protected Map<Vector2d, AbstractWorldMapElement> elements = new HashMap<>();
    public String toString() {
        MapVisualizer odp = new MapVisualizer(this);
        return odp.draw(this.lowerLeft, this.upperRight);
    }
    public void positionChanged (Vector2d oldPosition, Vector2d newPosition) {
        boundary.positionChanged(oldPosition, newPosition);
        this.adaptCorners();
    }
    protected void adaptCorners(){
        this.upperRight = this.boundary.sortedx.last().position.upperRight(this.boundary.sortedy.last().position);
        this.lowerLeft = this.boundary.sortedx.first().position.lowerLeft(this.boundary.sortedy.first().position);
    }
    public boolean isOccupied(Vector2d position) {
        return !(elements.get(position) == null);
    }
    public AbstractWorldMapElement objectAt(Vector2d position) {
        return elements.get(position);
    }
}
