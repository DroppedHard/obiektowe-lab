package agh.ics.oop;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MapBoundaryTest {

    @Test
    void addBoundaryElement() {
        MapBoundary boundary = new MapBoundary();
        BoundaryElement el1 = new BoundaryElement(new Vector2d(2,2), Animal.class);
        boundary.addBoundaryElement(el1);
        assertEquals(boundary.sortedx.last(), boundary.sortedx.first());
        assertEquals(boundary.sortedy.last(), boundary.sortedy.first());
        assertEquals(boundary.sortedx.last(), boundary.sortedy.first());
        assertEquals(boundary.sortedy.last(), boundary.sortedx.first());
        BoundaryElement el2 = new BoundaryElement(new Vector2d(3,3), Grass.class);
        boundary.addBoundaryElement(el2);
        assertEquals(boundary.sortedx.last(), el2);
        assertEquals(boundary.sortedy.last(), el2);
        assertEquals(boundary.sortedx.first(), el1);
        assertEquals(boundary.sortedy.first(), el1);
        BoundaryElement el3 = new BoundaryElement(new Vector2d(3,3), Animal.class);
        boundary.addBoundaryElement(el3);
        assertEquals(boundary.sortedx.last(), el2);
        assertEquals(boundary.sortedy.last(), el2);
        assertEquals(boundary.sortedx.first(), el1);
        assertEquals(boundary.sortedy.first(), el1);
    }

    @Test
    void positionChanged() {
        MapBoundary boundary = new MapBoundary();
        BoundaryElement el1 = new BoundaryElement(new Vector2d(2,2), Animal.class);
        boundary.addBoundaryElement(el1);
        BoundaryElement el2 = new BoundaryElement(new Vector2d(3,3), Grass.class);
        boundary.addBoundaryElement(el2);
        BoundaryElement el3 = new BoundaryElement(new Vector2d(3,3), Animal.class);
        boundary.addBoundaryElement(el3);
        assertEquals(boundary.sortedx.last(), el2);
        assertEquals(boundary.sortedy.last(), el2);
        assertEquals(boundary.sortedx.first(), el1);
        assertEquals(boundary.sortedy.first(), el1);
        // test przenoszenia Animal by był pierwszy
        Vector2d newPos1 = new Vector2d(0,0);
        boundary.positionChanged(el1.position, newPos1);
        el1.position = newPos1;
        assertEquals(boundary.sortedx.first().position, el1.position);
        assertEquals(boundary.sortedx.first().className, el1.className);
        assertEquals(boundary.sortedy.first().position, el1.position);
        assertEquals(boundary.sortedy.first().className, el1.className);
        assertEquals(boundary.sortedx.last(), el2);
        assertEquals(boundary.sortedy.last(), el2);
        // test przenoszenia animal by był ostatni
        Vector2d newPos3 = new Vector2d(10,10);
        boundary.positionChanged(el3.position, newPos3);
        el3.position = newPos3;
        assertEquals(boundary.sortedx.first().position, el1.position);
        assertEquals(boundary.sortedx.first().className, el1.className);
        assertEquals(boundary.sortedy.first().position, el1.position);
        assertEquals(boundary.sortedy.first().className, el1.className);
        assertEquals(boundary.sortedx.last().position, el3.position);
        assertEquals(boundary.sortedx.last().className, el3.className);
        assertEquals(boundary.sortedy.last().position, el3.position);
        assertEquals(boundary.sortedy.last().className, el3.className);
        // próba przeniesienia trawy - nic się nie zmienia
        Vector2d newPos2 = new Vector2d(10,10);
        boundary.positionChanged(el2.position, newPos2);
        el2.position = newPos2;
        assertEquals(boundary.sortedx.first().position, el1.position);
        assertEquals(boundary.sortedx.first().className, el1.className);
        assertEquals(boundary.sortedy.first().position, el1.position);
        assertEquals(boundary.sortedy.first().className, el1.className);
        assertEquals(boundary.sortedx.last().position, el3.position);
        assertEquals(boundary.sortedx.last().className, el3.className);
        assertEquals(boundary.sortedy.last().position, el3.position);
        assertEquals(boundary.sortedy.last().className, el3.className);
    }

    @Test
    void removeBoundaryElement() {
        MapBoundary boundary = new MapBoundary();
        BoundaryElement el1 = new BoundaryElement(new Vector2d(2,2), Animal.class);
        boundary.addBoundaryElement(el1);
        BoundaryElement el2 = new BoundaryElement(new Vector2d(3,3), Grass.class);
        boundary.addBoundaryElement(el2);
        BoundaryElement el3 = new BoundaryElement(new Vector2d(3,3), Animal.class);
        boundary.addBoundaryElement(el3);
        assertEquals(boundary.sortedx.last(), el2);
        assertEquals(boundary.sortedy.last(), el2);
        assertEquals(boundary.sortedx.first(), el1);
        assertEquals(boundary.sortedy.first(), el1);
        boundary.removeBoundaryElement(el1);
        boundary.removeBoundaryElement(el2);
        assertEquals(boundary.sortedx.last(), el3);
        assertEquals(boundary.sortedy.last(), el3);
        assertEquals(boundary.sortedx.first(), el3);
        assertEquals(boundary.sortedy.first(), el3);
    }
}