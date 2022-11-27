package agh.ics.oop;

import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.*;

class GrassFieldTest {
    @Test
    void canMoveTo_whereAnimal() throws FileNotFoundException {
        GrassField map = new GrassField(0);
        Animal zwierz = new Animal(map, new Vector2d(2,2));
        assertFalse(map.canMoveTo(new Vector2d(2,2)));
        assertTrue(map.canMoveTo(new Vector2d(2,3)));
    }

    @Test
    void canMoveTo_whereGrass() throws FileNotFoundException {
        GrassField map = new GrassField(0);
        map.elements.put(new Vector2d(2,2), new Grass(new Vector2d(2,2)));
        assertTrue(map.canMoveTo(new Vector2d(2,2)));
        assertTrue(map.canMoveTo(new Vector2d(2,3)));
    }

    @Test
    void place() throws FileNotFoundException {
        GrassField map = new GrassField(0);
        Vector2d pos = new Vector2d(2,2);
        Animal zwierz = new Animal(map, pos);
        assertEquals(map.objectAt(pos).getClass(), Animal.class);
    }
    @Test
    void place_error() throws FileNotFoundException {
        GrassField map = new GrassField(0);
        Vector2d pos = new Vector2d(2,2);
        Animal zwierz = new Animal(map, pos);
        Exception exception = assertThrows(IllegalArgumentException.class, () -> map.place(new Animal(map, pos)));

        String expectedMessage = "Nie można ruszyć się na pozycję (2,2)";
        String actualMessage = exception.getMessage();
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    void isOccupied_withAnimal() throws FileNotFoundException {
        GrassField map = new GrassField(0);
        Vector2d pos = new Vector2d(2,2);
        Animal zwierz = new Animal(map, pos);
        assertTrue(map.isOccupied(pos));
    }

    @Test
    void isOccupied_withGrass() throws FileNotFoundException {
        GrassField map = new GrassField(0);
        Vector2d pos = new Vector2d(2,2);
//        map.things.add(new Grass(pos));
        map.elements.put(pos, new Grass(pos));
        assertTrue(map.isOccupied(pos));
    }
    @Test
    void objectAt() throws FileNotFoundException {
        GrassField map = new GrassField(0);
        Vector2d pos1 = new Vector2d(2,2);
        Vector2d pos2 = new Vector2d(2,3);
        map.elements.put(pos1, new Grass(pos1));
        Animal zwierz = new Animal(map, pos2);
        assertEquals(map.objectAt(pos1).getClass(), Grass.class);
        assertEquals(map.objectAt(pos2).getClass(), Animal.class);
    }
    // Ten test jest zależny od działania metody isOccupied, nie wiem jak dodać zależność testów...
    @Test
    void remove() throws FileNotFoundException {
        GrassField map = new GrassField(0);
        Vector2d pos1 = new Vector2d(2,2);
        Vector2d pos2 = new Vector2d(2,3);
        map.elements.put(pos1, new Grass(pos1));
        Animal zwierz = new Animal(map, pos2);
        assertTrue(map.isOccupied(pos1));
        assertTrue(map.isOccupied(pos2));
        map.remove(pos1);
        map.remove(pos2);
        assertFalse(map.isOccupied(pos1));
        assertFalse(map.isOccupied(pos2));
    }
}