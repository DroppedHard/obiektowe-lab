package agh.ics.oop.gui;

import agh.ics.oop.*;
import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class App extends Application {
    AbstractWorldMap abstractMap;
    @Override
    public void init(){
        try {
            System.out.println("System wystartowal");
            MoveDirection[] directions = OptionsParser.parse(getParameters().getRaw().toArray(new String[0]));
            System.out.println("Args");
            GrassField grassMap = new GrassField(10);
            abstractMap = grassMap;
            System.out.println("dodano mapę");
            Vector2d[] positions = {new Vector2d(2,2), new Vector2d(3,3)};
//            Vector2d[] positions = {};
            System.out.println("dodano zwierzaki");
            IEngine engine = new SimulationEngine(directions, grassMap, positions);
            System.out.println("Rysujemy mapę");
            engine.run();
            System.out.println("System zakonczyl dzialanie");
        } catch (IllegalArgumentException exception){
            System.out.println(exception.getMessage());
        }
    }

    @Override
    public void start(Stage primaryStage){
        primaryStage.setTitle("oolab");
        StackPane root = new StackPane();
//        Label label = new Label("y/x");

        GridPane grid = new GridPane();
//        grid.add(label, 0,0);
//        grid.add(label1, 1,0);
//        grid.add(label2, 2,0);
//        grid.add(label3, 0,1);
        grid.setGridLinesVisible(true);
        ColumnConstraints cc = new ColumnConstraints(20);
        RowConstraints rc = new RowConstraints(20);
        grid.setAlignment(Pos.CENTER);
        for (int j = abstractMap.lowerLeft.x-1; j <= abstractMap.upperRight.x; j++) {
            grid.getColumnConstraints().add(cc);
        }
        for (int i = abstractMap.upperRight.y+1; i >= abstractMap.lowerLeft.y; i--) {
            grid.getRowConstraints().add(rc);
        }

        for (int i = abstractMap.upperRight.y+1; i >= abstractMap.lowerLeft.y; i--) {

            if (i == abstractMap.upperRight.y+1) {
                Label label = new Label("y/x");
                grid.add(label, 0,0);
                GridPane.setHalignment(label, HPos.CENTER);
            }else {
                Label labeli = new Label(i+"");
                grid.add(labeli,0, abstractMap.upperRight.y - i + 1);
                GridPane.setHalignment(labeli, HPos.CENTER);
            }
            for (int j = abstractMap.lowerLeft.x; j <= abstractMap.upperRight.x; j++) {
                if (i == abstractMap.upperRight.y+1) {
//                    builder.append(drawFrame(j <= upperRight.x));
                    Label labelj = new Label(j+"");
                    grid.add(labelj,j+1 - abstractMap.lowerLeft.x, 0);
                    GridPane.setHalignment(labelj, HPos.CENTER);
                } else {
                    drawObject(grid, new Vector2d(j, i));
//                    Label labelx = new Label((j+1)+"-"+(abstractMap.upperRight.y - i + 1));
//                    grid.add(labelx,j+1, abstractMap.upperRight.y - i + 1);
                }
            }
//            builder.append(System.lineSeparator());
        }


        root.getChildren().add(grid);

        Scene scene = new Scene(root, 400, 400);

        primaryStage.setScene(scene);
        primaryStage.show();
    }
    private void drawObject(GridPane grid, Vector2d pos){
        String result = "";
        if (this.abstractMap.isOccupied(pos)) {
            Object object = this.abstractMap.objectAt(pos);
            if (object != null) {
                result = object.toString();
            }
        }
        Label cell = new Label(result);
        grid.add(cell, pos.x+1 - abstractMap.lowerLeft.x, abstractMap.upperRight.y - pos.y+1);
        GridPane.setHalignment(cell, HPos.CENTER);
    }
}
