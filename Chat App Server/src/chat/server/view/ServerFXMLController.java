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
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author sharno
 */
public class ServerFXMLController extends Application implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private Button switchButton;
    @FXML
    private PieChart pieChart;
    
    private boolean isRunning = true;
    private ServerController serverController;
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("ServerFXML.fxml"));
        primaryStage.setTitle("Server");
        primaryStage.setScene(new Scene(root, 600, 500));
        primaryStage.show();
        
        
//        pieChart.setData(null);
        
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        serverController = new ServerController(this);
    }
    
    @FXML
    private void switchService () {
        if (isRunning) {
            serverController.stopService();
            isRunning = false;
            
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    switchButton.setText("Start");
                }
            });
        } else {
            serverController.startService();
            isRunning = true;
            
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    switchButton.setText("Stop");
                }
            });
        }
    }
}
