package agh.ics.oop;

public class Animal {

    private final IWorldMap worldMap;
    private MapDirection facing = MapDirection.NORTH;
    public Vector2d position;   // Lepiej by była publiczna, dzięki temu dodawanie w RectangularMap jest prostsze.

    public Animal(IWorldMap map, Vector2d initialPosition){
        this.worldMap = map;
        this.position = initialPosition;
        map.place(this);
    }

    @Override
    public String toString() {
        return facing.toString();
    }

    boolean isAt(Vector2d position){
        return this.position == position;
    }

    void move(MoveDirection direction){
        switch (direction){
            case LEFT -> facing = facing.previous();
            case RIGHT -> facing = facing.next();
            case FORWARD -> {
                if (this.worldMap.canMoveTo(position.add(this.facing.toUnitVector()))) {
                    this.worldMap.remove(position);
                    position = position.add(this.facing.toUnitVector());
                    this.worldMap.place(this);
                }
            }
            case BACKWARD -> {
                if (this.worldMap.canMoveTo(position.substract(this.facing.toUnitVector()))) {
                    this.worldMap.remove(position);
                    position = position.substract(this.facing.toUnitVector());
                    this.worldMap.place(this);
                }
            }
        }
    }
}
