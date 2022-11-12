package agh.ics.oop;

public class Animal extends AbstractWorldMapElement {

    private final IWorldMap worldMap;
    private MapDirection facing = MapDirection.NORTH;

    public Animal(IWorldMap map, Vector2d initialPosition){
        super(initialPosition);
        this.worldMap = map;
        map.place(this);
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
                if (this.worldMap.canMoveTo(position.add(this.facing.toUnitVector()))) {
                    this.worldMap.remove(position);
                    if (this.worldMap.objectAt(position.add(this.facing.toUnitVector())) != null &&
                            this.worldMap.objectAt(position.add(this.facing.toUnitVector())).getClass() == Grass.class) {
                        this.worldMap.remove(position.add(this.facing.toUnitVector()));
                        // zwierzak zjadł trawę (jak przejdzie tyłem to nie zje póki co
                    }
                    position = position.add(this.facing.toUnitVector());
                    this.worldMap.place(this);
                }
            }
            case BACKWARD -> {
                if (this.worldMap.canMoveTo(position.substract(this.facing.toUnitVector()))) {
                    this.worldMap.remove(position);
                    if (this.worldMap.objectAt(position.substract(this.facing.toUnitVector())) != null &&
                            this.worldMap.objectAt(position.substract(this.facing.toUnitVector())).getClass() == Grass.class) {
                        this.worldMap.remove(position.substract(this.facing.toUnitVector()));
                        // zwierzak zjadł trawę (jak przejdzie tyłem to nie zje póki co
                    }
                    position = position.substract(this.facing.toUnitVector());
                    this.worldMap.place(this);
                }
            }
        }
    }
}
