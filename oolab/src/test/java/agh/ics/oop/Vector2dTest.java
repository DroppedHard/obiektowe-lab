package agh.ics.oop;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Vector2dTest {
    final int randMin = 1000;
    final int randMax = 100000;
    @Test
    void testToStringNormal() {
        Vector2d pkt1 = new Vector2d(5,6);
        assertEquals(pkt1.toString(), "(5,6)");
    }
    @Test
    void testToStringRandom() {
        int x = randMin + (int) (Math.random()*(randMax-randMin));
        int y = randMin + (int) (Math.random()*(randMax-randMin));
        Vector2d pkt1 = new Vector2d(x,y);
        assertEquals(pkt1.toString(), "("+x+","+y+")");
    }

    @Test
    void precedes() {
        Vector2d pkt1 = new Vector2d(1,1);
        Vector2d pkt2 = new Vector2d(2,2);
        Vector2d pkt3 = new Vector2d(2,3);
        assertTrue(pkt1.precedes(pkt2));
        assertTrue(pkt1.precedes(pkt3));
        assertTrue(pkt1.precedes(pkt1));
        assertFalse(pkt2.precedes(pkt1));
        assertFalse(pkt3.precedes(pkt1));
    }

    @Test
    void follows() {
        Vector2d pkt1 = new Vector2d(1,1);
        Vector2d pkt2 = new Vector2d(2,2);
        Vector2d pkt3 = new Vector2d(2,3);
        assertFalse(pkt1.follows(pkt2));
        assertFalse(pkt1.follows(pkt3));
        assertTrue(pkt1.follows(pkt1));
        assertTrue(pkt2.follows(pkt1));
        assertTrue(pkt3.follows(pkt1));
    }

    @Test
    void upperRight() {
        Vector2d pkt1 = new Vector2d(1,1);
        Vector2d pkt2 = new Vector2d(3,3);
        Vector2d pkt3 = new Vector2d(1,6);
        assertEquals(pkt1.upperRight(pkt2), pkt2);
        assertEquals(pkt2.upperRight(pkt1), pkt2);
        assertEquals(pkt2.upperRight(pkt3), new Vector2d(3,6));
        assertEquals(pkt3.upperRight(pkt2), new Vector2d(3,6));
        assertEquals(pkt1.upperRight(pkt3), pkt3);
        assertEquals(pkt3.upperRight(pkt1), pkt3);
        assertEquals(pkt1.upperRight(pkt1), pkt1);
        assertEquals(pkt2.upperRight(pkt2), pkt2);
        assertEquals(pkt3.upperRight(pkt3), pkt3);
    }

    @Test
    void lowerLeft() {
        Vector2d pkt1 = new Vector2d(1,1);
        Vector2d pkt2 = new Vector2d(3,3);
        Vector2d pkt3 = new Vector2d(1,6);
        assertEquals(pkt1.lowerLeft(pkt2), pkt1);
        assertEquals(pkt2.lowerLeft(pkt1), pkt1);
        assertEquals(pkt2.lowerLeft(pkt3), new Vector2d(1,3));
        assertEquals(pkt3.lowerLeft(pkt2), new Vector2d(1,3));
        assertEquals(pkt1.lowerLeft(pkt3), pkt1);
        assertEquals(pkt3.lowerLeft(pkt1), pkt1);
        assertEquals(pkt1.lowerLeft(pkt1), pkt1);
        assertEquals(pkt2.lowerLeft(pkt2), pkt2);
        assertEquals(pkt3.lowerLeft(pkt3), pkt3);
    }

    @Test
    void add() {
        Vector2d pkt1 = new Vector2d(1,1);
        Vector2d pkt2 = new Vector2d(3,3);
        Vector2d pkt3 = new Vector2d(1,6);
        assertEquals(pkt1.add(pkt2), new Vector2d(4,4));
        assertEquals(pkt2.add(pkt1), new Vector2d(4,4));
        assertEquals(pkt1.add(pkt3), new Vector2d(2,7));
        assertEquals(pkt3.add(pkt1), new Vector2d(2,7));
        assertEquals(pkt3.add(pkt2), new Vector2d(4,9));
        assertEquals(pkt2.add(pkt3), new Vector2d(4,9));
        assertEquals(pkt1.add(pkt1), new Vector2d(2,2));
        assertEquals(pkt2.add(pkt2), new Vector2d(6,6));
        assertEquals(pkt3.add(pkt3), new Vector2d(2,12));
    }

    @Test
    void substract() {
        Vector2d pkt1 = new Vector2d(1,1);
        Vector2d pkt2 = new Vector2d(3,3);
        Vector2d pkt3 = new Vector2d(1,6);
        assertEquals(pkt1.substract(pkt2), new Vector2d(-2, -2));
        assertEquals(pkt2.substract(pkt1), new Vector2d(2, 2));
        assertEquals(pkt1.substract(pkt3), new Vector2d(0, -5));
        assertEquals(pkt3.substract(pkt1), new Vector2d(0, 5));
        assertEquals(pkt2.substract(pkt3), new Vector2d(2, -3));
        assertEquals(pkt3.substract(pkt2), new Vector2d(-2, 3));
        assertEquals(pkt1.substract(pkt1), new Vector2d(0, 0));
        assertEquals(pkt2.substract(pkt2), new Vector2d(0, 0));
        assertEquals(pkt3.substract(pkt3), new Vector2d(0, 0));
    }

    @Test
    void testEquals() {
        Vector2d pkt1 = new Vector2d(1,1);
        assertEquals(pkt1, pkt1);
        assertNotEquals(pkt1, new Vector2d(2, 1));
        assertNotEquals(pkt1, 4);
        assertNotEquals(pkt1, null);
    }

    @Test
    void opposite() {
        Vector2d pkt1 = new Vector2d(1,1);
        Vector2d pkt2 = new Vector2d(3,3);
        assertEquals(pkt1.opposite(), new Vector2d(-1, -1));
        assertEquals(pkt2.opposite(), new Vector2d(-3, -3));
        assertEquals(pkt1.opposite().opposite(), pkt1);
        assertEquals(pkt2.opposite().opposite(), pkt2);
    }
}