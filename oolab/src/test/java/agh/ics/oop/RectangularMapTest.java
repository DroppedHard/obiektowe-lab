package agh.ics.oop;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class RectangularMapTest {

    @Test
    void canMoveTo() {
        RectangularMap map = new RectangularMap(5,5);
        map.place(new Animal(map, new Vector2d(2,2)));
        assertFalse(map.canMoveTo(new Vector2d(2,2)));
        assertFalse(map.canMoveTo(new Vector2d(2,6)));
        assertFalse(map.canMoveTo(new Vector2d(6,2)));
        assertFalse(map.canMoveTo(new Vector2d(-2,2)));
        assertFalse(map.canMoveTo(new Vector2d(2,-2)));
        assertTrue(map.canMoveTo(new Vector2d(2,3)));
    }

    @Test
    void place() {
        RectangularMap map = new RectangularMap(5,5);
        assertNull(map.objectAt(new Vector2d(2, 2)));
        map.place(new Animal(map, new Vector2d(2,2)));
        assertFalse(map.canMoveTo(new Vector2d(2,2)));
    }

    @Test
    void isOccupied() {
        RectangularMap map = new RectangularMap(5,5);
        assertFalse(map.isOccupied(new Vector2d(2, 2)));
        map.place(new Animal(map, new Vector2d(2,2)));
        assertTrue(map.isOccupied(new Vector2d(2,2)));
    }

    @Test
    void objectAt() {
        RectangularMap map = new RectangularMap(5,5);
        assertNull(map.objectAt(new Vector2d(2, 2)));
        map.place(new Animal(map, new Vector2d(2,2)));
        assertEquals(map.objectAt(new Vector2d(2,2)).getClass(), Animal.class);
    }

    @Test
    void remove() {
        RectangularMap map = new RectangularMap(5,5);
        map.place(new Animal(map, new Vector2d(2,2)));
        assertEquals(map.objectAt(new Vector2d(2,2)).getClass(), Animal.class);
        map.remove(new Vector2d(2,2));
        assertNull(map.objectAt(new Vector2d(2,2)));
    }
}