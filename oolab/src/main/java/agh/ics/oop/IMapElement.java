package agh.ics.oop;

import javafx.scene.image.Image;

import java.io.FileNotFoundException;

public interface IMapElement {
    Image getImg() throws FileNotFoundException;
    String getLabel();
}
