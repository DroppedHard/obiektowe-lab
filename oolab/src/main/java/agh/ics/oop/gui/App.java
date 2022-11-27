package agh.ics.oop.gui;
import agh.ics.oop.*;
import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import java.io.FileNotFoundException;

public class App extends Application implements IPositionChangeObserver{
    AbstractWorldMap abstractMap;
    ColumnConstraints cc = new ColumnConstraints(40);
    RowConstraints rc = new RowConstraints(40);
    GridPane grid;
    @Override
    public void init(){
        try {
            System.out.println("System wystartowal");
            MoveDirection[] directions = OptionsParser.parse(getParameters().getRaw().toArray(new String[0]));
            System.out.println("Args");
            GrassField grassMap = new GrassField(10);
            abstractMap = grassMap;
            System.out.println("dodano mapę");
            Vector2d[] positions = {new Vector2d(2,2), new Vector2d(3,4)};
//            Vector2d[] positions = {};
            System.out.println("dodano zwierzaki");
            SimulationEngine engine = new SimulationEngine(directions, grassMap, positions, this);
            System.out.println("Rysujemy mapę");
            Thread engineThread = new Thread(engine);
            engineThread.start();
            System.out.println("System zakonczyl dzialanie");
        } catch (IllegalArgumentException exception){
            System.out.println(exception.getMessage());
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public void start(Stage primaryStage) throws FileNotFoundException {
        primaryStage.setTitle("oolab");
        StackPane root = new StackPane();
        grid = new GridPane();
        grid.setGridLinesVisible(true);
        centerGrid();
        addElements();
        root.getChildren().add(grid);
        Scene scene = new Scene(root, 600, 600);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    private void centerGrid(){
        grid.setAlignment(Pos.CENTER);
        for (int j = abstractMap.lowerLeft.x-1; j <= abstractMap.upperRight.x; j++) {
            grid.getColumnConstraints().add(cc);
        }
        for (int i = abstractMap.upperRight.y+1; i >= abstractMap.lowerLeft.y; i--) {
            grid.getRowConstraints().add(rc);
        }
    }
    public void addElements() throws FileNotFoundException {
        for (int i = abstractMap.upperRight.y+1; i >= abstractMap.lowerLeft.y; i--) {
            if (i == abstractMap.upperRight.y+1) {
                addCell(0, 0, "y/x");
            } else {
                addCell(0, abstractMap.upperRight.y - i + 1, i+"");
            }
            for (int j = abstractMap.lowerLeft.x; j <= abstractMap.upperRight.x; j++) {
                if (i == abstractMap.upperRight.y+1) {
                    addCell(j+1 - abstractMap.lowerLeft.x, 0, j+"");
                } else {
                    drawObject(grid, new Vector2d(j, i));
                }
            }
        }
    }
    private void changeElements() throws FileNotFoundException {
        for (int i = abstractMap.upperRight.y; i >= abstractMap.lowerLeft.y; i--) {
            for (int j = abstractMap.lowerLeft.x+1; j <= abstractMap.upperRight.x; j++) {
                drawObject(grid, new Vector2d(j, i));
            }
        }
    }
    private void addCell(int colIdx, int rowIdx, String text){
        Label label = new Label(text);
        label.setStyle("-fx-font-size: 20");
        grid.add(label, colIdx, rowIdx);
        GridPane.setHalignment(label, HPos.CENTER);
    }
    private void drawObject(GridPane grid, Vector2d pos) throws FileNotFoundException {
        GuiElementBox el = null;
        if (this.abstractMap.isOccupied(pos)) {
            Object object = this.abstractMap.objectAt(pos);
            if (object != null) {
                el = new GuiElementBox((IMapElement) object);
            }
        }
        if (el != null) {
            grid.add(el.box, pos.x+1 - abstractMap.lowerLeft.x, abstractMap.upperRight.y - pos.y+1);
            GridPane.setHalignment(el.box, HPos.CENTER);
        }
    }
    public void clearMap(){
        grid.getChildren().clear();
    }

    @Override
    public void positionChanged(Vector2d oldPosition, Vector2d newPosition) {

    }
}
