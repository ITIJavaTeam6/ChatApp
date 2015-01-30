/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chat.server.view;

import chat.server.controller.ServerController;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author AmoOOOnA
 */
public class ViewController extends Application  {

    ServerController serverController;
    private boolean isRunning = true;
    private Button btn;

    @Override
    public void start(Stage stage) {
        Scene scene = new Scene(new Group());
        stage.setTitle("Statistics");
        stage.setWidth(500);
        stage.setHeight(500);

        btn = new Button();
        btn.setLayoutX(210);
        btn.setLayoutY(415);
        btn.setText("StopServer");

        btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                switchService();
            }
        });

        ObservableList<PieChart.Data> pieChartData
                = FXCollections.observableArrayList(
                        new PieChart.Data("Online", 50),
                        new PieChart.Data("Offline", 30),
                        new PieChart.Data("Busy", 40));

        final PieChart chart = new PieChart(pieChartData);
        chart.setTitle("Server");
        ((Group) scene.getRoot()).getChildren().add(btn);

        ((Group) scene.getRoot()).getChildren().add(chart);
        stage.setScene(scene);

        stage.show();
serverController = new ServerController(this);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

   

    private void switchService() {
        if (isRunning) {
            serverController.stopService();
            isRunning = false;

            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    btn.setText("Start");
                }
            });
        } else {
            serverController.startService();
            isRunning = true;

            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    btn.setText("Stop");
                }
            });
        }
    }

}
