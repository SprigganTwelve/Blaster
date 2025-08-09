package com.wireghost;

import java.io.File;
import java.lang.reflect.Constructor;
import java.net.URL;
import java.net.URLClassLoader;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class App extends Application {

    private HBox root;
    private Stage stage;

    private final double SPACE_BETWEEN = 20.0;
    private final double WINDOW_WIDTH = 1180.0;
    private final double WINDOW_HEIGHT = 620.0;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        this.stage = primaryStage;
        stage.initStyle(StageStyle.TRANSPARENT);
        loadScene();
        stage.show();
    }

    private void loadScene() {
        root = new HBox(SPACE_BETWEEN);
        root.getStyleClass().add("root");

        // UI initial
        reloadUI();

        Scene scene = new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT);
        scene.setFill(Color.TRANSPARENT);

        String styleSheetUrl = getClass().getResource("/styles/AppStyle.css").toExternalForm();
        scene.getStylesheets().add(styleSheetUrl);

        // Tap F5 → Complete reload with new classes
        
        scene.setOnKeyPressed(e -> {
            switch (e.getCode()) {
                case F5 -> {
                    System.out.println("... Class dynamic loading ...");
                    reloadUI();
                }
                default -> {
                }
            }
        });

        stage.setScene(scene);
    }
    

    /*
      Loading SideBar and MediaHub from target/classes
     */
    private void reloadUI() {
        try {
            root.getChildren().clear();

            // 📂 Path to (Maven : target/classes)

            File classesDir = new  File(System.getProperty("user.dir"), "target/classes");
            System.out.println(".......ClassDir path :"+classesDir.toURI().toURL() + ".......");
            URL[] urls = { classesDir.toURI().toURL() };

            // New ClassLoader for each reload
            try (URLClassLoader cl = new ChildFirstClassLoader(urls, getClass().getClassLoader())) {

                // dynamic loading - SideBar
                Class<?> sideBarClass = cl.loadClass("com.wireghost.SideBar");
                Constructor<?> sideBarConstructor = sideBarClass.getConstructor(double.class);
                Object sideBar = sideBarConstructor.newInstance(50.0);

                // dynamic loading - MediaHub
                Class<?> mediaHubClass = cl.loadClass("com.wireghost.MediaHub");
                Constructor<?> mediaHubConstructor = mediaHubClass.getConstructor(double.class);
                Object mediaHub = mediaHubConstructor.newInstance(WINDOW_WIDTH);

                root.getChildren().addAll(
                        (javafx.scene.Node) sideBar,
                        (javafx.scene.Node) mediaHub
                );
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
