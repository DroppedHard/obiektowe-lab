package agh.ics.oop;

import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.*;


public class IntegrationTest {
    @Test
    void movement1_rectangular() throws FileNotFoundException {
        String[] args = {"f","b","r","l"};
        MoveDirection[] directions = OptionsParser.parse(args);
        IWorldMap map = new RectangularMap(10, 5);
        Vector2d[] positions = { new Vector2d(2,2), new Vector2d(3,4) };
        IEngine engine = new SimulationEngine(directions, map, positions, null);
        engine.run();
        assertFalse(map.isOccupied(new Vector2d(2,2)));
        assertFalse(map.isOccupied(new Vector2d(3,4)));
        assertEquals((map.objectAt(new Vector2d(2,3))).toString(), ">");
        assertEquals((map.objectAt(new Vector2d(3,3))).toString(), "<");
        System.out.println(map);
    }
    @Test
    void movement2_rectangular() throws FileNotFoundException {
        String[] args = {"f","b","r","l","f","f","r","r","f","f","f","f","f","f","f","f"};
        MoveDirection[] directions = OptionsParser.parse(args);
        IWorldMap map = new RectangularMap(10, 5);
        Vector2d[] positions = { new Vector2d(2,2), new Vector2d(3,4) };
        IEngine engine = new SimulationEngine(directions, map, positions, null);
        engine.run();
        assertFalse(map.isOccupied(new Vector2d(2,2)));
        assertFalse(map.isOccupied(new Vector2d(2,3)));
        assertFalse(map.isOccupied(new Vector2d(3,3)));
        assertTrue(map.isOccupied(new Vector2d(3,4)));
        assertEquals((map.objectAt(new Vector2d(2,0))).toString(), "v");
        assertEquals((map.objectAt(new Vector2d(3,4))).toString(), "^");
    }
    @Test
    void movement1_grassField() throws FileNotFoundException {
        String[] args = {"f","b","r","l"};
        MoveDirection[] directions = OptionsParser.parse(args);
        IWorldMap map = new GrassField(10);
        Vector2d[] positions = { new Vector2d(2,2), new Vector2d(3,4) };
        IEngine engine = new SimulationEngine(directions, map, positions, null);
        engine.run();
        assertFalse(map.isOccupied(new Vector2d(2,2)));
        assertFalse(map.isOccupied(new Vector2d(3,4)));
        assertEquals((map.objectAt(new Vector2d(2,3))).toString(), ">");
        assertEquals((map.objectAt(new Vector2d(3,3))).toString(), "<");
        System.out.println(map);
    }
    @Test
    void movement2_grassField() throws FileNotFoundException {
        String[] args = {"f","b","r","l","f","f","r","r","f","f","f","f","f","f","f","f"};
        MoveDirection[] directions = OptionsParser.parse(args);
        IWorldMap map = new GrassField(10);
        Vector2d[] positions = { new Vector2d(2,2), new Vector2d(3,4) };
        IEngine engine = new SimulationEngine(directions, map, positions, null);
        engine.run();
        assertFalse(map.isOccupied(new Vector2d(2,2)));
        assertFalse(map.isOccupied(new Vector2d(2,3)));
        assertFalse(map.isOccupied(new Vector2d(3,3)));
        assertFalse(map.isOccupied(new Vector2d(3,4)));
        assertTrue(map.isOccupied(new Vector2d(3,7)));
        assertTrue(map.isOccupied(new Vector2d(2,-1)));
        assertEquals((map.objectAt(new Vector2d(2,-1))).toString(), "v");
        assertEquals((map.objectAt(new Vector2d(3,7))).toString(), "^");
    }
}
