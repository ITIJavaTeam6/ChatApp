/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chat.server.view;

import chat.database.services.UserService;
import chat.server.controller.ServerController;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

/**
 *
 * @author AmoOOOnA
 */
public class ViewController extends Application {

    UserService userService = new UserService();
    ServerController serverController;
    private boolean isRunning = true;
    private Button btn;
    private Button announceButton;

    @Override
    public void start(Stage stage) {
        try {
            stage.setOnCloseRequest(new EventHandler<WindowEvent>() {

                @Override
                public void handle(WindowEvent event) {
                    System.exit(0);
                }
            });

            Scene scene = new Scene(new Group());
            stage.setTitle("Statistics");
            stage.setWidth(500);
            stage.setHeight(500);

            stage.setResizable(false);

            btn = new Button();
            btn.setLayoutX(210);
            btn.setLayoutY(415);
            btn.setText("Stop");

            btn.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {

                    switchService();
                }
            });

            announceButton = new Button("Announce");
            announceButton.setLayoutX(10);
            announceButton.setLayoutY(415);
            announceButton.setOnAction(new EventHandler<ActionEvent>() {

                @Override
                public void handle(ActionEvent event) {
                    SwingUtilities.invokeLater(new Runnable() {

                        @Override
                        public void run() {
                            String message = JOptionPane.showInputDialog("Enter the announcement message:");
                            if (!message.equals("")) {
                                serverController.serverAnnounce(message);
                            }

                        }
                    });
                }
            });
            ((Group) scene.getRoot()).getChildren().add(announceButton);

            ObservableList<PieChart.Data> pieChartData
                    = FXCollections.observableArrayList(
                            new PieChart.Data("Online", userService.getOnlineStatus()),
                            new PieChart.Data("Offline", userService.getOffLineStatus()),
                            new PieChart.Data("Busy", userService.getBusyStatus()),
                            new PieChart.Data("Away", userService.getAwayStatus()));

//            ObservableList<PieChart.Data> pieChartgender
//                    = FXCollections.observableArrayList(
//                            new PieChart.Data("Males", userService.getMales()),
//                            new PieChart.Data("Females", userService.getFemales()));
//            final PieChart chartgender = new PieChart(pieChartgender);
//            ((Group) scene.getRoot()).getChildren().add(chartgender);
            final PieChart chart = new PieChart(pieChartData);
            chart.setTitle("Server");
            ((Group) scene.getRoot()).getChildren().add(btn);

            new Thread() {

                @Override
                public void run() {
                    try {
                        int onlineCount = userService.getOnlineStatus();
                        int offlineCount = userService.getOffLineStatus();
                        int busyCount = userService.getBusyStatus();
                        int awayCount = userService.getAwayStatus();
                        while (true) {
                            try {
                                Thread.sleep(2000);
                                if (onlineCount != userService.getOnlineStatus()
                                        || offlineCount != userService.getOffLineStatus()
                                        || busyCount != userService.getBusyStatus()
                                        || awayCount != userService.getAwayStatus()) {

                                    onlineCount = userService.getOnlineStatus();
                                    offlineCount = userService.getOffLineStatus();
                                    busyCount = userService.getBusyStatus();
                                    awayCount = userService.getAwayStatus();

                                    ObservableList<PieChart.Data> pieChartData
                                            = FXCollections.observableArrayList(
                                                    new PieChart.Data("Online", onlineCount),
                                                    new PieChart.Data("Offline", offlineCount),
                                                    new PieChart.Data("Busy", busyCount),
                                                    new PieChart.Data("Away", awayCount));

                                    Platform.runLater(new Runnable() {

                                        @Override
                                        public void run() {
                                            chart.setData(pieChartData);
                                        }
                                    });
                                }
                            } catch (InterruptedException ex) {
                                Logger.getLogger(ViewController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(ViewController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }

            }.start();

            ((Group) scene.getRoot()).getChildren().add(chart);
            stage.setScene(scene);

            stage.show();
            serverController = new ServerController(this);
        } catch (SQLException ex) {
            Logger.getLogger(ViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
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
