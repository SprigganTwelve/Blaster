package com.wireghost;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;

import com.wireghost.layouts.SideBar;
import com.wireghost.utils.ChildFirstClassLoader;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.stage.StageStyle;



public class App extends Application {

    private HBox root;
    private Stage stage;
    private Scene scene;
    private double xOffset;
    private double yOffset;
    private URLClassLoader cl;

    private final double SPACE_IN_BETWEEN = 20.0;
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

        // Move the window whith dragging

        root.setOnMousePressed((event)-> {
            xOffset = event.getSceneX();
            yOffset = event.getSceneY();
            event.consume();
        });

        root.setOnMouseDragged((event)->{
            stage.setX(event.getScreenX() - xOffset );
            stage.setY(event.getScreenY() - yOffset );
            event.consume();
        });

        //---

        stage.show();
    }

    private void loadScene() {
        root = new HBox(SPACE_IN_BETWEEN);
        root.getStyleClass().add("root");

        // UI initial
        reloadUI();

        scene = new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT);
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

            System.out.println(".... Reload with F5 .....");
            File classesDir = new  File(System.getProperty("user.dir"), "target/classes");
            URL[] urls = { classesDir.toURI().toURL() };


            if (cl != null) {
                try {
                    cl.close(); // close the previous one if needed
                } catch (IOException ignored) {}
            }
            cl = new ChildFirstClassLoader(urls, getClass().getClassLoader());

            // New ClassLoader for each reload

            // dynamic loading - SideBar
            Class<?> sideBarClass = cl.loadClass("com.wireghost.layouts.SideBar");
            Constructor<?> sideBarConstructor = sideBarClass.getConstructor(double.class);
            Object sideBar = sideBarConstructor.newInstance(50.0);

                // dynamic loading - SearchBar
            Class<?> searchBarClass = cl.loadClass("com.wireghost.layouts.ExpandedSearchBar");
            Constructor<?> searchBarClassConstructor = searchBarClass.getConstructor(double.class, Font.class);
            Object searchBar = searchBarClassConstructor.newInstance(0.6 * (WINDOW_WIDTH - SideBar.PREF_WIDTH), Font.font("Calibri", FontWeight.BOLD ,15.0  )) ;
            Method getPrefWidthMethod = searchBarClass.getMethod("getPrefMargin");
            double SEARCH_BAR_PREF_MARGIN = (double) getPrefWidthMethod.invoke(searchBar);

                // dynamic loading - MediaHub
            Class<?> mediaHubClass = cl.loadClass("com.wireghost.templates.MediaHub");
            Constructor<?> mediaHubConstructor = mediaHubClass.getConstructor(double.class);
            Object mediaHub = mediaHubConstructor.newInstance(WINDOW_WIDTH);

            VBox mainContainer = new VBox(20.2);
            mainContainer.getChildren().addAll( (javafx.scene.Node) searchBar , (javafx.scene.Node) mediaHub );


            root.getChildren().addAll(
                    (javafx.scene.Node) sideBar,
                    mainContainer
            );

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
