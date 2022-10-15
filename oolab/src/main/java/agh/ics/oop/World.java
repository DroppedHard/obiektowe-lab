package agh.ics.oop;

import java.util.ArrayList;
import java.util.List;


public class World {
    static void run(List<MoveDirection> movement){
        for (MoveDirection way : movement){
            switch (way) {
                case FORWARD -> System.out.println("Ruch do przodu");
                case BACKWARD -> System.out.println("Ruch do tylu");
                case LEFT -> System.out.println("Ruch w lewo");
                case RIGHT -> System.out.println("Ruch w prawo");
            }
        }

    }
    static void readArgs(String[] args){
//        Scanner scanner = new Scanner(System.in);
//        String str = scanner.nextLine();
        for (String str : args){
            int dl = str.length();
            List<MoveDirection> movement = new ArrayList<>();
            for(int i = 0; i < dl; i++){
                char c = str.charAt(i);
                switch (c) {
                    case 'f' -> movement.add(MoveDirection.FORWARD);
                    case 'b' -> movement.add(MoveDirection.BACKWARD);
                    case 'l' -> movement.add(MoveDirection.LEFT);
                    case 'r' -> movement.add(MoveDirection.RIGHT);
                }
            }
            World.run(movement);
        }
    }
    public static void main(String[] args) {
        System.out.println("""
                System wystartowal
                Podaj argumenty:
                p - ruch w prawo
                l - ruch w lewo
                f - ruch do przodu
                b - ruch do tylu""");
        World.readArgs(args);
        Vector2d position1 = new Vector2d(1,2);
        System.out.println(position1);
        Vector2d position2 = new Vector2d(-2,1);
        System.out.println(position2);
        System.out.println(position1.add(position2));
        MapDirection kierunek = MapDirection.NORTH;
        System.out.println(kierunek);
        System.out.println(kierunek.next().toString());
        System.out.println(kierunek.previous().toString());
        System.out.println(kierunek.toUnitVector().toString());

        System.out.println("System zakonczyl dzialanie");
    }
}
