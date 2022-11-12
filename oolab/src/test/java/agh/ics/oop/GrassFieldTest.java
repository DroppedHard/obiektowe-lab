package agh.ics.oop;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GrassFieldTest {
    @Test
    void canMoveTo_whereAnimal() {
        GrassField map = new GrassField(0);
        map.place(new Animal(map, new Vector2d(2,2)));
        assertFalse(map.canMoveTo(new Vector2d(2,2)));
        assertTrue(map.canMoveTo(new Vector2d(2,3)));
    }

    @Test
    void canMoveTo_whereGrass() {
        GrassField map = new GrassField(0);
        map.things.add(new Grass(new Vector2d(2,2)));
        assertTrue(map.canMoveTo(new Vector2d(2,2)));
        assertTrue(map.canMoveTo(new Vector2d(2,3)));
    }

    @Test
    void place() {
        GrassField map = new GrassField(0);
        Vector2d pos = new Vector2d(2,2);
        map.place(new Animal(map, pos));
        assertEquals(map.objectAt(pos).getClass(), Animal.class);
    }

    @Test
    void isOccupied_withAnimal() {
        GrassField map = new GrassField(0);
        Vector2d pos = new Vector2d(2,2);
        map.place(new Animal(map, pos));
        assertTrue(map.isOccupied(pos));
    }

    @Test
    void isOccupied_withGrass() {
        GrassField map = new GrassField(0);
        Vector2d pos = new Vector2d(2,2);
        map.things.add(new Grass(pos));
        assertTrue(map.isOccupied(pos));
    }
    @Test
    void objectAt() {
        GrassField map = new GrassField(0);
        Vector2d pos1 = new Vector2d(2,2);
        Vector2d pos2 = new Vector2d(2,3);
        map.things.add(new Grass(pos1));
        map.place(new Animal(map, pos2));
        assertEquals(map.objectAt(pos1).getClass(), Grass.class);
        assertEquals(map.objectAt(pos2).getClass(), Animal.class);
    }
    // Ten test jest zależny od działania metody isOccupied, nie wiem jak dodać zależność testów...
    @Test
    void remove() {
        GrassField map = new GrassField(0);
        Vector2d pos1 = new Vector2d(2,2);
        Vector2d pos2 = new Vector2d(2,3);
        map.things.add(new Grass(pos1));
        map.place(new Animal(map, pos2));
        assertTrue(map.isOccupied(pos1));
        assertTrue(map.isOccupied(pos2));
        map.remove(pos1);
        map.remove(pos2);
        assertFalse(map.isOccupied(pos1));
        assertFalse(map.isOccupied(pos2));
    }
}