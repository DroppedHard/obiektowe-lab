package agh.ics.oop;


public class World {
    public static void main(String[] args) {
        System.out.println("System wystartowal");
        MoveDirection[] directions = OptionsParser.parse(args);
        System.out.println("Args");
        IWorldMap map = new GrassField(10);
        System.out.println("dodano mapę");
        Vector2d[] positions = { new Vector2d(2,2), new Vector2d(3,4)};
        System.out.println("dodano zwierzaki");
        IEngine engine = new SimulationEngine(directions, map, positions);
        System.out.println("Rysujemy mapę");
        engine.run();
        System.out.println("System zakonczyl dzialanie");
    }
}
