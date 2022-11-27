package agh.ics.oop;

import javafx.scene.image.Image;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Grass extends AbstractWorldMapElement implements IMapElement{
    private final Image img = new Image(new FileInputStream("src/main/resources/grass.jpg"));
    public Grass(Vector2d position) throws FileNotFoundException {
        super(position);
    }

    public String toString(){return "*";}

    @Override
    public Image getImg() {
        return img;
    }

    @Override
    public String getLabel() {
        return "Grass " + position;
    }
}
