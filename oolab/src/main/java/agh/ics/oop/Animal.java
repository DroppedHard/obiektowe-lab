package agh.ics.oop;

public class Animal {

    private MapDirection facing = MapDirection.NORTH;
    private Vector2d position = new Vector2d(2,2);

    public Animal(){
    }

    @Override
    public String toString() {
        return position + " " + facing;
    }

    boolean isAt(Vector2d position){
        return this.position == position;
    }

    void move(MoveDirection direction){
        switch (direction){
            case LEFT -> facing = facing.previous();
            case RIGHT -> facing = facing.next();
            case FORWARD -> {
                if (this.onMap(true)) {
                    position = position.add(this.facing.toUnitVector());
                }
            }
            case BACKWARD -> {
                if (this.onMap(false)) {
                    position = position.substract(this.facing.toUnitVector());
                }
            }
        }
    }
    private boolean onMap(boolean where){
        if ((facing == MapDirection.NORTH && where) || (facing == MapDirection.SOUTH && !where)){
            int maxY = 4;
            return position.y + 1 <= maxY;
        } else if ((facing == MapDirection.WEST && where) ||(facing == MapDirection.EAST && !where)) {
            int minX = 0;
            return position.x - 1 >= minX;
        } else if (facing == MapDirection.SOUTH || facing == MapDirection.NORTH) {
            int minY = 0;
            return position.y - 1 >= minY;
        } else {
            int maxX = 4;
            return position.x + 1 <= maxX;
        }
    }
}
