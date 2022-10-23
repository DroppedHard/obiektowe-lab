package agh.ics.oop;

import java.util.ArrayList;
import java.util.List;

public class OptionsParser {
    static MoveDirection[] parse(String[] moves) {
        List<MoveDirection> movement = new ArrayList<>();
        for (String move : moves) {
            switch (move) {
                case "f", "forward" -> movement.add(MoveDirection.FORWARD);
                case "b", "backward" -> movement.add(MoveDirection.BACKWARD);
                case "l", "left" -> movement.add(MoveDirection.LEFT);
                case "r", "right" -> movement.add(MoveDirection.RIGHT);
            }
        }
        MoveDirection[] odp = new MoveDirection[movement.size()];
        movement.toArray(odp);
        return odp;
    }
}
