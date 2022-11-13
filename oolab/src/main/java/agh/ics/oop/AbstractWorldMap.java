package agh.ics.oop;

import java.util.HashMap;
import java.util.Map;

abstract class AbstractWorldMap implements IPositionChangeObserver{
    protected Vector2d upperRight;
    protected Vector2d lowerLeft;

    protected Map<Vector2d, AbstractWorldMapElement> elements = new HashMap<>();
    public String toString() {
        MapVisualizer odp = new MapVisualizer((IWorldMap) this);
        return odp.draw(this.lowerLeft, this.upperRight);
    }

    public void positionChanged (Vector2d oldPosition, Vector2d newPosition) {
        AbstractWorldMapElement el = elements.get(oldPosition);
        elements.remove(oldPosition);
        elements.put(newPosition, el);
        adaptCorners(newPosition);
    }
    protected void adaptCorners(Vector2d pos){
        this.upperRight = pos.upperRight(this.upperRight);
        this.lowerLeft = pos.lowerLeft(this.lowerLeft);
    }
}
