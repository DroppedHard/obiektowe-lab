package agh.ics.oop;


public class World {
    static void run(MoveDirection[] movement, Animal zwierz){
        for (MoveDirection way : movement){
            zwierz.move(way);
//            System.out.println(way);
        }
    }
    public static void main(String[] args) {
        System.out.println("System wystartowal");
        Animal zwierz = new Animal();
        System.out.println(zwierz + " start");
        MoveDirection[] moves = OptionsParser.parse(args);
        World.run(moves, zwierz);
        System.out.println(zwierz + " koniec");
        System.out.println("System zakonczyl dzialanie");
    }
}
