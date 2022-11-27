package agh.ics.oop.gui;
import agh.ics.oop.IMapElement;
import javafx.geometry.Pos;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import java.io.FileNotFoundException;

public class GuiElementBox {
    ImageView imgView;
    public VBox box;
    final int imgWidth = 20;
    final int imgHeight = 20;

    public GuiElementBox(IMapElement element) throws FileNotFoundException {
        Image img = element.getImg();
        String labelText = element.getLabel();
        imgView = new ImageView(img);
        imgView.setFitWidth(imgWidth);
        imgView.setFitHeight(imgHeight);
        box = new VBox();
        Text label = new Text(labelText);
        label.setStyle("-fx-font-size: 7");
        box.getChildren().add(imgView);
        box.getChildren().add(label);
        box.setAlignment(Pos.CENTER);
    }
}
