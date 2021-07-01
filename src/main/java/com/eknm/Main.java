package com.eknm;

import com.eknm.controller.Controller;

import com.eknm.view.SliderAnim;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Main class of application
 */
public class Main extends Application {

    /**
     * Coefficient of scale width
     */
    public static double scaleWidth = 1;

    /**
     * Coefficient of scale height
     */
    public static double scaleHeight = 1;

    /**
     * Main scene of application
     */
    public static Scene scene;

    /**
     * Min count  pixels height of scene
     */
    public static final int MIN_HEIGHT = 380;
    /**
     * Min count  pixels width of scene
     */
    public static final int MIN_WIDTH = 320;
    /**
     * Default count  pixels  of scene
     */
    public static final int DEFAULT_SIZE = 500;

    public static void main(String[] args) {
        launch(args);
    }

    /**
     * Started method of application
     */
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/fxml/calculator.fxml"));

        scene = new Scene(fxmlLoader.load());
        Controller controller = fxmlLoader.getController();
        String css = this.getClass().getResource("../../css/styles.css").toExternalForm();
        scene.getStylesheets().add(css);

        stage.setScene(scene);

        stage.setMinHeight(MIN_HEIGHT);
        stage.setMinWidth(MIN_WIDTH);
        stage.setHeight(DEFAULT_SIZE);
        stage.setWidth(DEFAULT_SIZE);
        stage.show();
        controller.scale();

        stage.widthProperty().addListener((observable, oldValue, newValue) ->
        {
            scaleWidth = scene.getWidth() / MIN_WIDTH;
            controller.scale();
        });

        stage.heightProperty().addListener((observable, oldValue, newValue) ->
        {
            scaleHeight = scene.getHeight() / MIN_HEIGHT;
            controller.scale();
        });
        stage.sizeToScene();

       SliderAnim.init(controller.getScrollPane());
    }
}