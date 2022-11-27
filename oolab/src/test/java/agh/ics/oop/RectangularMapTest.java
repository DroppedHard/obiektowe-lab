package agh.ics.oop;

import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.*;

class RectangularMapTest {

    @Test
    void canMoveTo() throws FileNotFoundException {
        RectangularMap map = new RectangularMap(5,5);
        Animal zwierz = new Animal(map, new Vector2d(2,2));
        assertFalse(map.canMoveTo(new Vector2d(2,2)));
        assertFalse(map.canMoveTo(new Vector2d(2,6)));
        assertFalse(map.canMoveTo(new Vector2d(6,2)));
        assertFalse(map.canMoveTo(new Vector2d(-2,2)));
        assertFalse(map.canMoveTo(new Vector2d(2,-2)));
        assertTrue(map.canMoveTo(new Vector2d(2,3)));
    }

    @Test
    void place() throws FileNotFoundException {
        RectangularMap map = new RectangularMap(5,5);
        assertNull(map.objectAt(new Vector2d(2, 2)));
        Animal zwierz = new Animal(map, new Vector2d(2,2));
        assertFalse(map.canMoveTo(new Vector2d(2,2)));
    }
    @Test
    void place_error(){
        RectangularMap map = new RectangularMap(5,5);
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            map.place(new Animal(map, new Vector2d(2,2)));
            map.place(new Animal(map, new Vector2d(2,2)));
        });
        String expectedMessage = "Nie można ruszyć się na pozycję (2,2)";
        String actualMessage = exception.getMessage();
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    void isOccupied() throws FileNotFoundException {
        RectangularMap map = new RectangularMap(5,5);
        assertFalse(map.isOccupied(new Vector2d(2, 2)));
        Animal zwierz = new Animal(map, new Vector2d(2,2));
        assertTrue(map.isOccupied(new Vector2d(2,2)));
    }

    @Test
    void objectAt() throws FileNotFoundException {
        RectangularMap map = new RectangularMap(5,5);
        assertNull(map.objectAt(new Vector2d(2, 2)));
        Animal zwierz = new Animal(map, new Vector2d(2,2));
        assertEquals(map.objectAt(new Vector2d(2,2)).getClass(), Animal.class);
    }

    @Test
    void remove() throws FileNotFoundException {
        RectangularMap map = new RectangularMap(5,5);
        Animal zwierz = new Animal(map, new Vector2d(2,2));
        assertEquals(map.objectAt(new Vector2d(2,2)).getClass(), Animal.class);
        map.remove(new Vector2d(2,2));
        assertNull(map.objectAt(new Vector2d(2,2)));
    }
}