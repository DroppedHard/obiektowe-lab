package agh.ics.oop;

public class SimulationEngine implements IEngine{
    private final MoveDirection[] moves;
    private final Animal[] animals;
    private final IWorldMap map;

    public SimulationEngine(MoveDirection[] moves, IWorldMap map, Vector2d[] startingPos) {
        this.moves = moves;
        this.map = map;
        animals = new Animal[startingPos.length];
        for (int i = 0; i < startingPos.length; i++) {
            animals[i] = new Animal(map, startingPos[i]);
        }
        System.out.println(map);
    }
    @Override
    public void run() {
        int i=0;
        for (MoveDirection move: moves) {
            animals[i%animals.length].move(move);
            i+=1;
            System.out.println(map);
        }
    }
}
