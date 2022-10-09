package agh.ics.oop;

import java.util.ArrayList;
import java.util.List;

public class World {
    static void run(List<Direction> movement){
        for (Direction way : movement){
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
            List<Direction> movement = new ArrayList<>();
            for(int i = 0; i < dl; i++){
                char c = str.charAt(i);
                switch (c) {
                    case 'f' -> movement.add(Direction.FORWARD);
                    case 'b' -> movement.add(Direction.BACKWARD);
                    case 'l' -> movement.add(Direction.LEFT);
                    case 'r' -> movement.add(Direction.RIGHT);
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
        System.out.println("System zakonczyl dzialanie");
    }
}
